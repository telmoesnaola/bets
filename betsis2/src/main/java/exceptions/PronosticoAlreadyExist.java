package exceptions;
public class PronosticoAlreadyExist extends Exception {
 private static final long serialVersionUID = 1L;
 
 public PronosticoAlreadyExist()
  {
    super();
  }
  /**This exception is triggered if the event has already finished
  *@param s String of the exception
  */
  public PronosticoAlreadyExist(String s)
  {
    super(s);
  }
}