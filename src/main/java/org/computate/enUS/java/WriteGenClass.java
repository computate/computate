package org.computate.enUS.java;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.translate.AggregateTranslator;
import org.apache.commons.text.translate.CharSequenceTranslator;
import org.apache.commons.text.translate.EntityArrays;
import org.apache.commons.text.translate.LookupTranslator;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;

/**	
 *	For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 **/
public class WriteGenClass extends WriteClass {

	public static final String[] HTML_ELEMENTS = new String[] { "div", "span", "a", "ul", "ol", "li", "p", "h1", "h2", "h3", "h4", "h5", "h6", "i", "table", "tbody", "thead", "tr", "td", "th", "pre", "code", "br", "dd", "dt" };

	protected String classDirPathGen;

	protected String classPathGen;

	protected String classPathApiPackageInfo;

	protected String classPathGenApiServiceImpl;

	protected String classPathApiServiceImpl;

	protected String classPathGenApiService;

	protected File classDirGen;

	protected File classFileGen;

	protected File classFileApiPackageInfo;

	protected File classFileGenApiServiceImpl;

	protected File classFileApiServiceImpl;

	protected File classFileGenApiService;

	protected StringBuilder s = new StringBuilder();

	protected SolrDocument classDoc;

	protected SolrDocument doc;

	protected String classCanonicalName;

	protected String classSimpleNameGen;

	protected String classSuperSimpleName;

	protected String classSuperSimpleNameGeneric;

	protected String classSuperCanonicalNameGeneric;

	protected String classPackageName;

	protected String classSimpleName;

	protected String classSuperCanonicalName;

	protected String classApiUri;

	protected String classComment;

	protected String classVarPrimaryKey;

	protected String classVarUniqueKey;

	protected List<String> classImportsGen;

	protected List<String> classInitDeepExceptions;

	protected List<String> classImportsGenApi;

	protected List<String> classImportsGenPage;

	protected List<String> classParameterTypeNames;

	protected List<String> classSuperParameterTypeNames;

	protected List<String> entitySuperClassesAndMeWithoutGen;

	protected List<String> classSuperWriteMethods;

	protected List<String> classWriteMethods;

	protected List<AllWriter> classWriteWriters;

	protected Boolean classExtendsGen;

	protected Boolean classBaseExtendsGen;

	protected Boolean classInitDeep;

	protected Boolean classContainsSiteRequest;

	protected Boolean classIndexed;

	protected Boolean classImage;

	protected Boolean classExtendsBase;

	protected Boolean classIsBase;

	protected Boolean classSaved;

	protected Boolean classModel;

	protected Boolean classApi;

	protected Boolean classPage;

	protected Boolean classRolesFound;

	protected List<String> classRoles;

	protected Boolean classFiltersFound;

	protected List<String> classFilters;

	protected List<String> classApiMethods;

	protected List<String> classEntityVars;

	protected List<String> classMethodVars;

	protected AllWriter wInitDeep;

	protected AllWriter wSiteRequest;

	protected AllWriter wIndex;

	protected AllWriter wText;

	protected AllWriter wObtain;

	protected AllWriter wAttribute;

	protected AllWriter wPut;

	protected AllWriter wPopulate;

	protected AllWriter wStore;

	protected AllWriter wExists;

	protected AllWriter wSaves;

	protected AllWriter wDefine;

	protected AllWriter wApiGet;

	protected AllWriter wApiGenerateGet;

	protected AllWriter wApiGeneratePost;

	protected AllWriter wApiGeneratePut;

	protected AllWriter wApiGeneratePatch;

	protected AllWriter wPageHtmlSingular;

	protected AllWriter wApiEntities;

	protected AllWriter wPageEntities;

	protected AllWriter wPageGet;

	protected AllWriter wHashCode;

	protected AllWriter wToString;

	protected AllWriter wEquals;

	protected String entityVar;

	protected String entityTypeSuffix;

	protected String entityVarCapitalized;

	Boolean entityAttribute;

	String entityAttributeSimpleName;

	String entityAttributeVar;

	String entityAttributeVarTitle;

	String entityAttributeVarDescription;

	String entityAttributeVarImageUrl;

	String entityAttributeVarSuggest;

	Boolean entityDefine;

	protected String entityCanonicalName;

	protected String entityCanonicalNameGeneric;

	protected String entitySimpleNameComplete;

	protected String entitySimpleNameCompleteGeneric;

	protected String entitySimpleName;

	protected String entitySimpleNameGeneric;

	protected String entityComment;

	protected String entityVarParam;

	protected Boolean entityWrap;

	protected Boolean entityInitialized;

	protected Boolean entityInitDeep;

	protected AllWriter writerGenClass;

	protected Integer entityIndex;

	protected String contextAName;

	protected String contextThis;

	protected String contextThisName;

	protected String contextA;

	protected String contextTheName;

	protected String contextNameSingular;

	protected String contextNamePlural;

	protected String contextActualName;

	protected String contextAll;

	protected String contextAllName;

	protected String contextSearchAllNameBy;

	protected String contextSearchAllName;

	protected String contextH1;

	protected String contextH2;

	protected String contextH3;

	protected String contextTitle;

	protected String contextTheNames;

	protected String contextNoNameFound;

	protected String contextNameVar;

	protected String contextOfName;

	protected String contextAdjective;

	protected String contextAdjectivePlural;

	protected String contextAdjectiveVar;

	protected String contextANameAdjective;

	protected String contextNameAdjectiveSingular;

	protected String contextNameAdjectivePlural;

	protected String contextColor;

	protected String contextIconGroup;

	protected String contextIconName;

	protected String contextDescription;

	protected Integer contextImageWidth;

	protected Integer contextImageHeight;

	protected String contextVideoId;

	protected Boolean classContext;

	Boolean entiteHighlighting;

	Boolean entityHtml;

	Boolean entityModify;

	String entityJsonType;

	String entityAttributeJsonType;

	String entityOperationIdPATCH;

	String entityAttributeOperationIdPATCH;

	String entityAttributeOperationIdSearch;

	Boolean entityMultiline;

	String entityDisplayName;

	String entityDescription;

	Integer entityHtmlRow;

	Boolean entityIndexed;

	Boolean entityText;

	String entityLanguage;

	Boolean entitySuggested;

	Boolean entityStored;

	String entitySolrCanonicalName;

	String entitySolrSimpleName;

	String entitySimpleNameVertxJson;

	String entityCanonicalNameVertxJson;

	String entityListSimpleNameVertxJson;

	String entityListCanonicalNameVertxJson;

	SolrDocument entitySolrDocument;

	Integer searchRows;

	Integer searchRowSearch;

	Integer searchRowActualSearch;

	Integer searchRowPOST;

	Integer searchRowActualPOST;

	Integer searchRowPATCH;

	Integer searchRowActualPATCH;

	Integer searchRowPage;

	Integer searchRowActualPage;

	AllWriter classVals;

	protected Stack<String> entityXmlStack = new Stack<String>();

	protected Stack<Integer> entityNumberStack = new Stack<Integer>();

	protected Stack<String> methodXmlStack = new Stack<String>();

	protected Stack<Integer> entityNumberStack = new Stack<Integer>();

	public void  genCodeInit() throws Exception, Exception {

		wInitDeep = AllWriter.create();
		wSiteRequest = AllWriter.create();
		wIndex = AllWriter.create();
		wText = AllWriter.create();
		wObtain = AllWriter.create();
		wAttribute = AllWriter.create();
		wPut = AllWriter.create();
		wPopulate = AllWriter.create();
		wStore = AllWriter.create();
		wSaves = AllWriter.create();
		wExists = AllWriter.create();
		wDefine = AllWriter.create();
		wApiEntities = AllWriter.create();
		wPageEntities = AllWriter.create();
		wApiGet = AllWriter.create();
		wApiGenerateGet = AllWriter.create();
		wApiGeneratePost = AllWriter.create();
		wApiGeneratePut = AllWriter.create();
		wApiGeneratePatch = AllWriter.create();
		wPageHtmlSingular = AllWriter.create();
		wPageGet = AllWriter.create();
		wHashCode = AllWriter.create();
		wToString = AllWriter.create();
		wEquals = AllWriter.create();
	}

	public void  genCodeInitDeep(String languageName) throws Exception, Exception {
		if(BooleanUtils.isTrue(classInitDeep) && classPartsSiteRequest != null) {
			wInitDeep.l(); 
			wInitDeep.tl(1, "//////////////");
			wInitDeep.tl(1, "// ", str_initDeep(languageName), " //");
			wInitDeep.tl(1, "//////////////");
			wInitDeep.l(); 
			wInitDeep.tl(1, "protected boolean ", str_alreadyInitialized(languageName), classSimpleName, " = false;");
			wInitDeep.l();
			wInitDeep.t(1, "public ", classSimpleName, " ", str_initDeep(languageName), classSimpleName, "(", classPartsSiteRequest.simpleName(languageName), " ", str_siteRequest(languageName), "_)");
			if(classInitDeepExceptions.size() > 0) {
				wInitDeep.s(" throws ");
				for(int i = 0; i < classInitDeepExceptions.size(); i++) {
String classInitDeepException = classInitDeepExceptions.get(i);
					String classInitDeepExceptionSimpleName = StringUtils.substringAfterLast(classInitDeepException, ".");
					if(i > 0)
						wInitDeep.s(", ");
					wInitDeep.s(classInitDeepExceptionSimpleName);
				}
			}
			wInitDeep.l(" {");
//						if(contient", classPartsSiteRequest.simpleName(languageName), " && !StringUtils.equals(classSimpleName, "", classPartsSiteRequest.simpleName(languageName), ""))
//							tl(2, "((", classSimpleName, ")this).setSiteRequest_(siteRequest);");
			if(classeContientSiteRequest)
				wInitDeep.tl(2, "set", str_SiteRequest(languageName), "_(", str_siteRequest(languageName), "_);");
			wInitDeep.tl(2, "if(!", str_alreadyInitialized(languageName), classSimpleName, ") {");
			wInitDeep.tl(3, str_alreadyInitialized(languageName), classSimpleName, " = true;");
			wInitDeep.tl(3, str_initDeep(languageName), classSimpleName, "();");
			wInitDeep.tl(2, "}");
			wInitDeep.tl(2, "return (", classSimpleName, ")this;");
			wInitDeep.tl(1, "}");
			wInitDeep.l();
			wInitDeep.t(1, "public void ", str_initDeep(languageName), classSimpleName, "()");
			if(classInitDeepExceptions.size() > 0) {
				wInitDeep.s(" throws ");
				for(int i = 0; i < classInitDeepExceptions.size(); i++) {
					String classInitDeepException = classInitDeepExceptions.get(i);
					String classInitDeepExceptionSimpleName = StringUtils.substringAfterLast(classInitDeepException, ".");
					if(i > 0)
						wInitDeep.s(", ");
					wInitDeep.s(classInitDeepExceptionSimpleName);
				}
			}
			wInitDeep.l(" {");
			if(BooleanUtils.isTrue(classExtendsBase)) 
				wInitDeep.tl(2, "super.", str_initDeep(languageName), classSuperSimpleNameGeneric, "(", str_siteRequest(languageName), "_);");
			wInitDeep.tl(2, "init", classSimpleName, "();");
			wInitDeep.tl(1, "}");
			wInitDeep.l();
			wInitDeep.t(1, "public void init", classSimpleName, "()");
			if(classInitDeepExceptions.size() > 0) {
				wInitDeep.s(" throws ");
				for(int i = 0; i < classInitDeepExceptions.size(); i++) {
					String classInitDeepException = classInitDeepExceptions.get(i);
					String classInitDeepExceptionSimpleName = StringUtils.substringAfterLast(classInitDeepException, ".");
					if(i > 0)
						wInitDeep.s(", ");
					wInitDeep.s(classInitDeepExceptionSimpleName);
				}
			}
			wInitDeep.l(" {");
		}
	}

	public void  genCodeSiteRequest(String languageName) throws Exception, Exception {
		if(BooleanUtils.isTrue(classeContientSiteRequest) && classPartsSiteRequest != null) {
			o = wSiteRequest;
			l(); 
			tl(1, "/////////////////");
			tl(1, "// ", str_siteRequest(languageName), " //");
			tl(1, "/////////////////");
			l(); 
			tl(1, "public void ", str_siteRequest(languageName), classSimpleName, "(", classPartsSiteRequest.simpleName(languageName), " ", str_siteRequest(languageName), "_) {");
			if(BooleanUtils.isTrue(classExtendsBase)) 
				tl(3, "super.", str_siteRequest(languageName), classSuperSimpleNameGeneric, "(", str_siteRequest(languageName), "_);");
		}
	}

	public void  genCodeObtain(String languageName) throws Exception, Exception {
		o = wObtain;
		if(classInitDeep && (classExtendsBase || classIsBase)) {
			l(); 
			tl(1, "/////////////");
			tl(1, "// ", str_obtain(langueNom), " //");
			tl(1, "/////////////");
			tl(0);
			t(1);
			if(!classIsBase)
				s("@Override ");
			l("public Object ", str_obtain(langueNom), str_PourClasse(langueNom), "(String var) {");
			tl(2, "String[] vars = StringUtils.split(var, \".\");");
			tl(2, "Object o = null;");
			tl(2, "for(String v : vars) {");
			tl(3, "if(o == null)");
			tl(4, "o = ", str_obtain(langueNom), classSimpleName, "(v);");
			tl(3, "else if(o instanceof Cluster) {");
			tl(4, "Cluster cluster = (Cluster)o;");
			tl(4, "o = cluster.", str_obtain(langueNom), str_PourClasse(langueNom), "(v);");
			tl(3, "}");
			tl(2, "}");
			tl(2, "return o;");
			tl(1, "}");
			tl(1, "public Object ", str_obtain(langueNom), classSimpleName, "(String var) {");
			tl(2, classSimpleName, " o", classSimpleName, " = (", classSimpleName, ")this;");
			tl(2, "switch(var) {");
		}
	}

	public void  genCodeAttribute(String languageName) throws Exception, Exception {
		o = wAttribute;
		if(classInitDeep && (classExtendsBase || classIsBase)) {
			l(); 
			tl(1, "///////////////");
			tl(1, "// ", str_attribute(langueNom), " //");
			tl(1, "///////////////");
			tl(0);
			t(1);
			if(!classIsBase)
				s("@Override ");
			s("public boolean ", str_attribute(langueNom), str_PourClasse(langueNom), "(String var, Object val)");
			if(classInitDeepExceptions.size() > 0) {
				s(" throws ");
				for(int i = 0; i < classInitDeepExceptions.size(); i++) {
					String classInitDeepException = classInitDeepExceptions.get(i);
					String classInitDeepExceptionNomSimple = StringUtils.substringAfterLast(classInitDeepException, ".");
					if(i > 0)
						s(", ");
					s(classInitDeepExceptionNomSimple);
				}
			}
			l(" {");
			tl(2, "String[] vars = StringUtils.split(var, \".\");");
			tl(2, "Object o = null;");
			tl(2, "for(String v : vars) {");
			tl(3, "if(o == null)");
			tl(4, "o = ", str_attribute(langueNom), classSimpleName + "(v, val);");
			tl(3, "else if(o instanceof Cluster) {");
			tl(4, "Cluster cluster = (Cluster)o;");
			tl(4, "o = cluster.", str_attribute(langueNom), str_PourClasse(langueNom), "(v, val);");
			tl(3, "}");
			tl(2, "}");
			tl(2, "return o != null;");
			tl(1, "}");
			tl(1, "public Object ", str_attribute(langueNom), classSimpleName, "(String var, Object val) {");
			tl(2, classSimpleName, " o", classSimpleName, " = (", classSimpleName, ")this;");
			tl(2, "switch(var) {");

		}
	}

	public void  genCodePut(String languageName) throws Exception, Exception {
		o = wPut;
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
			l("public void ", str_put(langueNom), str_PourClasse(langueNom), "(JsonObject requeteJson) {");
			tl(2, "Set<String> vars = requeteJson.fieldNames();");
			tl(2, "for(String var : vars) {");
			tl(3, "put", classSimpleName + "(requeteJson, var);");
			tl(2, "}");
			tl(1, "}");
			l();
			t(1);
			if(!classIsBase)
				s("@Override ");
			l("public Boolean ", str_put(langueNom), classSimpleName, "(JsonObject requeteJson, String var) {");
			tl(2, "switch(var) {");
		}
	}

	public void  genCodePopulate(String languageName) throws Exception, Exception {
		o = wPopulate;
		if(classSaved) {
		}
	}

	public void  genCodeExists(String languageName) throws Exception, Exception {
		o = wExists;
		if(classSaved) {
//			l(); 
//			tl(1, "////////////");
//			tl(1, "// exists //");
//			tl(1, "////////////");
//			tl(0);
//			t(1);
//			if(!classSimpleName.equals("Cluster"))
//				s("@Override ");
//			l("public Boolean existsForClass() {");
//			tl(2, "String pkStr = requeteSite_.getServerRequest().getParam(\"pk\");");
//			tl(2, "Long pk = ", StringUtils.class.getCanonicalName(), ".isNumeric(pkStr) ? Long.parseLong(pkStr) : null;");
//			tl(2, "Boolean exists = existsForClass(pk);");
//			tl(2, "return exists;");
//			tl(1, "}");
//			t(1);
//			if(!classSimpleName.equals("Cluster"))
//				s("@Override ");
//			l("public Boolean existsForClass(Long pk) {");
//			tl(2, QueryRunner.class.getCanonicalName(), " coureur = new ", QueryRunner.class.getCanonicalName(), "(requeteSite_.", classePartsSiteContexte.nomSimple(langueNom), ".sourceDonnees);");
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
//			tl(5, "connectionSql.queryWithParams(", classePartsSiteContexte.nomSimple(langueNom), ".SQL_exists, new JsonArray().add(pk)), chercher -> {");
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

	public static final String escapeJava(String input) {
        return org.computate.frFR.java.EcrireGenClasse.ESCAPE_JAVA.translate(input);
    }

	public void  genCodeSaves(String languageName) throws Exception, Exception {
		o = wSaves;
		if(classSaved) {
			l(); 
			tl(1, "/////////////////");
			tl(1, "// ", str_saves(langueNom), " //");
			tl(1, "/////////////////");
			tl(0);
			tl(1, "protected List<String> ", str_saves(langueNom), "", classSimpleName, " = new ArrayList<String>();");
//			t(1);
//			if(!classSimpleName.equals("Cluster"))
//				s("@Override ");
//			l("public void ", str_saves(langueNom), "PourClasse(", classePartsRequeteSite.nomSimple(langueNom), " requeteSite) {");
//			tl(2, QueryRunner.class.getCanonicalName(), " coureur = new ", QueryRunner.class.getCanonicalName(), "(requeteSite.", classePartsSiteContexte.nomSimple(langueNom), ".sourceDonnees);");
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
//			tl(4, "defineForClass(chemin, valeur);");
//			tl(4, "", str_saves(langueNom), "", classSimpleName, ".add(chemin);");
//			tl(3, "}");
//			tl(2, "}");
//			tl(1, "}");
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
			String classValVarOld = null;
			Integer classValVarNumber = 0;
			String classValVar = null;
			String classValLanguage = null;
			String classValVarLanguage = null;
			String classValVarLanguageOld = null;
			String classValValue = null;

			for(int j = 0; j < classValsVar.size(); j++) {
				classValVar = classValsVar.get(j);
				classValLanguage = classValsLanguage.get(j);
				classValVarLanguage = classValVar + classValLanguage;
				classValValue = classValsValue.get(j);

				if(!StringUtils.equals(classValVarLanguage, classValVarLanguageOld) && (StringUtils.equals(classValVarLanguageOld, classValVarOld + languageName))) {
					t(1, "public static final String ", classValVarOld, " = ");
					for(int k = 1; k <= classValVarNumber; k++) {
						if(k > 1)
							s(" + ");
						s(classValVarOld, k);
					}
					l(";");
					classValVarNumber = 0;
				}

				if(StringUtils.equals(languageName, classValLanguage)) {
					classValVarNumber++;
					tl(1, "public static final String ", classValVar, classValVarNumber, " = \"", escapeJava(classValValue), "\";");
					if(!classVals.getEmpty())
						classVals.s(", ");
					classVals.s(classValVar, classValVarNumber);
				}

				classValVarOld = classValVar;
				classValVarLanguageOld = classValVarLanguage;
			}
			if(StringUtils.equals(languageName, classValLanguage)) {
				classValVarOld = classValVar;
				classValVarLanguageOld = classValVarLanguage;
				classValVar = null;
	
				if(classValVarOld != null && !StringUtils.equals(classValVar, classValVarLanguageOld)) {
					t(1, "public static final String ", classValVarOld, " = ");
					for(int k = 1; k <= classValVarNumber; k++) {
						if(k > 1)
							s(" + ");
						s(classValVarOld, k);
					}
					l(";");
					classValVarNumber = 0;
				}
			}
		}
		
		//////////////
		// Contexte //
		//////////////
		
		if(classContext) {

			contextColor = (String)classDoc.get("contextColor_stored_string");
			contextIconGroup = (String)classDoc.get("contextIconGroup_stored_string");
			contextIconName = (String)classDoc.get("contextIconName_stored_string");

			contextVideoId = (String)classDoc.get("contextVideoId" + "_" + languageName + "_stored_string");
			contextAName = (String)classDoc.get("contextAName" + "_" + languageName + "_stored_string");
			contextNameSingular = (String)classDoc.get("contextNameSingular" + "_" + languageName + "_stored_string");
			contextNamePlural = (String)classDoc.get("contextNamePlural" + "_" + languageName + "_stored_string");
			contextNameVar = (String)classDoc.get("contextNameVar" + "_" + languageName + "_stored_string");
			contextAdjective = (String)classDoc.get("contextAdjective" + "_" + languageName + "_stored_string");
			contextAdjectivePlural = (String)classDoc.get("contextAdjectivePlural" + "_" + languageName + "_stored_string");
			contextAdjectiveVar = (String)classDoc.get("contextAdjectiveVar" + "_" + languageName + "_stored_string");
			contextNameAdjectiveSingular = (String)classDoc.get("contextNameAdjectiveSingular" + "_" + languageName + "_stored_string");
			contextNameAdjectivePlural = (String)classDoc.get("contextNameAdjectivePlural" + "_" + languageName + "_stored_string");
			contextThis = (String)classDoc.get("contextThis" + "_" + languageName + "_stored_string");
			contextA = (String)classDoc.get("contextA" + "_" + languageName + "_stored_string");
			contextActualName = (String)classDoc.get("contextActualName" + "_" + languageName + "_stored_string");
			contextAllName = (String)classDoc.get("contextAllName" + "_" + languageName + "_stored_string");
			contextSearchAllNameBy = (String)classDoc.get("contextSearchAllNameBy" + "_" + languageName + "_stored_string");
			contextSearchAllName = (String)classDoc.get("contextSearchAllName" + "_" + languageName + "_stored_string");
			contextTheNames = (String)classDoc.get("contextTheNames" + "_" + languageName + "_stored_string");
			contextTitle = (String)classDoc.get("contextTitle" + "_" + languageName + "_stored_string");
			contextH1 = (String)classDoc.get("contextH1" + "_" + languageName + "_stored_string");
			contextH2 = (String)classDoc.get("contextH2" + "_" + languageName + "_stored_string");
			contextH3 = (String)classDoc.get("contextH3" + "_" + languageName + "_stored_string");
			contextNoNameFound = (String)classDoc.get("contextNoNameFound" + "_" + languageName + "_stored_string");
			contextANameAdjective = (String)classDoc.get("contextANameAdjective" + "_" + languageName + "_stored_string");
			contextThisName = (String)classDoc.get("contextThisName" + "_" + languageName + "_stored_string");
			contextTheName = (String)classDoc.get("contextTheName" + "_" + languageName + "_stored_string");
			contextOfName = (String)classDoc.get("contextOfName" + "_" + languageName + "_stored_string");
			
			l();
			if(contextAName != null)
				tl(1, "public static final String ", classSimpleName, "_AName = ", q(contextAName), ";");
			
			if(contextThis != null)
				tl(1, "public static final String ", classSimpleName, "_This = ", q(contextThis), ";");
			
			if(contextThisName != null)
				tl(1, "public static final String ", classSimpleName, "_ThisName = ", q(contextThisName), ";");
			
			if(contextA != null)
				tl(1, "public static final String ", classSimpleName, "_A = ", q(contextA), ";");
			
			if(contextTheName != null)
				tl(1, "public static final String ", classSimpleName, "_TheName = ", q(contextTheName), ";");
			
			if(contextNameSingular != null)
				tl(1, "public static final String ", classSimpleName, "_NameSingular = ", q(contextNameSingular), ";");
			
			if(contextNamePlural != null)
				tl(1, "public static final String ", classSimpleName, "_NamePlural = ", q(contextNamePlural), ";");
			
			if(contextActualName != null)
				tl(1, "public static final String ", classSimpleName, "_NameActual = ", q(contextActualName), ";");
			
			if(contextAll != null)
				tl(1, "public static final String ", classSimpleName, "_All = ", q(contextAll), ";");
			
			if(contextAllName != null)
				tl(1, "public static final String ", classSimpleName, "_AllName = ", q(contextAllName), ";");
			
			if(contextSearchAllNameBy != null)
				tl(1, "public static final String ", classSimpleName, "_SearchAllNameBy = ", q(contextSearchAllNameBy), ";");
			
			if(contextSearchAllName != null)
				tl(1, "public static final String ", classSimpleName, "_SearchAllName = ", q(contextSearchAllName), ";");
			
			if(contextH1 != null)
				tl(1, "public static final String ", classSimpleName, "_H1 = ", q(contextH1), ";");
			
			if(contextH2 != null)
				tl(1, "public static final String ", classSimpleName, "_H2 = ", q(contextH2), ";");
			
			if(contextH3 != null)
				tl(1, "public static final String ", classSimpleName, "_H3 = ", q(contextH3), ";");
			
			if(contextTitle != null)
				tl(1, "public static final String ", classSimpleName, "_Title = ", q(contextTitle), ";");
			
			if(contextTheNames != null)
				tl(1, "public static final String ", classSimpleName, "_TheNames = ", q(contextTheNames), ";");
			
			if(contextNoNameFound != null)
				tl(1, "public static final String ", classSimpleName, "_NoNameFound = ", q(contextNoNameFound), ";");
			
			if(contextNameVar != null)
				tl(1, "public static final String ", classSimpleName, "_NameVar = ", q(contextNameVar), ";");
			
			if(contextOfName != null)
				tl(1, "public static final String ", classSimpleName, "_OfName = ", q(contextOfName), ";");
			
			if(contextAdjective != null)
				tl(1, "public static final String ", classSimpleName, "_Adjective = ", q(contextAdjective), ";");
			
			if(contextAdjectivePlural != null)
				tl(1, "public static final String ", classSimpleName, "_AdjectivePlural = ", q(contextAdjectivePlural), ";");
			
			if(contextAdjectiveVar != null)
				tl(1, "public static final String ", classSimpleName, "_AdjectiveVar = ", q(contextAdjectiveVar), ";");
			
			if(contextANameAdjective != null)
				tl(1, "public static final String ", classSimpleName, "_ANameAdjective = ", q(contextANameAdjective), ";");
			
			if(contextNameAdjectiveSingular != null)
				tl(1, "public static final String ", classSimpleName, "_NameAdjectiveSingular = ", q(contextNameAdjectiveSingular), ";");
			
			if(contextNameAdjectivePlural != null)
				tl(1, "public static final String ", classSimpleName, "_NameAdjectivePlural = ", q(contextNameAdjectivePlural), ";");
			
			if(contextColor != null)
				tl(1, "public static final String ", classSimpleName, "_Color = ", q(contextColor), ";");
			
			if(contextIconGroup != null)
				tl(1, "public static final String ", classSimpleName, "_IconGroup = ", q(contextIconGroup), ";");
			
			if(contextIconName != null)
				tl(1, "public static final String ", classSimpleName, "_IconName = ", q(contextIconName), ";");
		}

		for(String classPageMethod : classApiMethods) {

			String classPageUriMethod = (String)classDoc.get("classeApiUri" + classPageMethod + "_stored_string");
			String classPageSimpleName = (String)doc.get("classPageSimpleName" + classPageMethod  + "_stored_string");
			String classePageCheminGen = (String)classDoc.get("classePageCheminGen" + classPageMethod  + "_stored_string");
	
			if(classPageSimpleName != null) {
				if(classPageUriMethod != null) {
					tl(1, "public static final String ", classPageSimpleName, "_Uri", " = ", q(classPageUriMethod), ";");
				}
				if(classePageCheminGen != null) {
					tl(1, "public static final String ", classPageSimpleName, "_ImageUri", " = ", q("/png", classPageUriMethod, "-999.png"), ";");
				}
			}
		}
	}

	public void  genCodeConstructor(String languageName) throws Exception, Exception {
		String constructorSourceCode = (String)doc.get("constructorSourceCode_" + languageName + "_stored_string");
		String constructorComment = (String)doc.get("constructorComment_" + languageName + "_stored_string");
		List<String> constructorExceptionsSimpleNameComplete = (List<String>)doc.get("constructorExceptionsSimpleNameComplete_stored_strings");
		List<String> constructorAnnotationsSimpleNameCompleteList = (List<String>)doc.get("constructorAnnotationsSimpleNameComplete_" + languageName + "_stored_strings");
		List<String> constructorAnnotationsCodeBlockList = (List<String>)doc.get("constructorAnnotationsCodeBlock_" + languageName + "_stored_strings");

		l(""); 
		writeComment(constructorComment, 1);
		if(constructorAnnotationsSimpleNameCompleteList != null && constructorAnnotationsCodeBlockList != null) {
			for(int j = 0; j < constructorAnnotationsSimpleNameCompleteList.size(); j++) {
				String constructorAnnotationNomSimpleComplet = constructorAnnotationsSimpleNameCompleteList.get(j);
				String constructorAnnotationCodeBlock = constructorAnnotationsCodeBlockList.get(j);
				l("\t@", constructorAnnotationNomSimpleComplet, constructorAnnotationCodeBlock, "");
			}
		}
		s("\t");
		if(BooleanUtils.isTrue((Boolean)doc.get("constructorIsPublic_stored_boolean")))
			s("public ");
		if(BooleanUtils.isTrue((Boolean)doc.get("constructorIsProtected_stored_boolean")))
			s("protected ");
		if(BooleanUtils.isTrue((Boolean)doc.get("constructorIsPrivate_stored_boolean")))
			s("private ");
		if(BooleanUtils.isTrue((Boolean)doc.get("constructorIsStatic_stored_boolean")))
			s("static ");
		if(BooleanUtils.isTrue((Boolean)doc.get("constructorIsFinal_stored_boolean")))
			s("final ");
		if(BooleanUtils.isTrue((Boolean)doc.get("constructorIsAbstract_stored_boolean")))
			s("abstract ");
		if(BooleanUtils.isTrue((Boolean)doc.get("constructorIsNative_stored_boolean")))
			s("native ");

		s(classSimpleNameGen);
		s("(");
		List<String> constructorParamsSimpleNameComplete = (List<String>)doc.get("constructorParamsSimpleNameComplete_" + languageName + "_stored_strings"); 
		List<String> constructorParamsVar = (List<String>)doc.get("constructorParamsVar_" + languageName + "_stored_strings");
		List<Boolean> constructorParamsVariableArgs = (List<Boolean>)doc.get("constructorParamsVariableArgs_stored_booleans");
		if(constructorParamsSimpleNameComplete != null && constructorParamsVar != null && constructorParamsSimpleNameComplete.size() == constructorParamsVar.size()) {
			for(int j = 0; j < constructorParamsVar.size(); j++) {
				String constructorParamSimpleNameComplete = constructorParamsSimpleNameComplete.get(j);
				String constructorParamVar = constructorParamsVar.get(j);
				Boolean constructorParamVariableArgs = constructorParamsVariableArgs.get(j);
				if(j > 0)
					s(", ");
				s(constructorParamSimpleNameComplete);

				if(constructorParamVariableArgs)
					s("...");
				else
					s(" ");

				s(constructorParamVar);
			}
		}    
		s(")");
		if(constructorExceptionsSimpleNameComplete != null && constructorExceptionsSimpleNameComplete.size() > 0) {
			s(" throws ");
			for(int j = 0; j < constructorExceptionsSimpleNameComplete.size(); j++) {
				String constructorExceptionSimpleNameComplete = constructorExceptionsSimpleNameComplete.get(j);
				if(j > 0)
					s(", ");
				s(constructorExceptionSimpleNameComplete);
			}
		}
		s(" {");
		s(constructorSourceCode);
		l("}");
	}

	public void  genCodeEntity(String languageName) throws Exception, Exception {

		String methodVar = (String)doc.get("methodVar_" + langueNom + "_stored_string");

		ToutEcrivain methodValsWriter = ToutEcrivain.create();
		List<String> methodValsVar = (List<String>)doc.get("methodValsVar_stored_strings");
		List<String> methodValsLanguage = (List<String>)doc.get("methodValsLanguage_stored_strings");
		List<String> methodeValsCode = (List<String>)doc.get("methodeValsCode_stored_strings");
		List<String> methodValsValue = (List<String>)doc.get("methodValsValue_stored_strings");
		if(methodValsVar != null && methodValsLanguage != null && methodValsValue != null) {
			String methodValVarOld = null;
			Integer methodValVarNumber = 0;
			String methodValVar = null;
			String methodValLanguage = null;
			String methodValVarLanguage = null;
			String methodValVarLanguageOld = null;
			String methodeValCode = null;
			String methodValValue = null;

			methodXmlStack = new Stack<String>();
			methodNumberStack = new Stack<Integer>();
			for(int j = 0; j < methodValsVar.size(); j++) {
				methodValVar = methodValsVar.get(j);
				methodValLanguage = methodValsLanguage.get(j);
				if(StringUtils.isBlank(methodValLanguage))
					methodValLanguage = langueNom;
				methodValVarLanguage = methodValVar + methodValLanguage;
				methodeValCode = methodeValsCode.get(j);
				methodValValue = methodValsValue.get(j);

				Integer xmlPart = 0;
				if(!StringUtils.equals(methodValVarLanguage, methodValVarLanguageOld) && (StringUtils.equals(methodValVarLanguageOld, methodValVarOld + langueNom))) {
					t(1, "public static final String ", methodVar, methodValVarOld, " = ");
					for(int k = 1; k <= methodValVarNumber; k++) {
						if(k > 1)
							s(" + ");
						s(methodVar, methodValVarOld, k);
					}
					l(";");
					methodValVarNumber = 0;
				}

				if(StringUtils.equals(langueNom, methodValLanguage)) {
					methodValVarNumber++;
					tl(1, "public static final String ", methodVar, methodValVar, methodValVarNumber, " = \"", escapeJava(methodValValue), "\";");
					if(!classeVals.getEmpty())
						classeVals.s(", ");
					classeVals.s(methodVar, methodValVar, methodValVarNumber);
					{
						String[] parts = splitByCharacterTypeCamelCase(methodValVar);
						Boolean html = false;
						for(Integer p = 0; p < parts.length; p++) {
							String part = StringUtils.uncapitalize(parts[p]);

							Matcher regex = Pattern.compile("^(\\w+?)(\\d*)$").matcher(part);
							boolean trouve = regex.find();
							if(trouve) {
								String element = StringUtils.lowerCase(regex.group(1));
								String numeroStr = regex.group(2);
								Integer numero = StringUtils.isEmpty(numeroStr) ? null : Integer.parseInt(numeroStr);
								if("h".equals(element)) {
									element += numero;
									numero = null;
								}

//									methodValsWriter.t(1);
								if(StringUtils.equalsAny(element, HTML_ELEMENTS)) {
									html = true;

									String css = methodVar;
									for(Integer r = 0; r <= xmlPart; r++) {
										String s = parts[r];
										css += s;
									}
									css += " ";

									String cssNumero = numero == null ? "" : (StringUtils.substringBeforeLast(StringUtils.substringBeforeLast(css, numero.toString()), "0") + (numero % 2 == 0 ? " even " : " odd "));

									if(numero == null)
										numero = 1;

									if(methodXmlStack.size() < (xmlPart + 1)) {
										if("i".equals(element))
											methodValsWriter.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", ", methodVar, methodValVar, methodValVarNumber, ", \" site-menu-icon ", css, cssNumero, "\").f();");
										else if("a".equals(element))
											methodValsWriter.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", \" ", css, cssNumero, "\").a(\"href\", ", methodVar, methodValVar, methodValVarNumber, ").f();");
										else if("br".equals(element))
											methodValsWriter.tl(2 + xmlPart, "e(\"", element, "\").fg();");
										else if("td".equals(element))
											methodValsWriter.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", \" w3-mobile ", css, cssNumero, "\").f();");
										else
											methodValsWriter.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", \" ", css, cssNumero, "\").f();");

										if(!"br".equals(element)) {
											methodXmlStack.push(element);
											methodNumberStack.push(numero);
											xmlPart++;
										}
									}
									else if(StringUtils.equals(element, methodXmlStack.get(xmlPart)) && numero.equals(methodNumberStack.get(xmlPart))) {
										xmlPart++;
									}
									else {
										while(methodXmlStack.size() > xmlPart) {
											methodValsWriter.tl(1 + methodXmlStack.size(), "} g(\"", methodXmlStack.peek(), "\");");
											methodXmlStack.pop();
											methodNumberStack.pop();
										}
										if("i".equals(element))
											methodValsWriter.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", ", methodVar, methodValVar, methodValVarNumber, ", \" site-menu-icon ", css, cssNumero, "\").f();");
										else if("a".equals(element))
											methodValsWriter.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", \" ", css, cssNumero, "\").a(\"href\", ", methodVar, methodValVar, methodValVarNumber, ").f();");
										else if("br".equals(element))
											methodValsWriter.tl(2 + xmlPart, "e(\"", element, "\").fg();");
										else if("td".equals(element))
											methodValsWriter.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", \" w3-mobile ", css, cssNumero, "\").f();");
										else
											methodValsWriter.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", \" ", css, cssNumero, "\").f();");

										if(!"br".equals(element)) {
											methodXmlStack.push(element);
											methodNumberStack.push(numero);
											xmlPart++;
										}
									}
								}
							}
						}
						if(html && !"i".equals(methodXmlStack.peek())) {
							Integer p = methodXmlStack.size();
							if(StringUtils.isEmpty(methodeValCode)) {
								methodValsWriter.tl(2 + p, "sx(", methodVar, methodValVar, methodValVarNumber, ");");
							}
							else {
								if(classeEntiteVars.contains("utilisateurId"))
									methodValsWriter.tl(2 + p, "sx(utilisateurId == null ? ", methodVar, methodValVar, methodValVarNumber, " : ", methodeValCode, ");");
								else
									methodValsWriter.tl(2 + p, "sx(", str_requeteSite(langueNom), "_.getUtilisateurId() == null ? ", methodVar, methodValVar, methodValVarNumber, " : ", methodeValCode, ");");
							}
						}
					}
				}

				methodValVarOld = methodValVar;
				methodValVarLanguageOld = methodValVarLanguage;
			}
			if(StringUtils.equals(langueNom, methodValLanguage)) {
				methodValVarOld = methodValVar;
				methodValVarLanguageOld = methodValVarLanguage;
				methodValVar = null;
	
				if(methodValVarOld != null && !StringUtils.equals(methodValVar, methodValVarLanguageOld)) {
					t(1, "public static final String ", methodVar, methodValVarOld, " = ");
					for(int k = 1; k <= methodValVarNumber; k++) {
						if(k > 1)
							s(" + ");
						s(methodVar, methodValVarOld, k);
					}
					l(";");
					methodValVarNumber = 0;
				}
			}
			l();

			for(int q = methodXmlStack.size() - 1; q >= 0; q--) {
				methodValsWriter.tl(2 + q, "} g(\"", methodXmlStack.get(q), "\");");
				methodXmlStack.pop();
			}
		}
	}

	public void  genCodeEntity(String languageName) throws Exception, Exception {
		String entityVar = (String)doc.get("entityVar_" + languageName + "_stored_string");
		String entityTypeSuffix = (String)doc.get("entityTypeSuffix_stored_string");
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

		if(entityCanonicalName != null) {
	//		String entityVarCleUniqueActuel = (String)doc.get("entityVarCleUnique_stored_string");
	//		if(StringUtils.isNotEmpty(entityVarCleUniqueActuel))
	//			entityVarCleUnique = entityVarCleUniqueActuel;
			String entitySolrCanonicalName = (String)doc.get("entitySolrCanonicalName_stored_string");
			String entitySolrSimpleName = (String)doc.get("entitySolrSimpleName_stored_string");
			String entitySimpleNameVertxJson = (String)doc.get("entitySimpleNameVertxJson_stored_string");
			String entityCanonicalNameVertxJson = (String)doc.get("entityCanonicalNameVertxJson_stored_string");
			String entityListSimpleNameVertxJson = (String)doc.get("entityListSimpleNameVertxJson_stored_string");
			String entityListCanonicalNameVertxJson = (String)doc.get("entityListCanonicalNameVertxJson_stored_string");
	
			Boolean entityExact = (Boolean)doc.get("entityExact_stored_boolean");
			Boolean entityPrimaryKey = (Boolean)doc.get("entityPrimaryKey_stored_boolean");
			Boolean entityUniqueKey = (Boolean)doc.get("entityUniqueKey_stored_boolean");
			Boolean entityEncrypted = (Boolean)doc.get("entityEncrypted_stored_boolean");
			Boolean entitySuggested = (Boolean)doc.get("entitySuggested_stored_boolean");
			Boolean entitySaved = (Boolean)doc.get("entitySaved_stored_boolean");
			Boolean entityIndexed = (Boolean)doc.get("entityIndexed_stored_boolean");
			Boolean entityStored = (Boolean)doc.get("entityStored_stored_boolean");
			Boolean entityText = (Boolean)doc.get("entityText_stored_boolean");
			String entityLanguage = (String)doc.get("entityLanguage_stored_string");
			Boolean entityIncremented = (Boolean)doc.get("entityIncremented_stored_boolean");
			Boolean entityIgnored = (Boolean)doc.get("entityIgnored_stored_boolean");
			Boolean entityDeclared = (Boolean)doc.get("entityDeclared_stored_boolean");
			Boolean entitySearch = (Boolean)doc.get("entitySearch_stored_boolean");
			Boolean entityAttribute = BooleanUtils.isTrue((Boolean)doc.get("entityAttribute_stored_boolean"));
			String entityAttributeCanonicalName = (String)doc.get("entityAttributeCanonicalName_" + languageName + "_stored_string");
			String entityAttributeSimpleName = (String)doc.get("entityAttributeSimpleName_" + languageName + "_stored_string");
			String entityAttributeVar = (String)doc.get("entityAttributeVar_" + languageName + "_stored_string");
			String entityAttributeVarTitle = (String)doc.get("entityAttributeVarTitle_" + languageName + "_stored_string");
			String entityAttributeVarDescription = (String)doc.get("entityAttributeVarDescription_" + languageName + "_stored_string");
			String entityAttributeVarImageUrl = (String)doc.get("entityAttributeVarImageUrl_" + languageName + "_stored_string");
			Boolean entityAdd = (Boolean)doc.get("entityAdd_stored_boolean");
			Boolean entityDelete = (Boolean)doc.get("entityDelete_stored_boolean");
			Boolean entityModify = (Boolean)doc.get("entityModify_stored_boolean");
			Boolean entityRefresh = (Boolean)doc.get("entityRefresh_stored_boolean");
			Boolean entityMultiLine = (Boolean)doc.get("entityMultiLine_stored_boolean");
			Boolean entityKeys = (Boolean)doc.get("entityKeys_stored_boolean");
			Boolean entityIndexedOrStored = (Boolean)doc.get("entityIndexedOrStored_stored_boolean");
			Boolean entityDefined = (Boolean)doc.get("entityDefined_stored_boolean");
			Boolean entityContientSiteRequest = BooleanUtils.isTrue((Boolean)doc.get("entityContientSiteRequest_stored_boolean"));
	
			String entityDisplayName = (String)doc.get("entityDisplayName_" + languageName + "_stored_string");
			String entityHtmlTooltip = (String)doc.get("entityHtmlTooltip_" + languageName + "_stored_string");
			Boolean entityHtml = (Boolean)doc.get("entityHtml_" + languageName + "_stored_boolean");

			entitySuperClassesAndMeWithoutGen = (List<String>)doc.get("entitySuperClassesAndMeWithoutGen_stored_strings");
	
			List<String> entityMethodsBeforeVisibility = (List<String>)doc.get("entityMethodsBeforeVisibility_stored_strings");
			List<String> entityMethodsBeforeVar = (List<String>)doc.get("entityMethodsBeforeVar_" + languageName + "_stored_strings");
			List<String> entityMethodsBeforeParamVar = (List<String>)doc.get("entityMethodsBeforeParamVar_" + languageName + "_stored_strings");
			List<String> entityMethodsBeforeSimpleName = (List<String>)doc.get("entityMethodsBeforeSimpleName_" + languageName + "_stored_strings");
			List<Boolean> entityMethodsBeforeParamName = (List<Boolean>)doc.get("entityMethodsBeforeParamName_stored_booleans");
			List<Boolean> entityMethodsBeforeWrite = (List<Boolean>)doc.get("entityMethodsBeforeWrite_stored_booleans");
	
			List<String> entityMethodsAfterVisibility = (List<String>)doc.get("entityMethodsAfterVisibility_stored_strings");
			List<String> entityMethodsAfterVar = (List<String>)doc.get("entityMethodsAfterVar_" + languageName + "_stored_strings");
			List<String> entityMethodsAfterParamVar = (List<String>)doc.get("entityMethodsAfterParamVar_" + languageName + "_stored_strings");
			List<String> entityMethodsAfterSimpleName = (List<String>)doc.get("entityMethodsAfterSimpleName_" + languageName + "_stored_strings");
			List<Boolean> entityMethodsAfterParamName = (List<Boolean>)doc.get("entityMethodsAfterParamName_stored_booleans");
			List<Boolean> entityMethodsAfterWrite = (List<Boolean>)doc.get("entityMethodsAfterWrite_stored_booleans");

			List<String> entityWriteMethods = (List<String>)doc.get("entityWriteMethods_stored_strings");
			if(entityWriteMethods == null)
				entityWriteMethods = new ArrayList<>();
			for(int i = 0; i < classWriteMethods.size(); i++) {
				String classWriteMethod = classWriteMethods.get(i);
				if(entitySimpleNameCompleteGeneric == null && entityWriteMethods.contains(classWriteMethod)) {
					AllWriter w = classWriteWriters.get(i);
					String var = classWriteMethod + entityVarCapitalized;
					if(classMethodVars.contains(var)) {
						w.tl(2, "((", classSimpleName, ")this).", var, "();");
					}
					else {
						w.tl(2, "if(", entityVar, " != null)");
						w.tl(3, entityVar, ".", classWriteMethod, "();");
					}
				}
			}
	
			o = writerGenClass;
	
			l();
			String commentLine = "\t///" + String.join("", Collections.nCopies(entityVar.length(), "/")) + "///";
			l(commentLine);
			tl(1, "// ", entityVar, " //");
			l(commentLine);
			l();

			AllWriter entityValsWriter = AllWriter.create();
			List<String> entityValsVar = (List<String>)doc.get("entityValsVar_stored_strings");
			List<String> entityValsLanguage = (List<String>)doc.get("entityValsLanguage_stored_strings");
			List<String> entityValsCode = (List<String>)doc.get("entityValsCode_stored_strings");
			List<String> entityValsValue = (List<String>)doc.get("entityValsValue_stored_strings");
			if(entityValsVar != null && entityValsLanguage != null && entityValsValue != null) {
				String entityValVarOld = null;
				Integer entityValVarNumber = 0;
				String entityValVar = null;
				String entityValLanguage = null;
				String entityValVarLanguage = null;
				String entityValVarLanguageOld = null;
				String entityValCode = null;
				String entityValValue = null;
	
				entityXmlStack = new Stack<String>();
				entityNumberStack = new Stack<Integer>();
				for(int j = 0; j < entityValsVar.size(); j++) {
					entityValVar = entityValsVar.get(j);
					entityValLanguage = entityValsLanguage.get(j);
					if(StringUtils.isBlank(entityValLanguage))
						entityValLanguage = languageName;
					entityValVarLanguage = entityValVar + entityValLanguage;
					entityValCode = entityValsCode.get(j);
					entityValValue = entityValsValue.get(j);
	
					Integer xmlPart = 0;
					if(!StringUtils.equals(entityValVarLanguage, entityValVarLanguageOld) && (StringUtils.equals(entityValVarLanguageOld, entityValVarOld + languageName))) {
						t(1, "public static final String ", entityVar, entityValVarOld, " = ");
						for(int k = 1; k <= entityValVarNumber; k++) {
							if(k > 1)
								s(" + ");
							s(entityVar, entityValVarOld, k);
						}
						l(";");
						entityValVarNumber = 0;
					}
	
					if(StringUtils.equals(languageName, entityValLanguage)) {
						entityValVarNumber++;
						tl(1, "public static final String ", entityVar, entityValVar, entityValVarNumber, " = \"", escapeJava(entityValValue), "\";");
						if(!classVals.getEmpty())
							classVals.s(", ");
						classVals.s(entityVar, entityValVar, entityValVarNumber);
						{
							String[] parts = splitByCharacterTypeCamelCase(entityValVar);
							Boolean html = false;
							for(Integer p = 0; p < parts.length; p++) {
								String part = StringUtils.uncapitalize(parts[p]);

								Matcher regex = Pattern.compile("^(\\w+?)(\\d*)$").matcher(part);
								boolean trouve = regex.find();
								if(trouve) {
									String element = StringUtils.lowerCase(regex.group(1));
									String numberStr = regex.group(2);
									Integer number = StringUtils.isEmpty(numberStr) ? null : Integer.parseInt(numberStr);
									if("h".equals(element)) {
										element += number;
										number = null;
									}

//									entityValsWriter.t(1);
									if(StringUtils.equalsAny(element, HTML_ELEMENTS)) {
										html = true;

										String css = entityVar;
										for(Integer r = 0; r <= xmlPart; r++) {
											String s = parts[r];
											css += s;
										}
										css += " ";

										String cssNumber = number == null ? "" : (StringUtils.substringBeforeLast(StringUtils.substringBeforeLast(css, number.toString()), "0") + (number % 2 == 0 ? " even " : " odd "));

										if(number == null)
											number = 1;

										if(entityXmlStack.size() < (xmlPart + 1)) {
											if("i".equals(element))
												entityValsWriter.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", ", entityVar, entityValVar, entityValVarNumber, ", \" site-menu-icon ", css, cssNumber, "\").f();");
											else if("a".equals(element))
												entityValsWriter.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", \" ", css, cssNumber, "\").a(\"href\", ", entityVar, entityValVar, entityValVarNumber, ").f();");
											else if("br".equals(element))
												entityValsWriter.tl(2 + xmlPart, "e(\"", element, "\").fg();");
											else if("td".equals(element))
												entityValsWriter.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", \" w3-mobile ", css, cssNumber, "\").f();");
											else
												entityValsWriter.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", \" ", css, cssNumber, "\").f();");

											if(!"br".equals(element)) {
												entityXmlStack.push(element);
												entityNumberStack.push(number);
												xmlPart++;
											}
										}
										else if(StringUtils.equals(element, entityXmlStack.get(xmlPart)) && number.equals(entityNumberStack.get(xmlPart))) {
											xmlPart++;
										}
										else {
											while(entityXmlStack.size() > xmlPart) {
												entityValsWriter.tl(1 + entityXmlStack.size(), "} g(\"", entityXmlStack.peek(), "\");");
												entityXmlStack.pop();
												entityNumberStack.pop();
											}
											if("i".equals(element))
												entityValsWriter.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", ", entityVar, entityValVar, entityValVarNumber, ", \" site-menu-icon ", css, cssNumber, "\").f();");
											else if("a".equals(element))
												entityValsWriter.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", \" ", css, cssNumber, "\").a(\"href\", ", entityVar, entityValVar, entityValVarNumber, ").f();");
											else if("br".equals(element))
												entityValsWriter.tl(2 + xmlPart, "e(\"", element, "\").fg();");
											else if("td".equals(element))
												entityValsWriter.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", \" w3-mobile ", css, cssNumber, "\").f();");
											else
												entityValsWriter.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", \" ", css, cssNumber, "\").f();");

											if(!"br".equals(element)) {
												entityXmlStack.push(element);
												entityNumberStack.push(number);
												xmlPart++;
											}
										}
									}
								}
							}
							if(html && !"i".equals(entityXmlStack.peek())) {
								Integer p = entityXmlStack.size();
								if(StringUtils.isEmpty(entityValCode)) {
									entityValsWriter.tl(2 + p, "sx(", entityVar, entityValVar, entityValVarNumber, ");");
								}
								else {
									if(classEntityVars.contains("userId"))
										entityValsWriter.tl(2 + p, "sx(userId == null ? ", entityVar, entityValVar, entityValVarNumber, " : ", entityValCode, ");");
									else
										entityValsWriter.tl(2 + p, "sx(", str_siteRequest(languageName), "_.getUserId() == null ? ", entityVar, entityValVar, entityValVarNumber, " : ", entityValCode, ");");
								}
							}
						}
					}
	
					entityValVarOld = entityValVar;
					entityValVarLanguageOld = entityValVarLanguage;
				}
				if(StringUtils.equals(languageName, entityValLanguage)) {
					entityValVarOld = entityValVar;
					entityValVarLanguageOld = entityValVarLanguage;
					entityValVar = null;
		
					if(entityValVarOld != null && !StringUtils.equals(entityValVar, entityValVarLanguageOld)) {
						t(1, "public static final String ", entityVar, entityValVarOld, " = ");
						for(int k = 1; k <= entityValVarNumber; k++) {
							if(k > 1)
								s(" + ");
							s(entityVar, entityValVarOld, k);
						}
						l(";");
						entityValVarNumber = 0;
					}
				}
				l();

				for(int q = entityXmlStack.size() - 1; q >= 0; q--) {
					entityValsWriter.tl(2 + q, "} g(\"", entityXmlStack.get(q), "\");");
					entityXmlStack.pop();
				}
			}

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
	
			if(entityIgnored)
				tl(1, "@JsonIgnore");
			t(1, "protected ", entitySimpleNameComplete, " ", entityVar);
			if(!entityWrap) {
				if("java.util.List".equals(entityCanonicalName)) {
					s(" = new java.util.ArrayList<");
					s(entityCanonicalNameGeneric);
					s(">()");
				}
				else if("java.util.Map".equals(entityCanonicalName)) {
					s(" = new java.util.HashMap<");
					s(entityCanonicalNameGeneric);
					s(">()");
				}
				else if("java.util.Set".equals(entityCanonicalName)) {
					s(" = new java.util.HashSet<");
					s(entityCanonicalNameGeneric);
					s(">()");
				}
				else {
					s(" = new ", entitySimpleNameComplete, "()");
				}
			}
			l(";");
	
			tl(1, "@JsonIgnore");
			t(1, "public ", classPartsWrap.simpleName(languageName), "<", entitySimpleNameComplete, "> ", entityVar, classPartsWrap.simpleName(languageName));
			l(" = new ", classPartsWrap.simpleName(languageName), "<", entitySimpleNameComplete, ">().p(this).c(", entitySimpleName, ".class).var(\"", entityVar, "\").o(", entityVar, ");");
	
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
	//				String methodExceptionSimpleNameComplete = methodExceptionsSimpleNameComplete.get(i);
	//				tl(1, " * @throws ", methodExceptionSimpleNameComplete);
	//			}
	//		}
			tl(1, " **/");
			t(1, "protected abstract void");
			s(" _", entityVar);
			s("(");
			if(entityWrap) {
				s(classPartsWrap.simpleName(languageName), "<", entitySimpleNameComplete, "> ", entityVarParam);
			}
			else {
				s(entitySimpleNameComplete, " ", entityVarParam);
			}
			s(")");
			if(methodExceptionsSimpleNameComplete != null && methodExceptionsSimpleNameComplete.size() > 0) {
	
				s(" throws ");
				for(int i = 0; i < methodExceptionsSimpleNameComplete.size(); i++) {
					String methodExceptionSimpleNameComplete = methodExceptionsSimpleNameComplete.get(i);
					if(i > 0) 
						s(", ");
					s(methodExceptionSimpleNameComplete);
				}
			}
			l(";");
	
	//						l();
	//						tl(1, "public ", classSimpleName, " ", entityVar, "(", entitySimpleNameComplete, " ", entityVarParam, ") {");
	//						tl(2, "set", entityVarCapitalized, "(", entityVarParam, ");");
	//						tl(2, "return (", classSimpleName, ")this;");
	//						tl(1, "}");
	
			l();
			tl(1, "public ", entitySimpleNameComplete, " get", entityVarCapitalized, "() {");
			tl(2, "return ", entityVar, ";");
			tl(1, "}");
	
			l();
			tl(1, "public void set", entityVarCapitalized, "(", entitySimpleNameComplete, " ", entityVar, ") {");
			tl(2, "this.", entityVar, " = ", entityVar, ";");
			tl(2, "this.", entityVar, classPartsWrap.simpleName(languageName), ".", str_alreadyInitialized(languageName), " = true;");
			tl(1, "}");
	//
	//						l();
	//						tl(1, "public ", entitySimpleNameComplete, " ", entityVar, "() {");
	//						tl(2, "return get", entityVarCapitalized, "();");
	//						tl(1, "}");
	
			// Setter List //
			if(StringUtils.equals(entityCanonicalName, ArrayList.class.getCanonicalName()) && StringUtils.equals(entityCanonicalNameGeneric, Long.class.getCanonicalName())) {
				tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(String o) {");
				tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {");
				tl(3, "Long l = Long.parseLong(o);");
				tl(3, "add", entityVarCapitalized, "(l);");
				tl(2, "}");
				tl(2, "this.", entityVar, classPartsWrap.simpleName(languageName), ".", str_alreadyInitialized(languageName), " = true;");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
			}
	
			// Setter Boolean //
			if(StringUtils.equals(entityCanonicalName, Boolean.class.getCanonicalName())) {
				tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(String o) {");
				tl(2, "this.", entityVar, " = Boolean.parseBoolean(o);");
				tl(2, "this.", entityVar, classPartsWrap.simpleName(languageName), ".", str_alreadyInitialized(languageName), " = true;");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
			}
	
			// Setter Integer //
			if(StringUtils.equals(entityCanonicalName, Integer.class.getCanonicalName())) {
				tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(String o) {");
				tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))");
				tl(3, "this.", entityVar, " = Integer.parseInt(o);");
				tl(2, "this.", entityVar, classPartsWrap.simpleName(languageName), ".", str_alreadyInitialized(languageName), " = true;");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
			}
	
			// Setter Float //
			if(StringUtils.equals(entityCanonicalName, Float.class.getCanonicalName())) {
				tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(String o) {");
				tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))");
				tl(3, "this.", entityVar, " = Float.parseFloat(o);");
				tl(2, "this.", entityVar, classPartsWrap.simpleName(languageName), ".", str_alreadyInitialized(languageName), " = true;");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
			}
	
			// Setter Double //
			if(StringUtils.equals(entityCanonicalName, Double.class.getCanonicalName())) {
				tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(String o) {");
				tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))");
				tl(3, "this.", entityVar, " = Double.parseDouble(o);");
				tl(2, "this.", entityVar, classPartsWrap.simpleName(languageName), ".", str_alreadyInitialized(languageName), " = true;");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
			}
	
			// Setter Long //
			if(StringUtils.equals(entityCanonicalName, Long.class.getCanonicalName())) {
				tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(String o) {");
				tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))");
				tl(3, "this.", entityVar, " = Long.parseLong(o);");
				tl(2, "this.", entityVar, classPartsWrap.simpleName(languageName), ".", str_alreadyInitialized(languageName), " = true;");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
			}
	
			// Setter Long //
			if(StringUtils.equals(entitySimpleName, "Chain")) {
				tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(String o) {");
				tl(2, entityVar, ".s(o);");
				tl(2, "this.", entityVar, classPartsWrap.simpleName(languageName), ".", str_alreadyInitialized(languageName), " = true;");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
			}
	
			// Setter BigDecimal //
			if(StringUtils.equals(entityCanonicalName, BigDecimal.class.getCanonicalName())) {
				tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(String o) {");
				tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))");
				tl(3, "this.", entityVar, " = new BigDecimal(o);");
				tl(2, "this.", entityVar, classPartsWrap.simpleName(languageName), ".", str_alreadyInitialized(languageName), " = true;");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
				tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(Double o) {");
				tl(3, "this.", entityVar, " = new BigDecimal(o);");
				tl(2, "this.", entityVar, classPartsWrap.simpleName(languageName), ".", str_alreadyInitialized(languageName), " = true;");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
				tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(Integer o) {");
				tl(3, "this.", entityVar, " = new BigDecimal(o);");
				tl(2, "this.", entityVar, classPartsWrap.simpleName(languageName), ".", str_alreadyInitialized(languageName), " = true;");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
			}
	
			// Setter Timestamp //
			if(StringUtils.equals(entityCanonicalName, Timestamp.class.getCanonicalName())) {
				tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
				tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(String o) {");
				tl(2, "this.", entityVar, " = Timestamp.valueOf((LocalDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME)));");
				// TODO: ctate until vertx fixes bug. 
				tl(2, "this.", entityVar, " = Timestamp.valueOf((LocalDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME)));");
				tl(2, "this.", entityVar, classPartsWrap.simpleName(languageName), ".", str_alreadyInitialized(languageName), " = true;");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
			}
	
			// Setter Date //
			if(StringUtils.equals(entityCanonicalName, Date.class.getCanonicalName())) {
				tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
				tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(String o) {");
				tl(2, "this.", entityVar, " = Date.from(LocalDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME).atZone(ZoneId.systemDefault()).toInstant());");
				tl(2, "this.", entityVar, classPartsWrap.simpleName(languageName), ".", str_alreadyInitialized(languageName), " = true;");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
			}
	
			// Setter LocalDate //
			if(StringUtils.equals(entityCanonicalName, LocalDate.class.getCanonicalName())) {
				tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(Instant o) {");
				tl(2, "this.", entityVar, " = LocalDate.from(o);");
				tl(2, "this.", entityVar, classPartsWrap.simpleName(languageName), ".", str_alreadyInitialized(languageName), " = true;");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
				tl(1, "/** Example: 2011-12-03+01:00 **/");
				tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(String o) {");
				// TODO: ctate until vertx fixes bug. 
				tl(2, "this.", entityVar, " = LocalDate.parse(o, DateTimeFormatter.ofPattern(\"yyyy/MM/dd\"));");
//				tl(2, "this.", entityVar, " = LocalDate.parse(o, DateTimeFormatter.ISO_OFFSET_DATE);");
				tl(2, "this.", entityVar, classPartsWrap.simpleName(languageName), ".", str_alreadyInitialized(languageName), " = true;");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
				tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(Date o) {");
				tl(2, "this.", entityVar, " = o.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();");
				tl(2, "this.", entityVar, classPartsWrap.simpleName(languageName), ".", str_alreadyInitialized(languageName), " = true;");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
			}
	
			// Setter ZonedDateTime //
			if(StringUtils.equals(entityCanonicalName, ZonedDateTime.class.getCanonicalName())) {
				tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(Instant o) {");
				tl(2, "this.", entityVar, " = ZonedDateTime.from(o);");
				tl(2, "this.", entityVar, classPartsWrap.simpleName(languageName), ".", str_alreadyInitialized(languageName), " = true;");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
				tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
				tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(String o) {");
				tl(2, "this.", entityVar, " = ZonedDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME);");
				tl(2, "this.", entityVar, classPartsWrap.simpleName(languageName), ".", str_alreadyInitialized(languageName), " = true;");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
				tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(Date o) {");
				tl(2, "this.", entityVar, " = ZonedDateTime.ofInstant(o.toInstant(), ZoneId.systemDefault());");
				tl(2, "this.", entityVar, classPartsWrap.simpleName(languageName), ".", str_alreadyInitialized(languageName), " = true;");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
			}
	
			// Setter LocalDateTime //
			if(StringUtils.equals(entityCanonicalName, LocalDateTime.class.getCanonicalName())) {
				tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(Instant o) {");
				tl(2, "this.", entityVar, " = LocalDateTime.from(o);");
				tl(2, "this.", entityVar, classPartsWrap.simpleName(languageName), ".", str_alreadyInitialized(languageName), " = true;");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
				tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
				tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(String o) {");
				tl(2, "this.", entityVar, " = LocalDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME);");
				tl(2, "this.", entityVar, classPartsWrap.simpleName(languageName), ".", str_alreadyInitialized(languageName), " = true;");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
				tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(Date o) {");
				tl(2, "this.", entityVar, " = LocalDateTime.ofInstant(o.toInstant(), ZoneId.systemDefault());");
				tl(2, "this.", entityVar, classPartsWrap.simpleName(languageName), ".", str_alreadyInitialized(languageName), " = true;");
				tl(2, "return (", classSimpleName, ")this;");
				tl(1, "}");
			}
	
			// Ajouter //
			if(StringUtils.equals(entityCanonicalName, List.class.getCanonicalName()) || StringUtils.equals(entityCanonicalName, ArrayList.class.getCanonicalName()) || StringUtils.equals(entityCanonicalName, Set.class.getCanonicalName()) || StringUtils.equals(entityCanonicalName, HashSet.class.getCanonicalName())) {
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
		
				// Setter String //
				if(StringUtils.equals(entityCanonicalNameGeneric, String.class.getCanonicalName())) {
					tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(JsonArray objets) {");
					tl(2, entityVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, entitySimpleNameCompleteGeneric, " o = objets.get", entitySimpleNameCompleteGeneric, "(i);");
					tl(3, "add", entityVarCapitalized, "(o);");
					tl(2, "}");
					tl(2, "return (", classSimpleName, ")this;");
					tl(1, "}");
				}
		
				// Setter Boolean //
				if(StringUtils.equals(entityCanonicalNameGeneric, Boolean.class.getCanonicalName())) {
					tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(JsonArray objets) {");
					tl(2, entityVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, entitySimpleNameCompleteGeneric, " o = objets.get", entitySimpleNameCompleteGeneric, "(i);");
					tl(3, "add", entityVarCapitalized, "(o);");
					tl(2, "}");
					tl(2, "return (", classSimpleName, ")this;");
					tl(1, "}");
					tl(1, "public ", classSimpleName, " add", entityVarCapitalized, "(String o) {");
					tl(2, entitySimpleNameCompleteGeneric, " p = Boolean.parseBoolean(o);");
					tl(2, "add", entityVarCapitalized, "(p);");
					tl(2, "return (", classSimpleName, ")this;");
					tl(1, "}");
				}
		
				// Setter Integer //
				if(StringUtils.equals(entityCanonicalNameGeneric, Integer.class.getCanonicalName())) {
					tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(JsonArray objets) {");
					tl(2, entityVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, entitySimpleNameCompleteGeneric, " o = objets.get", entitySimpleNameCompleteGeneric, "(i);");
					tl(3, "add", entityVarCapitalized, "(o);");
					tl(2, "}");
					tl(2, "return (", classSimpleName, ")this;");
					tl(1, "}");
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
					tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(JsonArray objets) {");
					tl(2, entityVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, "Double o = objets.getDouble(i);");
					tl(3, "add", entityVarCapitalized, "(new BigDecimal(o));");
					tl(2, "}");
					tl(2, "return (", classSimpleName, ")this;");
					tl(1, "}");
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
					tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(JsonArray objets) {");
					tl(2, entityVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, entitySimpleNameCompleteGeneric, " o = objets.get", entitySimpleNameCompleteGeneric, "(i);");
					tl(3, "add", entityVarCapitalized, "(o);");
					tl(2, "}");
					tl(2, "return (", classSimpleName, ")this;");
					tl(1, "}");
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
					tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(JsonArray objets) {");
					tl(2, entityVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, entitySimpleNameCompleteGeneric, " o = objets.get", entitySimpleNameCompleteGeneric, "(i);");
					tl(3, "add", entityVarCapitalized, "(o);");
					tl(2, "}");
					tl(2, "return (", classSimpleName, ")this;");
					tl(1, "}");
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
					tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(JsonArray objets) {");
					tl(2, entityVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, entitySimpleNameCompleteGeneric, " o = objets.get", entitySimpleNameCompleteGeneric, "(i);");
					tl(3, "add", entityVarCapitalized, "(o);");
					tl(2, "}");
					tl(2, "return (", classSimpleName, ")this;");
					tl(1, "}");
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
					tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(JsonArray objets) {");
					tl(2, entityVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, "Instant o = objets.getInstant(i);");
					tl(3, "add", entityVarCapitalized, "(o);");
					tl(2, "}");
					tl(2, "return (", classSimpleName, ")this;");
					tl(1, "}");
					tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
					tl(1, "public ", classSimpleName, " add", entityVarCapitalized, "(String o) {");
					tl(2, entitySimpleNameCompleteGeneric, " p = Timestamp.valueOf((LocalDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME)));");
					tl(2, "add", entityVarCapitalized, "(p);");
					tl(2, "return (", classSimpleName, ")this;");
					tl(1, "}");
				}
		
				// Setter Date //
				if(StringUtils.equals(entityCanonicalNameGeneric, Date.class.getCanonicalName())) {
					tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(JsonArray objets) {");
					tl(2, entityVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, "Instant o = objets.getInstant(i);");
					tl(3, "add", entityVarCapitalized, "(o);");
					tl(2, "}");
					tl(2, "return (", classSimpleName, ")this;");
					tl(1, "}");
					tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
					tl(1, "public ", classSimpleName, " add", entityVarCapitalized, "(String o) {");
					tl(2, entitySimpleNameCompleteGeneric, " p = Date.from(LocalDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME).atZone(ZoneId.systemDefault()).toInstant());");
					tl(2, "add", entityVarCapitalized, "(p);");
					tl(2, "return (", classSimpleName, ")this;");
					tl(1, "}");
				}
		
				// Setter LocalDate //
				if(StringUtils.equals(entityCanonicalNameGeneric, LocalDate.class.getCanonicalName())) {
					tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(JsonArray objets) {");
					tl(2, entityVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, "Instant o = objets.getInstant(i);");
					tl(3, "add", entityVarCapitalized, "(o);");
					tl(2, "}");
					tl(2, "return (", classSimpleName, ")this;");
					tl(1, "}");
					tl(1, "/** Example: 2011-12-03+01:00 **/");
					tl(1, "public ", classSimpleName, " add", entityVarCapitalized, "(String o) {");
					tl(2, entitySimpleNameCompleteGeneric, " p = LocalDate.parse(o, DateTimeFormatter.ofPattern(\"yyyy/MM/dd\"));");
				// TODO: ctate until vertx fixes bug. 
//					tl(2, entitySimpleNameCompleteGeneric, " p = LocalDate.parse(o, DateTimeFormatter.ISO_OFFSET_DATE);");
					tl(2, "add", entityVarCapitalized, "(p);");
					tl(2, "return (", classSimpleName, ")this;");
					tl(1, "}");
					tl(1, "public ", classSimpleName, " add", entityVarCapitalized, "(Date o) {");
					tl(2, entitySimpleNameCompleteGeneric, " p = o.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();");
					tl(2, "add", entityVarCapitalized, "(p);");
					tl(2, "return (", classSimpleName, ")this;");
					tl(1, "}");
				}
		
				// Setter ZonedDateTime //
				if(StringUtils.equals(entityCanonicalNameGeneric, ZonedDateTime.class.getCanonicalName())) {
					tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(JsonArray objets) {");
					tl(2, entityVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, "Instant o = objets.getInstant(i);");
					tl(3, "add", entityVarCapitalized, "(o);");
					tl(2, "}");
					tl(2, "return (", classSimpleName, ")this;");
					tl(1, "}");
					tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
					tl(1, "public ", classSimpleName, " add", entityVarCapitalized, "(String o) {");
					tl(2, entitySimpleNameCompleteGeneric, " p = ZonedDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME);");
					tl(2, "add", entityVarCapitalized, "(p);");
					tl(2, "return (", classSimpleName, ")this;");
					tl(1, "}");
					tl(1, "public ", classSimpleName, " add", entityVarCapitalized, "(Date o) {");
					tl(2, entitySimpleNameCompleteGeneric, " p = ZonedDateTime.ofInstant(o.toInstant(), ZoneId.systemDefault());");
					tl(2, "add", entityVarCapitalized, "(p);");
					tl(2, "return (", classSimpleName, ")this;");
					tl(1, "}");
				}
		
				// Setter LocalDateTime //
				if(StringUtils.equals(entityCanonicalNameGeneric, LocalDateTime.class.getCanonicalName())) {
					tl(1, "public ", classSimpleName, " set", entityVarCapitalized, "(JsonArray objets) {");
					tl(2, entityVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, "Instant o = objets.getInstant(i);");
					tl(3, "add", entityVarCapitalized, "(o);");
					tl(2, "}");
					tl(2, "return (", classSimpleName, ")this;");
					tl(1, "}");
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
			t(1, "protected ", classSimpleName, " ", entityVar, "Init()");
			if(classInitDeepExceptions.size() > 0) {
				s(" throws ");
				for(int i = 0; i < classInitDeepExceptions.size(); i++) {
					String classInitDeepException = classInitDeepExceptions.get(i);
					String classInitDeepExceptionNomSimple = StringUtils.substringAfterLast(classInitDeepException, ".");
					if(i > 0)
						s(", ");
					s(classInitDeepExceptionNomSimple);
				}
			}
			l(" {");

			if(entityCanonicalNameGeneric == null && entityMethodsBeforeVar != null && entityMethodsBeforeVar.size() > 0) {
				tl(2, "if(", entityVar, " != null) {");
				for(int j = 0; j < entityMethodsBeforeVar.size(); j++) {
					String entityMethodBeforeVar = entityMethodsBeforeVar.get(j);
					Boolean entityMethodBeforeParamName = entityMethodsBeforeParamName.get(j);

					t(3, "((", classSimpleName, ")this).", entityMethodBeforeVar, "(", entityVar);
					if(entityMethodBeforeParamName)
						s(", \"", entityVar, "\"");
					l(");");
				}
				tl(2, "}");
			}

			tl(2, "if(!", entityVar, classPartsWrap.simpleName(languageName), ".", str_alreadyInitialized(languageName), ") {");
			if(entityWrap) {
				tl(3, "_", entityVar, "(", entityVar, classPartsWrap.simpleName(languageName), ");");
				tl(3, "if(", entityVar, " == null)");
				tl(4, "set", entityVarCapitalized, "(", entityVar, classPartsWrap.simpleName(languageName), ".o);");
			}
			else {
				tl(3, "_", entityVar, "(", entityVar, ");");
			}
			tl(2, "}");

			// initDeep

//						if(initDeep && canonicalName.enUS().startsWith(classe.nomEnsembleDomaine.enUS())) {
			if(entityInitDeep && entityInitialized) {
				if(entityWrap) {
					tl(2, "if(", entityVar, " != null)");
					tl(3, entityVar, ".", str_initDeep(languageName), str_ForClass(languageName), "(", classeContientSiteRequest ? (str_siteRequest(languageName) + "_") : "null", ");");
				}
				else {
					tl(2, entityVar, ".", str_initDeep(languageName), str_ForClass(languageName), "(", classeContientSiteRequest ? (str_siteRequest(languageName) + "_") : "null", ");");
				}
			}

			if(entityCanonicalNameGeneric == null && entityMethodsAfterVar != null && entityMethodsAfterVar.size() > 0) {
				tl(2, "if(", entityVar, " != null) {");
				for(int j = 0; j < entityMethodsAfterVar.size(); j++) {
					String entityMethodAfterVisibility = entityMethodsAfterVisibility.get(j);
					String entityMethodAfterVar = entityMethodsAfterVar.get(j);
					Boolean entityMethodAfterParamName = entityMethodsAfterParamName.get(j);

					t(3, "((", classSimpleName, ")this).", entityMethodAfterVar, "(", entityVar);
					if(entityMethodAfterParamName)
						s(", \"", entityVar, "\"");
					l(");");
				}
				tl(2, "}");
			}

			tl(2, entityVar, classPartsWrap.simpleName(languageName), ".", str_alreadyInitialized(languageName), "(true);");
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

			//////////
			// htm //
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
				else if(entityCanonicalName.toString().equals(ZonedDateTime.class.getCanonicalName())) {
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
				else if("java.util.List".equals(entityCanonicalName)) {
					tl(2, "return ", entityVar, ";");
				}
				else if("java.util.Set".equals(entityCanonicalName) || "java.util.HashSet".equals(entityCanonicalName)) {
					tl(2, "return new ArrayList<>(", entityVar, ");");
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
				if(VAL_canonicalNameZonedDateTime.equals(entityCanonicalName)) {
					if("frFR".equals(languageName))
						tl(2, "return ", entityVar, " == null ? \"\" : ", entityVar, ".format(DateTimeFormatter.ofPattern(\"EEE d MMM yyyy H'h'mm:ss zz VV\", Locale.FRANCE));");
					else
						tl(2, "return ", entityVar, " == null ? \"\" : ", entityVar, ".format(DateTimeFormatter.ofPattern(\"EEE MMM d yyyy h:mm:ss a zz VV\", Locale.US));");
				}
				else if(VAL_canonicalNameString.equals(entityCanonicalName))
					tl(2, "return ", entityVar, " == null ? \"\" : ", entityVar, ";");
				else
					tl(2, "return ", entityVar, " == null ? \"\" : ", entityVar, ".toString();");
				tl(1, "}");
	
				//////////////////
				// displayName //
				//////////////////
				l();
				tl(1, "public String displayName", entityVarCapitalized, "() {");
				tl(2, "return ", entityDisplayName == null ? "null" : "\"" + escapeJava(entityDisplayName) + "\"", ";");
				tl(1, "}");
	
				/////////////////
				// htmTooltip //
				/////////////////
				l();
				tl(1, "public String htmTooltip", entityVarCapitalized, "() {");
				tl(2, "return ", entityHtmlTooltip == null ? "null" : "\"" + escapeJava(entityHtmlTooltip) + "\"", ";");
				tl(1, "}");
	
				//////////
				// htm //
				//////////
	
				l();
				tl(1, "public String htm", entityVarCapitalized, "() {");
				tl(2, "return ", entityVar, " == null ? \"\" : StringEscapeUtils.escapeHtml4(str", entityVarCapitalized, "());");
				tl(1, "}");
	
				if(entityVarCapitalized != null && classSaved && entitySolrCanonicalName != null) {
					l();
					tl(1, "public void htm", entityVarCapitalized, "(", classPartsAllWriter.simpleName(languageName), " r, Boolean patch", str_Rights(languageName), ") {");
					tl(2, "if(", classVarPrimaryKey, "!= null) {");
					tl(3, "r.s(\"<div id=\\\"patch", classSimpleName, "\", str", StringUtils.capitalize(classVarPrimaryKey), "(), \"", entityVarCapitalized, "\\\">\");");
					tl(3, "if(patch", str_Rights(languageName), ") {");
					tl(4, "r.l();");
					tl(4, "r.l(\"	<script>//<![CDATA[\");");
					tl(4, "r.l(\"		function patch", classSimpleName, "\", str", StringUtils.capitalize(classVarPrimaryKey), "(), \"", entityVarCapitalized, "() {\");");
					tl(4, "r.l(\"			$.ajax({\");");
					tl(4, "r.l(\"				url: '", classApiUri, "?fq=", classVarPrimaryKey, ":\", str", StringUtils.capitalize(classVarPrimaryKey), "(), \"',\");");
					tl(4, "r.l(\"				dataType: 'json',\");");
					tl(4, "r.l(\"				type: 'patch',\");");
					tl(4, "r.l(\"				contentType: 'application/json',\");");
					tl(4, "r.l(\"				processData: false,\");");
					tl(4, "r.l(\"				success: function( data, textStatus, jQxhr ) {\");");
					tl(4, "r.l(\"					\");");
					tl(4, "r.l(\"				},\");");
					tl(4, "r.l(\"				error: function( jqXhr, textStatus, errorThrown ) {\");");
					tl(4, "r.l(\"					\");");
					tl(4, "r.l(\"				},\");");
					tl(4, "r.l(\"				data: {\\\"set", entityVarCapitalized, "\\\": this.value },\");");
					tl(4, "r.l(\"				\");");
					tl(4, "r.l(\"			});\");");
					tl(4, "r.l(\"		}\");");
					tl(4, "r.l(\"	//]]></script>\");");
					tl(4, "r.l(\"	<div class=\\\"\\\">\");");
					tl(4, "r.l(\"		<label class=\\\"w3-tooltip \\\">\");");
					tl(4, "r.l(\"			<span>\", StringEscapeUtils.escapeHtml4(displayName", entityVarCapitalized, "()), \"</span>\");");
					tl(4, "r.s(\"			<input\");"); {
						tl(7, "r.s(\" name=\\\"", entityVar, "\\\"\");");
						tl(7, "r.s(\" value=\\\"\", htm", entityVarCapitalized, "(), \"\\\");\");");
						tl(7, "r.s(\" onchange=\\\"\\\"\");");
						tl(7, "r.l(\"/>\");");
					}
					if(entityHtmlTooltip != null)
						tl(4, "r.s(\"<span class=\\\"w3-text w3-tag site-tooltip \\\">", escapeJava(entityHtmlTooltip), "</span>\");");
					tl(4, "r.l(\"		</label>\");");
					tl(4, "r.l(\"	</div>\");");
					tl(3, "} else {");
					tl(4, "r.s(htm", entityVarCapitalized, "());");
					tl(3, "}");
					tl(3, "r.l(\"</div>\");");
					tl(2, "}");
					tl(1, "}");
				}
			}

			for(String classWriteMethod : new String[] { "htmlBody" }) {
				if(entityWriteMethods.contains(classWriteMethod)) {
					if(classPartsPagePart == null)
						throw new Exception("Ajouter une classe avec le commentaire: MotCle: classSimpleNamePagePart");
					if(entitySimpleNameCompleteGeneric == null && "htmlBody".equals(classWriteMethod) && entitySuperClassesAndMeWithoutGen.contains(classPartsPagePart.canonicalName)) {
						tl(1, "public void ", classWriteMethod, entityVarCapitalized, "(", entitySimpleNameComplete, " o) {");
						if(entitySuperClassesAndMeWithoutGen.contains(classPartsPagePart.canonicalName)) {
							// do stuff here. 
							if(!entityValsWriter.getEmpty()) {
								s(entityValsWriter);
							}
						}
						tl(1, "}");
						tl(1, "public void ", classWriteMethod, entityVarCapitalized, "() {");
						tl(2, entityVar, ".htmlAvant();");
						tl(2, classWriteMethod, entityVarCapitalized, "(", entityVar, ");");
						tl(2, entityVar, ".htmlApres();");
						tl(1, "}");
					}
				}
			}
	
			////////////////////
			// codeIninitDeep //
			////////////////////
//			if(entityInitDeep) {
			wInitDeep.tl(2, entityVar, "Init();");
//			}
	
	
			/////////////////////
			// codeSiteRequest //
			/////////////////////
			if(classeContientSiteRequest && entityContientSiteRequest && classInitDeep && entityInitialized) {
				o = wSiteRequest;
				tl(2, "if(", entityVar, " != null)");
				tl(3, entityVar, ".set", str_SiteRequest(languageName), "_(", str_siteRequest(languageName), "_);");
			}
	
			/////////////////
			// codeIndexer //
			/////////////////
			o = wIndex;
			if(classIndexed && entityIndexedOrStored) {
				tl(2, "if(", entityVar, " != null) {");
				if(StringUtils.isNotEmpty(classVarUniqueKey) && entityUniqueKey) {
					// uniqueKey
					tl(3, "document.addField(\"", classVarUniqueKey, "\", ", entityVar, ");");
				}
				if(entityEncrypted) {
					// crypte
					tl(3, "String valEncrypted = siteRequest.encryptStr(", entityVar, ");");
					tl(3, "document.addField(\"", entityVar, "_encrypted", entityTypeSuffix, "\"", "valEncrypted);");
				}
				if(entityIncremented) {
					// crypte
					tl(3, "document.addField(\"", entityVar, "_incremented", "\", new java.util.HashMap<String, ", entitySimpleName, ">() {{ put(\"inc\"", ("Long".equals(entitySimpleName.toString()) ? "1L" : "1"), "); }});");
				}
				if(entitySuggested) {
					// suggere
					if(entitySimpleName.equals("Chain")) {
						tl(3, "document.addField(\"", entityVar, "_suggested", "\", ", entityVar, ");");
					}
					else if(entitySimpleName.equals("Timestamp")) {
						tl(3, "document.addField(\"", entityVar, "_suggested", "\", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(java.time.ZonedDateTime.ofInstant(", entityVar, ".toLocalDateTime(), java.time.OffsetDateTime.now().getOffset(), ZoneId.of(\"Z\"))));");
					}
					else if(entityCanonicalName.toString().equals(ZonedDateTime.class.getCanonicalName())) {
						tl(3, "document.addField(\"", entityVar, "_suggested", "\", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(", entityVar, "));");
					}
					else if(entityCanonicalName.toString().equals(LocalDateTime.class.getCanonicalName())) {
						tl(3, "document.addField(\"", entityVar, "_suggested", "\", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(", entityVar, ".atOffset(ZoneOffset.UTC)));");
					}
					else if(entitySimpleName.toString().equals("LocalDate")) {
						tl(3, "document.addField(\"", entityVar, "_suggested", "\", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(", entityVar, ".atStartOfDay(ZoneId.of(\"Z\"))));");
					}
					else {
						tl(3, "document.addField(\"", entityVar, "_suggested", "\", ", entityVar, ");");
					}
				}
				if(entityText && entityLanguage != null) {
					if("frFR".equals(entityLanguage) || "esES".equals(entityLanguage)) {
						if(entitySimpleName.equals("List") || entitySimpleName.equals("ArrayList") || entitySimpleName.equals("Set") || entitySimpleName.equals("HashSet")) {
							tl(3, "for(", entitySimpleNameCompleteGeneric, " o : ", entityVar, ") {");
							tl(4, "document.addField(\"", entityVar, "_text_", entityLanguage, "\", o", "String".equals(entitySimpleNameCompleteGeneric) ? "" : ".toString()", ");");
							tl(3, "}");
						}
						else {
							tl(3, "document.addField(\"", entityVar, "_text_", entityLanguage, "\", ", entityVar, "", "String".equals(entitySimpleNameCompleteGeneric) ? "" : ".toString()", ");");
						}
					}
					else {
						if(entitySimpleName.equals("List") || entitySimpleName.equals("ArrayList") || entitySimpleName.equals("Set") || entitySimpleName.equals("HashSet")) {
							tl(3, "for(", entitySimpleNameCompleteGeneric, " o : ", entityVar, ") {");
							tl(4, "document.addField(\"", entityVar, "_text_enUS\", o", "String".equals(entitySimpleNameCompleteGeneric) ? "" : ".toString()", ");");
							tl(3, "}");
						}
						else {
							tl(3, "document.addField(\"", entityVar, "_text_enUS", "\", ", entityVar, "", "String".equals(entitySimpleNameCompleteGeneric) ? "" : ".toString()", ");");
						}
					}
				}
	
				if(entitySimpleName != null && entityIndexed) {
					// indexe
					if(entitySimpleName.equals("Chain")) {
						tl(3, "document.addField(\"", entityVar, "_indexed", entityTypeSuffix, "\", ", entityVar, ");");
					}
					else if(entitySimpleName.equals("Timestamp")) {
						tl(3, "document.addField(\"", entityVar, "_indexed", entityTypeSuffix, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss'Z'\").format(java.time.ZonedDateTime.ofInstant(", entityVar, ".toLocalDateTime(), java.time.OffsetDateTime.now().getOffset(), ZoneId.of(\"Z\"))));");
					}
					else if(entityCanonicalName.toString().equals(ZonedDateTime.class.getCanonicalName())) {
						tl(3, "document.addField(\"", entityVar, "_indexed", entityTypeSuffix, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss'Z'\").format(ZonedDateTime.ofInstant(", entityVar, ".toInstant(), ZoneId.of(\"UTC\"))));");
					}
					else if(entityCanonicalName.toString().equals(LocalDateTime.class.getCanonicalName())) {
						tl(3, "document.addField(\"", entityVar, "_indexed", entityTypeSuffix, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss'Z'\").format(", entityVar, ".atOffset(ZoneOffset.UTC)));");
					}
					else if(entitySimpleName.toString().equals("LocalDate")) {
						tl(3, "document.addField(\"", entityVar, "_indexed", entityTypeSuffix, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss'Z'\").format(", entityVar, ".atStartOfDay(ZoneId.of(\"Z\"))));");
					}
					else if(entitySimpleName.equals("List") || entitySimpleName.equals("ArrayList") || entitySimpleName.equals("Set") || entitySimpleName.equals("HashSet")) {
						tl(3, "for(", entityCanonicalNameGeneric, " o : ", entityVar, ") {");
						tl(4, "document.addField(\"", entityVar, "_indexed", entityTypeSuffix, "\", o);");
						tl(3, "}");
					}
					else {
						tl(3, "document.addField(\"", entityVar, "_indexed", entityTypeSuffix, "\", ", entityVar, ");");
					}
				}
	
				if(entityStored) {
					// stocke
					if(entitySimpleName.equals("Chain")) {
						tl(3, "document.addField(\"", entityVar, "_stored", entityTypeSuffix, "\", ", entityVar, ");");
					}
					else if(entitySimpleName.equals("Timestamp")) {
						tl(3, "document.addField(\"", entityVar, "_stored", entityTypeSuffix, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss'Z'\").format(java.time.ZonedDateTime.ofInstant(", entityVar, ".toLocalDateTime(), java.time.OffsetDateTime.now().getOffset(), ZoneId.of(\"Z\"))));");
					}
					else if(entityCanonicalName.toString().equals(ZonedDateTime.class.getCanonicalName())) {
						tl(3, "document.addField(\"", entityVar, "_stored", entityTypeSuffix, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss'Z'\").format(ZonedDateTime.ofInstant(", entityVar, ".toInstant(), ZoneId.of(\"UTC\"))));");
					}
					else if(entityCanonicalName.toString().equals(LocalDateTime.class.getCanonicalName())) {
						tl(3, "document.addField(\"", entityVar, "_stored", entityTypeSuffix, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss'Z'\").format(", entityVar, ".atOffset(ZoneOffset.UTC)));");
					}
					else if(entitySimpleName.toString().equals("LocalDate")) {
						tl(3, "document.addField(\"", entityVar, "_stored", entityTypeSuffix, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss'Z'\").format(", entityVar, ".atStartOfDay(ZoneId.of(\"Z\"))));");
					}
					else if(entitySimpleName.equals("List") || entitySimpleName.equals("ArrayList") || entitySimpleName.equals("Set") || entitySimpleName.equals("HashSet")) {
						tl(3, "for(", entityCanonicalNameGeneric, " o : ", entityVar, ") {");
						tl(4, "document.addField(\"", entityVar, "_stored", entityTypeSuffix, "\", o);");
						tl(3, "}");
					}
					else {
						tl(3, "document.addField(\"", entityVar, "_stored", entityTypeSuffix, "\", ", entityVar, ");");
					}
				}
				tl(2, "}");
			}
	
			/////////////////
			// codeObtenir //
			/////////////////
			o = wObtain;
			if(classExtendsBase || classIsBase) {
				tl(3, "case \"", entityVar, "\":");
				tl(4, "return o", classSimpleName, ".", entityVar, ";");
			}	
	
			///////////////////
			// codeAttribuer //
			///////////////////
			o = wAttribute;
			if((classExtendsBase || classIsBase) && entityAttribute) {
				tl(3, "case \"", entityVar, "\":");
				if(StringUtils.equals(entityCanonicalName, List.class.getCanonicalName()) || StringUtils.equals(entityCanonicalName, ArrayList.class.getCanonicalName()))
					tl(4, "o", classSimpleName, ".add", entityVarCapitalized, "((", entitySimpleNameCompleteGeneric, ")val);");
				else
					tl(4, "o", classSimpleName, ".set", entityVarCapitalized, "((", entitySimpleNameComplete, ")val);");
				tl(4, "return val;");
			}	
	
			/////////////
			// ", str_define(languageName), " //
			/////////////
			o = wDefine;
			
			if(classSaved && BooleanUtils.isTrue(entityDefined)) {
					tl(3, "case \"", entityVar, "\":");
					if(StringUtils.equals(entityCanonicalName, List.class.getCanonicalName()) || StringUtils.equals(entityCanonicalName, ArrayList.class.getCanonicalName())) {
						tl(4, "add", entityVarCapitalized, "(val);");
						tl(4, "if(!", str_saves(languageName), "", classSimpleName, ".contains(var))");
						tl(5, "", str_saves(languageName), "", classSimpleName, ".add(var);");
					}
					else {
						tl(4, "set", entityVarCapitalized, "(val);");
						tl(4, "", str_saves(languageName), "", classSimpleName, ".add(var);");
					}
					tl(4, "return val;");
			}	
	
			/////////////
			// codePut //
			/////////////
			o = wPut;
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
			// codePeupler //
			/////////////////
			o = wPopulate;
			if(classSaved) {
	//							String nomChamp = entityVar.toString();
	//							String varCrypte = entityVarEncrypted.toString();
	//							String varStocke = entityVarStored.toString();
	//							String varSuggere = entityVarSuggested.toString();
	//							String varIncremente = entityVarIncremented.toString();
	//							String varCleUnique = entityVarCleUniqueActuel.toString();
				if(entityEncrypted || entityStored || entityUniqueKey || entitySuggested || entityIncremented) {
					tl(0);
	
//					if(entitySuggested) {
//						tl(3, "if(", str_saves(languageName), "", classSimpleName, ".contains(\"", entityVar, "\")) {");
//						tl(4, entitySolrSimpleName, " ", entityVar, " = (", entitySolrSimpleName, ")solrDocument.get(\"", entityVar, "_suggested", "\");");
//						tl(4, "o", classSimpleName, ".set", entityVarCapitalized, "(", entityVar, ");");
//						tl(3, "}");
//					}
//					else if(entityIncremented) {
//						tl(3, "if(", str_saves(languageName), "", classSimpleName, ".contains(\"", entityVar, "\")) {");
//						tl(4, entitySolrSimpleName, " ", entityVar, " = (", entitySolrSimpleName, ")solrDocument.get(\"", entityVar, "_incremented", "\");");
//						tl(4, "o", classSimpleName, ".set", entityVarCapitalized, "(", entityVar, ");");
//						tl(3, "}");
//					}
//					else if(entityUniqueKey) {
					if(entityUniqueKey) {
						tl(3, entitySolrSimpleName, " ", entityVar, " = (", entitySolrSimpleName, ")solrDocument.get(\"", entityVar, "\");");
						tl(3, "o", classSimpleName, ".set", entityVarCapitalized, "(", entityVar, ");");
					}
					else if(entityPrimaryKey) {
						tl(3, entitySolrSimpleName, " ", entityVar, " = (", entitySolrSimpleName, ")solrDocument.get(\"", entityVar, "_stored", entityTypeSuffix, "\");");
						tl(3, "o", classSimpleName, ".set", entityVarCapitalized, "(", entityVar, ");");
					}
					else if(entityEncrypted) {
						tl(3, "if(", str_saves(languageName), "", classSimpleName, ".contains(\"", entityVar, "\")) {");
						if(siteEncrypted)
							tl(4, entitySolrSimpleName, " ", entityVar, " = siteRequest.deencryptStr((", entitySolrSimpleName, ")solrDocument.get(\"", entityVar, "_encrypted", entityTypeSuffix, "\"));");
						else
							tl(4, entitySolrSimpleName, " ", entityVar, " = (", entitySolrSimpleName, ")solrDocument.get(\"", entityVar, "_encrypted", entityTypeSuffix, "\");");
						tl(4, "o", classSimpleName, ".set", entityVarCapitalized, "(", entityVar, ");");
						tl(3, "}");
					}
					else if(entityAttribute) {
						tl(3, entitySolrSimpleName, " ", entityVar, " = (", entitySolrSimpleName, ")solrDocument.get(\"", entityVar, "_stored", entityTypeSuffix, "\");");
						tl(3, "if(", entityVar, " != null)");
						if(StringUtils.contains(entitySolrCanonicalName, "<"))
							tl(4, "o", classSimpleName, ".", entityVar, ".addAll(", entityVar, ");");
						else
							tl(4, "o", classSimpleName, ".set", entityVarCapitalized, "(", entityVar, ");");
					}
					else {
						tl(3, "if(", str_saves(languageName), "", classSimpleName, ".contains(\"", entityVar, "\")) {");
						tl(4, entitySolrSimpleName, " ", entityVar, " = (", entitySolrSimpleName, ")solrDocument.get(\"", entityVar, "_stored", entityTypeSuffix, "\");");
						tl(4, "if(", entityVar, " != null)");
						if(StringUtils.contains(entitySolrCanonicalName, "<"))
							tl(5, "o", classSimpleName, ".", entityVar, ".addAll(", entityVar, ");");
						else
							tl(5, "o", classSimpleName, ".set", entityVarCapitalized, "(", entityVar, ");");
						tl(3, "}");
					}
	
				}
			}	
	
			/////////////////
			// codeStocker //
			/////////////////
			o = wStore;
			if(entityEncrypted || entityStored || entityUniqueKey || entitySuggested || entityIncremented || entityText) {
				tl(0);

//				if(entityText) {
//					if("frFR".equals(languageName) || "esES".equals(languageName))
//						tl(2, entitySolrSimpleName, " ", entityVar, " = (", entitySolrSimpleName, ")solrDocument.get(\"", entityVar, "_text_", languageName, "\");");
//					else
//						tl(2, entitySolrSimpleName, " ", entityVar, " = (", entitySolrSimpleName, ")solrDocument.get(\"", entityVar, "_text_enUS\");");
//					tl(2, "o", classSimpleName, ".set", entityVarCapitalized, "(", entityVar, ");");
//				}
//				else if(entitySuggested) {
//					tl(2, entitySolrSimpleName, " ", entityVar, " = (", entitySolrSimpleName, ")solrDocument.get(\"", entityVar, "_suggested", "\");");
//					tl(2, "o", classSimpleName, ".set", entityVarCapitalized, "(", entityVar, ");");
//				}
//				else if(entityIncremented) {
//					tl(2, entitySolrSimpleName, " ", entityVar, " = (", entitySolrSimpleName, ")solrDocument.get(\"", entityVar, "_incremented", "\");");
//					tl(2, "o", classSimpleName, ".set", entityVarCapitalized, "(", entityVar, ");");
//				}
//				else if(entityUniqueKey) {
				if(entityUniqueKey) {
					tl(2, entitySolrSimpleName, " ", entityVar, " = (", entitySolrSimpleName, ")solrDocument.get(\"", entityVar, "\");");
					tl(2, "o", classSimpleName, ".set", entityVarCapitalized, "(", entityVar, ");");
				}
				else if(entityEncrypted) {
					if(siteEncrypted)
						tl(2, entitySolrSimpleName, " ", entityVar, " = siteRequest.deencryptStr((", entitySolrSimpleName, ")solrDocument.get(\"", entityVar, "_encrypted", entityTypeSuffix, "\"));");
					else
						tl(2, entitySolrSimpleName, " ", entityVar, " = (", entitySolrSimpleName, ")solrDocument.get(\"", entityVar, "_encrypted", entityTypeSuffix, "\");");
					tl(2, "o", classSimpleName, ".set", entityVarCapitalized, "(", entityVar, ");");
				}
				else {
					tl(2, entitySolrSimpleName, " ", entityVar, " = (", entitySolrSimpleName, ")solrDocument.get(\"", entityVar, "_stored", entityTypeSuffix, "\");");
					tl(2, "if(", entityVar, " != null)");
					if(StringUtils.contains(entitySolrCanonicalName, "<"))
						tl(3, "o", classSimpleName, ".", entityVar, ".addAll(", entityVar, ");");
					else
						tl(3, "o", classSimpleName, ".set", entityVarCapitalized, "(", entityVar, ");");
				}

			}
	
			/////////////////
			// codeApiChamps //
			/////////////////
			o = wApiEntities;
	//		l();
	//		tl(1, "public static final String ENTITY_VAR_", entityVar, " = \"", entityVar, "\";");
	//		if(classIndexed) {
	//			if(entityIndexed)
	//				tl(1, "public static final String ENTITY_VAR_INDEXED_", entityVar, " = \"", entityVar, "_indexed", entityTypeSuffix, "\";");
	//			if(entityStored)
	//				tl(1, "public static final String ENTITY_VAR_STORED_", entityVar, " = \"", entityVar, "_stored", entityTypeSuffix, "\";");
	//			if(entityEncrypted)
	//				tl(1, "public static final String ENTITY_VAR_ENCRYPTED_", entityVar, " = \"", entityVar, "_encrypted", entityTypeSuffix, "\";");
	//		}
	//		if(entityAttribute)
	//			tl(1, "public static final String ENTITY_VAR_", entityVar, "_ATTRIBUTE_", entityAttributeSimpleName, "_", entityAttributeVar, " = \"", entityAttributeVar, "\";");
	
			////////////////////////
			// codeApiGenererPost //
			////////////////////////
			o = wApiGeneratePost;
	
			Integer tBase = 3;
	//		if(classRolesFound) {
	//			tBase = 6;
	//		}
	//		else {
	//			tBase = 4;
	//		}
			if(classSaved && BooleanUtils.isTrue(entityDefined)) {
				tl(tBase + 2, "case \"", entityVar, "\":");
				tl(tBase + 3, "postSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_setD);");
				tl(tBase + 3, "postSqlParams.addAll(Arrays.asList(\"", entityVar, "\", jsonObject.get", entitySimpleNameVertxJson, "(", str_entity(languageName), "Var), ", classVarPrimaryKey, "));");
				tl(tBase + 3, "break;");
			}	
			if(classSaved && BooleanUtils.isTrue(entityAttribute)) {
				tl(tBase + 2, "case \"", entityVar, "\":");
				tl(tBase + 3, "postSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_addA);");
				if(StringUtils.compare(entityVar, entityAttributeVar) < 0)
					tl(tBase + 3, "postSqlParams.addAll(Arrays.asList(\"", entityVar, "\", jsonObject.getLong(", str_entity(languageName), "Var), \"", entityAttributeVar, "\", ", classVarPrimaryKey, "));");
				else
					tl(tBase + 3, "postSqlParams.addAll(Arrays.asList(\"", entityAttributeVar, "\", ", classVarPrimaryKey, ", \"", entityVar, "\", jsonObject.getLong(", str_entity(languageName), "Var)));");
				tl(tBase + 3, "break;");
			}	
	
			///////////////////////
			// codeApiGenererPut //
			///////////////////////
			o = wApiGeneratePut;
	
			tBase = 0;
			if(classRolesFound) {
				tBase = 6;
			}
			else {
				tBase = 4;
			}
			if(classSaved && BooleanUtils.isTrue(entityDefined)) {
				tl(tBase + 6, "case \"", entityVar, "\":");
				tl(tBase + 7, "putSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_setD);");
				tl(tBase + 7, "putSqlParams.addAll(Arrays.asList(\"", entityVar, "\", requestJson.get", entitySimpleNameVertxJson, "(", str_entity(languageName), "Var), putPk));");
				tl(tBase + 7, "break;");
			}	
	
			if(entityDefined) {
		
				//////////////
				// hashCode //
				//////////////
		
				if(entityIndex > 0) 
					wHashCode.s(", ");
				wHashCode.s(entityVar);
		
				////////////
				// equals //
				////////////
		
				if(entityIndex > 0) 
					wEquals.l().t(4, "&& ");
				wEquals.s("Objects.equals( " + entityVar + ", that." + entityVar + " )");
		
				//////////////
				// toString //
				//////////////
		
				wToString.tl(2, "sb.append( \"" + (entityIndex > 0 ? ", " : "") + entityVar + ": " + ("String".equals(entitySimpleNameComplete) ? "\\\"" : "") + "\" ).append(" + entityVar + "" + ("String".equals(entitySimpleNameComplete) ? ").append( \"\\\"\" " : "") + ");");
		
				entityIndex++;
			}
		}
	}

	public void  genCodeClassEnd(String languageName) throws Exception, Exception {
		//////////////////
		// codeInitLoin //
		//////////////////
		if(classInitDeep && classPartsSiteRequest != null) {
//			wInitDeep.tl(3, "", alreadyInitialized(languageName), "", classSimpleName, " = true;");
			wInitDeep.tl(1, "}");
			if(classInitDeep) {
				wInitDeep.l();
				wInitDeep.t(1);
				if(classExtendsBase)
					wInitDeep.s("@Override ");
				wInitDeep.s("public void ", str_initDeep(languageName), str_ForClass(languageName), "(", classPartsSiteRequest.simpleName(languageName), " ", str_siteRequest(languageName), "_)");
				if(classInitDeepExceptions.size() > 0) {
					wInitDeep.s(" throws ");
					for(int i = 0; i < classInitDeepExceptions.size(); i++) {
						String classInitDeepException = classInitDeepExceptions.get(i);
						String classInitDeepExceptionSimpleName = StringUtils.substringAfterLast(classInitDeepException, ".");
						if(i > 0)
							wInitDeep.s(", ");
						wInitDeep.s(classInitDeepExceptionSimpleName);
					}
				}
				wInitDeep.l(" {");
				wInitDeep.tl(2, str_initDeep(languageName), classSimpleName, "(", str_siteRequest(languageName), "_);");
				wInitDeep.tl(1, "}");  
			}
		}

		/////////////////////
		// codeSiteRequest //
		/////////////////////
		if(classContientSiteRequest && classInitDeep && classPartsSiteRequest != null) {
			o = wSiteRequest;
			tl(1, "}");
			l();
			tl(1, "public void ", str_siteRequest(languageName), str_ForClass(languageName), "(", classPartsSiteRequest.simpleName(languageName), " ", str_siteRequest(languageName), "_) {");
			tl(2, str_siteRequest(languageName), classSimpleName, "(", str_siteRequest(languageName), "_);");
			tl(1, "}");  
		}

		/////////////////
		// codeObtenir //
		/////////////////
		o = wObtain;
		if(classInitDeep && (classExtendsBase || classIsBase)) {
			tl(3, "default:");

			if(classIsBase)
				tl(4, "return null;");
			else
				tl(4, "return super.", str_obtain(languageName), classSuperSimpleNameGeneric, "(var);");

			tl(2, "}");
			tl(1, "}");
		}	

		///////////////////
		// codeAttribuer //
		///////////////////
		o = wAttribute;
		if(classInitDeep && (classExtendsBase || classIsBase)) {
			tl(3, "default:");

			if(classIsBase)
				tl(4, "return null;");
			else
				tl(4, "return super.", str_attribute(languageName), classSuperSimpleNameGeneric, "(var, val);");

			tl(2, "}");
			tl(1, "}");

		}	

		/////////////
		// codePut //
		/////////////
		o = wPut;
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

		wInitDeep.flushClose();
		wSiteRequest.flushClose();
		wIndex.flushClose();
		wObtain.flushClose();
		wAttribute.flushClose();
		wPut.flushClose();
		wPopulate.flushClose();
		wStore.flushClose();
		wExists.flushClose();
		wSaves.flushClose();
		wDefine.flushClose();
		wApiEntities.flushClose();
		wPageEntities.flushClose();
		wPageGet.flushClose();
		wHashCode.flushClose();
		wToString.flushClose();
		wEquals.flushClose();

		o = writerGenClass;

		if(BooleanUtils.isTrue(classInitDeep) && classPartsSiteRequest != null) {
			s(wInitDeep.toString());
			s(wSiteRequest.toString());
			s(wObtain.toString());
			s(wAttribute.toString());
		}


		if(classInitDeep && (classExtendsBase || classIsBase)) {
			l(); 
			tl(1, "/////////////");
			tl(1, "// ", str_define(languageName), " //");
			tl(1, "/////////////");
			tl(0);
			t(1);
			if(!classIsBase)
				s("@Override ");
			s("public boolean ", str_define(languageName), str_ForClass(languageName), "(String var, String val)");
			if(classInitDeepExceptions.size() > 0) {
				s(" throws ");
				for(int i = 0; i < classInitDeepExceptions.size(); i++) {
					String classInitDeepException = classInitDeepExceptions.get(i);
					String classInitDeepExceptionSimpleName = StringUtils.substringAfterLast(classInitDeepException, ".");
					if(i > 0)
						s(", ");
					s(classInitDeepExceptionSimpleName);
				}
			}
			l(" {");
			tl(2, "String[] vars = StringUtils.split(var, \".\");");
			tl(2, "Object o = null;");
			tl(2, "if(val != null) {");
			tl(3, "for(String v : vars) {");
			tl(4, "if(o == null)");
			tl(5, "o = ", str_define(languageName), "", classSimpleName, "(v, val);");
			tl(4, "else if(o instanceof Cluster) {");
			tl(5, "Cluster cluster = (Cluster)o;");
			tl(5, "o = cluster.", str_define(languageName), str_ForClass(languageName), "(v, val);");
			tl(4, "}");
			tl(3, "}");
			tl(2, "}");
			tl(2, "return o != null;");
			tl(1, "}");
			tl(1, "public Object ", str_define(languageName), "", classSimpleName, "(String var, String val) {");
			tl(2, "switch(var) {");
			s(wDefine.toString());
			tl(3, "default:");

			if(classIsBase)
				tl(4, "return null;");
			else
				tl(4, "return super.", str_define(languageName), "", classSuperSimpleNameGeneric, "(var, val);");

			tl(2, "}");
			tl(1, "}");
		}

		if(classSaved) {
			l(); 
			tl(1, "/////////////////");
			tl(1, "// ", str_saves(languageName), " //");
			tl(1, "/////////////////");
			tl(0);
			tl(1, "protected List<String> ", str_saves(languageName), "", classSimpleName, " = new ArrayList<String>();");
		}

		/////////////////
		// codePeupler //
		/////////////////
		if(classSaved) {
			l(); 
			tl(1, "/////////////");
			tl(1, "// ", str_populate(languageName), " //");
			tl(1, "/////////////");
			tl(0);
			t(1);
			if(BooleanUtils.isTrue(classExtendsBase))
				s("@Override ");
			l("public void ", str_populate(languageName), str_ForClass(languageName), "(SolrDocument solrDocument) {");
			if(classSaved) {
			tl(2, str_populate(languageName), classSimpleName, "(solrDocument);");
			}
			tl(1, "}");
			tl(1, "public void ", str_populate(languageName), classSimpleName, "(SolrDocument solrDocument) {");
			tl(2, classSimpleName, " o", classSimpleName, " = (", classSimpleName, ")this;");
			tl(2, "", str_saves(languageName), "", classSimpleName, " = (List<String>)solrDocument.get(\"", str_saves(languageName), "", classSimpleName, "_stored_strings\");");
			tl(2, "if(", str_saves(languageName), "", classSimpleName, " != null) {");
			s(wPopulate.toString());
			tl(2, "}");
			if(BooleanUtils.isTrue(classExtendsBase)) {
				tl(0);
				tl(2, "super.", str_populate(languageName), classSuperSimpleNameGeneric, "(solrDocument);");
			}

			tl(1, "}");
		}	

		/////////////////
		// codeIndexer //
		/////////////////
		if(classImage) {
			l(); 
			tl(1, "///////////");
			tl(1, "// image //");
			tl(1, "///////////");
			tl(0);
			tl(1, "public static void image() {");
			tl(2, "try {");
			tl(3, "DefaultExecutor executor = new DefaultExecutor();");
			for(String classPageMethod : classApiMethods) {
	
				String classPagePathGen = (String)classDoc.get("classPagePathGen" + classPageMethod  + "_stored_string");
				String classPagePath = (String)classDoc.get("classPagePath" + classPageMethod  + "_stored_string");
				String classPagePathCss = (String)classDoc.get("classPagePathCss" + classPageMethod  + "_stored_string");
				String classPagePathJs = (String)classDoc.get("classPagePathJs" + classPageMethod  + "_stored_string");
				String classPageUriMethod = (String)classDoc.get("classApiUri" + classPageMethod + "_stored_string");
				String classPageLanguageName = (String)classDoc.get("classPageLanguageName" + classPageMethod + "_stored_string");
				String classPageSimpleName = (String)classDoc.get("classPageSimpleName" + classPageMethod  + "_stored_string");
		
				if(classPagePathGen != null) {
			
					tl(3, "{");
					tl(4, "new File(\"", appPath, "-static/png", StringUtils.substringBeforeLast(classPageUriMethod, "/"), "\").mkdirs();");
					tl(4, "executor.execute(CommandLine.parse(\"/usr/bin/CutyCapt --min-height=200 --url=", siteBaseUrl, classPageUriMethod, "?pageReview=true --out=", appPath, "-static/png", classPageUriMethod, "-999.png\"));");
					tl(4, "BufferedImage img = ImageIO.read(new File(\"", appPath, "-static/png", classPageUriMethod, "-999.png\"));");
					tl(4, "System.out.println(\"", classPageSimpleName, "\");");
					tl(4, "System.out.println(\" * ImageWidth.", classPageLanguageName, ": \" + img.getWidth());");
					tl(4, "System.out.println(\" * ImageHeight.", classPageLanguageName, ": \" + img.getHeight());");
					tl(3, "}");
				}
			}
			tl(2, "} catch(Exception e) {");
			tl(3, "ExceptionUtils.rethrow(e);");
			tl(2, "}");
			tl(1, "}");
		}
		if(classIndexed && classPartsSiteRequest != null) {
			l(); 
			tl(1, "/////////////");
			tl(1, "// ", str_index(languageName), " //");
			tl(1, "/////////////");
			tl(0);
			tl(1, "public static void ", str_index(languageName), "() {");
			tl(2, "try {");
			tl(3, classPartsSiteRequest.simpleName(languageName), " ", str_siteRequest(languageName), " = new ", classPartsSiteRequest.simpleName(languageName), "();");
			tl(3, str_siteRequest(languageName), ".", str_initDeep(languageName), classPartsSiteRequest.simpleName(languageName), "();");
			tl(3, classPartsSiteContext.simpleName(languageName), " ", str_siteContext(languageName), " = new ", classPartsSiteContext.simpleName(languageName), "();");
			tl(3, str_siteContext(languageName), ".get", classPartsSiteConfig.simpleName(languageName), "().set", str_ConfigChemin(languageName), "(", q(configPath), ");");
			tl(3, str_siteContext(languageName), ".", str_initDeep(languageName), classPartsSiteContext.simpleName(languageName), "();");
			tl(3, str_siteRequest(languageName), ".set", str_SiteContext(languageName), "_(", str_siteContext(languageName), ");");
			tl(3, str_siteRequest(languageName), ".set", str_SiteConfig(languageName), "_(", str_siteContext(languageName), ".get", str_SiteConfig(languageName), "());");
			tl(3, "SolrQuery ", str_solrSearch(languageName), " = new SolrQuery();");
			tl(3, "", str_solrSearch(languageName), ".setQuery(\"*:*\");");
			tl(3, "", str_solrSearch(languageName), ".setRows(1);");
			tl(3, "", str_solrSearch(languageName), ".addFilterQuery(\"id:\" + ClientUtils.escapeQueryChars(\"", classCanonicalName, "\"));");
			tl(3, "QueryResponse ", str_searchResponse(languageName), " = ", str_siteRequest(languageName), ".get", str_SiteContext(languageName), "_().get", str_SolrClient(languageName), "().query(", str_solrSearch(languageName), ");");
			tl(3, "if(", str_searchResponse(languageName), ".getResults().size() > 0)");
			tl(4, str_siteRequest(languageName), ".set", str_SolrDocument(languageName), "(", str_searchResponse(languageName), ".getResults().get(0));");
			tl(3, classSimpleName, " o = new ", classSimpleName, "();");
			tl(3, "o.", str_siteRequest(languageName), classSimpleName, "(", str_siteRequest(languageName), ");");
			tl(3, "o.", str_initDeep(languageName), classSimpleName, "(", str_siteRequest(languageName), ");");
			tl(3, "o.", str_index(languageName), classSimpleName, "();");
			tl(2, "} catch(Exception e) {");
			tl(3, "ExceptionUtils.rethrow(e);");
			tl(2, "}");
			tl(1, "}");
			tl(0);
			if(classExtendsBase || classIsBase) {
				tl(0);
				t(1);
				if(!classIsBase)
					s("@Override ");
				l("public void ", str_index(languageName), str_ForClass(languageName), "() {");
				tl(2, str_index(languageName), classSimpleName, "();");
				tl(1, "}");
				tl(0);
				t(1);
				if(!classIsBase)
					s("@Override ");
				l("public void ", str_index(languageName), str_ForClass(languageName), "(SolrInputDocument document) {");
				tl(2, str_index(languageName), classSimpleName, "(document);");
				tl(1, "}");
			}
			l();
			tl(1, "public void ", str_index(languageName), classSimpleName, "(SolrClient solrClient) {");
			tl(2, "try {");
			tl(3, "SolrInputDocument document = new SolrInputDocument();");
			tl(3, str_index(languageName), classSimpleName, "(document);");
			tl(3, "solrClient.add(document);");
			tl(3, "solrClient.commit();");
			tl(2, "} catch(Exception e) {");
			tl(3, "ExceptionUtils.rethrow(e);");
			tl(2, "}");
			l("\t}");
			l();
			tl(1, "public void ", str_index(languageName), classSimpleName, "() {");
			tl(2, "try {");
			tl(3, "SolrInputDocument document = new SolrInputDocument();");
			tl(3, str_index(languageName), classSimpleName, "(document);");
			tl(3, "SolrClient solrClient = ", str_siteRequest(languageName), "_.get", str_SiteContext(languageName), "_().get", str_SolrClient(languageName), "();");
			tl(3, "solrClient.add(document);");
			tl(3, "solrClient.commit();");
			tl(2, "} catch(Exception e) {");
			tl(3, "ExceptionUtils.rethrow(e);");
			tl(2, "}");
			l("\t}");

			tl(0);
			tl(1, "public void ", str_index(languageName), classSimpleName, "(SolrInputDocument document) {");
			if(classSaved) {
				tl(2, "if(", str_saves(languageName), "", classSimpleName, " != null)");
				tl(3, "document.addField(\"", str_saves(languageName), "", classSimpleName, "_stored_strings\", ", str_saves(languageName), "", classSimpleName, ");");
				l();
			}
			s(wIndex.toString());
			if(classExtendsBase && !classIsBase) {
				tl(2, "super.", str_index(languageName), "", classSuperSimpleNameGeneric, "(document);");
				tl(0);
			}
			l("\t}");

			if(StringUtils.isNotEmpty(classVarUniqueKey)) {
				tl(0);
				tl(1, "public void ", str_unindex(languageName), "", classSimpleName, "() {");
				tl(2, "try {");
				tl(2, "", classPartsSiteRequest.simpleName(languageName), " ", str_siteRequest(languageName), " = new ", classPartsSiteRequest.simpleName(languageName), "();");
				tl(3, "", str_siteRequest(languageName), ".", str_initDeep(languageName), classPartsSiteRequest.simpleName(languageName), "();");
				tl(3, classPartsSiteContext.simpleName(languageName), " ", str_siteContext(languageName), " = new ", classPartsSiteContext.simpleName(languageName), "();");
				tl(3, "", str_siteContext(languageName), ".", str_initDeep(languageName), classPartsSiteContext.simpleName(languageName), "();");
				tl(3, str_siteRequest(languageName), ".set", str_SiteContext(languageName), "_(", str_siteContext(languageName), ");");
				tl(3, str_siteRequest(languageName), ".set", str_SiteConfig(languageName), "_(", str_siteContext(languageName), ".get", str_SiteConfig(languageName), "());");
				tl(3, str_initDeep(languageName), classSimpleName, "(", str_siteRequest(languageName), ");");
				tl(3, "SolrClient ", str_solrClient(languageName), " = ", str_siteContext(languageName), ".get", str_SolrClient(languageName), "();");
				tl(3, str_solrClient(languageName), ".deleteById(", classVarUniqueKey, ".toString());");
				tl(3, str_solrClient(languageName), ".commit();");
				tl(2, "} catch(Exception e) {");
				tl(3, "ExceptionUtils.rethrow(e);");
				tl(2, "}");
				tl(1, "}");
			}
		}

		/////////////////
		// codeStore //
		/////////////////
		if(classIndexed) {
			l(); 
			tl(1, "/////////////");
			tl(1, "// ", str_store(languageName), " //");
			tl(1, "/////////////");
			tl(0);
			t(1);
			if(BooleanUtils.isTrue(classExtendsBase))
				s("@Override ");
			l("public void ", str_store(languageName), str_ForClass(languageName), "(SolrDocument solrDocument) {");
			if(classIndexed) {
			tl(2, str_store(languageName), classSimpleName, "(solrDocument);");
			}
			tl(1, "}");
			tl(1, "public void ", str_store(languageName), classSimpleName, "(SolrDocument solrDocument) {");
			tl(2, classSimpleName, " o", classSimpleName, " = (", classSimpleName, ")this;");
			s(wStore.toString());
			if(BooleanUtils.isTrue(classExtendsBase)) {
				tl(0);
				tl(2, "super.", str_store(languageName), classSuperSimpleNameGeneric, "(solrDocument);");
			}

			tl(1, "}");
		}	

		if(classWriteMethods != null) {
			for(int i = 0; i < classWriteMethods.size(); i++) {
				String classWriteMethod = classWriteMethods.get(i);
				l();
				String strComment = "///" + String.join("", Collections.nCopies(classWriteMethod.length(), "/")) + "///";
				tl(1, strComment);
				tl(1, "// ", classWriteMethod, " //");
				tl(1, strComment);
				tl(0);
				t(1);

				if(classSuperWriteMethods != null && classSuperWriteMethods.contains(classWriteMethod)) {
					s("@Override ");
					l("public void ", classWriteMethod, "() {");
					tl(2, classWriteMethod, classSimpleName, "();");
					tl(2, "super.", classWriteMethod, "();");
				}
				else {
					l("public void ", classWriteMethod, "() {");
					tl(2, classWriteMethod, classSimpleName, "();");
				}

				tl(1, "}");
				l();
				tl(1, "public void ", classWriteMethod, classSimpleName, "() {");
				s(classWriteWriters.get(i));
				tl(1, "}");
	//				tl(1, "public void ", siteWriteMethod, "Before() {");
	//				tl(2, siteWriteMethod, classSimpleName, "Before();");
	//				if(BooleanUtils.isTrue(classExtendsBase)) {
	//					tl(2, "super.", siteWriteMethod, classSuperSimpleNameGeneric, "Before();");
	//				}
	//				tl(1, "}");
	//				tl(1, "public void ", siteWriteMethod, "Middle() {");
	//				tl(2, siteWriteMethod, classSimpleName, "Middle();");
	//				if(BooleanUtils.isTrue(classExtendsBase)) {
	//					tl(2, "super.", siteWriteMethod, classSuperSimpleNameGeneric, "Middle();");
	//				}
	//				tl(1, "}");
	//				tl(1, "public void ", siteWriteMethod, "After() {");
	//				tl(2, siteWriteMethod, classSimpleName, "After();");
	//				if(BooleanUtils.isTrue(classExtendsBase)) {
	//					tl(2, "super.", siteWriteMethod, classSuperSimpleNameGeneric, "After();");
	//				}
	//				tl(1, "}");
	//				tl(1, "public void ", siteWriteMethod, classSimpleName, "Before() {");
	////				s(wStore.toString());
	//				tl(1, "}");
	//				tl(1, "public void ", siteWriteMethod, classSimpleName, "Middle() {");
	////				s(wStore.toString());
	//				tl(1, "}");
	//				tl(1, "public void ", siteWriteMethod, classSimpleName, "After() {");
	////				s(wStore.toString());
	//				tl(1, "}");
			}
		}

//		s(wExists.toString());

		/////////////////////
		// codeSauvegarder //
		/////////////////////
//		if(classSaved) {
//			l(); 
//			tl(1, "/////////////////");
//			tl(1, "// save //");
//			tl(1, "/////////////////");
//			tl(0);
//			t(1);
//			if(classExtendsBase)
//				s("@Override ");
//			l("public void saveForClass(", classPartsSiteRequest.simpleName(languageName), " siteRequest_) {");
//			tl(2, QueryRunner.class.getCanonicalName(), " coureur = new ", QueryRunner.class.getCanonicalName(), "(siteRequest.", classPartsSiteContext.simpleName(languageName), ".sourceDonnees);");
//			tl(2, ArrayListHandler.class.getCanonicalName(), " gestionnaireListe = new ", ArrayListHandler.class.getCanonicalName(), "();");
//			tl(2, "String pkStr = siteRequest_.getRequeteServeur().getParam(\"pk\");");
//			tl(2, "pk = ", StringUtils.class.getCanonicalName(), ".isNumeric(pkStr) ? Long.parseLong(pkStr) : null;");
//			tl(2, "utilisateurId = siteRequest.utilisateurId;");
//			tl(2, "String nomCanonique = getClass().getCanonicalName();");
//			tl(2, "modifie = ", LocalDateTime.class.getCanonicalName(), ".now();");
//			tl(2, Timestamp.class.getCanonicalName(), " horodatage = java.sql.Timestamp.valueOf(modifie);");
//
//			tl(2);
//			tl(2, "if(pk == null) {");
//			tl(3, "String sql = \"insert into objet(nom_canonique, id_utilisateur, cree, modifie) values(?, ?, ?, ?) returning pk\";");
//			tl(3, List.class.getCanonicalName(), "<Object[]> resultats = coureur.insert(sql, gestionnaireListe /*insert into objet(nom_canonique, id_utilisateur, cree, modifie) values(*/, nomCanonique, siteRequest.utilisateurId, horodatage, horodatage /*) returning pk, cree*/);");
//			tl(3, "pk = (Long)resultats.get(0)[0];");
//			tl(3, "cree = modifie;");
//			tl(2, "}");
//			tl(2, "else {");
//			tl(3, "String sql = \"update objet set modifie=? where objet.pk=? and objet.id_utilisateur=? and objet.nom_canonique=? returning cree\";");
//			tl(3, List.class.getCanonicalName(), "<Object[]> resultats = coureur.query(sql, gestionnaireListe /*update objet set modifie=*/, horodatage /* where objet.pk=*/, pk /* and objet.id_utilisateur=*/, siteRequest.utilisateurId /* and objet.nom_canonique=*/, nomCanonique /* returning cree*/);");
//			tl(3, "if(resultats.size() == 0)");
//			t(4, "throw new Exception(\"");
//			s("L'objet avec le pk \" + pk + \" et nom canonique \" + pk + \" pour utilisateur \" + siteRequest.utilisateurId + \" \" + siteRequest.utilisateurNom + \" n'existe pas dejà. ");
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
////							tl(4, "String valeur = siteRequest.decrypterStr((String)objets[1]);");
////						else
////							tl(4, "String valeur = (String)objets[1];");
////						tl(4, "", str_define(languageName), "(chemin, valeur);");
////						tl(4, "", str_saves(languageName), "", classSimpleName, ".add(chemin);");
////						tl(3, "}");
////						tl(2, "}");
//			tl(0);
////						tl(2, "{");
//			tl(2, "String sqlInsertP = \"insert into p(chemin, valeur, pk_objet) values(?, ?, ?) on conflict(chemin, pk_objet) do update set valeur=? where p.chemin=? and p.pk_objet=?\";");
//			tl(2, "String sqlInsertA = \"insert into a(champ1, pk1, champ2, pk2) values(?, ?, ?, ?) on conflict  do nothing\";");
//			tl(2, "String sqlDeleteP = \"delete from p where chemin=? and pk_objet=?\";");
//			tl(2, "String sqlDeleteA = \"delete from a where champ1=? and pk1=? and champ2=? and pk2=?\";");
//			tl(2, "save", classSimpleName, "(siteRequest, sqlInsertP, sqlInsertA, sqlDeleteP, sqlDeleteA, gestionnaireListe, coureur);");
////						tl(2, "}");
//			tl(1, "}");
//			tl(1, "public void save", classSimpleName, "(", classPartsSiteRequest.simpleName(languageName), " siteRequest, String sqlInsertP, String sqlInsertA, String sqlDeleteP, String sqlDeleteA, ", ArrayListHandler.class.getCanonicalName(), " gestionnaireListe, ", QueryRunner.class.getCanonicalName(), " coureur) {");
//			s(wSave.toString());
//			if(classExtendsBase) {
//				tl(0);
//				tl(2, "super.save", classSuperSimpleNameGeneric + "(siteRequest, sqlInsertP, sqlInsertA, sqlDeleteP, sqlDeleteA, gestionnaireListe, coureur);");
//			}
//			tl(1, "}");
//		}	

		//////////////
		// hashCode //
		//////////////
		l(); 
		tl(1, "//////////////");
		tl(1, "// hashCode //");
		tl(1, "//////////////");
		l();
		tl(1, "@Override public int hashCode() {");
		t(2, "return Objects.hash(");
		if(BooleanUtils.isTrue(classExtendsBase)) {
			s("super.hashCode()");
			if(entityIndex > 0)
				s(", ");
		}
		s(wHashCode.toString());
		l(");");
		tl(1, "}");

		////////////
		// equals //
		////////////
		l(); 
		tl(1, "////////////");
		tl(1, "// equals //");
		tl(1, "////////////");
		l();
		tl(1, "@Override public boolean equals(Object o) {");
		tl(2, "if(this == o)");
		tl(3, "return true;");
		tl(2, "if(!(o instanceof ", classSimpleName, "))");
		tl(3, "return false;");
		tl(2, classSimpleName, " that = (", classSimpleName, ")o;");
		t(2, "return ");
		if(BooleanUtils.isTrue(classExtendsBase)) {
			s("super.equals(o)");
			if(entityIndex > 0) {
				l();
				t(4, "&& ");
			}
		}
		s(wEquals.toString());
		if(!BooleanUtils.isTrue(classExtendsBase) && entityIndex == 0)
			s("true");
		l(";");
		tl(1, "}");

		//////////////
		// toString //
		//////////////
		l(); 
		tl(1, "//////////////");
		tl(1, "// toString //");
		tl(1, "//////////////");
		l();
		tl(1, "@Override public String toString() {");
		tl(2, "StringBuilder sb = new StringBuilder();");
		if(BooleanUtils.isTrue(classExtendsBase)) 
			tl(2, "sb.append(super.toString() + \"\\n\");");
		tl(2, "sb.append(\"", classSimpleName, " {\");");
		s(wToString.toString());
		tl(2, "sb.append(\" }\");");
		tl(2, "return sb.toString();");
		tl(1, "}");

		if(!classVals.getEmpty()) {
			l();
			tl(1, "public static final String[] ", classSimpleName, "Vals", " = new String[] { ", classVals, " };");
		}

		l("}"); 

		System.out.println("Write: " + classPathGen); 
		writerGenClass.flushClose();
	}

	public String[] splitByCharacterTypeCamelCase(String str) {
        return splitByCharacterType(str, true);
    }

	public static String[] splitByCharacterType(String str, boolean camelCase) {
        if (str == null) {
            return null;
        }
        if (str.isEmpty()) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        final char[] c = str.toCharArray();
        final List<String> list = new ArrayList<>();
        int tokenStart = 0;
        int currentType = Character.getType(c[tokenStart]);
        for (int pos = tokenStart + 1; pos < c.length; pos++) {
            final int type = Character.getType(c[pos]);
            if (type == currentType) {
                continue;
            }
            if (camelCase && type == Character.DECIMAL_DIGIT_NUMBER && (currentType == Character.LOWERCASE_LETTER || currentType == Character.UPPERCASE_LETTER)) {
            	currentType = type;
                continue;
            }
            if (camelCase && type == Character.LOWERCASE_LETTER && currentType == Character.UPPERCASE_LETTER) {
                final int newTokenStart = pos - 1;
                if (newTokenStart != tokenStart) {
                    list.add(new String(c, tokenStart, newTokenStart - tokenStart));
                    tokenStart = newTokenStart;
                }
            } else {
                list.add(new String(c, tokenStart, pos - tokenStart));
                tokenStart = pos;
            }
            currentType = type;
        }
        list.add(new String(c, tokenStart, c.length - tokenStart));
        return list.toArray(new String[list.size()]);
    }
}
