package pe.edu.upc.moviles.buholector.utils;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utilitarios {

  public static String idTransaction = UUID.randomUUID().toString();

}
