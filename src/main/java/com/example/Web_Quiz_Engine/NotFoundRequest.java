package com.example.Web_Quiz_Engine;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (code = HttpStatus.NOT_FOUND)
public class NotFoundRequest extends RuntimeException{
}
