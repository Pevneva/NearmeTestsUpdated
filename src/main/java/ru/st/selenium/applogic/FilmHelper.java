package ru.st.selenium.applogic;

import java.util.List;

import ru.st.selenium.applogic.FilmHelper;
import ru.st.selenium.model.Film;

public class FilmHelper extends DriverBasedHelper {

  public FilmHelper(ApplicationManager manager) {
    super(manager.getWebDriver());
  }

//  @Override
  public void create(Film film) {
    // TODO Auto-generated method stub

  }

//  @Override
  public void delete(Film film) {
    // TODO Auto-generated method stub

  }

 // @Override
  public List<Film> search(String title) {
    // TODO Auto-generated method stub
    return null;
  }

}
