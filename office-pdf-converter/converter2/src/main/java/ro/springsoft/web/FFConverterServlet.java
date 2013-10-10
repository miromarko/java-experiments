package ro.springsoft.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.document.DocumentFormat;

/**
 * Servlet implementation class FFConverterServlet
 */
public class FFConverterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FFConverterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		final Logger logger = Logger.getLogger(getClass().getName());

		WebappContext webappContext = WebappContext.get(getServletContext());
		ServletFileUpload fileUpload = webappContext.getFileUpload();
		OfficeDocumentConverter converter = webappContext
				.getDocumentConverter();

		String strInFile = request.getParameter("infile");
		String strOutFile = request.getParameter("outfile");

		String inputExtension = request.getParameter("infile_type");//FilenameUtils.getExtension(strInFile);
		String outputExtension = request.getParameter("outfile_type");//FilenameUtils.getExtension(strOutFile);

		File inputFile = new File(strInFile);
		File outputFile = new File(strOutFile);

		response.setContentType("text/html");
		try {
			DocumentFormat outputFormat = converter.getFormatRegistry()
					.getFormatByExtension(outputExtension);
			long startTime = System.currentTimeMillis();
			converter.convert(inputFile, outputFile);
			long conversionTime = System.currentTimeMillis() - startTime;
			logger.info(String.format(
					"successful conversion: %s [%db] to %s in %dms",
					inputExtension, inputFile.length(), outputExtension,
					conversionTime));
			 PrintWriter out = response.getWriter();
			 response.setStatus(response.SC_OK);
			 out.println(" OK ");
		} catch (Exception exception) {
			logger.severe(String.format(
					"failed conversion: %s [%db] to %s; %s; input file: %s",
					inputExtension, inputFile.length(), outputExtension,
					exception, inputFile.getName()));
			response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
			throw new ServletException("conversion failed", exception);
		} 
	}
}
