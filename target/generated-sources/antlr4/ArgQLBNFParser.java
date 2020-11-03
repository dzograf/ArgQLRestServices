// Generated from ArgQLBNF.g4 by ANTLR 4.5.3
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ArgQLBNFParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, MATCH=15, RETURN=16, 
		ATTACK=17, REBUT=18, UNDERCUT=19, SUPPORT=20, ENDORSE=21, BACK=22, VARIABLE=23, 
		EMPTYSET=24, TIMESTAMP=25, AUTHOR=26, SCHEME=27, DOTS=28, TEXT=29, INT=30, 
		WS=31;
	public static final int
		RULE_query = 0, RULE_dialogue_pattern = 1, RULE_argpattern = 2, RULE_premisePattern = 3, 
		RULE_conclusionPattern = 4, RULE_premiseFilters = 5, RULE_argFilters = 6, 
		RULE_metadata = 7, RULE_inclusionFilter = 8, RULE_joinFilter = 9, RULE_disjointFilter = 10, 
		RULE_propositionSet = 11, RULE_propositionVar = 12, RULE_pathpattern = 13, 
		RULE_pp = 14, RULE_relation = 15;
	public static final String[] ruleNames = {
		"query", "dialogue_pattern", "argpattern", "premisePattern", "conclusionPattern", 
		"premiseFilters", "argFilters", "metadata", "inclusionFilter", "joinFilter", 
		"disjointFilter", "propositionSet", "propositionVar", "pathpattern", "pp", 
		"relation"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "','", "'<'", "'>'", "'['", "']'", "'/'", "'.'", "'!'", "'{'", "'}'", 
		"'('", "')'", "'*'", "'+'", null, null, null, null, null, null, null, 
		null, null, "'_'", null, null, null, "':'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, "MATCH", "RETURN", "ATTACK", "REBUT", "UNDERCUT", "SUPPORT", 
		"ENDORSE", "BACK", "VARIABLE", "EMPTYSET", "TIMESTAMP", "AUTHOR", "SCHEME", 
		"DOTS", "TEXT", "INT", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "ArgQLBNF.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ArgQLBNFParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class QueryContext extends ParserRuleContext {
		public TerminalNode MATCH() { return getToken(ArgQLBNFParser.MATCH, 0); }
		public TerminalNode RETURN() { return getToken(ArgQLBNFParser.RETURN, 0); }
		public List<Dialogue_patternContext> dialogue_pattern() {
			return getRuleContexts(Dialogue_patternContext.class);
		}
		public Dialogue_patternContext dialogue_pattern(int i) {
			return getRuleContext(Dialogue_patternContext.class,i);
		}
		public List<TerminalNode> VARIABLE() { return getTokens(ArgQLBNFParser.VARIABLE); }
		public TerminalNode VARIABLE(int i) {
			return getToken(ArgQLBNFParser.VARIABLE, i);
		}
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).exitQuery(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_query);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			match(MATCH);
			{
			setState(33);
			dialogue_pattern();
			setState(38);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(34);
				match(T__0);
				setState(35);
				dialogue_pattern();
				}
				}
				setState(40);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			setState(41);
			match(RETURN);
			{
			setState(42);
			match(VARIABLE);
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(43);
				match(T__0);
				setState(44);
				match(VARIABLE);
				}
				}
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Dialogue_patternContext extends ParserRuleContext {
		public List<ArgpatternContext> argpattern() {
			return getRuleContexts(ArgpatternContext.class);
		}
		public ArgpatternContext argpattern(int i) {
			return getRuleContext(ArgpatternContext.class,i);
		}
		public List<PathpatternContext> pathpattern() {
			return getRuleContexts(PathpatternContext.class);
		}
		public PathpatternContext pathpattern(int i) {
			return getRuleContext(PathpatternContext.class,i);
		}
		public Dialogue_patternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dialogue_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).enterDialogue_pattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).exitDialogue_pattern(this);
		}
	}

	public final Dialogue_patternContext dialogue_pattern() throws RecognitionException {
		Dialogue_patternContext _localctx = new Dialogue_patternContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_dialogue_pattern);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			argpattern();
			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__10) | (1L << ATTACK) | (1L << REBUT) | (1L << UNDERCUT) | (1L << SUPPORT) | (1L << ENDORSE) | (1L << BACK))) != 0)) {
				{
				{
				setState(51);
				pathpattern(2);
				setState(52);
				argpattern();
				}
				}
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgpatternContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(ArgQLBNFParser.VARIABLE, 0); }
		public PremisePatternContext premisePattern() {
			return getRuleContext(PremisePatternContext.class,0);
		}
		public ConclusionPatternContext conclusionPattern() {
			return getRuleContext(ConclusionPatternContext.class,0);
		}
		public ArgFiltersContext argFilters() {
			return getRuleContext(ArgFiltersContext.class,0);
		}
		public TerminalNode DOTS() { return getToken(ArgQLBNFParser.DOTS, 0); }
		public ArgpatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argpattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).enterArgpattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).exitArgpattern(this);
		}
	}

	public final ArgpatternContext argpattern() throws RecognitionException {
		ArgpatternContext _localctx = new ArgpatternContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_argpattern);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(59);
				match(VARIABLE);
				}
				break;
			case 2:
				{
				setState(62);
				_la = _input.LA(1);
				if (_la==VARIABLE) {
					{
					setState(60);
					match(VARIABLE);
					setState(61);
					match(DOTS);
					}
				}

				setState(64);
				match(T__1);
				setState(65);
				premisePattern();
				setState(66);
				match(T__0);
				setState(67);
				conclusionPattern();
				setState(68);
				match(T__2);
				}
				break;
			}
			setState(73);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(72);
				argFilters();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PremisePatternContext extends ParserRuleContext {
		public PropositionSetContext propositionSet() {
			return getRuleContext(PropositionSetContext.class,0);
		}
		public PropositionVarContext propositionVar() {
			return getRuleContext(PropositionVarContext.class,0);
		}
		public PremiseFiltersContext premiseFilters() {
			return getRuleContext(PremiseFiltersContext.class,0);
		}
		public TerminalNode EMPTYSET() { return getToken(ArgQLBNFParser.EMPTYSET, 0); }
		public PremisePatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_premisePattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).enterPremisePattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).exitPremisePattern(this);
		}
	}

	public final PremisePatternContext premisePattern() throws RecognitionException {
		PremisePatternContext _localctx = new PremisePatternContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_premisePattern);
		int _la;
		try {
			setState(81);
			switch (_input.LA(1)) {
			case T__8:
				enterOuterAlt(_localctx, 1);
				{
				setState(75);
				propositionSet();
				}
				break;
			case VARIABLE:
				enterOuterAlt(_localctx, 2);
				{
				setState(76);
				propositionVar();
				setState(78);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(77);
					premiseFilters();
					}
				}

				}
				break;
			case EMPTYSET:
				enterOuterAlt(_localctx, 3);
				{
				setState(80);
				match(EMPTYSET);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConclusionPatternContext extends ParserRuleContext {
		public PropositionVarContext propositionVar() {
			return getRuleContext(PropositionVarContext.class,0);
		}
		public TerminalNode TEXT() { return getToken(ArgQLBNFParser.TEXT, 0); }
		public ConclusionPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conclusionPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).enterConclusionPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).exitConclusionPattern(this);
		}
	}

	public final ConclusionPatternContext conclusionPattern() throws RecognitionException {
		ConclusionPatternContext _localctx = new ConclusionPatternContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_conclusionPattern);
		try {
			setState(85);
			switch (_input.LA(1)) {
			case VARIABLE:
				enterOuterAlt(_localctx, 1);
				{
				setState(83);
				propositionVar();
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 2);
				{
				setState(84);
				match(TEXT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PremiseFiltersContext extends ParserRuleContext {
		public InclusionFilterContext inclusionFilter() {
			return getRuleContext(InclusionFilterContext.class,0);
		}
		public JoinFilterContext joinFilter() {
			return getRuleContext(JoinFilterContext.class,0);
		}
		public DisjointFilterContext disjointFilter() {
			return getRuleContext(DisjointFilterContext.class,0);
		}
		public PremiseFiltersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_premiseFilters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).enterPremiseFilters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).exitPremiseFilters(this);
		}
	}

	public final PremiseFiltersContext premiseFilters() throws RecognitionException {
		PremiseFiltersContext _localctx = new PremiseFiltersContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_premiseFilters);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(T__3);
			setState(91);
			switch (_input.LA(1)) {
			case T__5:
				{
				setState(88);
				inclusionFilter();
				}
				break;
			case T__6:
				{
				setState(89);
				joinFilter();
				}
				break;
			case T__7:
				{
				setState(90);
				disjointFilter();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(93);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgFiltersContext extends ParserRuleContext {
		public TerminalNode SCHEME() { return getToken(ArgQLBNFParser.SCHEME, 0); }
		public TerminalNode DOTS() { return getToken(ArgQLBNFParser.DOTS, 0); }
		public TerminalNode TEXT() { return getToken(ArgQLBNFParser.TEXT, 0); }
		public MetadataContext metadata() {
			return getRuleContext(MetadataContext.class,0);
		}
		public ArgFiltersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argFilters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).enterArgFilters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).exitArgFilters(this);
		}
	}

	public final ArgFiltersContext argFilters() throws RecognitionException {
		ArgFiltersContext _localctx = new ArgFiltersContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_argFilters);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(T__3);
			setState(100);
			switch (_input.LA(1)) {
			case SCHEME:
				{
				setState(96);
				match(SCHEME);
				setState(97);
				match(DOTS);
				setState(98);
				match(TEXT);
				}
				break;
			case TIMESTAMP:
			case AUTHOR:
				{
				setState(99);
				metadata();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(102);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MetadataContext extends ParserRuleContext {
		public TerminalNode TIMESTAMP() { return getToken(ArgQLBNFParser.TIMESTAMP, 0); }
		public TerminalNode DOTS() { return getToken(ArgQLBNFParser.DOTS, 0); }
		public TerminalNode TEXT() { return getToken(ArgQLBNFParser.TEXT, 0); }
		public TerminalNode AUTHOR() { return getToken(ArgQLBNFParser.AUTHOR, 0); }
		public MetadataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_metadata; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).enterMetadata(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).exitMetadata(this);
		}
	}

	public final MetadataContext metadata() throws RecognitionException {
		MetadataContext _localctx = new MetadataContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_metadata);
		try {
			setState(110);
			switch (_input.LA(1)) {
			case TIMESTAMP:
				enterOuterAlt(_localctx, 1);
				{
				setState(104);
				match(TIMESTAMP);
				setState(105);
				match(DOTS);
				setState(106);
				match(TEXT);
				}
				break;
			case AUTHOR:
				enterOuterAlt(_localctx, 2);
				{
				setState(107);
				match(AUTHOR);
				setState(108);
				match(DOTS);
				setState(109);
				match(TEXT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InclusionFilterContext extends ParserRuleContext {
		public PropositionSetContext propositionSet() {
			return getRuleContext(PropositionSetContext.class,0);
		}
		public TerminalNode VARIABLE() { return getToken(ArgQLBNFParser.VARIABLE, 0); }
		public InclusionFilterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inclusionFilter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).enterInclusionFilter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).exitInclusionFilter(this);
		}
	}

	public final InclusionFilterContext inclusionFilter() throws RecognitionException {
		InclusionFilterContext _localctx = new InclusionFilterContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_inclusionFilter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(T__5);
			setState(115);
			switch (_input.LA(1)) {
			case T__8:
				{
				setState(113);
				propositionSet();
				}
				break;
			case VARIABLE:
				{
				setState(114);
				match(VARIABLE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JoinFilterContext extends ParserRuleContext {
		public PropositionSetContext propositionSet() {
			return getRuleContext(PropositionSetContext.class,0);
		}
		public TerminalNode VARIABLE() { return getToken(ArgQLBNFParser.VARIABLE, 0); }
		public JoinFilterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_joinFilter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).enterJoinFilter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).exitJoinFilter(this);
		}
	}

	public final JoinFilterContext joinFilter() throws RecognitionException {
		JoinFilterContext _localctx = new JoinFilterContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_joinFilter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(T__6);
			setState(120);
			switch (_input.LA(1)) {
			case T__8:
				{
				setState(118);
				propositionSet();
				}
				break;
			case VARIABLE:
				{
				setState(119);
				match(VARIABLE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DisjointFilterContext extends ParserRuleContext {
		public PropositionSetContext propositionSet() {
			return getRuleContext(PropositionSetContext.class,0);
		}
		public TerminalNode VARIABLE() { return getToken(ArgQLBNFParser.VARIABLE, 0); }
		public DisjointFilterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_disjointFilter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).enterDisjointFilter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).exitDisjointFilter(this);
		}
	}

	public final DisjointFilterContext disjointFilter() throws RecognitionException {
		DisjointFilterContext _localctx = new DisjointFilterContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_disjointFilter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			match(T__7);
			setState(125);
			switch (_input.LA(1)) {
			case T__8:
				{
				setState(123);
				propositionSet();
				}
				break;
			case VARIABLE:
				{
				setState(124);
				match(VARIABLE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropositionSetContext extends ParserRuleContext {
		public List<TerminalNode> TEXT() { return getTokens(ArgQLBNFParser.TEXT); }
		public TerminalNode TEXT(int i) {
			return getToken(ArgQLBNFParser.TEXT, i);
		}
		public PropositionSetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propositionSet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).enterPropositionSet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).exitPropositionSet(this);
		}
	}

	public final PropositionSetContext propositionSet() throws RecognitionException {
		PropositionSetContext _localctx = new PropositionSetContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_propositionSet);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(T__8);
			setState(128);
			match(TEXT);
			setState(133);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(129);
				match(T__0);
				setState(130);
				match(TEXT);
				}
				}
				setState(135);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(136);
			match(T__9);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropositionVarContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(ArgQLBNFParser.VARIABLE, 0); }
		public PropositionVarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propositionVar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).enterPropositionVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).exitPropositionVar(this);
		}
	}

	public final PropositionVarContext propositionVar() throws RecognitionException {
		PropositionVarContext _localctx = new PropositionVarContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_propositionVar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			match(VARIABLE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PathpatternContext extends ParserRuleContext {
		public int a;
		public List<PpContext> pp() {
			return getRuleContexts(PpContext.class);
		}
		public PpContext pp(int i) {
			return getRuleContext(PpContext.class,i);
		}
		public PathpatternContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public PathpatternContext(ParserRuleContext parent, int invokingState, int a) {
			super(parent, invokingState);
			this.a = a;
		}
		@Override public int getRuleIndex() { return RULE_pathpattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).enterPathpattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).exitPathpattern(this);
		}
	}

	public final PathpatternContext pathpattern(int a) throws RecognitionException {
		PathpatternContext _localctx = new PathpatternContext(_ctx, getState(), a);
		enterRule(_localctx, 26, RULE_pathpattern);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			pp(2);
			setState(145);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(141);
				match(T__5);
				setState(142);
				pp(2);
				}
				}
				setState(147);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PpContext extends ParserRuleContext {
		public int a;
		public RelationContext relation() {
			return getRuleContext(RelationContext.class,0);
		}
		public PathpatternContext pathpattern() {
			return getRuleContext(PathpatternContext.class,0);
		}
		public TerminalNode INT() { return getToken(ArgQLBNFParser.INT, 0); }
		public PpContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public PpContext(ParserRuleContext parent, int invokingState, int a) {
			super(parent, invokingState);
			this.a = a;
		}
		@Override public int getRuleIndex() { return RULE_pp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).enterPp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).exitPp(this);
		}
	}

	public final PpContext pp(int a) throws RecognitionException {
		PpContext _localctx = new PpContext(_ctx, getState(), a);
		enterRule(_localctx, 28, RULE_pp);
		try {
			setState(158);
			switch (_input.LA(1)) {
			case ATTACK:
			case REBUT:
			case UNDERCUT:
			case SUPPORT:
			case ENDORSE:
			case BACK:
				enterOuterAlt(_localctx, 1);
				{
				setState(148);
				relation();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 2);
				{
				setState(149);
				match(T__10);
				setState(150);
				pathpattern(2);
				setState(151);
				match(T__11);
				setState(156);
				switch (_input.LA(1)) {
				case T__12:
					{
					setState(152);
					match(T__12);
					setState(153);
					match(INT);
					}
					break;
				case T__13:
					{
					setState(154);
					match(T__13);
					setState(155);
					match(INT);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationContext extends ParserRuleContext {
		public TerminalNode ATTACK() { return getToken(ArgQLBNFParser.ATTACK, 0); }
		public TerminalNode REBUT() { return getToken(ArgQLBNFParser.REBUT, 0); }
		public TerminalNode UNDERCUT() { return getToken(ArgQLBNFParser.UNDERCUT, 0); }
		public TerminalNode SUPPORT() { return getToken(ArgQLBNFParser.SUPPORT, 0); }
		public TerminalNode ENDORSE() { return getToken(ArgQLBNFParser.ENDORSE, 0); }
		public TerminalNode BACK() { return getToken(ArgQLBNFParser.BACK, 0); }
		public RelationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).enterRelation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLBNFListener ) ((ArgQLBNFListener)listener).exitRelation(this);
		}
	}

	public final RelationContext relation() throws RecognitionException {
		RelationContext _localctx = new RelationContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_relation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ATTACK) | (1L << REBUT) | (1L << UNDERCUT) | (1L << SUPPORT) | (1L << ENDORSE) | (1L << BACK))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3!\u00a5\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\3"+
		"\2\3\2\7\2\'\n\2\f\2\16\2*\13\2\3\2\3\2\3\2\3\2\7\2\60\n\2\f\2\16\2\63"+
		"\13\2\3\3\3\3\3\3\3\3\7\39\n\3\f\3\16\3<\13\3\3\4\3\4\3\4\5\4A\n\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\5\4I\n\4\3\4\5\4L\n\4\3\5\3\5\3\5\5\5Q\n\5\3\5\5"+
		"\5T\n\5\3\6\3\6\5\6X\n\6\3\7\3\7\3\7\3\7\5\7^\n\7\3\7\3\7\3\b\3\b\3\b"+
		"\3\b\3\b\5\bg\n\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\5\tq\n\t\3\n\3\n\3\n"+
		"\5\nv\n\n\3\13\3\13\3\13\5\13{\n\13\3\f\3\f\3\f\5\f\u0080\n\f\3\r\3\r"+
		"\3\r\3\r\7\r\u0086\n\r\f\r\16\r\u0089\13\r\3\r\3\r\3\16\3\16\3\17\3\17"+
		"\3\17\7\17\u0092\n\17\f\17\16\17\u0095\13\17\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\5\20\u009f\n\20\5\20\u00a1\n\20\3\21\3\21\3\21\2\2\22"+
		"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \2\3\3\2\23\30\u00a9\2\"\3\2\2"+
		"\2\4\64\3\2\2\2\6H\3\2\2\2\bS\3\2\2\2\nW\3\2\2\2\fY\3\2\2\2\16a\3\2\2"+
		"\2\20p\3\2\2\2\22r\3\2\2\2\24w\3\2\2\2\26|\3\2\2\2\30\u0081\3\2\2\2\32"+
		"\u008c\3\2\2\2\34\u008e\3\2\2\2\36\u00a0\3\2\2\2 \u00a2\3\2\2\2\"#\7\21"+
		"\2\2#(\5\4\3\2$%\7\3\2\2%\'\5\4\3\2&$\3\2\2\2\'*\3\2\2\2(&\3\2\2\2()\3"+
		"\2\2\2)+\3\2\2\2*(\3\2\2\2+,\7\22\2\2,\61\7\31\2\2-.\7\3\2\2.\60\7\31"+
		"\2\2/-\3\2\2\2\60\63\3\2\2\2\61/\3\2\2\2\61\62\3\2\2\2\62\3\3\2\2\2\63"+
		"\61\3\2\2\2\64:\5\6\4\2\65\66\5\34\17\2\66\67\5\6\4\2\679\3\2\2\28\65"+
		"\3\2\2\29<\3\2\2\2:8\3\2\2\2:;\3\2\2\2;\5\3\2\2\2<:\3\2\2\2=I\7\31\2\2"+
		">?\7\31\2\2?A\7\36\2\2@>\3\2\2\2@A\3\2\2\2AB\3\2\2\2BC\7\4\2\2CD\5\b\5"+
		"\2DE\7\3\2\2EF\5\n\6\2FG\7\5\2\2GI\3\2\2\2H=\3\2\2\2H@\3\2\2\2IK\3\2\2"+
		"\2JL\5\16\b\2KJ\3\2\2\2KL\3\2\2\2L\7\3\2\2\2MT\5\30\r\2NP\5\32\16\2OQ"+
		"\5\f\7\2PO\3\2\2\2PQ\3\2\2\2QT\3\2\2\2RT\7\32\2\2SM\3\2\2\2SN\3\2\2\2"+
		"SR\3\2\2\2T\t\3\2\2\2UX\5\32\16\2VX\7\37\2\2WU\3\2\2\2WV\3\2\2\2X\13\3"+
		"\2\2\2Y]\7\6\2\2Z^\5\22\n\2[^\5\24\13\2\\^\5\26\f\2]Z\3\2\2\2][\3\2\2"+
		"\2]\\\3\2\2\2^_\3\2\2\2_`\7\7\2\2`\r\3\2\2\2af\7\6\2\2bc\7\35\2\2cd\7"+
		"\36\2\2dg\7\37\2\2eg\5\20\t\2fb\3\2\2\2fe\3\2\2\2gh\3\2\2\2hi\7\7\2\2"+
		"i\17\3\2\2\2jk\7\33\2\2kl\7\36\2\2lq\7\37\2\2mn\7\34\2\2no\7\36\2\2oq"+
		"\7\37\2\2pj\3\2\2\2pm\3\2\2\2q\21\3\2\2\2ru\7\b\2\2sv\5\30\r\2tv\7\31"+
		"\2\2us\3\2\2\2ut\3\2\2\2v\23\3\2\2\2wz\7\t\2\2x{\5\30\r\2y{\7\31\2\2z"+
		"x\3\2\2\2zy\3\2\2\2{\25\3\2\2\2|\177\7\n\2\2}\u0080\5\30\r\2~\u0080\7"+
		"\31\2\2\177}\3\2\2\2\177~\3\2\2\2\u0080\27\3\2\2\2\u0081\u0082\7\13\2"+
		"\2\u0082\u0087\7\37\2\2\u0083\u0084\7\3\2\2\u0084\u0086\7\37\2\2\u0085"+
		"\u0083\3\2\2\2\u0086\u0089\3\2\2\2\u0087\u0085\3\2\2\2\u0087\u0088\3\2"+
		"\2\2\u0088\u008a\3\2\2\2\u0089\u0087\3\2\2\2\u008a\u008b\7\f\2\2\u008b"+
		"\31\3\2\2\2\u008c\u008d\7\31\2\2\u008d\33\3\2\2\2\u008e\u0093\5\36\20"+
		"\2\u008f\u0090\7\b\2\2\u0090\u0092\5\36\20\2\u0091\u008f\3\2\2\2\u0092"+
		"\u0095\3\2\2\2\u0093\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094\35\3\2\2"+
		"\2\u0095\u0093\3\2\2\2\u0096\u00a1\5 \21\2\u0097\u0098\7\r\2\2\u0098\u0099"+
		"\5\34\17\2\u0099\u009e\7\16\2\2\u009a\u009b\7\17\2\2\u009b\u009f\7 \2"+
		"\2\u009c\u009d\7\20\2\2\u009d\u009f\7 \2\2\u009e\u009a\3\2\2\2\u009e\u009c"+
		"\3\2\2\2\u009f\u00a1\3\2\2\2\u00a0\u0096\3\2\2\2\u00a0\u0097\3\2\2\2\u00a1"+
		"\37\3\2\2\2\u00a2\u00a3\t\2\2\2\u00a3!\3\2\2\2\25(\61:@HKPSW]fpuz\177"+
		"\u0087\u0093\u009e\u00a0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}