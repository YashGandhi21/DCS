package Tempconvcalc;


/**
* Tempconvcalc/_TempcalcImplBase.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Tempconvcalc.idl
* Monday, 14 September, 2020 11:52:57 AM IST
*/

public abstract class _TempcalcImplBase extends org.omg.CORBA.portable.ObjectImpl
                implements Tempconvcalc.Tempcalc, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors
  public _TempcalcImplBase ()
  {
  }

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("get_celsius", new java.lang.Integer (0));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // Tempconvcalc/Tempcalc/get_celsius
       {
         String symbol = in.read_string ();
         double $result = (double)0;
         $result = this.get_celsius (symbol);
         out = $rh.createReply();
         out.write_double ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:Tempconvcalc/Tempcalc:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }


} // class _TempcalcImplBase
