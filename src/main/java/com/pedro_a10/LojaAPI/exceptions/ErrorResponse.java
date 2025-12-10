package com.pedro_a10.LojaAPI.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {
  private LocalDateTime localDateTime;
  private int status;
  private String error;
  private String path;
}
