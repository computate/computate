package org.computate.frFR.java;


/**	Pour récupérer une classe Java de Solr et écrire la classe Java dans un fichier pour chaque langue.
 */
public abstract class EcrireGenClasseApiGen implements Handler<RoutingContext> {

	public static final String VAL_virguleEspace = ", ";
	public static final String VAL_citation = "\"";
	public static final String VAL_citationDeuxPointsEspaceCitation = "\": \"";
	public static final String VAL_citationDeuxPointsEspace = "\": ";
	public static final String VAL_citationLigne = "\"\n";
	public static final String VAL_ligne = "\n";
	public static final String VAL_citationVirguleEspaceCitation = "\", \"";
	public static final String VAL_citationDeuxPointsEspaceGuillmets = "\": [";
	public static final String VAL_guillmetsFin = "]";

	public void handleGetEcrireGenClasse(Vertx vertx, Router router, OAuth2Auth oauth2) {
		router.get("").handler(rc -> {
			rc.user().isAuthorized("realm:view-account", authRes -> {
				try {
					if (authRes.result() == Boolean.TRUE) {
						rc.response().putHeader("content-type", "application/json");
						RequeteSite requeteSite = genererRequeteSitePourEcrireGenClasse(vertx, rc);
						SolrDocumentList resultatsRecherche = requeteSite.reponseRecherche.getResults();
						ReactiveReadStream<ResultatRecherche> flotLire = requeteSite.flotLire;
						ReactiveWriteStream<ResultatRecherche> flotEcrire = requeteSite.flotEcrire;
						genererGetDebutEcrireGenClasse(requeteSite);
						for(int i = 0; i < resultatsRecherche.size(); i++) {
							SolrDocument documentSolr = resultatsRecherche.get(i);
							ResultatRecherche resultatRecherche = new ResultatRecherche();
							resultatRecherche.setRequeteSite_(requeteSite);
							resultatRecherche.setDocumentSolr(documentSolr);
							resultatRecherche.setResultatIndice(i);
							if(i == 0)
								genererGetIndividuelEcrireGenClasse(resultatRecherche);
							else
								flotEcrire.write(resultatRecherche);
						}
						flotLire.handler(resultatRecherche -> {
							if (flotEcrire.writeQueueFull()) {
								flotEcrire.drainHandler((s) -> {
									flotLire.resume();
								});
								flotLire.pause();
							} else {
								try {
									genererGetIndividuelEcrireGenClasse(resultatRecherche);
								} catch(Exception e) {
									// TODO
								}
							}
						}).endHandler(h -> {
							genererGetFinEcrireGenClasse(requeteSite);
							flotEcrire.end();
						});
					}
					else {
						rc.response().setStatusCode(HttpResponseStatus.UNAUTHORIZED.code()).end();
					}
				} catch(Exception e) {
					// TODO
				}
			});
		});
	}

	public void genererGetDebutEcrireGenClasse(RequeteSite requeteSite) {
		HttpServerResponse reponseServeur = requeteSite.reponseServeur;
		QueryResponse reponseRecherche = requeteSite.reponseRecherche;
		reponseServeur.write("{\n");
		Long millisRecherche = Long.valueOf(reponseRecherche.getQTime());
		Long millisTransmission = reponseRecherche.getElapsedTime();
		Long numCommence = reponseRecherche.getResults().getStart();
		Long numTrouve = reponseRecherche.getResults().getNumFound();
		Integer numRetourne = reponseRecherche.getResponse().size();
		String tempsRecherche = String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toSeconds(millisRecherche), TimeUnit.MILLISECONDS.toMillis(millisRecherche) - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millisRecherche)));
		String tempsTransmission = String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toSeconds(millisTransmission), TimeUnit.MILLISECONDS.toMillis(millisTransmission) - TimeUnit.SECONDS.toSeconds(TimeUnit.MILLISECONDS.toSeconds(millisTransmission)));
		Exception exceptionRecherche = reponseRecherche.getException();

		reponseServeur.write("\t\"numCommence\": ");
		reponseServeur.write(numCommence.toString());

		reponseServeur.write("\t, \"numTrouve\": ");
		reponseServeur.write(numTrouve.toString());

		reponseServeur.write("\t, \"numRetourne\": ");
		reponseServeur.write(numRetourne.toString());

		reponseServeur.write("\t, \"tempsRecherche\": \"");
		reponseServeur.write(tempsRecherche);
		reponseServeur.write("\"");

		reponseServeur.write("\t, \"tempsTransmission\": \"");
		reponseServeur.write(tempsTransmission);
		reponseServeur.write("\"");

		if(exceptionRecherche != null) {
			reponseServeur.write("\t, \"exceptionRecherche\": \"");
			reponseServeur.write(exceptionRecherche.getMessage());
			reponseServeur.write("\"");
		}

		reponseServeur.write("\t, \"resultats\": [\n");
	}

	public void genererGetIndividuelEcrireGenClasse(ResultatRecherche resultatRecherche) throws Exception {
		RequeteSite requeteSite = resultatRecherche.requeteSite_;
		SolrDocument documentSolr = resultatRecherche.documentSolr;
		Integer resultatIndice = resultatRecherche.resultatIndice;
		HttpServerResponse reponseServeur = requeteSite.reponseServeur;
		reponseServeur.write("\t\t");
		if(resultatIndice > 0)
			reponseServeur.write(", ");
		reponseServeur.write("{\n");
		Collection<String> champNoms = documentSolr.getFieldNames();
		Integer j = 0;
		for(String champNomStocke : champNoms) {
			Collection<Object> champValeurs = documentSolr.getFieldValues(champNomStocke);
			j = genererGetEcrireGenClasse(j, resultatRecherche, champNomStocke, champValeurs);
		}
		reponseServeur.write("\t\t}\n");
	}

	public void genererGetFinEcrireGenClasse(RequeteSite requeteSite) {
		HttpServerResponse reponseServeur = requeteSite.reponseServeur;
		QueryResponse reponseRecherche = requeteSite.reponseRecherche;
		reponseServeur.write("\t]\n");
		reponseServeur.write("}\n");
	}

	public String varIndexeEcrireGenClasse(String entiteVar) throws Exception {
		switch(entiteVar) {
			default:
				throw new Exception(String.format("\"%s\" n'est pas une entité indexé. ", entiteVar));
		}
	}

	public SolrQuery genererRecherche(HttpServerRequest requeteServeur) throws Exception {
		String entiteVar = null;
		String valeurIndexe = null;
		String varIndexe = null;
		String valeurTri = null;
		Integer rechercheDebut = null;
		Integer rechercheNum = null;
		SolrQuery rechercheSolr = new SolrQuery();
		rechercheSolr.setQuery("*:*");
		rechercheSolr.setRows(1000000);
		rechercheSolr.addSort("partNumero_indexed_int", ORDER.asc);
		MultiMap paramMap = requeteServeur.params();
		for(String paramCle : paramMap.names()) {
			List<String> paramValeurs = paramMap.getAll(paramCle);
			for(String paramValeur : paramValeurs) {
				switch(paramCle) {
					case "q":
						entiteVar = StringUtils.trim(StringUtils.substringBefore(paramValeur, ":"));
						valeurIndexe = StringUtils.trim(StringUtils.substringAfter(paramValeur, ":"));
						varIndexe = varIndexeEcrireGenClasse(paramCle);
						rechercheSolr.setQuery(varIndexe + ":" + ClientUtils.escapeQueryChars(valeurIndexe));
						break;
					case "fq":
						entiteVar = StringUtils.trim(StringUtils.substringBefore(paramValeur, ":"));
						valeurIndexe = StringUtils.trim(StringUtils.substringAfter(paramValeur, ":"));
						varIndexe = varIndexeEcrireGenClasse(paramCle);
						rechercheSolr.addFilterQuery(varIndexe + ":" + ClientUtils.escapeQueryChars(valeurIndexe));
						break;
					case "sort":
						entiteVar = StringUtils.trim(StringUtils.substringBefore(paramValeur, " "));
						valeurTri = StringUtils.trim(StringUtils.substringAfter(paramValeur, " "));
						varIndexe = varIndexeEcrireGenClasse(paramCle);
						rechercheSolr.addSort(varIndexe, ORDER.valueOf(valeurTri));
						break;
					case "fl":
						entiteVar = StringUtils.trim(paramValeur);
						varIndexe = varIndexeEcrireGenClasse(paramCle);
						rechercheSolr.addField(varIndexe);
						break;
					case "start":
						rechercheDebut = Integer.parseInt(paramValeur);
						rechercheSolr.setStart(rechercheDebut);
						break;
					case "rows":
						rechercheNum = Integer.parseInt(paramValeur);
						rechercheSolr.setRows(rechercheNum);
						break;
				}
			}
		}
		return rechercheSolr;
	}

	public RequeteSite genererRequeteSitePourEcrireGenClasse(Vertx vertx, RoutingContext contexteItineraire) throws Exception {
		SiteContexte SiteContexte = (SiteContexte)vertx.sharedData().getLocalMap("SiteContexte");
		SolrQuery rechercheSolr = genererRecherche(contexteItineraire.request());

		RequeteSite requeteSite = new RequeteSite();
		requeteSite.setVertx_(vertx);
		requeteSite.setContexteItineraire_(contexteItineraire);
		requeteSite.setSiteContexte_(SiteContexte);
		requeteSite.setRechercheSolr_(rechercheSolr);
		requeteSite.initLoinRequeteSite(requeteSite);

		UtilisateurSite utilisateurSite = new UtilisateurSite();
		utilisateurSite.initLoinUtilisateurSite(requeteSite);
		requeteSite.setUtilisateurSite(utilisateurSite);
		utilisateurSite.setRequeteSite_(requeteSite);
		return requeteSite;
	}

	public Integer genererGetEcrireGenClasse(Integer j, ResultatRecherche resultatRecherche, String entiteVarStocke, Collection<Object> champValeurs) throws Exception {
		RequeteSite requeteSite = resultatRecherche.requeteSite_;
		HttpServerResponse reponseServeur = requeteSite.reponseServeur;
		if(!champValeurs.isEmpty()) {
			Object champValeur = champValeurs.iterator().next();
			if(champValeur != null) {
			}
		}
		return j;
	}

	protected void handlePostEcrireGenClasse(Vertx vertx, Router router, OAuth2Auth oauth2) {
		router.post("").handler(rc -> {
			rc.user().isAuthorized("realm:view-account", authRes -> {
				try {
					if (authRes.result() == Boolean.TRUE) {
						rc.response().putHeader("content-type", "application/json");
						RequeteSite requeteSite = genererRequeteSitePourEcrireGenClasse(vertx, rc);
						SolrDocumentList resultatsRecherche = requeteSite.reponseRecherche.getResults();
						ReactiveReadStream<ResultatRecherche> flotLire = requeteSite.flotLire;
						ReactiveWriteStream<ResultatRecherche> flotEcrire = requeteSite.flotEcrire;
						genererGetDebutEcrireGenClasse(requeteSite);
						for(int i = 0; i < resultatsRecherche.size(); i++) {
							SolrDocument documentSolr = resultatsRecherche.get(i);
							ResultatRecherche resultatRecherche = new ResultatRecherche();
							resultatRecherche.setRequeteSite_(requeteSite);
							resultatRecherche.setDocumentSolr(documentSolr);
							resultatRecherche.setResultatIndice(i);
							if(i == 0)
								genererPostIndividuelEcrireGenClasse(resultatRecherche);
							else
								flotEcrire.write(resultatRecherche);
						}
						flotLire.handler(resultatRecherche -> {
							if (flotEcrire.writeQueueFull()) {
								flotEcrire.drainHandler((s) -> {
									flotLire.resume();
								});
								flotLire.pause();
							} else {
								try {
									genererPostIndividuelEcrireGenClasse(resultatRecherche);
								} catch(Exception e) {
									// TODO
								}
							}
						}).endHandler(h -> {
							genererGetFinEcrireGenClasse(requeteSite);
							flotEcrire.end();
						});
					}
					else {
						rc.response().setStatusCode(HttpResponseStatus.UNAUTHORIZED.code()).end();
					}
				} catch(Exception e) {
					// TODO
				}
			});
		});
	}

	public void genererPostIndividuelEcrireGenClasse(ResultatRecherche resultatRecherche) throws Exception {
		RequeteSite requeteSite = resultatRecherche.requeteSite_;
		SolrDocument documentSolr = resultatRecherche.documentSolr;
		Integer resultatIndice = resultatRecherche.resultatIndice;
		HttpServerResponse reponseServeur = requeteSite.reponseServeur;
		reponseServeur.write("\t\t");
		if(resultatIndice > 0)
			reponseServeur.write(", ");
		reponseServeur.write("{\n");
		Collection<String> champNoms = documentSolr.getFieldNames();
		Integer j = 0;
		for(String champNomStocke : champNoms) {
			Collection<Object> champValeurs = documentSolr.getFieldValues(champNomStocke);
			j = genererPostEcrireGenClasse(j, resultatRecherche, champNomStocke, champValeurs);
		}
		reponseServeur.write("\t\t}\n");
	}

	public Integer genererPostEcrireGenClasse(Integer j, ResultatRecherche resultatRecherche, String entiteVarStocke, Collection<Object> champValeurs) throws Exception {
		RequeteSite requeteSite = resultatRecherche.requeteSite_;
		HttpServerResponse reponseServeur = requeteSite.reponseServeur;
		if(!champValeurs.isEmpty()) {
			Object champValeur = champValeurs.iterator().next();
			if(champValeur != null) {
			}
		}
		return j;
	}
}
