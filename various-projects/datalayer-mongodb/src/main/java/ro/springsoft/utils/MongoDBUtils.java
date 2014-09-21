package ro.springsoft.utils;

import com.mongodb.*;
import ro.springsoft.conf.ModuleConfig;

import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Miroslav MARKO <miromarko@gmail.com>
 */
public final class MongoDBUtils {
    private static final short NR_RETRIES = Short.parseShort(ModuleConfig
            .getProperty("nrRetriesMongo"));
    private static final short THREAD_SLEEP = Short.parseShort(ModuleConfig
            .getProperty("mongoRetriesThreadSleep"));
    private static final Logger LOG = Logger.getLogger(MongoDBUtils.class
            .getName());

    private MongoDBUtils() {
        // not called - this is a common class
    }

    public static void safeInsert(DBCollection collection,
                                  DBObject... insertObj) {

        for (int retries = 0; retries < NR_RETRIES; retries++) {
            try {
                collection.insert(insertObj, WriteConcern.ACKNOWLEDGED);
                break;
            } catch (DuplicateKeyException e) {
                LOG.log(Level.WARNING, "Document {0} already inserted",
                        insertObj);
                LOG.warning(e.toString());
            } catch (MongoException e) {
                LOG.warning(e.toString());
                try {
                    LOG.log(Level.WARNING,
                            "Retrying insert nr: {0} insetObject: {1}",
                            new Object[]{retries, insertObj});
                    Thread.sleep(THREAD_SLEEP);
                } catch (InterruptedException ex) {
                    LOG.log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * @param hosts space separated list
     * @param ports space separated list
     */
    public static MongoClient getMongoClient(String hosts, String ports) {
        int connPerHost = Integer.parseInt(ModuleConfig
                .getProperty("connectionsPerHost"));
        int thMultiplier = Integer.parseInt(ModuleConfig
                .getProperty("threadsAllowedToBlockForConnectionMultiplier"));

        MongoClientOptions options = MongoClientOptions.builder()
                .connectionsPerHost(connPerHost)
                .threadsAllowedToBlockForConnectionMultiplier(thMultiplier).build();

        return new MongoClient(getMongoSeeds(hosts, ports), options);
    }

    public static MongoClient getMongoClient() {
        int connPerHost = Integer.parseInt(ModuleConfig
                .getProperty("connectionsPerHost"));
        int thMultiplier = Integer.parseInt(ModuleConfig
                .getProperty("threadsAllowedToBlockForConnectionMultiplier"));

        MongoClientOptions options = MongoClientOptions.builder()
                .connectionsPerHost(connPerHost)
                .threadsAllowedToBlockForConnectionMultiplier(thMultiplier)
                .build();

        String hosts = ModuleConfig.getProperty("mongoHosts");
        String ports = ModuleConfig.getProperty("mongoPorts");

        return new MongoClient(getMongoSeeds(hosts, ports), options);

    }

    private static List<ServerAddress> getMongoSeeds(String ahosts,
                                                     String aports) {
        // get space delimited hosts and ports
        String[] hosts = ahosts.split("\\ ");
        String[] ports = aports.split("\\ ");

        List<ServerAddress> seeds = new LinkedList<>();
        if (ports == null || hosts.length != ports.length) {
            LOG.severe("Different nr. of hosts and ports or parsed hosts|ports = null !!!");
        } else {
            for (int i = 0; i < hosts.length; i++) {
                try {
                    seeds.add(new ServerAddress(hosts[i], Integer
                            .parseInt(ports[i])));
                } catch (UnknownHostException ex) {
                    LOG.severe(ex.toString());
                    LOG.log(Level.SEVERE, "Cannot parse mongo port: {0}",
                            ports[i]);
                }
            }
        }
        return seeds;
    }

    private static String getPasswordHash(String digest, String password) {
        String passwordHash = password;
        if (digest != null && !digest.equals("")) {
            try {
                MessageDigest md = MessageDigest.getInstance(digest);

                byte[] passwordByte = password.getBytes(Charset
                        .forName(ModuleConfig.getProperty("pass_encoding")));
                byte[] hash = md.digest(passwordByte);

                passwordHash = new String(Base64Coder.encode(hash));
            } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                LOG.severe(noSuchAlgorithmException.getMessage());
            }
        }
        return passwordHash;
    }

    public static String getPasswordHash(String password) {
        return getPasswordHash(ModuleConfig.getProperty("pass_digest"),
                password);
    }
}