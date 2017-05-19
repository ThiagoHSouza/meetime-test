
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Singleton;

import play.http.HttpErrorHandler;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import play.mvc.Results;

@Singleton
public class ErrorHandler implements HttpErrorHandler {

	public CompletionStage<Result> onClientError(RequestHeader request, int statusCode, String message) {
		return CompletableFuture.completedFuture(Results.status(statusCode, message));
	}

	public CompletionStage<Result> onServerError(RequestHeader request, Throwable exception) {
		return CompletableFuture.completedFuture(Results.internalServerError(formatMessageError(exception.getMessage())));
	}
	
	private String formatMessageError(String msg){
		return msg.replaceFirst("[^\\s]+[\\s]", "");
	}
}
