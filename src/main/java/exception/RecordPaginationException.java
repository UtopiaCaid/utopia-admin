package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
			value = HttpStatus.NOT_FOUND,
			reason = "Could not find requested record offset and page size"
)

public class RecordPaginationException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4930328737089017215L;


	
}
