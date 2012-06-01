package results;

import play.libs.MimeTypes;
import play.mvc.Http.Request;
import play.mvc.Http.Response;
import play.templates.TemplateLoader;

public class BadRequest extends play.mvc.results.BadRequest {

	@Override
	public void apply(Request request, Response response) {
		try {
			super.apply(request, response);
			response.contentType = MimeTypes.getContentType("xx.html");
			String errorHtml = TemplateLoader.load("errors/400.html").render();
			response.out.write(errorHtml.getBytes(getEncoding()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
