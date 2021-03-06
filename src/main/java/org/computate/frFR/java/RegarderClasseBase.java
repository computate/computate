package org.computate.frFR.java; 

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.common.SolrDocument;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaType;

/**   
 * NomCanonique.enUS: org.computate.enUS.java.WatchClassBase
 */   
public class RegarderClasseBase extends ConfigSite {

	public String[] args;

	/**
	 * Var.enUS: _appPath
	 * r: appliChemin
	 * r.enUS: appPath
	 */ 
	@Override protected void _appliChemin() throws Exception {
		if(appliChemin == null) {
			appliChemin = System.getenv(str_appliChemin(langueNom)); 
			if(appliChemin == null)
				appliChemin = args[0]; 
		}
	}

	/**
	 * Var.enUS: _appPathVertx
	 * r: appliCheminVertx
	 * r.enUS: appPathVertx
	 */ 
	@Override protected void _appliCheminVertx() throws Exception {
		if(appliCheminVertx == null) {
			appliCheminVertx = System.getenv(str_appliChemin(langueNom) + "Vertx"); 
		}
	}

	/**
	 * Var.enUS: classAbsolutePath
	 */ 
	public String classeCheminAbsolu;
	/** 
	 * Var.enUS: _classAbsolutePath
	 * r: classeCheminAbsolu
	 * r.enUS: classAbsolutePath
	 */  
	protected void _classeCheminAbsolu() throws Exception {
		if(classeCheminAbsolu == null) {
			classeCheminAbsolu = System.getenv(str_classeCheminAbsolu(langueNom)); 
			if(classeCheminAbsolu == null)
				classeCheminAbsolu = args[1];
		}
	}
	
	/** 
	 * Var.enUS: appPaths
	 */
	public HashMap<String, String> appliChemins = new HashMap<String, String>(); 
	/** 
	 * Var.enUS: _appPaths
	 * r: autresLangues
	 * r.enUS: otherLanguages
	 * r: langueNom
	 * r.enUS: languageName
	 * r: appliCheminLange
	 * r.enUS: appPathLanguage
	 * r: appliNom
	 * r.enUS: appName
	 * r: appliCheminLangue
	 * r.enUS: appPathLanguage
	 * r: appliChemin
	 * r.enUS: appPath
	 */  
	protected void _appliChemins() throws Exception {
		for(String langueNom : toutesLangues) { 
			String appliCheminLangue = config.getString(appliNom + "." + str_appliChemin(langueNom) + "_" + langueNom); 
			if(StringUtils.isEmpty(appliCheminLangue)) {
				appliChemins.put(langueNom, appliChemin);
			}
			else {
				appliChemins.put(langueNom, appliCheminLangue);
			}
		}
	}
	
	/** 
	 * Var.enUS: appPathsVertx
	 */
	public HashMap<String, String> appliCheminsVertx = new HashMap<String, String>(); 
	/** 
	 * Var.enUS: _appPathsVertx
	 * r: autresLangues
	 * r.enUS: otherLanguages
	 * r: langueNom
	 * r.enUS: languageName
	 * r: appliCheminVertxLangue
	 * r.enUS: appPathVertxLanguage
	 * r: appliNom
	 * r.enUS: appName
	 * r: appliChemin
	 * r.enUS: appPath
	 */  
	protected void _appliCheminsVertx() throws Exception {
		for(String langueNom : toutesLangues) { 
			String appliCheminVertxLangue = config.getString(appliNom + "." + str_appliChemin(langueNom) + "Vertx_" + langueNom); 
			if(StringUtils.isEmpty(appliCheminVertxLangue)) {
				appliCheminsVertx.put(langueNom, appliCheminVertx);
			}
			else {
				appliCheminsVertx.put(langueNom, appliCheminVertxLangue);
			}
		}
	}

	/**  
	 * Var.enUS: classDocs
	 */
	public HashMap<String, SolrDocument> classeDocs = new HashMap<String, SolrDocument>();
	/**
	 * Var.enUS: _classDocs
	 */
	protected void _classeDocs() throws Exception {
	}

	/** 
	 * Var.enUS: classPartsGen
	 * r: ClasseParts
	 * r.enUS: ClassParts
	 */
	public HashMap<String, ClasseParts> classePartsGen = new HashMap<String, ClasseParts>();
	/**
	 * Var.enUS: _classPartsGen
	 */
	protected void _classePartsGen() throws Exception {
	}

	/**
	 * Var.enUS: builder
	 */ 
	public JavaProjectBuilder bricoleur;
	/**
	 * Var.enUS: _builder
	 * r: bricoleur
	 * r.enUS: builder
	 * r: cheminSource
	 * r.enUS: sourcePath
	 * r: toutCheminsSource
	 * r.enUS: allSourcePaths
	 * r: repertoireSource
	 * r.enUS: sourceDir
	 */
	protected void _bricoleur() throws Exception { 
		bricoleur = new JavaProjectBuilder();
		for(String cheminSource : toutCheminsSource) {
			File repertoireSource = new File(cheminSource);
			bricoleur.addSourceFolder(repertoireSource);
		}
	} 


	/**
	 * Var.enUS: initWatchClassBase
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: classeCheminAbsolu
	 * r.enUS: classAbsolutePath
	 * r: appliChemins
	 * r.enUS: appPaths
	 * r: appliCheminsVertx
	 * r.enUS: appPathsVertx
	 * r: classeDocs
	 * r.enUS: classDocs
	 * r: classeParts
	 * r.enUS: classParts
	 * r: bricoleur
	 * r.enUS: builder
	 */
	public void initRegarderClasseBase() throws Exception {
		initConfigSite();
		_classeCheminAbsolu();
		_appliChemin();
		_appliChemins();
		_appliCheminVertx();
		_appliCheminsVertx();
		_classeDocs();
		_classePartsGen();
		_bricoleur();
	} 

	////////////
	// etend //
	////////////
	
	/**
	 * Var.enUS: extendClass
	 * Param1.var.enUS: classQdox
	 * Param2.var.enUS: canonicalName
	 * r: resultat
	 * r.enUS: result
	 * r:etendClasse
	 * r.enUS: extendClass
	 * r: classeQdox
	 * r.enUS: classQdox
	 * r: nomCanonique
	 * r.enUS: canonicalName
	 */
	protected boolean etendClasse(JavaClass classeQdox, String nomCanonique) {
		boolean resultat = etendClasse(nomCanonique, classeQdox);
		return resultat;
	}
	
	/**
	 * Var.enUS: extendClass
	 * Param1.var.enUS: canonicalNameSearch
	 * Param2.var.enUS: canonicalNameActual
	 * r: bricoleur
	 * r.enUS: builder
	 * r:etendClasse
	 * r.enUS: extendClass
	 * r: classeQdox
	 * r.enUS: classQdox
	 * r: nomCanoniqueRecherche
	 * r.enUS: canonicalNameSearch
	 * r: nomCanoniqueActuel
	 * r.enUS: canonicalNameActual
	 */
	protected boolean etendClasse(String nomCanoniqueRecherche, String nomCanoniqueActuel) {
		JavaClass classeQdox = bricoleur.getClassByName(nomCanoniqueActuel);
		return etendClasse(nomCanoniqueRecherche, classeQdox);
	}

	/**
	 * Var.enUS: extendClass
	 * Param1.var.enUS: canonicalName
	 * Param2.var.enUS: classQdox
	 * r: bricoleur
	 * r.enUS: builder
	 * r:etendClasse
	 * r.enUS: extendClass
	 * r: classeQdox
	 * r.enUS: classQdox
	 * r: nomCanonique
	 * r.enUS: canonicalName
	 */
	protected boolean etendClasse(String nomCanonique, JavaClass classeQdox) {
		if(nomCanonique != null && classeQdox != null) {
			if(classeQdox.getCanonicalName().equals(Object.class.getCanonicalName()))
				return false;
			if(classeQdox.getCanonicalName().equals(nomCanonique))
				return true;
			else if(!classeQdox.equals(classeQdox.getSuperClass()))
				return etendClasse(nomCanonique, classeQdox.getSuperJavaClass());
		}
		return false;
	}

	//////////////
	// contient //
	//////////////
	
	/**
	 * Var.enUS: containsField
	 * Param1.var.enUS: qdoxSuperClassesAndMe
	 * Param2.var.enUS: fieldName
	 * r: classeQdox
	 * r.enUS: classQdox
	 * r: bricoleur
	 * r.enUS: builder
	 * r: classesSuperQdoxEtMoi
	 * r.enUS: qdoxSuperClassesAndMe
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: contientChamp
	 * r.enUS: containsField
	 */
	public Boolean contientChamp(List<JavaClass> classesSuperQdoxEtMoi, String nomChamp, Class<?> c) {
		JavaClass classeQdox = bricoleur.getClassByName(c.getCanonicalName());
		Boolean o = contientChamp(classesSuperQdoxEtMoi, nomChamp, classeQdox);
		return o;
	} 
	
	/**
	 * Var.enUS: containsField
	 * Param1.var.enUS: qdoxSuperClassesAndMe
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: paramsArray
	 * r: tableauParams
	 * r.enUS: paramsArray
	 * r: listeParams
	 * r.enUS: paramsList
	 * r: classesSuperQdoxEtMoi
	 * r.enUS: qdoxSuperClassesAndMe
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: classeSuper
	 * r.enUS: superClass
	 * r: methodeQdox
	 * r.enUS: qdoxMethod
	 * r: champQdox
	 * r.enUS: qdoxField
	 */   
	public Boolean contientChamp(List<JavaClass> classesSuperQdoxEtMoi, String nomChamp, JavaClass...tableauParams) {
		ArrayList<JavaType> listeParams = new ArrayList<JavaType>();
		Collections.addAll(listeParams, tableauParams);
		for(JavaClass classeSuper : classesSuperQdoxEtMoi) {
			JavaField champQdox = classeSuper.getFieldByName(nomChamp);
			JavaMethod methodeQdox = classeSuper.getMethod("_" + nomChamp, listeParams, false);
			Boolean o = champQdox != null || methodeQdox != null;
			if(o)
				return true;
		}
		return false;
	}

	/**
	 * Var.enUS: containsMethodAlone
	 * Param1.var.enUS: classQdox
	 * Param2.var.enUS: methodName
	 * Param3.var.enUS: paramsArray
	 * r: obtenirMethodeSeul
	 * r.enUS: obtainMethodAlone
	 * r: classeQdox
	 * r.enUS: classQdox
	 * r: nomMethode
	 * r.enUS: methodName
	 * r: tableauParams
	 * r.enUS: paramsArray
	 */
	public Boolean contientMethodeSeul(JavaClass classeQdox, String nomMethode, JavaClass...tableauParams) {
		JavaMethod o = obtenirMethodeSeul(classeQdox, nomMethode, tableauParams);
		return o != null;
	}

	/**
	 * Var.enUS: containsMethod
	 * Param1.var.enUS: classQdox
	 * Param2.var.enUS: methodName
	 * Param3.var.enUS: paramsArray
	 * r: obtenirMethode
	 * r.enUS: obtainMethod
	 * r: classeQdox
	 * r.enUS: classQdox
	 * r: nomMethode
	 * r.enUS: methodName
	 * r: tableauParams
	 * r.enUS: paramsArray
	 */
	public Boolean contientMethode(JavaClass classeQdox, String nomMethode, JavaClass...tableauParams) {
		JavaMethod o = obtenirMethode(classeQdox, nomMethode, tableauParams);
		return o != null;
	}

	/**
	 * Var.enUS: containsAttribute
	 * Param1.var.enUS: domainPackageName
	 * Param2.var.enUS: attributeName
	 * Param3.var.enUS: attributeClassQdox
	 * r: classeAttributQdox
	 * r.enUS: attributeClassQdox
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: nomAttribut
	 * r.enUS: attributeName
	 * r: attributQdox
	 * r.enUS: attributeQdox
	 * r: contientAttribut
	 * r.enUS: containsAttribute
	 */
	public Boolean contientAttribut(String nomEnsembleDomaine, String nomAttribut, JavaClass classeAttributQdox) {
		Boolean o = false;
		if(classeAttributQdox.getCanonicalName().startsWith(nomEnsembleDomaine.toString())) {
			JavaField attributQdox = classeAttributQdox.getFieldByName(nomAttribut);
			if(attributQdox == null) {
				o = contientAttribut(nomEnsembleDomaine, nomAttribut, classeAttributQdox.getSuperJavaClass());
			}
			else {
				o = true;
			}
		}
		else {
			o = false;
		}
		return o;
	}

	/////////////
	// obtenir //
	/////////////
	
	/**
	 * Var.enUS: obtainMethod
	 * Param1.var.enUS: classQdox
	 * Param2.var.enUS: methodName
	 * Param3.var.enUS: paramsArray
	 * r: listeParams
	 * r.enUS: paramsList
	 * r: tableauParams
	 * r.enUS: paramsArray
	 * r: nomMethode
	 * r.enUS: methodName
	 * r: classeQdox
	 * r.enUS: classQdox
	 * r: methode
	 * r.enUS: method
	 */
	public JavaMethod obtenirMethode(JavaClass classeQdox, String nomMethode, JavaClass...tableauParams) {
		ArrayList<JavaType> listeParams = new ArrayList<JavaType>();
		Collections.addAll(listeParams, tableauParams);
		JavaMethod methode = classeQdox.getMethodBySignature(nomMethode, listeParams, true);
		return methode;
	}
	
	/**
	 * Var.enUS: obtainMethod
	 * Param1.var.enUS: superClassAndMeQdox
	 * Param2.var.enUS: methodName
	 * Param3.var.enUS: paramsArray
	 * r: listeParams
	 * r.enUS: paramsList
	 * r: tableauParams
	 * r.enUS: paramsArray
	 * r: nomMethode
	 * r.enUS: methodName
	 * r: classesSuperQdoxEtMoi
	 * r.enUS: superClassAndMeQdox
	 * r: classesSuperQdox
	 * r.enUS: superClassQdox
	 * r: methode
	 * r.enUS: method
	 * r: classeSuperQdox
	 * r.enUS: superClassQdox
	 */
	public JavaMethod obtenirMethode(List<JavaClass> classesSuperQdoxEtMoi, String nomMethode, JavaClass...tableauParams) {
		ArrayList<JavaType> listeParams = new ArrayList<JavaType>();
		Collections.addAll(listeParams, tableauParams);
		for(JavaClass classeSuperQdox : classesSuperQdoxEtMoi) {
			JavaMethod methode = classeSuperQdox.getMethodBySignature(nomMethode, listeParams);
			if(methode != null)
				return methode;
		}
		return null;
	}
	
	/**
	 * Var.enUS: obtainMethodAlone
	 * Param1.var.enUS: classQdox
	 * Param2.var.enUS: methodName
	 * Param3.var.enUS: paramsArray
	 * r: listeParams
	 * r.enUS: paramsList
	 * r: tableauParams
	 * r.enUS: paramsArray
	 * r: nomMethode
	 * r.enUS: methodName
	 * r: classeQdox
	 * r.enUS: classQdox
	 * r: methode
	 * r.enUS: method
	 */
	public JavaMethod obtenirMethodeSeul(JavaClass classeQdox, String nomMethode, JavaClass...tableauParams) {
		ArrayList<JavaType> listeParams = new ArrayList<JavaType>();
		Collections.addAll(listeParams, tableauParams);
		JavaMethod methode = classeQdox.getMethodBySignature(nomMethode, listeParams, false);
		return methode;
	}
}
