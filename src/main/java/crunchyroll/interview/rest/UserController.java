package crunchyroll.interview.rest;

import crunchyroll.interview.models.Product;
import crunchyroll.interview.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/v1")
public class APIController{
  private static HashMap<String, User> users = new HashMap<>();
  private static HashMap<String, Product> products = new HashMap<>();
  @GetMapping

}
