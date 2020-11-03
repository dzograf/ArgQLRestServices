// Generated from ArgQLBNF.g4 by ANTLR 4.5.3
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ArgQLBNFParser}.
 */
public interface ArgQLBNFListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ArgQLBNFParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(ArgQLBNFParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLBNFParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(ArgQLBNFParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArgQLBNFParser#dialogue_pattern}.
	 * @param ctx the parse tree
	 */
	void enterDialogue_pattern(ArgQLBNFParser.Dialogue_patternContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLBNFParser#dialogue_pattern}.
	 * @param ctx the parse tree
	 */
	void exitDialogue_pattern(ArgQLBNFParser.Dialogue_patternContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArgQLBNFParser#argpattern}.
	 * @param ctx the parse tree
	 */
	void enterArgpattern(ArgQLBNFParser.ArgpatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLBNFParser#argpattern}.
	 * @param ctx the parse tree
	 */
	void exitArgpattern(ArgQLBNFParser.ArgpatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArgQLBNFParser#premisePattern}.
	 * @param ctx the parse tree
	 */
	void enterPremisePattern(ArgQLBNFParser.PremisePatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLBNFParser#premisePattern}.
	 * @param ctx the parse tree
	 */
	void exitPremisePattern(ArgQLBNFParser.PremisePatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArgQLBNFParser#conclusionPattern}.
	 * @param ctx the parse tree
	 */
	void enterConclusionPattern(ArgQLBNFParser.ConclusionPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLBNFParser#conclusionPattern}.
	 * @param ctx the parse tree
	 */
	void exitConclusionPattern(ArgQLBNFParser.ConclusionPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArgQLBNFParser#premiseFilters}.
	 * @param ctx the parse tree
	 */
	void enterPremiseFilters(ArgQLBNFParser.PremiseFiltersContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLBNFParser#premiseFilters}.
	 * @param ctx the parse tree
	 */
	void exitPremiseFilters(ArgQLBNFParser.PremiseFiltersContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArgQLBNFParser#argFilters}.
	 * @param ctx the parse tree
	 */
	void enterArgFilters(ArgQLBNFParser.ArgFiltersContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLBNFParser#argFilters}.
	 * @param ctx the parse tree
	 */
	void exitArgFilters(ArgQLBNFParser.ArgFiltersContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArgQLBNFParser#metadata}.
	 * @param ctx the parse tree
	 */
	void enterMetadata(ArgQLBNFParser.MetadataContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLBNFParser#metadata}.
	 * @param ctx the parse tree
	 */
	void exitMetadata(ArgQLBNFParser.MetadataContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArgQLBNFParser#inclusionFilter}.
	 * @param ctx the parse tree
	 */
	void enterInclusionFilter(ArgQLBNFParser.InclusionFilterContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLBNFParser#inclusionFilter}.
	 * @param ctx the parse tree
	 */
	void exitInclusionFilter(ArgQLBNFParser.InclusionFilterContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArgQLBNFParser#joinFilter}.
	 * @param ctx the parse tree
	 */
	void enterJoinFilter(ArgQLBNFParser.JoinFilterContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLBNFParser#joinFilter}.
	 * @param ctx the parse tree
	 */
	void exitJoinFilter(ArgQLBNFParser.JoinFilterContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArgQLBNFParser#disjointFilter}.
	 * @param ctx the parse tree
	 */
	void enterDisjointFilter(ArgQLBNFParser.DisjointFilterContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLBNFParser#disjointFilter}.
	 * @param ctx the parse tree
	 */
	void exitDisjointFilter(ArgQLBNFParser.DisjointFilterContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArgQLBNFParser#propositionSet}.
	 * @param ctx the parse tree
	 */
	void enterPropositionSet(ArgQLBNFParser.PropositionSetContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLBNFParser#propositionSet}.
	 * @param ctx the parse tree
	 */
	void exitPropositionSet(ArgQLBNFParser.PropositionSetContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArgQLBNFParser#propositionVar}.
	 * @param ctx the parse tree
	 */
	void enterPropositionVar(ArgQLBNFParser.PropositionVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLBNFParser#propositionVar}.
	 * @param ctx the parse tree
	 */
	void exitPropositionVar(ArgQLBNFParser.PropositionVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArgQLBNFParser#pathpattern}.
	 * @param ctx the parse tree
	 */
	void enterPathpattern(ArgQLBNFParser.PathpatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLBNFParser#pathpattern}.
	 * @param ctx the parse tree
	 */
	void exitPathpattern(ArgQLBNFParser.PathpatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArgQLBNFParser#pp}.
	 * @param ctx the parse tree
	 */
	void enterPp(ArgQLBNFParser.PpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLBNFParser#pp}.
	 * @param ctx the parse tree
	 */
	void exitPp(ArgQLBNFParser.PpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArgQLBNFParser#relation}.
	 * @param ctx the parse tree
	 */
	void enterRelation(ArgQLBNFParser.RelationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLBNFParser#relation}.
	 * @param ctx the parse tree
	 */
	void exitRelation(ArgQLBNFParser.RelationContext ctx);
}