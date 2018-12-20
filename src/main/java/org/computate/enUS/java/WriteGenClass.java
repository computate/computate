package org.computate.enUS.java;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;

/**	
 *	For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 **/
public class WriteGenClass extends WriteClass {

	protected String classDirPathGen;

	protected String classPathGen;

	protected String classPathApiGen;

	protected String classPathPageGen;

	protected File classDirGen;

	protected File classFileGen;

	protected File classFileApi;

	protected File classFilePage;

	protected StringBuilder s = new StringBuilder();

	protected SolrDocument doc;

	protected String classCanonicalName;

	protected String classSimpleNameGen;

	protected String classSuperSimpleName;

	protected String classSuperSimpleNameGeneric;

	protected String classSuperCanonicalNameGeneric;

	protected String classPackageName;

	protected String classSimpleName;

	protected String classSuperCanonicalName;

	protected String classPageUri;

	protected String classApiUri;

	protected String classComment;

	protected String classVarPrimaryKey;

	protected List<String> classImportsGen;

	protected List<String> classImportsGenApi;

	protected List<String> classImportsGenPage;

	protected List<String> classParameterTypeNames;

	protected List<String> classSuperParameterTypeNames;

	protected Boolean classExtendsGen;

	protected Boolean classBaseExtendsGen;

	protected Boolean classInitDeep;

	protected Boolean classIndexed;

	protected Boolean classExtendsBase;

	protected Boolean classIsBase;

	protected Boolean classSaved;

	protected Boolean classModel;

	protected Boolean classApi;

	protected Boolean classPage;

	protected Boolean classRolesFound;

	protected List<String> classRoles;

	protected StringWriter wInitDeep;

	protected PrintWriter codeInitDeep;

	protected StringWriter wSiteRequest;

	protected PrintWriter codeSiteRequest;

	protected StringWriter wIndex;

	protected PrintWriter codeIndex;

	protected StringWriter wObtain;

	protected PrintWriter codeObtain;

	protected StringWriter wAttribute;

	protected PrintWriter codeAttribute;

	protected StringWriter wPut;

	protected PrintWriter codePut;

	protected StringWriter wPopulate;

	protected PrintWriter codePopulate;

	protected StringWriter wExists;

	protected PrintWriter codeExists;

	protected StringWriter wSaves;

	protected PrintWriter codeSaves;

	protected StringWriter wSave;

	protected PrintWriter codeSave;

	protected StringWriter wApiGet;

	protected PrintWriter codeApiGet;

	protected StringWriter wApiGenerateGet;

	protected PrintWriter codeApiGenerateGet;

	protected StringWriter wApiGeneratePost;

	protected PrintWriter codeApiGeneratePost;

	protected StringWriter wApiGeneratePut;

	protected PrintWriter codeApiGeneratePut;

	protected StringWriter wApiGeneratePatch;

	protected PrintWriter codeApiGeneratePatch;

	protected StringWriter wApiEntities;

	protected PrintWriter codeApiEntities;

	protected StringWriter wPageEntities;

	protected PrintWriter codePageEntities;

	protected StringWriter wPageGet;

	protected PrintWriter codePageGet;

	protected String entityVar;

	protected String entityVarCapitalized;

	protected String entityCanonicalName;

	protected String entityCanonicalNameGeneric;

	protected String entitySimpleNameComplete;

	protected String entitySimpleNameCompleteGeneric;

	protected String entitySimpleName;

	protected String entityComment;

	protected String entityVarParam;

	protected Boolean entityWrap;

	protected Boolean entityInitialized;

	protected Boolean entityInitDeep;

	protected PrintWriter writerGenClass;

	public void  genCodeInit() throws Exception, Exception {

		wInitDeep = new StringWriter();
		codeInitDeep = new PrintWriter(wInitDeep);

		wSiteRequest = new StringWriter();
		codeSiteRequest = new PrintWriter(wSiteRequest);

		wIndex = new StringWriter();
		codeIndex = new PrintWriter(wIndex);

		wObtain = new StringWriter();
		codeObtain = new PrintWriter(wObtain);

		wAttribute = new StringWriter();
		codeAttribute = new PrintWriter(wAttribute);

		wPut = new StringWriter();
		codePut = new PrintWriter(wPut);

		wPopulate = new StringWriter();
		codePopulate = new PrintWriter(wPopulate);

		wSaves = new StringWriter();
		codeSaves = new PrintWriter(wSaves);

		wExists = new StringWriter();
		codeExists = new PrintWriter(wExists);

		wSave = new StringWriter();
		codeSave = new PrintWriter(wSave);

		wApiEntities = new StringWriter();
		codeApiEntities = new PrintWriter(wApiEntities);

		wPageEntities = new StringWriter();
		codePageEntities = new PrintWriter(wPageEntities);

		wApiGet = new StringWriter();
		codeApiGet = new PrintWriter(wApiGet);

		wApiGenerateGet = new StringWriter();
		codeApiGenerateGet = new PrintWriter(wApiGenerateGet);

		wApiGeneratePost = new StringWriter();
		codeApiGeneratePost = new PrintWriter(wApiGeneratePost);

		wApiGeneratePut = new StringWriter();
		codeApiGeneratePut = new PrintWriter(wApiGeneratePut);

		wApiGeneratePatch = new StringWriter();
		codeApiGeneratePatch = new PrintWriter(wApiGeneratePatch);

		wPageGet = new StringWriter();
		codePageGet = new PrintWriter(wPageGet);
	}

	public void  genCodeInitDeep(String languageName) throws Exception, Exception {
		if(BooleanUtils.isTrue(classInitDeep)) {
			o = codeInitDeep;
			l(); 
			tl(1, "//////////////");
			tl(1, "// initDeep //");
			tl(1, "//////////////");
			l(); 
			tl(1, "protected boolean alreadyInitialized", classSimpleName, " = false;");
			l();
			tl(1, "public ", classSimpleName, " initDeep", classSimpleName, "(SiteRequest siteRequest) throws Exception {");
//						if(contientSiteRequest && !StringUtils.equals(classSimpleName, "SiteRequest"))
//							tl(2, "((", classSimpleName, ")this).setSiteRequest_(siteRequest);");
			tl(2, "setSiteRequest_(siteRequest);");
			tl(2, "initDeep", classSimpleName, "();");
			tl(2, "return (", classSimpleName, ")this;");
			tl(1, "}");
			l();
			tl(1, "public ", classSimpleName, " initDeep", classSimpleName, "() throws Exception {");
			tl(2, "if(!alreadyInitialized", classSimpleName, ") {");
			tl(3, "alreadyInitialized", classSimpleName, " = true;");
			if(BooleanUtils.isTrue(classExtendsBase)) 
				tl(3, "super.initDeep", classSuperSimpleNameGeneric, "(siteRequest_);");
		}
	}

	public void  genCodeSiteRequest(String languageName) throws Exception, Exception {
		if(BooleanUtils.isTrue(classInitDeep)) {
			o = codeSiteRequest;
			l(); 
			tl(1, "/////////////////");
			tl(1, "// siteRequest //");
			tl(1, "/////////////////");
			l(); 
			tl(1, "public void siteRequest", classSimpleName, "(SiteRequest siteRequest) throws Exception {");
			if(BooleanUtils.isTrue(classExtendsBase)) 
				tl(3, "super.siteRequest", classSuperSimpleNameGeneric, "(siteRequest);");
		}
	}

	public void  genCodeIndex(String languageName) throws Exception, Exception {
		o = codeIndex;
		if(classIndexed) {
			l(); 
			tl(1, "/////////////");
			tl(1, "// index //");
			tl(1, "/////////////");
			tl(0);
			tl(1, "public void index", classSimpleName, "() throws Exception {");
			tl(2, "SiteRequest siteRequest = new SiteRequest();");
			tl(2, "siteRequest.initDeepSiteRequest();");
			tl(2, "SiteContext siteContext = new SiteContext();");
			tl(2, "siteContext.initDeepSiteContext();");
			tl(2, "siteContext.setSiteRequest_(siteRequest);");
			tl(2, "siteRequest.setSiteContext_(SiteContext);");
			tl(2, "siteRequest", classSimpleName, "(siteRequest);");
			tl(2, "initDeep", classSimpleName, "(siteRequest);");
			tl(2, "index", classSimpleName, "(siteRequest);");
			tl(1, "}");
			tl(0);
			if(classExtendsBase || classIsBase) {
				tl(0);
				t(1);
				if(!classIsBase)
					s("@Override ");
				l("public void indexForClass(SiteRequest siteRequest) throws Exception {");
				tl(2, "index", classSimpleName, "(siteRequest_);");
				tl(1, "}");
				tl(0);
				t(1);
				if(!classIsBase)
					s("@Override ");
				l("public void indexForClass(SolrInputDocument document) throws Exception {");
				tl(2, "index", classSimpleName, "(document);");
				tl(1, "}");
			}
			tl(1, "public void index", classSimpleName, "(SiteRequest siteRequest) throws Exception {");
			tl(2, "SolrInputDocument document = new SolrInputDocument();");
			tl(2, "index", classSimpleName, "(document);");
//			if(classeSauvegarde)
//				tl(2, "document.addField(\"sauvegardes", classSimpleName, "_stored_strings\", sauvegardes", classSimpleName, ");");
			tl(2, "SolrClient clientSolr = siteRequest_.getSiteContext_().getClientSolr();");
			tl(2, "clientSolr.add(document);");
			tl(2, "clientSolr.commit();");
			l("\t}");

			tl(0);
			tl(1, "public void index", classSimpleName, "(SolrInputDocument document) throws Exception {");
		}
	}

	public void  genCodeObtain(String languageName) throws Exception, Exception {
		o = codeObtain;
		if(classInitDeep && (classExtendsBase || classIsBase)) {
			l(); 
			tl(1, "/////////////");
			tl(1, "// obtain //");
			tl(1, "/////////////");
			tl(0);
			t(1);
			if(!classIsBase)
				s("@Override ");
			l("public Object obtainForClass(String var) throws Exception {");
			tl(2, "String[] vars = StringUtils.split(var, \".\");");
			tl(2, "Object o = null;");
			tl(2, "for(String v : vars) {");
			tl(3, "if(o == null)");
			tl(4, "o = obtain", classSimpleName, "(v);");
			tl(3, "else if(o instanceof Cluster) {");
			tl(4, "Cluster cluster = (Cluster)o;");
			tl(4, "o = cluster.obtainForClass(v);");
			tl(3, "}");
			tl(2, "}");
			tl(2, "return o;");
			tl(1, "}");
			tl(1, "public Object obtain", classSimpleName, "(String var) throws Exception {");
			tl(2, classSimpleName, " o", classSimpleName, " = (", classSimpleName, ")this;");
			tl(2, "switch(var) {");
		}
	}

	public void  genCodeAttribute(String languageName) throws Exception, Exception {
		o = codeAttribute;
		if(classInitDeep && (classExtendsBase || classIsBase)) {
			l(); 
			tl(1, "///////////////");
			tl(1, "// attribute //");
			tl(1, "///////////////");
			tl(0);
			t(1);
			if(!classIsBase)
				s("@Override ");
			l("public boolean attributeForClass(String var, Object val) throws Exception {");
			tl(2, "String[] vars = StringUtils.split(var, \".\");");
			tl(2, "Object o = null;");
			tl(2, "for(String v : vars) {");
			tl(3, "if(o == null)");
			tl(4, "o = attribute", classSimpleName + "(v, val);");
			tl(3, "else if(o instanceof Cluster) {");
			tl(4, "Cluster cluster = (Cluster)o;");
			tl(4, "o = cluster.attributeForClass(v, val);");
			tl(3, "}");
			tl(2, "}");
			tl(2, "return o != null;");
			tl(1, "}");
			tl(1, "public Object attribute", classSimpleName, "(String var, Object val) throws Exception {");
			tl(2, classSimpleName, " o", classSimpleName, " = (", classSimpleName, ")this;");
			tl(2, "switch(var) {");

		}
	}

	public void  genCodePut(String languageName) throws Exception, Exception {
		o = codePut;
		if(classSaved) {
//		if(classInitDeep && (classExtendsBase || classIsBase)) {
			l(); 
			tl(1, "/////////");
			tl(1, "// put //");
			tl(1, "/////////");
			l();
			t(1);
			if(!classIsBase)
				s("@Override ");
			l("public void putForClass(JsonObject requeteJson) throws Exception {");
			tl(2, "Set<String> vars = requeteJson.fieldNames();");
			tl(2, "for(String var : vars) {");
			tl(3, "put", classSimpleName + "(requeteJson, var);");
			tl(2, "}");
			tl(1, "}");
			l();
			t(1);
			if(!classIsBase)
				s("@Override ");
			l("public Boolean put", classSimpleName, "(JsonObject requeteJson, String var) throws Exception {");
			tl(2, "switch(var) {");
		}
	}

	public void  genCodePopulate(String languageName) throws Exception, Exception {
		o = codePopulate;
		if(classSaved) {
			l(); 
			tl(1, "/////////////");
			tl(1, "// populate //");
			tl(1, "/////////////");
			tl(0);
			t(1);
			if(!classSimpleName.equals("Cluster"))
				s("@Override ");
			l("public void populateForClass(", SolrDocument.class.getCanonicalName(), " solrDocument) throws Exception {");
			if(classSaved) {
				tl(2, "saves", classSimpleName, " = (java.util.ArrayList<String>)solrDocument.get(\"saves", classSimpleName, "_stored_strings\");");
			tl(2, "populate", classSimpleName, "(solrDocument);");
			}
			tl(1, "}");
			tl(1, "public void populate", classSimpleName, "(", SolrDocument.class.getCanonicalName(), " solrDocument) throws Exception {");
			tl(2, classSimpleName, " o", classSimpleName, " = (", classSimpleName, ")this;");
		}
	}

	public void  genCodeExists(String languageName) throws Exception, Exception {
		o = codeExists;
		if(classSaved) {
//			l(); 
//			tl(1, "////////////");
//			tl(1, "// exists //");
//			tl(1, "////////////");
//			tl(0);
//			t(1);
//			if(!classSimpleName.equals("Cluster"))
//				s("@Override ");
//			l("public Boolean existsForClass() throws Exception {");
//			tl(2, "String pkStr = requeteSite_.getServerRequest().getParam(\"pk\");");
//			tl(2, "Long pk = ", StringUtils.class.getCanonicalName(), ".isNumeric(pkStr) ? Long.parseLong(pkStr) : null;");
//			tl(2, "Boolean exists = existsForClass(pk);");
//			tl(2, "return exists;");
//			tl(1, "}");
//			t(1);
//			if(!classSimpleName.equals("Cluster"))
//				s("@Override ");
//			l("public Boolean existsForClass(Long pk) throws Exception {");
//			tl(2, QueryRunner.class.getCanonicalName(), " coureur = new ", QueryRunner.class.getCanonicalName(), "(requeteSite_.SiteContexte.sourceDonnees);");
//			tl(2, ArrayListHandler.class.getCanonicalName(), " gestionnaireListe = new ", ArrayListHandler.class.getCanonicalName(), "();");
//			tl(2, "userId = requeteSite_.userId;");
//			tl(2, "this.pk = pk;");
//			tl(2, "String canonicalName = getClass().getCanonicalName();");
//			tl(2, "Boolean exists = false;");
//			tl(2);
//			tl(2, "if(pk == null) {");
//			tl(3, "String sql = \"select pk from c where c.id_utilisateur=? and c.nom_canonique=?\";");
//			tl(3, "SQLClient clientSql = requeteSite_.getSiteContexte_().getClientSql();");
//			tl(3, "clientSql.getConnection(ar -> {");
//			tl(4, "if(ar.failed()) {");
//			tl(5, "LOGGER.error(\"Impossible d'ouvrir une connexion à la base de données. \", ar.cause());");
//			tl(5, "future.fail(ar.cause());");
//			tl(4, "} else {");
//			tl(5, "SQLConnection connexionSql = ar.result();");
//			tl(5, "connectionSql.queryWithParams(SiteContexte.SQL_exists, new JsonArray().add(pk)), chercher -> {");
//			tl(6, "connexionSql.close();");
//			tl(6, "if(chercher.succeeded()) {");
//			tl(7, "JsonArray ligneDonnees = chercher.result().getResults().stream().findFirst().orElseGet(() -> null);");
//			tl(7, "if(ligneDonnees != null) {");
//			tl(8, "Long pkDonnees = ligneDonnees.getLong(0);");
//			tl(8, "if(ligneDonnees != null && ligne) {");
//			tl(9, "");
//			tl(8, "}");
//			tl(7, "}");
//			tl(6, "}");
//			tl(5, "});");
//			tl(4, "}");
//			tl(3, "});");
////			tl(3, List.class.getCanonicalName(), "<Object[]> resultats = coureur.query(sql, gestionnaireListe /*select count(*) from objet where objet.id_utilisateur=*/, requeteSite_.userId /* and objet.nom_canonique=*/, canonicalName);");
//			tl(7, "exists = resultats.size() > 0;");
//			tl(7, "if(exists) {");
//			tl(8, "pk = (Long)resultats.get(0)[0];");
//			tl(8, "setPk(pk);");
//			tl(7, "}");
//			tl(2, "}");
//			tl(2, "else {");
//			tl(3, "String sql = \"select count(*) from objet where objet.pk=? and objet.id_utilisateur=? and objet.nom_canonique=?\";");
//			tl(3, List.class.getCanonicalName(), "<Object[]> resultats = coureur.query(sql, gestionnaireListe /*select count(*) from objet where objet.pk=*/, pk /* and objet.id_utilisateur=*/, requeteSite_.userId /* and objet.nom_canonique=*/, canonicalName);");
//			tl(3, "exists = ((Long)resultats.get(0)[0]) > 0L;");
//
//			tl(2, "}");
//			tl(2, "return exists;");
//			tl(1, "}");
		}
	}

	public void  genCodeSaves(String languageName) throws Exception, Exception {
		o = codeSaves;
		if(classSaved) {
//			l(); 
//			tl(1, "/////////////////");
//			tl(1, "// saves //");
//			tl(1, "/////////////////");
//			tl(0);
//			tl(1, "protected java.util.ArrayList<String> saves", classSimpleName, " = new java.util.ArrayList<String>();");
//			t(1);
//			if(!classSimpleName.equals("Cluster"))
//				s("@Override ");
//			l("public void savesPourClasse(RequeteSite requeteSite) throws Exception {");
//			tl(2, QueryRunner.class.getCanonicalName(), " coureur = new ", QueryRunner.class.getCanonicalName(), "(requeteSite.SiteContexte.sourceDonnees);");
//			tl(2, ArrayListHandler.class.getCanonicalName(), " gestionnaireListe = new ", ArrayListHandler.class.getCanonicalName(), "();");
//
//			tl(2);
//			tl(2, "if(pk != null) {");
//			tl(3, "String sql = \"select cree, modifie from objet where objet.pk=?\";");
//			tl(3, List.class.getCanonicalName(), "<Object[]> resultats = coureur.query(sql, gestionnaireListe /*select cree, modifie from objet where objet.pk=*/, pk);");
//			tl(3, "if(resultats.size() > 0) {");
//			tl(4, "cree((Date)resultats.get(0)[0]);");
//			tl(4, "modifie((Date)resultats.get(0)[1]);");
//			tl(3, "}");
//
//			t(3, "sql = \"select chemin, valeur from p where p.pk_objet=? ");
//			s("union select champ2, pk2::text from a where a.pk1=? ");
//			s("union select champ1, pk1::text from a where a.pk2=? ");
//			l("\";");
//			tl(3, "resultats = coureur.query(sql, gestionnaireListe /*select chemin, valeur from p where p.pk_objet=*/, pk, pk, pk);");
//			tl(3, "for(Object[] objets : resultats) {");
//			tl(4, "String chemin = (String)objets[0];");
//			tl(4, "String valeur = requeteSite.decrypterStr((String)objets[1]);");
//			tl(4, "definirPourClasse(chemin, valeur);");
//			tl(4, "saves", classSimpleName, ".add(chemin);");
//			tl(3, "}");
//			tl(2, "}");
//			tl(1, "}");
		}
	}

	public void  genCodeSave(String languageName) throws Exception, Exception {
		o = codeSave;
		if(classSaved) {
//			l(); 
//			tl(1, "/////////////////");
//			tl(1, "// save //");
//			tl(1, "/////////////////");
//			tl(0);
//			t(1);
//			if(!classSimpleName.equals("Cluster"))
//				s("@Override ");
//			l("public void savePourClasse(RequeteSite requeteSite) throws Exception {");
//			tl(2, QueryRunner.class.getCanonicalName(), " coureur = new ", QueryRunner.class.getCanonicalName(), "(requeteSite.SiteContexte.sourceDonnees);");
//			tl(2, ArrayListHandler.class.getCanonicalName(), " gestionnaireListe = new ", ArrayListHandler.class.getCanonicalName(), "();");
//			tl(2, "String pkStr = requeteSite_.getRequeteServeur().getParam(\"pk\");");
//			tl(2, "pk = ", StringUtils.class.getCanonicalName(), ".isNumeric(pkStr) ? Long.parseLong(pkStr) : null;");
//			tl(2, "utilisateurId = requeteSite.utilisateurId;");
//			tl(2, "String nomCanonique = getClass().getCanonicalName();");
//			tl(2, "modifie = ", LocalDateTime.class.getCanonicalName(), ".now();");
//			tl(2, Timestamp.class.getCanonicalName(), " horodatage = java.sql.Timestamp.valueOf(modifie);");
//
//			tl(2);
//			tl(2, "if(pk == null) {");
//			tl(3, "String sql = \"insert into objet(nom_canonique, id_utilisateur, cree, modifie) values(?, ?, ?, ?) returning pk\";");
//			tl(3, List.class.getCanonicalName(), "<Object[]> resultats = coureur.insert(sql, gestionnaireListe /*insert into objet(nom_canonique, id_utilisateur, cree, modifie) values(*/, nomCanonique, requeteSite.utilisateurId, horodatage, horodatage /*) returning pk, cree*/);");
//			tl(3, "pk = (Long)resultats.get(0)[0];");
//			tl(3, "cree = modifie;");
//			tl(2, "}");
//			tl(2, "else {");
//			tl(3, "String sql = \"update objet set modifie=? where objet.pk=? and objet.id_utilisateur=? and objet.nom_canonique=? returning cree\";");
//			tl(3, List.class.getCanonicalName(), "<Object[]> resultats = coureur.query(sql, gestionnaireListe /*update objet set modifie=*/, horodatage /* where objet.pk=*/, pk /* and objet.id_utilisateur=*/, requeteSite.utilisateurId /* and objet.nom_canonique=*/, nomCanonique /* returning cree*/);");
//			tl(3, "if(resultats.size() == 0)");
//			t(4, "throw new Exception(\"");
//			s("L'objet avec le pk \" + pk + \" et nom canonique \" + pk + \" pour utilisateur \" + requeteSite.utilisateurId + \" \" + requeteSite.utilisateurNom + \" n'existe pas dejà. ");
//			l("\");");
//			tl(3, "horodatage = (java.sql.Timestamp)resultats.get(0)[0];");
//			tl(3, "cree = ", LocalDateTime.class.getCanonicalName(), ".from(horodatage.toLocalDateTime());");
//			tl(2, "}");
////						tl(0);
////						tl(2, "{");
////						tl(3, "String sqlSelectP = \"select chemin, valeur from p where p.pk_objet=?\";");
////						tl(3, List.class.getCanonicalName(), "<Object[]> resultats = coureur.query(sqlSelectP, gestionnaireListe /*select chemin, valeur from p where p.pk_objet=*/, pk);");
////						tl(3, "for(Object[] objets : resultats) {");
////						tl(4, "String chemin = (String)objets[0];");
////						if(coursCrypte)
////							tl(4, "String valeur = requeteSite.decrypterStr((String)objets[1]);");
////						else
////							tl(4, "String valeur = (String)objets[1];");
////						tl(4, "definir(chemin, valeur);");
////						tl(4, "sauvegardes", classSimpleName, ".add(chemin);");
////						tl(3, "}");
////						tl(2, "}");
//			tl(0);
////						tl(2, "{");
//			tl(2, "String sqlInsertP = \"insert into p(chemin, valeur, pk_objet) values(?, ?, ?) on conflict(chemin, pk_objet) do update set valeur=? where p.chemin=? and p.pk_objet=?\";");
//			tl(2, "String sqlInsertA = \"insert into a(champ1, pk1, champ2, pk2) values(?, ?, ?, ?) on conflict  do nothing\";");
//			tl(2, "String sqlDeleteP = \"delete from p where chemin=? and pk_objet=?\";");
//			tl(2, "String sqlDeleteA = \"delete from a where champ1=? and pk1=? and champ2=? and pk2=?\";");
//			tl(2, "save", classSimpleName, "(requeteSite, sqlInsertP, sqlInsertA, sqlDeleteP, sqlDeleteA, gestionnaireListe, coureur);");
////						tl(2, "}");
//			tl(1, "}");
//			tl(1, "public void save", classSimpleName, "(RequeteSite requeteSite, String sqlInsertP, String sqlInsertA, String sqlDeleteP, String sqlDeleteA, ", ArrayListHandler.class.getCanonicalName(), " gestionnaireListe, ", QueryRunner.class.getCanonicalName(), " coureur) throws Exception {");
		}
	}

	public void  genCodeClassBegin(String languageName) throws Exception, Exception {
		o = writerGenClass;

		l("package ", classPackageName, ";");
		l();
		if(classImportsGen.size() > 0) { 
			for(String classImport : classImportsGen) {
				l("import ", classImport, ";");
			}
			l();
		}
		l("/**\t");
		writeCommentPart(classComment, 0); 
		tl(0, " * <br/><a href=\"", solrUrlComputate, "/select?q=*:*&fq=partIsClass_indexed_boolean:true&fq=classCanonicalName_", languageName, "_indexed_string:", ClientUtils.escapeQueryChars(classCanonicalName), "&fq=classExtendsGen_indexed_boolean:true\">Find the class ", entityVar, " in Solr</a>");
		tl(0, " * <br/>");
		l(" **/");  
		s("public abstract class ", classSimpleNameGen);
		if(classParameterTypeNames != null && classParameterTypeNames.size() > 0) {
			s("<");
			for(int j = 0; j < classParameterTypeNames.size(); j++) {
				String classParameterTypeName = classParameterTypeNames.get(j);
				if(j > 0)
					s(", ");
				s(classParameterTypeName);
			}
			s(">");
		}
		else {
			s("<DEV>");
		}
		if(classSuperSimpleNameGeneric != null && !"java.lang.Object".equals(classSuperSimpleNameGeneric) && !"DEV".equals(classSuperSimpleNameGeneric)) {
			s(" extends ");
//						s(classSimpleNameSuper);
			
			if(classSuperSimpleNameGeneric != null) {
				s(classSuperSimpleNameGeneric);
			}
//						else if(classSuperParameterTypeNames != null && classSuperParameterTypeNames.size() > 0) {
////							s("<");
//							for(int j = 0; j < classSuperParameterTypeNames.size(); j++) {
//								String classSuperParameterTypeName = classSuperParameterTypeNames.get(j);
//								if(i > 0)
//									s();
//								s(classSuperParameterTypeName);
//							}
////							s(">");
//						}	
		}
		s(" {\n");
		if(classSaved) {
			tl(1, "private static final Logger LOGGER = LoggerFactory.getLogger(", classSimpleName, ".class);");
		}
		List<String> classValsVar = (List<String>)doc.get("classValsVar_stored_strings");
		List<String> classValsLanguage = (List<String>)doc.get("classValsLanguage_stored_strings");
		List<String> classValsValue = (List<String>)doc.get("classValsValue_stored_strings");
		if(classValsVar != null && classValsLanguage != null && classValsValue != null) {
			for(int j = 0; j < classValsVar.size(); j++) {
				String classValVar = classValsVar.get(j);
				String classValLanguage = classValsLanguage.get(j);
				String classValValue = classValsValue.get(j);

				if(StringUtils.equals(languageName, classValLanguage)) {
					tl(1, "public static final String ", classValVar, " = \"", StringEscapeUtils.escapeJava(classValValue), "\";");
				}
			}
		}
	}

	public void  genCodeEntity(String languageName) throws Exception, Exception {
		String entityVar = (String)doc.get("entityVar_" + languageName + "_stored_string");
		String entityVarCapitalized = (String)doc.get("entityVarCapitalized_" + languageName + "_stored_string");
		String entityCanonicalName = (String)doc.get("entityCanonicalName_" + languageName + "_stored_string");
		String entityCanonicalNameGeneric = (String)doc.get("entityCanonicalNameGeneric_" + languageName + "_stored_string");
		String entitySimpleNameComplete = (String)doc.get("entitySimpleNameComplete_" + languageName + "_stored_string");
		String entitySimpleNameCompleteGeneric = (String)doc.get("entitySimpleNameCompleteGeneric_" + languageName + "_stored_string");
		String entitySimpleName = (String)doc.get("entitySimpleName_" + languageName + "_stored_string");
		String entityComment = (String)doc.get("entityComment_" + languageName + "_stored_string");
		String entityVarParam = (String)doc.get("entityVarParam_" + languageName + "_stored_string");
		Boolean entityWrap = (Boolean)doc.get("entityWrap_stored_boolean");
		Boolean entityInitialized = (Boolean)doc.get("entityInitialized_stored_boolean");
		Boolean entityInitDeep = (Boolean)doc.get("entityInitDeep_stored_boolean");
		List<String> methodExceptionsSimpleNameComplete = (List<String>)doc.get("methodExceptionsSimpleNameComplete_stored_strings");

//		String entityVarCleUniqueActuel = (String)doc.get("entityVarCleUnique_stored_string");
//		if(StringUtils.isNotEmpty(entityVarCleUniqueActuel))
//			entityVarCleUnique = entityVarCleUniqueActuel;
		String entityVarSuggested = (String)doc.get("entityVarSuggested_stored_string");
		String entityVarIncremented = (String)doc.get("entityVarIncremented_stored_string");
		String entityVarEncrypted = (String)doc.get("entityVarEncrypted_stored_string");
		String entityVarIndexed = (String)doc.get("entityVarIndexed_stored_string");
		String entityVarStored = (String)doc.get("entityVarStored_stored_string");
		String entitySolrCanonicalName = (String)doc.get("entitySolrCanonicalName_stored_string");
		String entitySolrSimpleName = (String)doc.get("entitySolrSimpleName_stored_string");
		String entitySimpleNameVertxJson = (String)doc.get("entitySimpleNameVertxJson_stored_string");
		String entityCanonicalNameVertxJson = (String)doc.get("entityCanonicalNameVertxJson_stored_string");
		String entityListSimpleNameVertxJson = (String)doc.get("entityListSimpleNameVertxJson_stored_string");
		String entityListCanonicalNameVertxJson = (String)doc.get("entityListCanonicalNameVertxJson_stored_string");

		Boolean entityExact = (Boolean)doc.get("entityExact_stored_boolean");
		Boolean entityPrimaryKey = (Boolean)doc.get("entityPrimaryKey_stored_boolean");
		Boolean entityEncrypted = (Boolean)doc.get("entityEncrypted_stored_boolean");
		Boolean entitySuggested = (Boolean)doc.get("entitySuggested_stored_boolean");
		Boolean entitySaved = (Boolean)doc.get("entitySaved_stored_boolean");
		Boolean entityIndexed = (Boolean)doc.get("entityIndexed_stored_boolean");
		Boolean entityStored = (Boolean)doc.get("entityStored_stored_boolean");
		Boolean entitytexte = (Boolean)doc.get("entitytexte_stored_boolean");
		Boolean entityIncremented = (Boolean)doc.get("entityIncremented_stored_boolean");
		Boolean entityIgnored = (Boolean)doc.get("entityIgnored_stored_boolean");
		Boolean entityDeclared = (Boolean)doc.get("entityDeclared_stored_boolean");
		Boolean entitySearch = (Boolean)doc.get("entitySearch_stored_boolean");
		Boolean entityAttribute = BooleanUtils.isTrue((Boolean)doc.get("entityAttribute_stored_boolean"));
		String entityAttributeNomCanonique = (String)doc.get("entityAttributeNomCanonique_" + languageName + "_stored_string");
		String entityAttributeNomSimple = (String)doc.get("entityAttributeNomSimple_" + languageName + "_stored_string");
		String entityAttributeVar = (String)doc.get("entityAttributeVar_" + languageName + "_stored_string");
		Boolean entityAdd = (Boolean)doc.get("entityAdd_stored_boolean");
		Boolean entityDelete = (Boolean)doc.get("entityDelete_stored_boolean");
		Boolean entityModify = (Boolean)doc.get("entityModify_stored_boolean");
		Boolean entityRefresh = (Boolean)doc.get("entityRefresh_stored_boolean");
		Boolean entityMultiLine = (Boolean)doc.get("entityMultiLine_stored_boolean");
		Boolean entityKeys = (Boolean)doc.get("entityKeys_stored_boolean");
		Boolean entityIndexedOrStored = (Boolean)doc.get("entityIndexedOrStored_stored_boolean");
		Boolean entityDefined = (Boolean)doc.get("entityDefined_stored_boolean");

		String entityDisplayName = (String)doc.get("entityDisplayName_" + languageName + "_stored_string");
		String entityHtmlTooltip = (String)doc.get("entityHtmlTooltip_" + languageName + "_stored_string");

		List<String> entityMethodsBeforeVisibility = (List<String>)doc.get("entityMethodsBeforeVisibility_stored_strings");
		List<String> entityMethodsBeforeVar = (List<String>)doc.get("entityMethodsBeforeVar_stored_strings");
		List<String> entityMethodsBeforeParamVar = (List<String>)doc.get("entityMethodsBeforeParamVar_stored_strings");
		List<String> entityMethodsBeforeSimpleName = (List<String>)doc.get("entityMethodsBeforeSimpleName_stored_strings");
		List<Boolean> entityMethodsBeforeParamName = (List<Boolean>)doc.get("entityMethodsBeforeParamName_stored_booleans");
		List<Boolean> entityMethodsBeforeWrite = (List<Boolean>)doc.get("entityMethodsBeforeWrite_stored_booleans");

		List<String> entityMethodsAfterVisibility = (List<String>)doc.get("entityMethodsAfterVisibility_stored_strings");
		List<String> entityMethodsAfterVar = (List<String>)doc.get("entityMethodsAfterVar_stored_strings");
		List<String> entityMethodsAfterParamVar = (List<String>)doc.get("entityMethodsAfterParamVar_stored_strings");
		List<String> entityMethodsAfterSimpleName = (List<String>)doc.get("entityMethodsAfterSimpleName_stored_strings");
		List<Boolean> entityMethodsAfterParamName = (List<Boolean>)doc.get("entityMethodsAfterParamName_stored_booleans");
		List<Boolean> entityMethodsAfterWrite = (List<Boolean>)doc.get("entityMethodsAfterWrite_stored_booleans");

		o = writerGenClass;

		l();
		String commentLine = "\t///" + String.join("", Collections.nCopies(entityVar.length(), "/")) + "///";
		l(commentLine);
		tl(1, "// ", entityVar, " //");
		l(commentLine);
		l();
		t(1, "/**");
		t(1);
			s("The entity \" ", entityVar, " \"");
		l();

		if(entityComment != null) {
			String[] lines = entityComment.toString().split("\n");
			for(int j = 0; j < lines.length; j++) {
				String line = lines[j];
				if(!StringUtils.isEmpty(line)) {
					Boolean first = j == 0;
					Integer tabs = StringUtils.countMatches(line, "\t");
					if(!first)
						t(1 + tabs, " *\t");
					l(line.substring(tabs));
				}
			}
		}

		if(entityWrap) {
			tl(1, " *\t", " is defined as null before being initialized. ");
		}
		else {
			tl(1, " *\t", "It is constructed before being initialized with the constructor by default ", entitySimpleNameComplete, "(). ");
		}
		tl(1, " */");

		t(1, "protected ", entitySimpleNameComplete, " ", entityVar);
		if(!entityWrap) {
			if("java.util.List".equals(entityCanonicalName)) {
				s(" = new java.util.ArrayList<");
				s(entityCanonicalNameGeneric);
				s(">()");
			}
			else {
				s(" = new ", entitySimpleNameComplete, "()");
			}
		}
		l(";");

		t(1, "public Wrap<", entitySimpleNameComplete, "> ", entityVar, "Wrap");
		l(" = new Wrap<", entitySimpleNameComplete, ">().p(this).c(", entitySimpleName, ".class).var(\"", entityVar, "\").o(", entityVar, ");");

		// Methode underscore //
		l();
		t(1, "/**");
		t(1);
		s("<br/>", "The entity \" ", entityVar, " \"");
		l();

		if(entityComment != null) {
			String[] lines = entityComment.toString().split("\n");
			for(int j = 0; j < lines.length; j++) {
				String line = lines[j];
				if(!StringUtils.isEmpty(line)) {
					Boolean first = j == 0;
					Integer tabs = StringUtils.countMatches(line, "\t");
					if(!first)
						t(1 + tabs, " *\t");
					l(line.substring(tabs));
				}
			}
		}

		if(entityWrap) {
			tl(1, " * ", " is defined as null before being initialized. ");
		}
		else {
			tl(1, " * ", "It is constructed before being initialized with the constructor by default ", entitySimpleNameComplete, "(). ");
		}

		// Lien vers Solr //
		tl(1, " * <br/><a href=\"", solrUrlComputate, "/select?q=*:*&fq=partIsEntity_indexed_boolean:true&fq=classCanonicalName_", languageName, "_indexed_string:", ClientUtils.escapeQueryChars(classCanonicalName), "&fq=classExtendsGen_indexed_boolean:true&fq=entityVar_", languageName, "_indexed_string:", ClientUtils.escapeQueryChars(entityVar), "\">Find the entity ", entityVar, " in Solr</a>");
		tl(1, " * <br/>");

		if(entityWrap) {
			tl(1, " * @param ", entityVarParam, " is for wrapping a value to assign to this entity at initialization. ");
		}
		else {
			tl(1, " * @param ", entityVar, " is the entity already constructed. ");
		}
//		if(methodExceptionsSimpleNameComplete != null && methodExceptionsSimpleNameComplete.size() > 0) {
//
//			for(int i = 0; i < methodExceptionsSimpleNameComplete.size(); i++) {
//				String methodeExceptionNomSimpleComplet = methodExceptionsSimpleNameComplete.get(i);
//				tl(1, " * @throws ", methodeExceptionNomSimpleComplet);
//			}
//		}
		tl(1, " **/");
		t(1, "protected abstract void");
		s(" _", entityVar);
		s("(");
		if(entityWrap) {
			s("Wrap<", entitySimpleNameComplete, "> ", entityVarParam);
		}
		else {
			s(entitySimpleNameComplete, " ", entityVarParam);
		}
		s(")");
		if(methodExceptionsSimpleNameComplete != null && methodExceptionsSimpleNameComplete.size() > 0) {

			s(" throws ");
			for(int i = 0; i < methodExceptionsSimpleNameComplete.size(); i++) {
				String methodeExceptionNomSimpleComplet = methodExceptionsSimpleNameComplete.get(i);
				if(i > 0) 
					s(", ");
				s(methodeExceptionNomSimpleComplet);
			}
		}
		l(";");

//						l();
//						tl(1, "public ", classSimpleName, " ", entityVar, "(", entitySimpleNameComplete, " ", entityVarParam, ") throws Exception {");
//						tl(2, "set", entityVarCapitalized, "(", entityVarParam, ");");
//						tl(2, "return (", classSimpleName, ")this;");
//						tl(1, "}");

		l();
		tl(1, "public ", entitySimpleNameComplete, " get", entityVarCapitalized, "() {");
		tl(2, "return ", entityVar, ";");
		tl(1, "}");

		l();
		tl(1, "public void set", entityVarCapitalized, "(", entitySimpleNameComplete, " ", entityVarParam, ") {");
		tl(2, "this.", entityVar, " = ", entityVarParam, ";");
		tl(2, "this.", entityVar, "Wrap.alreadyInitialized = true;");
		tl(1, "}");
//
//						l();
//						tl(1, "public ", entitySimpleNameComplete, " ", entityVar, "() throws Exception {");
//						tl(2, "return get", entityVarCapitalized, "();");
//						tl(1, "}");

		// Setter List //
		if(StringUtils.equals(entityCanonicalName, ArrayList.class.getCanonicalName()) && StringUtils.equals(entityCanonicalNameGeneric, Long.class.getCanonicalName())) {
			tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(String o) throws Exception {");
			tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {");
			tl(3, "Long l = Long.parseLong(o);");
			tl(3, "add", entityVarCapitalized, "(l);");
			tl(2, "}");
			tl(2, "this.", entityVar, "Wrap.alreadyInitialized = true;");
			tl(2, "return (", classSimpleName, ")this;");
			tl(1, "}");
		}

		// Setter Boolean //
		if(StringUtils.equals(entityCanonicalName, Boolean.class.getCanonicalName())) {
			tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(String o) throws Exception {");
			tl(2, "if(org.apache.commons.lang3.BooleanUtils.isTrue(org.apache.commons.lang3.BooleanUtils.toBoolean(o)))");
			tl(3, "this.", entityVar, " = Boolean.parseBoolean(o);");
			tl(2, "this.", entityVar, "Wrap.alreadyInitialized = true;");
			tl(2, "return (", classSimpleName, ")this;");
			tl(1, "}");
		}

		// Setter Integer //
		if(StringUtils.equals(entityCanonicalName, Integer.class.getCanonicalName())) {
			tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(String o) throws Exception {");
			tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))");
			tl(3, "this.", entityVar, " = Integer.parseInt(o);");
			tl(2, "this.", entityVar, "Wrap.alreadyInitialized = true;");
			tl(2, "return (", classSimpleName, ")this;");
			tl(1, "}");
		}

		// Setter BigDecimal //
		if(StringUtils.equals(entityCanonicalName, BigDecimal.class.getCanonicalName())) {
			tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(String o) throws Exception {");
			tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))");
			tl(3, "this.", entityVar, " = new BigDecimal(o);");
			tl(2, "this.", entityVar, "Wrap.alreadyInitialized = true;");
			tl(2, "return (", classSimpleName, ")this;");
			tl(1, "}");
			tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(Double o) throws Exception {");
			tl(3, "this.", entityVar, " = new BigDecimal(o);");
			tl(2, "this.", entityVar, "Wrap.alreadyInitialized = true;");
			tl(2, "return (", classSimpleName, ")this;");
			tl(1, "}");
			tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(Integer o) throws Exception {");
			tl(3, "this.", entityVar, " = new BigDecimal(o);");
			tl(2, "this.", entityVar, "Wrap.alreadyInitialized = true;");
			tl(2, "return (", classSimpleName, ")this;");
			tl(1, "}");
		}

		// Setter Float //
		if(StringUtils.equals(entityCanonicalName, Float.class.getCanonicalName())) {
			tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(String o) throws Exception {");
			tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))");
			tl(3, "this.", entityVar, " = Float.parseFloat(o);");
			tl(2, "this.", entityVar, "Wrap.alreadyInitialized = true;");
			tl(2, "return (", classSimpleName, ")this;");
			tl(1, "}");
		}

		// Setter Double //
		if(StringUtils.equals(entityCanonicalName, Double.class.getCanonicalName())) {
			tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(String o) throws Exception {");
			tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))");
			tl(3, "this.", entityVar, " = Double.parseDouble(o);");
			tl(2, "this.", entityVar, "Wrap.alreadyInitialized = true;");
			tl(2, "return (", classSimpleName, ")this;");
			tl(1, "}");
		}

		// Setter Long //
		if(StringUtils.equals(entityCanonicalName, Long.class.getCanonicalName())) {
			tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(String o) throws Exception {");
			tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))");
			tl(3, "this.", entityVar, " = Long.parseLong(o);");
			tl(2, "this.", entityVar, "Wrap.alreadyInitialized = true;");
			tl(2, "return (", classSimpleName, ")this;");
			tl(1, "}");
		}

		// Setter Long //
		if(StringUtils.equals(entitySimpleName, "Chain")) {
			tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(String o) throws Exception {");
			tl(2, entityVar, ".tout(o);");
			tl(2, "this.", entityVar, "Wrap.alreadyInitialized = true;");
			tl(2, "return (", classSimpleName, ")this;");
			tl(1, "}");
		}

		// Setter Timestamp //
		if(StringUtils.equals(entityCanonicalName, Timestamp.class.getCanonicalName())) {
			tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
			tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(String o) throws Exception {");
			tl(2, "this.", entityVar, " = Timestamp.valueOf((LocalDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME)));");
			tl(2, "this.", entityVar, "Wrap.alreadyInitialized = true;");
			tl(2, "return (", classSimpleName, ")this;");
			tl(1, "}");
		}

		// Setter Date //
		if(StringUtils.equals(entityCanonicalName, Date.class.getCanonicalName())) {
			tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
			tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(String o) throws Exception {");
			tl(2, "this.", entityVar, " = Date.from(LocalDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME).atZone(ZoneId.systemDefault()).toInstant());");
			tl(2, "this.", entityVar, "Wrap.alreadyInitialized = true;");
			tl(2, "return (", classSimpleName, ")this;");
			tl(1, "}");
		}

		// Setter LocalDate //
		if(StringUtils.equals(entityCanonicalName, LocalDate.class.getCanonicalName())) {
			tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(Instant o) throws Exception {");
			tl(2, "this.", entityVar, " = LocalDate.from(o);");
			tl(2, "this.", entityVar, "Wrap.alreadyInitialized = true;");
			tl(2, "return (", classSimpleName, ")this;");
			tl(1, "}");
			tl(1, "/** Example: 2011-12-03+01:00 **/");
			tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(String o) throws Exception {");
			tl(2, "this.", entityVar, " = LocalDate.parse(o, DateTimeFormatter.ISO_OFFSET_DATE);");
			tl(2, "this.", entityVar, "Wrap.alreadyInitialized = true;");
			tl(2, "return (", classSimpleName, ")this;");
			tl(1, "}");
			tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(Date o) throws Exception {");
			tl(2, "this.", entityVar, " = o.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();");
			tl(2, "this.", entityVar, "Wrap.alreadyInitialized = true;");
			tl(2, "return (", classSimpleName, ")this;");
			tl(1, "}");
		}

		// Setter LocalDateTime //
		if(StringUtils.equals(entityCanonicalName, LocalDateTime.class.getCanonicalName())) {
			tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(Instant o) throws Exception {");
			tl(2, "this.", entityVar, " = LocalDateTime.from(o);");
			tl(2, "this.", entityVar, "Wrap.alreadyInitialized = true;");
			tl(2, "return (", classSimpleName, ")this;");
			tl(1, "}");
			tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
			tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(String o) throws Exception {");
			tl(2, "this.", entityVar, " = LocalDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME);");
			tl(2, "this.", entityVar, "Wrap.alreadyInitialized = true;");
			tl(2, "return (", classSimpleName, ")this;");
			tl(1, "}");
			tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(Date o) throws Exception {");
			tl(2, "this.", entityVar, " = LocalDateTime.ofInstant(o.toInstant(), ZoneId.systemDefault());");
			tl(2, "this.", entityVar, "Wrap.alreadyInitialized = true;");
			tl(2, "return (", classSimpleName, ")this;");
			tl(1, "}");
		}

		// Ajouter //
		if(StringUtils.equals(entityCanonicalName, List.class.getCanonicalName()) || StringUtils.equals(entityCanonicalName, ArrayList.class.getCanonicalName())) {
			tl(1, "public ", classSimpleName, " add", entityVarCapitalized, "(", entitySimpleNameCompleteGeneric, "...objets) {");
			tl(2, "for(", entitySimpleNameCompleteGeneric, " o : objets) {");
			tl(3, "add", entityVarCapitalized, "(o);");
			tl(2, "}");
			tl(2, "return (", classSimpleName, ")this;");
			tl(1, "}");
			tl(1, "public ", classSimpleName, " add", entityVarCapitalized, "(", entitySimpleNameCompleteGeneric, " o) {");
			tl(2, "if(o != null && !", entityVar, ".contains(o))");
			tl(3, "this.", entityVar, ".add(o);");
			tl(2, "return (", classSimpleName, ")this;");
			tl(1, "}");
	
			// Setter Boolean //
			if(StringUtils.equals(entityCanonicalNameGeneric, Boolean.class.getCanonicalName())) {
				tl(1, "public ", classSimpleName, " add", entityVarCapitalized, "(String o) {");
				tl(2, "if(org.apache.commons.lang3.BooleanUtils.isTrue(org.apache.commons.lang3.BooleanUtils.toBoolean(o)))");
				tl(3, entitySimpleNameCompleteGeneric, " p = Boolean.parseBoolean(o);");
				tl(2, "add", entityVarCapitalized, "(p);");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
			}
	
			// Setter Integer //
			if(StringUtils.equals(entityCanonicalNameGeneric, Integer.class.getCanonicalName())) {
				tl(1, "public ", classSimpleName, " add", entityVarCapitalized, "(String o) {");
				tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {");
				tl(3, entitySimpleNameCompleteGeneric, " p = Integer.parseInt(o);");
				tl(3, "add", entityVarCapitalized, "(p);");
				tl(3, "}");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
			}
	
			// Setter BigDecimal //
			if(StringUtils.equals(entityCanonicalNameGeneric, BigDecimal.class.getCanonicalName())) {
				tl(1, "public ", classSimpleName, " add", entityVarCapitalized, "(String o) {");
				tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {");
				tl(3, entitySimpleNameCompleteGeneric, " p = new BigDecimal(o);");
				tl(3, "add", entityVarCapitalized, "(p);");
				tl(2, "}");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
			}
	
			// Setter Float //
			if(StringUtils.equals(entityCanonicalNameGeneric, Float.class.getCanonicalName())) {
				tl(1, "public ", classSimpleName, " add", entityVarCapitalized, "(String o) {");
				tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {");
				tl(3, entitySimpleNameCompleteGeneric, " p = Float.parseFloat(o);");
				tl(3, "add", entityVarCapitalized, "(p);");
				tl(2, "}");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
			}
	
			// Setter Double //
			if(StringUtils.equals(entityCanonicalNameGeneric, Double.class.getCanonicalName())) {
				tl(1, "public ", classSimpleName, " add", entityVarCapitalized, "(String o) {");
				tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {");
				tl(3, entitySimpleNameCompleteGeneric, " p = Double.parseDouble(o);");
				tl(3, "add", entityVarCapitalized, "(p);");
				tl(2, "}");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
			}
	
			// Setter Long //
			if(StringUtils.equals(entityCanonicalNameGeneric, Long.class.getCanonicalName())) {
				tl(1, "public ", classSimpleName, " add", entityVarCapitalized, "(String o) {");
				tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {");
				tl(3, entitySimpleNameCompleteGeneric, " p = Long.parseLong(o);");
				tl(3, "add", entityVarCapitalized, "(p);");
				tl(2, "}");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
			}
	
			// Setter Timestamp //
			if(StringUtils.equals(entityCanonicalNameGeneric, Timestamp.class.getCanonicalName())) {
				tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
				tl(1, "public ", classSimpleName, " add", entityVarCapitalized, "(String o) {");
				tl(2, entitySimpleNameCompleteGeneric, " p = Timestamp.valueOf((LocalDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME)));");
				tl(2, "add", entityVarCapitalized, "(p);");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
			}
	
			// Setter Date //
			if(StringUtils.equals(entityCanonicalNameGeneric, Date.class.getCanonicalName())) {
				tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
				tl(1, "public ", classSimpleName, " add", entityVarCapitalized, "(String o) {");
				tl(2, entitySimpleNameCompleteGeneric, " p = Date.from(LocalDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME).atZone(ZoneId.systemDefault()).toInstant());");
				tl(2, "add", entityVarCapitalized, "(p);");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
			}
	
			// Setter LocalDate //
			if(StringUtils.equals(entityCanonicalNameGeneric, LocalDate.class.getCanonicalName())) {
				tl(1, "/** Example: 2011-12-03+01:00 **/");
				tl(1, "public ", classSimpleName, " add", entityVarCapitalized, "(String o) {");
				tl(2, entitySimpleNameCompleteGeneric, " p = LocalDate.parse(o, DateTimeFormatter.ISO_OFFSET_DATE);");
				tl(2, "add", entityVarCapitalized, "(p);");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
				tl(1, "public ", classSimpleName, " add", entityVarCapitalized, "(Date o) {");
				tl(2, entitySimpleNameCompleteGeneric, " p = o.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();");
				tl(2, "add", entityVarCapitalized, "(p);");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
			}
	
			// Setter LocalDateTime //
			if(StringUtils.equals(entityCanonicalNameGeneric, LocalDateTime.class.getCanonicalName())) {
				tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
				tl(1, "public ", classSimpleName, " add", entityVarCapitalized, "(String o) {");
				tl(2, entitySimpleNameCompleteGeneric, " p = LocalDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME);");
				tl(2, "add", entityVarCapitalized, "(p);");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
				tl(1, "public ", classSimpleName, " add", entityVarCapitalized, "(Date o) {");
				tl(2, entitySimpleNameCompleteGeneric, " p = LocalDateTime.ofInstant(o.toInstant(), ZoneId.systemDefault());");
				tl(2, "add", entityVarCapitalized, "(p);");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
			}
		}

		// Initialise //
		if(entityInitialized) {

			if(entityMethodsBeforeVar != null && entityMethodsBeforeVar.size() > 0) {
				for(int j = 0; j < entityMethodsBeforeVar.size(); j++) {
					String entityMethodBeforeVisibility = entityMethodsBeforeVisibility.get(j);
					String entityMethodBeforeVar = entityMethodsBeforeVar.get(j);
					String entityMethodBeforeParamVar = entityMethodsBeforeParamVar.get(j);
					String entityMethodBeforeSimpleName = entityMethodsBeforeSimpleName.get(j);
					Boolean entityMethodBeforeParamName = entityMethodsBeforeParamName.get(j);
					Boolean entityMethodBeforeWrite = entityMethodsBeforeWrite.get(j);

					if(BooleanUtils.isTrue(entityMethodBeforeWrite)) {
						t(1, entityMethodBeforeVisibility, " abstract void ", entityMethodBeforeVar, "(", entityMethodBeforeSimpleName, " ", entityMethodBeforeParamVar);
						if(entityMethodBeforeParamName)
							s(", String entityVar");
						l(");");
					}
				}
			}
	
			// Initialiser //
			tl(1, "protected ", classSimpleName, " ", entityVar, "Init() throws Exception {");

			if(entityCanonicalNameGeneric == null && entityMethodsBeforeVar != null && entityMethodsBeforeVar.size() > 0) {
				tl(2, "if(", entityVar, " != null) {");
				for(int j = 0; j < entityMethodsBeforeVar.size(); j++) {
					String entityMethodBeforeVar = entityMethodsBeforeVar.get(j);
					Boolean entityMethodBeforeParamName = entityMethodsBeforeParamName.get(j);

					t(3, entityMethodBeforeVar, "(", entityVar);
					if(entityMethodBeforeParamName)
						s(", \"", entityVar, "\"");
					l(");");
				}
				tl(2, "}");
			}

			tl(2, "if(!", entityVar, "Wrap.alreadyInitialized) {");
			if(entityWrap) {
				tl(3, "_", entityVar, "(", entityVar, "Wrap);");
				tl(3, "if(", entityVar, " == null)");
				tl(4, "set", entityVarCapitalized, "(", entityVar, "Wrap.o);");
			}
			else {
				tl(3, "_", entityVar, "(", entityVar, ");");
			}
			tl(2, "}");

			// initLoin

//						if(initLoin && canonicalName.enUS().startsWith(classe.nomEnsembleDomaine.enUS())) {
			if(entityInitDeep) {
				if(entityWrap) {
					tl(2, "if(", entityVar, " != null)");
					tl(3, entityVar, ".initLoinPourClasse(siteRequest_);");
				}
				else {
					tl(2, entityVar, ".initLoinPourClasse(siteRequest_);");
				}
			}

			if(entityCanonicalNameGeneric == null && entityMethodsAfterVar != null && entityMethodsAfterVar.size() > 0) {
				tl(2, "if(", entityVar, " != null) {");
				for(int j = 0; j < entityMethodsAfterVar.size(); j++) {
					String entityMethodAfterVisibility = entityMethodsAfterVisibility.get(j);
					String entityMethodAfterVar = entityMethodsAfterVar.get(j);
					Boolean entityMethodAfterParamName = entityMethodsAfterParamName.get(j);

					t(3, entityMethodAfterVar, "(", entityVar);
					if(entityMethodAfterParamName)
						s(", \"", entityVar, "\"");
					l(");");
				}
				tl(2, "}");
			}

			tl(2, entityVar, "Wrap.alreadyInitialized(true);");
			tl(2, "return (", classSimpleName, ")this;");
			tl(1, "}");

			if(entityMethodsAfterVar != null) {
				for(int j = 0; j < entityMethodsAfterVar.size(); j++) {
					String entityMethodAfterVisibility = entityMethodsAfterVisibility.get(j);
					String entityMethodAfterVar = entityMethodsAfterVar.get(j);
					String entityMethodAfterParamVar = entityMethodsAfterParamVar.get(j);
					String entityMethodAfterSimpleName = entityMethodsAfterSimpleName.get(j);
					Boolean entityMethodAfterParamName = entityMethodsAfterParamName.get(j);
					Boolean entityMethodAfterWrite = entityMethodsBeforeWrite.get(j);

					if(BooleanUtils.isTrue(entityMethodAfterWrite)) {
						t(1, entityMethodAfterVisibility, " abstract void ", entityMethodAfterVar, "(", entityMethodAfterSimpleName, " ", entityMethodAfterParamVar);
						if(entityMethodAfterParamName)
							s(", String entityVar");
						l(");");
					}
				}
			}
		}

		//////////
		// html //
		//////////
		if(entitySimpleName != null && entitySolrCanonicalName != null) {

			//////////
			// solr //
			//////////
			l();
			tl(1, "public ", entitySolrSimpleName, " solr", entityVarCapitalized, "() {");
			if(entitySimpleName.equals("Chain")) {
				tl(2, "return ", entityVar, " == null ? null : ", entityVar, ".toString();");
			}
			else if(entitySimpleName.equals("Timestamp")) {
				tl(2, "return ", entityVar, " == null ? null : Date.from(", entityVar, ".toInstant());");
			}
			else if(entityCanonicalName.toString().equals(LocalDateTime.class.getCanonicalName())) {
				tl(2, "return ", entityVar, " == null ? null : Date.from(", entityVar, ".atZone(ZoneId.systemDefault()).toInstant());");
			}
			else if(entitySimpleName.toString().equals("LocalDate")) {
				tl(2, "return ", entityVar, " == null ? null : Date.from(", entityVar, ".atStartOfDay(ZoneId.systemDefault()).toInstant());");
			}
			else if(entitySimpleName.toString().equals("BigDecimal")) {
				tl(2, "return ", entityVar, " == null ? null : ", entityVar, ".doubleValue();");
			}
			else {
				tl(2, "return ", entityVar, ";");
			}
			tl(1, "}");

			/////////
			// str //
			/////////
			l();
			tl(1, "public String str", entityVarCapitalized, "() {");
			if(VAL_canonicalNameString.equals(entityCanonicalName))
				tl(2, "return ", entityVar, " == null ? \"\" : ", entityVar, ";");
			else
				tl(2, "return ", entityVar, " == null ? \"\" : ", entityVar, ".toString();");
			tl(1, "}");

			//////////////////
			// nomAffichage //
			//////////////////
			l();
			tl(1, "public String nomAffichage", entityVarCapitalized, "() {");
			tl(2, "return ", entityDisplayName == null ? "null" : "\"" + StringEscapeUtils.escapeJava(entityDisplayName) + "\"", ";");
			tl(1, "}");

			/////////////////
			// htmlTooltip //
			/////////////////
			l();
			tl(1, "public String htmlTooltip", entityVarCapitalized, "() {");
			tl(2, "return ", entityHtmlTooltip == null ? "null" : "\"" + StringEscapeUtils.escapeJava(entityHtmlTooltip) + "\"", ";");
			tl(1, "}");

			//////////
			// html //
			//////////

			l();
			tl(1, "public String html", entityVarCapitalized, "() {");
			tl(2, "return ", entityVar, " == null ? \"\" : StringEscapeUtils.escapeHtml4(str", entityVarCapitalized, "());");
			tl(1, "}");

			if(entityVarCapitalized != null && classSaved && entitySolrCanonicalName != null) {
				l();
				tl(1, "public void html", entityVarCapitalized, "(HttpServerResponse r, Boolean patchDroits) {");
				tl(2, "if(", classVarPrimaryKey, "!= null) {");
				tl(3, "r.write(\"<div id=\\\"patch", classSimpleName, "\").write(str", StringUtils.capitalize(classVarPrimaryKey), "()).write(\"", entityVarCapitalized, "\\\">\");");
				tl(3, "if(patchDroits) {");
				tl(4, "r.write(\"\\n\");");
				tl(4, "r.write(\"	<script>//<![CDATA[\\n\");");
				tl(4, "r.write(\"		function patch", classSimpleName, "\").write(str", StringUtils.capitalize(classVarPrimaryKey), "()).write(\"", entityVarCapitalized, "() {\\n\");");
				tl(4, "r.write(\"			$.ajax({\\n\");");
				tl(4, "r.write(\"				url: '", classApiUri, "?fq=", classVarPrimaryKey, ":\").write(str", StringUtils.capitalize(classVarPrimaryKey), "()).write(\"',\\n\");");
				tl(4, "r.write(\"				dataType: 'json',\\n\");");
				tl(4, "r.write(\"				type: 'patch',\\n\");");
				tl(4, "r.write(\"				contentType: 'application/json',\\n\");");
				tl(4, "r.write(\"				processData: false,\\n\");");
				tl(4, "r.write(\"				success: function( data, textStatus, jQxhr ) {\\n\");");
				tl(4, "r.write(\"					\\n\");");
				tl(4, "r.write(\"				},\\n\");");
				tl(4, "r.write(\"				error: function( jqXhr, textStatus, errorThrown ) {\\n\");");
				tl(4, "r.write(\"					\\n\");");
				tl(4, "r.write(\"				},\\n\");");
				tl(4, "r.write(\"				data: {\\\"set", entityVarCapitalized, "\\\": this.value },\\n\");");
				tl(4, "r.write(\"				\\n\");");
				tl(4, "r.write(\"			});\\n\");");
				tl(4, "r.write(\"		}\\n\");");
				tl(4, "r.write(\"	//]]></script>\\n\");");
				tl(4, "r.write(\"	<div class=\\\"\\\">\\n\");");
				tl(4, "r.write(\"		<label class=\\\"w3-tooltip \\\">\\n\");");
				tl(4, "r.write(\"			<span>\").write(StringEscapeUtils.escapeHtml4(nomAffichage", entityVarCapitalized, "())).write(\"</span>\\n\");");
				tl(4, "r.write(\"			<input\");"); {
					tl(7, "r.write(\" name=\\\"", entityVar, "\\\"\");");
					tl(7, "r.write(\" value=\\\"\").write(html", entityVarCapitalized, "()).write(\"\\\");\");");
					tl(7, "r.write(\" onchange=\\\"\\\"\");");
					tl(7, "r.write(\"/>\\n\");");
				}
				if(entityHtmlTooltip != null)
					tl(4, "r.write(\"<span class=\\\"w3-text w3-tag site-tooltip \\\">", StringEscapeUtils.escapeJava(entityHtmlTooltip), "</span>\");");
				tl(4, "r.write(\"		</label>\\n\");");
				tl(4, "r.write(\"	</div>\\n\");");
				tl(3, "} else {");
				tl(4, "r.write(html", entityVarCapitalized, "());");
				tl(3, "}");
				tl(3, "r.write(\"</div>\\n\");");
				tl(2, "}");
				tl(1, "}");
			}
		}

		////////////////////
		// codeIninitLoin //
		////////////////////
		o = codeInitDeep;
		if(entityInitialized) {
			tl(3, entityVar, "Init();");
		}


		/////////////////////
		// codeSiteRequest //
		/////////////////////
		if(classInitDeep && entityInitDeep) {
			o = codeSiteRequest;
			tl(2, entityVar, ".setRequeteSite_(siteRequest);");
		}

		/////////////////
		// codeIndex //
		/////////////////
		o = codeIndex;
		if(classIndexed && entityIndexedOrStored) {
			tl(2, "if(", entityVar, " != null) {");
			if(StringUtils.isNotEmpty(classVarPrimaryKey) && entityPrimaryKey) {
				// primaryKey
				tl(3, "document.addField(\"", classVarPrimaryKey, "\", ", entityVar, ");");
			}
			if(StringUtils.isNotEmpty(entityVarEncrypted) && entityEncrypted) {
				// crypte
				tl(3, "String valEncrypted = siteRequest.encryptStr(", entityVar, ");");
				tl(3, "document.addField(\"", entityVarEncrypted, "\"", "valEncrypted);");
			}
			if(StringUtils.isNotEmpty(entityVarIncremented) && entityIncremented) {
				// crypte
				tl(3, "document.addField(\"", entityVarIncremented, "\", new java.util.HashMap<String, ", entitySimpleName, ">() {{ put(\"inc\"", ("Long".equals(entitySimpleName.toString()) ? "1L" : "1"), "); }});");
			}
			if(StringUtils.isNotEmpty(entityVarSuggested) && entitySuggested) {
				// suggere
				if(entitySimpleName.equals("Chain")) {
					tl(3, "document.addField(\"", entityVarSuggested, "\", ", entityVar, ");");
				}
				else if(entitySimpleName.equals("Timestamp")) {
					tl(3, "document.addField(\"", entityVarSuggested, "\", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(java.time.ZonedDateTime.ofInstant(", entityVar, ".toLocalDateTime(), java.time.OffsetDateTime.now().getOffset(), ZoneId.of(\"UTC\"))));");
				}
				else if(entityCanonicalName.toString().equals(LocalDateTime.class.getCanonicalName())) {
					tl(3, "document.addField(\"", entityVarSuggested, "\", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(java.time.ZonedDateTime.ofInstant(", entityVar, ", java.time.OffsetDateTime.now().getOffset(), ZoneId.of(\"UTC\"))));");
				}
				else if(entitySimpleName.toString().equals("LocalDate")) {
					tl(3, "document.addField(\"", entityVarSuggested, "\", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(", entityVar, ".atStartOfDay(ZoneId.of(\"UTC\"))));");
				}
				else {
					tl(3, "document.addField(\"", entityVarSuggested, "\", ", entityVar, ");");
				}
			}

			if(StringUtils.isNotEmpty(entityVarIndexed) && entityIndexed) {
				// indexe
				if(entitySimpleName.equals("Chain")) {
					tl(3, "document.addField(\"", entityVarIndexed, "\", ", entityVar, ");");
				}
				else if(entitySimpleName.equals("Timestamp")) {
					tl(3, "document.addField(\"", entityVarIndexed, "\", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(java.time.ZonedDateTime.ofInstant(", entityVar, ".toLocalDateTime(), java.time.OffsetDateTime.now().getOffset(), ZoneId.of(\"UTC\"))));");
				}
				else if(entityCanonicalName.toString().equals(LocalDateTime.class.getCanonicalName())) {
					tl(3, "document.addField(\"", entityVarIndexed, "\", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(java.time.ZonedDateTime.ofInstant(", entityVar, ", java.time.OffsetDateTime.now().getOffset(), ZoneId.of(\"UTC\"))));");
				}
				else if(entitySimpleName.toString().equals("LocalDate")) {
					tl(3, "document.addField(\"", entityVarIndexed, "\", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(", entityVar, ".atStartOfDay(ZoneId.of(\"UTC\"))));");
				}
				else if(entitySimpleName.equals("List") || entitySimpleName.equals("ArrayList")) {
					tl(3, "for(", entityCanonicalNameGeneric, " o : ", entityVar, ") {");
					tl(4, "document.addField(\"", entityVarIndexed, "\", o);");
					tl(3, "}");
				}
				else {
					tl(3, "document.addField(\"", entityVarIndexed, "\", ", entityVar, ");");
				}
			}
			else {
				if(StringUtils.isNotEmpty(entityVarIndexed)) {
					tl(3, "document.addField(\"", entityVarIndexed, "\", ", entityVar, ");");
				}
			}

			if(StringUtils.isNotEmpty(entityVarStored) && entityStored) {
				// stocke
				if(entitySimpleName.equals("Chain")) {
					tl(3, "document.addField(\"", entityVarStored, "\", ", entityVar, ");");
				}
				else if(entitySimpleName.equals("Timestamp")) {
					tl(3, "document.addField(\"", entityVarStored, "\", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(java.time.ZonedDateTime.ofInstant(", entityVar, ".toLocalDateTime(), java.time.OffsetDateTime.now().getOffset(), ZoneId.of(\"UTC\"))));");
				}
				else if(entityCanonicalName.toString().equals(LocalDateTime.class.getCanonicalName())) {
					tl(3, "document.addField(\"", entityVarStored, "\", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(java.time.ZonedDateTime.ofInstant(", entityVar, ", java.time.OffsetDateTime.now().getOffset(), ZoneId.of(\"UTC\"))));");
				}
				else if(entitySimpleName.toString().equals("LocalDate")) {
					tl(3, "document.addField(\"", entityVarStored, "\", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(", entityVar, ".atStartOfDay(ZoneId.of(\"UTC\"))));");
				}
				else if(entitySimpleName.equals("List") || entitySimpleName.equals("ArrayList")) {
					tl(3, "for(", entityCanonicalNameGeneric, " o : ", entityVar, ") {");
					tl(4, "document.addField(\"", entityVarStored, "\", o);");
					tl(3, "}");
				}
				else {
					tl(3, "document.addField(\"", entityVarStored, "\", ", entityVar, ");");
				}
			}
			tl(2, "}");
		}

		/////////////////
		// codeObtain //
		/////////////////
		o = codeObtain;
		if(classExtendsBase || classIsBase) {
			tl(3, "case \"", entityVar, "\":");
			tl(4, "return o", classSimpleName, ".", entityVar, ";");
		}	

		///////////////////
		// codeAttribute //
		///////////////////
		o = codeAttribute;
		if((classExtendsBase || classIsBase) && entityAttribute) {
			tl(3, "case \"", entityVar, "\":");
			if(StringUtils.equals(entityCanonicalName, List.class.getCanonicalName()) || StringUtils.equals(entityCanonicalName, ArrayList.class.getCanonicalName()))
				tl(4, "o", classSimpleName, ".add", entityVarCapitalized, "((", entitySimpleNameCompleteGeneric, ")val);");
			else
				tl(4, "o", classSimpleName, ".set", entityVarCapitalized, "((", entitySimpleNameComplete, ")val);");
			tl(4, "return val;");
		}	

		/////////////
		// codePut //
		/////////////
		o = codePut;
		if(classSaved && BooleanUtils.isTrue(entityDefined)) {
//		if((classExtendsBase || classIsBase) && BooleanUtils.isTrue(entityDefined)) {
//							if(field.contientSetterString) {
//							if(entityContientSetterString) {
				tl(3, "case \"", entityVar, "\":");
				if(StringUtils.equals(entityCanonicalName, List.class.getCanonicalName()) || StringUtils.equals(entityCanonicalName, ArrayList.class.getCanonicalName())) {
					tl(4, "add", entityVarCapitalized, "(requestJson.get", entitySimpleNameVertxJson, "(var));");
				}
				else {
					tl(4, "set", entityVarCapitalized, "(requestJson.get", entitySimpleNameVertxJson, "(var));");
				}
				tl(4, "return true;");
//							}
		}	

		/////////////////
		// codePopulate //
		/////////////////
		o = codePopulate;
		if(classSaved) {
//							String nomChamp = entityVar.toString();
//							String varCrypte = entityVarEncrypted.toString();
//							String varStocke = entityVarStored.toString();
//							String varSuggere = entityVarSuggested.toString();
//							String varIncremente = entityVarIncremented.toString();
//							String varCleUnique = entityVarCleUniqueActuel.toString();
			if(!StringUtils.isEmpty(entityVarEncrypted) || !StringUtils.isEmpty(entityVarStored) || !StringUtils.isEmpty(classVarPrimaryKey) || !StringUtils.isEmpty(entityVarSuggested) || !StringUtils.isEmpty(entityVarIncremented)) {
				tl(0);

				if(!StringUtils.isEmpty(entityVarSuggested)) {
//									tl(3, "if(sauvegardes", classSimpleName, ".contains(\"", entityVar, "\")) {");
					tl(4, entitySolrCanonicalName, " ", entityVar, " = (", entitySolrCanonicalName, ")solrDocument.get(\"", entityVarSuggested, "\");");
					tl(4, "o", classSimpleName, ".set", entityVarCapitalized, "(", entityVar, ");");
//									tl(3, "}");
				}
				else if(!StringUtils.isEmpty(entityVarIncremented)) {
//									tl(3, "if(sauvegardes", classSimpleName, ".contains(\"", entityVar, "\")) {");
					tl(4, entitySolrCanonicalName, " ", entityVar, " = (", entitySolrCanonicalName, ")solrDocument.get(\"", entityVarIncremented, "\");");
					tl(4, "o", classSimpleName, ".set", entityVarCapitalized, "(", entityVar, ");");
//									tl(3, "}");
				}
				else if(!StringUtils.isEmpty(classVarPrimaryKey)) {
//									tl(3, "if(sauvegardes", classSimpleName, ".contains(\"", entityVar, "\")) {");
					tl(4, entitySolrCanonicalName, " ", entityVar, " = org.apache.commons.lang3.math.NumberUtils.toLong((String)solrDocument.get(\"", classVarPrimaryKey, "\"));");
					tl(4, "o", classSimpleName, ".set", entityVarCapitalized, "(", entityVar, ");");
//									tl(3, "}");
				}
				else if(!StringUtils.isEmpty(entityVarEncrypted)) {
//									tl(3, "if(sauvegardes", classSimpleName, ".contains(\"", entityVar, "\")) {");
					if(siteEncrypted)
						tl(4, entitySolrCanonicalName, " ", entityVar, " = siteRequest.deencryptStr((", entitySolrCanonicalName, ")solrDocument.get(\"", entityVarEncrypted, "\"));");
					else
						tl(4, entitySolrCanonicalName, " ", entityVar, " = (", entitySolrCanonicalName, ")solrDocument.get(\"", entityVarEncrypted, "\");");
					tl(4, "o", classSimpleName, ".set", entityVarCapitalized, "(", entityVar, ");");
//									tl(3, "}");
				}
				else {
//									tl(3, "if(sauvegardes", classSimpleName, ".contains(\"", entityVar, "\")) {");
					tl(4, entitySolrCanonicalName, " ", entityVar, " = (", entitySolrCanonicalName, ")solrDocument.get(\"", entityVarStored, "\");");
					tl(4, "if(", entityVar, " != null)");
					if(StringUtils.contains(entitySolrCanonicalName, "<"))
						tl(5, "o", classSimpleName, ".", entityVar, ".addAll(", entityVar, ");");
					else
						tl(5, "o", classSimpleName, ".set", entityVarCapitalized, "(", entityVar, ");");
//									tl(3, "}");
				}

			}
		}	

		/////////////////////
		// codeSave //
		/////////////////////
		o = codeSave;
		if(classSaved) {
				String nomChamp = entityVar.toString();
				if(entitySaved) {
					tl(0);

					tl(2, "if(\"true\".equals(siteRequest.request.getParameter(\"", nomChamp, "Supprimer\"))) {");
					tl(3, "coureur.update(sqlDeleteP /*delete from p where chemin=*/, \"", nomChamp, "\" /* and pk_objet=*/, pk);");
					tl(2, "} else if(definirPourClasse(\"", nomChamp, "\"", "siteRequest.request.getParameterValues(\"", nomChamp, "\"))) {");
					if(siteEncrypted) {
						tl(3, "String valEncrypted = siteRequest.encryptStr(", nomChamp, ");");
						tl(3, "coureur.insert(sqlInsertP, gestionnaireListe /*insert into p(chemin, valeur, pk_objet) values(*/, \"", nomChamp, "\"", "valEncrypted, pk /*) on conflict(chemin, pk_objet) do update set valeur=*/, valEncrypted /* where p.chemin=*/, \"", nomChamp, "\" /* and p.pk_objet=*/, pk);");
					}
					else {
						tl(3, "coureur.insert(sqlInsertP, gestionnaireListe /*insert into p(chemin, valeur, pk_objet) values(*/, \"", nomChamp, "\"", nomChamp, ", ", "pk /*) on conflict(chemin, pk_objet) do update set valeur=*/, ", nomChamp, " /* where p.chemin=*/, \"", nomChamp, "\" /* and p.pk_objet=*/, pk);");
					}
					tl(3, "sauvegardes", classSimpleName, ".add(\"", nomChamp, "\");");
					tl(2, "}");
				}

//								if(field.cles && field.contexteParent != null) {
//									tl(0);
//									String parentContexteVar = entityVar.toString() + "VarInverse";
//									String chaineVarInverse = field.contexteParent.obtenirPourClasse(parentContexteVar).toString();
//									String var1, var2, val1, val2, val, valSupprimer, varSupprimer;
//									if(nomChamp.compareTo(chaineVarInverse) < 0) {
//										var1 = nomChamp;
//										var2 = chaineVarInverse;
//										varSupprimer = field.contexteEnfant.nomVarMinuscule + (siteRequest ? "Cle" : "Key");
//										valSupprimer = nomChamp + (entityCanonicalName.equals(ArrayList.class.getCanonicalName()) ? ".get(0)" : "");
//										val1 = varSupprimer;
//										val2 = siteRequest ? "pk" : "key";
//									}
//									else {
//										var1 = chaineVarInverse;
//										var2 = nomChamp;
//										varSupprimer = field.contexteEnfant.nomVarMinuscule + (siteRequest ? "Cle" : "Key");
//										valSupprimer = nomChamp + (entityCanonicalName.equals(ArrayList.class.getCanonicalName()) ? ".get(0)" : "");
//										val1 = siteRequest ? "pk" : "key";
//										val2 = varSupprimer;
//									}
//	
//									tl(2, "{");
//									tl(3, "String[] valeursCles = siteRequest.request.getParameterValues(\"", nomChamp, "\");");
//									tl(3, "if(valeursCles != null) {");
//									tl(4, "String[] valeursSuppression = siteRequest.request.getParameterValues(\"", nomChamp, "Supprimer\");");
//									tl(4, "Long ", varSupprimer, " = Long.parseLong(valeursCles[valeursCles.length - 1]);");
//									tl(4, "if(valeursSuppression != null && \"true\".equals(valeursSuppression[valeursSuppression.length - 1])) {");
//									tl(5, "coureur.update(sqlDeleteA /*delete from a where field1=*/, \"", var1, "\" /* and pk1=*/, ", val1, " /* and field2=*/, \"", var2, "\" /* and pk2=*/, ", val2, ");");
//									tl(4, "} else if(definirPourClasse(\"", nomChamp, "\"", "valeursCles[valeursCles.length - 1])) {");
//	//								tl(5, varSupprimer, " = ", valSupprimer, ";");
//									tl(5, "coureur.insert(sqlInsertA, gestionnaireListe /*insert into a(field1, pk1, field2, pk2) values(*/, \"", var1, "\"", val1, ", \"", var2, "\"", val2, " /*) on conflict do nothing */);");
//									tl(5, "sauvegardes", classSimpleName, ".add(\"", nomChamp, "\");");
//									tl(4, "}");
//									tl(4, "if(", varSupprimer, " != null) {");
//									tl(5, field.contexteEnfant.classSimpleName, " ", field.contexteEnfant.nomVarMinuscule, " = new ", field.contexteEnfant.classSimpleName, "();");
//									tl(5, field.contexteEnfant.nomVarMinuscule, ".pk(", varSupprimer, ");");
//									tl(5, field.contexteEnfant.nomVarMinuscule, ".sauvegardesPourClasse(siteRequest);");
//									tl(5, field.contexteEnfant.nomVarMinuscule, ".initLoinPourClasse(siteRequest_);");
//									tl(5, field.contexteEnfant.nomVarMinuscule, ".indexerPourClasse(siteRequest);");
//									tl(4, "}");
//									tl(3, "}");
//									tl(2, "}");
//								}
		}	

		/////////////////
		// codeApiChamps //
		/////////////////
		o = codeApiEntities;
		l();
		tl(1, "public static final String ENTITY_VAR_", entityVar, " = \"", entityVar, "\";");
		if(classIndexed) {
			if(entityIndexed)
				tl(1, "public static final String ENTITY_VAR_INDEXED_", entityVar, " = \"", entityVarIndexed, "\";");
			if(entityStored)
				tl(1, "public static final String ENTITY_VAR_STORED_", entityVar, " = \"", entityVarStored, "\";");
			if(entityEncrypted)
				tl(1, "public static final String ENTITY_VAR_ENCRYPTED_", entityVar, " = \"", entityVarEncrypted, "\";");
		}
		if(entityAttribute)
			tl(1, "public static final String ENTITY_VAR_", entityVar, "_ATTRIBUTE_", entityAttributeNomSimple, "_", entityAttributeVar, " = \"", entityAttributeVar, "\";");

		/////////////////
		// codeApiGet //
		/////////////////
		o = codeApiGet;
		if(classIndexed && entityIndexed) {
			tl(3, "case ENTITY_VAR_", entityVar, ":");
			tl(4, "return ENTITY_VAR_INDEXED_", entityVar, ";");
		}

		///////////////////////
		// codeApiGenerateGet //
		///////////////////////
		o = codeApiGenerateGet;
		if(classIndexed && entityStored) {
			tl(4, "if(ENTITY_VAR_STORED_", entityVar, ".equals(entityVarStored)) {");
			if (VAL_canonicalNameBoolean.equals(entitySolrCanonicalName)) {
				tl(5, "if(j > 0)");
				tl(6, "serverResponse.write(\", \");");
				tl(5, "serverResponse.write(\"\\\"", entityVar, "\\\": \");");
				tl(5, "serverResponse.write(((Boolean)fieldValue).toString());");
				tl(5, "serverResponse.write(\"\\n\");");
				tl(5, "j++;");
				tl(5, "return j;");
			} else if (VAL_canonicalNameDate.equals(entitySolrCanonicalName)) {
				if (VAL_canonicalNameTimestamp.equals(entityCanonicalName)) {
					tl(5, "if(j > 0)");
					tl(6, "serverResponse.write(\", \");");
					tl(5, "serverResponse.write(\"\\\"", entityVar, "\\\": \\\"\");");
					tl(5, "serverResponse.write(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)fieldValue).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
					tl(5, "serverResponse.write(\"\\\"\\n\");");
					tl(5, "j++;");
					tl(5, "return j;");
				} else if (VAL_canonicalNameLocalDateTime.equals(entityCanonicalName)) {
					tl(5, "if(j > 0)");
					tl(6, "serverResponse.write(\", \");");
					tl(5, "serverResponse.write(\"\\\"", entityVar, "\\\": \\\"\");");
					tl(5, "serverResponse.write(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)fieldValue).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
					tl(5, "serverResponse.write(\"\\\"\\n\");");
					tl(5, "j++;");
					tl(5, "return j;");
				} else if (VAL_canonicalNameLocalDate.equals(entityCanonicalName)) {
					tl(5, "if(j > 0)");
					tl(6, "serverResponse.write(\", \");");
					tl(5, "serverResponse.write(\"\\\"", entityVar, "\\\": \\\"\");");
					tl(5, "serverResponse.write(DateTimeFormatter.ISO_OFFSET_DATE.format(((Date)fieldValue).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));");
					tl(5, "serverResponse.write(\"\\\"\\n\");");
					tl(5, "j++;");
					tl(5, "return j;");
				} else {
					tl(5, "if(j > 0)");
					tl(6, "serverResponse.write(\", \");");
					tl(5, "serverResponse.write(\"\\\"", entityVar, "\\\": \\\"\");");
					tl(5, "serverResponse.write(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)fieldValue).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
					tl(5, "serverResponse.write(\"\\\"\\n\");");
					tl(5, "j++;");
					tl(5, "return j;");
				}
			} else if (VAL_canonicalNameLong.equals(entitySolrCanonicalName)) {
				tl(5, "if(j > 0)");
				tl(6, "serverResponse.write(\", \");");
				tl(5, "serverResponse.write(\"\\\"", entityVar, "\\\": \");");
				tl(5, "serverResponse.write(((Long)fieldValue).toString());");
				tl(5, "serverResponse.write(\"\\n\");");
				tl(5, "j++;");
				tl(5, "return j;");
			} else if (VAL_canonicalNameDouble.equals(entitySolrCanonicalName)) {
				if (VAL_canonicalNameBigDecimal.equals(entityCanonicalName)) {
					tl(5, "if(j > 0)");
					tl(6, "serverResponse.write(\", \");");
					tl(5, "serverResponse.write(\"\\\"", entityVar, "\\\": \");");
					tl(5, "serverResponse.write(BigDecimal.valueOf((Double)fieldValue).toString());");
					tl(5, "serverResponse.write(\"\\n\");");
					tl(5, "j++;");
					tl(5, "return j;");
				}
				else {
					tl(5, "if(j > 0)");
					tl(6, "serverResponse.write(\", \");");
					tl(5, "serverResponse.write(\"\\\"", entityVar, "\\\": \");");
					tl(5, "serverResponse.write(((Double)fieldValue).toString());");
					tl(5, "serverResponse.write(\"\\n\");");
					tl(5, "j++;");
					tl(5, "return j;");
				}
			} else if (VAL_canonicalNameFloat.equals(entitySolrCanonicalName)) {
				tl(5, "if(j > 0)");
				tl(6, "serverResponse.write(\", \");");
				tl(5, "serverResponse.write(\"\\\"", entityVar, "\\\": \");");
				tl(5, "serverResponse.write(((Float)fieldValue).toString());");
				tl(5, "serverResponse.write(\"\\n\");");
				tl(5, "j++;");
				tl(5, "return j;");
			} else if (VAL_canonicalNameInteger.equals(entitySolrCanonicalName)) {
				tl(5, "if(j > 0)");
				tl(6, "serverResponse.write(\", \");");
				tl(5, "serverResponse.write(\"\\\"", entityVar, "\\\": \");");
				tl(5, "serverResponse.write(((Integer)fieldValue).toString());");
				tl(5, "serverResponse.write(\"\\n\");");
				tl(5, "j++;");
				tl(5, "return j;");
			} else {
				if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameList, VAL_canonicalNameArrayList)) {
					if(VAL_canonicalNameBoolean.equals(entityCanonicalNameGeneric)) {
						tl(5, "if(j > 0)");
						tl(6, "serverResponse.write(\", \");");
						tl(5, "serverResponse.write(\"\\\"", entityVar, "\\\": \");");
						tl(5, "int k = 0;");
						tl(5, "while(fieldValue != null) {");
						tl(6, "if(k > 0)");
						tl(7, "serverResponse.write(\", \");");
						tl(6, "serverResponse.write(((Boolean)fieldValue).toString());");
						tl(6, "fieldValue = fieldValues.iterator().next();");
						tl(5, "}");
						tl(5, "serverResponse.write(VAL_guillmetsFin);");
						tl(5, "j++;");
						tl(5, "return j;");
					}
					else if(VAL_canonicalNameDate.equals(entityCanonicalNameGeneric)) {
						tl(5, "if(j > 0)");
						tl(6, "serverResponse.write(\", \");");
						tl(5, "serverResponse.write(\"\\\"", entityVar, "\\\": \");");
						tl(5, "int k = 0;");
						tl(5, "while(fieldValue != null) {");
						tl(6, "if(k > 0)");
						tl(7, "serverResponse.write(\", \");");
						tl(6, "serverResponse.write(VAL_citation);");
						tl(6, "serverResponse.write(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)fieldValue).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
						tl(6, "serverResponse.write(VAL_citation);");
						tl(6, "fieldValue = fieldValues.iterator().next();");
						tl(5, "}");
						tl(5, "serverResponse.write(VAL_guillmetsFin);");
						tl(5, "j++;");
						tl(5, "return j;");
					}
					else if(VAL_canonicalNameTimestamp.equals(entityCanonicalNameGeneric)) {
						tl(5, "if(j > 0)");
						tl(6, "serverResponse.write(\", \");");
						tl(5, "serverResponse.write(VAL_citation);");
						tl(5, "serverResponse.write(ENTITY_VAR_", entityVar, ");");
						tl(5, "serverResponse.write(VAL_citationDeuxPointsEspaceGuillmets);");
						tl(5, "int k = 0;");
						tl(5, "while(fieldValue != null) {");
						tl(6, "if(k > 0)");
						tl(7, "serverResponse.write(\", \");");
						tl(6, "serverResponse.write(VAL_citation);");
						tl(6, "serverResponse.write(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)fieldValue).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
						tl(6, "serverResponse.write(VAL_citation);");
						tl(6, "fieldValue = fieldValues.iterator().next();");
						tl(5, "}");
						tl(5, "serverResponse.write(VAL_guillmetsFin);");
						tl(5, "j++;");
						tl(5, "return j;");
					}
					else if(VAL_canonicalNameLocalDateTime.equals(entityCanonicalNameGeneric)) {
						tl(5, "if(j > 0)");
						tl(6, "serverResponse.write(\", \");");
						tl(5, "serverResponse.write(VAL_citation);");
						tl(5, "serverResponse.write(ENTITY_VAR_", entityVar, ");");
						tl(5, "serverResponse.write(VAL_citationDeuxPointsEspaceGuillmets);");
						tl(5, "int k = 0;");
						tl(5, "while(fieldValue != null) {");
						tl(6, "if(k > 0)");
						tl(7, "serverResponse.write(\", \");");
						tl(6, "serverResponse.write(VAL_citation);");
						tl(6, "serverResponse.write(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)fieldValue).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
						tl(6, "serverResponse.write(VAL_citation);");
						tl(6, "fieldValue = fieldValues.iterator().next();");
						tl(5, "}");
						tl(5, "serverResponse.write(VAL_guillmetsFin);");
						tl(5, "j++;");
						tl(5, "return j;");
					}
					else if(VAL_canonicalNameLocalDate.equals(entityCanonicalNameGeneric)) {
						tl(5, "if(j > 0)");
						tl(6, "serverResponse.write(\", \");");
						tl(5, "serverResponse.write(VAL_citation);");
						tl(5, "serverResponse.write(ENTITY_VAR_", entityVar, ");");
						tl(5, "serverResponse.write(VAL_citationDeuxPointsEspaceGuillmets);");
						tl(5, "int k = 0;");
						tl(5, "while(fieldValue != null) {");
						tl(6, "if(k > 0)");
						tl(7, "serverResponse.write(\", \");");
						tl(6, "serverResponse.write(VAL_citation);");
						tl(6, "serverResponse.write(DateTimeFormatter.ISO_OFFSET_DATE.format(((Date)fieldValue).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));");
						tl(6, "serverResponse.write(VAL_citation);");
						tl(6, "fieldValue = fieldValues.iterator().next();");
						tl(5, "}");
						tl(5, "serverResponse.write(VAL_guillmetsFin);");
						tl(5, "j++;");
						tl(5, "return j;");
					}
					else if(VAL_canonicalNameLong.equals(entityCanonicalNameGeneric)) {
						tl(5, "if(j > 0)");
						tl(6, "serverResponse.write(\", \");");
						tl(5, "serverResponse.write(VAL_citation);");
						tl(5, "serverResponse.write(ENTITY_VAR_", entityVar, ");");
						tl(5, "serverResponse.write(VAL_citationDeuxPointsEspaceGuillmets);");
						tl(5, "int k = 0;");
						tl(5, "while(fieldValue != null) {");
						tl(6, "if(k > 0)");
						tl(7, "serverResponse.write(\", \");");
						tl(6, "serverResponse.write(((Long)fieldValue).toString());");
						tl(6, "fieldValue = fieldValues.iterator().next();");
						tl(5, "}");
						tl(5, "serverResponse.write(VAL_guillmetsFin);");
						tl(5, "j++;");
						tl(5, "return j;");
					}
					else if(VAL_canonicalNameBigDecimal.equals(entityCanonicalNameGeneric)) {
						tl(5, "if(j > 0)");
						tl(6, "serverResponse.write(\", \");");
						tl(5, "serverResponse.write(VAL_citation);");
						tl(5, "serverResponse.write(ENTITY_VAR_", entityVar, ");");
						tl(5, "serverResponse.write(VAL_citationDeuxPointsEspaceGuillmets);");
						tl(5, "int k = 0;");
						tl(5, "while(fieldValue != null) {");
						tl(6, "if(k > 0)");
						tl(7, "serverResponse.write(\", \");");
						tl(6, "serverResponse.write(BigDecimal.valueOf((Double)fieldValue).toString());");
						tl(6, "fieldValue = fieldValues.iterator().next();");
						tl(5, "}");
						tl(5, "serverResponse.write(VAL_guillmetsFin);");
						tl(5, "j++;");
						tl(5, "return j;");
					}
					else if(VAL_canonicalNameDouble.equals(entityCanonicalNameGeneric)) {
						tl(5, "if(j > 0)");
						tl(6, "serverResponse.write(\", \");");
						tl(5, "serverResponse.write(VAL_citation);");
						tl(5, "serverResponse.write(ENTITY_VAR_", entityVar, ");");
						tl(5, "serverResponse.write(VAL_citationDeuxPointsEspaceGuillmets);");
						tl(5, "int k = 0;");
						tl(5, "while(fieldValue != null) {");
						tl(6, "if(k > 0)");
						tl(7, "serverResponse.write(\", \");");
						tl(6, "serverResponse.write(((Double)fieldValue).toString());");
						tl(6, "fieldValue = fieldValues.iterator().next();");
						tl(5, "}");
						tl(5, "serverResponse.write(VAL_guillmetsFin);");
						tl(5, "j++;");
						tl(5, "return j;");
					}
					else if(VAL_canonicalNameFloat.equals(entityCanonicalNameGeneric)) {
						tl(5, "if(j > 0)");
						tl(6, "serverResponse.write(\", \");");
						tl(5, "serverResponse.write(VAL_citation);");
						tl(5, "serverResponse.write(ENTITY_VAR_", entityVar, ");");
						tl(5, "serverResponse.write(VAL_citationDeuxPointsEspaceGuillmets);");
						tl(5, "int k = 0;");
						tl(5, "while(fieldValue != null) {");
						tl(6, "if(k > 0)");
						tl(7, "serverResponse.write(\", \");");
						tl(6, "serverResponse.write(((Float)fieldValue).toString());");
						tl(6, "fieldValue = fieldValues.iterator().next();");
						tl(5, "}");
						tl(5, "serverResponse.write(VAL_guillmetsFin);");
						tl(5, "j++;");
						tl(5, "return j;");
					}
					else if(VAL_canonicalNameInteger.equals(entityCanonicalNameGeneric)) {
						tl(5, "if(j > 0)");
						tl(6, "serverResponse.write(\", \");");
						tl(5, "serverResponse.write(VAL_citation);");
						tl(5, "serverResponse.write(ENTITY_VAR_", entityVar, ");");
						tl(5, "serverResponse.write(VAL_citationDeuxPointsEspaceGuillmets);");
						tl(5, "int k = 0;");
						tl(5, "while(fieldValue != null) {");
						tl(6, "if(k > 0)");
						tl(7, "serverResponse.write(\", \");");
						tl(6, "serverResponse.write(((Integer)fieldValue).toString());");
						tl(6, "fieldValue = fieldValues.iterator().next();");
						tl(5, "}");
						tl(5, "serverResponse.write(VAL_guillmetsFin);");
						tl(5, "j++;");
						tl(5, "return j;");
					}
					else {
						tl(5, "if(j > 0)");
						tl(6, "serverResponse.write(\", \");");
						tl(5, "serverResponse.write(VAL_citation);");
						tl(5, "serverResponse.write(ENTITY_VAR_", entityVar, ");");
						tl(5, "serverResponse.write(VAL_citationDeuxPointsEspaceGuillmets);");
						tl(5, "int k = 0;");
						tl(5, "while(fieldValue != null) {");
						tl(6, "if(k > 0)");
						tl(7, "serverResponse.write(\", \");");
						tl(6, "serverResponse.write(\", \");");
						tl(6, "serverResponse.write(VAL_citation);");
						tl(6, "serverResponse.write(((String)fieldValue));");
						tl(6, "serverResponse.write(VAL_citation);");
						tl(6, "fieldValue = fieldValues.iterator().next();");
						tl(5, "}");
						tl(5, "serverResponse.write(VAL_guillmetsFin);");
						tl(5, "j++;");
						tl(5, "return j;");
					}
				}
				else {
					tl(5, "if(j > 0)");
					tl(6, "serverResponse.write(\", \");");
					tl(5, "serverResponse.write(\"\\\"", entityVar, "\\\": \\\"\");");
					tl(5, "serverResponse.write(Json.encode((String)fieldValues.iterator().next()));");
					tl(5, "serverResponse.write(\"\\\"\\n\");");
					tl(5, "j++;");
					tl(5, "return j;");
				}
			}
			tl(4, "}");
		}

		////////////////////////
		// codeApiGeneratePost //
		////////////////////////
		o = codeApiGeneratePost;

		Integer tBase = 0;
		if(classRolesFound) {
			tBase = 6;
		}
		else {
			tBase = 4;
		}
		if(classSaved && BooleanUtils.isTrue(entityDefined)) {
			tl(tBase + 6, "case \"", entityVar, "\":");
			tl(tBase + 7, "postSql.append(SiteContext.SQL_setP);");
			tl(tBase + 7, "postSqlParams.addAll(Arrays.asList(ENTITY_VAR_", entityVar, ", requestJson.get", entitySimpleNameVertxJson, "(entityVar), postPk));");
			tl(tBase + 7, "break;");
		}	

		///////////////////////
		// codeApiGeneratePut //
		///////////////////////
		o = codeApiGeneratePut;

		tBase = 0;
		if(classRolesFound) {
			tBase = 6;
		}
		else {
			tBase = 4;
		}
		if(classSaved && BooleanUtils.isTrue(entityDefined)) {
			tl(tBase + 6, "case \"", entityVar, "\":");
			tl(tBase + 7, "putSql.append(SiteContext.SQL_setP);");
			tl(tBase + 7, "putSqlParams.addAll(Arrays.asList(ENTITY_VAR_", entityVar, ", requestJson.get", entitySimpleNameVertxJson, "(entityVar), putPk));");
			tl(tBase + 7, "break;");
		}	

		////////////////////////
		// codeApiGeneratePatch //
		////////////////////////
		o = codeApiGeneratePatch;

		tBase = 0;
		if(classRolesFound) {
			tBase = 6;
		}
		else {
			tBase = 4;
		}
		if(classSaved && BooleanUtils.isTrue(entityDefined)) {
			if(BooleanUtils.isTrue(entityAttribute)) {
				if(StringUtils.equals(entityCanonicalName, List.class.getCanonicalName()) || StringUtils.equals(entityCanonicalName, ArrayList.class.getCanonicalName())) {
	
					if(StringUtils.compare(entityVar, entityAttributeVar) <= 0) {
						tl(tBase + 6, "case \"add", entityVarCapitalized, "\":");
						tl(tBase + 7, "patchSql.append(SiteContext.SQL_addA);");
						tl(tBase + 7, "patchSqlParams.addAll(Arrays.asList(");
						tl(tBase + 9, "ENTITY_VAR_", entityVar);
						tl(tBase + 9, ", patchPk");
						tl(tBase + 9, ", ENTITY_VAR_", entityVar, "_ATTRIBUTE_", entityAttributeNomSimple, "_", entityAttributeVar, "");
						tl(tBase + 9, ", requestJson.get", entityListSimpleNameVertxJson, "(methodeNom)");
						tl(tBase + 9, "));");

						tl(tBase + 6, "case \"addAll", entityVarCapitalized, "\":");
						tl(tBase + 7, entitySimpleNameVertxJson, " addAll", entityVarCapitalized, "Valeurs = requestJson.get", entitySimpleNameVertxJson, "(methodeNom);");
						tl(tBase + 7, "for(Integer i = 0; i <  addAll", entityVarCapitalized, "Valeurs.size(); i++) {");
						tl(tBase + 8, "patchSql.append(SiteContext.SQL_addA);");
						tl(tBase + 8, "patchSqlParams.addAll(Arrays.asList(");
						tl(tBase + 10, "ENTITY_VAR_", entityVar);
						tl(tBase + 10, ", patchPk");
						tl(tBase + 10, ", ENTITY_VAR_", entityVar, "_ATTRIBUTE_", entityAttributeNomSimple, "_", entityAttributeVar, "");
						tl(tBase + 10, ", addAll", entityVarCapitalized, "Valeurs.get", entityListSimpleNameVertxJson, "(i)");
						tl(tBase + 10, "));");
						tl(tBase + 7, "}");
	
						tl(tBase + 6, "case \"set", entityVarCapitalized, "\":");
						tl(tBase + 7, entitySimpleNameVertxJson, " set", entityVarCapitalized, "Valeurs = requestJson.get", entitySimpleNameVertxJson, "(methodeNom);");
						tl(tBase + 7, "patchSql.append(SiteContext.SQL_clearA1);");
						tl(tBase + 7, "patchSqlParams.addAll(Arrays.asList(");
						tl(tBase + 9, "ENTITY_VAR_", entityVar);
						tl(tBase + 9, ", patchPk");
						tl(tBase + 9, ", ENTITY_VAR_", entityVar, "_ATTRIBUTE_", entityAttributeNomSimple, "_", entityAttributeVar, "");
						tl(tBase + 9, ", requestJson.get", entitySimpleNameVertxJson, "(methodeNom)");
						tl(tBase + 9, "));");

						tl(tBase + 7, "for(Integer i = 0; i <  set", entityVarCapitalized, "Valeurs.size(); i++) {");
						tl(tBase + 8, "patchSql.append(SiteContext.SQL_addA);");
						tl(tBase + 8, "patchSqlParams.addAll(Arrays.asList(");
						tl(tBase + 10, "ENTITY_VAR_", entityVar);
						tl(tBase + 10, ", patchPk");
						tl(tBase + 10, ", ENTITY_VAR_", entityVar, "_ATTRIBUTE_", entityAttributeNomSimple, "_", entityAttributeVar, "");
						tl(tBase + 10, ", set", entityVarCapitalized, "Valeurs.get", entityListSimpleNameVertxJson, "(i)");
						tl(tBase + 10, "));");
						tl(tBase + 7, "}");
					}
					else {
						tl(tBase + 6, "case \"add", entityVarCapitalized, "\":");
						tl(tBase + 7, "patchSql.append(SiteContext.SQL_addA);");
						tl(tBase + 7, "patchSqlParams.addAll(Arrays.asList(");
						tl(tBase + 9, "ENTITY_VAR_", entityVar);
						tl(tBase + 9, ", patchPk");
						tl(tBase + 9, ", ENTITY_VAR_", entityVar, "_ATTRIBUTE_", entityAttributeNomSimple, "_", entityAttributeVar, "");
						tl(tBase + 9, ", requestJson.get", entityListSimpleNameVertxJson, "(methodeNom)");
						tl(tBase + 9, "));");

						tl(tBase + 6, "case \"addAll", entityVarCapitalized, "\":");
						tl(tBase + 7, entitySimpleNameVertxJson, " addAll", entityVarCapitalized, "Valeurs = requestJson.get", entitySimpleNameVertxJson, "(methodeNom);");
						tl(tBase + 7, "for(Integer i = 0; i <  addAll", entityVarCapitalized, "Valeurs.size(); i++) {");
						tl(tBase + 8, "patchSql.append(SiteContext.SQL_setA2);");
						tl(tBase + 8, "patchSqlParams.addAll(Arrays.asList(");
						tl(tBase + 10, "ENTITY_VAR_", entityVar, "_ATTRIBUTE_", entityAttributeNomSimple, "_", entityAttributeVar, "");
						tl(tBase + 10, ", addAll", entityVarCapitalized, "Valeurs.get", entityListSimpleNameVertxJson, "(i)");
						tl(tBase + 10, ", ENTITY_VAR_", entityVar);
						tl(tBase + 10, ", patchPk");
						tl(tBase + 10, "));");
						tl(tBase + 7, "}");
	
						tl(tBase + 6, "case \"set", entityVarCapitalized, "\":");
						tl(tBase + 7, entitySimpleNameVertxJson, " set", entityVarCapitalized, "Valeurs = requestJson.get", entitySimpleNameVertxJson, "(methodeNom);");
						tl(tBase + 7, "patchSql.append(SiteContext.SQL_clearA2);");
						tl(tBase + 7, "patchSqlParams.addAll(Arrays.asList(");
						tl(tBase + 9, "ENTITY_VAR_", entityVar, "_ATTRIBUTE_", entityAttributeNomSimple, "_", entityAttributeVar, "");
						tl(tBase + 9, ", requestJson.get", entitySimpleNameVertxJson, "(methodeNom)");
						tl(tBase + 9, ", ENTITY_VAR_", entityVar);
						tl(tBase + 9, ", patchPk");
						tl(tBase + 9, "));");

						tl(tBase + 7, "for(Integer i = 0; i <  set", entityVarCapitalized, "Valeurs.size(); i++) {");
						tl(tBase + 8, "patchSql.append(SiteContext.SQL_setA2);");
						tl(tBase + 8, "patchSqlParams.addAll(Arrays.asList(");
						tl(tBase + 10, "ENTITY_VAR_", entityVar, "_ATTRIBUTE_", entityAttributeNomSimple, "_", entityAttributeVar, "");
						tl(tBase + 10, ", set", entityVarCapitalized, "Valeurs.get", entityListSimpleNameVertxJson, "(i)");
						tl(tBase + 10, ", ENTITY_VAR_", entityVar);
						tl(tBase + 10, ", patchPk");
						tl(tBase + 10, "));");
						tl(tBase + 7, "}");
					}
				}
				else {
	
					tl(tBase + 6, "case \"set", entityVarCapitalized, "\":");
					if(StringUtils.compare(entityVar, entityAttributeVar) <= 0) {
						tl(tBase + 7, "patchSql.append(SiteContext.SQL_setA1);");
						tl(tBase + 7, "patchSqlParams.addAll(Arrays.asList(");
						tl(tBase + 9, "ENTITY_VAR_", entityVar);
						tl(tBase + 9, ", patchPk");
						tl(tBase + 9, ", ENTITY_VAR_", entityVar, "_ATTRIBUTE_", entityAttributeNomSimple, "_", entityAttributeVar, "");
						tl(tBase + 9, ", requestJson.get", entitySimpleNameVertxJson, "(methodeNom)");
					}
					else {
						tl(tBase + 7, "patchSql.append(SiteContext.SQL_setA2);");
						tl(tBase + 7, "patchSqlParams.addAll(Arrays.asList(");
						tl(tBase + 9, "ENTITY_VAR_", entityVar, "_ATTRIBUTE_", entityAttributeNomSimple, "_", entityAttributeVar, "");
						tl(tBase + 9, ", requestJson.get", entitySimpleNameVertxJson, "(methodeNom)");
						tl(tBase + 9, ", ENTITY_VAR_", entityVar);
						tl(tBase + 9, ", patchPk");
					}
					tl(tBase + 9, "));");
				}
	
				tl(tBase + 7, "break;");
			}
			else if(BooleanUtils.isTrue(entityDefined)) {
				if(StringUtils.equals(entityCanonicalName, List.class.getCanonicalName()) || StringUtils.equals(entityCanonicalName, ArrayList.class.getCanonicalName())) {
	
					tl(tBase + 6, "case \"add", entityVarCapitalized, "\":");
					tl(tBase + 7, "patchSql.append(SiteContext.SQL_addA);");
					tl(tBase + 7, "patchSqlParams.addAll(Arrays.asList(");
					tl(tBase + 9, "ENTITY_VAR_", entityVar);
					tl(tBase + 9, ", requestJson.get", entitySimpleNameVertxJson, "(methodeNom)");
					tl(tBase + 9, ", patchPk");
					tl(tBase + 9, "));");
	
					tl(tBase + 6, "case \"set", entityVarCapitalized, "\":");
					tl(tBase + 7, "patchSql.append(SiteContext.SQL_setP);");
					tl(tBase + 7, "patchSqlParams.addAll(Arrays.asList(ENTITY_VAR_", entityVar, ", requestJson.get", entitySimpleNameVertxJson, "(methodeNom), patchPk));");
				}
				else {
	
					tl(tBase + 6, "case \"set", entityVarCapitalized, "\":");
					tl(tBase + 7, "patchSql.append(SiteContext.SQL_setP);");
					tl(tBase + 7, "patchSqlParams.addAll(Arrays.asList(ENTITY_VAR_", entityVar, ", requestJson.get", entitySimpleNameVertxJson, "(methodeNom), patchPk));");
				}
	
				tl(tBase + 7, "break;");
			}
		}	
	}

	public void  genCodeClassEnd(String languageName) throws Exception, Exception {
		//////////////////
		// codeInitDeep //
		//////////////////
		o = codeInitDeep;
		if(classInitDeep) {
//			tl(3, "alreadyInitialized", classSimpleName, " = true;");
			tl(2, "}");
			tl(2, "return (", classSimpleName, ")this;");
			tl(1, "}");
			if(classInitDeep) {
				l();
				tl(1, "public void initDeepForClass(SiteRequest siteRequest) throws Exception {");
				tl(2, "initDeep", classSimpleName, "(siteRequest);");
				tl(1, "}");  
			}
		}

		/////////////////////
		// codeSiteRequest //
		/////////////////////
		if(classInitDeep) {
			o = codeSiteRequest;
			tl(1, "}");
			l();
			tl(1, "public void siteRequestForClass(SiteRequest siteRequest) throws Exception {");
			tl(2, "siteRequest", classSimpleName, "(siteRequest);");
			tl(1, "}");  
		}

		/////////////////
		// codeIndex //
		/////////////////
		o = codeIndex;
		if(classIndexed) {
			if(classExtendsBase && !classIsBase) {
				tl(2, "super.indexer", classSuperSimpleNameGeneric, "(document);");
				tl(0);
			}
			l("\t}");

			if(StringUtils.isNotEmpty(classVarPrimaryKey)) {
				tl(0);
				tl(1, "public void unindex", classSimpleName, "() throws Exception {");
				tl(2, "SiteRequest siteRequest = new SiteRequest();");
				tl(2, "siteRequest.initDeepSiteRequest();");
				tl(2, "SiteContext siteContext = new SiteContext();");
				tl(2, "siteContext.initDeepSiteContext();");
				tl(2, "siteContext.setSiteRequest_(siteRequest);");
				tl(2, "siteRequest.setSiteContext_(siteContext);");
				tl(2, "siteRequest.setSiteConfig_(siteContext.getSiteConfig());");
				tl(2, "initDeep", classSimpleName, "(siteContext.getSiteRequest_());");
				tl(2, "SolrClient solrClient = siteContext.getSolrClient();");
				tl(2, "solrClient.deleteById(", classVarPrimaryKey, ".toString());");
				tl(2, "solrClient.commit();");
				tl(1, "}");
			}
		}

		/////////////////
		// codeObtain //
		/////////////////
		o = codeObtain;
		if(classInitDeep && (classExtendsBase || classIsBase)) {
			tl(3, "default:");

			if(classIsBase)
				tl(4, "return null;");
			else
				tl(4, "return super.obtain", classSuperSimpleNameGeneric, "(var);");

			tl(2, "}");
			tl(1, "}");
		}	

		///////////////////
		// codeAttribute //
		///////////////////
		o = codeAttribute;
		if(classInitDeep && (classExtendsBase || classIsBase)) {
			tl(3, "default:");

			if(classIsBase)
				tl(4, "return null;");
			else
				tl(4, "return super.attribute", classSuperSimpleNameGeneric, "(var, val);");

			tl(2, "}");
			tl(1, "}");

		}	

		/////////////
		// codePut //
		/////////////
		o = codePut;
		if(classSaved) {
//		if(classInitDeep && (classExtendsBase || classIsBase)) {
			tl(3, "default:");

			if(classIsBase)
				tl(4, "return false;");
			else
				tl(4, "return super.put", classSuperSimpleNameGeneric, "(requestJson, var);");

			tl(2, "}");
			tl(1, "}");
		}	

		/////////////////
		// codePopulate //
		/////////////////
		o = codePopulate;
		if(classSaved) {
//						t(2, "}");

			if(!classSimpleName.equals("Cluster")) {
				tl(0);
				tl(2, "super.populate", classSuperSimpleNameGeneric, "(solrDocument);");
			}

			tl(1, "}");
		}	

		/////////////////////
		// codeSave //
		/////////////////////
		o = codeSave;
		if(classSaved) {
			if(!classSimpleName.equals("Cluster")) {
				tl(0);
				tl(2, "super.save", classSuperSimpleNameGeneric + "(siteRequest, sqlInsertP, sqlInsertA, sqlDeleteP, sqlDeleteA, gestionnaireListe, coureur);");
			}
			tl(1, "}");
		}	

		codeInitDeep.flush();
		codeSiteRequest.flush();
		codeIndex.flush();
		codeObtain.flush();
		codeAttribute.flush();
		codePut.flush();
		codePopulate.flush();
		codeExists.flush();
		codeSaves.flush();
		codeSave.flush();
		codeApiEntities.flush();
		codeApiGet.flush();
		codeApiGenerateGet.flush();
		codePageEntities.flush();
		codePageGet.flush();

		o = writerGenClass;

		s(wInitDeep.toString());
		s(wSiteRequest.toString());
		s(wIndex.toString());
		s(wObtain.toString());
		s(wAttribute.toString());
//		s(wDefine.toString());
//		s(wPopulate.toString());
//		s(wExists.toString());
//		s(wSaves.toString());
//		s(wSave.toString());

		l("}"); 

		System.out.println("Ecrire: " + classPathGen); 
		writerGenClass.flush();
		writerGenClass.close();
	}
}
