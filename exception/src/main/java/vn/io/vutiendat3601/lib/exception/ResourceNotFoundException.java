package vn.io.vutiendat3601.lib.exception;

import java.util.Map;
import java.util.Optional;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public final class ResourceNotFoundException extends RuntimeException {
  private final Map<String, String> params;

  public ResourceNotFoundException(String message, Map<String, String> params) {
    super(message);
    this.params = Map.copyOf(Optional.ofNullable(params).orElse(Map.of()));
  }
}
