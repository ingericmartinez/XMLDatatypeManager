package app.validators.dd;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext; 
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;


public class SelectEntry {
   DirContext  ldapContext = null;
  public SelectEntry(String login,String pass)
    {
        try
        {
       String url = "ldap://telcel.di.sds.oficinarquitectura.test";
       String conn_type = "simple";
       String dn  = "cn=Administrador,dc=telcel,dc=com,dc=mx";
       String password = "d@rte";
      // String dn  = "uid="+login+",ou=people,dc=telcel,dc=com,dc=mx";
//       String dn  = "uid="+login+",ou=people,dc=telcel,dc=com,dc=mx";
//       String password = pass;
       Hashtable<String,String> environment = new Hashtable<String, String>();

        environment.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        environment.put(Context.PROVIDER_URL,url);         
        environment.put(Context.SECURITY_AUTHENTICATION,conn_type);         
        environment.put(Context.SECURITY_PRINCIPAL,dn);
        environment.put(Context.SECURITY_CREDENTIALS, password);

         ldapContext = new InitialDirContext(environment);
         System.out.println("Bind successful LDAP");
         
                  
         
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }
  
  
  //desarrollador
  public boolean getEntryDesarrollador(String user) throws NamingException
  {
                 System.out.println("entre a LDAP query, usuario LDAP: " + user);
                 String name = "";
                 
                 if(null == user)
                                return false;
                 
                 if(user.equals(""))
                                return false;     
               
                 Attribute atb = null;
  try
  {
   SearchControls searcCon = new SearchControls();
   searcCon.setSearchScope(SearchControls.OBJECT_SCOPE);
   @SuppressWarnings("all")
   NamingEnumeration results =  
   ldapContext.search("cn=desarrollador,ou=groups,dc=telcel,dc=com,dc=mx","cn=desarrollador",searcCon);

  if(results != null)
  {
   while(results.hasMore())
   {
    SearchResult res = (SearchResult)results.next(); 
    Attributes atbs = res.getAttributes();
    atb = atbs.get("uniqueMember");

 
   }
  }
  else
  { System.out.println("fail"); }
  
  
  //inicio ldap desarrollador
  for(int i=0;i < atb.size();i++)
  {             
                 //System.out.println(atb.get(i));
                 String []cad = atb.get(i).toString().split(",");
                 String []uid = cad[0].toString().split("=");
               
               if(uid[1].equals(user))
               {
                              System.out.println("usuario valido desarrollador");
                              return true;
               } 
               
  }
  //fin  
  }
  catch(Exception excption)
  {
                 
  }
  
  

   return false;
  }
  
  
  // lider de proyecto
  public boolean getEntryProyecto(String user) throws NamingException
  {
                 System.out.println("entre a LDAP query, usuario LDAP: " + user);
                 String name = "";
                 
                 if(null == user)
                                return false;
                 
                 if(user.equals(""))
                                return false;     
               
                 Attribute atb = null;
  try
  {
   SearchControls searcCon = new SearchControls();
   searcCon.setSearchScope(SearchControls.OBJECT_SCOPE);
   @SuppressWarnings("all")
   NamingEnumeration results =  
   ldapContext.search("cn=liderdeproyecto,ou=groups,dc=telcel,dc=com,dc=mx","cn=liderdeproyecto",searcCon);

  if(results != null)
  {
   while(results.hasMore())
   {
    SearchResult res = (SearchResult)results.next(); 
    Attributes atbs = res.getAttributes();
    atb = atbs.get("uniqueMember");

 
   }
  }
  else
  { System.out.println("fail"); }
  
  
  //inicio ldap desarrollador
  for(int i=0;i < atb.size();i++)
  {             
                 String []cad = atb.get(i).toString().split(",");
                 String []uid = cad[0].toString().split("=");
               
               if(uid[1].equals(user))
               {
                              System.out.println("usuario valido lider de proyecto");
                              return true;
               }            
  }
  //fin  
  }
  catch(Exception excption)
  {
                 
  }
  
  

   return false;
  }
  
  
  //oficina de arquitectura
  public boolean getEntryArquitectura(String user) throws NamingException
  {
                 System.out.println("entre a LDAP query, usuario LDAP: " + user);
                 String name = "";
                 
                 if(null == user)
                                return false;
                 
                 if(user.equals(""))
                                return false;     
               
                 Attribute atb = null;
  try
  {
   SearchControls searcCon = new SearchControls();
   searcCon.setSearchScope(SearchControls.OBJECT_SCOPE);
   @SuppressWarnings("all")
   NamingEnumeration results =  
   ldapContext.search("cn=oficinaarquitectura,ou=groups,dc=telcel,dc=com,dc=mx","cn=oficinaarquitectura",searcCon);

  if(results != null)
  {
   while(results.hasMore())
   {
    SearchResult res = (SearchResult)results.next(); 
    Attributes atbs = res.getAttributes();
    atb = atbs.get("uniqueMember");

 
   }
  }
  else
  { System.out.println("fail"); }
  
  
  //inicio ldap desarrollador
  for(int i=0;i < atb.size();i++)
  {             
                 String []cad = atb.get(i).toString().split(",");
                 String []uid = cad[0].toString().split("=");
               
               if(uid[1].equals(user))
               {
                              System.out.println("usuario valido arquitectura");
                              return true;
               } 
               
  }
  
  }
  catch(Exception excption)
  {
                 
  }
  
  

   return false;
  }
  
  
  //por ver si se usa
  public String getName(String user)
  {
                
                 String name = "";
                 
                 if(null == user)
                                return "vacio";
                 
                 if(user.equals(""))
                                return "vacio"; 
               
  try
  {
   SearchControls searcCon = new SearchControls();
   searcCon.setSearchScope(SearchControls.OBJECT_SCOPE);
   @SuppressWarnings("all")
   NamingEnumeration results =  
   ldapContext.search("cn=desarrollador,ou=groups,dc=telcel,dc=com,dc=mx","cn=desarrollador",searcCon);
  
  if(results != null)
  {
   while(results.hasMore())
   {
    SearchResult res = (SearchResult)results.next(); 
  
    Attributes atbs = res.getAttributes();
  
    Attribute atb = atbs.get("uniqueMember");
    name = (String)atb.get();
   
   }
  }
  else
  {  }
  }
  catch(Exception excption)
  { }
   return name;
  }
   
  //get usuario
  public String getEntry(String usuario)
  {
	  String nombre ="";
  try
  {
   SearchControls searcCon = new SearchControls();
   searcCon.setSearchScope(SearchControls.SUBTREE_SCOPE);
   NamingEnumeration results =  
   ldapContext.search("uid="+usuario+",ou=people,dc=telcel,dc=com,dc=mx","(uid="+usuario+")",searcCon);   
  if(results != null)
  {
   while(results.hasMore())
   {
    SearchResult res = (SearchResult)results.next(); 
    Attributes atbs = res.getAttributes();
    Attribute atb = atbs.get("cn");
     nombre = (String)atb.get();
  
   }
  }
  else
  {
    
  }
  }
  catch(Exception excption)
  {

  }
  
  return nombre;
  }
  
  
  //checar usuario en los grupos LDAP
  public String getEntryDeptos(String user) throws NamingException
  {
	 
	  String name = "";
	  
	  if(null == user)
		  return "";
	  
	  if(user.equals(""))
		  return "";	
	 
	  Attribute atb = null;
	  
	  //grupos exluidos
	  //"oficinaarquitectura","liderdeproyecto","di-sds-gsac-darte","desarrollador","jefedepartamento","lectoradmin","snapshots",
	  //"sonar-users","sonar-users","wsrr-admin","wsrr-business","wsrr-development","wsrr-operations","wsrr-soagovernance","wsrr-user",,"integrador"	
	  String []deptos = {
			  "di-sds-gcrm-dsrvcte","di-sds-gsa-darte","di-sds-gscrm-dlscrm","administraciondsie",
			  "auditorscv","dicr-disicr-gicpisea-dipgac","di-di-gcrm-dsrvcte","di-di-gsrh-dsrh",
			  "di-di-gsyam2k-dsuycp","di-sdr-geg-dgci","di-sdr-ggc-dgrc","di-sdr-ggc-dsc","di-sds-gds-dcol",
			  "di-sds-gds-dsrr","di-sds-gds-dst","di-sds-gsac-cam","di-sds-gsac-dsa",
			  "di-sds-gsac-dsaf","di-sds-gsac-dsam2k","di-sds-gsac-dsapp","di-sds-gsac-dsc","di-sds-gsac-dsie",
			  "di-sds-gsce-dcem","di-sds-gsce-dsrvcte","di-sds-gsce-dvtae2","di-sds-gsce-dvtae","di-sds-gsce-sevtae",
			  "di-sds-gscrm-dadcrm","di-sds-gscrm-dccrm","di-sds-gscrm-dicrm","di-sds-gscrm-dlscrm","di-sds-gsfi-dldi",
			  "di-sds-gsfi-dsc","di-sds-gsfi-dwhvycan","di-sds-gsfi-fi","di-sst-gosamsl-dswi"		  
			  };
	  
	  
	  for(int j =0; j < deptos.length;j++)
	  {
		  try
		  {
		   SearchControls searcCon = new SearchControls();
		   searcCon.setSearchScope(SearchControls.OBJECT_SCOPE);
		   @SuppressWarnings("all")
		   NamingEnumeration results =  
		   ldapContext.search("cn="+deptos[j]+",ou=groups,dc=telcel,dc=com,dc=mx","cn="+deptos[j]+"",searcCon);
		  
		   
		
		  if(results != null)
		  {
			  
		   while(results.hasMore())
		   {
		    SearchResult res = (SearchResult)results.next(); 
		    Attributes atbs = res.getAttributes();
		    atb = atbs.get("uniqueMember");
		 
		   }
		  }
		  else
		  {  
		  }
		  		  
		  
		  for(int i=0;i < atb.size();i++)
		  {	  
			  
			  //filas del ldap
			  String []cad = atb.get(i).toString().split(",");
			  String []uid = cad[0].toString().split("=");
			
			 if(uid[1].equals(user))
			 {
				 
				
				 //primera ocurrencia LDAP
				 if(!name.equals(""))
						 {
					 continue;
					 
						 }
				 else{
					 
					 name = deptos[j];
				 }
			 } 
			
		  }
		
		  }
		  catch(Exception excption)
		  {
			  
		  }  		  
	  } 

   return name;
  }
  
  
  public boolean selectPassword(String user, String pass)
  {
      try
      {
     String url = "ldap://telcel.di.sds.oficinarquitectura.test";
     String conn_type = "simple";
     String dn  = "uid="+user+",ou=people,dc=telcel,dc=com,dc=mx";// uid="+user+",ou=people,dc=telcel,dc=com,dc=mx
     String password = pass;
     Hashtable<String,String> environment = new Hashtable<String, String>();

      environment.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
      environment.put(Context.PROVIDER_URL,url);         
      environment.put(Context.SECURITY_AUTHENTICATION,conn_type);         
      environment.put(Context.SECURITY_PRINCIPAL,dn);
      environment.put(Context.SECURITY_CREDENTIALS, password);

       ldapContext = new InitialDirContext(environment);
      
       return true;
                
       
      }
      catch(Exception exception)
      {
         
          return false;
      }
      
     
  }
  
  
 
public static void main(String[] args)
{       
 //String res = new selectEntry().getEntry("eric");
//boolean res;
//try {
//               res = new SelectEntry().getEntryDesarrollador("4320","123");
//               res = new SelectEntry().getEntryProyecto("4320","123");
//               res = new SelectEntry().getEntryArquitectura("4320","123");
//} catch (NamingException e) {
//               // TODO Auto-generated catch block
//               e.printStackTrace();
//}

 
}
}
