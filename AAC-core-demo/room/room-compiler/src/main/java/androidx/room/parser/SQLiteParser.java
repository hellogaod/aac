//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package androidx.room.parser;

import java.util.List;
import org.antlr.v4.runtime.FailedPredicateException;
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

public class SQLiteParser extends Parser {
    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache;
    public static final int SCOL = 1;
    public static final int DOT = 2;
    public static final int OPEN_PAR = 3;
    public static final int CLOSE_PAR = 4;
    public static final int COMMA = 5;
    public static final int ASSIGN = 6;
    public static final int STAR = 7;
    public static final int PLUS = 8;
    public static final int MINUS = 9;
    public static final int TILDE = 10;
    public static final int PIPE2 = 11;
    public static final int DIV = 12;
    public static final int MOD = 13;
    public static final int LT2 = 14;
    public static final int GT2 = 15;
    public static final int AMP = 16;
    public static final int PIPE = 17;
    public static final int LT = 18;
    public static final int LT_EQ = 19;
    public static final int GT = 20;
    public static final int GT_EQ = 21;
    public static final int EQ = 22;
    public static final int NOT_EQ1 = 23;
    public static final int NOT_EQ2 = 24;
    public static final int TRUE = 25;
    public static final int FALSE = 26;
    public static final int K_ABORT = 27;
    public static final int K_ACTION = 28;
    public static final int K_ADD = 29;
    public static final int K_AFTER = 30;
    public static final int K_ALL = 31;
    public static final int K_ALTER = 32;
    public static final int K_ANALYZE = 33;
    public static final int K_AND = 34;
    public static final int K_AS = 35;
    public static final int K_ASC = 36;
    public static final int K_ATTACH = 37;
    public static final int K_AUTOINCREMENT = 38;
    public static final int K_BEFORE = 39;
    public static final int K_BEGIN = 40;
    public static final int K_BETWEEN = 41;
    public static final int K_BY = 42;
    public static final int K_CASCADE = 43;
    public static final int K_CASE = 44;
    public static final int K_CAST = 45;
    public static final int K_CHECK = 46;
    public static final int K_COLLATE = 47;
    public static final int K_COLUMN = 48;
    public static final int K_COMMIT = 49;
    public static final int K_CONFLICT = 50;
    public static final int K_CONSTRAINT = 51;
    public static final int K_CREATE = 52;
    public static final int K_CROSS = 53;
    public static final int K_CURRENT_DATE = 54;
    public static final int K_CURRENT_TIME = 55;
    public static final int K_CURRENT_TIMESTAMP = 56;
    public static final int K_DATABASE = 57;
    public static final int K_DEFAULT = 58;
    public static final int K_DEFERRABLE = 59;
    public static final int K_DEFERRED = 60;
    public static final int K_DELETE = 61;
    public static final int K_DESC = 62;
    public static final int K_DETACH = 63;
    public static final int K_DISTINCT = 64;
    public static final int K_DROP = 65;
    public static final int K_EACH = 66;
    public static final int K_ELSE = 67;
    public static final int K_END = 68;
    public static final int K_ESCAPE = 69;
    public static final int K_EXCEPT = 70;
    public static final int K_EXCLUSIVE = 71;
    public static final int K_EXISTS = 72;
    public static final int K_EXPLAIN = 73;
    public static final int K_FAIL = 74;
    public static final int K_FOR = 75;
    public static final int K_FOREIGN = 76;
    public static final int K_FROM = 77;
    public static final int K_FULL = 78;
    public static final int K_GLOB = 79;
    public static final int K_GROUP = 80;
    public static final int K_HAVING = 81;
    public static final int K_IF = 82;
    public static final int K_IGNORE = 83;
    public static final int K_IMMEDIATE = 84;
    public static final int K_IN = 85;
    public static final int K_INDEX = 86;
    public static final int K_INDEXED = 87;
    public static final int K_INITIALLY = 88;
    public static final int K_INNER = 89;
    public static final int K_INSERT = 90;
    public static final int K_INSTEAD = 91;
    public static final int K_INTERSECT = 92;
    public static final int K_INTO = 93;
    public static final int K_IS = 94;
    public static final int K_ISNULL = 95;
    public static final int K_JOIN = 96;
    public static final int K_KEY = 97;
    public static final int K_LEFT = 98;
    public static final int K_LIKE = 99;
    public static final int K_LIMIT = 100;
    public static final int K_MATCH = 101;
    public static final int K_NATURAL = 102;
    public static final int K_NO = 103;
    public static final int K_NOT = 104;
    public static final int K_NOTNULL = 105;
    public static final int K_NULL = 106;
    public static final int K_OF = 107;
    public static final int K_OFFSET = 108;
    public static final int K_ON = 109;
    public static final int K_OR = 110;
    public static final int K_ORDER = 111;
    public static final int K_OUTER = 112;
    public static final int K_PLAN = 113;
    public static final int K_PRAGMA = 114;
    public static final int K_PRIMARY = 115;
    public static final int K_QUERY = 116;
    public static final int K_RAISE = 117;
    public static final int K_RECURSIVE = 118;
    public static final int K_REFERENCES = 119;
    public static final int K_REGEXP = 120;
    public static final int K_REINDEX = 121;
    public static final int K_RELEASE = 122;
    public static final int K_RENAME = 123;
    public static final int K_REPLACE = 124;
    public static final int K_RESTRICT = 125;
    public static final int K_RIGHT = 126;
    public static final int K_ROLLBACK = 127;
    public static final int K_ROW = 128;
    public static final int K_SAVEPOINT = 129;
    public static final int K_SELECT = 130;
    public static final int K_SET = 131;
    public static final int K_TABLE = 132;
    public static final int K_TEMP = 133;
    public static final int K_TEMPORARY = 134;
    public static final int K_THEN = 135;
    public static final int K_TO = 136;
    public static final int K_TRANSACTION = 137;
    public static final int K_TRIGGER = 138;
    public static final int K_UNION = 139;
    public static final int K_UNIQUE = 140;
    public static final int K_UPDATE = 141;
    public static final int K_USING = 142;
    public static final int K_VACUUM = 143;
    public static final int K_VALUES = 144;
    public static final int K_VIEW = 145;
    public static final int K_VIRTUAL = 146;
    public static final int K_WHEN = 147;
    public static final int K_WHERE = 148;
    public static final int K_WITH = 149;
    public static final int K_WITHOUT = 150;
    public static final int WITHOUT_ROWID = 151;
    public static final int DO_NOTHING = 152;
    public static final int DO_UPDATE = 153;
    public static final int IDENTIFIER = 154;
    public static final int NUMERIC_LITERAL = 155;
    public static final int BIND_PARAMETER = 156;
    public static final int STRING_LITERAL = 157;
    public static final int BLOB_LITERAL = 158;
    public static final int SINGLE_LINE_COMMENT = 159;
    public static final int MULTILINE_COMMENT = 160;
    public static final int SPACES = 161;
    public static final int UNEXPECTED_CHAR = 162;
    public static final int RULE_parse = 0;
    public static final int RULE_error = 1;
    public static final int RULE_sql_stmt_list = 2;
    public static final int RULE_sql_stmt = 3;
    public static final int RULE_alter_table_stmt = 4;
    public static final int RULE_analyze_stmt = 5;
    public static final int RULE_attach_stmt = 6;
    public static final int RULE_begin_stmt = 7;
    public static final int RULE_commit_stmt = 8;
    public static final int RULE_create_index_stmt = 9;
    public static final int RULE_create_table_stmt = 10;
    public static final int RULE_create_trigger_stmt = 11;
    public static final int RULE_create_view_stmt = 12;
    public static final int RULE_create_virtual_table_stmt = 13;
    public static final int RULE_delete_stmt = 14;
    public static final int RULE_delete_stmt_limited = 15;
    public static final int RULE_detach_stmt = 16;
    public static final int RULE_drop_index_stmt = 17;
    public static final int RULE_drop_table_stmt = 18;
    public static final int RULE_drop_trigger_stmt = 19;
    public static final int RULE_drop_view_stmt = 20;
    public static final int RULE_insert_stmt = 21;
    public static final int RULE_upsert_clause = 22;
    public static final int RULE_pragma_stmt = 23;
    public static final int RULE_reindex_stmt = 24;
    public static final int RULE_release_stmt = 25;
    public static final int RULE_rollback_stmt = 26;
    public static final int RULE_savepoint_stmt = 27;
    public static final int RULE_select_stmt = 28;
    public static final int RULE_select_or_values = 29;
    public static final int RULE_update_stmt = 30;
    public static final int RULE_update_stmt_limited = 31;
    public static final int RULE_vacuum_stmt = 32;
    public static final int RULE_column_def = 33;
    public static final int RULE_type_name = 34;
    public static final int RULE_column_constraint = 35;
    public static final int RULE_conflict_clause = 36;
    public static final int RULE_expr = 37;
    public static final int RULE_foreign_key_clause = 38;
    public static final int RULE_raise_function = 39;
    public static final int RULE_indexed_column = 40;
    public static final int RULE_table_constraint = 41;
    public static final int RULE_with_clause = 42;
    public static final int RULE_common_table_expression = 43;
    public static final int RULE_qualified_table_name = 44;
    public static final int RULE_order_clause = 45;
    public static final int RULE_ordering_term = 46;
    public static final int RULE_limit_clause = 47;
    public static final int RULE_pragma_value = 48;
    public static final int RULE_result_column = 49;
    public static final int RULE_table_or_subquery = 50;
    public static final int RULE_join_clause = 51;
    public static final int RULE_join_operator = 52;
    public static final int RULE_join_constraint = 53;
    public static final int RULE_compound_operator = 54;
    public static final int RULE_signed_number = 55;
    public static final int RULE_literal_value = 56;
    public static final int RULE_boolean_literal = 57;
    public static final int RULE_unary_operator = 58;
    public static final int RULE_binary_operator = 59;
    public static final int RULE_error_message = 60;
    public static final int RULE_module_argument = 61;
    public static final int RULE_column_alias = 62;
    public static final int RULE_column_name_list = 63;
    public static final int RULE_keyword = 64;
    public static final int RULE_name = 65;
    public static final int RULE_function_name = 66;
    public static final int RULE_schema_name = 67;
    public static final int RULE_table_function = 68;
    public static final int RULE_table_name = 69;
    public static final int RULE_table_or_index_name = 70;
    public static final int RULE_new_table_name = 71;
    public static final int RULE_column_name = 72;
    public static final int RULE_collation_name = 73;
    public static final int RULE_foreign_table = 74;
    public static final int RULE_index_name = 75;
    public static final int RULE_trigger_name = 76;
    public static final int RULE_view_name = 77;
    public static final int RULE_module_name = 78;
    public static final int RULE_pragma_name = 79;
    public static final int RULE_savepoint_name = 80;
    public static final int RULE_table_alias = 81;
    public static final int RULE_transaction_name = 82;
    public static final int RULE_any_name = 83;
    public static final String[] ruleNames;
    private static final String[] _LITERAL_NAMES;
    private static final String[] _SYMBOLIC_NAMES;
    public static final Vocabulary VOCABULARY;
    /** @deprecated */
    @Deprecated
    public static final String[] tokenNames;
    public static final String _serializedATN = "\u0003悋Ꜫ脳맭䅼㯧瞆奤\u0003¤و\u0004\u0002\t\u0002\u0004\u0003\t\u0003\u0004\u0004\t\u0004\u0004\u0005\t\u0005\u0004\u0006\t\u0006\u0004\u0007\t\u0007\u0004\b\t\b\u0004\t\t\t\u0004\n\t\n\u0004\u000b\t\u000b\u0004\f\t\f\u0004\r\t\r\u0004\u000e\t\u000e\u0004\u000f\t\u000f\u0004\u0010\t\u0010\u0004\u0011\t\u0011\u0004\u0012\t\u0012\u0004\u0013\t\u0013\u0004\u0014\t\u0014\u0004\u0015\t\u0015\u0004\u0016\t\u0016\u0004\u0017\t\u0017\u0004\u0018\t\u0018\u0004\u0019\t\u0019\u0004\u001a\t\u001a\u0004\u001b\t\u001b\u0004\u001c\t\u001c\u0004\u001d\t\u001d\u0004\u001e\t\u001e\u0004\u001f\t\u001f\u0004 \t \u0004!\t!\u0004\"\t\"\u0004#\t#\u0004$\t$\u0004%\t%\u0004&\t&\u0004'\t'\u0004(\t(\u0004)\t)\u0004*\t*\u0004+\t+\u0004,\t,\u0004-\t-\u0004.\t.\u0004/\t/\u00040\t0\u00041\t1\u00042\t2\u00043\t3\u00044\t4\u00045\t5\u00046\t6\u00047\t7\u00048\t8\u00049\t9\u0004:\t:\u0004;\t;\u0004<\t<\u0004=\t=\u0004>\t>\u0004?\t?\u0004@\t@\u0004A\tA\u0004B\tB\u0004C\tC\u0004D\tD\u0004E\tE\u0004F\tF\u0004G\tG\u0004H\tH\u0004I\tI\u0004J\tJ\u0004K\tK\u0004L\tL\u0004M\tM\u0004N\tN\u0004O\tO\u0004P\tP\u0004Q\tQ\u0004R\tR\u0004S\tS\u0004T\tT\u0004U\tU\u0003\u0002\u0003\u0002\u0007\u0002\u00ad\n\u0002\f\u0002\u000e\u0002°\u000b\u0002\u0003\u0002\u0003\u0002\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0004\u0007\u0004¸\n\u0004\f\u0004\u000e\u0004»\u000b\u0004\u0003\u0004\u0003\u0004\u0006\u0004¿\n\u0004\r\u0004\u000e\u0004À\u0003\u0004\u0007\u0004Ä\n\u0004\f\u0004\u000e\u0004Ç\u000b\u0004\u0003\u0004\u0007\u0004Ê\n\u0004\f\u0004\u000e\u0004Í\u000b\u0004\u0003\u0005\u0003\u0005\u0003\u0005\u0005\u0005Ò\n\u0005\u0005\u0005Ô\n\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0005\u0005ñ\n\u0005\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0005\u0006ø\n\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0005\u0006Ā\n\u0006\u0003\u0006\u0005\u0006ă\n\u0006\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0005\u0007Č\n\u0007\u0003\b\u0003\b\u0005\bĐ\n\b\u0003\b\u0003\b\u0003\b\u0003\b\u0003\t\u0003\t\u0005\tĘ\n\t\u0003\t\u0003\t\u0005\tĜ\n\t\u0005\tĞ\n\t\u0003\n\u0003\n\u0003\n\u0005\nģ\n\n\u0005\nĥ\n\n\u0003\u000b\u0003\u000b\u0005\u000bĩ\n\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0005\u000bį\n\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0005\u000bĴ\n\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0007\u000bĽ\n\u000b\f\u000b\u000e\u000bŀ\u000b\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0005\u000bŅ\n\u000b\u0003\f\u0003\f\u0005\fŉ\n\f\u0003\f\u0003\f\u0003\f\u0003\f\u0005\fŏ\n\f\u0003\f\u0003\f\u0003\f\u0005\fŔ\n\f\u0003\f\u0003\f\u0003\f\u0003\f\u0003\f\u0007\fś\n\f\f\f\u000e\fŞ\u000b\f\u0003\f\u0003\f\u0007\fŢ\n\f\f\f\u000e\fť\u000b\f\u0003\f\u0003\f\u0005\fũ\n\f\u0003\f\u0003\f\u0005\fŭ\n\f\u0003\r\u0003\r\u0005\rű\n\r\u0003\r\u0003\r\u0003\r\u0003\r\u0005\rŷ\n\r\u0003\r\u0003\r\u0003\r\u0005\rż\n\r\u0003\r\u0003\r\u0003\r\u0003\r\u0003\r\u0005\rƃ\n\r\u0003\r\u0003\r\u0003\r\u0003\r\u0003\r\u0003\r\u0003\r\u0007\rƌ\n\r\f\r\u000e\rƏ\u000b\r\u0005\rƑ\n\r\u0005\rƓ\n\r\u0003\r\u0003\r\u0003\r\u0003\r\u0005\rƙ\n\r\u0003\r\u0003\r\u0003\r\u0003\r\u0005\rƟ\n\r\u0003\r\u0003\r\u0005\rƣ\n\r\u0003\r\u0003\r\u0003\r\u0003\r\u0003\r\u0005\rƪ\n\r\u0003\r\u0003\r\u0006\rƮ\n\r\r\r\u000e\rƯ\u0003\r\u0003\r\u0003\u000e\u0003\u000e\u0005\u000eƶ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0005\u000eƼ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0005\u000eǁ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0007\u000eǇ\n\u000e\f\u000e\u000e\u000eǊ\u000b\u000e\u0005\u000eǌ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0005\u000fǗ\n\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0005\u000fǜ\n\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0007\u000fǥ\n\u000f\f\u000f\u000e\u000fǨ\u000b\u000f\u0003\u000f\u0003\u000f\u0005\u000fǬ\n\u000f\u0003\u0010\u0005\u0010ǯ\n\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0005\u0010Ƕ\n\u0010\u0003\u0011\u0005\u0011ǹ\n\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0005\u0011Ȁ\n\u0011\u0003\u0011\u0005\u0011ȃ\n\u0011\u0003\u0011\u0005\u0011Ȇ\n\u0011\u0003\u0012\u0003\u0012\u0005\u0012Ȋ\n\u0012\u0003\u0012\u0003\u0012\u0003\u0013\u0003\u0013\u0003\u0013\u0003\u0013\u0005\u0013Ȓ\n\u0013\u0003\u0013\u0003\u0013\u0003\u0013\u0005\u0013ȗ\n\u0013\u0003\u0013\u0003\u0013\u0003\u0014\u0003\u0014\u0003\u0014\u0003\u0014\u0005\u0014ȟ\n\u0014\u0003\u0014\u0003\u0014\u0003\u0014\u0005\u0014Ȥ\n\u0014\u0003\u0014\u0003\u0014\u0003\u0015\u0003\u0015\u0003\u0015\u0003\u0015\u0005\u0015Ȭ\n\u0015\u0003\u0015\u0003\u0015\u0003\u0015\u0005\u0015ȱ\n\u0015\u0003\u0015\u0003\u0015\u0003\u0016\u0003\u0016\u0003\u0016\u0003\u0016\u0005\u0016ȹ\n\u0016\u0003\u0016\u0003\u0016\u0003\u0016\u0005\u0016Ⱦ\n\u0016\u0003\u0016\u0003\u0016\u0003\u0017\u0005\u0017Ƀ\n\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0005\u0017ɖ\n\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0005\u0017ɜ\n\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0005\u0017ɡ\n\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0007\u0017ɧ\n\u0017\f\u0017\u000e\u0017ɪ\u000b\u0017\u0003\u0017\u0003\u0017\u0005\u0017ɮ\n\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0007\u0017ɵ\n\u0017\f\u0017\u000e\u0017ɸ\u000b\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0007\u0017ʀ\n\u0017\f\u0017\u000e\u0017ʃ\u000b\u0017\u0003\u0017\u0003\u0017\u0007\u0017ʇ\n\u0017\f\u0017\u000e\u0017ʊ\u000b\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0005\u0017ʏ\n\u0017\u0003\u0017\u0005\u0017ʒ\n\u0017\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0007\u0018ʚ\n\u0018\f\u0018\u000e\u0018ʝ\u000b\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0005\u0018ʢ\n\u0018\u0005\u0018ʤ\n\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0005\u0018ʫ\n\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0005\u0018ʲ\n\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0007\u0018ʷ\n\u0018\f\u0018\u000e\u0018ʺ\u000b\u0018\u0003\u0018\u0003\u0018\u0005\u0018ʾ\n\u0018\u0005\u0018ˀ\n\u0018\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0005\u0019ˆ\n\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0005\u0019ˏ\n\u0019\u0003\u001a\u0003\u001a\u0003\u001a\u0003\u001a\u0003\u001a\u0005\u001a˖\n\u001a\u0003\u001a\u0003\u001a\u0005\u001a˚\n\u001a\u0005\u001a˜\n\u001a\u0003\u001b\u0003\u001b\u0005\u001bˠ\n\u001b\u0003\u001b\u0003\u001b\u0003\u001c\u0003\u001c\u0003\u001c\u0005\u001c˧\n\u001c\u0005\u001c˩\n\u001c\u0003\u001c\u0003\u001c\u0005\u001c˭\n\u001c\u0003\u001c\u0005\u001c˰\n\u001c\u0003\u001d\u0003\u001d\u0003\u001d\u0003\u001e\u0005\u001e˶\n\u001e\u0003\u001e\u0003\u001e\u0003\u001e\u0003\u001e\u0007\u001e˼\n\u001e\f\u001e\u000e\u001e˿\u000b\u001e\u0003\u001e\u0005\u001ê\n\u001e\u0003\u001e\u0005\u001e̅\n\u001e\u0003\u001f\u0003\u001f\u0005\u001f̉\n\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0007\u001f̎\n\u001f\f\u001f\u000e\u001f̑\u000b\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0007\u001f̗\n\u001f\f\u001f\u000e\u001f̚\u000b\u001f\u0003\u001f\u0005\u001f̝\n\u001f\u0005\u001f̟\n\u001f\u0003\u001f\u0003\u001f\u0005\u001f̣\n\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0007\u001f̪\n\u001f\f\u001f\u000e\u001f̭\u000b\u001f\u0003\u001f\u0003\u001f\u0005\u001f̱\n\u001f\u0005\u001f̳\n\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0007\u001f̺\n\u001f\f\u001f\u000e\u001f̽\u000b\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0007\u001fͅ\n\u001f\f\u001f\u000e\u001f͈\u000b\u001f\u0003\u001f\u0003\u001f\u0007\u001f͌\n\u001f\f\u001f\u000e\u001f͏\u000b\u001f\u0005\u001f͑\n\u001f\u0003 \u0005 ͔\n \u0003 \u0003 \u0003 \u0003 \u0003 \u0003 \u0003 \u0003 \u0003 \u0003 \u0003 \u0005 ͡\n \u0003 \u0003 \u0003 \u0003 \u0005 ͧ\n \u0003 \u0003 \u0003 \u0003 \u0003 \u0005 ͮ\n \u0003 \u0003 \u0003 \u0007 ͳ\n \f \u000e Ͷ\u000b \u0003 \u0003 \u0005 ͺ\n \u0003!\u0005!ͽ\n!\u0003!\u0003!\u0003!\u0003!\u0003!\u0003!\u0003!\u0003!\u0003!\u0003!\u0003!\u0005!Ί\n!\u0003!\u0003!\u0003!\u0003!\u0005!ΐ\n!\u0003!\u0003!\u0003!\u0003!\u0003!\u0005!Η\n!\u0003!\u0003!\u0003!\u0007!Μ\n!\f!\u000e!Ο\u000b!\u0003!\u0003!\u0005!Σ\n!\u0003!\u0005!Φ\n!\u0003!\u0005!Ω\n!\u0003\"\u0003\"\u0005\"έ\n\"\u0003#\u0003#\u0005#α\n#\u0003#\u0007#δ\n#\f#\u000e#η\u000b#\u0003$\u0006$κ\n$\r$\u000e$λ\u0003$\u0003$\u0003$\u0003$\u0003$\u0003$\u0003$\u0003$\u0003$\u0003$\u0005$ψ\n$\u0003%\u0003%\u0005%ό\n%\u0003%\u0003%\u0003%\u0005%ϑ\n%\u0003%\u0003%\u0005%ϕ\n%\u0003%\u0005%Ϙ\n%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0005%Ϫ\n%\u0003%\u0003%\u0003%\u0005%ϯ\n%\u0003&\u0003&\u0003&\u0005&ϴ\n&\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0005'ϼ\n'\u0003'\u0003'\u0003'\u0005'Ё\n'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0005'Њ\n'\u0003'\u0003'\u0003'\u0007'Џ\n'\f'\u000e'В\u000b'\u0003'\u0005'Е\n'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0007'Н\n'\f'\u000e'Р\u000b'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0005'Ь\n'\u0003'\u0005'Я\n'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0005'з\n'\u0003'\u0003'\u0003'\u0003'\u0003'\u0006'о\n'\r'\u000e'п\u0003'\u0003'\u0005'ф\n'\u0003'\u0003'\u0003'\u0005'щ\n'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0005'ђ\n'\u0003'\u0003'\u0003'\u0005'ї\n'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0005'ѣ\n'\u0003'\u0003'\u0003'\u0003'\u0005'ѩ\n'\u0003'\u0003'\u0003'\u0003'\u0003'\u0005'Ѱ\n'\u0003'\u0003'\u0005'Ѵ\n'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0007'Ѽ\n'\f'\u000e'ѿ\u000b'\u0005'ҁ\n'\u0003'\u0003'\u0003'\u0003'\u0005'҇\n'\u0003'\u0003'\u0003'\u0003'\u0005'ҍ\n'\u0003'\u0003'\u0003'\u0003'\u0003'\u0007'Ҕ\n'\f'\u000e'җ\u000b'\u0005'ҙ\n'\u0003'\u0003'\u0005'ҝ\n'\u0007'ҟ\n'\f'\u000e'Ң\u000b'\u0003(\u0003(\u0003(\u0003(\u0003(\u0003(\u0007(Ҫ\n(\f(\u000e(ҭ\u000b(\u0003(\u0003(\u0005(ұ\n(\u0003(\u0003(\u0003(\u0003(\u0003(\u0003(\u0003(\u0003(\u0003(\u0003(\u0005(ҽ\n(\u0003(\u0003(\u0005(Ӂ\n(\u0007(Ӄ\n(\f(\u000e(ӆ\u000b(\u0003(\u0005(Ӊ\n(\u0003(\u0003(\u0003(\u0003(\u0003(\u0005(Ӑ\n(\u0005(Ӓ\n(\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0005)Ӛ\n)\u0003)\u0003)\u0003*\u0003*\u0005*Ӡ\n*\u0003*\u0003*\u0005*Ӥ\n*\u0003*\u0005*ӧ\n*\u0003+\u0003+\u0005+ӫ\n+\u0003+\u0003+\u0003+\u0005+Ӱ\n+\u0003+\u0003+\u0003+\u0003+\u0007+Ӷ\n+\f+\u000e+ӹ\u000b+\u0003+\u0003+\u0003+\u0003+\u0003+\u0003+\u0003+\u0003+\u0003+\u0003+\u0003+\u0003+\u0003+\u0003+\u0007+ԉ\n+\f+\u000e+Ԍ\u000b+\u0003+\u0003+\u0003+\u0005+ԑ\n+\u0003,\u0003,\u0005,ԕ\n,\u0003,\u0003,\u0003,\u0007,Ԛ\n,\f,\u000e,ԝ\u000b,\u0003-\u0003-\u0003-\u0003-\u0003-\u0007-Ԥ\n-\f-\u000e-ԧ\u000b-\u0003-\u0003-\u0005-ԫ\n-\u0003-\u0003-\u0003-\u0003-\u0003-\u0003.\u0003.\u0003.\u0005.Ե\n.\u0003.\u0003.\u0003.\u0005.Ժ\n.\u0003.\u0003.\u0003.\u0003.\u0003.\u0005.Ձ\n.\u0003/\u0003/\u0003/\u0003/\u0003/\u0007/Ո\n/\f/\u000e/Ջ\u000b/\u00030\u00030\u00030\u00050Ր\n0\u00030\u00050Փ\n0\u00031\u00031\u00031\u00031\u00051ՙ\n1\u00032\u00032\u00032\u00032\u00052՟\n2\u00033\u00033\u00033\u00033\u00033\u00033\u00033\u00053ը\n3\u00033\u00053ի\n3\u00053խ\n3\u00034\u00034\u00034\u00054ղ\n4\u00034\u00034\u00054ն\n4\u00034\u00054չ\n4\u00034\u00034\u00034\u00034\u00034\u00054ր\n4\u00034\u00034\u00034\u00054օ\n4\u00034\u00034\u00034\u00034\u00034\u00074\u058c\n4\f4\u000e4֏\u000b4\u00054֑\n4\u00034\u00034\u00054֕\n4\u00034\u00054֘\n4\u00034\u00034\u00034\u00034\u00074֞\n4\f4\u000e4֡\u000b4\u00034\u00054֤\n4\u00034\u00034\u00034\u00034\u00034\u00034\u00054֬\n4\u00034\u00054֯\n4\u00054ֱ\n4\u00035\u00035\u00035\u00035\u00035\u00075ָ\n5\f5\u000e5ֻ\u000b5\u00036\u00036\u00056ֿ\n6\u00036\u00036\u00056׃\n6\u00036\u00036\u00056ׇ\n6\u00036\u00056\u05ca\n6\u00037\u00037\u00037\u00037\u00037\u00037\u00037\u00077ד\n7\f7\u000e7ז\u000b7\u00037\u00037\u00057ך\n7\u00038\u00038\u00038\u00038\u00038\u00058ס\n8\u00039\u00059פ\n9\u00039\u00039\u0003:\u0003:\u0003:\u0003:\u0003:\u0003:\u0003:\u0003:\u0005:װ\n:\u0003;\u0003;\u0003<\u0003<\u0003=\u0003=\u0003=\u0003=\u0003=\u0003=\u0003=\u0003=\u0005=\u05fe\n=\u0003>\u0003>\u0003?\u0003?\u0005?\u0604\n?\u0003@\u0003@\u0003A\u0003A\u0003A\u0003A\u0007A،\nA\fA\u000eA؏\u000bA\u0003A\u0003A\u0003B\u0003B\u0003C\u0003C\u0003D\u0003D\u0003E\u0003E\u0003F\u0003F\u0003G\u0003G\u0003H\u0003H\u0003I\u0003I\u0003J\u0003J\u0003K\u0003K\u0003L\u0003L\u0003M\u0003M\u0003N\u0003N\u0003O\u0003O\u0003P\u0003P\u0003Q\u0003Q\u0003R\u0003R\u0003S\u0003S\u0003S\u0003S\u0003S\u0003S\u0005Sػ\nS\u0003T\u0003T\u0003U\u0003U\u0003U\u0003U\u0003U\u0003U\u0003U\u0005Uن\nU\u0003U\u0004Ŝλ\u0003LV\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e ¢¤¦¨\u0002\u0015\u0005\u0002>>IIVV\u0004\u000233FF\u0003\u0002\u0087\u0088\u0004\u0002!!BB\u0004\u0002&&@@\u0007\u0002\u001d\u001dLLUU~~\u0081\u0081\u0006\u0002QQeeggzz\u0004\u0002??\u008f\u008f\u0005\u0002\u001d\u001dLL\u0081\u0081\u0004\u0002\u0007\u0007nn\u0003\u0002\n\u000b\u0003\u0002\u001b\u001c\u0004\u0002\n\fjj\u0004\u0002\t\t\u000e\u000f\u0003\u0002\u0010\u0013\u0003\u0002\u0014\u0017\u0004\u0002\b\b\u0018\u001a\u0004\u0002\u009c\u009c\u009f\u009f\u0003\u0002\u001d\u0098\u0002ܾ\u0002®\u0003\u0002\u0002\u0002\u0004³\u0003\u0002\u0002\u0002\u0006¹\u0003\u0002\u0002\u0002\bÓ\u0003\u0002\u0002\u0002\nò\u0003\u0002\u0002\u0002\fĄ\u0003\u0002\u0002\u0002\u000eč\u0003\u0002\u0002\u0002\u0010ĕ\u0003\u0002\u0002\u0002\u0012ğ\u0003\u0002\u0002\u0002\u0014Ħ\u0003\u0002\u0002\u0002\u0016ņ\u0003\u0002\u0002\u0002\u0018Ů\u0003\u0002\u0002\u0002\u001aƳ\u0003\u0002\u0002\u0002\u001cǐ\u0003\u0002\u0002\u0002\u001eǮ\u0003\u0002\u0002\u0002 Ǹ\u0003\u0002\u0002\u0002\"ȇ\u0003\u0002\u0002\u0002$ȍ\u0003\u0002\u0002\u0002&Ț\u0003\u0002\u0002\u0002(ȧ\u0003\u0002\u0002\u0002*ȴ\u0003\u0002\u0002\u0002,ɂ\u0003\u0002\u0002\u0002.ʓ\u0003\u0002\u0002\u00020ˁ\u0003\u0002\u0002\u00022ː\u0003\u0002\u0002\u00024˝\u0003\u0002\u0002\u00026ˣ\u0003\u0002\u0002\u00028˱\u0003\u0002\u0002\u0002:˵\u0003\u0002\u0002\u0002<͐\u0003\u0002\u0002\u0002>͓\u0003\u0002\u0002\u0002@ͼ\u0003\u0002\u0002\u0002BΪ\u0003\u0002\u0002\u0002Dή\u0003\u0002\u0002\u0002Fι\u0003\u0002\u0002\u0002Hϋ\u0003\u0002\u0002\u0002Jϳ\u0003\u0002\u0002\u0002Lш\u0003\u0002\u0002\u0002Nң\u0003\u0002\u0002\u0002Pӓ\u0003\u0002\u0002\u0002Rӟ\u0003\u0002\u0002\u0002TӪ\u0003\u0002\u0002\u0002VԒ\u0003\u0002\u0002\u0002XԞ\u0003\u0002\u0002\u0002ZԴ\u0003\u0002\u0002\u0002\\Ղ\u0003\u0002\u0002\u0002^Ռ\u0003\u0002\u0002\u0002`Ք\u0003\u0002\u0002\u0002b՞\u0003\u0002\u0002\u0002dլ\u0003\u0002\u0002\u0002fְ\u0003\u0002\u0002\u0002hֲ\u0003\u0002\u0002\u0002j\u05c9\u0003\u0002\u0002\u0002lי\u0003\u0002\u0002\u0002nנ\u0003\u0002\u0002\u0002pף\u0003\u0002\u0002\u0002r\u05ef\u0003\u0002\u0002\u0002tױ\u0003\u0002\u0002\u0002v׳\u0003\u0002\u0002\u0002x\u05fd\u0003\u0002\u0002\u0002z\u05ff\u0003\u0002\u0002\u0002|\u0603\u0003\u0002\u0002\u0002~\u0605\u0003\u0002\u0002\u0002\u0080؇\u0003\u0002\u0002\u0002\u0082ؒ\u0003\u0002\u0002\u0002\u0084ؔ\u0003\u0002\u0002\u0002\u0086ؖ\u0003\u0002\u0002\u0002\u0088ؘ\u0003\u0002\u0002\u0002\u008aؚ\u0003\u0002\u0002\u0002\u008c\u061c\u0003\u0002\u0002\u0002\u008e؞\u0003\u0002\u0002\u0002\u0090ؠ\u0003\u0002\u0002\u0002\u0092آ\u0003\u0002\u0002\u0002\u0094ؤ\u0003\u0002\u0002\u0002\u0096ئ\u0003\u0002\u0002\u0002\u0098ب\u0003\u0002\u0002\u0002\u009aت\u0003\u0002\u0002\u0002\u009cج\u0003\u0002\u0002\u0002\u009eخ\u0003\u0002\u0002\u0002 ذ\u0003\u0002\u0002\u0002¢ز\u0003\u0002\u0002\u0002¤غ\u0003\u0002\u0002\u0002¦ؼ\u0003\u0002\u0002\u0002¨م\u0003\u0002\u0002\u0002ª\u00ad\u0005\u0006\u0004\u0002«\u00ad\u0005\u0004\u0003\u0002¬ª\u0003\u0002\u0002\u0002¬«\u0003\u0002\u0002\u0002\u00ad°\u0003\u0002\u0002\u0002®¬\u0003\u0002\u0002\u0002®¯\u0003\u0002\u0002\u0002¯±\u0003\u0002\u0002\u0002°®\u0003\u0002\u0002\u0002±²\u0007\u0002\u0002\u0003²\u0003\u0003\u0002\u0002\u0002³´\u0007¤\u0002\u0002´µ\b\u0003\u0001\u0002µ\u0005\u0003\u0002\u0002\u0002¶¸\u0007\u0003\u0002\u0002·¶\u0003\u0002\u0002\u0002¸»\u0003\u0002\u0002\u0002¹·\u0003\u0002\u0002\u0002¹º\u0003\u0002\u0002\u0002º¼\u0003\u0002\u0002\u0002»¹\u0003\u0002\u0002\u0002¼Å\u0005\b\u0005\u0002½¿\u0007\u0003\u0002\u0002¾½\u0003\u0002\u0002\u0002¿À\u0003\u0002\u0002\u0002À¾\u0003\u0002\u0002\u0002ÀÁ\u0003\u0002\u0002\u0002ÁÂ\u0003\u0002\u0002\u0002ÂÄ\u0005\b\u0005\u0002Ã¾\u0003\u0002\u0002\u0002ÄÇ\u0003\u0002\u0002\u0002ÅÃ\u0003\u0002\u0002\u0002ÅÆ\u0003\u0002\u0002\u0002ÆË\u0003\u0002\u0002\u0002ÇÅ\u0003\u0002\u0002\u0002ÈÊ\u0007\u0003\u0002\u0002ÉÈ\u0003\u0002\u0002\u0002ÊÍ\u0003\u0002\u0002\u0002ËÉ\u0003\u0002\u0002\u0002ËÌ\u0003\u0002\u0002\u0002Ì\u0007\u0003\u0002\u0002\u0002ÍË\u0003\u0002\u0002\u0002ÎÑ\u0007K\u0002\u0002ÏÐ\u0007v\u0002\u0002ÐÒ\u0007s\u0002\u0002ÑÏ\u0003\u0002\u0002\u0002ÑÒ\u0003\u0002\u0002\u0002ÒÔ\u0003\u0002\u0002\u0002ÓÎ\u0003\u0002\u0002\u0002ÓÔ\u0003\u0002\u0002\u0002Ôð\u0003\u0002\u0002\u0002Õñ\u0005\n\u0006\u0002Öñ\u0005\f\u0007\u0002×ñ\u0005\u000e\b\u0002Øñ\u0005\u0010\t\u0002Ùñ\u0005\u0012\n\u0002Úñ\u0005\u0014\u000b\u0002Ûñ\u0005\u0016\f\u0002Üñ\u0005\u0018\r\u0002Ýñ\u0005\u001a\u000e\u0002Þñ\u0005\u001c\u000f\u0002ßñ\u0005\u001e\u0010\u0002àñ\u0005 \u0011\u0002áñ\u0005\"\u0012\u0002âñ\u0005$\u0013\u0002ãñ\u0005&\u0014\u0002äñ\u0005(\u0015\u0002åñ\u0005*\u0016\u0002æñ\u0005,\u0017\u0002çñ\u00050\u0019\u0002èñ\u00052\u001a\u0002éñ\u00054\u001b\u0002êñ\u00056\u001c\u0002ëñ\u00058\u001d\u0002ìñ\u0005:\u001e\u0002íñ\u0005> \u0002îñ\u0005@!\u0002ïñ\u0005B\"\u0002ðÕ\u0003\u0002\u0002\u0002ðÖ\u0003\u0002\u0002\u0002ð×\u0003\u0002\u0002\u0002ðØ\u0003\u0002\u0002\u0002ðÙ\u0003\u0002\u0002\u0002ðÚ\u0003\u0002\u0002\u0002ðÛ\u0003\u0002\u0002\u0002ðÜ\u0003\u0002\u0002\u0002ðÝ\u0003\u0002\u0002\u0002ðÞ\u0003\u0002\u0002\u0002ðß\u0003\u0002\u0002\u0002ðà\u0003\u0002\u0002\u0002ðá\u0003\u0002\u0002\u0002ðâ\u0003\u0002\u0002\u0002ðã\u0003\u0002\u0002\u0002ðä\u0003\u0002\u0002\u0002ðå\u0003\u0002\u0002\u0002ðæ\u0003\u0002\u0002\u0002ðç\u0003\u0002\u0002\u0002ðè\u0003\u0002\u0002\u0002ðé\u0003\u0002\u0002\u0002ðê\u0003\u0002\u0002\u0002ðë\u0003\u0002\u0002\u0002ðì\u0003\u0002\u0002\u0002ðí\u0003\u0002\u0002\u0002ðî\u0003\u0002\u0002\u0002ðï\u0003\u0002\u0002\u0002ñ\t\u0003\u0002\u0002\u0002òó\u0007\"\u0002\u0002ó÷\u0007\u0086\u0002\u0002ôõ\u0005\u0088E\u0002õö\u0007\u0004\u0002\u0002öø\u0003\u0002\u0002\u0002÷ô\u0003\u0002\u0002\u0002÷ø\u0003\u0002\u0002\u0002øù\u0003\u0002\u0002\u0002ùĂ\u0005\u008cG\u0002úû\u0007}\u0002\u0002ûü\u0007\u008a\u0002\u0002üă\u0005\u0090I\u0002ýÿ\u0007\u001f\u0002\u0002þĀ\u00072\u0002\u0002ÿþ\u0003\u0002\u0002\u0002ÿĀ\u0003\u0002\u0002\u0002Āā\u0003\u0002\u0002\u0002āă\u0005D#\u0002Ăú\u0003\u0002\u0002\u0002Ăý\u0003\u0002\u0002\u0002ă\u000b\u0003\u0002\u0002\u0002Ąċ\u0007#\u0002\u0002ąČ\u0005\u0088E\u0002ĆČ\u0005\u008eH\u0002ćĈ\u0005\u0088E\u0002Ĉĉ\u0007\u0004\u0002\u0002ĉĊ\u0005\u008eH\u0002ĊČ\u0003\u0002\u0002\u0002ċą\u0003\u0002\u0002\u0002ċĆ\u0003\u0002\u0002\u0002ċć\u0003\u0002\u0002\u0002ċČ\u0003\u0002\u0002\u0002Č\r\u0003\u0002\u0002\u0002čď\u0007'\u0002\u0002ĎĐ\u0007;\u0002\u0002ďĎ\u0003\u0002\u0002\u0002ďĐ\u0003\u0002\u0002\u0002Đđ\u0003\u0002\u0002\u0002đĒ\u0005L'\u0002Ēē\u0007%\u0002\u0002ēĔ\u0005\u0088E\u0002Ĕ\u000f\u0003\u0002\u0002\u0002ĕė\u0007*\u0002\u0002ĖĘ\t\u0002\u0002\u0002ėĖ\u0003\u0002\u0002\u0002ėĘ\u0003\u0002\u0002\u0002Ęĝ\u0003\u0002\u0002\u0002ęě\u0007\u008b\u0002\u0002ĚĜ\u0005¦T\u0002ěĚ\u0003\u0002\u0002\u0002ěĜ\u0003\u0002\u0002\u0002ĜĞ\u0003\u0002\u0002\u0002ĝę\u0003\u0002\u0002\u0002ĝĞ\u0003\u0002\u0002\u0002Ğ\u0011\u0003\u0002\u0002\u0002ğĤ\t\u0003\u0002\u0002ĠĢ\u0007\u008b\u0002\u0002ġģ\u0005¦T\u0002Ģġ\u0003\u0002\u0002\u0002Ģģ\u0003\u0002\u0002\u0002ģĥ\u0003\u0002\u0002\u0002ĤĠ\u0003\u0002\u0002\u0002Ĥĥ\u0003\u0002\u0002\u0002ĥ\u0013\u0003\u0002\u0002\u0002ĦĨ\u00076\u0002\u0002ħĩ\u0007\u008e\u0002\u0002Ĩħ\u0003\u0002\u0002\u0002Ĩĩ\u0003\u0002\u0002\u0002ĩĪ\u0003\u0002\u0002\u0002ĪĮ\u0007X\u0002\u0002īĬ\u0007T\u0002\u0002Ĭĭ\u0007j\u0002\u0002ĭį\u0007J\u0002\u0002Įī\u0003\u0002\u0002\u0002Įį\u0003\u0002\u0002\u0002įĳ\u0003\u0002\u0002\u0002İı\u0005\u0088E\u0002ıĲ\u0007\u0004\u0002\u0002ĲĴ\u0003\u0002\u0002\u0002ĳİ\u0003\u0002\u0002\u0002ĳĴ\u0003\u0002\u0002\u0002Ĵĵ\u0003\u0002\u0002\u0002ĵĶ\u0005\u0098M\u0002Ķķ\u0007o\u0002\u0002ķĸ\u0005\u008cG\u0002ĸĹ\u0007\u0005\u0002\u0002Ĺľ\u0005R*\u0002ĺĻ\u0007\u0007\u0002\u0002ĻĽ\u0005R*\u0002ļĺ\u0003\u0002\u0002\u0002Ľŀ\u0003\u0002\u0002\u0002ľļ\u0003\u0002\u0002\u0002ľĿ\u0003\u0002\u0002\u0002ĿŁ\u0003\u0002\u0002\u0002ŀľ\u0003\u0002\u0002\u0002Łń\u0007\u0006\u0002\u0002łŃ\u0007\u0096\u0002\u0002ŃŅ\u0005L'\u0002ńł\u0003\u0002\u0002\u0002ńŅ\u0003\u0002\u0002\u0002Ņ\u0015\u0003\u0002\u0002\u0002ņň\u00076\u0002\u0002Ňŉ\t\u0004\u0002\u0002ňŇ\u0003\u0002\u0002\u0002ňŉ\u0003\u0002\u0002\u0002ŉŊ\u0003\u0002\u0002\u0002ŊŎ\u0007\u0086\u0002\u0002ŋŌ\u0007T\u0002\u0002Ōō\u0007j\u0002\u0002ōŏ\u0007J\u0002\u0002Ŏŋ\u0003\u0002\u0002\u0002Ŏŏ\u0003\u0002\u0002\u0002ŏœ\u0003\u0002\u0002\u0002Őő\u0005\u0088E\u0002őŒ\u0007\u0004\u0002\u0002ŒŔ\u0003\u0002\u0002\u0002œŐ\u0003\u0002\u0002\u0002œŔ\u0003\u0002\u0002\u0002Ŕŕ\u0003\u0002\u0002\u0002ŕŬ\u0005\u008cG\u0002Ŗŗ\u0007\u0005\u0002\u0002ŗŜ\u0005D#\u0002Řř\u0007\u0007\u0002\u0002řś\u0005D#\u0002ŚŘ\u0003\u0002\u0002\u0002śŞ\u0003\u0002\u0002\u0002Ŝŝ\u0003\u0002\u0002\u0002ŜŚ\u0003\u0002\u0002\u0002ŝţ\u0003\u0002\u0002\u0002ŞŜ\u0003\u0002\u0002\u0002şŠ\u0007\u0007\u0002\u0002ŠŢ\u0005T+\u0002šş\u0003\u0002\u0002\u0002Ţť\u0003\u0002\u0002\u0002ţš\u0003\u0002\u0002\u0002ţŤ\u0003\u0002\u0002\u0002ŤŦ\u0003\u0002\u0002\u0002ťţ\u0003\u0002\u0002\u0002ŦŨ\u0007\u0006\u0002\u0002ŧũ\u0007\u0099\u0002\u0002Ũŧ\u0003\u0002\u0002\u0002Ũũ\u0003\u0002\u0002\u0002ũŭ\u0003\u0002\u0002\u0002Ūū\u0007%\u0002\u0002ūŭ\u0005:\u001e\u0002ŬŖ\u0003\u0002\u0002\u0002ŬŪ\u0003\u0002\u0002\u0002ŭ\u0017\u0003\u0002\u0002\u0002ŮŰ\u00076\u0002\u0002ůű\t\u0004\u0002\u0002Űů\u0003\u0002\u0002\u0002Űű\u0003\u0002\u0002\u0002űŲ\u0003\u0002\u0002\u0002ŲŶ\u0007\u008c\u0002\u0002ųŴ\u0007T\u0002\u0002Ŵŵ\u0007j\u0002\u0002ŵŷ\u0007J\u0002\u0002Ŷų\u0003\u0002\u0002\u0002Ŷŷ\u0003\u0002\u0002\u0002ŷŻ\u0003\u0002\u0002\u0002ŸŹ\u0005\u0088E\u0002Źź\u0007\u0004\u0002\u0002źż\u0003\u0002\u0002\u0002ŻŸ\u0003\u0002\u0002\u0002Żż\u0003\u0002\u0002\u0002żŽ\u0003\u0002\u0002\u0002ŽƂ\u0005\u009aN\u0002žƃ\u0007)\u0002\u0002ſƃ\u0007 \u0002\u0002ƀƁ\u0007]\u0002\u0002Ɓƃ\u0007m\u0002\u0002Ƃž\u0003\u0002\u0002\u0002Ƃſ\u0003\u0002\u0002\u0002Ƃƀ\u0003\u0002\u0002\u0002Ƃƃ\u0003\u0002\u0002\u0002ƃƒ\u0003\u0002\u0002\u0002ƄƓ\u0007?\u0002\u0002ƅƓ\u0007\\\u0002\u0002ƆƐ\u0007\u008f\u0002\u0002Ƈƈ\u0007m\u0002\u0002ƈƍ\u0005\u0092J\u0002ƉƊ\u0007\u0007\u0002\u0002Ɗƌ\u0005\u0092J\u0002ƋƉ\u0003\u0002\u0002\u0002ƌƏ\u0003\u0002\u0002\u0002ƍƋ\u0003\u0002\u0002\u0002ƍƎ\u0003\u0002\u0002\u0002ƎƑ\u0003\u0002\u0002\u0002Əƍ\u0003\u0002\u0002\u0002ƐƇ\u0003\u0002\u0002\u0002ƐƑ\u0003\u0002\u0002\u0002ƑƓ\u0003\u0002\u0002\u0002ƒƄ\u0003\u0002\u0002\u0002ƒƅ\u0003\u0002\u0002\u0002ƒƆ\u0003\u0002\u0002\u0002ƓƔ\u0003\u0002\u0002\u0002ƔƘ\u0007o\u0002\u0002ƕƖ\u0005\u0088E\u0002ƖƗ\u0007\u0004\u0002\u0002Ɨƙ\u0003\u0002\u0002\u0002Ƙƕ\u0003\u0002\u0002\u0002Ƙƙ\u0003\u0002\u0002\u0002ƙƚ\u0003\u0002\u0002\u0002ƚƞ\u0005\u008cG\u0002ƛƜ\u0007M\u0002\u0002ƜƝ\u0007D\u0002\u0002ƝƟ\u0007\u0082\u0002\u0002ƞƛ\u0003\u0002\u0002\u0002ƞƟ\u0003\u0002\u0002\u0002ƟƢ\u0003\u0002\u0002\u0002Ơơ\u0007\u0095\u0002\u0002ơƣ\u0005L'\u0002ƢƠ\u0003\u0002\u0002\u0002Ƣƣ\u0003\u0002\u0002\u0002ƣƤ\u0003\u0002\u0002\u0002Ƥƭ\u0007*\u0002\u0002ƥƪ\u0005> \u0002Ʀƪ\u0005,\u0017\u0002Ƨƪ\u0005\u001e\u0010\u0002ƨƪ\u0005:\u001e\u0002Ʃƥ\u0003\u0002\u0002\u0002ƩƦ\u0003\u0002\u0002\u0002ƩƧ\u0003\u0002\u0002\u0002Ʃƨ\u0003\u0002\u0002\u0002ƪƫ\u0003\u0002\u0002\u0002ƫƬ\u0007\u0003\u0002\u0002ƬƮ\u0003\u0002\u0002\u0002ƭƩ\u0003\u0002\u0002\u0002ƮƯ\u0003\u0002\u0002\u0002Ưƭ\u0003\u0002\u0002\u0002Ưư\u0003\u0002\u0002\u0002ưƱ\u0003\u0002\u0002\u0002ƱƲ\u0007F\u0002\u0002Ʋ\u0019\u0003\u0002\u0002\u0002ƳƵ\u00076\u0002\u0002ƴƶ\t\u0004\u0002\u0002Ƶƴ\u0003\u0002\u0002\u0002Ƶƶ\u0003\u0002\u0002\u0002ƶƷ\u0003\u0002\u0002\u0002Ʒƻ\u0007\u0093\u0002\u0002Ƹƹ\u0007T\u0002\u0002ƹƺ\u0007j\u0002\u0002ƺƼ\u0007J\u0002\u0002ƻƸ\u0003\u0002\u0002\u0002ƻƼ\u0003\u0002\u0002\u0002Ƽǀ\u0003\u0002\u0002\u0002ƽƾ\u0005\u0088E\u0002ƾƿ\u0007\u0004\u0002\u0002ƿǁ\u0003\u0002\u0002\u0002ǀƽ\u0003\u0002\u0002\u0002ǀǁ\u0003\u0002\u0002\u0002ǁǂ\u0003\u0002\u0002\u0002ǂǋ\u0005\u009cO\u0002ǃǈ\u0005\u0092J\u0002Ǆǅ\u0007\u0007\u0002\u0002ǅǇ\u0005\u0092J\u0002ǆǄ\u0003\u0002\u0002\u0002ǇǊ\u0003\u0002\u0002\u0002ǈǆ\u0003\u0002\u0002\u0002ǈǉ\u0003\u0002\u0002\u0002ǉǌ\u0003\u0002\u0002\u0002Ǌǈ\u0003\u0002\u0002\u0002ǋǃ\u0003\u0002\u0002\u0002ǋǌ\u0003\u0002\u0002\u0002ǌǍ\u0003\u0002\u0002\u0002Ǎǎ\u0007%\u0002\u0002ǎǏ\u0005:\u001e\u0002Ǐ\u001b\u0003\u0002\u0002\u0002ǐǑ\u00076\u0002\u0002Ǒǒ\u0007\u0094\u0002\u0002ǒǖ\u0007\u0086\u0002\u0002Ǔǔ\u0007T\u0002\u0002ǔǕ\u0007j\u0002\u0002ǕǗ\u0007J\u0002\u0002ǖǓ\u0003\u0002\u0002\u0002ǖǗ\u0003\u0002\u0002\u0002ǗǛ\u0003\u0002\u0002\u0002ǘǙ\u0005\u0088E\u0002Ǚǚ\u0007\u0004\u0002\u0002ǚǜ\u0003\u0002\u0002\u0002Ǜǘ\u0003\u0002\u0002\u0002Ǜǜ\u0003\u0002\u0002\u0002ǜǝ\u0003\u0002\u0002\u0002ǝǞ\u0005\u008cG\u0002Ǟǟ\u0007\u0090\u0002\u0002ǟǫ\u0005\u009eP\u0002Ǡǡ\u0007\u0005\u0002\u0002ǡǦ\u0005|?\u0002Ǣǣ\u0007\u0007\u0002\u0002ǣǥ\u0005|?\u0002ǤǢ\u0003\u0002\u0002\u0002ǥǨ\u0003\u0002\u0002\u0002ǦǤ\u0003\u0002\u0002\u0002Ǧǧ\u0003\u0002\u0002\u0002ǧǩ\u0003\u0002\u0002\u0002ǨǦ\u0003\u0002\u0002\u0002ǩǪ\u0007\u0006\u0002\u0002ǪǬ\u0003\u0002\u0002\u0002ǫǠ\u0003\u0002\u0002\u0002ǫǬ\u0003\u0002\u0002\u0002Ǭ\u001d\u0003\u0002\u0002\u0002ǭǯ\u0005V,\u0002Ǯǭ\u0003\u0002\u0002\u0002Ǯǯ\u0003\u0002\u0002\u0002ǯǰ\u0003\u0002\u0002\u0002ǰǱ\u0007?\u0002\u0002Ǳǲ\u0007O\u0002\u0002ǲǵ\u0005Z.\u0002ǳǴ\u0007\u0096\u0002\u0002ǴǶ\u0005L'\u0002ǵǳ\u0003\u0002\u0002\u0002ǵǶ\u0003\u0002\u0002\u0002Ƕ\u001f\u0003\u0002\u0002\u0002Ƿǹ\u0005V,\u0002ǸǷ\u0003\u0002\u0002\u0002Ǹǹ\u0003\u0002\u0002\u0002ǹǺ\u0003\u0002\u0002\u0002Ǻǻ\u0007?\u0002\u0002ǻǼ\u0007O\u0002\u0002Ǽǿ\u0005Z.\u0002ǽǾ\u0007\u0096\u0002\u0002ǾȀ\u0005L'\u0002ǿǽ\u0003\u0002\u0002\u0002ǿȀ\u0003\u0002\u0002\u0002Ȁȅ\u0003\u0002\u0002\u0002ȁȃ\u0005\\/\u0002Ȃȁ\u0003\u0002\u0002\u0002Ȃȃ\u0003\u0002\u0002\u0002ȃȄ\u0003\u0002\u0002\u0002ȄȆ\u0005`1\u0002ȅȂ\u0003\u0002\u0002\u0002ȅȆ\u0003\u0002\u0002\u0002Ȇ!\u0003\u0002\u0002\u0002ȇȉ\u0007A\u0002\u0002ȈȊ\u0007;\u0002\u0002ȉȈ\u0003\u0002\u0002\u0002ȉȊ\u0003\u0002\u0002\u0002Ȋȋ\u0003\u0002\u0002\u0002ȋȌ\u0005\u0088E\u0002Ȍ#\u0003\u0002\u0002\u0002ȍȎ\u0007C\u0002\u0002Ȏȑ\u0007X\u0002\u0002ȏȐ\u0007T\u0002\u0002ȐȒ\u0007J\u0002\u0002ȑȏ\u0003\u0002\u0002\u0002ȑȒ\u0003\u0002\u0002\u0002ȒȖ\u0003\u0002\u0002\u0002ȓȔ\u0005\u0088E\u0002Ȕȕ\u0007\u0004\u0002\u0002ȕȗ\u0003\u0002\u0002\u0002Ȗȓ\u0003\u0002\u0002\u0002Ȗȗ\u0003\u0002\u0002\u0002ȗȘ\u0003\u0002\u0002\u0002Șș\u0005\u0098M\u0002ș%\u0003\u0002\u0002\u0002Țț\u0007C\u0002\u0002țȞ\u0007\u0086\u0002\u0002Ȝȝ\u0007T\u0002\u0002ȝȟ\u0007J\u0002\u0002ȞȜ\u0003\u0002\u0002\u0002Ȟȟ\u0003\u0002\u0002\u0002ȟȣ\u0003\u0002\u0002\u0002Ƞȡ\u0005\u0088E\u0002ȡȢ\u0007\u0004\u0002\u0002ȢȤ\u0003\u0002\u0002\u0002ȣȠ\u0003\u0002\u0002\u0002ȣȤ\u0003\u0002\u0002\u0002Ȥȥ\u0003\u0002\u0002\u0002ȥȦ\u0005\u008cG\u0002Ȧ'\u0003\u0002\u0002\u0002ȧȨ\u0007C\u0002\u0002Ȩȫ\u0007\u008c\u0002\u0002ȩȪ\u0007T\u0002\u0002ȪȬ\u0007J\u0002\u0002ȫȩ\u0003\u0002\u0002\u0002ȫȬ\u0003\u0002\u0002\u0002ȬȰ\u0003\u0002\u0002\u0002ȭȮ\u0005\u0088E\u0002Ȯȯ\u0007\u0004\u0002\u0002ȯȱ\u0003\u0002\u0002\u0002Ȱȭ\u0003\u0002\u0002\u0002Ȱȱ\u0003\u0002\u0002\u0002ȱȲ\u0003\u0002\u0002\u0002Ȳȳ\u0005\u009aN\u0002ȳ)\u0003\u0002\u0002\u0002ȴȵ\u0007C\u0002\u0002ȵȸ\u0007\u0093\u0002\u0002ȶȷ\u0007T\u0002\u0002ȷȹ\u0007J\u0002\u0002ȸȶ\u0003\u0002\u0002\u0002ȸȹ\u0003\u0002\u0002\u0002ȹȽ\u0003\u0002\u0002\u0002ȺȻ\u0005\u0088E\u0002Ȼȼ\u0007\u0004\u0002\u0002ȼȾ\u0003\u0002\u0002\u0002ȽȺ\u0003\u0002\u0002\u0002ȽȾ\u0003\u0002\u0002\u0002Ⱦȿ\u0003\u0002\u0002\u0002ȿɀ\u0005\u009cO\u0002ɀ+\u0003\u0002\u0002\u0002ɁɃ\u0005V,\u0002ɂɁ\u0003\u0002\u0002\u0002ɂɃ\u0003\u0002\u0002\u0002Ƀɕ\u0003\u0002\u0002\u0002Ʉɖ\u0007\\\u0002\u0002Ʌɖ\u0007~\u0002\u0002Ɇɇ\u0007\\\u0002\u0002ɇɈ\u0007p\u0002\u0002Ɉɖ\u0007~\u0002\u0002ɉɊ\u0007\\\u0002\u0002Ɋɋ\u0007p\u0002\u0002ɋɖ\u0007\u0081\u0002\u0002Ɍɍ\u0007\\\u0002\u0002ɍɎ\u0007p\u0002\u0002Ɏɖ\u0007\u001d\u0002\u0002ɏɐ\u0007\\\u0002\u0002ɐɑ\u0007p\u0002\u0002ɑɖ\u0007L\u0002\u0002ɒɓ\u0007\\\u0002\u0002ɓɔ\u0007p\u0002\u0002ɔɖ\u0007U\u0002\u0002ɕɄ\u0003\u0002\u0002\u0002ɕɅ\u0003\u0002\u0002\u0002ɕɆ\u0003\u0002\u0002\u0002ɕɉ\u0003\u0002\u0002\u0002ɕɌ\u0003\u0002\u0002\u0002ɕɏ\u0003\u0002\u0002\u0002ɕɒ\u0003\u0002\u0002\u0002ɖɗ\u0003\u0002\u0002\u0002ɗɛ\u0007_\u0002\u0002ɘə\u0005\u0088E\u0002əɚ\u0007\u0004\u0002\u0002ɚɜ\u0003\u0002\u0002\u0002ɛɘ\u0003\u0002\u0002\u0002ɛɜ\u0003\u0002\u0002\u0002ɜɝ\u0003\u0002\u0002\u0002ɝɠ\u0005\u008cG\u0002ɞɟ\u0007%\u0002\u0002ɟɡ\u0005¤S\u0002ɠɞ\u0003\u0002\u0002\u0002ɠɡ\u0003\u0002\u0002\u0002ɡɭ\u0003\u0002\u0002\u0002ɢɣ\u0007\u0005\u0002\u0002ɣɨ\u0005\u0092J\u0002ɤɥ\u0007\u0007\u0002\u0002ɥɧ\u0005\u0092J\u0002ɦɤ\u0003\u0002\u0002\u0002ɧɪ\u0003\u0002\u0002\u0002ɨɦ\u0003\u0002\u0002\u0002ɨɩ\u0003\u0002\u0002\u0002ɩɫ\u0003\u0002\u0002\u0002ɪɨ\u0003\u0002\u0002\u0002ɫɬ\u0007\u0006\u0002\u0002ɬɮ\u0003\u0002\u0002\u0002ɭɢ\u0003\u0002\u0002\u0002ɭɮ\u0003\u0002\u0002\u0002ɮʎ\u0003\u0002\u0002\u0002ɯɰ\u0007\u0092\u0002\u0002ɰɱ\u0007\u0005\u0002\u0002ɱɶ\u0005L'\u0002ɲɳ\u0007\u0007\u0002\u0002ɳɵ\u0005L'\u0002ɴɲ\u0003\u0002\u0002\u0002ɵɸ\u0003\u0002\u0002\u0002ɶɴ\u0003\u0002\u0002\u0002ɶɷ\u0003\u0002\u0002\u0002ɷɹ\u0003\u0002\u0002\u0002ɸɶ\u0003\u0002\u0002\u0002ɹʈ\u0007\u0006\u0002\u0002ɺɻ\u0007\u0007\u0002\u0002ɻɼ\u0007\u0005\u0002\u0002ɼʁ\u0005L'\u0002ɽɾ\u0007\u0007\u0002\u0002ɾʀ\u0005L'\u0002ɿɽ\u0003\u0002\u0002\u0002ʀʃ\u0003\u0002\u0002\u0002ʁɿ\u0003\u0002\u0002\u0002ʁʂ\u0003\u0002\u0002\u0002ʂʄ\u0003\u0002\u0002\u0002ʃʁ\u0003\u0002\u0002\u0002ʄʅ\u0007\u0006\u0002\u0002ʅʇ\u0003\u0002\u0002\u0002ʆɺ\u0003\u0002\u0002\u0002ʇʊ\u0003\u0002\u0002\u0002ʈʆ\u0003\u0002\u0002\u0002ʈʉ\u0003\u0002\u0002\u0002ʉʏ\u0003\u0002\u0002\u0002ʊʈ\u0003\u0002\u0002\u0002ʋʏ\u0005:\u001e\u0002ʌʍ\u0007<\u0002\u0002ʍʏ\u0007\u0092\u0002\u0002ʎɯ\u0003\u0002\u0002\u0002ʎʋ\u0003\u0002\u0002\u0002ʎʌ\u0003\u0002\u0002\u0002ʏʑ\u0003\u0002\u0002\u0002ʐʒ\u0005.\u0018\u0002ʑʐ\u0003\u0002\u0002\u0002ʑʒ\u0003\u0002\u0002\u0002ʒ-\u0003\u0002\u0002\u0002ʓʔ\u0007o\u0002\u0002ʔʣ\u00074\u0002\u0002ʕʖ\u0007\u0005\u0002\u0002ʖʛ\u0005R*\u0002ʗʘ\u0007\u0007\u0002\u0002ʘʚ\u0005R*\u0002ʙʗ\u0003\u0002\u0002\u0002ʚʝ\u0003\u0002\u0002\u0002ʛʙ\u0003\u0002\u0002\u0002ʛʜ\u0003\u0002\u0002\u0002ʜʞ\u0003\u0002\u0002\u0002ʝʛ\u0003\u0002\u0002\u0002ʞʡ\u0007\u0006\u0002\u0002ʟʠ\u0007\u0096\u0002\u0002ʠʢ\u0005L'\u0002ʡʟ\u0003\u0002\u0002\u0002ʡʢ\u0003\u0002\u0002\u0002ʢʤ\u0003\u0002\u0002\u0002ʣʕ\u0003\u0002\u0002\u0002ʣʤ\u0003\u0002\u0002\u0002ʤʿ\u0003\u0002\u0002\u0002ʥˀ\u0007\u009a\u0002\u0002ʦʧ\u0007\u009b\u0002\u0002ʧʪ\u0007\u0085\u0002\u0002ʨʫ\u0005\u0092J\u0002ʩʫ\u0005\u0080A\u0002ʪʨ\u0003\u0002\u0002\u0002ʪʩ\u0003\u0002\u0002\u0002ʫʬ\u0003\u0002\u0002\u0002ʬʭ\u0007\b\u0002\u0002ʭʸ\u0005L'\u0002ʮʱ\u0007\u0007\u0002\u0002ʯʲ\u0005\u0092J\u0002ʰʲ\u0005\u0080A\u0002ʱʯ\u0003\u0002\u0002\u0002ʱʰ\u0003\u0002\u0002\u0002ʲʳ\u0003\u0002\u0002\u0002ʳʴ\u0007\b\u0002\u0002ʴʵ\u0005L'\u0002ʵʷ\u0003\u0002\u0002\u0002ʶʮ\u0003\u0002\u0002\u0002ʷʺ\u0003\u0002\u0002\u0002ʸʶ\u0003\u0002\u0002\u0002ʸʹ\u0003\u0002\u0002\u0002ʹʽ\u0003\u0002\u0002\u0002ʺʸ\u0003\u0002\u0002\u0002ʻʼ\u0007\u0096\u0002\u0002ʼʾ\u0005L'\u0002ʽʻ\u0003\u0002\u0002\u0002ʽʾ\u0003\u0002\u0002\u0002ʾˀ\u0003\u0002\u0002\u0002ʿʥ\u0003\u0002\u0002\u0002ʿʦ\u0003\u0002\u0002\u0002ˀ/\u0003\u0002\u0002\u0002ˁ˅\u0007t\u0002\u0002˂˃\u0005\u0088E\u0002˃˄\u0007\u0004\u0002\u0002˄ˆ\u0003\u0002\u0002\u0002˅˂\u0003\u0002\u0002\u0002˅ˆ\u0003\u0002\u0002\u0002ˆˇ\u0003\u0002\u0002\u0002ˇˎ\u0005 Q\u0002ˈˉ\u0007\b\u0002\u0002ˉˏ\u0005b2\u0002ˊˋ\u0007\u0005\u0002\u0002ˋˌ\u0005b2\u0002ˌˍ\u0007\u0006\u0002\u0002ˍˏ\u0003\u0002\u0002\u0002ˎˈ\u0003\u0002\u0002\u0002ˎˊ\u0003\u0002\u0002\u0002ˎˏ\u0003\u0002\u0002\u0002ˏ1\u0003\u0002\u0002\u0002ː˛\u0007{\u0002\u0002ˑ˜\u0005\u0094K\u0002˒˓\u0005\u0088E\u0002˓˔\u0007\u0004\u0002\u0002˔˖\u0003\u0002\u0002\u0002˕˒\u0003\u0002\u0002\u0002˕˖\u0003\u0002\u0002\u0002˖˙\u0003\u0002\u0002\u0002˗˚\u0005\u008cG\u0002˘˚\u0005\u0098M\u0002˙˗\u0003\u0002\u0002\u0002˙˘\u0003\u0002\u0002\u0002˚˜\u0003\u0002\u0002\u0002˛ˑ\u0003\u0002\u0002\u0002˛˕\u0003\u0002\u0002\u0002˛˜\u0003\u0002\u0002\u0002˜3\u0003\u0002\u0002\u0002˝˟\u0007|\u0002\u0002˞ˠ\u0007\u0083\u0002\u0002˟˞\u0003\u0002\u0002\u0002˟ˠ\u0003\u0002\u0002\u0002ˠˡ\u0003\u0002\u0002\u0002ˡˢ\u0005¢R\u0002ˢ5\u0003\u0002\u0002\u0002ˣ˨\u0007\u0081\u0002\u0002ˤ˦\u0007\u008b\u0002\u0002˥˧\u0005¦T\u0002˦˥\u0003\u0002\u0002\u0002˦˧\u0003\u0002\u0002\u0002˧˩\u0003\u0002\u0002\u0002˨ˤ\u0003\u0002\u0002\u0002˨˩\u0003\u0002\u0002\u0002˩˯\u0003\u0002\u0002\u0002˪ˬ\u0007\u008a\u0002\u0002˫˭\u0007\u0083\u0002\u0002ˬ˫\u0003\u0002\u0002\u0002ˬ˭\u0003\u0002\u0002\u0002˭ˮ\u0003\u0002\u0002\u0002ˮ˰\u0005¢R\u0002˯˪\u0003\u0002\u0002\u0002˯˰\u0003\u0002\u0002\u0002˰7\u0003\u0002\u0002\u0002˱˲\u0007\u0083\u0002\u0002˲˳\u0005¢R\u0002˳9\u0003\u0002\u0002\u0002˴˶\u0005V,\u0002˵˴\u0003\u0002\u0002\u0002˵˶\u0003\u0002\u0002\u0002˶˷\u0003\u0002\u0002\u0002˷˽\u0005<\u001f\u0002˸˹\u0005n8\u0002˹˺\u0005<\u001f\u0002˺˼\u0003\u0002\u0002\u0002˻˸\u0003\u0002\u0002\u0002˼˿\u0003\u0002\u0002\u0002˽˻\u0003\u0002\u0002\u0002˽˾\u0003\u0002\u0002\u0002˾́\u0003\u0002\u0002\u0002˿˽\u0003\u0002\u0002\u0002̀̂\u0005\\/\u0002́̀\u0003\u0002\u0002\u0002́̂\u0003\u0002\u0002\u0002̂̄\u0003\u0002\u0002\u0002̃̅\u0005`1\u0002̄̃\u0003\u0002\u0002\u0002̄̅\u0003\u0002\u0002\u0002̅;\u0003\u0002\u0002\u0002̆̈\u0007\u0084\u0002\u0002̇̉\t\u0005\u0002\u0002̈̇\u0003\u0002\u0002\u0002̈̉\u0003\u0002\u0002\u0002̉̊\u0003\u0002\u0002\u0002̊̏\u0005d3\u0002̋̌\u0007\u0007\u0002\u0002̌̎\u0005d3\u0002̍̋\u0003\u0002\u0002\u0002̎̑\u0003\u0002\u0002\u0002̏̍\u0003\u0002\u0002\u0002̏̐\u0003\u0002\u0002\u0002̞̐\u0003\u0002\u0002\u0002̑̏\u0003\u0002\u0002\u0002̜̒\u0007O\u0002\u0002̘̓\u0005f4\u0002̔̕\u0007\u0007\u0002\u0002̗̕\u0005f4\u0002̖̔\u0003\u0002\u0002\u0002̗̚\u0003\u0002\u0002\u0002̘̖\u0003\u0002\u0002\u0002̘̙\u0003\u0002\u0002\u0002̙̝\u0003\u0002\u0002\u0002̘̚\u0003\u0002\u0002\u0002̛̝\u0005h5\u0002̜̓\u0003\u0002\u0002\u0002̛̜\u0003\u0002\u0002\u0002̝̟\u0003\u0002\u0002\u0002̞̒\u0003\u0002\u0002\u0002̞̟\u0003\u0002\u0002\u0002̢̟\u0003\u0002\u0002\u0002̡̠\u0007\u0096\u0002\u0002̡̣\u0005L'\u0002̢̠\u0003\u0002\u0002\u0002̢̣\u0003\u0002\u0002\u0002̣̲\u0003\u0002\u0002\u0002̤̥\u0007R\u0002\u0002̥̦\u0007,\u0002\u0002̦̫\u0005L'\u0002̧̨\u0007\u0007\u0002\u0002̨̪\u0005L'\u0002̧̩\u0003\u0002\u0002\u0002̪̭\u0003\u0002\u0002\u0002̫̩\u0003\u0002\u0002\u0002̫̬\u0003\u0002\u0002\u0002̬̰\u0003\u0002\u0002\u0002̭̫\u0003\u0002\u0002\u0002̮̯\u0007S\u0002\u0002̯̱\u0005L'\u0002̰̮\u0003\u0002\u0002\u0002̰̱\u0003\u0002\u0002\u0002̱̳\u0003\u0002\u0002\u0002̲̤\u0003\u0002\u0002\u0002̲̳\u0003\u0002\u0002\u0002̳͑\u0003\u0002\u0002\u0002̴̵\u0007\u0092\u0002\u0002̵̶\u0007\u0005\u0002\u0002̶̻\u0005L'\u0002̷̸\u0007\u0007\u0002\u0002̸̺\u0005L'\u0002̷̹\u0003\u0002\u0002\u0002̺̽\u0003\u0002\u0002\u0002̻̹\u0003\u0002\u0002\u0002̻̼\u0003\u0002\u0002\u0002̼̾\u0003\u0002\u0002\u0002̻̽\u0003\u0002\u0002\u0002͍̾\u0007\u0006\u0002\u0002̿̀\u0007\u0007\u0002\u0002̀́\u0007\u0005\u0002\u0002́͆\u0005L'\u0002͂̓\u0007\u0007\u0002\u0002̓ͅ\u0005L'\u0002̈́͂\u0003\u0002\u0002\u0002͈ͅ\u0003\u0002\u0002\u0002͆̈́\u0003\u0002\u0002\u0002͇͆\u0003\u0002\u0002\u0002͇͉\u0003\u0002\u0002\u0002͈͆\u0003\u0002\u0002\u0002͉͊\u0007\u0006\u0002\u0002͊͌\u0003\u0002\u0002\u0002͋̿\u0003\u0002\u0002\u0002͌͏\u0003\u0002\u0002\u0002͍͋\u0003\u0002\u0002\u0002͍͎\u0003\u0002\u0002\u0002͎͑\u0003\u0002\u0002\u0002͏͍\u0003\u0002\u0002\u0002͐̆\u0003\u0002\u0002\u0002̴͐\u0003\u0002\u0002\u0002͑=\u0003\u0002\u0002\u0002͔͒\u0005V,\u0002͓͒\u0003\u0002\u0002\u0002͓͔\u0003\u0002\u0002\u0002͔͕\u0003\u0002\u0002\u0002͕͠\u0007\u008f\u0002\u0002͖͗\u0007p\u0002\u0002͗͡\u0007\u0081\u0002\u0002͙͘\u0007p\u0002\u0002͙͡\u0007\u001d\u0002\u0002͚͛\u0007p\u0002\u0002͛͡\u0007~\u0002\u0002͜͝\u0007p\u0002\u0002͝͡\u0007L\u0002\u0002͟͞\u0007p\u0002\u0002͟͡\u0007U\u0002\u0002͖͠\u0003\u0002\u0002\u0002͘͠\u0003\u0002\u0002\u0002͚͠\u0003\u0002\u0002\u0002͜͠\u0003\u0002\u0002\u0002͠͞\u0003\u0002\u0002\u0002͠͡\u0003\u0002\u0002\u0002͢͡\u0003\u0002\u0002\u0002ͣ͢\u0005Z.\u0002ͣͦ\u0007\u0085\u0002\u0002ͤͧ\u0005\u0092J\u0002ͥͧ\u0005\u0080A\u0002ͦͤ\u0003\u0002\u0002\u0002ͦͥ\u0003\u0002\u0002\u0002ͧͨ\u0003\u0002\u0002\u0002ͨͩ\u0007\b\u0002\u0002ͩʹ\u0005L'\u0002ͪͭ\u0007\u0007\u0002\u0002ͫͮ\u0005\u0092J\u0002ͬͮ\u0005\u0080A\u0002ͭͫ\u0003\u0002\u0002\u0002ͭͬ\u0003\u0002\u0002\u0002ͮͯ\u0003\u0002\u0002\u0002ͯͰ\u0007\b\u0002\u0002Ͱͱ\u0005L'\u0002ͱͳ\u0003\u0002\u0002\u0002Ͳͪ\u0003\u0002\u0002\u0002ͳͶ\u0003\u0002\u0002\u0002ʹͲ\u0003\u0002\u0002\u0002ʹ͵\u0003\u0002\u0002\u0002͵\u0379\u0003\u0002\u0002\u0002Ͷʹ\u0003\u0002\u0002\u0002ͷ\u0378\u0007\u0096\u0002\u0002\u0378ͺ\u0005L'\u0002\u0379ͷ\u0003\u0002\u0002\u0002\u0379ͺ\u0003\u0002\u0002\u0002ͺ?\u0003\u0002\u0002\u0002ͻͽ\u0005V,\u0002ͼͻ\u0003\u0002\u0002\u0002ͼͽ\u0003\u0002\u0002\u0002ͽ;\u0003\u0002\u0002\u0002;Ή\u0007\u008f\u0002\u0002Ϳ\u0380\u0007p\u0002\u0002\u0380Ί\u0007\u0081\u0002\u0002\u0381\u0382\u0007p\u0002\u0002\u0382Ί\u0007\u001d\u0002\u0002\u0383΄\u0007p\u0002\u0002΄Ί\u0007~\u0002\u0002΅Ά\u0007p\u0002\u0002ΆΊ\u0007L\u0002\u0002·Έ\u0007p\u0002\u0002ΈΊ\u0007U\u0002\u0002ΉͿ\u0003\u0002\u0002\u0002Ή\u0381\u0003\u0002\u0002\u0002Ή\u0383\u0003\u0002\u0002\u0002Ή΅\u0003\u0002\u0002\u0002Ή·\u0003\u0002\u0002\u0002ΉΊ\u0003\u0002\u0002\u0002Ί\u038b\u0003\u0002\u0002\u0002\u038bΌ\u0005Z.\u0002ΌΏ\u0007\u0085\u0002\u0002\u038dΐ\u0005\u0092J\u0002Ύΐ\u0005\u0080A\u0002Ώ\u038d\u0003\u0002\u0002\u0002ΏΎ\u0003\u0002\u0002\u0002ΐΑ\u0003\u0002\u0002\u0002ΑΒ\u0007\b\u0002\u0002ΒΝ\u0005L'\u0002ΓΖ\u0007\u0007\u0002\u0002ΔΗ\u0005\u0092J\u0002ΕΗ\u0005\u0080A\u0002ΖΔ\u0003\u0002\u0002\u0002ΖΕ\u0003\u0002\u0002\u0002ΗΘ\u0003\u0002\u0002\u0002ΘΙ\u0007\b\u0002\u0002ΙΚ\u0005L'\u0002ΚΜ\u0003\u0002\u0002\u0002ΛΓ\u0003\u0002\u0002\u0002ΜΟ\u0003\u0002\u0002\u0002ΝΛ\u0003\u0002\u0002\u0002ΝΞ\u0003\u0002\u0002\u0002Ξ\u03a2\u0003\u0002\u0002\u0002ΟΝ\u0003\u0002\u0002\u0002ΠΡ\u0007\u0096\u0002\u0002ΡΣ\u0005L'\u0002\u03a2Π\u0003\u0002\u0002\u0002\u03a2Σ\u0003\u0002\u0002\u0002ΣΨ\u0003\u0002\u0002\u0002ΤΦ\u0005\\/\u0002ΥΤ\u0003\u0002\u0002\u0002ΥΦ\u0003\u0002\u0002\u0002ΦΧ\u0003\u0002\u0002\u0002ΧΩ\u0005`1\u0002ΨΥ\u0003\u0002\u0002\u0002ΨΩ\u0003\u0002\u0002\u0002ΩA\u0003\u0002\u0002\u0002Ϊά\u0007\u0091\u0002\u0002Ϋέ\u0005\u0088E\u0002άΫ\u0003\u0002\u0002\u0002άέ\u0003\u0002\u0002\u0002έC\u0003\u0002\u0002\u0002ήΰ\u0005\u0092J\u0002ία\u0005F$\u0002ΰί\u0003\u0002\u0002\u0002ΰα\u0003\u0002\u0002\u0002αε\u0003\u0002\u0002\u0002βδ\u0005H%\u0002γβ\u0003\u0002\u0002\u0002δη\u0003\u0002\u0002\u0002εγ\u0003\u0002\u0002\u0002εζ\u0003\u0002\u0002\u0002ζE\u0003\u0002\u0002\u0002ηε\u0003\u0002\u0002\u0002θκ\u0005\u0084C\u0002ιθ\u0003\u0002\u0002\u0002κλ\u0003\u0002\u0002\u0002λμ\u0003\u0002\u0002\u0002λι\u0003\u0002\u0002\u0002μχ\u0003\u0002\u0002\u0002νξ\u0007\u0005\u0002\u0002ξο\u0005p9\u0002οπ\u0007\u0006\u0002\u0002πψ\u0003\u0002\u0002\u0002ρς\u0007\u0005\u0002\u0002ςσ\u0005p9\u0002στ\u0007\u0007\u0002\u0002τυ\u0005p9\u0002υφ\u0007\u0006\u0002\u0002φψ\u0003\u0002\u0002\u0002χν\u0003\u0002\u0002\u0002χρ\u0003\u0002\u0002\u0002χψ\u0003\u0002\u0002\u0002ψG\u0003\u0002\u0002\u0002ωϊ\u00075\u0002\u0002ϊό\u0005\u0084C\u0002ϋω\u0003\u0002\u0002\u0002ϋό\u0003\u0002\u0002\u0002όϮ\u0003\u0002\u0002\u0002ύώ\u0007u\u0002\u0002ώϐ\u0007c\u0002\u0002Ϗϑ\t\u0006\u0002\u0002ϐϏ\u0003\u0002\u0002\u0002ϐϑ\u0003\u0002\u0002\u0002ϑϒ\u0003\u0002\u0002\u0002ϒϔ\u0005J&\u0002ϓϕ\u0007(\u0002\u0002ϔϓ\u0003\u0002\u0002\u0002ϔϕ\u0003\u0002\u0002\u0002ϕϯ\u0003\u0002\u0002\u0002ϖϘ\u0007j\u0002\u0002ϗϖ\u0003\u0002\u0002\u0002ϗϘ\u0003\u0002\u0002\u0002Ϙϙ\u0003\u0002\u0002\u0002ϙϚ\u0007l\u0002\u0002Ϛϯ\u0005J&\u0002ϛϜ\u0007\u008e\u0002\u0002Ϝϯ\u0005J&\u0002ϝϞ\u00070\u0002\u0002Ϟϟ\u0007\u0005\u0002\u0002ϟϠ\u0005L'\u0002Ϡϡ\u0007\u0006\u0002\u0002ϡϯ\u0003\u0002\u0002\u0002Ϣϩ\u0007<\u0002\u0002ϣϪ\u0005p9\u0002ϤϪ\u0005r:\u0002ϥϦ\u0007\u0005\u0002\u0002Ϧϧ\u0005L'\u0002ϧϨ\u0007\u0006\u0002\u0002ϨϪ\u0003\u0002\u0002\u0002ϩϣ\u0003\u0002\u0002\u0002ϩϤ\u0003\u0002\u0002\u0002ϩϥ\u0003\u0002\u0002\u0002Ϫϯ\u0003\u0002\u0002\u0002ϫϬ\u00071\u0002\u0002Ϭϯ\u0005\u0094K\u0002ϭϯ\u0005N(\u0002Ϯύ\u0003\u0002\u0002\u0002Ϯϗ\u0003\u0002\u0002\u0002Ϯϛ\u0003\u0002\u0002\u0002Ϯϝ\u0003\u0002\u0002\u0002ϮϢ\u0003\u0002\u0002\u0002Ϯϫ\u0003\u0002\u0002\u0002Ϯϭ\u0003\u0002\u0002\u0002ϯI\u0003\u0002\u0002\u0002ϰϱ\u0007o\u0002\u0002ϱϲ\u00074\u0002\u0002ϲϴ\t\u0007\u0002\u0002ϳϰ\u0003\u0002\u0002\u0002ϳϴ\u0003\u0002\u0002\u0002ϴK\u0003\u0002\u0002\u0002ϵ϶\b'\u0001\u0002϶щ\u0005r:\u0002Ϸщ\u0007\u009e\u0002\u0002ϸϹ\u0005\u0088E\u0002ϹϺ\u0007\u0004\u0002\u0002Ϻϼ\u0003\u0002\u0002\u0002ϻϸ\u0003\u0002\u0002\u0002ϻϼ\u0003\u0002\u0002\u0002ϼϽ\u0003\u0002\u0002\u0002ϽϾ\u0005\u008cG\u0002ϾϿ\u0007\u0004\u0002\u0002ϿЁ\u0003\u0002\u0002\u0002Ѐϻ\u0003\u0002\u0002\u0002ЀЁ\u0003\u0002\u0002\u0002ЁЂ\u0003\u0002\u0002\u0002Ђщ\u0005\u0092J\u0002ЃЄ\u0005v<\u0002ЄЅ\u0005L'\u0010Ѕщ\u0003\u0002\u0002\u0002ІЇ\u0005\u0086D\u0002ЇД\u0007\u0005\u0002\u0002ЈЊ\u0007B\u0002\u0002ЉЈ\u0003\u0002\u0002\u0002ЉЊ\u0003\u0002\u0002\u0002ЊЋ\u0003\u0002\u0002\u0002ЋА\u0005L'\u0002ЌЍ\u0007\u0007\u0002\u0002ЍЏ\u0005L'\u0002ЎЌ\u0003\u0002\u0002\u0002ЏВ\u0003\u0002\u0002\u0002АЎ\u0003\u0002\u0002\u0002АБ\u0003\u0002\u0002\u0002БЕ\u0003\u0002\u0002\u0002ВА\u0003\u0002\u0002\u0002ГЕ\u0007\t\u0002\u0002ДЉ\u0003\u0002\u0002\u0002ДГ\u0003\u0002\u0002\u0002ДЕ\u0003\u0002\u0002\u0002ЕЖ\u0003\u0002\u0002\u0002ЖЗ\u0007\u0006\u0002\u0002Зщ\u0003\u0002\u0002\u0002ИЙ\u0007\u0005\u0002\u0002ЙО\u0005L'\u0002КЛ\u0007\u0007\u0002\u0002ЛН\u0005L'\u0002МК\u0003\u0002\u0002\u0002НР\u0003\u0002\u0002\u0002ОМ\u0003\u0002\u0002\u0002ОП\u0003\u0002\u0002\u0002ПС\u0003\u0002\u0002\u0002РО\u0003\u0002\u0002\u0002СТ\u0007\u0006\u0002\u0002Тщ\u0003\u0002\u0002\u0002УФ\u0007/\u0002\u0002ФХ\u0007\u0005\u0002\u0002ХЦ\u0005L'\u0002ЦЧ\u0007%\u0002\u0002ЧШ\u0005F$\u0002ШЩ\u0007\u0006\u0002\u0002Щщ\u0003\u0002\u0002\u0002ЪЬ\u0007j\u0002\u0002ЫЪ\u0003\u0002\u0002\u0002ЫЬ\u0003\u0002\u0002\u0002ЬЭ\u0003\u0002\u0002\u0002ЭЯ\u0007J\u0002\u0002ЮЫ\u0003\u0002\u0002\u0002ЮЯ\u0003\u0002\u0002\u0002Яа\u0003\u0002\u0002\u0002аб\u0007\u0005\u0002\u0002бв\u0005:\u001e\u0002вг\u0007\u0006\u0002\u0002гщ\u0003\u0002\u0002\u0002дж\u0007.\u0002\u0002ез\u0005L'\u0002же\u0003\u0002\u0002\u0002жз\u0003\u0002\u0002\u0002зн\u0003\u0002\u0002\u0002ий\u0007\u0095\u0002\u0002йк\u0005L'\u0002кл\u0007\u0089\u0002\u0002лм\u0005L'\u0002мо\u0003\u0002\u0002\u0002ни\u0003\u0002\u0002\u0002оп\u0003\u0002\u0002\u0002пн\u0003\u0002\u0002\u0002пр\u0003\u0002\u0002\u0002ру\u0003\u0002\u0002\u0002ст\u0007E\u0002\u0002тф\u0005L'\u0002ус\u0003\u0002\u0002\u0002уф\u0003\u0002\u0002\u0002фх\u0003\u0002\u0002\u0002хц\u0007F\u0002\u0002цщ\u0003\u0002\u0002\u0002чщ\u0005P)\u0002шϵ\u0003\u0002\u0002\u0002шϷ\u0003\u0002\u0002\u0002шЀ\u0003\u0002\u0002\u0002шЃ\u0003\u0002\u0002\u0002шІ\u0003\u0002\u0002\u0002шИ\u0003\u0002\u0002\u0002шУ\u0003\u0002\u0002\u0002шЮ\u0003\u0002\u0002\u0002шд\u0003\u0002\u0002\u0002шч\u0003\u0002\u0002\u0002щҠ\u0003\u0002\u0002\u0002ъы\f\u000f\u0002\u0002ыь\u0005x=\u0002ьэ\u0005L'\u0010эҟ\u0003\u0002\u0002\u0002юя\f\b\u0002\u0002яё\u0007`\u0002\u0002ѐђ\u0007j\u0002\u0002ёѐ\u0003\u0002\u0002\u0002ёђ\u0003\u0002\u0002\u0002ђѓ\u0003\u0002\u0002\u0002ѓҟ\u0005L'\tєі\f\u0007\u0002\u0002ѕї\u0007j\u0002\u0002іѕ\u0003\u0002\u0002\u0002ії\u0003\u0002\u0002\u0002їј\u0003\u0002\u0002\u0002јљ\u0007+\u0002\u0002љњ\u0005L'\u0002њћ\u0007$\u0002\u0002ћќ\u0005L'\bќҟ\u0003\u0002\u0002\u0002ѝў\f\u000b\u0002\u0002ўџ\u00071\u0002\u0002џҟ\u0005\u0094K\u0002ѠѢ\f\n\u0002\u0002ѡѣ\u0007j\u0002\u0002Ѣѡ\u0003\u0002\u0002\u0002Ѣѣ\u0003\u0002\u0002\u0002ѣѤ\u0003\u0002\u0002\u0002Ѥѥ\t\b\u0002\u0002ѥѨ\u0005L'\u0002Ѧѧ\u0007G\u0002\u0002ѧѩ\u0005L'\u0002ѨѦ\u0003\u0002\u0002\u0002Ѩѩ\u0003\u0002\u0002\u0002ѩҟ\u0003\u0002\u0002\u0002Ѫѯ\f\t\u0002\u0002ѫѰ\u0007a\u0002\u0002ѬѰ\u0007k\u0002\u0002ѭѮ\u0007j\u0002\u0002ѮѰ\u0007l\u0002\u0002ѯѫ\u0003\u0002\u0002\u0002ѯѬ\u0003\u0002\u0002\u0002ѯѭ\u0003\u0002\u0002\u0002Ѱҟ\u0003\u0002\u0002\u0002ѱѳ\f\u0006\u0002\u0002ѲѴ\u0007j\u0002\u0002ѳѲ\u0003\u0002\u0002\u0002ѳѴ\u0003\u0002\u0002\u0002Ѵѵ\u0003\u0002\u0002\u0002ѵҜ\u0007W\u0002\u0002ѶҀ\u0007\u0005\u0002\u0002ѷҁ\u0005:\u001e\u0002Ѹѽ\u0005L'\u0002ѹѺ\u0007\u0007\u0002\u0002ѺѼ\u0005L'\u0002ѻѹ\u0003\u0002\u0002\u0002Ѽѿ\u0003\u0002\u0002\u0002ѽѻ\u0003\u0002\u0002\u0002ѽѾ\u0003\u0002\u0002\u0002Ѿҁ\u0003\u0002\u0002\u0002ѿѽ\u0003\u0002\u0002\u0002Ҁѷ\u0003\u0002\u0002\u0002ҀѸ\u0003\u0002\u0002\u0002Ҁҁ\u0003\u0002\u0002\u0002ҁ҂\u0003\u0002\u0002\u0002҂ҝ\u0007\u0006\u0002\u0002҃҄\u0005\u0088E\u0002҄҅\u0007\u0004\u0002\u0002҅҇\u0003\u0002\u0002\u0002҆҃\u0003\u0002\u0002\u0002҆҇\u0003\u0002\u0002\u0002҇҈\u0003\u0002\u0002\u0002҈ҝ\u0005\u008cG\u0002҉Ҋ\u0005\u0088E\u0002Ҋҋ\u0007\u0004\u0002\u0002ҋҍ\u0003\u0002\u0002\u0002Ҍ҉\u0003\u0002\u0002\u0002Ҍҍ\u0003\u0002\u0002\u0002ҍҎ\u0003\u0002\u0002\u0002Ҏҏ\u0005\u008aF\u0002ҏҘ\u0007\u0005\u0002\u0002Ґҕ\u0005L'\u0002ґҒ\u0007\u0007\u0002\u0002ҒҔ\u0005L'\u0002ғґ\u0003\u0002\u0002\u0002Ҕҗ\u0003\u0002\u0002\u0002ҕғ\u0003\u0002\u0002\u0002ҕҖ\u0003\u0002\u0002\u0002Җҙ\u0003\u0002\u0002\u0002җҕ\u0003\u0002\u0002\u0002ҘҐ\u0003\u0002\u0002\u0002Ҙҙ\u0003\u0002\u0002\u0002ҙҚ\u0003\u0002\u0002\u0002Ққ\u0007\u0006\u0002\u0002қҝ\u0003\u0002\u0002\u0002ҜѶ\u0003\u0002\u0002\u0002Ҝ҆\u0003\u0002\u0002\u0002ҜҌ\u0003\u0002\u0002\u0002ҝҟ\u0003\u0002\u0002\u0002Ҟъ\u0003\u0002\u0002\u0002Ҟю\u0003\u0002\u0002\u0002Ҟє\u0003\u0002\u0002\u0002Ҟѝ\u0003\u0002\u0002\u0002ҞѠ\u0003\u0002\u0002\u0002ҞѪ\u0003\u0002\u0002\u0002Ҟѱ\u0003\u0002\u0002\u0002ҟҢ\u0003\u0002\u0002\u0002ҠҞ\u0003\u0002\u0002\u0002Ҡҡ\u0003\u0002\u0002\u0002ҡM\u0003\u0002\u0002\u0002ҢҠ\u0003\u0002\u0002\u0002ңҤ\u0007y\u0002\u0002ҤҰ\u0005\u0096L\u0002ҥҦ\u0007\u0005\u0002\u0002Ҧҫ\u0005\u0092J\u0002ҧҨ\u0007\u0007\u0002\u0002ҨҪ\u0005\u0092J\u0002ҩҧ\u0003\u0002\u0002\u0002Ҫҭ\u0003\u0002\u0002\u0002ҫҩ\u0003\u0002\u0002\u0002ҫҬ\u0003\u0002\u0002\u0002ҬҮ\u0003\u0002\u0002\u0002ҭҫ\u0003\u0002\u0002\u0002Үү\u0007\u0006\u0002\u0002үұ\u0003\u0002\u0002\u0002Ұҥ\u0003\u0002\u0002\u0002Ұұ\u0003\u0002\u0002\u0002ұӄ\u0003\u0002\u0002\u0002Ҳҳ\u0007o\u0002\u0002ҳҼ\t\t\u0002\u0002Ҵҵ\u0007\u0085\u0002\u0002ҵҽ\u0007l\u0002\u0002Ҷҷ\u0007\u0085\u0002\u0002ҷҽ\u0007<\u0002\u0002Ҹҽ\u0007-\u0002\u0002ҹҽ\u0007\u007f\u0002\u0002Һһ\u0007i\u0002\u0002һҽ\u0007\u001e\u0002\u0002ҼҴ\u0003\u0002\u0002\u0002ҼҶ\u0003\u0002\u0002\u0002ҼҸ\u0003\u0002\u0002\u0002Ҽҹ\u0003\u0002\u0002\u0002ҼҺ\u0003\u0002\u0002\u0002ҽӁ\u0003\u0002\u0002\u0002Ҿҿ\u0007g\u0002\u0002ҿӁ\u0005\u0084C\u0002ӀҲ\u0003\u0002\u0002\u0002ӀҾ\u0003\u0002\u0002\u0002ӁӃ\u0003\u0002\u0002\u0002ӂӀ\u0003\u0002\u0002\u0002Ӄӆ\u0003\u0002\u0002\u0002ӄӂ\u0003\u0002\u0002\u0002ӄӅ\u0003\u0002\u0002\u0002Ӆӑ\u0003\u0002\u0002\u0002ӆӄ\u0003\u0002\u0002\u0002ӇӉ\u0007j\u0002\u0002ӈӇ\u0003\u0002\u0002\u0002ӈӉ\u0003\u0002\u0002\u0002Ӊӊ\u0003\u0002\u0002\u0002ӊӏ\u0007=\u0002\u0002Ӌӌ\u0007Z\u0002\u0002ӌӐ\u0007>\u0002\u0002Ӎӎ\u0007Z\u0002\u0002ӎӐ\u0007V\u0002\u0002ӏӋ\u0003\u0002\u0002\u0002ӏӍ\u0003\u0002\u0002\u0002ӏӐ\u0003\u0002\u0002\u0002ӐӒ\u0003\u0002\u0002\u0002ӑӈ\u0003\u0002\u0002\u0002ӑӒ\u0003\u0002\u0002\u0002ӒO\u0003\u0002\u0002\u0002ӓӔ\u0007w\u0002\u0002Ӕә\u0007\u0005\u0002\u0002ӕӚ\u0007U\u0002\u0002Ӗӗ\t\n\u0002\u0002ӗӘ\u0007\u0007\u0002\u0002ӘӚ\u0005z>\u0002әӕ\u0003\u0002\u0002\u0002әӖ\u0003\u0002\u0002\u0002Ӛӛ\u0003\u0002\u0002\u0002ӛӜ\u0007\u0006\u0002\u0002ӜQ\u0003\u0002\u0002\u0002ӝӠ\u0005\u0092J\u0002ӞӠ\u0005L'\u0002ӟӝ\u0003\u0002\u0002\u0002ӟӞ\u0003\u0002\u0002\u0002Ӡӣ\u0003\u0002\u0002\u0002ӡӢ\u00071\u0002\u0002ӢӤ\u0005\u0094K\u0002ӣӡ\u0003\u0002\u0002\u0002ӣӤ\u0003\u0002\u0002\u0002ӤӦ\u0003\u0002\u0002\u0002ӥӧ\t\u0006\u0002\u0002Ӧӥ\u0003\u0002\u0002\u0002Ӧӧ\u0003\u0002\u0002\u0002ӧS\u0003\u0002\u0002\u0002Өө\u00075\u0002\u0002өӫ\u0005\u0084C\u0002ӪӨ\u0003\u0002\u0002\u0002Ӫӫ\u0003\u0002\u0002\u0002ӫԐ\u0003\u0002\u0002\u0002Ӭӭ\u0007u\u0002\u0002ӭӰ\u0007c\u0002\u0002ӮӰ\u0007\u008e\u0002\u0002ӯӬ\u0003\u0002\u0002\u0002ӯӮ\u0003\u0002\u0002\u0002Ӱӱ\u0003\u0002\u0002\u0002ӱӲ\u0007\u0005\u0002\u0002Ӳӷ\u0005R*\u0002ӳӴ\u0007\u0007\u0002\u0002ӴӶ\u0005R*\u0002ӵӳ\u0003\u0002\u0002\u0002Ӷӹ\u0003\u0002\u0002\u0002ӷӵ\u0003\u0002\u0002\u0002ӷӸ\u0003\u0002\u0002\u0002ӸӺ\u0003\u0002\u0002\u0002ӹӷ\u0003\u0002\u0002\u0002Ӻӻ\u0007\u0006\u0002\u0002ӻӼ\u0005J&\u0002Ӽԑ\u0003\u0002\u0002\u0002ӽӾ\u00070\u0002\u0002Ӿӿ\u0007\u0005\u0002\u0002ӿԀ\u0005L'\u0002Ԁԁ\u0007\u0006\u0002\u0002ԁԑ\u0003\u0002\u0002\u0002Ԃԃ\u0007N\u0002\u0002ԃԄ\u0007c\u0002\u0002Ԅԅ\u0007\u0005\u0002\u0002ԅԊ\u0005\u0092J\u0002Ԇԇ\u0007\u0007\u0002\u0002ԇԉ\u0005\u0092J\u0002ԈԆ\u0003\u0002\u0002\u0002ԉԌ\u0003\u0002\u0002\u0002ԊԈ\u0003\u0002\u0002\u0002Ԋԋ\u0003\u0002\u0002\u0002ԋԍ\u0003\u0002\u0002\u0002ԌԊ\u0003\u0002\u0002\u0002ԍԎ\u0007\u0006\u0002\u0002Ԏԏ\u0005N(\u0002ԏԑ\u0003\u0002\u0002\u0002Ԑӯ\u0003\u0002\u0002\u0002Ԑӽ\u0003\u0002\u0002\u0002ԐԂ\u0003\u0002\u0002\u0002ԑU\u0003\u0002\u0002\u0002ԒԔ\u0007\u0097\u0002\u0002ԓԕ\u0007x\u0002\u0002Ԕԓ\u0003\u0002\u0002\u0002Ԕԕ\u0003\u0002\u0002\u0002ԕԖ\u0003\u0002\u0002\u0002Ԗԛ\u0005X-\u0002ԗԘ\u0007\u0007\u0002\u0002ԘԚ\u0005X-\u0002ԙԗ\u0003\u0002\u0002\u0002Ԛԝ\u0003\u0002\u0002\u0002ԛԙ\u0003\u0002\u0002\u0002ԛԜ\u0003\u0002\u0002\u0002ԜW\u0003\u0002\u0002\u0002ԝԛ\u0003\u0002\u0002\u0002ԞԪ\u0005\u008cG\u0002ԟԠ\u0007\u0005\u0002\u0002Ԡԥ\u0005\u0092J\u0002ԡԢ\u0007\u0007\u0002\u0002ԢԤ\u0005\u0092J\u0002ԣԡ\u0003\u0002\u0002\u0002Ԥԧ\u0003\u0002\u0002\u0002ԥԣ\u0003\u0002\u0002\u0002ԥԦ\u0003\u0002\u0002\u0002ԦԨ\u0003\u0002\u0002\u0002ԧԥ\u0003\u0002\u0002\u0002Ԩԩ\u0007\u0006\u0002\u0002ԩԫ\u0003\u0002\u0002\u0002Ԫԟ\u0003\u0002\u0002\u0002Ԫԫ\u0003\u0002\u0002\u0002ԫԬ\u0003\u0002\u0002\u0002Ԭԭ\u0007%\u0002\u0002ԭԮ\u0007\u0005\u0002\u0002Ԯԯ\u0005:\u001e\u0002ԯ\u0530\u0007\u0006\u0002\u0002\u0530Y\u0003\u0002\u0002\u0002ԱԲ\u0005\u0088E\u0002ԲԳ\u0007\u0004\u0002\u0002ԳԵ\u0003\u0002\u0002\u0002ԴԱ\u0003\u0002\u0002\u0002ԴԵ\u0003\u0002\u0002\u0002ԵԶ\u0003\u0002\u0002\u0002ԶԹ\u0005\u008cG\u0002ԷԸ\u0007%\u0002\u0002ԸԺ\u0005¤S\u0002ԹԷ\u0003\u0002\u0002\u0002ԹԺ\u0003\u0002\u0002\u0002ԺՀ\u0003\u0002\u0002\u0002ԻԼ\u0007Y\u0002\u0002ԼԽ\u0007,\u0002\u0002ԽՁ\u0005\u0098M\u0002ԾԿ\u0007j\u0002\u0002ԿՁ\u0007Y\u0002\u0002ՀԻ\u0003\u0002\u0002\u0002ՀԾ\u0003\u0002\u0002\u0002ՀՁ\u0003\u0002\u0002\u0002Ձ[\u0003\u0002\u0002\u0002ՂՃ\u0007q\u0002\u0002ՃՄ\u0007,\u0002\u0002ՄՉ\u0005^0\u0002ՅՆ\u0007\u0007\u0002\u0002ՆՈ\u0005^0\u0002ՇՅ\u0003\u0002\u0002\u0002ՈՋ\u0003\u0002\u0002\u0002ՉՇ\u0003\u0002\u0002\u0002ՉՊ\u0003\u0002\u0002\u0002Պ]\u0003\u0002\u0002\u0002ՋՉ\u0003\u0002\u0002\u0002ՌՏ\u0005L'\u0002ՍՎ\u00071\u0002\u0002ՎՐ\u0005\u0094K\u0002ՏՍ\u0003\u0002\u0002\u0002ՏՐ\u0003\u0002\u0002\u0002ՐՒ\u0003\u0002\u0002\u0002ՑՓ\t\u0006\u0002\u0002ՒՑ\u0003\u0002\u0002\u0002ՒՓ\u0003\u0002\u0002\u0002Փ_\u0003\u0002\u0002\u0002ՔՕ\u0007f\u0002\u0002Օ\u0558\u0005L'\u0002Ֆ\u0557\t\u000b\u0002\u0002\u0557ՙ\u0005L'\u0002\u0558Ֆ\u0003\u0002\u0002\u0002\u0558ՙ\u0003\u0002\u0002\u0002ՙa\u0003\u0002\u0002\u0002՚՟\u0005p9\u0002՛՟\u0005\u0084C\u0002՜՟\u0007\u009f\u0002\u0002՝՟\u0005t;\u0002՞՚\u0003\u0002\u0002\u0002՞՛\u0003\u0002\u0002\u0002՞՜\u0003\u0002\u0002\u0002՞՝\u0003\u0002\u0002\u0002՟c\u0003\u0002\u0002\u0002\u0560խ\u0007\t\u0002\u0002աբ\u0005\u008cG\u0002բգ\u0007\u0004\u0002\u0002գդ\u0007\t\u0002\u0002դխ\u0003\u0002\u0002\u0002եժ\u0005L'\u0002զը\u0007%\u0002\u0002էզ\u0003\u0002\u0002\u0002էը\u0003\u0002\u0002\u0002ըթ\u0003\u0002\u0002\u0002թի\u0005~@\u0002ժէ\u0003\u0002\u0002\u0002ժի\u0003\u0002\u0002\u0002իխ\u0003\u0002\u0002\u0002լ\u0560\u0003\u0002\u0002\u0002լա\u0003\u0002\u0002\u0002լե\u0003\u0002\u0002\u0002խe\u0003\u0002\u0002\u0002ծկ\u0005\u0088E\u0002կհ\u0007\u0004\u0002\u0002հղ\u0003\u0002\u0002\u0002ձծ\u0003\u0002\u0002\u0002ձղ\u0003\u0002\u0002\u0002ղճ\u0003\u0002\u0002\u0002ճո\u0005\u008cG\u0002մն\u0007%\u0002\u0002յմ\u0003\u0002\u0002\u0002յն\u0003\u0002\u0002\u0002նշ\u0003\u0002\u0002\u0002շչ\u0005¤S\u0002ոյ\u0003\u0002\u0002\u0002ոչ\u0003\u0002\u0002\u0002չտ\u0003\u0002\u0002\u0002պջ\u0007Y\u0002\u0002ջռ\u0007,\u0002\u0002ռր\u0005\u0098M\u0002սվ\u0007j\u0002\u0002վր\u0007Y\u0002\u0002տպ\u0003\u0002\u0002\u0002տս\u0003\u0002\u0002\u0002տր\u0003\u0002\u0002\u0002րֱ\u0003\u0002\u0002\u0002ցւ\u0005\u0088E\u0002ւփ\u0007\u0004\u0002\u0002փօ\u0003\u0002\u0002\u0002քց\u0003\u0002\u0002\u0002քօ\u0003\u0002\u0002\u0002օֆ\u0003\u0002\u0002\u0002ֆև\u0005\u008aF\u0002և\u0590\u0007\u0005\u0002\u0002\u0588֍\u0005L'\u0002։֊\u0007\u0007\u0002\u0002֊\u058c\u0005L'\u0002\u058b։\u0003\u0002\u0002\u0002\u058c֏\u0003\u0002\u0002\u0002֍\u058b\u0003\u0002\u0002\u0002֍֎\u0003\u0002\u0002\u0002֎֑\u0003\u0002\u0002\u0002֏֍\u0003\u0002\u0002\u0002\u0590\u0588\u0003\u0002\u0002\u0002\u0590֑\u0003\u0002\u0002\u0002֑֒\u0003\u0002\u0002\u0002֒֗\u0007\u0006\u0002\u0002֓֕\u0007%\u0002\u0002֔֓\u0003\u0002\u0002\u0002֔֕\u0003\u0002\u0002\u0002֖֕\u0003\u0002\u0002\u0002֖֘\u0005¤S\u0002֗֔\u0003\u0002\u0002\u0002֗֘\u0003\u0002\u0002\u0002ֱ֘\u0003\u0002\u0002\u0002֣֙\u0007\u0005\u0002\u0002֚֟\u0005f4\u0002֛֜\u0007\u0007\u0002\u0002֜֞\u0005f4\u0002֛֝\u0003\u0002\u0002\u0002֞֡\u0003\u0002\u0002\u0002֟֝\u0003\u0002\u0002\u0002֟֠\u0003\u0002\u0002\u0002֤֠\u0003\u0002\u0002\u0002֡֟\u0003\u0002\u0002\u0002֢֤\u0005h5\u0002֣֚\u0003\u0002\u0002\u0002֣֢\u0003\u0002\u0002\u0002֤֥\u0003\u0002\u0002\u0002֥֦\u0007\u0006\u0002\u0002ֱ֦\u0003\u0002\u0002\u0002֧֨\u0007\u0005\u0002\u0002֨֩\u0005:\u001e\u0002֮֩\u0007\u0006\u0002\u0002֪֬\u0007%\u0002\u0002֪֫\u0003\u0002\u0002\u0002֫֬\u0003\u0002\u0002\u0002֭֬\u0003\u0002\u0002\u0002֭֯\u0005¤S\u0002֮֫\u0003\u0002\u0002\u0002֮֯\u0003\u0002\u0002\u0002ֱ֯\u0003\u0002\u0002\u0002ְձ\u0003\u0002\u0002\u0002ְք\u0003\u0002\u0002\u0002ְ֙\u0003\u0002\u0002\u0002ְ֧\u0003\u0002\u0002\u0002ֱg\u0003\u0002\u0002\u0002ֲֹ\u0005f4\u0002ֳִ\u0005j6\u0002ִֵ\u0005f4\u0002ֵֶ\u0005l7\u0002ֶָ\u0003\u0002\u0002\u0002ֳַ\u0003\u0002\u0002\u0002ָֻ\u0003\u0002\u0002\u0002ַֹ\u0003\u0002\u0002\u0002ֹֺ\u0003\u0002\u0002\u0002ֺi\u0003\u0002\u0002\u0002ֹֻ\u0003\u0002\u0002\u0002ּ\u05ca\u0007\u0007\u0002\u0002ֽֿ\u0007h\u0002\u0002־ֽ\u0003\u0002\u0002\u0002־ֿ\u0003\u0002\u0002\u0002ֿ׆\u0003\u0002\u0002\u0002׀ׂ\u0007d\u0002\u0002ׁ׃\u0007r\u0002\u0002ׁׂ\u0003\u0002\u0002\u0002ׂ׃\u0003\u0002\u0002\u0002׃ׇ\u0003\u0002\u0002\u0002ׇׄ\u0007[\u0002\u0002ׇׅ\u00077\u0002\u0002׆׀\u0003\u0002\u0002\u0002׆ׄ\u0003\u0002\u0002\u0002׆ׅ\u0003\u0002\u0002\u0002׆ׇ\u0003\u0002\u0002\u0002ׇ\u05c8\u0003\u0002\u0002\u0002\u05c8\u05ca\u0007b\u0002\u0002\u05c9ּ\u0003\u0002\u0002\u0002\u05c9־\u0003\u0002\u0002\u0002\u05cak\u0003\u0002\u0002\u0002\u05cb\u05cc\u0007o\u0002\u0002\u05ccך\u0005L'\u0002\u05cd\u05ce\u0007\u0090\u0002\u0002\u05ce\u05cf\u0007\u0005\u0002\u0002\u05cfה\u0005\u0092J\u0002אב\u0007\u0007\u0002\u0002בד\u0005\u0092J\u0002גא\u0003\u0002\u0002\u0002דז\u0003\u0002\u0002\u0002הג\u0003\u0002\u0002\u0002הו\u0003\u0002\u0002\u0002וח\u0003\u0002\u0002\u0002זה\u0003\u0002\u0002\u0002חט\u0007\u0006\u0002\u0002טך\u0003\u0002\u0002\u0002י\u05cb\u0003\u0002\u0002\u0002י\u05cd\u0003\u0002\u0002\u0002יך\u0003\u0002\u0002\u0002ךm\u0003\u0002\u0002\u0002כס\u0007\u008d\u0002\u0002לם\u0007\u008d\u0002\u0002םס\u0007!\u0002\u0002מס\u0007^\u0002\u0002ןס\u0007H\u0002\u0002נכ\u0003\u0002\u0002\u0002נל\u0003\u0002\u0002\u0002נמ\u0003\u0002\u0002\u0002נן\u0003\u0002\u0002\u0002סo\u0003\u0002\u0002\u0002עפ\t\f\u0002\u0002ףע\u0003\u0002\u0002\u0002ףפ\u0003\u0002\u0002\u0002פץ\u0003\u0002\u0002\u0002ץצ\u0007\u009d\u0002\u0002צq\u0003\u0002\u0002\u0002קװ\u0007\u009d\u0002\u0002רװ\u0007\u009f\u0002\u0002שװ\u0007 \u0002\u0002תװ\u0007l\u0002\u0002\u05ebװ\u00079\u0002\u0002\u05ecװ\u00078\u0002\u0002\u05edװ\u0007:\u0002\u0002\u05eeװ\u0005t;\u0002\u05efק\u0003\u0002\u0002\u0002\u05efר\u0003\u0002\u0002\u0002\u05efש\u0003\u0002\u0002\u0002\u05efת\u0003\u0002\u0002\u0002\u05ef\u05eb\u0003\u0002\u0002\u0002\u05ef\u05ec\u0003\u0002\u0002\u0002\u05ef\u05ed\u0003\u0002\u0002\u0002\u05ef\u05ee\u0003\u0002\u0002\u0002װs\u0003\u0002\u0002\u0002ױײ\t\r\u0002\u0002ײu\u0003\u0002\u0002\u0002׳״\t\u000e\u0002\u0002״w\u0003\u0002\u0002\u0002\u05f5\u05fe\u0007\r\u0002\u0002\u05f6\u05fe\t\u000f\u0002\u0002\u05f7\u05fe\t\f\u0002\u0002\u05f8\u05fe\t\u0010\u0002\u0002\u05f9\u05fe\t\u0011\u0002\u0002\u05fa\u05fe\t\u0012\u0002\u0002\u05fb\u05fe\u0007$\u0002\u0002\u05fc\u05fe\u0007p\u0002\u0002\u05fd\u05f5\u0003\u0002\u0002\u0002\u05fd\u05f6\u0003\u0002\u0002\u0002\u05fd\u05f7\u0003\u0002\u0002\u0002\u05fd\u05f8\u0003\u0002\u0002\u0002\u05fd\u05f9\u0003\u0002\u0002\u0002\u05fd\u05fa\u0003\u0002\u0002\u0002\u05fd\u05fb\u0003\u0002\u0002\u0002\u05fd\u05fc\u0003\u0002\u0002\u0002\u05fey\u0003\u0002\u0002\u0002\u05ff\u0600\u0007\u009f\u0002\u0002\u0600{\u0003\u0002\u0002\u0002\u0601\u0604\u0005L'\u0002\u0602\u0604\u0005D#\u0002\u0603\u0601\u0003\u0002\u0002\u0002\u0603\u0602\u0003\u0002\u0002\u0002\u0604}\u0003\u0002\u0002\u0002\u0605؆\t\u0013\u0002\u0002؆\u007f\u0003\u0002\u0002\u0002؇؈\u0007\u0005\u0002\u0002؈؍\u0005\u0092J\u0002؉؊\u0007\u0007\u0002\u0002؊،\u0005\u0092J\u0002؋؉\u0003\u0002\u0002\u0002،؏\u0003\u0002\u0002\u0002؍؋\u0003\u0002\u0002\u0002؍؎\u0003\u0002\u0002\u0002؎ؐ\u0003\u0002\u0002\u0002؏؍\u0003\u0002\u0002\u0002ؐؑ\u0007\u0006\u0002\u0002ؑ\u0081\u0003\u0002\u0002\u0002ؒؓ\t\u0014\u0002\u0002ؓ\u0083\u0003\u0002\u0002\u0002ؔؕ\u0005¨U\u0002ؕ\u0085\u0003\u0002\u0002\u0002ؖؗ\u0005¨U\u0002ؗ\u0087\u0003\u0002\u0002\u0002ؘؙ\u0005¨U\u0002ؙ\u0089\u0003\u0002\u0002\u0002ؚ؛\u0005¨U\u0002؛\u008b\u0003\u0002\u0002\u0002\u061c\u061d\u0005¨U\u0002\u061d\u008d\u0003\u0002\u0002\u0002؞؟\u0005¨U\u0002؟\u008f\u0003\u0002\u0002\u0002ؠء\u0005¨U\u0002ء\u0091\u0003\u0002\u0002\u0002آأ\u0005¨U\u0002أ\u0093\u0003\u0002\u0002\u0002ؤإ\u0005¨U\u0002إ\u0095\u0003\u0002\u0002\u0002ئا\u0005¨U\u0002ا\u0097\u0003\u0002\u0002\u0002بة\u0005¨U\u0002ة\u0099\u0003\u0002\u0002\u0002تث\u0005¨U\u0002ث\u009b\u0003\u0002\u0002\u0002جح\u0005¨U\u0002ح\u009d\u0003\u0002\u0002\u0002خد\u0005¨U\u0002د\u009f\u0003\u0002\u0002\u0002ذر\u0005¨U\u0002ر¡\u0003\u0002\u0002\u0002زس\u0005¨U\u0002س£\u0003\u0002\u0002\u0002شػ\u0007\u009c\u0002\u0002صػ\u0007\u009f\u0002\u0002ضط\u0007\u0005\u0002\u0002طظ\u0005¤S\u0002ظع\u0007\u0006\u0002\u0002عػ\u0003\u0002\u0002\u0002غش\u0003\u0002\u0002\u0002غص\u0003\u0002\u0002\u0002غض\u0003\u0002\u0002\u0002ػ¥\u0003\u0002\u0002\u0002ؼؽ\u0005¨U\u0002ؽ§\u0003\u0002\u0002\u0002ؾن\u0007\u009c\u0002\u0002ؿن\u0005\u0082B\u0002ـن\u0007\u009f\u0002\u0002فق\u0007\u0005\u0002\u0002قك\u0005¨U\u0002كل\u0007\u0006\u0002\u0002لن\u0003\u0002\u0002\u0002مؾ\u0003\u0002\u0002\u0002مؿ\u0003\u0002\u0002\u0002مـ\u0003\u0002\u0002\u0002مف\u0003\u0002\u0002\u0002ن©\u0003\u0002\u0002\u0002å¬®¹ÀÅËÑÓð÷ÿĂċďėěĝĢĤĨĮĳľńňŎœŜţŨŬŰŶŻƂƍƐƒƘƞƢƩƯƵƻǀǈǋǖǛǦǫǮǵǸǿȂȅȉȑȖȞȣȫȰȸȽɂɕɛɠɨɭɶʁʈʎʑʛʡʣʪʱʸʽʿ˅ˎ˕˙˛˟˦˨ˬ˯˵˽̢̘̜̞̫̰̲̻͍͓́̄̈̏͆͐ͦͭ͠ʹ\u0379ͼΉΏΖΝ\u03a2ΥΨάΰελχϋϐϔϗϩϮϳϻЀЉАДОЫЮжпушёіѢѨѯѳѽҀ҆ҌҕҘҜҞҠҫҰҼӀӄӈӏӑәӟӣӦӪӯӷԊԐԔԛԥԪԴԹՀՉՏՒ\u0558՞էժլձյոտք֍\u0590ְֹ֣֮֔֗֟֫־ׂ׆\u05c9הינף\u05ef\u05fd\u0603؍غم";
    public static final ATN _ATN;

    /** @deprecated */
    @Deprecated
    public String[] getTokenNames() {
        return tokenNames;
    }

    public Vocabulary getVocabulary() {
        return VOCABULARY;
    }

    public String getGrammarFileName() {
        return "SQLite.g4";
    }

    public String[] getRuleNames() {
        return ruleNames;
    }

    public String getSerializedATN() {
        return "\u0003悋Ꜫ脳맭䅼㯧瞆奤\u0003¤و\u0004\u0002\t\u0002\u0004\u0003\t\u0003\u0004\u0004\t\u0004\u0004\u0005\t\u0005\u0004\u0006\t\u0006\u0004\u0007\t\u0007\u0004\b\t\b\u0004\t\t\t\u0004\n\t\n\u0004\u000b\t\u000b\u0004\f\t\f\u0004\r\t\r\u0004\u000e\t\u000e\u0004\u000f\t\u000f\u0004\u0010\t\u0010\u0004\u0011\t\u0011\u0004\u0012\t\u0012\u0004\u0013\t\u0013\u0004\u0014\t\u0014\u0004\u0015\t\u0015\u0004\u0016\t\u0016\u0004\u0017\t\u0017\u0004\u0018\t\u0018\u0004\u0019\t\u0019\u0004\u001a\t\u001a\u0004\u001b\t\u001b\u0004\u001c\t\u001c\u0004\u001d\t\u001d\u0004\u001e\t\u001e\u0004\u001f\t\u001f\u0004 \t \u0004!\t!\u0004\"\t\"\u0004#\t#\u0004$\t$\u0004%\t%\u0004&\t&\u0004'\t'\u0004(\t(\u0004)\t)\u0004*\t*\u0004+\t+\u0004,\t,\u0004-\t-\u0004.\t.\u0004/\t/\u00040\t0\u00041\t1\u00042\t2\u00043\t3\u00044\t4\u00045\t5\u00046\t6\u00047\t7\u00048\t8\u00049\t9\u0004:\t:\u0004;\t;\u0004<\t<\u0004=\t=\u0004>\t>\u0004?\t?\u0004@\t@\u0004A\tA\u0004B\tB\u0004C\tC\u0004D\tD\u0004E\tE\u0004F\tF\u0004G\tG\u0004H\tH\u0004I\tI\u0004J\tJ\u0004K\tK\u0004L\tL\u0004M\tM\u0004N\tN\u0004O\tO\u0004P\tP\u0004Q\tQ\u0004R\tR\u0004S\tS\u0004T\tT\u0004U\tU\u0003\u0002\u0003\u0002\u0007\u0002\u00ad\n\u0002\f\u0002\u000e\u0002°\u000b\u0002\u0003\u0002\u0003\u0002\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0004\u0007\u0004¸\n\u0004\f\u0004\u000e\u0004»\u000b\u0004\u0003\u0004\u0003\u0004\u0006\u0004¿\n\u0004\r\u0004\u000e\u0004À\u0003\u0004\u0007\u0004Ä\n\u0004\f\u0004\u000e\u0004Ç\u000b\u0004\u0003\u0004\u0007\u0004Ê\n\u0004\f\u0004\u000e\u0004Í\u000b\u0004\u0003\u0005\u0003\u0005\u0003\u0005\u0005\u0005Ò\n\u0005\u0005\u0005Ô\n\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0005\u0005ñ\n\u0005\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0005\u0006ø\n\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0005\u0006Ā\n\u0006\u0003\u0006\u0005\u0006ă\n\u0006\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0005\u0007Č\n\u0007\u0003\b\u0003\b\u0005\bĐ\n\b\u0003\b\u0003\b\u0003\b\u0003\b\u0003\t\u0003\t\u0005\tĘ\n\t\u0003\t\u0003\t\u0005\tĜ\n\t\u0005\tĞ\n\t\u0003\n\u0003\n\u0003\n\u0005\nģ\n\n\u0005\nĥ\n\n\u0003\u000b\u0003\u000b\u0005\u000bĩ\n\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0005\u000bį\n\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0005\u000bĴ\n\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0007\u000bĽ\n\u000b\f\u000b\u000e\u000bŀ\u000b\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0005\u000bŅ\n\u000b\u0003\f\u0003\f\u0005\fŉ\n\f\u0003\f\u0003\f\u0003\f\u0003\f\u0005\fŏ\n\f\u0003\f\u0003\f\u0003\f\u0005\fŔ\n\f\u0003\f\u0003\f\u0003\f\u0003\f\u0003\f\u0007\fś\n\f\f\f\u000e\fŞ\u000b\f\u0003\f\u0003\f\u0007\fŢ\n\f\f\f\u000e\fť\u000b\f\u0003\f\u0003\f\u0005\fũ\n\f\u0003\f\u0003\f\u0005\fŭ\n\f\u0003\r\u0003\r\u0005\rű\n\r\u0003\r\u0003\r\u0003\r\u0003\r\u0005\rŷ\n\r\u0003\r\u0003\r\u0003\r\u0005\rż\n\r\u0003\r\u0003\r\u0003\r\u0003\r\u0003\r\u0005\rƃ\n\r\u0003\r\u0003\r\u0003\r\u0003\r\u0003\r\u0003\r\u0003\r\u0007\rƌ\n\r\f\r\u000e\rƏ\u000b\r\u0005\rƑ\n\r\u0005\rƓ\n\r\u0003\r\u0003\r\u0003\r\u0003\r\u0005\rƙ\n\r\u0003\r\u0003\r\u0003\r\u0003\r\u0005\rƟ\n\r\u0003\r\u0003\r\u0005\rƣ\n\r\u0003\r\u0003\r\u0003\r\u0003\r\u0003\r\u0005\rƪ\n\r\u0003\r\u0003\r\u0006\rƮ\n\r\r\r\u000e\rƯ\u0003\r\u0003\r\u0003\u000e\u0003\u000e\u0005\u000eƶ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0005\u000eƼ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0005\u000eǁ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0007\u000eǇ\n\u000e\f\u000e\u000e\u000eǊ\u000b\u000e\u0005\u000eǌ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0005\u000fǗ\n\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0005\u000fǜ\n\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0007\u000fǥ\n\u000f\f\u000f\u000e\u000fǨ\u000b\u000f\u0003\u000f\u0003\u000f\u0005\u000fǬ\n\u000f\u0003\u0010\u0005\u0010ǯ\n\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0005\u0010Ƕ\n\u0010\u0003\u0011\u0005\u0011ǹ\n\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0005\u0011Ȁ\n\u0011\u0003\u0011\u0005\u0011ȃ\n\u0011\u0003\u0011\u0005\u0011Ȇ\n\u0011\u0003\u0012\u0003\u0012\u0005\u0012Ȋ\n\u0012\u0003\u0012\u0003\u0012\u0003\u0013\u0003\u0013\u0003\u0013\u0003\u0013\u0005\u0013Ȓ\n\u0013\u0003\u0013\u0003\u0013\u0003\u0013\u0005\u0013ȗ\n\u0013\u0003\u0013\u0003\u0013\u0003\u0014\u0003\u0014\u0003\u0014\u0003\u0014\u0005\u0014ȟ\n\u0014\u0003\u0014\u0003\u0014\u0003\u0014\u0005\u0014Ȥ\n\u0014\u0003\u0014\u0003\u0014\u0003\u0015\u0003\u0015\u0003\u0015\u0003\u0015\u0005\u0015Ȭ\n\u0015\u0003\u0015\u0003\u0015\u0003\u0015\u0005\u0015ȱ\n\u0015\u0003\u0015\u0003\u0015\u0003\u0016\u0003\u0016\u0003\u0016\u0003\u0016\u0005\u0016ȹ\n\u0016\u0003\u0016\u0003\u0016\u0003\u0016\u0005\u0016Ⱦ\n\u0016\u0003\u0016\u0003\u0016\u0003\u0017\u0005\u0017Ƀ\n\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0005\u0017ɖ\n\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0005\u0017ɜ\n\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0005\u0017ɡ\n\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0007\u0017ɧ\n\u0017\f\u0017\u000e\u0017ɪ\u000b\u0017\u0003\u0017\u0003\u0017\u0005\u0017ɮ\n\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0007\u0017ɵ\n\u0017\f\u0017\u000e\u0017ɸ\u000b\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0007\u0017ʀ\n\u0017\f\u0017\u000e\u0017ʃ\u000b\u0017\u0003\u0017\u0003\u0017\u0007\u0017ʇ\n\u0017\f\u0017\u000e\u0017ʊ\u000b\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0005\u0017ʏ\n\u0017\u0003\u0017\u0005\u0017ʒ\n\u0017\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0007\u0018ʚ\n\u0018\f\u0018\u000e\u0018ʝ\u000b\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0005\u0018ʢ\n\u0018\u0005\u0018ʤ\n\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0005\u0018ʫ\n\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0005\u0018ʲ\n\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0007\u0018ʷ\n\u0018\f\u0018\u000e\u0018ʺ\u000b\u0018\u0003\u0018\u0003\u0018\u0005\u0018ʾ\n\u0018\u0005\u0018ˀ\n\u0018\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0005\u0019ˆ\n\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0005\u0019ˏ\n\u0019\u0003\u001a\u0003\u001a\u0003\u001a\u0003\u001a\u0003\u001a\u0005\u001a˖\n\u001a\u0003\u001a\u0003\u001a\u0005\u001a˚\n\u001a\u0005\u001a˜\n\u001a\u0003\u001b\u0003\u001b\u0005\u001bˠ\n\u001b\u0003\u001b\u0003\u001b\u0003\u001c\u0003\u001c\u0003\u001c\u0005\u001c˧\n\u001c\u0005\u001c˩\n\u001c\u0003\u001c\u0003\u001c\u0005\u001c˭\n\u001c\u0003\u001c\u0005\u001c˰\n\u001c\u0003\u001d\u0003\u001d\u0003\u001d\u0003\u001e\u0005\u001e˶\n\u001e\u0003\u001e\u0003\u001e\u0003\u001e\u0003\u001e\u0007\u001e˼\n\u001e\f\u001e\u000e\u001e˿\u000b\u001e\u0003\u001e\u0005\u001ê\n\u001e\u0003\u001e\u0005\u001e̅\n\u001e\u0003\u001f\u0003\u001f\u0005\u001f̉\n\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0007\u001f̎\n\u001f\f\u001f\u000e\u001f̑\u000b\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0007\u001f̗\n\u001f\f\u001f\u000e\u001f̚\u000b\u001f\u0003\u001f\u0005\u001f̝\n\u001f\u0005\u001f̟\n\u001f\u0003\u001f\u0003\u001f\u0005\u001f̣\n\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0007\u001f̪\n\u001f\f\u001f\u000e\u001f̭\u000b\u001f\u0003\u001f\u0003\u001f\u0005\u001f̱\n\u001f\u0005\u001f̳\n\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0007\u001f̺\n\u001f\f\u001f\u000e\u001f̽\u000b\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0007\u001fͅ\n\u001f\f\u001f\u000e\u001f͈\u000b\u001f\u0003\u001f\u0003\u001f\u0007\u001f͌\n\u001f\f\u001f\u000e\u001f͏\u000b\u001f\u0005\u001f͑\n\u001f\u0003 \u0005 ͔\n \u0003 \u0003 \u0003 \u0003 \u0003 \u0003 \u0003 \u0003 \u0003 \u0003 \u0003 \u0005 ͡\n \u0003 \u0003 \u0003 \u0003 \u0005 ͧ\n \u0003 \u0003 \u0003 \u0003 \u0003 \u0005 ͮ\n \u0003 \u0003 \u0003 \u0007 ͳ\n \f \u000e Ͷ\u000b \u0003 \u0003 \u0005 ͺ\n \u0003!\u0005!ͽ\n!\u0003!\u0003!\u0003!\u0003!\u0003!\u0003!\u0003!\u0003!\u0003!\u0003!\u0003!\u0005!Ί\n!\u0003!\u0003!\u0003!\u0003!\u0005!ΐ\n!\u0003!\u0003!\u0003!\u0003!\u0003!\u0005!Η\n!\u0003!\u0003!\u0003!\u0007!Μ\n!\f!\u000e!Ο\u000b!\u0003!\u0003!\u0005!Σ\n!\u0003!\u0005!Φ\n!\u0003!\u0005!Ω\n!\u0003\"\u0003\"\u0005\"έ\n\"\u0003#\u0003#\u0005#α\n#\u0003#\u0007#δ\n#\f#\u000e#η\u000b#\u0003$\u0006$κ\n$\r$\u000e$λ\u0003$\u0003$\u0003$\u0003$\u0003$\u0003$\u0003$\u0003$\u0003$\u0003$\u0005$ψ\n$\u0003%\u0003%\u0005%ό\n%\u0003%\u0003%\u0003%\u0005%ϑ\n%\u0003%\u0003%\u0005%ϕ\n%\u0003%\u0005%Ϙ\n%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0005%Ϫ\n%\u0003%\u0003%\u0003%\u0005%ϯ\n%\u0003&\u0003&\u0003&\u0005&ϴ\n&\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0005'ϼ\n'\u0003'\u0003'\u0003'\u0005'Ё\n'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0005'Њ\n'\u0003'\u0003'\u0003'\u0007'Џ\n'\f'\u000e'В\u000b'\u0003'\u0005'Е\n'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0007'Н\n'\f'\u000e'Р\u000b'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0005'Ь\n'\u0003'\u0005'Я\n'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0005'з\n'\u0003'\u0003'\u0003'\u0003'\u0003'\u0006'о\n'\r'\u000e'п\u0003'\u0003'\u0005'ф\n'\u0003'\u0003'\u0003'\u0005'щ\n'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0005'ђ\n'\u0003'\u0003'\u0003'\u0005'ї\n'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0005'ѣ\n'\u0003'\u0003'\u0003'\u0003'\u0005'ѩ\n'\u0003'\u0003'\u0003'\u0003'\u0003'\u0005'Ѱ\n'\u0003'\u0003'\u0005'Ѵ\n'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0007'Ѽ\n'\f'\u000e'ѿ\u000b'\u0005'ҁ\n'\u0003'\u0003'\u0003'\u0003'\u0005'҇\n'\u0003'\u0003'\u0003'\u0003'\u0005'ҍ\n'\u0003'\u0003'\u0003'\u0003'\u0003'\u0007'Ҕ\n'\f'\u000e'җ\u000b'\u0005'ҙ\n'\u0003'\u0003'\u0005'ҝ\n'\u0007'ҟ\n'\f'\u000e'Ң\u000b'\u0003(\u0003(\u0003(\u0003(\u0003(\u0003(\u0007(Ҫ\n(\f(\u000e(ҭ\u000b(\u0003(\u0003(\u0005(ұ\n(\u0003(\u0003(\u0003(\u0003(\u0003(\u0003(\u0003(\u0003(\u0003(\u0003(\u0005(ҽ\n(\u0003(\u0003(\u0005(Ӂ\n(\u0007(Ӄ\n(\f(\u000e(ӆ\u000b(\u0003(\u0005(Ӊ\n(\u0003(\u0003(\u0003(\u0003(\u0003(\u0005(Ӑ\n(\u0005(Ӓ\n(\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0005)Ӛ\n)\u0003)\u0003)\u0003*\u0003*\u0005*Ӡ\n*\u0003*\u0003*\u0005*Ӥ\n*\u0003*\u0005*ӧ\n*\u0003+\u0003+\u0005+ӫ\n+\u0003+\u0003+\u0003+\u0005+Ӱ\n+\u0003+\u0003+\u0003+\u0003+\u0007+Ӷ\n+\f+\u000e+ӹ\u000b+\u0003+\u0003+\u0003+\u0003+\u0003+\u0003+\u0003+\u0003+\u0003+\u0003+\u0003+\u0003+\u0003+\u0003+\u0007+ԉ\n+\f+\u000e+Ԍ\u000b+\u0003+\u0003+\u0003+\u0005+ԑ\n+\u0003,\u0003,\u0005,ԕ\n,\u0003,\u0003,\u0003,\u0007,Ԛ\n,\f,\u000e,ԝ\u000b,\u0003-\u0003-\u0003-\u0003-\u0003-\u0007-Ԥ\n-\f-\u000e-ԧ\u000b-\u0003-\u0003-\u0005-ԫ\n-\u0003-\u0003-\u0003-\u0003-\u0003-\u0003.\u0003.\u0003.\u0005.Ե\n.\u0003.\u0003.\u0003.\u0005.Ժ\n.\u0003.\u0003.\u0003.\u0003.\u0003.\u0005.Ձ\n.\u0003/\u0003/\u0003/\u0003/\u0003/\u0007/Ո\n/\f/\u000e/Ջ\u000b/\u00030\u00030\u00030\u00050Ր\n0\u00030\u00050Փ\n0\u00031\u00031\u00031\u00031\u00051ՙ\n1\u00032\u00032\u00032\u00032\u00052՟\n2\u00033\u00033\u00033\u00033\u00033\u00033\u00033\u00053ը\n3\u00033\u00053ի\n3\u00053խ\n3\u00034\u00034\u00034\u00054ղ\n4\u00034\u00034\u00054ն\n4\u00034\u00054չ\n4\u00034\u00034\u00034\u00034\u00034\u00054ր\n4\u00034\u00034\u00034\u00054օ\n4\u00034\u00034\u00034\u00034\u00034\u00074\u058c\n4\f4\u000e4֏\u000b4\u00054֑\n4\u00034\u00034\u00054֕\n4\u00034\u00054֘\n4\u00034\u00034\u00034\u00034\u00074֞\n4\f4\u000e4֡\u000b4\u00034\u00054֤\n4\u00034\u00034\u00034\u00034\u00034\u00034\u00054֬\n4\u00034\u00054֯\n4\u00054ֱ\n4\u00035\u00035\u00035\u00035\u00035\u00075ָ\n5\f5\u000e5ֻ\u000b5\u00036\u00036\u00056ֿ\n6\u00036\u00036\u00056׃\n6\u00036\u00036\u00056ׇ\n6\u00036\u00056\u05ca\n6\u00037\u00037\u00037\u00037\u00037\u00037\u00037\u00077ד\n7\f7\u000e7ז\u000b7\u00037\u00037\u00057ך\n7\u00038\u00038\u00038\u00038\u00038\u00058ס\n8\u00039\u00059פ\n9\u00039\u00039\u0003:\u0003:\u0003:\u0003:\u0003:\u0003:\u0003:\u0003:\u0005:װ\n:\u0003;\u0003;\u0003<\u0003<\u0003=\u0003=\u0003=\u0003=\u0003=\u0003=\u0003=\u0003=\u0005=\u05fe\n=\u0003>\u0003>\u0003?\u0003?\u0005?\u0604\n?\u0003@\u0003@\u0003A\u0003A\u0003A\u0003A\u0007A،\nA\fA\u000eA؏\u000bA\u0003A\u0003A\u0003B\u0003B\u0003C\u0003C\u0003D\u0003D\u0003E\u0003E\u0003F\u0003F\u0003G\u0003G\u0003H\u0003H\u0003I\u0003I\u0003J\u0003J\u0003K\u0003K\u0003L\u0003L\u0003M\u0003M\u0003N\u0003N\u0003O\u0003O\u0003P\u0003P\u0003Q\u0003Q\u0003R\u0003R\u0003S\u0003S\u0003S\u0003S\u0003S\u0003S\u0005Sػ\nS\u0003T\u0003T\u0003U\u0003U\u0003U\u0003U\u0003U\u0003U\u0003U\u0005Uن\nU\u0003U\u0004Ŝλ\u0003LV\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e ¢¤¦¨\u0002\u0015\u0005\u0002>>IIVV\u0004\u000233FF\u0003\u0002\u0087\u0088\u0004\u0002!!BB\u0004\u0002&&@@\u0007\u0002\u001d\u001dLLUU~~\u0081\u0081\u0006\u0002QQeeggzz\u0004\u0002??\u008f\u008f\u0005\u0002\u001d\u001dLL\u0081\u0081\u0004\u0002\u0007\u0007nn\u0003\u0002\n\u000b\u0003\u0002\u001b\u001c\u0004\u0002\n\fjj\u0004\u0002\t\t\u000e\u000f\u0003\u0002\u0010\u0013\u0003\u0002\u0014\u0017\u0004\u0002\b\b\u0018\u001a\u0004\u0002\u009c\u009c\u009f\u009f\u0003\u0002\u001d\u0098\u0002ܾ\u0002®\u0003\u0002\u0002\u0002\u0004³\u0003\u0002\u0002\u0002\u0006¹\u0003\u0002\u0002\u0002\bÓ\u0003\u0002\u0002\u0002\nò\u0003\u0002\u0002\u0002\fĄ\u0003\u0002\u0002\u0002\u000eč\u0003\u0002\u0002\u0002\u0010ĕ\u0003\u0002\u0002\u0002\u0012ğ\u0003\u0002\u0002\u0002\u0014Ħ\u0003\u0002\u0002\u0002\u0016ņ\u0003\u0002\u0002\u0002\u0018Ů\u0003\u0002\u0002\u0002\u001aƳ\u0003\u0002\u0002\u0002\u001cǐ\u0003\u0002\u0002\u0002\u001eǮ\u0003\u0002\u0002\u0002 Ǹ\u0003\u0002\u0002\u0002\"ȇ\u0003\u0002\u0002\u0002$ȍ\u0003\u0002\u0002\u0002&Ț\u0003\u0002\u0002\u0002(ȧ\u0003\u0002\u0002\u0002*ȴ\u0003\u0002\u0002\u0002,ɂ\u0003\u0002\u0002\u0002.ʓ\u0003\u0002\u0002\u00020ˁ\u0003\u0002\u0002\u00022ː\u0003\u0002\u0002\u00024˝\u0003\u0002\u0002\u00026ˣ\u0003\u0002\u0002\u00028˱\u0003\u0002\u0002\u0002:˵\u0003\u0002\u0002\u0002<͐\u0003\u0002\u0002\u0002>͓\u0003\u0002\u0002\u0002@ͼ\u0003\u0002\u0002\u0002BΪ\u0003\u0002\u0002\u0002Dή\u0003\u0002\u0002\u0002Fι\u0003\u0002\u0002\u0002Hϋ\u0003\u0002\u0002\u0002Jϳ\u0003\u0002\u0002\u0002Lш\u0003\u0002\u0002\u0002Nң\u0003\u0002\u0002\u0002Pӓ\u0003\u0002\u0002\u0002Rӟ\u0003\u0002\u0002\u0002TӪ\u0003\u0002\u0002\u0002VԒ\u0003\u0002\u0002\u0002XԞ\u0003\u0002\u0002\u0002ZԴ\u0003\u0002\u0002\u0002\\Ղ\u0003\u0002\u0002\u0002^Ռ\u0003\u0002\u0002\u0002`Ք\u0003\u0002\u0002\u0002b՞\u0003\u0002\u0002\u0002dլ\u0003\u0002\u0002\u0002fְ\u0003\u0002\u0002\u0002hֲ\u0003\u0002\u0002\u0002j\u05c9\u0003\u0002\u0002\u0002lי\u0003\u0002\u0002\u0002nנ\u0003\u0002\u0002\u0002pף\u0003\u0002\u0002\u0002r\u05ef\u0003\u0002\u0002\u0002tױ\u0003\u0002\u0002\u0002v׳\u0003\u0002\u0002\u0002x\u05fd\u0003\u0002\u0002\u0002z\u05ff\u0003\u0002\u0002\u0002|\u0603\u0003\u0002\u0002\u0002~\u0605\u0003\u0002\u0002\u0002\u0080؇\u0003\u0002\u0002\u0002\u0082ؒ\u0003\u0002\u0002\u0002\u0084ؔ\u0003\u0002\u0002\u0002\u0086ؖ\u0003\u0002\u0002\u0002\u0088ؘ\u0003\u0002\u0002\u0002\u008aؚ\u0003\u0002\u0002\u0002\u008c\u061c\u0003\u0002\u0002\u0002\u008e؞\u0003\u0002\u0002\u0002\u0090ؠ\u0003\u0002\u0002\u0002\u0092آ\u0003\u0002\u0002\u0002\u0094ؤ\u0003\u0002\u0002\u0002\u0096ئ\u0003\u0002\u0002\u0002\u0098ب\u0003\u0002\u0002\u0002\u009aت\u0003\u0002\u0002\u0002\u009cج\u0003\u0002\u0002\u0002\u009eخ\u0003\u0002\u0002\u0002 ذ\u0003\u0002\u0002\u0002¢ز\u0003\u0002\u0002\u0002¤غ\u0003\u0002\u0002\u0002¦ؼ\u0003\u0002\u0002\u0002¨م\u0003\u0002\u0002\u0002ª\u00ad\u0005\u0006\u0004\u0002«\u00ad\u0005\u0004\u0003\u0002¬ª\u0003\u0002\u0002\u0002¬«\u0003\u0002\u0002\u0002\u00ad°\u0003\u0002\u0002\u0002®¬\u0003\u0002\u0002\u0002®¯\u0003\u0002\u0002\u0002¯±\u0003\u0002\u0002\u0002°®\u0003\u0002\u0002\u0002±²\u0007\u0002\u0002\u0003²\u0003\u0003\u0002\u0002\u0002³´\u0007¤\u0002\u0002´µ\b\u0003\u0001\u0002µ\u0005\u0003\u0002\u0002\u0002¶¸\u0007\u0003\u0002\u0002·¶\u0003\u0002\u0002\u0002¸»\u0003\u0002\u0002\u0002¹·\u0003\u0002\u0002\u0002¹º\u0003\u0002\u0002\u0002º¼\u0003\u0002\u0002\u0002»¹\u0003\u0002\u0002\u0002¼Å\u0005\b\u0005\u0002½¿\u0007\u0003\u0002\u0002¾½\u0003\u0002\u0002\u0002¿À\u0003\u0002\u0002\u0002À¾\u0003\u0002\u0002\u0002ÀÁ\u0003\u0002\u0002\u0002ÁÂ\u0003\u0002\u0002\u0002ÂÄ\u0005\b\u0005\u0002Ã¾\u0003\u0002\u0002\u0002ÄÇ\u0003\u0002\u0002\u0002ÅÃ\u0003\u0002\u0002\u0002ÅÆ\u0003\u0002\u0002\u0002ÆË\u0003\u0002\u0002\u0002ÇÅ\u0003\u0002\u0002\u0002ÈÊ\u0007\u0003\u0002\u0002ÉÈ\u0003\u0002\u0002\u0002ÊÍ\u0003\u0002\u0002\u0002ËÉ\u0003\u0002\u0002\u0002ËÌ\u0003\u0002\u0002\u0002Ì\u0007\u0003\u0002\u0002\u0002ÍË\u0003\u0002\u0002\u0002ÎÑ\u0007K\u0002\u0002ÏÐ\u0007v\u0002\u0002ÐÒ\u0007s\u0002\u0002ÑÏ\u0003\u0002\u0002\u0002ÑÒ\u0003\u0002\u0002\u0002ÒÔ\u0003\u0002\u0002\u0002ÓÎ\u0003\u0002\u0002\u0002ÓÔ\u0003\u0002\u0002\u0002Ôð\u0003\u0002\u0002\u0002Õñ\u0005\n\u0006\u0002Öñ\u0005\f\u0007\u0002×ñ\u0005\u000e\b\u0002Øñ\u0005\u0010\t\u0002Ùñ\u0005\u0012\n\u0002Úñ\u0005\u0014\u000b\u0002Ûñ\u0005\u0016\f\u0002Üñ\u0005\u0018\r\u0002Ýñ\u0005\u001a\u000e\u0002Þñ\u0005\u001c\u000f\u0002ßñ\u0005\u001e\u0010\u0002àñ\u0005 \u0011\u0002áñ\u0005\"\u0012\u0002âñ\u0005$\u0013\u0002ãñ\u0005&\u0014\u0002äñ\u0005(\u0015\u0002åñ\u0005*\u0016\u0002æñ\u0005,\u0017\u0002çñ\u00050\u0019\u0002èñ\u00052\u001a\u0002éñ\u00054\u001b\u0002êñ\u00056\u001c\u0002ëñ\u00058\u001d\u0002ìñ\u0005:\u001e\u0002íñ\u0005> \u0002îñ\u0005@!\u0002ïñ\u0005B\"\u0002ðÕ\u0003\u0002\u0002\u0002ðÖ\u0003\u0002\u0002\u0002ð×\u0003\u0002\u0002\u0002ðØ\u0003\u0002\u0002\u0002ðÙ\u0003\u0002\u0002\u0002ðÚ\u0003\u0002\u0002\u0002ðÛ\u0003\u0002\u0002\u0002ðÜ\u0003\u0002\u0002\u0002ðÝ\u0003\u0002\u0002\u0002ðÞ\u0003\u0002\u0002\u0002ðß\u0003\u0002\u0002\u0002ðà\u0003\u0002\u0002\u0002ðá\u0003\u0002\u0002\u0002ðâ\u0003\u0002\u0002\u0002ðã\u0003\u0002\u0002\u0002ðä\u0003\u0002\u0002\u0002ðå\u0003\u0002\u0002\u0002ðæ\u0003\u0002\u0002\u0002ðç\u0003\u0002\u0002\u0002ðè\u0003\u0002\u0002\u0002ðé\u0003\u0002\u0002\u0002ðê\u0003\u0002\u0002\u0002ðë\u0003\u0002\u0002\u0002ðì\u0003\u0002\u0002\u0002ðí\u0003\u0002\u0002\u0002ðî\u0003\u0002\u0002\u0002ðï\u0003\u0002\u0002\u0002ñ\t\u0003\u0002\u0002\u0002òó\u0007\"\u0002\u0002ó÷\u0007\u0086\u0002\u0002ôõ\u0005\u0088E\u0002õö\u0007\u0004\u0002\u0002öø\u0003\u0002\u0002\u0002÷ô\u0003\u0002\u0002\u0002÷ø\u0003\u0002\u0002\u0002øù\u0003\u0002\u0002\u0002ùĂ\u0005\u008cG\u0002úû\u0007}\u0002\u0002ûü\u0007\u008a\u0002\u0002üă\u0005\u0090I\u0002ýÿ\u0007\u001f\u0002\u0002þĀ\u00072\u0002\u0002ÿþ\u0003\u0002\u0002\u0002ÿĀ\u0003\u0002\u0002\u0002Āā\u0003\u0002\u0002\u0002āă\u0005D#\u0002Ăú\u0003\u0002\u0002\u0002Ăý\u0003\u0002\u0002\u0002ă\u000b\u0003\u0002\u0002\u0002Ąċ\u0007#\u0002\u0002ąČ\u0005\u0088E\u0002ĆČ\u0005\u008eH\u0002ćĈ\u0005\u0088E\u0002Ĉĉ\u0007\u0004\u0002\u0002ĉĊ\u0005\u008eH\u0002ĊČ\u0003\u0002\u0002\u0002ċą\u0003\u0002\u0002\u0002ċĆ\u0003\u0002\u0002\u0002ċć\u0003\u0002\u0002\u0002ċČ\u0003\u0002\u0002\u0002Č\r\u0003\u0002\u0002\u0002čď\u0007'\u0002\u0002ĎĐ\u0007;\u0002\u0002ďĎ\u0003\u0002\u0002\u0002ďĐ\u0003\u0002\u0002\u0002Đđ\u0003\u0002\u0002\u0002đĒ\u0005L'\u0002Ēē\u0007%\u0002\u0002ēĔ\u0005\u0088E\u0002Ĕ\u000f\u0003\u0002\u0002\u0002ĕė\u0007*\u0002\u0002ĖĘ\t\u0002\u0002\u0002ėĖ\u0003\u0002\u0002\u0002ėĘ\u0003\u0002\u0002\u0002Ęĝ\u0003\u0002\u0002\u0002ęě\u0007\u008b\u0002\u0002ĚĜ\u0005¦T\u0002ěĚ\u0003\u0002\u0002\u0002ěĜ\u0003\u0002\u0002\u0002ĜĞ\u0003\u0002\u0002\u0002ĝę\u0003\u0002\u0002\u0002ĝĞ\u0003\u0002\u0002\u0002Ğ\u0011\u0003\u0002\u0002\u0002ğĤ\t\u0003\u0002\u0002ĠĢ\u0007\u008b\u0002\u0002ġģ\u0005¦T\u0002Ģġ\u0003\u0002\u0002\u0002Ģģ\u0003\u0002\u0002\u0002ģĥ\u0003\u0002\u0002\u0002ĤĠ\u0003\u0002\u0002\u0002Ĥĥ\u0003\u0002\u0002\u0002ĥ\u0013\u0003\u0002\u0002\u0002ĦĨ\u00076\u0002\u0002ħĩ\u0007\u008e\u0002\u0002Ĩħ\u0003\u0002\u0002\u0002Ĩĩ\u0003\u0002\u0002\u0002ĩĪ\u0003\u0002\u0002\u0002ĪĮ\u0007X\u0002\u0002īĬ\u0007T\u0002\u0002Ĭĭ\u0007j\u0002\u0002ĭį\u0007J\u0002\u0002Įī\u0003\u0002\u0002\u0002Įį\u0003\u0002\u0002\u0002įĳ\u0003\u0002\u0002\u0002İı\u0005\u0088E\u0002ıĲ\u0007\u0004\u0002\u0002ĲĴ\u0003\u0002\u0002\u0002ĳİ\u0003\u0002\u0002\u0002ĳĴ\u0003\u0002\u0002\u0002Ĵĵ\u0003\u0002\u0002\u0002ĵĶ\u0005\u0098M\u0002Ķķ\u0007o\u0002\u0002ķĸ\u0005\u008cG\u0002ĸĹ\u0007\u0005\u0002\u0002Ĺľ\u0005R*\u0002ĺĻ\u0007\u0007\u0002\u0002ĻĽ\u0005R*\u0002ļĺ\u0003\u0002\u0002\u0002Ľŀ\u0003\u0002\u0002\u0002ľļ\u0003\u0002\u0002\u0002ľĿ\u0003\u0002\u0002\u0002ĿŁ\u0003\u0002\u0002\u0002ŀľ\u0003\u0002\u0002\u0002Łń\u0007\u0006\u0002\u0002łŃ\u0007\u0096\u0002\u0002ŃŅ\u0005L'\u0002ńł\u0003\u0002\u0002\u0002ńŅ\u0003\u0002\u0002\u0002Ņ\u0015\u0003\u0002\u0002\u0002ņň\u00076\u0002\u0002Ňŉ\t\u0004\u0002\u0002ňŇ\u0003\u0002\u0002\u0002ňŉ\u0003\u0002\u0002\u0002ŉŊ\u0003\u0002\u0002\u0002ŊŎ\u0007\u0086\u0002\u0002ŋŌ\u0007T\u0002\u0002Ōō\u0007j\u0002\u0002ōŏ\u0007J\u0002\u0002Ŏŋ\u0003\u0002\u0002\u0002Ŏŏ\u0003\u0002\u0002\u0002ŏœ\u0003\u0002\u0002\u0002Őő\u0005\u0088E\u0002őŒ\u0007\u0004\u0002\u0002ŒŔ\u0003\u0002\u0002\u0002œŐ\u0003\u0002\u0002\u0002œŔ\u0003\u0002\u0002\u0002Ŕŕ\u0003\u0002\u0002\u0002ŕŬ\u0005\u008cG\u0002Ŗŗ\u0007\u0005\u0002\u0002ŗŜ\u0005D#\u0002Řř\u0007\u0007\u0002\u0002řś\u0005D#\u0002ŚŘ\u0003\u0002\u0002\u0002śŞ\u0003\u0002\u0002\u0002Ŝŝ\u0003\u0002\u0002\u0002ŜŚ\u0003\u0002\u0002\u0002ŝţ\u0003\u0002\u0002\u0002ŞŜ\u0003\u0002\u0002\u0002şŠ\u0007\u0007\u0002\u0002ŠŢ\u0005T+\u0002šş\u0003\u0002\u0002\u0002Ţť\u0003\u0002\u0002\u0002ţš\u0003\u0002\u0002\u0002ţŤ\u0003\u0002\u0002\u0002ŤŦ\u0003\u0002\u0002\u0002ťţ\u0003\u0002\u0002\u0002ŦŨ\u0007\u0006\u0002\u0002ŧũ\u0007\u0099\u0002\u0002Ũŧ\u0003\u0002\u0002\u0002Ũũ\u0003\u0002\u0002\u0002ũŭ\u0003\u0002\u0002\u0002Ūū\u0007%\u0002\u0002ūŭ\u0005:\u001e\u0002ŬŖ\u0003\u0002\u0002\u0002ŬŪ\u0003\u0002\u0002\u0002ŭ\u0017\u0003\u0002\u0002\u0002ŮŰ\u00076\u0002\u0002ůű\t\u0004\u0002\u0002Űů\u0003\u0002\u0002\u0002Űű\u0003\u0002\u0002\u0002űŲ\u0003\u0002\u0002\u0002ŲŶ\u0007\u008c\u0002\u0002ųŴ\u0007T\u0002\u0002Ŵŵ\u0007j\u0002\u0002ŵŷ\u0007J\u0002\u0002Ŷų\u0003\u0002\u0002\u0002Ŷŷ\u0003\u0002\u0002\u0002ŷŻ\u0003\u0002\u0002\u0002ŸŹ\u0005\u0088E\u0002Źź\u0007\u0004\u0002\u0002źż\u0003\u0002\u0002\u0002ŻŸ\u0003\u0002\u0002\u0002Żż\u0003\u0002\u0002\u0002żŽ\u0003\u0002\u0002\u0002ŽƂ\u0005\u009aN\u0002žƃ\u0007)\u0002\u0002ſƃ\u0007 \u0002\u0002ƀƁ\u0007]\u0002\u0002Ɓƃ\u0007m\u0002\u0002Ƃž\u0003\u0002\u0002\u0002Ƃſ\u0003\u0002\u0002\u0002Ƃƀ\u0003\u0002\u0002\u0002Ƃƃ\u0003\u0002\u0002\u0002ƃƒ\u0003\u0002\u0002\u0002ƄƓ\u0007?\u0002\u0002ƅƓ\u0007\\\u0002\u0002ƆƐ\u0007\u008f\u0002\u0002Ƈƈ\u0007m\u0002\u0002ƈƍ\u0005\u0092J\u0002ƉƊ\u0007\u0007\u0002\u0002Ɗƌ\u0005\u0092J\u0002ƋƉ\u0003\u0002\u0002\u0002ƌƏ\u0003\u0002\u0002\u0002ƍƋ\u0003\u0002\u0002\u0002ƍƎ\u0003\u0002\u0002\u0002ƎƑ\u0003\u0002\u0002\u0002Əƍ\u0003\u0002\u0002\u0002ƐƇ\u0003\u0002\u0002\u0002ƐƑ\u0003\u0002\u0002\u0002ƑƓ\u0003\u0002\u0002\u0002ƒƄ\u0003\u0002\u0002\u0002ƒƅ\u0003\u0002\u0002\u0002ƒƆ\u0003\u0002\u0002\u0002ƓƔ\u0003\u0002\u0002\u0002ƔƘ\u0007o\u0002\u0002ƕƖ\u0005\u0088E\u0002ƖƗ\u0007\u0004\u0002\u0002Ɨƙ\u0003\u0002\u0002\u0002Ƙƕ\u0003\u0002\u0002\u0002Ƙƙ\u0003\u0002\u0002\u0002ƙƚ\u0003\u0002\u0002\u0002ƚƞ\u0005\u008cG\u0002ƛƜ\u0007M\u0002\u0002ƜƝ\u0007D\u0002\u0002ƝƟ\u0007\u0082\u0002\u0002ƞƛ\u0003\u0002\u0002\u0002ƞƟ\u0003\u0002\u0002\u0002ƟƢ\u0003\u0002\u0002\u0002Ơơ\u0007\u0095\u0002\u0002ơƣ\u0005L'\u0002ƢƠ\u0003\u0002\u0002\u0002Ƣƣ\u0003\u0002\u0002\u0002ƣƤ\u0003\u0002\u0002\u0002Ƥƭ\u0007*\u0002\u0002ƥƪ\u0005> \u0002Ʀƪ\u0005,\u0017\u0002Ƨƪ\u0005\u001e\u0010\u0002ƨƪ\u0005:\u001e\u0002Ʃƥ\u0003\u0002\u0002\u0002ƩƦ\u0003\u0002\u0002\u0002ƩƧ\u0003\u0002\u0002\u0002Ʃƨ\u0003\u0002\u0002\u0002ƪƫ\u0003\u0002\u0002\u0002ƫƬ\u0007\u0003\u0002\u0002ƬƮ\u0003\u0002\u0002\u0002ƭƩ\u0003\u0002\u0002\u0002ƮƯ\u0003\u0002\u0002\u0002Ưƭ\u0003\u0002\u0002\u0002Ưư\u0003\u0002\u0002\u0002ưƱ\u0003\u0002\u0002\u0002ƱƲ\u0007F\u0002\u0002Ʋ\u0019\u0003\u0002\u0002\u0002ƳƵ\u00076\u0002\u0002ƴƶ\t\u0004\u0002\u0002Ƶƴ\u0003\u0002\u0002\u0002Ƶƶ\u0003\u0002\u0002\u0002ƶƷ\u0003\u0002\u0002\u0002Ʒƻ\u0007\u0093\u0002\u0002Ƹƹ\u0007T\u0002\u0002ƹƺ\u0007j\u0002\u0002ƺƼ\u0007J\u0002\u0002ƻƸ\u0003\u0002\u0002\u0002ƻƼ\u0003\u0002\u0002\u0002Ƽǀ\u0003\u0002\u0002\u0002ƽƾ\u0005\u0088E\u0002ƾƿ\u0007\u0004\u0002\u0002ƿǁ\u0003\u0002\u0002\u0002ǀƽ\u0003\u0002\u0002\u0002ǀǁ\u0003\u0002\u0002\u0002ǁǂ\u0003\u0002\u0002\u0002ǂǋ\u0005\u009cO\u0002ǃǈ\u0005\u0092J\u0002Ǆǅ\u0007\u0007\u0002\u0002ǅǇ\u0005\u0092J\u0002ǆǄ\u0003\u0002\u0002\u0002ǇǊ\u0003\u0002\u0002\u0002ǈǆ\u0003\u0002\u0002\u0002ǈǉ\u0003\u0002\u0002\u0002ǉǌ\u0003\u0002\u0002\u0002Ǌǈ\u0003\u0002\u0002\u0002ǋǃ\u0003\u0002\u0002\u0002ǋǌ\u0003\u0002\u0002\u0002ǌǍ\u0003\u0002\u0002\u0002Ǎǎ\u0007%\u0002\u0002ǎǏ\u0005:\u001e\u0002Ǐ\u001b\u0003\u0002\u0002\u0002ǐǑ\u00076\u0002\u0002Ǒǒ\u0007\u0094\u0002\u0002ǒǖ\u0007\u0086\u0002\u0002Ǔǔ\u0007T\u0002\u0002ǔǕ\u0007j\u0002\u0002ǕǗ\u0007J\u0002\u0002ǖǓ\u0003\u0002\u0002\u0002ǖǗ\u0003\u0002\u0002\u0002ǗǛ\u0003\u0002\u0002\u0002ǘǙ\u0005\u0088E\u0002Ǚǚ\u0007\u0004\u0002\u0002ǚǜ\u0003\u0002\u0002\u0002Ǜǘ\u0003\u0002\u0002\u0002Ǜǜ\u0003\u0002\u0002\u0002ǜǝ\u0003\u0002\u0002\u0002ǝǞ\u0005\u008cG\u0002Ǟǟ\u0007\u0090\u0002\u0002ǟǫ\u0005\u009eP\u0002Ǡǡ\u0007\u0005\u0002\u0002ǡǦ\u0005|?\u0002Ǣǣ\u0007\u0007\u0002\u0002ǣǥ\u0005|?\u0002ǤǢ\u0003\u0002\u0002\u0002ǥǨ\u0003\u0002\u0002\u0002ǦǤ\u0003\u0002\u0002\u0002Ǧǧ\u0003\u0002\u0002\u0002ǧǩ\u0003\u0002\u0002\u0002ǨǦ\u0003\u0002\u0002\u0002ǩǪ\u0007\u0006\u0002\u0002ǪǬ\u0003\u0002\u0002\u0002ǫǠ\u0003\u0002\u0002\u0002ǫǬ\u0003\u0002\u0002\u0002Ǭ\u001d\u0003\u0002\u0002\u0002ǭǯ\u0005V,\u0002Ǯǭ\u0003\u0002\u0002\u0002Ǯǯ\u0003\u0002\u0002\u0002ǯǰ\u0003\u0002\u0002\u0002ǰǱ\u0007?\u0002\u0002Ǳǲ\u0007O\u0002\u0002ǲǵ\u0005Z.\u0002ǳǴ\u0007\u0096\u0002\u0002ǴǶ\u0005L'\u0002ǵǳ\u0003\u0002\u0002\u0002ǵǶ\u0003\u0002\u0002\u0002Ƕ\u001f\u0003\u0002\u0002\u0002Ƿǹ\u0005V,\u0002ǸǷ\u0003\u0002\u0002\u0002Ǹǹ\u0003\u0002\u0002\u0002ǹǺ\u0003\u0002\u0002\u0002Ǻǻ\u0007?\u0002\u0002ǻǼ\u0007O\u0002\u0002Ǽǿ\u0005Z.\u0002ǽǾ\u0007\u0096\u0002\u0002ǾȀ\u0005L'\u0002ǿǽ\u0003\u0002\u0002\u0002ǿȀ\u0003\u0002\u0002\u0002Ȁȅ\u0003\u0002\u0002\u0002ȁȃ\u0005\\/\u0002Ȃȁ\u0003\u0002\u0002\u0002Ȃȃ\u0003\u0002\u0002\u0002ȃȄ\u0003\u0002\u0002\u0002ȄȆ\u0005`1\u0002ȅȂ\u0003\u0002\u0002\u0002ȅȆ\u0003\u0002\u0002\u0002Ȇ!\u0003\u0002\u0002\u0002ȇȉ\u0007A\u0002\u0002ȈȊ\u0007;\u0002\u0002ȉȈ\u0003\u0002\u0002\u0002ȉȊ\u0003\u0002\u0002\u0002Ȋȋ\u0003\u0002\u0002\u0002ȋȌ\u0005\u0088E\u0002Ȍ#\u0003\u0002\u0002\u0002ȍȎ\u0007C\u0002\u0002Ȏȑ\u0007X\u0002\u0002ȏȐ\u0007T\u0002\u0002ȐȒ\u0007J\u0002\u0002ȑȏ\u0003\u0002\u0002\u0002ȑȒ\u0003\u0002\u0002\u0002ȒȖ\u0003\u0002\u0002\u0002ȓȔ\u0005\u0088E\u0002Ȕȕ\u0007\u0004\u0002\u0002ȕȗ\u0003\u0002\u0002\u0002Ȗȓ\u0003\u0002\u0002\u0002Ȗȗ\u0003\u0002\u0002\u0002ȗȘ\u0003\u0002\u0002\u0002Șș\u0005\u0098M\u0002ș%\u0003\u0002\u0002\u0002Țț\u0007C\u0002\u0002țȞ\u0007\u0086\u0002\u0002Ȝȝ\u0007T\u0002\u0002ȝȟ\u0007J\u0002\u0002ȞȜ\u0003\u0002\u0002\u0002Ȟȟ\u0003\u0002\u0002\u0002ȟȣ\u0003\u0002\u0002\u0002Ƞȡ\u0005\u0088E\u0002ȡȢ\u0007\u0004\u0002\u0002ȢȤ\u0003\u0002\u0002\u0002ȣȠ\u0003\u0002\u0002\u0002ȣȤ\u0003\u0002\u0002\u0002Ȥȥ\u0003\u0002\u0002\u0002ȥȦ\u0005\u008cG\u0002Ȧ'\u0003\u0002\u0002\u0002ȧȨ\u0007C\u0002\u0002Ȩȫ\u0007\u008c\u0002\u0002ȩȪ\u0007T\u0002\u0002ȪȬ\u0007J\u0002\u0002ȫȩ\u0003\u0002\u0002\u0002ȫȬ\u0003\u0002\u0002\u0002ȬȰ\u0003\u0002\u0002\u0002ȭȮ\u0005\u0088E\u0002Ȯȯ\u0007\u0004\u0002\u0002ȯȱ\u0003\u0002\u0002\u0002Ȱȭ\u0003\u0002\u0002\u0002Ȱȱ\u0003\u0002\u0002\u0002ȱȲ\u0003\u0002\u0002\u0002Ȳȳ\u0005\u009aN\u0002ȳ)\u0003\u0002\u0002\u0002ȴȵ\u0007C\u0002\u0002ȵȸ\u0007\u0093\u0002\u0002ȶȷ\u0007T\u0002\u0002ȷȹ\u0007J\u0002\u0002ȸȶ\u0003\u0002\u0002\u0002ȸȹ\u0003\u0002\u0002\u0002ȹȽ\u0003\u0002\u0002\u0002ȺȻ\u0005\u0088E\u0002Ȼȼ\u0007\u0004\u0002\u0002ȼȾ\u0003\u0002\u0002\u0002ȽȺ\u0003\u0002\u0002\u0002ȽȾ\u0003\u0002\u0002\u0002Ⱦȿ\u0003\u0002\u0002\u0002ȿɀ\u0005\u009cO\u0002ɀ+\u0003\u0002\u0002\u0002ɁɃ\u0005V,\u0002ɂɁ\u0003\u0002\u0002\u0002ɂɃ\u0003\u0002\u0002\u0002Ƀɕ\u0003\u0002\u0002\u0002Ʉɖ\u0007\\\u0002\u0002Ʌɖ\u0007~\u0002\u0002Ɇɇ\u0007\\\u0002\u0002ɇɈ\u0007p\u0002\u0002Ɉɖ\u0007~\u0002\u0002ɉɊ\u0007\\\u0002\u0002Ɋɋ\u0007p\u0002\u0002ɋɖ\u0007\u0081\u0002\u0002Ɍɍ\u0007\\\u0002\u0002ɍɎ\u0007p\u0002\u0002Ɏɖ\u0007\u001d\u0002\u0002ɏɐ\u0007\\\u0002\u0002ɐɑ\u0007p\u0002\u0002ɑɖ\u0007L\u0002\u0002ɒɓ\u0007\\\u0002\u0002ɓɔ\u0007p\u0002\u0002ɔɖ\u0007U\u0002\u0002ɕɄ\u0003\u0002\u0002\u0002ɕɅ\u0003\u0002\u0002\u0002ɕɆ\u0003\u0002\u0002\u0002ɕɉ\u0003\u0002\u0002\u0002ɕɌ\u0003\u0002\u0002\u0002ɕɏ\u0003\u0002\u0002\u0002ɕɒ\u0003\u0002\u0002\u0002ɖɗ\u0003\u0002\u0002\u0002ɗɛ\u0007_\u0002\u0002ɘə\u0005\u0088E\u0002əɚ\u0007\u0004\u0002\u0002ɚɜ\u0003\u0002\u0002\u0002ɛɘ\u0003\u0002\u0002\u0002ɛɜ\u0003\u0002\u0002\u0002ɜɝ\u0003\u0002\u0002\u0002ɝɠ\u0005\u008cG\u0002ɞɟ\u0007%\u0002\u0002ɟɡ\u0005¤S\u0002ɠɞ\u0003\u0002\u0002\u0002ɠɡ\u0003\u0002\u0002\u0002ɡɭ\u0003\u0002\u0002\u0002ɢɣ\u0007\u0005\u0002\u0002ɣɨ\u0005\u0092J\u0002ɤɥ\u0007\u0007\u0002\u0002ɥɧ\u0005\u0092J\u0002ɦɤ\u0003\u0002\u0002\u0002ɧɪ\u0003\u0002\u0002\u0002ɨɦ\u0003\u0002\u0002\u0002ɨɩ\u0003\u0002\u0002\u0002ɩɫ\u0003\u0002\u0002\u0002ɪɨ\u0003\u0002\u0002\u0002ɫɬ\u0007\u0006\u0002\u0002ɬɮ\u0003\u0002\u0002\u0002ɭɢ\u0003\u0002\u0002\u0002ɭɮ\u0003\u0002\u0002\u0002ɮʎ\u0003\u0002\u0002\u0002ɯɰ\u0007\u0092\u0002\u0002ɰɱ\u0007\u0005\u0002\u0002ɱɶ\u0005L'\u0002ɲɳ\u0007\u0007\u0002\u0002ɳɵ\u0005L'\u0002ɴɲ\u0003\u0002\u0002\u0002ɵɸ\u0003\u0002\u0002\u0002ɶɴ\u0003\u0002\u0002\u0002ɶɷ\u0003\u0002\u0002\u0002ɷɹ\u0003\u0002\u0002\u0002ɸɶ\u0003\u0002\u0002\u0002ɹʈ\u0007\u0006\u0002\u0002ɺɻ\u0007\u0007\u0002\u0002ɻɼ\u0007\u0005\u0002\u0002ɼʁ\u0005L'\u0002ɽɾ\u0007\u0007\u0002\u0002ɾʀ\u0005L'\u0002ɿɽ\u0003\u0002\u0002\u0002ʀʃ\u0003\u0002\u0002\u0002ʁɿ\u0003\u0002\u0002\u0002ʁʂ\u0003\u0002\u0002\u0002ʂʄ\u0003\u0002\u0002\u0002ʃʁ\u0003\u0002\u0002\u0002ʄʅ\u0007\u0006\u0002\u0002ʅʇ\u0003\u0002\u0002\u0002ʆɺ\u0003\u0002\u0002\u0002ʇʊ\u0003\u0002\u0002\u0002ʈʆ\u0003\u0002\u0002\u0002ʈʉ\u0003\u0002\u0002\u0002ʉʏ\u0003\u0002\u0002\u0002ʊʈ\u0003\u0002\u0002\u0002ʋʏ\u0005:\u001e\u0002ʌʍ\u0007<\u0002\u0002ʍʏ\u0007\u0092\u0002\u0002ʎɯ\u0003\u0002\u0002\u0002ʎʋ\u0003\u0002\u0002\u0002ʎʌ\u0003\u0002\u0002\u0002ʏʑ\u0003\u0002\u0002\u0002ʐʒ\u0005.\u0018\u0002ʑʐ\u0003\u0002\u0002\u0002ʑʒ\u0003\u0002\u0002\u0002ʒ-\u0003\u0002\u0002\u0002ʓʔ\u0007o\u0002\u0002ʔʣ\u00074\u0002\u0002ʕʖ\u0007\u0005\u0002\u0002ʖʛ\u0005R*\u0002ʗʘ\u0007\u0007\u0002\u0002ʘʚ\u0005R*\u0002ʙʗ\u0003\u0002\u0002\u0002ʚʝ\u0003\u0002\u0002\u0002ʛʙ\u0003\u0002\u0002\u0002ʛʜ\u0003\u0002\u0002\u0002ʜʞ\u0003\u0002\u0002\u0002ʝʛ\u0003\u0002\u0002\u0002ʞʡ\u0007\u0006\u0002\u0002ʟʠ\u0007\u0096\u0002\u0002ʠʢ\u0005L'\u0002ʡʟ\u0003\u0002\u0002\u0002ʡʢ\u0003\u0002\u0002\u0002ʢʤ\u0003\u0002\u0002\u0002ʣʕ\u0003\u0002\u0002\u0002ʣʤ\u0003\u0002\u0002\u0002ʤʿ\u0003\u0002\u0002\u0002ʥˀ\u0007\u009a\u0002\u0002ʦʧ\u0007\u009b\u0002\u0002ʧʪ\u0007\u0085\u0002\u0002ʨʫ\u0005\u0092J\u0002ʩʫ\u0005\u0080A\u0002ʪʨ\u0003\u0002\u0002\u0002ʪʩ\u0003\u0002\u0002\u0002ʫʬ\u0003\u0002\u0002\u0002ʬʭ\u0007\b\u0002\u0002ʭʸ\u0005L'\u0002ʮʱ\u0007\u0007\u0002\u0002ʯʲ\u0005\u0092J\u0002ʰʲ\u0005\u0080A\u0002ʱʯ\u0003\u0002\u0002\u0002ʱʰ\u0003\u0002\u0002\u0002ʲʳ\u0003\u0002\u0002\u0002ʳʴ\u0007\b\u0002\u0002ʴʵ\u0005L'\u0002ʵʷ\u0003\u0002\u0002\u0002ʶʮ\u0003\u0002\u0002\u0002ʷʺ\u0003\u0002\u0002\u0002ʸʶ\u0003\u0002\u0002\u0002ʸʹ\u0003\u0002\u0002\u0002ʹʽ\u0003\u0002\u0002\u0002ʺʸ\u0003\u0002\u0002\u0002ʻʼ\u0007\u0096\u0002\u0002ʼʾ\u0005L'\u0002ʽʻ\u0003\u0002\u0002\u0002ʽʾ\u0003\u0002\u0002\u0002ʾˀ\u0003\u0002\u0002\u0002ʿʥ\u0003\u0002\u0002\u0002ʿʦ\u0003\u0002\u0002\u0002ˀ/\u0003\u0002\u0002\u0002ˁ˅\u0007t\u0002\u0002˂˃\u0005\u0088E\u0002˃˄\u0007\u0004\u0002\u0002˄ˆ\u0003\u0002\u0002\u0002˅˂\u0003\u0002\u0002\u0002˅ˆ\u0003\u0002\u0002\u0002ˆˇ\u0003\u0002\u0002\u0002ˇˎ\u0005 Q\u0002ˈˉ\u0007\b\u0002\u0002ˉˏ\u0005b2\u0002ˊˋ\u0007\u0005\u0002\u0002ˋˌ\u0005b2\u0002ˌˍ\u0007\u0006\u0002\u0002ˍˏ\u0003\u0002\u0002\u0002ˎˈ\u0003\u0002\u0002\u0002ˎˊ\u0003\u0002\u0002\u0002ˎˏ\u0003\u0002\u0002\u0002ˏ1\u0003\u0002\u0002\u0002ː˛\u0007{\u0002\u0002ˑ˜\u0005\u0094K\u0002˒˓\u0005\u0088E\u0002˓˔\u0007\u0004\u0002\u0002˔˖\u0003\u0002\u0002\u0002˕˒\u0003\u0002\u0002\u0002˕˖\u0003\u0002\u0002\u0002˖˙\u0003\u0002\u0002\u0002˗˚\u0005\u008cG\u0002˘˚\u0005\u0098M\u0002˙˗\u0003\u0002\u0002\u0002˙˘\u0003\u0002\u0002\u0002˚˜\u0003\u0002\u0002\u0002˛ˑ\u0003\u0002\u0002\u0002˛˕\u0003\u0002\u0002\u0002˛˜\u0003\u0002\u0002\u0002˜3\u0003\u0002\u0002\u0002˝˟\u0007|\u0002\u0002˞ˠ\u0007\u0083\u0002\u0002˟˞\u0003\u0002\u0002\u0002˟ˠ\u0003\u0002\u0002\u0002ˠˡ\u0003\u0002\u0002\u0002ˡˢ\u0005¢R\u0002ˢ5\u0003\u0002\u0002\u0002ˣ˨\u0007\u0081\u0002\u0002ˤ˦\u0007\u008b\u0002\u0002˥˧\u0005¦T\u0002˦˥\u0003\u0002\u0002\u0002˦˧\u0003\u0002\u0002\u0002˧˩\u0003\u0002\u0002\u0002˨ˤ\u0003\u0002\u0002\u0002˨˩\u0003\u0002\u0002\u0002˩˯\u0003\u0002\u0002\u0002˪ˬ\u0007\u008a\u0002\u0002˫˭\u0007\u0083\u0002\u0002ˬ˫\u0003\u0002\u0002\u0002ˬ˭\u0003\u0002\u0002\u0002˭ˮ\u0003\u0002\u0002\u0002ˮ˰\u0005¢R\u0002˯˪\u0003\u0002\u0002\u0002˯˰\u0003\u0002\u0002\u0002˰7\u0003\u0002\u0002\u0002˱˲\u0007\u0083\u0002\u0002˲˳\u0005¢R\u0002˳9\u0003\u0002\u0002\u0002˴˶\u0005V,\u0002˵˴\u0003\u0002\u0002\u0002˵˶\u0003\u0002\u0002\u0002˶˷\u0003\u0002\u0002\u0002˷˽\u0005<\u001f\u0002˸˹\u0005n8\u0002˹˺\u0005<\u001f\u0002˺˼\u0003\u0002\u0002\u0002˻˸\u0003\u0002\u0002\u0002˼˿\u0003\u0002\u0002\u0002˽˻\u0003\u0002\u0002\u0002˽˾\u0003\u0002\u0002\u0002˾́\u0003\u0002\u0002\u0002˿˽\u0003\u0002\u0002\u0002̀̂\u0005\\/\u0002́̀\u0003\u0002\u0002\u0002́̂\u0003\u0002\u0002\u0002̂̄\u0003\u0002\u0002\u0002̃̅\u0005`1\u0002̄̃\u0003\u0002\u0002\u0002̄̅\u0003\u0002\u0002\u0002̅;\u0003\u0002\u0002\u0002̆̈\u0007\u0084\u0002\u0002̇̉\t\u0005\u0002\u0002̈̇\u0003\u0002\u0002\u0002̈̉\u0003\u0002\u0002\u0002̉̊\u0003\u0002\u0002\u0002̊̏\u0005d3\u0002̋̌\u0007\u0007\u0002\u0002̌̎\u0005d3\u0002̍̋\u0003\u0002\u0002\u0002̎̑\u0003\u0002\u0002\u0002̏̍\u0003\u0002\u0002\u0002̏̐\u0003\u0002\u0002\u0002̞̐\u0003\u0002\u0002\u0002̑̏\u0003\u0002\u0002\u0002̜̒\u0007O\u0002\u0002̘̓\u0005f4\u0002̔̕\u0007\u0007\u0002\u0002̗̕\u0005f4\u0002̖̔\u0003\u0002\u0002\u0002̗̚\u0003\u0002\u0002\u0002̘̖\u0003\u0002\u0002\u0002̘̙\u0003\u0002\u0002\u0002̙̝\u0003\u0002\u0002\u0002̘̚\u0003\u0002\u0002\u0002̛̝\u0005h5\u0002̜̓\u0003\u0002\u0002\u0002̛̜\u0003\u0002\u0002\u0002̝̟\u0003\u0002\u0002\u0002̞̒\u0003\u0002\u0002\u0002̞̟\u0003\u0002\u0002\u0002̢̟\u0003\u0002\u0002\u0002̡̠\u0007\u0096\u0002\u0002̡̣\u0005L'\u0002̢̠\u0003\u0002\u0002\u0002̢̣\u0003\u0002\u0002\u0002̣̲\u0003\u0002\u0002\u0002̤̥\u0007R\u0002\u0002̥̦\u0007,\u0002\u0002̦̫\u0005L'\u0002̧̨\u0007\u0007\u0002\u0002̨̪\u0005L'\u0002̧̩\u0003\u0002\u0002\u0002̪̭\u0003\u0002\u0002\u0002̫̩\u0003\u0002\u0002\u0002̫̬\u0003\u0002\u0002\u0002̬̰\u0003\u0002\u0002\u0002̭̫\u0003\u0002\u0002\u0002̮̯\u0007S\u0002\u0002̯̱\u0005L'\u0002̰̮\u0003\u0002\u0002\u0002̰̱\u0003\u0002\u0002\u0002̱̳\u0003\u0002\u0002\u0002̲̤\u0003\u0002\u0002\u0002̲̳\u0003\u0002\u0002\u0002̳͑\u0003\u0002\u0002\u0002̴̵\u0007\u0092\u0002\u0002̵̶\u0007\u0005\u0002\u0002̶̻\u0005L'\u0002̷̸\u0007\u0007\u0002\u0002̸̺\u0005L'\u0002̷̹\u0003\u0002\u0002\u0002̺̽\u0003\u0002\u0002\u0002̻̹\u0003\u0002\u0002\u0002̻̼\u0003\u0002\u0002\u0002̼̾\u0003\u0002\u0002\u0002̻̽\u0003\u0002\u0002\u0002͍̾\u0007\u0006\u0002\u0002̿̀\u0007\u0007\u0002\u0002̀́\u0007\u0005\u0002\u0002́͆\u0005L'\u0002͂̓\u0007\u0007\u0002\u0002̓ͅ\u0005L'\u0002̈́͂\u0003\u0002\u0002\u0002͈ͅ\u0003\u0002\u0002\u0002͆̈́\u0003\u0002\u0002\u0002͇͆\u0003\u0002\u0002\u0002͇͉\u0003\u0002\u0002\u0002͈͆\u0003\u0002\u0002\u0002͉͊\u0007\u0006\u0002\u0002͊͌\u0003\u0002\u0002\u0002͋̿\u0003\u0002\u0002\u0002͌͏\u0003\u0002\u0002\u0002͍͋\u0003\u0002\u0002\u0002͍͎\u0003\u0002\u0002\u0002͎͑\u0003\u0002\u0002\u0002͏͍\u0003\u0002\u0002\u0002͐̆\u0003\u0002\u0002\u0002̴͐\u0003\u0002\u0002\u0002͑=\u0003\u0002\u0002\u0002͔͒\u0005V,\u0002͓͒\u0003\u0002\u0002\u0002͓͔\u0003\u0002\u0002\u0002͔͕\u0003\u0002\u0002\u0002͕͠\u0007\u008f\u0002\u0002͖͗\u0007p\u0002\u0002͗͡\u0007\u0081\u0002\u0002͙͘\u0007p\u0002\u0002͙͡\u0007\u001d\u0002\u0002͚͛\u0007p\u0002\u0002͛͡\u0007~\u0002\u0002͜͝\u0007p\u0002\u0002͝͡\u0007L\u0002\u0002͟͞\u0007p\u0002\u0002͟͡\u0007U\u0002\u0002͖͠\u0003\u0002\u0002\u0002͘͠\u0003\u0002\u0002\u0002͚͠\u0003\u0002\u0002\u0002͜͠\u0003\u0002\u0002\u0002͠͞\u0003\u0002\u0002\u0002͠͡\u0003\u0002\u0002\u0002͢͡\u0003\u0002\u0002\u0002ͣ͢\u0005Z.\u0002ͣͦ\u0007\u0085\u0002\u0002ͤͧ\u0005\u0092J\u0002ͥͧ\u0005\u0080A\u0002ͦͤ\u0003\u0002\u0002\u0002ͦͥ\u0003\u0002\u0002\u0002ͧͨ\u0003\u0002\u0002\u0002ͨͩ\u0007\b\u0002\u0002ͩʹ\u0005L'\u0002ͪͭ\u0007\u0007\u0002\u0002ͫͮ\u0005\u0092J\u0002ͬͮ\u0005\u0080A\u0002ͭͫ\u0003\u0002\u0002\u0002ͭͬ\u0003\u0002\u0002\u0002ͮͯ\u0003\u0002\u0002\u0002ͯͰ\u0007\b\u0002\u0002Ͱͱ\u0005L'\u0002ͱͳ\u0003\u0002\u0002\u0002Ͳͪ\u0003\u0002\u0002\u0002ͳͶ\u0003\u0002\u0002\u0002ʹͲ\u0003\u0002\u0002\u0002ʹ͵\u0003\u0002\u0002\u0002͵\u0379\u0003\u0002\u0002\u0002Ͷʹ\u0003\u0002\u0002\u0002ͷ\u0378\u0007\u0096\u0002\u0002\u0378ͺ\u0005L'\u0002\u0379ͷ\u0003\u0002\u0002\u0002\u0379ͺ\u0003\u0002\u0002\u0002ͺ?\u0003\u0002\u0002\u0002ͻͽ\u0005V,\u0002ͼͻ\u0003\u0002\u0002\u0002ͼͽ\u0003\u0002\u0002\u0002ͽ;\u0003\u0002\u0002\u0002;Ή\u0007\u008f\u0002\u0002Ϳ\u0380\u0007p\u0002\u0002\u0380Ί\u0007\u0081\u0002\u0002\u0381\u0382\u0007p\u0002\u0002\u0382Ί\u0007\u001d\u0002\u0002\u0383΄\u0007p\u0002\u0002΄Ί\u0007~\u0002\u0002΅Ά\u0007p\u0002\u0002ΆΊ\u0007L\u0002\u0002·Έ\u0007p\u0002\u0002ΈΊ\u0007U\u0002\u0002ΉͿ\u0003\u0002\u0002\u0002Ή\u0381\u0003\u0002\u0002\u0002Ή\u0383\u0003\u0002\u0002\u0002Ή΅\u0003\u0002\u0002\u0002Ή·\u0003\u0002\u0002\u0002ΉΊ\u0003\u0002\u0002\u0002Ί\u038b\u0003\u0002\u0002\u0002\u038bΌ\u0005Z.\u0002ΌΏ\u0007\u0085\u0002\u0002\u038dΐ\u0005\u0092J\u0002Ύΐ\u0005\u0080A\u0002Ώ\u038d\u0003\u0002\u0002\u0002ΏΎ\u0003\u0002\u0002\u0002ΐΑ\u0003\u0002\u0002\u0002ΑΒ\u0007\b\u0002\u0002ΒΝ\u0005L'\u0002ΓΖ\u0007\u0007\u0002\u0002ΔΗ\u0005\u0092J\u0002ΕΗ\u0005\u0080A\u0002ΖΔ\u0003\u0002\u0002\u0002ΖΕ\u0003\u0002\u0002\u0002ΗΘ\u0003\u0002\u0002\u0002ΘΙ\u0007\b\u0002\u0002ΙΚ\u0005L'\u0002ΚΜ\u0003\u0002\u0002\u0002ΛΓ\u0003\u0002\u0002\u0002ΜΟ\u0003\u0002\u0002\u0002ΝΛ\u0003\u0002\u0002\u0002ΝΞ\u0003\u0002\u0002\u0002Ξ\u03a2\u0003\u0002\u0002\u0002ΟΝ\u0003\u0002\u0002\u0002ΠΡ\u0007\u0096\u0002\u0002ΡΣ\u0005L'\u0002\u03a2Π\u0003\u0002\u0002\u0002\u03a2Σ\u0003\u0002\u0002\u0002ΣΨ\u0003\u0002\u0002\u0002ΤΦ\u0005\\/\u0002ΥΤ\u0003\u0002\u0002\u0002ΥΦ\u0003\u0002\u0002\u0002ΦΧ\u0003\u0002\u0002\u0002ΧΩ\u0005`1\u0002ΨΥ\u0003\u0002\u0002\u0002ΨΩ\u0003\u0002\u0002\u0002ΩA\u0003\u0002\u0002\u0002Ϊά\u0007\u0091\u0002\u0002Ϋέ\u0005\u0088E\u0002άΫ\u0003\u0002\u0002\u0002άέ\u0003\u0002\u0002\u0002έC\u0003\u0002\u0002\u0002ήΰ\u0005\u0092J\u0002ία\u0005F$\u0002ΰί\u0003\u0002\u0002\u0002ΰα\u0003\u0002\u0002\u0002αε\u0003\u0002\u0002\u0002βδ\u0005H%\u0002γβ\u0003\u0002\u0002\u0002δη\u0003\u0002\u0002\u0002εγ\u0003\u0002\u0002\u0002εζ\u0003\u0002\u0002\u0002ζE\u0003\u0002\u0002\u0002ηε\u0003\u0002\u0002\u0002θκ\u0005\u0084C\u0002ιθ\u0003\u0002\u0002\u0002κλ\u0003\u0002\u0002\u0002λμ\u0003\u0002\u0002\u0002λι\u0003\u0002\u0002\u0002μχ\u0003\u0002\u0002\u0002νξ\u0007\u0005\u0002\u0002ξο\u0005p9\u0002οπ\u0007\u0006\u0002\u0002πψ\u0003\u0002\u0002\u0002ρς\u0007\u0005\u0002\u0002ςσ\u0005p9\u0002στ\u0007\u0007\u0002\u0002τυ\u0005p9\u0002υφ\u0007\u0006\u0002\u0002φψ\u0003\u0002\u0002\u0002χν\u0003\u0002\u0002\u0002χρ\u0003\u0002\u0002\u0002χψ\u0003\u0002\u0002\u0002ψG\u0003\u0002\u0002\u0002ωϊ\u00075\u0002\u0002ϊό\u0005\u0084C\u0002ϋω\u0003\u0002\u0002\u0002ϋό\u0003\u0002\u0002\u0002όϮ\u0003\u0002\u0002\u0002ύώ\u0007u\u0002\u0002ώϐ\u0007c\u0002\u0002Ϗϑ\t\u0006\u0002\u0002ϐϏ\u0003\u0002\u0002\u0002ϐϑ\u0003\u0002\u0002\u0002ϑϒ\u0003\u0002\u0002\u0002ϒϔ\u0005J&\u0002ϓϕ\u0007(\u0002\u0002ϔϓ\u0003\u0002\u0002\u0002ϔϕ\u0003\u0002\u0002\u0002ϕϯ\u0003\u0002\u0002\u0002ϖϘ\u0007j\u0002\u0002ϗϖ\u0003\u0002\u0002\u0002ϗϘ\u0003\u0002\u0002\u0002Ϙϙ\u0003\u0002\u0002\u0002ϙϚ\u0007l\u0002\u0002Ϛϯ\u0005J&\u0002ϛϜ\u0007\u008e\u0002\u0002Ϝϯ\u0005J&\u0002ϝϞ\u00070\u0002\u0002Ϟϟ\u0007\u0005\u0002\u0002ϟϠ\u0005L'\u0002Ϡϡ\u0007\u0006\u0002\u0002ϡϯ\u0003\u0002\u0002\u0002Ϣϩ\u0007<\u0002\u0002ϣϪ\u0005p9\u0002ϤϪ\u0005r:\u0002ϥϦ\u0007\u0005\u0002\u0002Ϧϧ\u0005L'\u0002ϧϨ\u0007\u0006\u0002\u0002ϨϪ\u0003\u0002\u0002\u0002ϩϣ\u0003\u0002\u0002\u0002ϩϤ\u0003\u0002\u0002\u0002ϩϥ\u0003\u0002\u0002\u0002Ϫϯ\u0003\u0002\u0002\u0002ϫϬ\u00071\u0002\u0002Ϭϯ\u0005\u0094K\u0002ϭϯ\u0005N(\u0002Ϯύ\u0003\u0002\u0002\u0002Ϯϗ\u0003\u0002\u0002\u0002Ϯϛ\u0003\u0002\u0002\u0002Ϯϝ\u0003\u0002\u0002\u0002ϮϢ\u0003\u0002\u0002\u0002Ϯϫ\u0003\u0002\u0002\u0002Ϯϭ\u0003\u0002\u0002\u0002ϯI\u0003\u0002\u0002\u0002ϰϱ\u0007o\u0002\u0002ϱϲ\u00074\u0002\u0002ϲϴ\t\u0007\u0002\u0002ϳϰ\u0003\u0002\u0002\u0002ϳϴ\u0003\u0002\u0002\u0002ϴK\u0003\u0002\u0002\u0002ϵ϶\b'\u0001\u0002϶щ\u0005r:\u0002Ϸщ\u0007\u009e\u0002\u0002ϸϹ\u0005\u0088E\u0002ϹϺ\u0007\u0004\u0002\u0002Ϻϼ\u0003\u0002\u0002\u0002ϻϸ\u0003\u0002\u0002\u0002ϻϼ\u0003\u0002\u0002\u0002ϼϽ\u0003\u0002\u0002\u0002ϽϾ\u0005\u008cG\u0002ϾϿ\u0007\u0004\u0002\u0002ϿЁ\u0003\u0002\u0002\u0002Ѐϻ\u0003\u0002\u0002\u0002ЀЁ\u0003\u0002\u0002\u0002ЁЂ\u0003\u0002\u0002\u0002Ђщ\u0005\u0092J\u0002ЃЄ\u0005v<\u0002ЄЅ\u0005L'\u0010Ѕщ\u0003\u0002\u0002\u0002ІЇ\u0005\u0086D\u0002ЇД\u0007\u0005\u0002\u0002ЈЊ\u0007B\u0002\u0002ЉЈ\u0003\u0002\u0002\u0002ЉЊ\u0003\u0002\u0002\u0002ЊЋ\u0003\u0002\u0002\u0002ЋА\u0005L'\u0002ЌЍ\u0007\u0007\u0002\u0002ЍЏ\u0005L'\u0002ЎЌ\u0003\u0002\u0002\u0002ЏВ\u0003\u0002\u0002\u0002АЎ\u0003\u0002\u0002\u0002АБ\u0003\u0002\u0002\u0002БЕ\u0003\u0002\u0002\u0002ВА\u0003\u0002\u0002\u0002ГЕ\u0007\t\u0002\u0002ДЉ\u0003\u0002\u0002\u0002ДГ\u0003\u0002\u0002\u0002ДЕ\u0003\u0002\u0002\u0002ЕЖ\u0003\u0002\u0002\u0002ЖЗ\u0007\u0006\u0002\u0002Зщ\u0003\u0002\u0002\u0002ИЙ\u0007\u0005\u0002\u0002ЙО\u0005L'\u0002КЛ\u0007\u0007\u0002\u0002ЛН\u0005L'\u0002МК\u0003\u0002\u0002\u0002НР\u0003\u0002\u0002\u0002ОМ\u0003\u0002\u0002\u0002ОП\u0003\u0002\u0002\u0002ПС\u0003\u0002\u0002\u0002РО\u0003\u0002\u0002\u0002СТ\u0007\u0006\u0002\u0002Тщ\u0003\u0002\u0002\u0002УФ\u0007/\u0002\u0002ФХ\u0007\u0005\u0002\u0002ХЦ\u0005L'\u0002ЦЧ\u0007%\u0002\u0002ЧШ\u0005F$\u0002ШЩ\u0007\u0006\u0002\u0002Щщ\u0003\u0002\u0002\u0002ЪЬ\u0007j\u0002\u0002ЫЪ\u0003\u0002\u0002\u0002ЫЬ\u0003\u0002\u0002\u0002ЬЭ\u0003\u0002\u0002\u0002ЭЯ\u0007J\u0002\u0002ЮЫ\u0003\u0002\u0002\u0002ЮЯ\u0003\u0002\u0002\u0002Яа\u0003\u0002\u0002\u0002аб\u0007\u0005\u0002\u0002бв\u0005:\u001e\u0002вг\u0007\u0006\u0002\u0002гщ\u0003\u0002\u0002\u0002дж\u0007.\u0002\u0002ез\u0005L'\u0002же\u0003\u0002\u0002\u0002жз\u0003\u0002\u0002\u0002зн\u0003\u0002\u0002\u0002ий\u0007\u0095\u0002\u0002йк\u0005L'\u0002кл\u0007\u0089\u0002\u0002лм\u0005L'\u0002мо\u0003\u0002\u0002\u0002ни\u0003\u0002\u0002\u0002оп\u0003\u0002\u0002\u0002пн\u0003\u0002\u0002\u0002пр\u0003\u0002\u0002\u0002ру\u0003\u0002\u0002\u0002ст\u0007E\u0002\u0002тф\u0005L'\u0002ус\u0003\u0002\u0002\u0002уф\u0003\u0002\u0002\u0002фх\u0003\u0002\u0002\u0002хц\u0007F\u0002\u0002цщ\u0003\u0002\u0002\u0002чщ\u0005P)\u0002шϵ\u0003\u0002\u0002\u0002шϷ\u0003\u0002\u0002\u0002шЀ\u0003\u0002\u0002\u0002шЃ\u0003\u0002\u0002\u0002шІ\u0003\u0002\u0002\u0002шИ\u0003\u0002\u0002\u0002шУ\u0003\u0002\u0002\u0002шЮ\u0003\u0002\u0002\u0002шд\u0003\u0002\u0002\u0002шч\u0003\u0002\u0002\u0002щҠ\u0003\u0002\u0002\u0002ъы\f\u000f\u0002\u0002ыь\u0005x=\u0002ьэ\u0005L'\u0010эҟ\u0003\u0002\u0002\u0002юя\f\b\u0002\u0002яё\u0007`\u0002\u0002ѐђ\u0007j\u0002\u0002ёѐ\u0003\u0002\u0002\u0002ёђ\u0003\u0002\u0002\u0002ђѓ\u0003\u0002\u0002\u0002ѓҟ\u0005L'\tєі\f\u0007\u0002\u0002ѕї\u0007j\u0002\u0002іѕ\u0003\u0002\u0002\u0002ії\u0003\u0002\u0002\u0002їј\u0003\u0002\u0002\u0002јљ\u0007+\u0002\u0002љњ\u0005L'\u0002њћ\u0007$\u0002\u0002ћќ\u0005L'\bќҟ\u0003\u0002\u0002\u0002ѝў\f\u000b\u0002\u0002ўџ\u00071\u0002\u0002џҟ\u0005\u0094K\u0002ѠѢ\f\n\u0002\u0002ѡѣ\u0007j\u0002\u0002Ѣѡ\u0003\u0002\u0002\u0002Ѣѣ\u0003\u0002\u0002\u0002ѣѤ\u0003\u0002\u0002\u0002Ѥѥ\t\b\u0002\u0002ѥѨ\u0005L'\u0002Ѧѧ\u0007G\u0002\u0002ѧѩ\u0005L'\u0002ѨѦ\u0003\u0002\u0002\u0002Ѩѩ\u0003\u0002\u0002\u0002ѩҟ\u0003\u0002\u0002\u0002Ѫѯ\f\t\u0002\u0002ѫѰ\u0007a\u0002\u0002ѬѰ\u0007k\u0002\u0002ѭѮ\u0007j\u0002\u0002ѮѰ\u0007l\u0002\u0002ѯѫ\u0003\u0002\u0002\u0002ѯѬ\u0003\u0002\u0002\u0002ѯѭ\u0003\u0002\u0002\u0002Ѱҟ\u0003\u0002\u0002\u0002ѱѳ\f\u0006\u0002\u0002ѲѴ\u0007j\u0002\u0002ѳѲ\u0003\u0002\u0002\u0002ѳѴ\u0003\u0002\u0002\u0002Ѵѵ\u0003\u0002\u0002\u0002ѵҜ\u0007W\u0002\u0002ѶҀ\u0007\u0005\u0002\u0002ѷҁ\u0005:\u001e\u0002Ѹѽ\u0005L'\u0002ѹѺ\u0007\u0007\u0002\u0002ѺѼ\u0005L'\u0002ѻѹ\u0003\u0002\u0002\u0002Ѽѿ\u0003\u0002\u0002\u0002ѽѻ\u0003\u0002\u0002\u0002ѽѾ\u0003\u0002\u0002\u0002Ѿҁ\u0003\u0002\u0002\u0002ѿѽ\u0003\u0002\u0002\u0002Ҁѷ\u0003\u0002\u0002\u0002ҀѸ\u0003\u0002\u0002\u0002Ҁҁ\u0003\u0002\u0002\u0002ҁ҂\u0003\u0002\u0002\u0002҂ҝ\u0007\u0006\u0002\u0002҃҄\u0005\u0088E\u0002҄҅\u0007\u0004\u0002\u0002҅҇\u0003\u0002\u0002\u0002҆҃\u0003\u0002\u0002\u0002҆҇\u0003\u0002\u0002\u0002҇҈\u0003\u0002\u0002\u0002҈ҝ\u0005\u008cG\u0002҉Ҋ\u0005\u0088E\u0002Ҋҋ\u0007\u0004\u0002\u0002ҋҍ\u0003\u0002\u0002\u0002Ҍ҉\u0003\u0002\u0002\u0002Ҍҍ\u0003\u0002\u0002\u0002ҍҎ\u0003\u0002\u0002\u0002Ҏҏ\u0005\u008aF\u0002ҏҘ\u0007\u0005\u0002\u0002Ґҕ\u0005L'\u0002ґҒ\u0007\u0007\u0002\u0002ҒҔ\u0005L'\u0002ғґ\u0003\u0002\u0002\u0002Ҕҗ\u0003\u0002\u0002\u0002ҕғ\u0003\u0002\u0002\u0002ҕҖ\u0003\u0002\u0002\u0002Җҙ\u0003\u0002\u0002\u0002җҕ\u0003\u0002\u0002\u0002ҘҐ\u0003\u0002\u0002\u0002Ҙҙ\u0003\u0002\u0002\u0002ҙҚ\u0003\u0002\u0002\u0002Ққ\u0007\u0006\u0002\u0002қҝ\u0003\u0002\u0002\u0002ҜѶ\u0003\u0002\u0002\u0002Ҝ҆\u0003\u0002\u0002\u0002ҜҌ\u0003\u0002\u0002\u0002ҝҟ\u0003\u0002\u0002\u0002Ҟъ\u0003\u0002\u0002\u0002Ҟю\u0003\u0002\u0002\u0002Ҟє\u0003\u0002\u0002\u0002Ҟѝ\u0003\u0002\u0002\u0002ҞѠ\u0003\u0002\u0002\u0002ҞѪ\u0003\u0002\u0002\u0002Ҟѱ\u0003\u0002\u0002\u0002ҟҢ\u0003\u0002\u0002\u0002ҠҞ\u0003\u0002\u0002\u0002Ҡҡ\u0003\u0002\u0002\u0002ҡM\u0003\u0002\u0002\u0002ҢҠ\u0003\u0002\u0002\u0002ңҤ\u0007y\u0002\u0002ҤҰ\u0005\u0096L\u0002ҥҦ\u0007\u0005\u0002\u0002Ҧҫ\u0005\u0092J\u0002ҧҨ\u0007\u0007\u0002\u0002ҨҪ\u0005\u0092J\u0002ҩҧ\u0003\u0002\u0002\u0002Ҫҭ\u0003\u0002\u0002\u0002ҫҩ\u0003\u0002\u0002\u0002ҫҬ\u0003\u0002\u0002\u0002ҬҮ\u0003\u0002\u0002\u0002ҭҫ\u0003\u0002\u0002\u0002Үү\u0007\u0006\u0002\u0002үұ\u0003\u0002\u0002\u0002Ұҥ\u0003\u0002\u0002\u0002Ұұ\u0003\u0002\u0002\u0002ұӄ\u0003\u0002\u0002\u0002Ҳҳ\u0007o\u0002\u0002ҳҼ\t\t\u0002\u0002Ҵҵ\u0007\u0085\u0002\u0002ҵҽ\u0007l\u0002\u0002Ҷҷ\u0007\u0085\u0002\u0002ҷҽ\u0007<\u0002\u0002Ҹҽ\u0007-\u0002\u0002ҹҽ\u0007\u007f\u0002\u0002Һһ\u0007i\u0002\u0002һҽ\u0007\u001e\u0002\u0002ҼҴ\u0003\u0002\u0002\u0002ҼҶ\u0003\u0002\u0002\u0002ҼҸ\u0003\u0002\u0002\u0002Ҽҹ\u0003\u0002\u0002\u0002ҼҺ\u0003\u0002\u0002\u0002ҽӁ\u0003\u0002\u0002\u0002Ҿҿ\u0007g\u0002\u0002ҿӁ\u0005\u0084C\u0002ӀҲ\u0003\u0002\u0002\u0002ӀҾ\u0003\u0002\u0002\u0002ӁӃ\u0003\u0002\u0002\u0002ӂӀ\u0003\u0002\u0002\u0002Ӄӆ\u0003\u0002\u0002\u0002ӄӂ\u0003\u0002\u0002\u0002ӄӅ\u0003\u0002\u0002\u0002Ӆӑ\u0003\u0002\u0002\u0002ӆӄ\u0003\u0002\u0002\u0002ӇӉ\u0007j\u0002\u0002ӈӇ\u0003\u0002\u0002\u0002ӈӉ\u0003\u0002\u0002\u0002Ӊӊ\u0003\u0002\u0002\u0002ӊӏ\u0007=\u0002\u0002Ӌӌ\u0007Z\u0002\u0002ӌӐ\u0007>\u0002\u0002Ӎӎ\u0007Z\u0002\u0002ӎӐ\u0007V\u0002\u0002ӏӋ\u0003\u0002\u0002\u0002ӏӍ\u0003\u0002\u0002\u0002ӏӐ\u0003\u0002\u0002\u0002ӐӒ\u0003\u0002\u0002\u0002ӑӈ\u0003\u0002\u0002\u0002ӑӒ\u0003\u0002\u0002\u0002ӒO\u0003\u0002\u0002\u0002ӓӔ\u0007w\u0002\u0002Ӕә\u0007\u0005\u0002\u0002ӕӚ\u0007U\u0002\u0002Ӗӗ\t\n\u0002\u0002ӗӘ\u0007\u0007\u0002\u0002ӘӚ\u0005z>\u0002әӕ\u0003\u0002\u0002\u0002әӖ\u0003\u0002\u0002\u0002Ӛӛ\u0003\u0002\u0002\u0002ӛӜ\u0007\u0006\u0002\u0002ӜQ\u0003\u0002\u0002\u0002ӝӠ\u0005\u0092J\u0002ӞӠ\u0005L'\u0002ӟӝ\u0003\u0002\u0002\u0002ӟӞ\u0003\u0002\u0002\u0002Ӡӣ\u0003\u0002\u0002\u0002ӡӢ\u00071\u0002\u0002ӢӤ\u0005\u0094K\u0002ӣӡ\u0003\u0002\u0002\u0002ӣӤ\u0003\u0002\u0002\u0002ӤӦ\u0003\u0002\u0002\u0002ӥӧ\t\u0006\u0002\u0002Ӧӥ\u0003\u0002\u0002\u0002Ӧӧ\u0003\u0002\u0002\u0002ӧS\u0003\u0002\u0002\u0002Өө\u00075\u0002\u0002өӫ\u0005\u0084C\u0002ӪӨ\u0003\u0002\u0002\u0002Ӫӫ\u0003\u0002\u0002\u0002ӫԐ\u0003\u0002\u0002\u0002Ӭӭ\u0007u\u0002\u0002ӭӰ\u0007c\u0002\u0002ӮӰ\u0007\u008e\u0002\u0002ӯӬ\u0003\u0002\u0002\u0002ӯӮ\u0003\u0002\u0002\u0002Ӱӱ\u0003\u0002\u0002\u0002ӱӲ\u0007\u0005\u0002\u0002Ӳӷ\u0005R*\u0002ӳӴ\u0007\u0007\u0002\u0002ӴӶ\u0005R*\u0002ӵӳ\u0003\u0002\u0002\u0002Ӷӹ\u0003\u0002\u0002\u0002ӷӵ\u0003\u0002\u0002\u0002ӷӸ\u0003\u0002\u0002\u0002ӸӺ\u0003\u0002\u0002\u0002ӹӷ\u0003\u0002\u0002\u0002Ӻӻ\u0007\u0006\u0002\u0002ӻӼ\u0005J&\u0002Ӽԑ\u0003\u0002\u0002\u0002ӽӾ\u00070\u0002\u0002Ӿӿ\u0007\u0005\u0002\u0002ӿԀ\u0005L'\u0002Ԁԁ\u0007\u0006\u0002\u0002ԁԑ\u0003\u0002\u0002\u0002Ԃԃ\u0007N\u0002\u0002ԃԄ\u0007c\u0002\u0002Ԅԅ\u0007\u0005\u0002\u0002ԅԊ\u0005\u0092J\u0002Ԇԇ\u0007\u0007\u0002\u0002ԇԉ\u0005\u0092J\u0002ԈԆ\u0003\u0002\u0002\u0002ԉԌ\u0003\u0002\u0002\u0002ԊԈ\u0003\u0002\u0002\u0002Ԋԋ\u0003\u0002\u0002\u0002ԋԍ\u0003\u0002\u0002\u0002ԌԊ\u0003\u0002\u0002\u0002ԍԎ\u0007\u0006\u0002\u0002Ԏԏ\u0005N(\u0002ԏԑ\u0003\u0002\u0002\u0002Ԑӯ\u0003\u0002\u0002\u0002Ԑӽ\u0003\u0002\u0002\u0002ԐԂ\u0003\u0002\u0002\u0002ԑU\u0003\u0002\u0002\u0002ԒԔ\u0007\u0097\u0002\u0002ԓԕ\u0007x\u0002\u0002Ԕԓ\u0003\u0002\u0002\u0002Ԕԕ\u0003\u0002\u0002\u0002ԕԖ\u0003\u0002\u0002\u0002Ԗԛ\u0005X-\u0002ԗԘ\u0007\u0007\u0002\u0002ԘԚ\u0005X-\u0002ԙԗ\u0003\u0002\u0002\u0002Ԛԝ\u0003\u0002\u0002\u0002ԛԙ\u0003\u0002\u0002\u0002ԛԜ\u0003\u0002\u0002\u0002ԜW\u0003\u0002\u0002\u0002ԝԛ\u0003\u0002\u0002\u0002ԞԪ\u0005\u008cG\u0002ԟԠ\u0007\u0005\u0002\u0002Ԡԥ\u0005\u0092J\u0002ԡԢ\u0007\u0007\u0002\u0002ԢԤ\u0005\u0092J\u0002ԣԡ\u0003\u0002\u0002\u0002Ԥԧ\u0003\u0002\u0002\u0002ԥԣ\u0003\u0002\u0002\u0002ԥԦ\u0003\u0002\u0002\u0002ԦԨ\u0003\u0002\u0002\u0002ԧԥ\u0003\u0002\u0002\u0002Ԩԩ\u0007\u0006\u0002\u0002ԩԫ\u0003\u0002\u0002\u0002Ԫԟ\u0003\u0002\u0002\u0002Ԫԫ\u0003\u0002\u0002\u0002ԫԬ\u0003\u0002\u0002\u0002Ԭԭ\u0007%\u0002\u0002ԭԮ\u0007\u0005\u0002\u0002Ԯԯ\u0005:\u001e\u0002ԯ\u0530\u0007\u0006\u0002\u0002\u0530Y\u0003\u0002\u0002\u0002ԱԲ\u0005\u0088E\u0002ԲԳ\u0007\u0004\u0002\u0002ԳԵ\u0003\u0002\u0002\u0002ԴԱ\u0003\u0002\u0002\u0002ԴԵ\u0003\u0002\u0002\u0002ԵԶ\u0003\u0002\u0002\u0002ԶԹ\u0005\u008cG\u0002ԷԸ\u0007%\u0002\u0002ԸԺ\u0005¤S\u0002ԹԷ\u0003\u0002\u0002\u0002ԹԺ\u0003\u0002\u0002\u0002ԺՀ\u0003\u0002\u0002\u0002ԻԼ\u0007Y\u0002\u0002ԼԽ\u0007,\u0002\u0002ԽՁ\u0005\u0098M\u0002ԾԿ\u0007j\u0002\u0002ԿՁ\u0007Y\u0002\u0002ՀԻ\u0003\u0002\u0002\u0002ՀԾ\u0003\u0002\u0002\u0002ՀՁ\u0003\u0002\u0002\u0002Ձ[\u0003\u0002\u0002\u0002ՂՃ\u0007q\u0002\u0002ՃՄ\u0007,\u0002\u0002ՄՉ\u0005^0\u0002ՅՆ\u0007\u0007\u0002\u0002ՆՈ\u0005^0\u0002ՇՅ\u0003\u0002\u0002\u0002ՈՋ\u0003\u0002\u0002\u0002ՉՇ\u0003\u0002\u0002\u0002ՉՊ\u0003\u0002\u0002\u0002Պ]\u0003\u0002\u0002\u0002ՋՉ\u0003\u0002\u0002\u0002ՌՏ\u0005L'\u0002ՍՎ\u00071\u0002\u0002ՎՐ\u0005\u0094K\u0002ՏՍ\u0003\u0002\u0002\u0002ՏՐ\u0003\u0002\u0002\u0002ՐՒ\u0003\u0002\u0002\u0002ՑՓ\t\u0006\u0002\u0002ՒՑ\u0003\u0002\u0002\u0002ՒՓ\u0003\u0002\u0002\u0002Փ_\u0003\u0002\u0002\u0002ՔՕ\u0007f\u0002\u0002Օ\u0558\u0005L'\u0002Ֆ\u0557\t\u000b\u0002\u0002\u0557ՙ\u0005L'\u0002\u0558Ֆ\u0003\u0002\u0002\u0002\u0558ՙ\u0003\u0002\u0002\u0002ՙa\u0003\u0002\u0002\u0002՚՟\u0005p9\u0002՛՟\u0005\u0084C\u0002՜՟\u0007\u009f\u0002\u0002՝՟\u0005t;\u0002՞՚\u0003\u0002\u0002\u0002՞՛\u0003\u0002\u0002\u0002՞՜\u0003\u0002\u0002\u0002՞՝\u0003\u0002\u0002\u0002՟c\u0003\u0002\u0002\u0002\u0560խ\u0007\t\u0002\u0002աբ\u0005\u008cG\u0002բգ\u0007\u0004\u0002\u0002գդ\u0007\t\u0002\u0002դխ\u0003\u0002\u0002\u0002եժ\u0005L'\u0002զը\u0007%\u0002\u0002էզ\u0003\u0002\u0002\u0002էը\u0003\u0002\u0002\u0002ըթ\u0003\u0002\u0002\u0002թի\u0005~@\u0002ժէ\u0003\u0002\u0002\u0002ժի\u0003\u0002\u0002\u0002իխ\u0003\u0002\u0002\u0002լ\u0560\u0003\u0002\u0002\u0002լա\u0003\u0002\u0002\u0002լե\u0003\u0002\u0002\u0002խe\u0003\u0002\u0002\u0002ծկ\u0005\u0088E\u0002կհ\u0007\u0004\u0002\u0002հղ\u0003\u0002\u0002\u0002ձծ\u0003\u0002\u0002\u0002ձղ\u0003\u0002\u0002\u0002ղճ\u0003\u0002\u0002\u0002ճո\u0005\u008cG\u0002մն\u0007%\u0002\u0002յմ\u0003\u0002\u0002\u0002յն\u0003\u0002\u0002\u0002նշ\u0003\u0002\u0002\u0002շչ\u0005¤S\u0002ոյ\u0003\u0002\u0002\u0002ոչ\u0003\u0002\u0002\u0002չտ\u0003\u0002\u0002\u0002պջ\u0007Y\u0002\u0002ջռ\u0007,\u0002\u0002ռր\u0005\u0098M\u0002սվ\u0007j\u0002\u0002վր\u0007Y\u0002\u0002տպ\u0003\u0002\u0002\u0002տս\u0003\u0002\u0002\u0002տր\u0003\u0002\u0002\u0002րֱ\u0003\u0002\u0002\u0002ցւ\u0005\u0088E\u0002ւփ\u0007\u0004\u0002\u0002փօ\u0003\u0002\u0002\u0002քց\u0003\u0002\u0002\u0002քօ\u0003\u0002\u0002\u0002օֆ\u0003\u0002\u0002\u0002ֆև\u0005\u008aF\u0002և\u0590\u0007\u0005\u0002\u0002\u0588֍\u0005L'\u0002։֊\u0007\u0007\u0002\u0002֊\u058c\u0005L'\u0002\u058b։\u0003\u0002\u0002\u0002\u058c֏\u0003\u0002\u0002\u0002֍\u058b\u0003\u0002\u0002\u0002֍֎\u0003\u0002\u0002\u0002֎֑\u0003\u0002\u0002\u0002֏֍\u0003\u0002\u0002\u0002\u0590\u0588\u0003\u0002\u0002\u0002\u0590֑\u0003\u0002\u0002\u0002֑֒\u0003\u0002\u0002\u0002֒֗\u0007\u0006\u0002\u0002֓֕\u0007%\u0002\u0002֔֓\u0003\u0002\u0002\u0002֔֕\u0003\u0002\u0002\u0002֖֕\u0003\u0002\u0002\u0002֖֘\u0005¤S\u0002֗֔\u0003\u0002\u0002\u0002֗֘\u0003\u0002\u0002\u0002ֱ֘\u0003\u0002\u0002\u0002֣֙\u0007\u0005\u0002\u0002֚֟\u0005f4\u0002֛֜\u0007\u0007\u0002\u0002֜֞\u0005f4\u0002֛֝\u0003\u0002\u0002\u0002֞֡\u0003\u0002\u0002\u0002֟֝\u0003\u0002\u0002\u0002֟֠\u0003\u0002\u0002\u0002֤֠\u0003\u0002\u0002\u0002֡֟\u0003\u0002\u0002\u0002֢֤\u0005h5\u0002֣֚\u0003\u0002\u0002\u0002֣֢\u0003\u0002\u0002\u0002֤֥\u0003\u0002\u0002\u0002֥֦\u0007\u0006\u0002\u0002ֱ֦\u0003\u0002\u0002\u0002֧֨\u0007\u0005\u0002\u0002֨֩\u0005:\u001e\u0002֮֩\u0007\u0006\u0002\u0002֪֬\u0007%\u0002\u0002֪֫\u0003\u0002\u0002\u0002֫֬\u0003\u0002\u0002\u0002֭֬\u0003\u0002\u0002\u0002֭֯\u0005¤S\u0002֮֫\u0003\u0002\u0002\u0002֮֯\u0003\u0002\u0002\u0002ֱ֯\u0003\u0002\u0002\u0002ְձ\u0003\u0002\u0002\u0002ְք\u0003\u0002\u0002\u0002ְ֙\u0003\u0002\u0002\u0002ְ֧\u0003\u0002\u0002\u0002ֱg\u0003\u0002\u0002\u0002ֲֹ\u0005f4\u0002ֳִ\u0005j6\u0002ִֵ\u0005f4\u0002ֵֶ\u0005l7\u0002ֶָ\u0003\u0002\u0002\u0002ֳַ\u0003\u0002\u0002\u0002ָֻ\u0003\u0002\u0002\u0002ַֹ\u0003\u0002\u0002\u0002ֹֺ\u0003\u0002\u0002\u0002ֺi\u0003\u0002\u0002\u0002ֹֻ\u0003\u0002\u0002\u0002ּ\u05ca\u0007\u0007\u0002\u0002ֽֿ\u0007h\u0002\u0002־ֽ\u0003\u0002\u0002\u0002־ֿ\u0003\u0002\u0002\u0002ֿ׆\u0003\u0002\u0002\u0002׀ׂ\u0007d\u0002\u0002ׁ׃\u0007r\u0002\u0002ׁׂ\u0003\u0002\u0002\u0002ׂ׃\u0003\u0002\u0002\u0002׃ׇ\u0003\u0002\u0002\u0002ׇׄ\u0007[\u0002\u0002ׇׅ\u00077\u0002\u0002׆׀\u0003\u0002\u0002\u0002׆ׄ\u0003\u0002\u0002\u0002׆ׅ\u0003\u0002\u0002\u0002׆ׇ\u0003\u0002\u0002\u0002ׇ\u05c8\u0003\u0002\u0002\u0002\u05c8\u05ca\u0007b\u0002\u0002\u05c9ּ\u0003\u0002\u0002\u0002\u05c9־\u0003\u0002\u0002\u0002\u05cak\u0003\u0002\u0002\u0002\u05cb\u05cc\u0007o\u0002\u0002\u05ccך\u0005L'\u0002\u05cd\u05ce\u0007\u0090\u0002\u0002\u05ce\u05cf\u0007\u0005\u0002\u0002\u05cfה\u0005\u0092J\u0002אב\u0007\u0007\u0002\u0002בד\u0005\u0092J\u0002גא\u0003\u0002\u0002\u0002דז\u0003\u0002\u0002\u0002הג\u0003\u0002\u0002\u0002הו\u0003\u0002\u0002\u0002וח\u0003\u0002\u0002\u0002זה\u0003\u0002\u0002\u0002חט\u0007\u0006\u0002\u0002טך\u0003\u0002\u0002\u0002י\u05cb\u0003\u0002\u0002\u0002י\u05cd\u0003\u0002\u0002\u0002יך\u0003\u0002\u0002\u0002ךm\u0003\u0002\u0002\u0002כס\u0007\u008d\u0002\u0002לם\u0007\u008d\u0002\u0002םס\u0007!\u0002\u0002מס\u0007^\u0002\u0002ןס\u0007H\u0002\u0002נכ\u0003\u0002\u0002\u0002נל\u0003\u0002\u0002\u0002נמ\u0003\u0002\u0002\u0002נן\u0003\u0002\u0002\u0002סo\u0003\u0002\u0002\u0002עפ\t\f\u0002\u0002ףע\u0003\u0002\u0002\u0002ףפ\u0003\u0002\u0002\u0002פץ\u0003\u0002\u0002\u0002ץצ\u0007\u009d\u0002\u0002צq\u0003\u0002\u0002\u0002קװ\u0007\u009d\u0002\u0002רװ\u0007\u009f\u0002\u0002שװ\u0007 \u0002\u0002תװ\u0007l\u0002\u0002\u05ebװ\u00079\u0002\u0002\u05ecװ\u00078\u0002\u0002\u05edװ\u0007:\u0002\u0002\u05eeװ\u0005t;\u0002\u05efק\u0003\u0002\u0002\u0002\u05efר\u0003\u0002\u0002\u0002\u05efש\u0003\u0002\u0002\u0002\u05efת\u0003\u0002\u0002\u0002\u05ef\u05eb\u0003\u0002\u0002\u0002\u05ef\u05ec\u0003\u0002\u0002\u0002\u05ef\u05ed\u0003\u0002\u0002\u0002\u05ef\u05ee\u0003\u0002\u0002\u0002װs\u0003\u0002\u0002\u0002ױײ\t\r\u0002\u0002ײu\u0003\u0002\u0002\u0002׳״\t\u000e\u0002\u0002״w\u0003\u0002\u0002\u0002\u05f5\u05fe\u0007\r\u0002\u0002\u05f6\u05fe\t\u000f\u0002\u0002\u05f7\u05fe\t\f\u0002\u0002\u05f8\u05fe\t\u0010\u0002\u0002\u05f9\u05fe\t\u0011\u0002\u0002\u05fa\u05fe\t\u0012\u0002\u0002\u05fb\u05fe\u0007$\u0002\u0002\u05fc\u05fe\u0007p\u0002\u0002\u05fd\u05f5\u0003\u0002\u0002\u0002\u05fd\u05f6\u0003\u0002\u0002\u0002\u05fd\u05f7\u0003\u0002\u0002\u0002\u05fd\u05f8\u0003\u0002\u0002\u0002\u05fd\u05f9\u0003\u0002\u0002\u0002\u05fd\u05fa\u0003\u0002\u0002\u0002\u05fd\u05fb\u0003\u0002\u0002\u0002\u05fd\u05fc\u0003\u0002\u0002\u0002\u05fey\u0003\u0002\u0002\u0002\u05ff\u0600\u0007\u009f\u0002\u0002\u0600{\u0003\u0002\u0002\u0002\u0601\u0604\u0005L'\u0002\u0602\u0604\u0005D#\u0002\u0603\u0601\u0003\u0002\u0002\u0002\u0603\u0602\u0003\u0002\u0002\u0002\u0604}\u0003\u0002\u0002\u0002\u0605؆\t\u0013\u0002\u0002؆\u007f\u0003\u0002\u0002\u0002؇؈\u0007\u0005\u0002\u0002؈؍\u0005\u0092J\u0002؉؊\u0007\u0007\u0002\u0002؊،\u0005\u0092J\u0002؋؉\u0003\u0002\u0002\u0002،؏\u0003\u0002\u0002\u0002؍؋\u0003\u0002\u0002\u0002؍؎\u0003\u0002\u0002\u0002؎ؐ\u0003\u0002\u0002\u0002؏؍\u0003\u0002\u0002\u0002ؐؑ\u0007\u0006\u0002\u0002ؑ\u0081\u0003\u0002\u0002\u0002ؒؓ\t\u0014\u0002\u0002ؓ\u0083\u0003\u0002\u0002\u0002ؔؕ\u0005¨U\u0002ؕ\u0085\u0003\u0002\u0002\u0002ؖؗ\u0005¨U\u0002ؗ\u0087\u0003\u0002\u0002\u0002ؘؙ\u0005¨U\u0002ؙ\u0089\u0003\u0002\u0002\u0002ؚ؛\u0005¨U\u0002؛\u008b\u0003\u0002\u0002\u0002\u061c\u061d\u0005¨U\u0002\u061d\u008d\u0003\u0002\u0002\u0002؞؟\u0005¨U\u0002؟\u008f\u0003\u0002\u0002\u0002ؠء\u0005¨U\u0002ء\u0091\u0003\u0002\u0002\u0002آأ\u0005¨U\u0002أ\u0093\u0003\u0002\u0002\u0002ؤإ\u0005¨U\u0002إ\u0095\u0003\u0002\u0002\u0002ئا\u0005¨U\u0002ا\u0097\u0003\u0002\u0002\u0002بة\u0005¨U\u0002ة\u0099\u0003\u0002\u0002\u0002تث\u0005¨U\u0002ث\u009b\u0003\u0002\u0002\u0002جح\u0005¨U\u0002ح\u009d\u0003\u0002\u0002\u0002خد\u0005¨U\u0002د\u009f\u0003\u0002\u0002\u0002ذر\u0005¨U\u0002ر¡\u0003\u0002\u0002\u0002زس\u0005¨U\u0002س£\u0003\u0002\u0002\u0002شػ\u0007\u009c\u0002\u0002صػ\u0007\u009f\u0002\u0002ضط\u0007\u0005\u0002\u0002طظ\u0005¤S\u0002ظع\u0007\u0006\u0002\u0002عػ\u0003\u0002\u0002\u0002غش\u0003\u0002\u0002\u0002غص\u0003\u0002\u0002\u0002غض\u0003\u0002\u0002\u0002ػ¥\u0003\u0002\u0002\u0002ؼؽ\u0005¨U\u0002ؽ§\u0003\u0002\u0002\u0002ؾن\u0007\u009c\u0002\u0002ؿن\u0005\u0082B\u0002ـن\u0007\u009f\u0002\u0002فق\u0007\u0005\u0002\u0002قك\u0005¨U\u0002كل\u0007\u0006\u0002\u0002لن\u0003\u0002\u0002\u0002مؾ\u0003\u0002\u0002\u0002مؿ\u0003\u0002\u0002\u0002مـ\u0003\u0002\u0002\u0002مف\u0003\u0002\u0002\u0002ن©\u0003\u0002\u0002\u0002å¬®¹ÀÅËÑÓð÷ÿĂċďėěĝĢĤĨĮĳľńňŎœŜţŨŬŰŶŻƂƍƐƒƘƞƢƩƯƵƻǀǈǋǖǛǦǫǮǵǸǿȂȅȉȑȖȞȣȫȰȸȽɂɕɛɠɨɭɶʁʈʎʑʛʡʣʪʱʸʽʿ˅ˎ˕˙˛˟˦˨ˬ˯˵˽̢̘̜̞̫̰̲̻͍͓́̄̈̏͆͐ͦͭ͠ʹ\u0379ͼΉΏΖΝ\u03a2ΥΨάΰελχϋϐϔϗϩϮϳϻЀЉАДОЫЮжпушёіѢѨѯѳѽҀ҆ҌҕҘҜҞҠҫҰҼӀӄӈӏӑәӟӣӦӪӯӷԊԐԔԛԥԪԴԹՀՉՏՒ\u0558՞էժլձյոտք֍\u0590ְֹ֣֮֔֗֟֫־ׂ׆\u05c9הינף\u05ef\u05fd\u0603؍غم";
    }

    public ATN getATN() {
        return _ATN;
    }

    public SQLiteParser(TokenStream input) {
        super(input);
        this._interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    public final SQLiteParser.ParseContext parse() throws RecognitionException {
        SQLiteParser.ParseContext _localctx = new SQLiteParser.ParseContext(this._ctx, this.getState());
        this.enterRule(_localctx, 0, 0);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(172);
            this._errHandler.sync(this);

            for(int _la = this._input.LA(1); (_la & -64) == 0 && (1L << _la & -6912461228224806910L) != 0L || (_la - 65 & -64) == 0 && (1L << _la - 65 & 5404882502831571209L) != 0L || (_la - 129 & -64) == 0 && (1L << _la - 129 & 8591036419L) != 0L; _la = this._input.LA(1)) {
                this.setState(170);
                this._errHandler.sync(this);
                switch(this._input.LA(1)) {
                    case 1:
                    case 32:
                    case 33:
                    case 37:
                    case 40:
                    case 49:
                    case 52:
                    case 61:
                    case 63:
                    case 65:
                    case 68:
                    case 73:
                    case 90:
                    case 114:
                    case 121:
                    case 122:
                    case 124:
                    case 127:
                    case 129:
                    case 130:
                    case 141:
                    case 143:
                    case 144:
                    case 149:
                        this.setState(168);
                        this.sql_stmt_list();
                        break;
                    case 162:
                        this.setState(169);
                        this.error();
                        break;
                    default:
                        throw new NoViableAltException(this);
                }

                this.setState(174);
                this._errHandler.sync(this);
            }

            this.setState(175);
            this.match(-1);
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.ErrorContext error() throws RecognitionException {
        SQLiteParser.ErrorContext _localctx = new SQLiteParser.ErrorContext(this._ctx, this.getState());
        this.enterRule(_localctx, 2, 1);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(177);
            _localctx.UNEXPECTED_CHAR = this.match(162);
            throw new RuntimeException("UNEXPECTED_CHAR=" + (_localctx.UNEXPECTED_CHAR != null ? _localctx.UNEXPECTED_CHAR.getText() : null));
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Sql_stmt_listContext sql_stmt_list() throws RecognitionException {
        SQLiteParser.Sql_stmt_listContext _localctx = new SQLiteParser.Sql_stmt_listContext(this._ctx, this.getState());
        this.enterRule(_localctx, 4, 2);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(183);
            this._errHandler.sync(this);

            int _la;
            for(_la = this._input.LA(1); _la == 1; _la = this._input.LA(1)) {
                this.setState(180);
                this.match(1);
                this.setState(185);
                this._errHandler.sync(this);
            }

            this.setState(186);
            this.sql_stmt();
            this.setState(195);
            this._errHandler.sync(this);

            int _alt;
            for(_alt = ((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 4, this._ctx); _alt != 2 && _alt != 0; _alt = ((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 4, this._ctx)) {
                if (_alt == 1) {
                    this.setState(188);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);

                    do {
                        this.setState(187);
                        this.match(1);
                        this.setState(190);
                        this._errHandler.sync(this);
                        _la = this._input.LA(1);
                    } while(_la == 1);

                    this.setState(192);
                    this.sql_stmt();
                }

                this.setState(197);
                this._errHandler.sync(this);
            }

            this.setState(201);
            this._errHandler.sync(this);

            for(_alt = ((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 5, this._ctx); _alt != 2 && _alt != 0; _alt = ((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 5, this._ctx)) {
                if (_alt == 1) {
                    this.setState(198);
                    this.match(1);
                }

                this.setState(203);
                this._errHandler.sync(this);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Sql_stmtContext sql_stmt() throws RecognitionException {
        SQLiteParser.Sql_stmtContext _localctx = new SQLiteParser.Sql_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 6, 3);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(209);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 73) {
                this.setState(204);
                this.match(73);
                this.setState(207);
                this._errHandler.sync(this);
                _la = this._input.LA(1);
                if (_la == 116) {
                    this.setState(205);
                    this.match(116);
                    this.setState(206);
                    this.match(113);
                }
            }

            this.setState(238);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 8, this._ctx)) {
                case 1:
                    this.setState(211);
                    this.alter_table_stmt();
                    break;
                case 2:
                    this.setState(212);
                    this.analyze_stmt();
                    break;
                case 3:
                    this.setState(213);
                    this.attach_stmt();
                    break;
                case 4:
                    this.setState(214);
                    this.begin_stmt();
                    break;
                case 5:
                    this.setState(215);
                    this.commit_stmt();
                    break;
                case 6:
                    this.setState(216);
                    this.create_index_stmt();
                    break;
                case 7:
                    this.setState(217);
                    this.create_table_stmt();
                    break;
                case 8:
                    this.setState(218);
                    this.create_trigger_stmt();
                    break;
                case 9:
                    this.setState(219);
                    this.create_view_stmt();
                    break;
                case 10:
                    this.setState(220);
                    this.create_virtual_table_stmt();
                    break;
                case 11:
                    this.setState(221);
                    this.delete_stmt();
                    break;
                case 12:
                    this.setState(222);
                    this.delete_stmt_limited();
                    break;
                case 13:
                    this.setState(223);
                    this.detach_stmt();
                    break;
                case 14:
                    this.setState(224);
                    this.drop_index_stmt();
                    break;
                case 15:
                    this.setState(225);
                    this.drop_table_stmt();
                    break;
                case 16:
                    this.setState(226);
                    this.drop_trigger_stmt();
                    break;
                case 17:
                    this.setState(227);
                    this.drop_view_stmt();
                    break;
                case 18:
                    this.setState(228);
                    this.insert_stmt();
                    break;
                case 19:
                    this.setState(229);
                    this.pragma_stmt();
                    break;
                case 20:
                    this.setState(230);
                    this.reindex_stmt();
                    break;
                case 21:
                    this.setState(231);
                    this.release_stmt();
                    break;
                case 22:
                    this.setState(232);
                    this.rollback_stmt();
                    break;
                case 23:
                    this.setState(233);
                    this.savepoint_stmt();
                    break;
                case 24:
                    this.setState(234);
                    this.select_stmt();
                    break;
                case 25:
                    this.setState(235);
                    this.update_stmt();
                    break;
                case 26:
                    this.setState(236);
                    this.update_stmt_limited();
                    break;
                case 27:
                    this.setState(237);
                    this.vacuum_stmt();
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Alter_table_stmtContext alter_table_stmt() throws RecognitionException {
        SQLiteParser.Alter_table_stmtContext _localctx = new SQLiteParser.Alter_table_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 8, 4);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(240);
            this.match(32);
            this.setState(241);
            this.match(132);
            this.setState(245);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 9, this._ctx)) {
                case 1:
                    this.setState(242);
                    this.schema_name();
                    this.setState(243);
                    this.match(2);
                default:
                    this.setState(247);
                    this.table_name();
                    this.setState(256);
                    this._errHandler.sync(this);
                    switch(this._input.LA(1)) {
                        case 29:
                            this.setState(251);
                            this.match(29);
                            this.setState(253);
                            this._errHandler.sync(this);
                            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 10, this._ctx)) {
                                case 1:
                                    this.setState(252);
                                    this.match(48);
                                default:
                                    this.setState(255);
                                    this.column_def();
                                    return _localctx;
                            }
                        case 123:
                            this.setState(248);
                            this.match(123);
                            this.setState(249);
                            this.match(136);
                            this.setState(250);
                            this.new_table_name();
                            break;
                        default:
                            throw new NoViableAltException(this);
                    }
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Analyze_stmtContext analyze_stmt() throws RecognitionException {
        SQLiteParser.Analyze_stmtContext _localctx = new SQLiteParser.Analyze_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 10, 5);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(258);
            this.match(33);
            this.setState(265);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 12, this._ctx)) {
                case 1:
                    this.setState(259);
                    this.schema_name();
                    break;
                case 2:
                    this.setState(260);
                    this.table_or_index_name();
                    break;
                case 3:
                    this.setState(261);
                    this.schema_name();
                    this.setState(262);
                    this.match(2);
                    this.setState(263);
                    this.table_or_index_name();
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Attach_stmtContext attach_stmt() throws RecognitionException {
        SQLiteParser.Attach_stmtContext _localctx = new SQLiteParser.Attach_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 12, 6);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(267);
            this.match(37);
            this.setState(269);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 13, this._ctx)) {
                case 1:
                    this.setState(268);
                    this.match(57);
                default:
                    this.setState(271);
                    this.expr(0);
                    this.setState(272);
                    this.match(35);
                    this.setState(273);
                    this.schema_name();
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Begin_stmtContext begin_stmt() throws RecognitionException {
        SQLiteParser.Begin_stmtContext _localctx = new SQLiteParser.Begin_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 14, 7);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(275);
            this.match(40);
            this.setState(277);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if ((_la - 60 & -64) == 0 && (1L << _la - 60 & 16779265L) != 0L) {
                this.setState(276);
                _la = this._input.LA(1);
                if ((_la - 60 & -64) == 0 && (1L << _la - 60 & 16779265L) != 0L) {
                    if (this._input.LA(1) == -1) {
                        this.matchedEOF = true;
                    }

                    this._errHandler.reportMatch(this);
                    this.consume();
                } else {
                    this._errHandler.recoverInline(this);
                }
            }

            this.setState(283);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 137) {
                this.setState(279);
                this.match(137);
                this.setState(281);
                this._errHandler.sync(this);
                switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 15, this._ctx)) {
                    case 1:
                        this.setState(280);
                        this.transaction_name();
                }
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Commit_stmtContext commit_stmt() throws RecognitionException {
        SQLiteParser.Commit_stmtContext _localctx = new SQLiteParser.Commit_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 16, 8);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(285);
            int _la = this._input.LA(1);
            if (_la != 49 && _la != 68) {
                this._errHandler.recoverInline(this);
            } else {
                if (this._input.LA(1) == -1) {
                    this.matchedEOF = true;
                }

                this._errHandler.reportMatch(this);
                this.consume();
            }

            this.setState(290);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 137) {
                this.setState(286);
                this.match(137);
                this.setState(288);
                this._errHandler.sync(this);
                switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 17, this._ctx)) {
                    case 1:
                        this.setState(287);
                        this.transaction_name();
                }
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Create_index_stmtContext create_index_stmt() throws RecognitionException {
        SQLiteParser.Create_index_stmtContext _localctx = new SQLiteParser.Create_index_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 18, 9);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(292);
            this.match(52);
            this.setState(294);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 140) {
                this.setState(293);
                this.match(140);
            }

            this.setState(296);
            this.match(86);
            this.setState(300);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 20, this._ctx)) {
                case 1:
                    this.setState(297);
                    this.match(82);
                    this.setState(298);
                    this.match(104);
                    this.setState(299);
                    this.match(72);
                default:
                    this.setState(305);
                    this._errHandler.sync(this);
                    switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 21, this._ctx)) {
                        case 1:
                            this.setState(302);
                            this.schema_name();
                            this.setState(303);
                            this.match(2);
                        default:
                            this.setState(307);
                            this.index_name();
                            this.setState(308);
                            this.match(109);
                            this.setState(309);
                            this.table_name();
                            this.setState(310);
                            this.match(3);
                            this.setState(311);
                            this.indexed_column();
                            this.setState(316);
                            this._errHandler.sync(this);
                            _la = this._input.LA(1);
                    }
            }

            while(_la == 5) {
                this.setState(312);
                this.match(5);
                this.setState(313);
                this.indexed_column();
                this.setState(318);
                this._errHandler.sync(this);
                _la = this._input.LA(1);
            }

            this.setState(319);
            this.match(4);
            this.setState(322);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 148) {
                this.setState(320);
                this.match(148);
                this.setState(321);
                this.expr(0);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Create_table_stmtContext create_table_stmt() throws RecognitionException {
        SQLiteParser.Create_table_stmtContext _localctx = new SQLiteParser.Create_table_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 20, 10);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(324);
            this.match(52);
            this.setState(326);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 133 || _la == 134) {
                this.setState(325);
                _la = this._input.LA(1);
                if (_la != 133 && _la != 134) {
                    this._errHandler.recoverInline(this);
                } else {
                    if (this._input.LA(1) == -1) {
                        this.matchedEOF = true;
                    }

                    this._errHandler.reportMatch(this);
                    this.consume();
                }
            }

            this.setState(328);
            this.match(132);
            this.setState(332);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 25, this._ctx)) {
                case 1:
                    this.setState(329);
                    this.match(82);
                    this.setState(330);
                    this.match(104);
                    this.setState(331);
                    this.match(72);
                default:
                    this.setState(337);
                    this._errHandler.sync(this);
                    switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 26, this._ctx)) {
                        case 1:
                            this.setState(334);
                            this.schema_name();
                            this.setState(335);
                            this.match(2);
                    }

                    this.setState(339);
                    this.table_name();
                    this.setState(362);
                    this._errHandler.sync(this);
                    switch(this._input.LA(1)) {
                        case 3:
                            this.setState(340);
                            this.match(3);
                            this.setState(341);
                            this.column_def();
                            this.setState(346);
                            this._errHandler.sync(this);

                            for(int _alt = ((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 27, this._ctx); _alt != 1 && _alt != 0; _alt = ((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 27, this._ctx)) {
                                if (_alt == 2) {
                                    this.setState(342);
                                    this.match(5);
                                    this.setState(343);
                                    this.column_def();
                                }

                                this.setState(348);
                                this._errHandler.sync(this);
                            }

                            this.setState(353);
                            this._errHandler.sync(this);

                            for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                                this.setState(349);
                                this.match(5);
                                this.setState(350);
                                this.table_constraint();
                                this.setState(355);
                                this._errHandler.sync(this);
                            }

                            this.setState(356);
                            this.match(4);
                            this.setState(358);
                            this._errHandler.sync(this);
                            _la = this._input.LA(1);
                            if (_la == 151) {
                                this.setState(357);
                                this.match(151);
                            }
                            break;
                        case 35:
                            this.setState(360);
                            this.match(35);
                            this.setState(361);
                            this.select_stmt();
                            break;
                        default:
                            throw new NoViableAltException(this);
                    }
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Create_trigger_stmtContext create_trigger_stmt() throws RecognitionException {
        SQLiteParser.Create_trigger_stmtContext _localctx = new SQLiteParser.Create_trigger_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 22, 11);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(364);
            this.match(52);
            this.setState(366);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 133 || _la == 134) {
                this.setState(365);
                _la = this._input.LA(1);
                if (_la != 133 && _la != 134) {
                    this._errHandler.recoverInline(this);
                } else {
                    if (this._input.LA(1) == -1) {
                        this.matchedEOF = true;
                    }

                    this._errHandler.reportMatch(this);
                    this.consume();
                }
            }

            this.setState(368);
            this.match(138);
            this.setState(372);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 32, this._ctx)) {
                case 1:
                    this.setState(369);
                    this.match(82);
                    this.setState(370);
                    this.match(104);
                    this.setState(371);
                    this.match(72);
            }

            this.setState(377);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 33, this._ctx)) {
                case 1:
                    this.setState(374);
                    this.schema_name();
                    this.setState(375);
                    this.match(2);
            }

            this.setState(379);
            this.trigger_name();
            this.setState(384);
            this._errHandler.sync(this);
            switch(this._input.LA(1)) {
                case 30:
                    this.setState(381);
                    this.match(30);
                    break;
                case 39:
                    this.setState(380);
                    this.match(39);
                case 61:
                case 90:
                case 141:
                default:
                    break;
                case 91:
                    this.setState(382);
                    this.match(91);
                    this.setState(383);
                    this.match(107);
            }

            this.setState(400);
            this._errHandler.sync(this);
            label215:
            switch(this._input.LA(1)) {
                case 61:
                    this.setState(386);
                    this.match(61);
                    break;
                case 90:
                    this.setState(387);
                    this.match(90);
                    break;
                case 141:
                    this.setState(388);
                    this.match(141);
                    this.setState(398);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la != 107) {
                        break;
                    }

                    this.setState(389);
                    this.match(107);
                    this.setState(390);
                    this.column_name();
                    this.setState(395);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);

                    while(true) {
                        if (_la != 5) {
                            break label215;
                        }

                        this.setState(391);
                        this.match(5);
                        this.setState(392);
                        this.column_name();
                        this.setState(397);
                        this._errHandler.sync(this);
                        _la = this._input.LA(1);
                    }
                default:
                    throw new NoViableAltException(this);
            }

            this.setState(402);
            this.match(109);
            this.setState(406);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 38, this._ctx)) {
                case 1:
                    this.setState(403);
                    this.schema_name();
                    this.setState(404);
                    this.match(2);
                default:
                    this.setState(408);
                    this.table_name();
                    this.setState(412);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 75) {
                        this.setState(409);
                        this.match(75);
                        this.setState(410);
                        this.match(66);
                        this.setState(411);
                        this.match(128);
                    }

                    this.setState(416);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 147) {
                        this.setState(414);
                        this.match(147);
                        this.setState(415);
                        this.expr(0);
                    }

                    this.setState(418);
                    this.match(40);
                    this.setState(427);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
            }

            do {
                this.setState(423);
                this._errHandler.sync(this);
                switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 41, this._ctx)) {
                    case 1:
                        this.setState(419);
                        this.update_stmt();
                        break;
                    case 2:
                        this.setState(420);
                        this.insert_stmt();
                        break;
                    case 3:
                        this.setState(421);
                        this.delete_stmt();
                        break;
                    case 4:
                        this.setState(422);
                        this.select_stmt();
                }

                this.setState(425);
                this.match(1);
                this.setState(429);
                this._errHandler.sync(this);
                _la = this._input.LA(1);
            } while(_la == 61 || (_la - 90 & -64) == 0 && (1L << _la - 90 & 596728067318087681L) != 0L);

            this.setState(431);
            this.match(68);
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Create_view_stmtContext create_view_stmt() throws RecognitionException {
        SQLiteParser.Create_view_stmtContext _localctx = new SQLiteParser.Create_view_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 24, 12);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(433);
            this.match(52);
            this.setState(435);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 133 || _la == 134) {
                this.setState(434);
                _la = this._input.LA(1);
                if (_la != 133 && _la != 134) {
                    this._errHandler.recoverInline(this);
                } else {
                    if (this._input.LA(1) == -1) {
                        this.matchedEOF = true;
                    }

                    this._errHandler.reportMatch(this);
                    this.consume();
                }
            }

            this.setState(437);
            this.match(145);
            this.setState(441);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 44, this._ctx)) {
                case 1:
                    this.setState(438);
                    this.match(82);
                    this.setState(439);
                    this.match(104);
                    this.setState(440);
                    this.match(72);
                default:
                    this.setState(446);
                    this._errHandler.sync(this);
                    switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 45, this._ctx)) {
                        case 1:
                            this.setState(443);
                            this.schema_name();
                            this.setState(444);
                            this.match(2);
                    }

                    this.setState(448);
                    this.view_name();
                    this.setState(457);
                    this._errHandler.sync(this);
                    switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 47, this._ctx)) {
                        case 1:
                            this.setState(449);
                            this.column_name();
                            this.setState(454);
                            this._errHandler.sync(this);

                            for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                                this.setState(450);
                                this.match(5);
                                this.setState(451);
                                this.column_name();
                                this.setState(456);
                                this._errHandler.sync(this);
                            }
                    }

                    this.setState(459);
                    this.match(35);
                    this.setState(460);
                    this.select_stmt();
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Create_virtual_table_stmtContext create_virtual_table_stmt() throws RecognitionException {
        SQLiteParser.Create_virtual_table_stmtContext _localctx = new SQLiteParser.Create_virtual_table_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 26, 13);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(462);
            this.match(52);
            this.setState(463);
            this.match(146);
            this.setState(464);
            this.match(132);
            this.setState(468);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 48, this._ctx)) {
                case 1:
                    this.setState(465);
                    this.match(82);
                    this.setState(466);
                    this.match(104);
                    this.setState(467);
                    this.match(72);
                default:
                    this.setState(473);
                    this._errHandler.sync(this);
                    switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 49, this._ctx)) {
                        case 1:
                            this.setState(470);
                            this.schema_name();
                            this.setState(471);
                            this.match(2);
                    }

                    this.setState(475);
                    this.table_name();
                    this.setState(476);
                    this.match(142);
                    this.setState(477);
                    this.module_name();
                    this.setState(489);
                    this._errHandler.sync(this);
                    int _la = this._input.LA(1);
                    if (_la == 3) {
                        this.setState(478);
                        this.match(3);
                        this.setState(479);
                        this.module_argument();
                        this.setState(484);
                        this._errHandler.sync(this);

                        for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                            this.setState(480);
                            this.match(5);
                            this.setState(481);
                            this.module_argument();
                            this.setState(486);
                            this._errHandler.sync(this);
                        }

                        this.setState(487);
                        this.match(4);
                    }
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Delete_stmtContext delete_stmt() throws RecognitionException {
        SQLiteParser.Delete_stmtContext _localctx = new SQLiteParser.Delete_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 28, 14);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(492);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 149) {
                this.setState(491);
                this.with_clause();
            }

            this.setState(494);
            this.match(61);
            this.setState(495);
            this.match(77);
            this.setState(496);
            this.qualified_table_name();
            this.setState(499);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 148) {
                this.setState(497);
                this.match(148);
                this.setState(498);
                this.expr(0);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Delete_stmt_limitedContext delete_stmt_limited() throws RecognitionException {
        SQLiteParser.Delete_stmt_limitedContext _localctx = new SQLiteParser.Delete_stmt_limitedContext(this._ctx, this.getState());
        this.enterRule(_localctx, 30, 15);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(502);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 149) {
                this.setState(501);
                this.with_clause();
            }

            this.setState(504);
            this.match(61);
            this.setState(505);
            this.match(77);
            this.setState(506);
            this.qualified_table_name();
            this.setState(509);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 148) {
                this.setState(507);
                this.match(148);
                this.setState(508);
                this.expr(0);
            }

            this.setState(515);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 100 || _la == 111) {
                this.setState(512);
                this._errHandler.sync(this);
                _la = this._input.LA(1);
                if (_la == 111) {
                    this.setState(511);
                    this.order_clause();
                }

                this.setState(514);
                this.limit_clause();
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Detach_stmtContext detach_stmt() throws RecognitionException {
        SQLiteParser.Detach_stmtContext _localctx = new SQLiteParser.Detach_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 32, 16);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(517);
            this.match(63);
            this.setState(519);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 58, this._ctx)) {
                case 1:
                    this.setState(518);
                    this.match(57);
                default:
                    this.setState(521);
                    this.schema_name();
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Drop_index_stmtContext drop_index_stmt() throws RecognitionException {
        SQLiteParser.Drop_index_stmtContext _localctx = new SQLiteParser.Drop_index_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 34, 17);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(523);
            this.match(65);
            this.setState(524);
            this.match(86);
            this.setState(527);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 59, this._ctx)) {
                case 1:
                    this.setState(525);
                    this.match(82);
                    this.setState(526);
                    this.match(72);
                default:
                    this.setState(532);
                    this._errHandler.sync(this);
                    switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 60, this._ctx)) {
                        case 1:
                            this.setState(529);
                            this.schema_name();
                            this.setState(530);
                            this.match(2);
                        default:
                            this.setState(534);
                            this.index_name();
                    }
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Drop_table_stmtContext drop_table_stmt() throws RecognitionException {
        SQLiteParser.Drop_table_stmtContext _localctx = new SQLiteParser.Drop_table_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 36, 18);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(536);
            this.match(65);
            this.setState(537);
            this.match(132);
            this.setState(540);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 61, this._ctx)) {
                case 1:
                    this.setState(538);
                    this.match(82);
                    this.setState(539);
                    this.match(72);
                default:
                    this.setState(545);
                    this._errHandler.sync(this);
                    switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 62, this._ctx)) {
                        case 1:
                            this.setState(542);
                            this.schema_name();
                            this.setState(543);
                            this.match(2);
                        default:
                            this.setState(547);
                            this.table_name();
                    }
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Drop_trigger_stmtContext drop_trigger_stmt() throws RecognitionException {
        SQLiteParser.Drop_trigger_stmtContext _localctx = new SQLiteParser.Drop_trigger_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 38, 19);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(549);
            this.match(65);
            this.setState(550);
            this.match(138);
            this.setState(553);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 63, this._ctx)) {
                case 1:
                    this.setState(551);
                    this.match(82);
                    this.setState(552);
                    this.match(72);
                default:
                    this.setState(558);
                    this._errHandler.sync(this);
                    switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 64, this._ctx)) {
                        case 1:
                            this.setState(555);
                            this.schema_name();
                            this.setState(556);
                            this.match(2);
                        default:
                            this.setState(560);
                            this.trigger_name();
                    }
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Drop_view_stmtContext drop_view_stmt() throws RecognitionException {
        SQLiteParser.Drop_view_stmtContext _localctx = new SQLiteParser.Drop_view_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 40, 20);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(562);
            this.match(65);
            this.setState(563);
            this.match(145);
            this.setState(566);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 65, this._ctx)) {
                case 1:
                    this.setState(564);
                    this.match(82);
                    this.setState(565);
                    this.match(72);
                default:
                    this.setState(571);
                    this._errHandler.sync(this);
                    switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 66, this._ctx)) {
                        case 1:
                            this.setState(568);
                            this.schema_name();
                            this.setState(569);
                            this.match(2);
                        default:
                            this.setState(573);
                            this.view_name();
                    }
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Insert_stmtContext insert_stmt() throws RecognitionException {
        SQLiteParser.Insert_stmtContext _localctx = new SQLiteParser.Insert_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 42, 21);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(576);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 149) {
                this.setState(575);
                this.with_clause();
            }

            this.setState(595);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 68, this._ctx)) {
                case 1:
                    this.setState(578);
                    this.match(90);
                    break;
                case 2:
                    this.setState(579);
                    this.match(124);
                    break;
                case 3:
                    this.setState(580);
                    this.match(90);
                    this.setState(581);
                    this.match(110);
                    this.setState(582);
                    this.match(124);
                    break;
                case 4:
                    this.setState(583);
                    this.match(90);
                    this.setState(584);
                    this.match(110);
                    this.setState(585);
                    this.match(127);
                    break;
                case 5:
                    this.setState(586);
                    this.match(90);
                    this.setState(587);
                    this.match(110);
                    this.setState(588);
                    this.match(27);
                    break;
                case 6:
                    this.setState(589);
                    this.match(90);
                    this.setState(590);
                    this.match(110);
                    this.setState(591);
                    this.match(74);
                    break;
                case 7:
                    this.setState(592);
                    this.match(90);
                    this.setState(593);
                    this.match(110);
                    this.setState(594);
                    this.match(83);
            }

            this.setState(597);
            this.match(93);
            this.setState(601);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 69, this._ctx)) {
                case 1:
                    this.setState(598);
                    this.schema_name();
                    this.setState(599);
                    this.match(2);
                default:
                    this.setState(603);
                    this.table_name();
                    this.setState(606);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 35) {
                        this.setState(604);
                        this.match(35);
                        this.setState(605);
                        this.table_alias();
                    }

                    this.setState(619);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 3) {
                        this.setState(608);
                        this.match(3);
                        this.setState(609);
                        this.column_name();
                        this.setState(614);
                        this._errHandler.sync(this);

                        for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                            this.setState(610);
                            this.match(5);
                            this.setState(611);
                            this.column_name();
                            this.setState(616);
                            this._errHandler.sync(this);
                        }

                        this.setState(617);
                        this.match(4);
                    }

                    this.setState(652);
                    this._errHandler.sync(this);
                    switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 76, this._ctx)) {
                        case 1:
                            this.setState(621);
                            this.match(144);
                            this.setState(622);
                            this.match(3);
                            this.setState(623);
                            this.expr(0);
                            this.setState(628);
                            this._errHandler.sync(this);

                            for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                                this.setState(624);
                                this.match(5);
                                this.setState(625);
                                this.expr(0);
                                this.setState(630);
                                this._errHandler.sync(this);
                            }

                            this.setState(631);
                            this.match(4);
                            this.setState(646);
                            this._errHandler.sync(this);

                            for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                                this.setState(632);
                                this.match(5);
                                this.setState(633);
                                this.match(3);
                                this.setState(634);
                                this.expr(0);
                                this.setState(639);
                                this._errHandler.sync(this);

                                for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                                    this.setState(635);
                                    this.match(5);
                                    this.setState(636);
                                    this.expr(0);
                                    this.setState(641);
                                    this._errHandler.sync(this);
                                }

                                this.setState(642);
                                this.match(4);
                                this.setState(648);
                                this._errHandler.sync(this);
                            }
                            break;
                        case 2:
                            this.setState(649);
                            this.select_stmt();
                            break;
                        case 3:
                            this.setState(650);
                            this.match(58);
                            this.setState(651);
                            this.match(144);
                    }

                    this.setState(655);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 109) {
                        this.setState(654);
                        this.upsert_clause();
                    }
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Upsert_clauseContext upsert_clause() throws RecognitionException {
        SQLiteParser.Upsert_clauseContext _localctx = new SQLiteParser.Upsert_clauseContext(this._ctx, this.getState());
        this.enterRule(_localctx, 44, 22);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(657);
            this.match(109);
            this.setState(658);
            this.match(50);
            this.setState(673);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 3) {
                this.setState(659);
                this.match(3);
                this.setState(660);
                this.indexed_column();
                this.setState(665);
                this._errHandler.sync(this);

                for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                    this.setState(661);
                    this.match(5);
                    this.setState(662);
                    this.indexed_column();
                    this.setState(667);
                    this._errHandler.sync(this);
                }

                this.setState(668);
                this.match(4);
                this.setState(671);
                this._errHandler.sync(this);
                _la = this._input.LA(1);
                if (_la == 148) {
                    this.setState(669);
                    this.match(148);
                    this.setState(670);
                    this.expr(0);
                }
            }

            this.setState(701);
            this._errHandler.sync(this);
            switch(this._input.LA(1)) {
                case 152:
                    this.setState(675);
                    this.match(152);
                    break;
                case 153:
                    this.setState(676);
                    this.match(153);
                    this.setState(677);
                    this.match(131);
                    this.setState(680);
                    this._errHandler.sync(this);
                    switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 81, this._ctx)) {
                        case 1:
                            this.setState(678);
                            this.column_name();
                            break;
                        case 2:
                            this.setState(679);
                            this.column_name_list();
                    }

                    this.setState(682);
                    this.match(6);
                    this.setState(683);
                    this.expr(0);
                    this.setState(694);
                    this._errHandler.sync(this);

                    for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                        this.setState(684);
                        this.match(5);
                        this.setState(687);
                        this._errHandler.sync(this);
                        switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 82, this._ctx)) {
                            case 1:
                                this.setState(685);
                                this.column_name();
                                break;
                            case 2:
                                this.setState(686);
                                this.column_name_list();
                        }

                        this.setState(689);
                        this.match(6);
                        this.setState(690);
                        this.expr(0);
                        this.setState(696);
                        this._errHandler.sync(this);
                    }

                    this.setState(699);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 148) {
                        this.setState(697);
                        this.match(148);
                        this.setState(698);
                        this.expr(0);
                    }
                    break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Pragma_stmtContext pragma_stmt() throws RecognitionException {
        SQLiteParser.Pragma_stmtContext _localctx = new SQLiteParser.Pragma_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 46, 23);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(703);
            this.match(114);
            this.setState(707);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 86, this._ctx)) {
                case 1:
                    this.setState(704);
                    this.schema_name();
                    this.setState(705);
                    this.match(2);
                default:
                    this.setState(709);
                    this.pragma_name();
                    this.setState(716);
                    this._errHandler.sync(this);
                    switch(this._input.LA(1)) {
                        case -1:
                        case 1:
                        case 32:
                        case 33:
                        case 37:
                        case 40:
                        case 49:
                        case 52:
                        case 61:
                        case 63:
                        case 65:
                        case 68:
                        case 73:
                        case 90:
                        case 114:
                        case 121:
                        case 122:
                        case 124:
                        case 127:
                        case 129:
                        case 130:
                        case 141:
                        case 143:
                        case 144:
                        case 149:
                        case 162:
                        default:
                            break;
                        case 3:
                            this.setState(712);
                            this.match(3);
                            this.setState(713);
                            this.pragma_value();
                            this.setState(714);
                            this.match(4);
                            break;
                        case 6:
                            this.setState(710);
                            this.match(6);
                            this.setState(711);
                            this.pragma_value();
                    }
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Reindex_stmtContext reindex_stmt() throws RecognitionException {
        SQLiteParser.Reindex_stmtContext _localctx = new SQLiteParser.Reindex_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 48, 24);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(718);
            this.match(121);
            this.setState(729);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 90, this._ctx)) {
                case 1:
                    this.setState(719);
                    this.collation_name();
                    break;
                case 2:
                    this.setState(723);
                    this._errHandler.sync(this);
                    switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 88, this._ctx)) {
                        case 1:
                            this.setState(720);
                            this.schema_name();
                            this.setState(721);
                            this.match(2);
                        default:
                            this.setState(727);
                            this._errHandler.sync(this);
                            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 89, this._ctx)) {
                                case 1:
                                    this.setState(725);
                                    this.table_name();
                                    break;
                                case 2:
                                    this.setState(726);
                                    this.index_name();
                            }
                    }
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Release_stmtContext release_stmt() throws RecognitionException {
        SQLiteParser.Release_stmtContext _localctx = new SQLiteParser.Release_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 50, 25);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(731);
            this.match(122);
            this.setState(733);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 91, this._ctx)) {
                case 1:
                    this.setState(732);
                    this.match(129);
                default:
                    this.setState(735);
                    this.savepoint_name();
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Rollback_stmtContext rollback_stmt() throws RecognitionException {
        SQLiteParser.Rollback_stmtContext _localctx = new SQLiteParser.Rollback_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 52, 26);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(737);
            this.match(127);
            this.setState(742);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 137) {
                this.setState(738);
                this.match(137);
                this.setState(740);
                this._errHandler.sync(this);
                switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 92, this._ctx)) {
                    case 1:
                        this.setState(739);
                        this.transaction_name();
                }
            }

            this.setState(749);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 136) {
                this.setState(744);
                this.match(136);
                this.setState(746);
                this._errHandler.sync(this);
                switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 94, this._ctx)) {
                    case 1:
                        this.setState(745);
                        this.match(129);
                    default:
                        this.setState(748);
                        this.savepoint_name();
                }
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Savepoint_stmtContext savepoint_stmt() throws RecognitionException {
        SQLiteParser.Savepoint_stmtContext _localctx = new SQLiteParser.Savepoint_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 54, 27);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(751);
            this.match(129);
            this.setState(752);
            this.savepoint_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Select_stmtContext select_stmt() throws RecognitionException {
        SQLiteParser.Select_stmtContext _localctx = new SQLiteParser.Select_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 56, 28);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(755);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 149) {
                this.setState(754);
                this.with_clause();
            }

            this.setState(757);
            this.select_or_values();
            this.setState(763);
            this._errHandler.sync(this);

            for(_la = this._input.LA(1); _la == 70 || _la == 92 || _la == 139; _la = this._input.LA(1)) {
                this.setState(758);
                this.compound_operator();
                this.setState(759);
                this.select_or_values();
                this.setState(765);
                this._errHandler.sync(this);
            }

            this.setState(767);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 111) {
                this.setState(766);
                this.order_clause();
            }

            this.setState(770);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 100) {
                this.setState(769);
                this.limit_clause();
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Select_or_valuesContext select_or_values() throws RecognitionException {
        SQLiteParser.Select_or_valuesContext _localctx = new SQLiteParser.Select_or_valuesContext(this._ctx, this.getState());
        this.enterRule(_localctx, 58, 29);

        try {
            this.setState(846);
            this._errHandler.sync(this);
            int _la;
            switch(this._input.LA(1)) {
                case 130:
                    this.enterOuterAlt(_localctx, 1);
                    this.setState(772);
                    this.match(130);
                    this.setState(774);
                    this._errHandler.sync(this);
                    switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 100, this._ctx)) {
                        case 1:
                            this.setState(773);
                            _la = this._input.LA(1);
                            if (_la != 31 && _la != 64) {
                                this._errHandler.recoverInline(this);
                            } else {
                                if (this._input.LA(1) == -1) {
                                    this.matchedEOF = true;
                                }

                                this._errHandler.reportMatch(this);
                                this.consume();
                            }
                    }

                    this.setState(776);
                    this.result_column();
                    this.setState(781);
                    this._errHandler.sync(this);

                    for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                        this.setState(777);
                        this.match(5);
                        this.setState(778);
                        this.result_column();
                        this.setState(783);
                        this._errHandler.sync(this);
                    }

                    this.setState(796);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 77) {
                        this.setState(784);
                        this.match(77);
                        this.setState(794);
                        this._errHandler.sync(this);
                        label178:
                        switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 103, this._ctx)) {
                            case 1:
                                this.setState(785);
                                this.table_or_subquery();
                                this.setState(790);
                                this._errHandler.sync(this);
                                _la = this._input.LA(1);

                                while(true) {
                                    if (_la != 5) {
                                        break label178;
                                    }

                                    this.setState(786);
                                    this.match(5);
                                    this.setState(787);
                                    this.table_or_subquery();
                                    this.setState(792);
                                    this._errHandler.sync(this);
                                    _la = this._input.LA(1);
                                }
                            case 2:
                                this.setState(793);
                                this.join_clause();
                        }
                    }

                    this.setState(800);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 148) {
                        this.setState(798);
                        this.match(148);
                        this.setState(799);
                        this.expr(0);
                    }

                    this.setState(816);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 80) {
                        this.setState(802);
                        this.match(80);
                        this.setState(803);
                        this.match(42);
                        this.setState(804);
                        this.expr(0);
                        this.setState(809);
                        this._errHandler.sync(this);

                        for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                            this.setState(805);
                            this.match(5);
                            this.setState(806);
                            this.expr(0);
                            this.setState(811);
                            this._errHandler.sync(this);
                        }

                        this.setState(814);
                        this._errHandler.sync(this);
                        _la = this._input.LA(1);
                        if (_la == 81) {
                            this.setState(812);
                            this.match(81);
                            this.setState(813);
                            this.expr(0);
                        }
                    }
                    break;
                case 144:
                    this.enterOuterAlt(_localctx, 2);
                    this.setState(818);
                    this.match(144);
                    this.setState(819);
                    this.match(3);
                    this.setState(820);
                    this.expr(0);
                    this.setState(825);
                    this._errHandler.sync(this);

                    for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                        this.setState(821);
                        this.match(5);
                        this.setState(822);
                        this.expr(0);
                        this.setState(827);
                        this._errHandler.sync(this);
                    }

                    this.setState(828);
                    this.match(4);
                    this.setState(843);
                    this._errHandler.sync(this);

                    for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                        this.setState(829);
                        this.match(5);
                        this.setState(830);
                        this.match(3);
                        this.setState(831);
                        this.expr(0);
                        this.setState(836);
                        this._errHandler.sync(this);

                        for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                            this.setState(832);
                            this.match(5);
                            this.setState(833);
                            this.expr(0);
                            this.setState(838);
                            this._errHandler.sync(this);
                        }

                        this.setState(839);
                        this.match(4);
                        this.setState(845);
                        this._errHandler.sync(this);
                    }

                    return _localctx;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Update_stmtContext update_stmt() throws RecognitionException {
        SQLiteParser.Update_stmtContext _localctx = new SQLiteParser.Update_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 60, 30);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(849);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 149) {
                this.setState(848);
                this.with_clause();
            }

            this.setState(851);
            this.match(141);
            this.setState(862);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 114, this._ctx)) {
                case 1:
                    this.setState(852);
                    this.match(110);
                    this.setState(853);
                    this.match(127);
                    break;
                case 2:
                    this.setState(854);
                    this.match(110);
                    this.setState(855);
                    this.match(27);
                    break;
                case 3:
                    this.setState(856);
                    this.match(110);
                    this.setState(857);
                    this.match(124);
                    break;
                case 4:
                    this.setState(858);
                    this.match(110);
                    this.setState(859);
                    this.match(74);
                    break;
                case 5:
                    this.setState(860);
                    this.match(110);
                    this.setState(861);
                    this.match(83);
            }

            this.setState(864);
            this.qualified_table_name();
            this.setState(865);
            this.match(131);
            this.setState(868);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 115, this._ctx)) {
                case 1:
                    this.setState(866);
                    this.column_name();
                    break;
                case 2:
                    this.setState(867);
                    this.column_name_list();
            }

            this.setState(870);
            this.match(6);
            this.setState(871);
            this.expr(0);
            this.setState(882);
            this._errHandler.sync(this);

            for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                this.setState(872);
                this.match(5);
                this.setState(875);
                this._errHandler.sync(this);
                switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 116, this._ctx)) {
                    case 1:
                        this.setState(873);
                        this.column_name();
                        break;
                    case 2:
                        this.setState(874);
                        this.column_name_list();
                }

                this.setState(877);
                this.match(6);
                this.setState(878);
                this.expr(0);
                this.setState(884);
                this._errHandler.sync(this);
            }

            this.setState(887);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 148) {
                this.setState(885);
                this.match(148);
                this.setState(886);
                this.expr(0);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Update_stmt_limitedContext update_stmt_limited() throws RecognitionException {
        SQLiteParser.Update_stmt_limitedContext _localctx = new SQLiteParser.Update_stmt_limitedContext(this._ctx, this.getState());
        this.enterRule(_localctx, 62, 31);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(890);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 149) {
                this.setState(889);
                this.with_clause();
            }

            this.setState(892);
            this.match(141);
            this.setState(903);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 120, this._ctx)) {
                case 1:
                    this.setState(893);
                    this.match(110);
                    this.setState(894);
                    this.match(127);
                    break;
                case 2:
                    this.setState(895);
                    this.match(110);
                    this.setState(896);
                    this.match(27);
                    break;
                case 3:
                    this.setState(897);
                    this.match(110);
                    this.setState(898);
                    this.match(124);
                    break;
                case 4:
                    this.setState(899);
                    this.match(110);
                    this.setState(900);
                    this.match(74);
                    break;
                case 5:
                    this.setState(901);
                    this.match(110);
                    this.setState(902);
                    this.match(83);
            }

            this.setState(905);
            this.qualified_table_name();
            this.setState(906);
            this.match(131);
            this.setState(909);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 121, this._ctx)) {
                case 1:
                    this.setState(907);
                    this.column_name();
                    break;
                case 2:
                    this.setState(908);
                    this.column_name_list();
            }

            this.setState(911);
            this.match(6);
            this.setState(912);
            this.expr(0);
            this.setState(923);
            this._errHandler.sync(this);

            for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                this.setState(913);
                this.match(5);
                this.setState(916);
                this._errHandler.sync(this);
                switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 122, this._ctx)) {
                    case 1:
                        this.setState(914);
                        this.column_name();
                        break;
                    case 2:
                        this.setState(915);
                        this.column_name_list();
                }

                this.setState(918);
                this.match(6);
                this.setState(919);
                this.expr(0);
                this.setState(925);
                this._errHandler.sync(this);
            }

            this.setState(928);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 148) {
                this.setState(926);
                this.match(148);
                this.setState(927);
                this.expr(0);
            }

            this.setState(934);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 100 || _la == 111) {
                this.setState(931);
                this._errHandler.sync(this);
                _la = this._input.LA(1);
                if (_la == 111) {
                    this.setState(930);
                    this.order_clause();
                }

                this.setState(933);
                this.limit_clause();
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Vacuum_stmtContext vacuum_stmt() throws RecognitionException {
        SQLiteParser.Vacuum_stmtContext _localctx = new SQLiteParser.Vacuum_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 64, 32);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(936);
            this.match(143);
            this.setState(938);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 127, this._ctx)) {
                case 1:
                    this.setState(937);
                    this.schema_name();
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Column_defContext column_def() throws RecognitionException {
        SQLiteParser.Column_defContext _localctx = new SQLiteParser.Column_defContext(this._ctx, this.getState());
        this.enterRule(_localctx, 66, 33);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(940);
            this.column_name();
            this.setState(942);
            this._errHandler.sync(this);
            int _la;
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 128, this._ctx)) {
                case 1:
                    this.setState(941);
                    this.type_name();
                default:
                    this.setState(947);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
            }

            while((_la & -64) == 0 && (1L << _la & 290693282197929984L) != 0L || (_la - 104 & -64) == 0 && (1L << _la - 104 & 68719511557L) != 0L) {
                this.setState(944);
                this.column_constraint();
                this.setState(949);
                this._errHandler.sync(this);
                _la = this._input.LA(1);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Type_nameContext type_name() throws RecognitionException {
        SQLiteParser.Type_nameContext _localctx = new SQLiteParser.Type_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 68, 34);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(951);
            this._errHandler.sync(this);
            int _alt = 2;

            do {
                switch(_alt) {
                    case 2:
                        this.setState(950);
                        this.name();
                        this.setState(953);
                        this._errHandler.sync(this);
                        _alt = ((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 130, this._ctx);
                        break;
                    default:
                        throw new NoViableAltException(this);
                }
            } while(_alt != 1 && _alt != 0);

            this.setState(965);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 131, this._ctx)) {
                case 1:
                    this.setState(955);
                    this.match(3);
                    this.setState(956);
                    this.signed_number();
                    this.setState(957);
                    this.match(4);
                    break;
                case 2:
                    this.setState(959);
                    this.match(3);
                    this.setState(960);
                    this.signed_number();
                    this.setState(961);
                    this.match(5);
                    this.setState(962);
                    this.signed_number();
                    this.setState(963);
                    this.match(4);
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Column_constraintContext column_constraint() throws RecognitionException {
        SQLiteParser.Column_constraintContext _localctx = new SQLiteParser.Column_constraintContext(this._ctx, this.getState());
        this.enterRule(_localctx, 70, 35);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(969);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 51) {
                this.setState(967);
                this.match(51);
                this.setState(968);
                this.name();
            }

            this.setState(1004);
            this._errHandler.sync(this);
            switch(this._input.LA(1)) {
                case 46:
                    this.setState(987);
                    this.match(46);
                    this.setState(988);
                    this.match(3);
                    this.setState(989);
                    this.expr(0);
                    this.setState(990);
                    this.match(4);
                    break;
                case 47:
                    this.setState(1001);
                    this.match(47);
                    this.setState(1002);
                    this.collation_name();
                    break;
                case 58:
                    this.setState(992);
                    this.match(58);
                    this.setState(999);
                    this._errHandler.sync(this);
                    switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 136, this._ctx)) {
                        case 1:
                            this.setState(993);
                            this.signed_number();
                            return _localctx;
                        case 2:
                            this.setState(994);
                            this.literal_value();
                            return _localctx;
                        case 3:
                            this.setState(995);
                            this.match(3);
                            this.setState(996);
                            this.expr(0);
                            this.setState(997);
                            this.match(4);
                            return _localctx;
                        default:
                            return _localctx;
                    }
                case 104:
                case 106:
                    this.setState(981);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 104) {
                        this.setState(980);
                        this.match(104);
                    }

                    this.setState(983);
                    this.match(106);
                    this.setState(984);
                    this.conflict_clause();
                    break;
                case 115:
                    this.setState(971);
                    this.match(115);
                    this.setState(972);
                    this.match(97);
                    this.setState(974);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 36 || _la == 62) {
                        this.setState(973);
                        _la = this._input.LA(1);
                        if (_la != 36 && _la != 62) {
                            this._errHandler.recoverInline(this);
                        } else {
                            if (this._input.LA(1) == -1) {
                                this.matchedEOF = true;
                            }

                            this._errHandler.reportMatch(this);
                            this.consume();
                        }
                    }

                    this.setState(976);
                    this.conflict_clause();
                    this.setState(978);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 38) {
                        this.setState(977);
                        this.match(38);
                    }
                    break;
                case 119:
                    this.setState(1003);
                    this.foreign_key_clause();
                    break;
                case 140:
                    this.setState(985);
                    this.match(140);
                    this.setState(986);
                    this.conflict_clause();
                    break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Conflict_clauseContext conflict_clause() throws RecognitionException {
        SQLiteParser.Conflict_clauseContext _localctx = new SQLiteParser.Conflict_clauseContext(this._ctx, this.getState());
        this.enterRule(_localctx, 72, 36);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1009);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 109) {
                this.setState(1006);
                this.match(109);
                this.setState(1007);
                this.match(50);
                this.setState(1008);
                _la = this._input.LA(1);
                if (_la != 27 && ((_la - 74 & -64) != 0 || (1L << _la - 74 & 10133099161584129L) == 0L)) {
                    this._errHandler.recoverInline(this);
                } else {
                    if (this._input.LA(1) == -1) {
                        this.matchedEOF = true;
                    }

                    this._errHandler.reportMatch(this);
                    this.consume();
                }
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.ExprContext expr() throws RecognitionException {
        return this.expr(0);
    }

    private SQLiteParser.ExprContext expr(int _p) throws RecognitionException {
        ParserRuleContext _parentctx = this._ctx;
        int _parentState = this.getState();
        SQLiteParser.ExprContext _localctx = new SQLiteParser.ExprContext(this._ctx, _parentState);
        int _startState = 74;
        this.enterRecursionRule(_localctx, 74, 37, _p);

        try {
            int _la;
            this.enterOuterAlt(_localctx, 1);
            this.setState(1094);
            this._errHandler.sync(this);
            label430:
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 150, this._ctx)) {
                case 1:
                    this.setState(1012);
                    this.literal_value();
                    break;
                case 2:
                    this.setState(1013);
                    this.match(156);
                    break;
                case 3:
                    this.setState(1022);
                    this._errHandler.sync(this);
                    switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 140, this._ctx)) {
                        case 1:
                            this.setState(1017);
                            this._errHandler.sync(this);
                            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 139, this._ctx)) {
                                case 1:
                                    this.setState(1014);
                                    this.schema_name();
                                    this.setState(1015);
                                    this.match(2);
                                default:
                                    this.setState(1019);
                                    this.table_name();
                                    this.setState(1020);
                                    this.match(2);
                            }
                        default:
                            this.setState(1024);
                            this.column_name();
                            break label430;
                    }
                case 4:
                    this.setState(1025);
                    this.unary_operator();
                    this.setState(1026);
                    this.expr(14);
                    break;
                case 5:
                    this.setState(1028);
                    this.function_name();
                    this.setState(1029);
                    this.match(3);
                    this.setState(1042);
                    this._errHandler.sync(this);
                    switch(this._input.LA(1)) {
                        case 3:
                        case 8:
                        case 9:
                        case 10:
                        case 25:
                        case 26:
                        case 27:
                        case 28:
                        case 29:
                        case 30:
                        case 31:
                        case 32:
                        case 33:
                        case 34:
                        case 35:
                        case 36:
                        case 37:
                        case 38:
                        case 39:
                        case 40:
                        case 41:
                        case 42:
                        case 43:
                        case 44:
                        case 45:
                        case 46:
                        case 47:
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57:
                        case 58:
                        case 59:
                        case 60:
                        case 61:
                        case 62:
                        case 63:
                        case 64:
                        case 65:
                        case 66:
                        case 67:
                        case 68:
                        case 69:
                        case 70:
                        case 71:
                        case 72:
                        case 73:
                        case 74:
                        case 75:
                        case 76:
                        case 77:
                        case 78:
                        case 79:
                        case 80:
                        case 81:
                        case 82:
                        case 83:
                        case 84:
                        case 85:
                        case 86:
                        case 87:
                        case 88:
                        case 89:
                        case 90:
                        case 91:
                        case 92:
                        case 93:
                        case 94:
                        case 95:
                        case 96:
                        case 97:
                        case 98:
                        case 99:
                        case 100:
                        case 101:
                        case 102:
                        case 103:
                        case 104:
                        case 105:
                        case 106:
                        case 107:
                        case 108:
                        case 109:
                        case 110:
                        case 111:
                        case 112:
                        case 113:
                        case 114:
                        case 115:
                        case 116:
                        case 117:
                        case 118:
                        case 119:
                        case 120:
                        case 121:
                        case 122:
                        case 123:
                        case 124:
                        case 125:
                        case 126:
                        case 127:
                        case 128:
                        case 129:
                        case 130:
                        case 131:
                        case 132:
                        case 133:
                        case 134:
                        case 135:
                        case 136:
                        case 137:
                        case 138:
                        case 139:
                        case 140:
                        case 141:
                        case 142:
                        case 143:
                        case 144:
                        case 145:
                        case 146:
                        case 147:
                        case 148:
                        case 149:
                        case 150:
                        case 154:
                        case 155:
                        case 156:
                        case 157:
                        case 158:
                            this.setState(1031);
                            this._errHandler.sync(this);
                            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 141, this._ctx)) {
                                case 1:
                                    this.setState(1030);
                                    this.match(64);
                                default:
                                    this.setState(1033);
                                    this.expr(0);
                                    this.setState(1038);
                                    this._errHandler.sync(this);
                                    _la = this._input.LA(1);
                            }

                            while(_la == 5) {
                                this.setState(1034);
                                this.match(5);
                                this.setState(1035);
                                this.expr(0);
                                this.setState(1040);
                                this._errHandler.sync(this);
                                _la = this._input.LA(1);
                            }
                        case 4:
                        case 5:
                        case 6:
                        case 11:
                        case 12:
                        case 13:
                        case 14:
                        case 15:
                        case 16:
                        case 17:
                        case 18:
                        case 19:
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                        case 151:
                        case 152:
                        case 153:
                        default:
                            break;
                        case 7:
                            this.setState(1041);
                            this.match(7);
                    }

                    this.setState(1044);
                    this.match(4);
                    break;
                case 6:
                    this.setState(1046);
                    this.match(3);
                    this.setState(1047);
                    this.expr(0);
                    this.setState(1052);
                    this._errHandler.sync(this);

                    for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                        this.setState(1048);
                        this.match(5);
                        this.setState(1049);
                        this.expr(0);
                        this.setState(1054);
                        this._errHandler.sync(this);
                    }

                    this.setState(1055);
                    this.match(4);
                    break;
                case 7:
                    this.setState(1057);
                    this.match(45);
                    this.setState(1058);
                    this.match(3);
                    this.setState(1059);
                    this.expr(0);
                    this.setState(1060);
                    this.match(35);
                    this.setState(1061);
                    this.type_name();
                    this.setState(1062);
                    this.match(4);
                    break;
                case 8:
                    this.setState(1068);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 72 || _la == 104) {
                        this.setState(1065);
                        this._errHandler.sync(this);
                        _la = this._input.LA(1);
                        if (_la == 104) {
                            this.setState(1064);
                            this.match(104);
                        }

                        this.setState(1067);
                        this.match(72);
                    }

                    this.setState(1070);
                    this.match(3);
                    this.setState(1071);
                    this.select_stmt();
                    this.setState(1072);
                    this.match(4);
                    break;
                case 9:
                    this.setState(1074);
                    this.match(44);
                    this.setState(1076);
                    this._errHandler.sync(this);
                    switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 147, this._ctx)) {
                        case 1:
                            this.setState(1075);
                            this.expr(0);
                        default:
                            this.setState(1083);
                            this._errHandler.sync(this);
                            _la = this._input.LA(1);
                    }

                    do {
                        this.setState(1078);
                        this.match(147);
                        this.setState(1079);
                        this.expr(0);
                        this.setState(1080);
                        this.match(135);
                        this.setState(1081);
                        this.expr(0);
                        this.setState(1085);
                        this._errHandler.sync(this);
                        _la = this._input.LA(1);
                    } while(_la == 147);

                    this.setState(1089);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 67) {
                        this.setState(1087);
                        this.match(67);
                        this.setState(1088);
                        this.expr(0);
                    }

                    this.setState(1091);
                    this.match(68);
                    break;
                case 10:
                    this.setState(1093);
                    this.raise_function();
            }

            this._ctx.stop = this._input.LT(-1);
            this.setState(1182);
            this._errHandler.sync(this);

            for(int _alt = ((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 165, this._ctx); _alt != 2 && _alt != 0; _alt = ((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 165, this._ctx)) {
                if (_alt == 1) {
                    if (this._parseListeners != null) {
                        this.triggerExitRuleEvent();
                    }

                    this.setState(1180);
                    this._errHandler.sync(this);
                    label494:
                    switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 164, this._ctx)) {
                        case 1:
                            _localctx = new SQLiteParser.ExprContext(_parentctx, _parentState);
                            this.pushNewRecursionContext(_localctx, _startState, 37);
                            this.setState(1096);
                            if (!this.precpred(this._ctx, 13)) {
                                throw new FailedPredicateException(this, "precpred(_ctx, 13)");
                            }

                            this.setState(1097);
                            this.binary_operator();
                            this.setState(1098);
                            this.expr(14);
                            break;
                        case 2:
                            _localctx = new SQLiteParser.ExprContext(_parentctx, _parentState);
                            this.pushNewRecursionContext(_localctx, _startState, 37);
                            this.setState(1100);
                            if (!this.precpred(this._ctx, 6)) {
                                throw new FailedPredicateException(this, "precpred(_ctx, 6)");
                            }

                            this.setState(1101);
                            this.match(94);
                            this.setState(1103);
                            this._errHandler.sync(this);
                            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 151, this._ctx)) {
                                case 1:
                                    this.setState(1102);
                                    this.match(104);
                                default:
                                    this.setState(1105);
                                    this.expr(7);
                                    break label494;
                            }
                        case 3:
                            _localctx = new SQLiteParser.ExprContext(_parentctx, _parentState);
                            this.pushNewRecursionContext(_localctx, _startState, 37);
                            this.setState(1106);
                            if (!this.precpred(this._ctx, 5)) {
                                throw new FailedPredicateException(this, "precpred(_ctx, 5)");
                            }

                            this.setState(1108);
                            this._errHandler.sync(this);
                            _la = this._input.LA(1);
                            if (_la == 104) {
                                this.setState(1107);
                                this.match(104);
                            }

                            this.setState(1110);
                            this.match(41);
                            this.setState(1111);
                            this.expr(0);
                            this.setState(1112);
                            this.match(34);
                            this.setState(1113);
                            this.expr(6);
                            break;
                        case 4:
                            _localctx = new SQLiteParser.ExprContext(_parentctx, _parentState);
                            this.pushNewRecursionContext(_localctx, _startState, 37);
                            this.setState(1115);
                            if (!this.precpred(this._ctx, 9)) {
                                throw new FailedPredicateException(this, "precpred(_ctx, 9)");
                            }

                            this.setState(1116);
                            this.match(47);
                            this.setState(1117);
                            this.collation_name();
                            break;
                        case 5:
                            _localctx = new SQLiteParser.ExprContext(_parentctx, _parentState);
                            this.pushNewRecursionContext(_localctx, _startState, 37);
                            this.setState(1118);
                            if (!this.precpred(this._ctx, 8)) {
                                throw new FailedPredicateException(this, "precpred(_ctx, 8)");
                            }

                            this.setState(1120);
                            this._errHandler.sync(this);
                            _la = this._input.LA(1);
                            if (_la == 104) {
                                this.setState(1119);
                                this.match(104);
                            }

                            this.setState(1122);
                            _la = this._input.LA(1);
                            if ((_la - 79 & -64) == 0 && (1L << _la - 79 & 2199028498433L) != 0L) {
                                if (this._input.LA(1) == -1) {
                                    this.matchedEOF = true;
                                }

                                this._errHandler.reportMatch(this);
                                this.consume();
                            } else {
                                this._errHandler.recoverInline(this);
                            }

                            this.setState(1123);
                            this.expr(0);
                            this.setState(1126);
                            this._errHandler.sync(this);
                            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 154, this._ctx)) {
                                case 1:
                                    this.setState(1124);
                                    this.match(69);
                                    this.setState(1125);
                                    this.expr(0);
                                default:
                                    break label494;
                            }
                        case 6:
                            _localctx = new SQLiteParser.ExprContext(_parentctx, _parentState);
                            this.pushNewRecursionContext(_localctx, _startState, 37);
                            this.setState(1128);
                            if (!this.precpred(this._ctx, 7)) {
                                throw new FailedPredicateException(this, "precpred(_ctx, 7)");
                            }

                            this.setState(1133);
                            this._errHandler.sync(this);
                            switch(this._input.LA(1)) {
                                case 95:
                                    this.setState(1129);
                                    this.match(95);
                                    break label494;
                                case 104:
                                    this.setState(1131);
                                    this.match(104);
                                    this.setState(1132);
                                    this.match(106);
                                    break label494;
                                case 105:
                                    this.setState(1130);
                                    this.match(105);
                                    break label494;
                                default:
                                    throw new NoViableAltException(this);
                            }
                        case 7:
                            _localctx = new SQLiteParser.ExprContext(_parentctx, _parentState);
                            this.pushNewRecursionContext(_localctx, _startState, 37);
                            this.setState(1135);
                            if (!this.precpred(this._ctx, 4)) {
                                throw new FailedPredicateException(this, "precpred(_ctx, 4)");
                            }

                            this.setState(1137);
                            this._errHandler.sync(this);
                            _la = this._input.LA(1);
                            if (_la == 104) {
                                this.setState(1136);
                                this.match(104);
                            }

                            this.setState(1139);
                            this.match(85);
                            this.setState(1178);
                            this._errHandler.sync(this);
                            label488:
                            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 163, this._ctx)) {
                                case 1:
                                    this.setState(1140);
                                    this.match(3);
                                    this.setState(1150);
                                    this._errHandler.sync(this);
                                    switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 158, this._ctx)) {
                                        case 1:
                                            this.setState(1141);
                                            this.select_stmt();
                                            break;
                                        case 2:
                                            this.setState(1142);
                                            this.expr(0);
                                            this.setState(1147);
                                            this._errHandler.sync(this);

                                            for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                                                this.setState(1143);
                                                this.match(5);
                                                this.setState(1144);
                                                this.expr(0);
                                                this.setState(1149);
                                                this._errHandler.sync(this);
                                            }
                                    }

                                    this.setState(1152);
                                    this.match(4);
                                    break;
                                case 2:
                                    this.setState(1156);
                                    this._errHandler.sync(this);
                                    switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 159, this._ctx)) {
                                        case 1:
                                            this.setState(1153);
                                            this.schema_name();
                                            this.setState(1154);
                                            this.match(2);
                                        default:
                                            this.setState(1158);
                                            this.table_name();
                                            break label488;
                                    }
                                case 3:
                                    this.setState(1162);
                                    this._errHandler.sync(this);
                                    switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 160, this._ctx)) {
                                        case 1:
                                            this.setState(1159);
                                            this.schema_name();
                                            this.setState(1160);
                                            this.match(2);
                                    }

                                    this.setState(1164);
                                    this.table_function();
                                    this.setState(1165);
                                    this.match(3);
                                    this.setState(1174);
                                    this._errHandler.sync(this);
                                    _la = this._input.LA(1);
                                    if ((_la & -64) == 0 && (1L << _la & -33552632L) != 0L || (_la - 64 & -64) == 0 && (1L << _la - 64 & -1L) != 0L || (_la - 128 & -64) == 0 && (1L << _la - 128 & 2088763391L) != 0L) {
                                        this.setState(1166);
                                        this.expr(0);
                                        this.setState(1171);
                                        this._errHandler.sync(this);

                                        for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                                            this.setState(1167);
                                            this.match(5);
                                            this.setState(1168);
                                            this.expr(0);
                                            this.setState(1173);
                                            this._errHandler.sync(this);
                                        }
                                    }

                                    this.setState(1176);
                                    this.match(4);
                            }
                    }
                }

                this.setState(1184);
                this._errHandler.sync(this);
            }
        } catch (RecognitionException var12) {
            _localctx.exception = var12;
            this._errHandler.reportError(this, var12);
            this._errHandler.recover(this, var12);
        } finally {
            this.unrollRecursionContexts(_parentctx);
        }

        return _localctx;
    }

    public final SQLiteParser.Foreign_key_clauseContext foreign_key_clause() throws RecognitionException {
        SQLiteParser.Foreign_key_clauseContext _localctx = new SQLiteParser.Foreign_key_clauseContext(this._ctx, this.getState());
        this.enterRule(_localctx, 76, 38);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1185);
            this.match(119);
            this.setState(1186);
            this.foreign_table();
            this.setState(1198);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 3) {
                this.setState(1187);
                this.match(3);
                this.setState(1188);
                this.column_name();
                this.setState(1193);
                this._errHandler.sync(this);

                for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                    this.setState(1189);
                    this.match(5);
                    this.setState(1190);
                    this.column_name();
                    this.setState(1195);
                    this._errHandler.sync(this);
                }

                this.setState(1196);
                this.match(4);
            }

            this.setState(1218);
            this._errHandler.sync(this);

            for(_la = this._input.LA(1); _la == 101 || _la == 109; _la = this._input.LA(1)) {
                this.setState(1214);
                this._errHandler.sync(this);
                label168:
                switch(this._input.LA(1)) {
                    case 101:
                        this.setState(1212);
                        this.match(101);
                        this.setState(1213);
                        this.name();
                        break;
                    case 109:
                        this.setState(1200);
                        this.match(109);
                        this.setState(1201);
                        _la = this._input.LA(1);
                        if (_la != 61 && _la != 141) {
                            this._errHandler.recoverInline(this);
                        } else {
                            if (this._input.LA(1) == -1) {
                                this.matchedEOF = true;
                            }

                            this._errHandler.reportMatch(this);
                            this.consume();
                        }

                        this.setState(1210);
                        this._errHandler.sync(this);
                        switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 168, this._ctx)) {
                            case 1:
                                this.setState(1202);
                                this.match(131);
                                this.setState(1203);
                                this.match(106);
                                break label168;
                            case 2:
                                this.setState(1204);
                                this.match(131);
                                this.setState(1205);
                                this.match(58);
                                break label168;
                            case 3:
                                this.setState(1206);
                                this.match(43);
                                break label168;
                            case 4:
                                this.setState(1207);
                                this.match(125);
                                break label168;
                            case 5:
                                this.setState(1208);
                                this.match(103);
                                this.setState(1209);
                                this.match(28);
                            default:
                                break label168;
                        }
                    default:
                        throw new NoViableAltException(this);
                }

                this.setState(1220);
                this._errHandler.sync(this);
            }

            this.setState(1231);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 173, this._ctx)) {
                case 1:
                    this.setState(1222);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 104) {
                        this.setState(1221);
                        this.match(104);
                    }

                    this.setState(1224);
                    this.match(59);
                    this.setState(1229);
                    this._errHandler.sync(this);
                    switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 172, this._ctx)) {
                        case 1:
                            this.setState(1225);
                            this.match(88);
                            this.setState(1226);
                            this.match(60);
                            return _localctx;
                        case 2:
                            this.setState(1227);
                            this.match(88);
                            this.setState(1228);
                            this.match(84);
                            return _localctx;
                    }
                default:
                    return _localctx;
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Raise_functionContext raise_function() throws RecognitionException {
        SQLiteParser.Raise_functionContext _localctx = new SQLiteParser.Raise_functionContext(this._ctx, this.getState());
        this.enterRule(_localctx, 78, 39);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1233);
            this.match(117);
            this.setState(1234);
            this.match(3);
            this.setState(1239);
            this._errHandler.sync(this);
            switch(this._input.LA(1)) {
                case 27:
                case 74:
                case 127:
                    this.setState(1236);
                    int _la = this._input.LA(1);
                    if (_la != 27 && _la != 74 && _la != 127) {
                        this._errHandler.recoverInline(this);
                    } else {
                        if (this._input.LA(1) == -1) {
                            this.matchedEOF = true;
                        }

                        this._errHandler.reportMatch(this);
                        this.consume();
                    }

                    this.setState(1237);
                    this.match(5);
                    this.setState(1238);
                    this.error_message();
                    break;
                case 83:
                    this.setState(1235);
                    this.match(83);
                    break;
                default:
                    throw new NoViableAltException(this);
            }

            this.setState(1241);
            this.match(4);
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Indexed_columnContext indexed_column() throws RecognitionException {
        SQLiteParser.Indexed_columnContext _localctx = new SQLiteParser.Indexed_columnContext(this._ctx, this.getState());
        this.enterRule(_localctx, 80, 40);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1245);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 175, this._ctx)) {
                case 1:
                    this.setState(1243);
                    this.column_name();
                    break;
                case 2:
                    this.setState(1244);
                    this.expr(0);
            }

            this.setState(1249);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 47) {
                this.setState(1247);
                this.match(47);
                this.setState(1248);
                this.collation_name();
            }

            this.setState(1252);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 36 || _la == 62) {
                this.setState(1251);
                _la = this._input.LA(1);
                if (_la != 36 && _la != 62) {
                    this._errHandler.recoverInline(this);
                } else {
                    if (this._input.LA(1) == -1) {
                        this.matchedEOF = true;
                    }

                    this._errHandler.reportMatch(this);
                    this.consume();
                }
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Table_constraintContext table_constraint() throws RecognitionException {
        SQLiteParser.Table_constraintContext _localctx = new SQLiteParser.Table_constraintContext(this._ctx, this.getState());
        this.enterRule(_localctx, 82, 41);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1256);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 51) {
                this.setState(1254);
                this.match(51);
                this.setState(1255);
                this.name();
            }

            this.setState(1294);
            this._errHandler.sync(this);
            switch(this._input.LA(1)) {
                case 46:
                    this.setState(1275);
                    this.match(46);
                    this.setState(1276);
                    this.match(3);
                    this.setState(1277);
                    this.expr(0);
                    this.setState(1278);
                    this.match(4);
                    break;
                case 76:
                    this.setState(1280);
                    this.match(76);
                    this.setState(1281);
                    this.match(97);
                    this.setState(1282);
                    this.match(3);
                    this.setState(1283);
                    this.column_name();
                    this.setState(1288);
                    this._errHandler.sync(this);

                    for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                        this.setState(1284);
                        this.match(5);
                        this.setState(1285);
                        this.column_name();
                        this.setState(1290);
                        this._errHandler.sync(this);
                    }

                    this.setState(1291);
                    this.match(4);
                    this.setState(1292);
                    this.foreign_key_clause();
                    break;
                case 115:
                case 140:
                    this.setState(1261);
                    this._errHandler.sync(this);
                    switch(this._input.LA(1)) {
                        case 115:
                            this.setState(1258);
                            this.match(115);
                            this.setState(1259);
                            this.match(97);
                            break;
                        case 140:
                            this.setState(1260);
                            this.match(140);
                            break;
                        default:
                            throw new NoViableAltException(this);
                    }

                    this.setState(1263);
                    this.match(3);
                    this.setState(1264);
                    this.indexed_column();
                    this.setState(1269);
                    this._errHandler.sync(this);

                    for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                        this.setState(1265);
                        this.match(5);
                        this.setState(1266);
                        this.indexed_column();
                        this.setState(1271);
                        this._errHandler.sync(this);
                    }

                    this.setState(1272);
                    this.match(4);
                    this.setState(1273);
                    this.conflict_clause();
                    break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.With_clauseContext with_clause() throws RecognitionException {
        SQLiteParser.With_clauseContext _localctx = new SQLiteParser.With_clauseContext(this._ctx, this.getState());
        this.enterRule(_localctx, 84, 42);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1296);
            this.match(149);
            this.setState(1298);
            this._errHandler.sync(this);
            int _la;
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 183, this._ctx)) {
                case 1:
                    this.setState(1297);
                    this.match(118);
                default:
                    this.setState(1300);
                    this.common_table_expression();
                    this.setState(1305);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
            }

            while(_la == 5) {
                this.setState(1301);
                this.match(5);
                this.setState(1302);
                this.common_table_expression();
                this.setState(1307);
                this._errHandler.sync(this);
                _la = this._input.LA(1);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Common_table_expressionContext common_table_expression() throws RecognitionException {
        SQLiteParser.Common_table_expressionContext _localctx = new SQLiteParser.Common_table_expressionContext(this._ctx, this.getState());
        this.enterRule(_localctx, 86, 43);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1308);
            this.table_name();
            this.setState(1320);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 3) {
                this.setState(1309);
                this.match(3);
                this.setState(1310);
                this.column_name();
                this.setState(1315);
                this._errHandler.sync(this);

                for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                    this.setState(1311);
                    this.match(5);
                    this.setState(1312);
                    this.column_name();
                    this.setState(1317);
                    this._errHandler.sync(this);
                }

                this.setState(1318);
                this.match(4);
            }

            this.setState(1322);
            this.match(35);
            this.setState(1323);
            this.match(3);
            this.setState(1324);
            this.select_stmt();
            this.setState(1325);
            this.match(4);
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Qualified_table_nameContext qualified_table_name() throws RecognitionException {
        SQLiteParser.Qualified_table_nameContext _localctx = new SQLiteParser.Qualified_table_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 88, 44);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1330);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 187, this._ctx)) {
                case 1:
                    this.setState(1327);
                    this.schema_name();
                    this.setState(1328);
                    this.match(2);
                default:
                    this.setState(1332);
                    this.table_name();
                    this.setState(1335);
                    this._errHandler.sync(this);
                    int _la = this._input.LA(1);
                    if (_la == 35) {
                        this.setState(1333);
                        this.match(35);
                        this.setState(1334);
                        this.table_alias();
                    }

                    this.setState(1342);
                    this._errHandler.sync(this);
                    switch(this._input.LA(1)) {
                        case -1:
                        case 1:
                        case 32:
                        case 33:
                        case 37:
                        case 40:
                        case 49:
                        case 52:
                        case 61:
                        case 63:
                        case 65:
                        case 68:
                        case 73:
                        case 90:
                        case 100:
                        case 111:
                        case 114:
                        case 121:
                        case 122:
                        case 124:
                        case 127:
                        case 129:
                        case 130:
                        case 131:
                        case 141:
                        case 143:
                        case 144:
                        case 148:
                        case 149:
                        case 162:
                        default:
                            break;
                        case 87:
                            this.setState(1337);
                            this.match(87);
                            this.setState(1338);
                            this.match(42);
                            this.setState(1339);
                            this.index_name();
                            break;
                        case 104:
                            this.setState(1340);
                            this.match(104);
                            this.setState(1341);
                            this.match(87);
                    }
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Order_clauseContext order_clause() throws RecognitionException {
        SQLiteParser.Order_clauseContext _localctx = new SQLiteParser.Order_clauseContext(this._ctx, this.getState());
        this.enterRule(_localctx, 90, 45);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1344);
            this.match(111);
            this.setState(1345);
            this.match(42);
            this.setState(1346);
            this.ordering_term();
            this.setState(1351);
            this._errHandler.sync(this);

            for(int _la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                this.setState(1347);
                this.match(5);
                this.setState(1348);
                this.ordering_term();
                this.setState(1353);
                this._errHandler.sync(this);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Ordering_termContext ordering_term() throws RecognitionException {
        SQLiteParser.Ordering_termContext _localctx = new SQLiteParser.Ordering_termContext(this._ctx, this.getState());
        this.enterRule(_localctx, 92, 46);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1354);
            this.expr(0);
            this.setState(1357);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 47) {
                this.setState(1355);
                this.match(47);
                this.setState(1356);
                this.collation_name();
            }

            this.setState(1360);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 36 || _la == 62) {
                this.setState(1359);
                _la = this._input.LA(1);
                if (_la != 36 && _la != 62) {
                    this._errHandler.recoverInline(this);
                } else {
                    if (this._input.LA(1) == -1) {
                        this.matchedEOF = true;
                    }

                    this._errHandler.reportMatch(this);
                    this.consume();
                }
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Limit_clauseContext limit_clause() throws RecognitionException {
        SQLiteParser.Limit_clauseContext _localctx = new SQLiteParser.Limit_clauseContext(this._ctx, this.getState());
        this.enterRule(_localctx, 94, 47);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1362);
            this.match(100);
            this.setState(1363);
            this.expr(0);
            this.setState(1366);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 5 || _la == 108) {
                this.setState(1364);
                _la = this._input.LA(1);
                if (_la != 5 && _la != 108) {
                    this._errHandler.recoverInline(this);
                } else {
                    if (this._input.LA(1) == -1) {
                        this.matchedEOF = true;
                    }

                    this._errHandler.reportMatch(this);
                    this.consume();
                }

                this.setState(1365);
                this.expr(0);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Pragma_valueContext pragma_value() throws RecognitionException {
        SQLiteParser.Pragma_valueContext _localctx = new SQLiteParser.Pragma_valueContext(this._ctx, this.getState());
        this.enterRule(_localctx, 96, 48);

        try {
            this.setState(1372);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 194, this._ctx)) {
                case 1:
                    this.enterOuterAlt(_localctx, 1);
                    this.setState(1368);
                    this.signed_number();
                    break;
                case 2:
                    this.enterOuterAlt(_localctx, 2);
                    this.setState(1369);
                    this.name();
                    break;
                case 3:
                    this.enterOuterAlt(_localctx, 3);
                    this.setState(1370);
                    this.match(157);
                    break;
                case 4:
                    this.enterOuterAlt(_localctx, 4);
                    this.setState(1371);
                    this.boolean_literal();
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Result_columnContext result_column() throws RecognitionException {
        SQLiteParser.Result_columnContext _localctx = new SQLiteParser.Result_columnContext(this._ctx, this.getState());
        this.enterRule(_localctx, 98, 49);

        try {
            this.setState(1386);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 197, this._ctx)) {
                case 1:
                    this.enterOuterAlt(_localctx, 1);
                    this.setState(1374);
                    this.match(7);
                    break;
                case 2:
                    this.enterOuterAlt(_localctx, 2);
                    this.setState(1375);
                    this.table_name();
                    this.setState(1376);
                    this.match(2);
                    this.setState(1377);
                    this.match(7);
                    break;
                case 3:
                    this.enterOuterAlt(_localctx, 3);
                    this.setState(1379);
                    this.expr(0);
                    this.setState(1384);
                    this._errHandler.sync(this);
                    int _la = this._input.LA(1);
                    if (_la == 35 || _la == 154 || _la == 157) {
                        this.setState(1381);
                        this._errHandler.sync(this);
                        _la = this._input.LA(1);
                        if (_la == 35) {
                            this.setState(1380);
                            this.match(35);
                        }

                        this.setState(1383);
                        this.column_alias();
                    }
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Table_or_subqueryContext table_or_subquery() throws RecognitionException {
        SQLiteParser.Table_or_subqueryContext _localctx = new SQLiteParser.Table_or_subqueryContext(this._ctx, this.getState());
        this.enterRule(_localctx, 100, 50);

        try {
            this.setState(1454);
            this._errHandler.sync(this);
            int _la;
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 211, this._ctx)) {
                case 1:
                    this.enterOuterAlt(_localctx, 1);
                    this.setState(1391);
                    this._errHandler.sync(this);
                    switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 198, this._ctx)) {
                        case 1:
                            this.setState(1388);
                            this.schema_name();
                            this.setState(1389);
                            this.match(2);
                    }

                    this.setState(1393);
                    this.table_name();
                    this.setState(1398);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 3 || _la == 35 || _la == 154 || _la == 157) {
                        this.setState(1395);
                        this._errHandler.sync(this);
                        _la = this._input.LA(1);
                        if (_la == 35) {
                            this.setState(1394);
                            this.match(35);
                        }

                        this.setState(1397);
                        this.table_alias();
                    }

                    this.setState(1405);
                    this._errHandler.sync(this);
                    switch(this._input.LA(1)) {
                        case -1:
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                        case 14:
                        case 15:
                        case 16:
                        case 17:
                        case 18:
                        case 19:
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                        case 25:
                        case 26:
                        case 27:
                        case 28:
                        case 29:
                        case 30:
                        case 31:
                        case 32:
                        case 33:
                        case 34:
                        case 35:
                        case 36:
                        case 37:
                        case 38:
                        case 39:
                        case 40:
                        case 41:
                        case 42:
                        case 43:
                        case 44:
                        case 45:
                        case 46:
                        case 47:
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57:
                        case 58:
                        case 59:
                        case 60:
                        case 61:
                        case 62:
                        case 63:
                        case 64:
                        case 65:
                        case 66:
                        case 67:
                        case 68:
                        case 69:
                        case 70:
                        case 71:
                        case 72:
                        case 73:
                        case 74:
                        case 75:
                        case 76:
                        case 77:
                        case 78:
                        case 79:
                        case 80:
                        case 81:
                        case 82:
                        case 83:
                        case 84:
                        case 85:
                        case 86:
                        case 88:
                        case 89:
                        case 90:
                        case 91:
                        case 92:
                        case 93:
                        case 94:
                        case 95:
                        case 96:
                        case 97:
                        case 98:
                        case 99:
                        case 100:
                        case 101:
                        case 102:
                        case 103:
                        case 105:
                        case 106:
                        case 107:
                        case 108:
                        case 109:
                        case 110:
                        case 111:
                        case 112:
                        case 113:
                        case 114:
                        case 115:
                        case 116:
                        case 117:
                        case 118:
                        case 119:
                        case 120:
                        case 121:
                        case 122:
                        case 123:
                        case 124:
                        case 125:
                        case 126:
                        case 127:
                        case 128:
                        case 129:
                        case 130:
                        case 131:
                        case 132:
                        case 133:
                        case 134:
                        case 135:
                        case 136:
                        case 137:
                        case 138:
                        case 139:
                        case 140:
                        case 141:
                        case 142:
                        case 143:
                        case 144:
                        case 145:
                        case 146:
                        case 147:
                        case 148:
                        case 149:
                        case 150:
                        case 151:
                        case 152:
                        case 153:
                        case 154:
                        case 155:
                        case 156:
                        case 157:
                        case 158:
                        case 159:
                        case 160:
                        case 161:
                        case 162:
                        default:
                            return _localctx;
                        case 87:
                            this.setState(1400);
                            this.match(87);
                            this.setState(1401);
                            this.match(42);
                            this.setState(1402);
                            this.index_name();
                            return _localctx;
                        case 104:
                            this.setState(1403);
                            this.match(104);
                            this.setState(1404);
                            this.match(87);
                            return _localctx;
                    }
                case 2:
                    this.enterOuterAlt(_localctx, 2);
                    this.setState(1410);
                    this._errHandler.sync(this);
                    switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 202, this._ctx)) {
                        case 1:
                            this.setState(1407);
                            this.schema_name();
                            this.setState(1408);
                            this.match(2);
                    }

                    this.setState(1412);
                    this.table_function();
                    this.setState(1413);
                    this.match(3);
                    this.setState(1422);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if ((_la & -64) == 0 && (1L << _la & -33552632L) != 0L || (_la - 64 & -64) == 0 && (1L << _la - 64 & -1L) != 0L || (_la - 128 & -64) == 0 && (1L << _la - 128 & 2088763391L) != 0L) {
                        this.setState(1414);
                        this.expr(0);
                        this.setState(1419);
                        this._errHandler.sync(this);

                        for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                            this.setState(1415);
                            this.match(5);
                            this.setState(1416);
                            this.expr(0);
                            this.setState(1421);
                            this._errHandler.sync(this);
                        }
                    }

                    this.setState(1424);
                    this.match(4);
                    this.setState(1429);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 3 || _la == 35 || _la == 154 || _la == 157) {
                        this.setState(1426);
                        this._errHandler.sync(this);
                        _la = this._input.LA(1);
                        if (_la == 35) {
                            this.setState(1425);
                            this.match(35);
                        }

                        this.setState(1428);
                        this.table_alias();
                    }
                    break;
                case 3:
                    this.enterOuterAlt(_localctx, 3);
                    this.setState(1431);
                    this.match(3);
                    this.setState(1441);
                    this._errHandler.sync(this);
                    label228:
                    switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 208, this._ctx)) {
                        case 1:
                            this.setState(1432);
                            this.table_or_subquery();
                            this.setState(1437);
                            this._errHandler.sync(this);
                            _la = this._input.LA(1);

                            while(true) {
                                if (_la != 5) {
                                    break label228;
                                }

                                this.setState(1433);
                                this.match(5);
                                this.setState(1434);
                                this.table_or_subquery();
                                this.setState(1439);
                                this._errHandler.sync(this);
                                _la = this._input.LA(1);
                            }
                        case 2:
                            this.setState(1440);
                            this.join_clause();
                    }

                    this.setState(1443);
                    this.match(4);
                    break;
                case 4:
                    this.enterOuterAlt(_localctx, 4);
                    this.setState(1445);
                    this.match(3);
                    this.setState(1446);
                    this.select_stmt();
                    this.setState(1447);
                    this.match(4);
                    this.setState(1452);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 3 || _la == 35 || _la == 154 || _la == 157) {
                        this.setState(1449);
                        this._errHandler.sync(this);
                        _la = this._input.LA(1);
                        if (_la == 35) {
                            this.setState(1448);
                            this.match(35);
                        }

                        this.setState(1451);
                        this.table_alias();
                    }
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Join_clauseContext join_clause() throws RecognitionException {
        SQLiteParser.Join_clauseContext _localctx = new SQLiteParser.Join_clauseContext(this._ctx, this.getState());
        this.enterRule(_localctx, 102, 51);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1456);
            this.table_or_subquery();
            this.setState(1463);
            this._errHandler.sync(this);

            for(int _la = this._input.LA(1); _la == 5 || _la == 53 || (_la - 89 & -64) == 0 && (1L << _la - 89 & 8833L) != 0L; _la = this._input.LA(1)) {
                this.setState(1457);
                this.join_operator();
                this.setState(1458);
                this.table_or_subquery();
                this.setState(1459);
                this.join_constraint();
                this.setState(1465);
                this._errHandler.sync(this);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Join_operatorContext join_operator() throws RecognitionException {
        SQLiteParser.Join_operatorContext _localctx = new SQLiteParser.Join_operatorContext(this._ctx, this.getState());
        this.enterRule(_localctx, 104, 52);

        try {
            this.setState(1479);
            this._errHandler.sync(this);
            switch(this._input.LA(1)) {
                case 5:
                    this.enterOuterAlt(_localctx, 1);
                    this.setState(1466);
                    this.match(5);
                    break;
                case 53:
                case 89:
                case 96:
                case 98:
                case 102:
                    this.enterOuterAlt(_localctx, 2);
                    this.setState(1468);
                    this._errHandler.sync(this);
                    int _la = this._input.LA(1);
                    if (_la == 102) {
                        this.setState(1467);
                        this.match(102);
                    }

                    this.setState(1476);
                    this._errHandler.sync(this);
                    switch(this._input.LA(1)) {
                        case 53:
                            this.setState(1475);
                            this.match(53);
                            break;
                        case 89:
                            this.setState(1474);
                            this.match(89);
                        case 96:
                        default:
                            break;
                        case 98:
                            this.setState(1470);
                            this.match(98);
                            this.setState(1472);
                            this._errHandler.sync(this);
                            _la = this._input.LA(1);
                            if (_la == 112) {
                                this.setState(1471);
                                this.match(112);
                            }
                    }

                    this.setState(1478);
                    this.match(96);
                    break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Join_constraintContext join_constraint() throws RecognitionException {
        SQLiteParser.Join_constraintContext _localctx = new SQLiteParser.Join_constraintContext(this._ctx, this.getState());
        this.enterRule(_localctx, 106, 53);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1495);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 218, this._ctx)) {
                case 1:
                    this.setState(1481);
                    this.match(109);
                    this.setState(1482);
                    this.expr(0);
                    break;
                case 2:
                    this.setState(1483);
                    this.match(142);
                    this.setState(1484);
                    this.match(3);
                    this.setState(1485);
                    this.column_name();
                    this.setState(1490);
                    this._errHandler.sync(this);

                    for(int _la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                        this.setState(1486);
                        this.match(5);
                        this.setState(1487);
                        this.column_name();
                        this.setState(1492);
                        this._errHandler.sync(this);
                    }

                    this.setState(1493);
                    this.match(4);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Compound_operatorContext compound_operator() throws RecognitionException {
        SQLiteParser.Compound_operatorContext _localctx = new SQLiteParser.Compound_operatorContext(this._ctx, this.getState());
        this.enterRule(_localctx, 108, 54);

        try {
            this.setState(1502);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 219, this._ctx)) {
                case 1:
                    this.enterOuterAlt(_localctx, 1);
                    this.setState(1497);
                    this.match(139);
                    break;
                case 2:
                    this.enterOuterAlt(_localctx, 2);
                    this.setState(1498);
                    this.match(139);
                    this.setState(1499);
                    this.match(31);
                    break;
                case 3:
                    this.enterOuterAlt(_localctx, 3);
                    this.setState(1500);
                    this.match(92);
                    break;
                case 4:
                    this.enterOuterAlt(_localctx, 4);
                    this.setState(1501);
                    this.match(70);
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Signed_numberContext signed_number() throws RecognitionException {
        SQLiteParser.Signed_numberContext _localctx = new SQLiteParser.Signed_numberContext(this._ctx, this.getState());
        this.enterRule(_localctx, 110, 55);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1505);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 8 || _la == 9) {
                this.setState(1504);
                _la = this._input.LA(1);
                if (_la != 8 && _la != 9) {
                    this._errHandler.recoverInline(this);
                } else {
                    if (this._input.LA(1) == -1) {
                        this.matchedEOF = true;
                    }

                    this._errHandler.reportMatch(this);
                    this.consume();
                }
            }

            this.setState(1507);
            this.match(155);
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Literal_valueContext literal_value() throws RecognitionException {
        SQLiteParser.Literal_valueContext _localctx = new SQLiteParser.Literal_valueContext(this._ctx, this.getState());
        this.enterRule(_localctx, 112, 56);

        try {
            this.setState(1517);
            this._errHandler.sync(this);
            switch(this._input.LA(1)) {
                case 25:
                case 26:
                    this.enterOuterAlt(_localctx, 8);
                    this.setState(1516);
                    this.boolean_literal();
                    break;
                case 54:
                    this.enterOuterAlt(_localctx, 6);
                    this.setState(1514);
                    this.match(54);
                    break;
                case 55:
                    this.enterOuterAlt(_localctx, 5);
                    this.setState(1513);
                    this.match(55);
                    break;
                case 56:
                    this.enterOuterAlt(_localctx, 7);
                    this.setState(1515);
                    this.match(56);
                    break;
                case 106:
                    this.enterOuterAlt(_localctx, 4);
                    this.setState(1512);
                    this.match(106);
                    break;
                case 155:
                    this.enterOuterAlt(_localctx, 1);
                    this.setState(1509);
                    this.match(155);
                    break;
                case 157:
                    this.enterOuterAlt(_localctx, 2);
                    this.setState(1510);
                    this.match(157);
                    break;
                case 158:
                    this.enterOuterAlt(_localctx, 3);
                    this.setState(1511);
                    this.match(158);
                    break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Boolean_literalContext boolean_literal() throws RecognitionException {
        SQLiteParser.Boolean_literalContext _localctx = new SQLiteParser.Boolean_literalContext(this._ctx, this.getState());
        this.enterRule(_localctx, 114, 57);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1519);
            int _la = this._input.LA(1);
            if (_la != 25 && _la != 26) {
                this._errHandler.recoverInline(this);
            } else {
                if (this._input.LA(1) == -1) {
                    this.matchedEOF = true;
                }

                this._errHandler.reportMatch(this);
                this.consume();
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Unary_operatorContext unary_operator() throws RecognitionException {
        SQLiteParser.Unary_operatorContext _localctx = new SQLiteParser.Unary_operatorContext(this._ctx, this.getState());
        this.enterRule(_localctx, 116, 58);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1521);
            int _la = this._input.LA(1);
            if (((_la & -64) != 0 || (1L << _la & 1792L) == 0L) && _la != 104) {
                this._errHandler.recoverInline(this);
            } else {
                if (this._input.LA(1) == -1) {
                    this.matchedEOF = true;
                }

                this._errHandler.reportMatch(this);
                this.consume();
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Binary_operatorContext binary_operator() throws RecognitionException {
        SQLiteParser.Binary_operatorContext _localctx = new SQLiteParser.Binary_operatorContext(this._ctx, this.getState());
        this.enterRule(_localctx, 118, 59);

        try {
            this.setState(1531);
            this._errHandler.sync(this);
            int _la;
            switch(this._input.LA(1)) {
                case 6:
                case 22:
                case 23:
                case 24:
                    this.enterOuterAlt(_localctx, 6);
                    this.setState(1528);
                    _la = this._input.LA(1);
                    if ((_la & -64) == 0 && (1L << _la & 29360192L) != 0L) {
                        if (this._input.LA(1) == -1) {
                            this.matchedEOF = true;
                        }

                        this._errHandler.reportMatch(this);
                        this.consume();
                    } else {
                        this._errHandler.recoverInline(this);
                    }
                    break;
                case 7:
                case 12:
                case 13:
                    this.enterOuterAlt(_localctx, 2);
                    this.setState(1524);
                    _la = this._input.LA(1);
                    if ((_la & -64) == 0 && (1L << _la & 12416L) != 0L) {
                        if (this._input.LA(1) == -1) {
                            this.matchedEOF = true;
                        }

                        this._errHandler.reportMatch(this);
                        this.consume();
                    } else {
                        this._errHandler.recoverInline(this);
                    }
                    break;
                case 8:
                case 9:
                    this.enterOuterAlt(_localctx, 3);
                    this.setState(1525);
                    _la = this._input.LA(1);
                    if (_la != 8 && _la != 9) {
                        this._errHandler.recoverInline(this);
                    } else {
                        if (this._input.LA(1) == -1) {
                            this.matchedEOF = true;
                        }

                        this._errHandler.reportMatch(this);
                        this.consume();
                    }
                    break;
                case 11:
                    this.enterOuterAlt(_localctx, 1);
                    this.setState(1523);
                    this.match(11);
                    break;
                case 14:
                case 15:
                case 16:
                case 17:
                    this.enterOuterAlt(_localctx, 4);
                    this.setState(1526);
                    _la = this._input.LA(1);
                    if ((_la & -64) == 0 && (1L << _la & 245760L) != 0L) {
                        if (this._input.LA(1) == -1) {
                            this.matchedEOF = true;
                        }

                        this._errHandler.reportMatch(this);
                        this.consume();
                    } else {
                        this._errHandler.recoverInline(this);
                    }
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                    this.enterOuterAlt(_localctx, 5);
                    this.setState(1527);
                    _la = this._input.LA(1);
                    if ((_la & -64) == 0 && (1L << _la & 3932160L) != 0L) {
                        if (this._input.LA(1) == -1) {
                            this.matchedEOF = true;
                        }

                        this._errHandler.reportMatch(this);
                        this.consume();
                    } else {
                        this._errHandler.recoverInline(this);
                    }
                    break;
                case 34:
                    this.enterOuterAlt(_localctx, 7);
                    this.setState(1529);
                    this.match(34);
                    break;
                case 110:
                    this.enterOuterAlt(_localctx, 8);
                    this.setState(1530);
                    this.match(110);
                    break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Error_messageContext error_message() throws RecognitionException {
        SQLiteParser.Error_messageContext _localctx = new SQLiteParser.Error_messageContext(this._ctx, this.getState());
        this.enterRule(_localctx, 120, 60);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1533);
            this.match(157);
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Module_argumentContext module_argument() throws RecognitionException {
        SQLiteParser.Module_argumentContext _localctx = new SQLiteParser.Module_argumentContext(this._ctx, this.getState());
        this.enterRule(_localctx, 122, 61);

        try {
            this.setState(1537);
            this._errHandler.sync(this);
            switch(((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 223, this._ctx)) {
                case 1:
                    this.enterOuterAlt(_localctx, 1);
                    this.setState(1535);
                    this.expr(0);
                    break;
                case 2:
                    this.enterOuterAlt(_localctx, 2);
                    this.setState(1536);
                    this.column_def();
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Column_aliasContext column_alias() throws RecognitionException {
        SQLiteParser.Column_aliasContext _localctx = new SQLiteParser.Column_aliasContext(this._ctx, this.getState());
        this.enterRule(_localctx, 124, 62);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1539);
            int _la = this._input.LA(1);
            if (_la != 154 && _la != 157) {
                this._errHandler.recoverInline(this);
            } else {
                if (this._input.LA(1) == -1) {
                    this.matchedEOF = true;
                }

                this._errHandler.reportMatch(this);
                this.consume();
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Column_name_listContext column_name_list() throws RecognitionException {
        SQLiteParser.Column_name_listContext _localctx = new SQLiteParser.Column_name_listContext(this._ctx, this.getState());
        this.enterRule(_localctx, 126, 63);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1541);
            this.match(3);
            this.setState(1542);
            this.column_name();
            this.setState(1547);
            this._errHandler.sync(this);

            for(int _la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                this.setState(1543);
                this.match(5);
                this.setState(1544);
                this.column_name();
                this.setState(1549);
                this._errHandler.sync(this);
            }

            this.setState(1550);
            this.match(4);
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.KeywordContext keyword() throws RecognitionException {
        SQLiteParser.KeywordContext _localctx = new SQLiteParser.KeywordContext(this._ctx, this.getState());
        this.enterRule(_localctx, 128, 64);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1552);
            int _la = this._input.LA(1);
            if (((_la - 27 & -64) != 0 || (1L << _la - 27 & -1L) == 0L) && ((_la - 91 & -64) != 0 || (1L << _la - 91 & 1152921504606846975L) == 0L)) {
                this._errHandler.recoverInline(this);
            } else {
                if (this._input.LA(1) == -1) {
                    this.matchedEOF = true;
                }

                this._errHandler.reportMatch(this);
                this.consume();
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.NameContext name() throws RecognitionException {
        SQLiteParser.NameContext _localctx = new SQLiteParser.NameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 130, 65);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1554);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Function_nameContext function_name() throws RecognitionException {
        SQLiteParser.Function_nameContext _localctx = new SQLiteParser.Function_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 132, 66);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1556);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Schema_nameContext schema_name() throws RecognitionException {
        SQLiteParser.Schema_nameContext _localctx = new SQLiteParser.Schema_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 134, 67);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1558);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Table_functionContext table_function() throws RecognitionException {
        SQLiteParser.Table_functionContext _localctx = new SQLiteParser.Table_functionContext(this._ctx, this.getState());
        this.enterRule(_localctx, 136, 68);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1560);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Table_nameContext table_name() throws RecognitionException {
        SQLiteParser.Table_nameContext _localctx = new SQLiteParser.Table_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 138, 69);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1562);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Table_or_index_nameContext table_or_index_name() throws RecognitionException {
        SQLiteParser.Table_or_index_nameContext _localctx = new SQLiteParser.Table_or_index_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 140, 70);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1564);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.New_table_nameContext new_table_name() throws RecognitionException {
        SQLiteParser.New_table_nameContext _localctx = new SQLiteParser.New_table_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 142, 71);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1566);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Column_nameContext column_name() throws RecognitionException {
        SQLiteParser.Column_nameContext _localctx = new SQLiteParser.Column_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 144, 72);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1568);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Collation_nameContext collation_name() throws RecognitionException {
        SQLiteParser.Collation_nameContext _localctx = new SQLiteParser.Collation_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 146, 73);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1570);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Foreign_tableContext foreign_table() throws RecognitionException {
        SQLiteParser.Foreign_tableContext _localctx = new SQLiteParser.Foreign_tableContext(this._ctx, this.getState());
        this.enterRule(_localctx, 148, 74);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1572);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Index_nameContext index_name() throws RecognitionException {
        SQLiteParser.Index_nameContext _localctx = new SQLiteParser.Index_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 150, 75);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1574);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Trigger_nameContext trigger_name() throws RecognitionException {
        SQLiteParser.Trigger_nameContext _localctx = new SQLiteParser.Trigger_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 152, 76);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1576);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.View_nameContext view_name() throws RecognitionException {
        SQLiteParser.View_nameContext _localctx = new SQLiteParser.View_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 154, 77);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1578);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Module_nameContext module_name() throws RecognitionException {
        SQLiteParser.Module_nameContext _localctx = new SQLiteParser.Module_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 156, 78);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1580);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Pragma_nameContext pragma_name() throws RecognitionException {
        SQLiteParser.Pragma_nameContext _localctx = new SQLiteParser.Pragma_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 158, 79);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1582);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Savepoint_nameContext savepoint_name() throws RecognitionException {
        SQLiteParser.Savepoint_nameContext _localctx = new SQLiteParser.Savepoint_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 160, 80);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1584);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Table_aliasContext table_alias() throws RecognitionException {
        SQLiteParser.Table_aliasContext _localctx = new SQLiteParser.Table_aliasContext(this._ctx, this.getState());
        this.enterRule(_localctx, 162, 81);

        try {
            this.setState(1592);
            this._errHandler.sync(this);
            switch(this._input.LA(1)) {
                case 3:
                    this.enterOuterAlt(_localctx, 3);
                    this.setState(1588);
                    this.match(3);
                    this.setState(1589);
                    this.table_alias();
                    this.setState(1590);
                    this.match(4);
                    break;
                case 154:
                    this.enterOuterAlt(_localctx, 1);
                    this.setState(1586);
                    this.match(154);
                    break;
                case 157:
                    this.enterOuterAlt(_localctx, 2);
                    this.setState(1587);
                    this.match(157);
                    break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Transaction_nameContext transaction_name() throws RecognitionException {
        SQLiteParser.Transaction_nameContext _localctx = new SQLiteParser.Transaction_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 164, 82);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1594);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final SQLiteParser.Any_nameContext any_name() throws RecognitionException {
        SQLiteParser.Any_nameContext _localctx = new SQLiteParser.Any_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 166, 83);

        try {
            this.setState(1603);
            this._errHandler.sync(this);
            switch(this._input.LA(1)) {
                case 3:
                    this.enterOuterAlt(_localctx, 4);
                    this.setState(1599);
                    this.match(3);
                    this.setState(1600);
                    this.any_name();
                    this.setState(1601);
                    this.match(4);
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 151:
                case 152:
                case 153:
                case 155:
                case 156:
                default:
                    throw new NoViableAltException(this);
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                case 69:
                case 70:
                case 71:
                case 72:
                case 73:
                case 74:
                case 75:
                case 76:
                case 77:
                case 78:
                case 79:
                case 80:
                case 81:
                case 82:
                case 83:
                case 84:
                case 85:
                case 86:
                case 87:
                case 88:
                case 89:
                case 90:
                case 91:
                case 92:
                case 93:
                case 94:
                case 95:
                case 96:
                case 97:
                case 98:
                case 99:
                case 100:
                case 101:
                case 102:
                case 103:
                case 104:
                case 105:
                case 106:
                case 107:
                case 108:
                case 109:
                case 110:
                case 111:
                case 112:
                case 113:
                case 114:
                case 115:
                case 116:
                case 117:
                case 118:
                case 119:
                case 120:
                case 121:
                case 122:
                case 123:
                case 124:
                case 125:
                case 126:
                case 127:
                case 128:
                case 129:
                case 130:
                case 131:
                case 132:
                case 133:
                case 134:
                case 135:
                case 136:
                case 137:
                case 138:
                case 139:
                case 140:
                case 141:
                case 142:
                case 143:
                case 144:
                case 145:
                case 146:
                case 147:
                case 148:
                case 149:
                case 150:
                    this.enterOuterAlt(_localctx, 2);
                    this.setState(1597);
                    this.keyword();
                    break;
                case 154:
                    this.enterOuterAlt(_localctx, 1);
                    this.setState(1596);
                    this.match(154);
                    break;
                case 157:
                    this.enterOuterAlt(_localctx, 3);
                    this.setState(1598);
                    this.match(157);
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
        switch(ruleIndex) {
            case 37:
                return this.expr_sempred((SQLiteParser.ExprContext)_localctx, predIndex);
            default:
                return true;
        }
    }

    private boolean expr_sempred(SQLiteParser.ExprContext _localctx, int predIndex) {
        switch(predIndex) {
            case 0:
                return this.precpred(this._ctx, 13);
            case 1:
                return this.precpred(this._ctx, 6);
            case 2:
                return this.precpred(this._ctx, 5);
            case 3:
                return this.precpred(this._ctx, 9);
            case 4:
                return this.precpred(this._ctx, 8);
            case 5:
                return this.precpred(this._ctx, 7);
            case 6:
                return this.precpred(this._ctx, 4);
            default:
                return true;
        }
    }

    static {
        RuntimeMetaData.checkVersion("4.7.1", "4.7.1");
        _sharedContextCache = new PredictionContextCache();
        ruleNames = new String[]{"parse", "error", "sql_stmt_list", "sql_stmt", "alter_table_stmt", "analyze_stmt", "attach_stmt", "begin_stmt", "commit_stmt", "create_index_stmt", "create_table_stmt", "create_trigger_stmt", "create_view_stmt", "create_virtual_table_stmt", "delete_stmt", "delete_stmt_limited", "detach_stmt", "drop_index_stmt", "drop_table_stmt", "drop_trigger_stmt", "drop_view_stmt", "insert_stmt", "upsert_clause", "pragma_stmt", "reindex_stmt", "release_stmt", "rollback_stmt", "savepoint_stmt", "select_stmt", "select_or_values", "update_stmt", "update_stmt_limited", "vacuum_stmt", "column_def", "type_name", "column_constraint", "conflict_clause", "expr", "foreign_key_clause", "raise_function", "indexed_column", "table_constraint", "with_clause", "common_table_expression", "qualified_table_name", "order_clause", "ordering_term", "limit_clause", "pragma_value", "result_column", "table_or_subquery", "join_clause", "join_operator", "join_constraint", "compound_operator", "signed_number", "literal_value", "boolean_literal", "unary_operator", "binary_operator", "error_message", "module_argument", "column_alias", "column_name_list", "keyword", "name", "function_name", "schema_name", "table_function", "table_name", "table_or_index_name", "new_table_name", "column_name", "collation_name", "foreign_table", "index_name", "trigger_name", "view_name", "module_name", "pragma_name", "savepoint_name", "table_alias", "transaction_name", "any_name"};
        _LITERAL_NAMES = new String[]{null, "';'", "'.'", "'('", "')'", "','", "'='", "'*'", "'+'", "'-'", "'~'", "'||'", "'/'", "'%'", "'<<'", "'>>'", "'&'", "'|'", "'<'", "'<='", "'>'", "'>='", "'=='", "'!='", "'<>'"};
        _SYMBOLIC_NAMES = new String[]{null, "SCOL", "DOT", "OPEN_PAR", "CLOSE_PAR", "COMMA", "ASSIGN", "STAR", "PLUS", "MINUS", "TILDE", "PIPE2", "DIV", "MOD", "LT2", "GT2", "AMP", "PIPE", "LT", "LT_EQ", "GT", "GT_EQ", "EQ", "NOT_EQ1", "NOT_EQ2", "TRUE", "FALSE", "K_ABORT", "K_ACTION", "K_ADD", "K_AFTER", "K_ALL", "K_ALTER", "K_ANALYZE", "K_AND", "K_AS", "K_ASC", "K_ATTACH", "K_AUTOINCREMENT", "K_BEFORE", "K_BEGIN", "K_BETWEEN", "K_BY", "K_CASCADE", "K_CASE", "K_CAST", "K_CHECK", "K_COLLATE", "K_COLUMN", "K_COMMIT", "K_CONFLICT", "K_CONSTRAINT", "K_CREATE", "K_CROSS", "K_CURRENT_DATE", "K_CURRENT_TIME", "K_CURRENT_TIMESTAMP", "K_DATABASE", "K_DEFAULT", "K_DEFERRABLE", "K_DEFERRED", "K_DELETE", "K_DESC", "K_DETACH", "K_DISTINCT", "K_DROP", "K_EACH", "K_ELSE", "K_END", "K_ESCAPE", "K_EXCEPT", "K_EXCLUSIVE", "K_EXISTS", "K_EXPLAIN", "K_FAIL", "K_FOR", "K_FOREIGN", "K_FROM", "K_FULL", "K_GLOB", "K_GROUP", "K_HAVING", "K_IF", "K_IGNORE", "K_IMMEDIATE", "K_IN", "K_INDEX", "K_INDEXED", "K_INITIALLY", "K_INNER", "K_INSERT", "K_INSTEAD", "K_INTERSECT", "K_INTO", "K_IS", "K_ISNULL", "K_JOIN", "K_KEY", "K_LEFT", "K_LIKE", "K_LIMIT", "K_MATCH", "K_NATURAL", "K_NO", "K_NOT", "K_NOTNULL", "K_NULL", "K_OF", "K_OFFSET", "K_ON", "K_OR", "K_ORDER", "K_OUTER", "K_PLAN", "K_PRAGMA", "K_PRIMARY", "K_QUERY", "K_RAISE", "K_RECURSIVE", "K_REFERENCES", "K_REGEXP", "K_REINDEX", "K_RELEASE", "K_RENAME", "K_REPLACE", "K_RESTRICT", "K_RIGHT", "K_ROLLBACK", "K_ROW", "K_SAVEPOINT", "K_SELECT", "K_SET", "K_TABLE", "K_TEMP", "K_TEMPORARY", "K_THEN", "K_TO", "K_TRANSACTION", "K_TRIGGER", "K_UNION", "K_UNIQUE", "K_UPDATE", "K_USING", "K_VACUUM", "K_VALUES", "K_VIEW", "K_VIRTUAL", "K_WHEN", "K_WHERE", "K_WITH", "K_WITHOUT", "WITHOUT_ROWID", "DO_NOTHING", "DO_UPDATE", "IDENTIFIER", "NUMERIC_LITERAL", "BIND_PARAMETER", "STRING_LITERAL", "BLOB_LITERAL", "SINGLE_LINE_COMMENT", "MULTILINE_COMMENT", "SPACES", "UNEXPECTED_CHAR"};
        VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);
        tokenNames = new String[_SYMBOLIC_NAMES.length];

        int i;
        for(i = 0; i < tokenNames.length; ++i) {
            tokenNames[i] = VOCABULARY.getLiteralName(i);
            if (tokenNames[i] == null) {
                tokenNames[i] = VOCABULARY.getSymbolicName(i);
            }

            if (tokenNames[i] == null) {
                tokenNames[i] = "<INVALID>";
            }
        }

        _ATN = (new ATNDeserializer()).deserialize("\u0003悋Ꜫ脳맭䅼㯧瞆奤\u0003¤و\u0004\u0002\t\u0002\u0004\u0003\t\u0003\u0004\u0004\t\u0004\u0004\u0005\t\u0005\u0004\u0006\t\u0006\u0004\u0007\t\u0007\u0004\b\t\b\u0004\t\t\t\u0004\n\t\n\u0004\u000b\t\u000b\u0004\f\t\f\u0004\r\t\r\u0004\u000e\t\u000e\u0004\u000f\t\u000f\u0004\u0010\t\u0010\u0004\u0011\t\u0011\u0004\u0012\t\u0012\u0004\u0013\t\u0013\u0004\u0014\t\u0014\u0004\u0015\t\u0015\u0004\u0016\t\u0016\u0004\u0017\t\u0017\u0004\u0018\t\u0018\u0004\u0019\t\u0019\u0004\u001a\t\u001a\u0004\u001b\t\u001b\u0004\u001c\t\u001c\u0004\u001d\t\u001d\u0004\u001e\t\u001e\u0004\u001f\t\u001f\u0004 \t \u0004!\t!\u0004\"\t\"\u0004#\t#\u0004$\t$\u0004%\t%\u0004&\t&\u0004'\t'\u0004(\t(\u0004)\t)\u0004*\t*\u0004+\t+\u0004,\t,\u0004-\t-\u0004.\t.\u0004/\t/\u00040\t0\u00041\t1\u00042\t2\u00043\t3\u00044\t4\u00045\t5\u00046\t6\u00047\t7\u00048\t8\u00049\t9\u0004:\t:\u0004;\t;\u0004<\t<\u0004=\t=\u0004>\t>\u0004?\t?\u0004@\t@\u0004A\tA\u0004B\tB\u0004C\tC\u0004D\tD\u0004E\tE\u0004F\tF\u0004G\tG\u0004H\tH\u0004I\tI\u0004J\tJ\u0004K\tK\u0004L\tL\u0004M\tM\u0004N\tN\u0004O\tO\u0004P\tP\u0004Q\tQ\u0004R\tR\u0004S\tS\u0004T\tT\u0004U\tU\u0003\u0002\u0003\u0002\u0007\u0002\u00ad\n\u0002\f\u0002\u000e\u0002°\u000b\u0002\u0003\u0002\u0003\u0002\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0004\u0007\u0004¸\n\u0004\f\u0004\u000e\u0004»\u000b\u0004\u0003\u0004\u0003\u0004\u0006\u0004¿\n\u0004\r\u0004\u000e\u0004À\u0003\u0004\u0007\u0004Ä\n\u0004\f\u0004\u000e\u0004Ç\u000b\u0004\u0003\u0004\u0007\u0004Ê\n\u0004\f\u0004\u000e\u0004Í\u000b\u0004\u0003\u0005\u0003\u0005\u0003\u0005\u0005\u0005Ò\n\u0005\u0005\u0005Ô\n\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0005\u0005ñ\n\u0005\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0005\u0006ø\n\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0005\u0006Ā\n\u0006\u0003\u0006\u0005\u0006ă\n\u0006\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0005\u0007Č\n\u0007\u0003\b\u0003\b\u0005\bĐ\n\b\u0003\b\u0003\b\u0003\b\u0003\b\u0003\t\u0003\t\u0005\tĘ\n\t\u0003\t\u0003\t\u0005\tĜ\n\t\u0005\tĞ\n\t\u0003\n\u0003\n\u0003\n\u0005\nģ\n\n\u0005\nĥ\n\n\u0003\u000b\u0003\u000b\u0005\u000bĩ\n\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0005\u000bį\n\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0005\u000bĴ\n\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0007\u000bĽ\n\u000b\f\u000b\u000e\u000bŀ\u000b\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0005\u000bŅ\n\u000b\u0003\f\u0003\f\u0005\fŉ\n\f\u0003\f\u0003\f\u0003\f\u0003\f\u0005\fŏ\n\f\u0003\f\u0003\f\u0003\f\u0005\fŔ\n\f\u0003\f\u0003\f\u0003\f\u0003\f\u0003\f\u0007\fś\n\f\f\f\u000e\fŞ\u000b\f\u0003\f\u0003\f\u0007\fŢ\n\f\f\f\u000e\fť\u000b\f\u0003\f\u0003\f\u0005\fũ\n\f\u0003\f\u0003\f\u0005\fŭ\n\f\u0003\r\u0003\r\u0005\rű\n\r\u0003\r\u0003\r\u0003\r\u0003\r\u0005\rŷ\n\r\u0003\r\u0003\r\u0003\r\u0005\rż\n\r\u0003\r\u0003\r\u0003\r\u0003\r\u0003\r\u0005\rƃ\n\r\u0003\r\u0003\r\u0003\r\u0003\r\u0003\r\u0003\r\u0003\r\u0007\rƌ\n\r\f\r\u000e\rƏ\u000b\r\u0005\rƑ\n\r\u0005\rƓ\n\r\u0003\r\u0003\r\u0003\r\u0003\r\u0005\rƙ\n\r\u0003\r\u0003\r\u0003\r\u0003\r\u0005\rƟ\n\r\u0003\r\u0003\r\u0005\rƣ\n\r\u0003\r\u0003\r\u0003\r\u0003\r\u0003\r\u0005\rƪ\n\r\u0003\r\u0003\r\u0006\rƮ\n\r\r\r\u000e\rƯ\u0003\r\u0003\r\u0003\u000e\u0003\u000e\u0005\u000eƶ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0005\u000eƼ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0005\u000eǁ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0007\u000eǇ\n\u000e\f\u000e\u000e\u000eǊ\u000b\u000e\u0005\u000eǌ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0005\u000fǗ\n\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0005\u000fǜ\n\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0007\u000fǥ\n\u000f\f\u000f\u000e\u000fǨ\u000b\u000f\u0003\u000f\u0003\u000f\u0005\u000fǬ\n\u000f\u0003\u0010\u0005\u0010ǯ\n\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0005\u0010Ƕ\n\u0010\u0003\u0011\u0005\u0011ǹ\n\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0005\u0011Ȁ\n\u0011\u0003\u0011\u0005\u0011ȃ\n\u0011\u0003\u0011\u0005\u0011Ȇ\n\u0011\u0003\u0012\u0003\u0012\u0005\u0012Ȋ\n\u0012\u0003\u0012\u0003\u0012\u0003\u0013\u0003\u0013\u0003\u0013\u0003\u0013\u0005\u0013Ȓ\n\u0013\u0003\u0013\u0003\u0013\u0003\u0013\u0005\u0013ȗ\n\u0013\u0003\u0013\u0003\u0013\u0003\u0014\u0003\u0014\u0003\u0014\u0003\u0014\u0005\u0014ȟ\n\u0014\u0003\u0014\u0003\u0014\u0003\u0014\u0005\u0014Ȥ\n\u0014\u0003\u0014\u0003\u0014\u0003\u0015\u0003\u0015\u0003\u0015\u0003\u0015\u0005\u0015Ȭ\n\u0015\u0003\u0015\u0003\u0015\u0003\u0015\u0005\u0015ȱ\n\u0015\u0003\u0015\u0003\u0015\u0003\u0016\u0003\u0016\u0003\u0016\u0003\u0016\u0005\u0016ȹ\n\u0016\u0003\u0016\u0003\u0016\u0003\u0016\u0005\u0016Ⱦ\n\u0016\u0003\u0016\u0003\u0016\u0003\u0017\u0005\u0017Ƀ\n\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0005\u0017ɖ\n\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0005\u0017ɜ\n\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0005\u0017ɡ\n\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0007\u0017ɧ\n\u0017\f\u0017\u000e\u0017ɪ\u000b\u0017\u0003\u0017\u0003\u0017\u0005\u0017ɮ\n\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0007\u0017ɵ\n\u0017\f\u0017\u000e\u0017ɸ\u000b\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0007\u0017ʀ\n\u0017\f\u0017\u000e\u0017ʃ\u000b\u0017\u0003\u0017\u0003\u0017\u0007\u0017ʇ\n\u0017\f\u0017\u000e\u0017ʊ\u000b\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0005\u0017ʏ\n\u0017\u0003\u0017\u0005\u0017ʒ\n\u0017\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0007\u0018ʚ\n\u0018\f\u0018\u000e\u0018ʝ\u000b\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0005\u0018ʢ\n\u0018\u0005\u0018ʤ\n\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0005\u0018ʫ\n\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0005\u0018ʲ\n\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0007\u0018ʷ\n\u0018\f\u0018\u000e\u0018ʺ\u000b\u0018\u0003\u0018\u0003\u0018\u0005\u0018ʾ\n\u0018\u0005\u0018ˀ\n\u0018\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0005\u0019ˆ\n\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0005\u0019ˏ\n\u0019\u0003\u001a\u0003\u001a\u0003\u001a\u0003\u001a\u0003\u001a\u0005\u001a˖\n\u001a\u0003\u001a\u0003\u001a\u0005\u001a˚\n\u001a\u0005\u001a˜\n\u001a\u0003\u001b\u0003\u001b\u0005\u001bˠ\n\u001b\u0003\u001b\u0003\u001b\u0003\u001c\u0003\u001c\u0003\u001c\u0005\u001c˧\n\u001c\u0005\u001c˩\n\u001c\u0003\u001c\u0003\u001c\u0005\u001c˭\n\u001c\u0003\u001c\u0005\u001c˰\n\u001c\u0003\u001d\u0003\u001d\u0003\u001d\u0003\u001e\u0005\u001e˶\n\u001e\u0003\u001e\u0003\u001e\u0003\u001e\u0003\u001e\u0007\u001e˼\n\u001e\f\u001e\u000e\u001e˿\u000b\u001e\u0003\u001e\u0005\u001ê\n\u001e\u0003\u001e\u0005\u001e̅\n\u001e\u0003\u001f\u0003\u001f\u0005\u001f̉\n\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0007\u001f̎\n\u001f\f\u001f\u000e\u001f̑\u000b\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0007\u001f̗\n\u001f\f\u001f\u000e\u001f̚\u000b\u001f\u0003\u001f\u0005\u001f̝\n\u001f\u0005\u001f̟\n\u001f\u0003\u001f\u0003\u001f\u0005\u001f̣\n\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0007\u001f̪\n\u001f\f\u001f\u000e\u001f̭\u000b\u001f\u0003\u001f\u0003\u001f\u0005\u001f̱\n\u001f\u0005\u001f̳\n\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0007\u001f̺\n\u001f\f\u001f\u000e\u001f̽\u000b\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0007\u001fͅ\n\u001f\f\u001f\u000e\u001f͈\u000b\u001f\u0003\u001f\u0003\u001f\u0007\u001f͌\n\u001f\f\u001f\u000e\u001f͏\u000b\u001f\u0005\u001f͑\n\u001f\u0003 \u0005 ͔\n \u0003 \u0003 \u0003 \u0003 \u0003 \u0003 \u0003 \u0003 \u0003 \u0003 \u0003 \u0005 ͡\n \u0003 \u0003 \u0003 \u0003 \u0005 ͧ\n \u0003 \u0003 \u0003 \u0003 \u0003 \u0005 ͮ\n \u0003 \u0003 \u0003 \u0007 ͳ\n \f \u000e Ͷ\u000b \u0003 \u0003 \u0005 ͺ\n \u0003!\u0005!ͽ\n!\u0003!\u0003!\u0003!\u0003!\u0003!\u0003!\u0003!\u0003!\u0003!\u0003!\u0003!\u0005!Ί\n!\u0003!\u0003!\u0003!\u0003!\u0005!ΐ\n!\u0003!\u0003!\u0003!\u0003!\u0003!\u0005!Η\n!\u0003!\u0003!\u0003!\u0007!Μ\n!\f!\u000e!Ο\u000b!\u0003!\u0003!\u0005!Σ\n!\u0003!\u0005!Φ\n!\u0003!\u0005!Ω\n!\u0003\"\u0003\"\u0005\"έ\n\"\u0003#\u0003#\u0005#α\n#\u0003#\u0007#δ\n#\f#\u000e#η\u000b#\u0003$\u0006$κ\n$\r$\u000e$λ\u0003$\u0003$\u0003$\u0003$\u0003$\u0003$\u0003$\u0003$\u0003$\u0003$\u0005$ψ\n$\u0003%\u0003%\u0005%ό\n%\u0003%\u0003%\u0003%\u0005%ϑ\n%\u0003%\u0003%\u0005%ϕ\n%\u0003%\u0005%Ϙ\n%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0003%\u0005%Ϫ\n%\u0003%\u0003%\u0003%\u0005%ϯ\n%\u0003&\u0003&\u0003&\u0005&ϴ\n&\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0005'ϼ\n'\u0003'\u0003'\u0003'\u0005'Ё\n'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0005'Њ\n'\u0003'\u0003'\u0003'\u0007'Џ\n'\f'\u000e'В\u000b'\u0003'\u0005'Е\n'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0007'Н\n'\f'\u000e'Р\u000b'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0005'Ь\n'\u0003'\u0005'Я\n'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0005'з\n'\u0003'\u0003'\u0003'\u0003'\u0003'\u0006'о\n'\r'\u000e'п\u0003'\u0003'\u0005'ф\n'\u0003'\u0003'\u0003'\u0005'щ\n'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0005'ђ\n'\u0003'\u0003'\u0003'\u0005'ї\n'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0005'ѣ\n'\u0003'\u0003'\u0003'\u0003'\u0005'ѩ\n'\u0003'\u0003'\u0003'\u0003'\u0003'\u0005'Ѱ\n'\u0003'\u0003'\u0005'Ѵ\n'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0007'Ѽ\n'\f'\u000e'ѿ\u000b'\u0005'ҁ\n'\u0003'\u0003'\u0003'\u0003'\u0005'҇\n'\u0003'\u0003'\u0003'\u0003'\u0005'ҍ\n'\u0003'\u0003'\u0003'\u0003'\u0003'\u0007'Ҕ\n'\f'\u000e'җ\u000b'\u0005'ҙ\n'\u0003'\u0003'\u0005'ҝ\n'\u0007'ҟ\n'\f'\u000e'Ң\u000b'\u0003(\u0003(\u0003(\u0003(\u0003(\u0003(\u0007(Ҫ\n(\f(\u000e(ҭ\u000b(\u0003(\u0003(\u0005(ұ\n(\u0003(\u0003(\u0003(\u0003(\u0003(\u0003(\u0003(\u0003(\u0003(\u0003(\u0005(ҽ\n(\u0003(\u0003(\u0005(Ӂ\n(\u0007(Ӄ\n(\f(\u000e(ӆ\u000b(\u0003(\u0005(Ӊ\n(\u0003(\u0003(\u0003(\u0003(\u0003(\u0005(Ӑ\n(\u0005(Ӓ\n(\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0005)Ӛ\n)\u0003)\u0003)\u0003*\u0003*\u0005*Ӡ\n*\u0003*\u0003*\u0005*Ӥ\n*\u0003*\u0005*ӧ\n*\u0003+\u0003+\u0005+ӫ\n+\u0003+\u0003+\u0003+\u0005+Ӱ\n+\u0003+\u0003+\u0003+\u0003+\u0007+Ӷ\n+\f+\u000e+ӹ\u000b+\u0003+\u0003+\u0003+\u0003+\u0003+\u0003+\u0003+\u0003+\u0003+\u0003+\u0003+\u0003+\u0003+\u0003+\u0007+ԉ\n+\f+\u000e+Ԍ\u000b+\u0003+\u0003+\u0003+\u0005+ԑ\n+\u0003,\u0003,\u0005,ԕ\n,\u0003,\u0003,\u0003,\u0007,Ԛ\n,\f,\u000e,ԝ\u000b,\u0003-\u0003-\u0003-\u0003-\u0003-\u0007-Ԥ\n-\f-\u000e-ԧ\u000b-\u0003-\u0003-\u0005-ԫ\n-\u0003-\u0003-\u0003-\u0003-\u0003-\u0003.\u0003.\u0003.\u0005.Ե\n.\u0003.\u0003.\u0003.\u0005.Ժ\n.\u0003.\u0003.\u0003.\u0003.\u0003.\u0005.Ձ\n.\u0003/\u0003/\u0003/\u0003/\u0003/\u0007/Ո\n/\f/\u000e/Ջ\u000b/\u00030\u00030\u00030\u00050Ր\n0\u00030\u00050Փ\n0\u00031\u00031\u00031\u00031\u00051ՙ\n1\u00032\u00032\u00032\u00032\u00052՟\n2\u00033\u00033\u00033\u00033\u00033\u00033\u00033\u00053ը\n3\u00033\u00053ի\n3\u00053խ\n3\u00034\u00034\u00034\u00054ղ\n4\u00034\u00034\u00054ն\n4\u00034\u00054չ\n4\u00034\u00034\u00034\u00034\u00034\u00054ր\n4\u00034\u00034\u00034\u00054օ\n4\u00034\u00034\u00034\u00034\u00034\u00074\u058c\n4\f4\u000e4֏\u000b4\u00054֑\n4\u00034\u00034\u00054֕\n4\u00034\u00054֘\n4\u00034\u00034\u00034\u00034\u00074֞\n4\f4\u000e4֡\u000b4\u00034\u00054֤\n4\u00034\u00034\u00034\u00034\u00034\u00034\u00054֬\n4\u00034\u00054֯\n4\u00054ֱ\n4\u00035\u00035\u00035\u00035\u00035\u00075ָ\n5\f5\u000e5ֻ\u000b5\u00036\u00036\u00056ֿ\n6\u00036\u00036\u00056׃\n6\u00036\u00036\u00056ׇ\n6\u00036\u00056\u05ca\n6\u00037\u00037\u00037\u00037\u00037\u00037\u00037\u00077ד\n7\f7\u000e7ז\u000b7\u00037\u00037\u00057ך\n7\u00038\u00038\u00038\u00038\u00038\u00058ס\n8\u00039\u00059פ\n9\u00039\u00039\u0003:\u0003:\u0003:\u0003:\u0003:\u0003:\u0003:\u0003:\u0005:װ\n:\u0003;\u0003;\u0003<\u0003<\u0003=\u0003=\u0003=\u0003=\u0003=\u0003=\u0003=\u0003=\u0005=\u05fe\n=\u0003>\u0003>\u0003?\u0003?\u0005?\u0604\n?\u0003@\u0003@\u0003A\u0003A\u0003A\u0003A\u0007A،\nA\fA\u000eA؏\u000bA\u0003A\u0003A\u0003B\u0003B\u0003C\u0003C\u0003D\u0003D\u0003E\u0003E\u0003F\u0003F\u0003G\u0003G\u0003H\u0003H\u0003I\u0003I\u0003J\u0003J\u0003K\u0003K\u0003L\u0003L\u0003M\u0003M\u0003N\u0003N\u0003O\u0003O\u0003P\u0003P\u0003Q\u0003Q\u0003R\u0003R\u0003S\u0003S\u0003S\u0003S\u0003S\u0003S\u0005Sػ\nS\u0003T\u0003T\u0003U\u0003U\u0003U\u0003U\u0003U\u0003U\u0003U\u0005Uن\nU\u0003U\u0004Ŝλ\u0003LV\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e ¢¤¦¨\u0002\u0015\u0005\u0002>>IIVV\u0004\u000233FF\u0003\u0002\u0087\u0088\u0004\u0002!!BB\u0004\u0002&&@@\u0007\u0002\u001d\u001dLLUU~~\u0081\u0081\u0006\u0002QQeeggzz\u0004\u0002??\u008f\u008f\u0005\u0002\u001d\u001dLL\u0081\u0081\u0004\u0002\u0007\u0007nn\u0003\u0002\n\u000b\u0003\u0002\u001b\u001c\u0004\u0002\n\fjj\u0004\u0002\t\t\u000e\u000f\u0003\u0002\u0010\u0013\u0003\u0002\u0014\u0017\u0004\u0002\b\b\u0018\u001a\u0004\u0002\u009c\u009c\u009f\u009f\u0003\u0002\u001d\u0098\u0002ܾ\u0002®\u0003\u0002\u0002\u0002\u0004³\u0003\u0002\u0002\u0002\u0006¹\u0003\u0002\u0002\u0002\bÓ\u0003\u0002\u0002\u0002\nò\u0003\u0002\u0002\u0002\fĄ\u0003\u0002\u0002\u0002\u000eč\u0003\u0002\u0002\u0002\u0010ĕ\u0003\u0002\u0002\u0002\u0012ğ\u0003\u0002\u0002\u0002\u0014Ħ\u0003\u0002\u0002\u0002\u0016ņ\u0003\u0002\u0002\u0002\u0018Ů\u0003\u0002\u0002\u0002\u001aƳ\u0003\u0002\u0002\u0002\u001cǐ\u0003\u0002\u0002\u0002\u001eǮ\u0003\u0002\u0002\u0002 Ǹ\u0003\u0002\u0002\u0002\"ȇ\u0003\u0002\u0002\u0002$ȍ\u0003\u0002\u0002\u0002&Ț\u0003\u0002\u0002\u0002(ȧ\u0003\u0002\u0002\u0002*ȴ\u0003\u0002\u0002\u0002,ɂ\u0003\u0002\u0002\u0002.ʓ\u0003\u0002\u0002\u00020ˁ\u0003\u0002\u0002\u00022ː\u0003\u0002\u0002\u00024˝\u0003\u0002\u0002\u00026ˣ\u0003\u0002\u0002\u00028˱\u0003\u0002\u0002\u0002:˵\u0003\u0002\u0002\u0002<͐\u0003\u0002\u0002\u0002>͓\u0003\u0002\u0002\u0002@ͼ\u0003\u0002\u0002\u0002BΪ\u0003\u0002\u0002\u0002Dή\u0003\u0002\u0002\u0002Fι\u0003\u0002\u0002\u0002Hϋ\u0003\u0002\u0002\u0002Jϳ\u0003\u0002\u0002\u0002Lш\u0003\u0002\u0002\u0002Nң\u0003\u0002\u0002\u0002Pӓ\u0003\u0002\u0002\u0002Rӟ\u0003\u0002\u0002\u0002TӪ\u0003\u0002\u0002\u0002VԒ\u0003\u0002\u0002\u0002XԞ\u0003\u0002\u0002\u0002ZԴ\u0003\u0002\u0002\u0002\\Ղ\u0003\u0002\u0002\u0002^Ռ\u0003\u0002\u0002\u0002`Ք\u0003\u0002\u0002\u0002b՞\u0003\u0002\u0002\u0002dլ\u0003\u0002\u0002\u0002fְ\u0003\u0002\u0002\u0002hֲ\u0003\u0002\u0002\u0002j\u05c9\u0003\u0002\u0002\u0002lי\u0003\u0002\u0002\u0002nנ\u0003\u0002\u0002\u0002pף\u0003\u0002\u0002\u0002r\u05ef\u0003\u0002\u0002\u0002tױ\u0003\u0002\u0002\u0002v׳\u0003\u0002\u0002\u0002x\u05fd\u0003\u0002\u0002\u0002z\u05ff\u0003\u0002\u0002\u0002|\u0603\u0003\u0002\u0002\u0002~\u0605\u0003\u0002\u0002\u0002\u0080؇\u0003\u0002\u0002\u0002\u0082ؒ\u0003\u0002\u0002\u0002\u0084ؔ\u0003\u0002\u0002\u0002\u0086ؖ\u0003\u0002\u0002\u0002\u0088ؘ\u0003\u0002\u0002\u0002\u008aؚ\u0003\u0002\u0002\u0002\u008c\u061c\u0003\u0002\u0002\u0002\u008e؞\u0003\u0002\u0002\u0002\u0090ؠ\u0003\u0002\u0002\u0002\u0092آ\u0003\u0002\u0002\u0002\u0094ؤ\u0003\u0002\u0002\u0002\u0096ئ\u0003\u0002\u0002\u0002\u0098ب\u0003\u0002\u0002\u0002\u009aت\u0003\u0002\u0002\u0002\u009cج\u0003\u0002\u0002\u0002\u009eخ\u0003\u0002\u0002\u0002 ذ\u0003\u0002\u0002\u0002¢ز\u0003\u0002\u0002\u0002¤غ\u0003\u0002\u0002\u0002¦ؼ\u0003\u0002\u0002\u0002¨م\u0003\u0002\u0002\u0002ª\u00ad\u0005\u0006\u0004\u0002«\u00ad\u0005\u0004\u0003\u0002¬ª\u0003\u0002\u0002\u0002¬«\u0003\u0002\u0002\u0002\u00ad°\u0003\u0002\u0002\u0002®¬\u0003\u0002\u0002\u0002®¯\u0003\u0002\u0002\u0002¯±\u0003\u0002\u0002\u0002°®\u0003\u0002\u0002\u0002±²\u0007\u0002\u0002\u0003²\u0003\u0003\u0002\u0002\u0002³´\u0007¤\u0002\u0002´µ\b\u0003\u0001\u0002µ\u0005\u0003\u0002\u0002\u0002¶¸\u0007\u0003\u0002\u0002·¶\u0003\u0002\u0002\u0002¸»\u0003\u0002\u0002\u0002¹·\u0003\u0002\u0002\u0002¹º\u0003\u0002\u0002\u0002º¼\u0003\u0002\u0002\u0002»¹\u0003\u0002\u0002\u0002¼Å\u0005\b\u0005\u0002½¿\u0007\u0003\u0002\u0002¾½\u0003\u0002\u0002\u0002¿À\u0003\u0002\u0002\u0002À¾\u0003\u0002\u0002\u0002ÀÁ\u0003\u0002\u0002\u0002ÁÂ\u0003\u0002\u0002\u0002ÂÄ\u0005\b\u0005\u0002Ã¾\u0003\u0002\u0002\u0002ÄÇ\u0003\u0002\u0002\u0002ÅÃ\u0003\u0002\u0002\u0002ÅÆ\u0003\u0002\u0002\u0002ÆË\u0003\u0002\u0002\u0002ÇÅ\u0003\u0002\u0002\u0002ÈÊ\u0007\u0003\u0002\u0002ÉÈ\u0003\u0002\u0002\u0002ÊÍ\u0003\u0002\u0002\u0002ËÉ\u0003\u0002\u0002\u0002ËÌ\u0003\u0002\u0002\u0002Ì\u0007\u0003\u0002\u0002\u0002ÍË\u0003\u0002\u0002\u0002ÎÑ\u0007K\u0002\u0002ÏÐ\u0007v\u0002\u0002ÐÒ\u0007s\u0002\u0002ÑÏ\u0003\u0002\u0002\u0002ÑÒ\u0003\u0002\u0002\u0002ÒÔ\u0003\u0002\u0002\u0002ÓÎ\u0003\u0002\u0002\u0002ÓÔ\u0003\u0002\u0002\u0002Ôð\u0003\u0002\u0002\u0002Õñ\u0005\n\u0006\u0002Öñ\u0005\f\u0007\u0002×ñ\u0005\u000e\b\u0002Øñ\u0005\u0010\t\u0002Ùñ\u0005\u0012\n\u0002Úñ\u0005\u0014\u000b\u0002Ûñ\u0005\u0016\f\u0002Üñ\u0005\u0018\r\u0002Ýñ\u0005\u001a\u000e\u0002Þñ\u0005\u001c\u000f\u0002ßñ\u0005\u001e\u0010\u0002àñ\u0005 \u0011\u0002áñ\u0005\"\u0012\u0002âñ\u0005$\u0013\u0002ãñ\u0005&\u0014\u0002äñ\u0005(\u0015\u0002åñ\u0005*\u0016\u0002æñ\u0005,\u0017\u0002çñ\u00050\u0019\u0002èñ\u00052\u001a\u0002éñ\u00054\u001b\u0002êñ\u00056\u001c\u0002ëñ\u00058\u001d\u0002ìñ\u0005:\u001e\u0002íñ\u0005> \u0002îñ\u0005@!\u0002ïñ\u0005B\"\u0002ðÕ\u0003\u0002\u0002\u0002ðÖ\u0003\u0002\u0002\u0002ð×\u0003\u0002\u0002\u0002ðØ\u0003\u0002\u0002\u0002ðÙ\u0003\u0002\u0002\u0002ðÚ\u0003\u0002\u0002\u0002ðÛ\u0003\u0002\u0002\u0002ðÜ\u0003\u0002\u0002\u0002ðÝ\u0003\u0002\u0002\u0002ðÞ\u0003\u0002\u0002\u0002ðß\u0003\u0002\u0002\u0002ðà\u0003\u0002\u0002\u0002ðá\u0003\u0002\u0002\u0002ðâ\u0003\u0002\u0002\u0002ðã\u0003\u0002\u0002\u0002ðä\u0003\u0002\u0002\u0002ðå\u0003\u0002\u0002\u0002ðæ\u0003\u0002\u0002\u0002ðç\u0003\u0002\u0002\u0002ðè\u0003\u0002\u0002\u0002ðé\u0003\u0002\u0002\u0002ðê\u0003\u0002\u0002\u0002ðë\u0003\u0002\u0002\u0002ðì\u0003\u0002\u0002\u0002ðí\u0003\u0002\u0002\u0002ðî\u0003\u0002\u0002\u0002ðï\u0003\u0002\u0002\u0002ñ\t\u0003\u0002\u0002\u0002òó\u0007\"\u0002\u0002ó÷\u0007\u0086\u0002\u0002ôõ\u0005\u0088E\u0002õö\u0007\u0004\u0002\u0002öø\u0003\u0002\u0002\u0002÷ô\u0003\u0002\u0002\u0002÷ø\u0003\u0002\u0002\u0002øù\u0003\u0002\u0002\u0002ùĂ\u0005\u008cG\u0002úû\u0007}\u0002\u0002ûü\u0007\u008a\u0002\u0002üă\u0005\u0090I\u0002ýÿ\u0007\u001f\u0002\u0002þĀ\u00072\u0002\u0002ÿþ\u0003\u0002\u0002\u0002ÿĀ\u0003\u0002\u0002\u0002Āā\u0003\u0002\u0002\u0002āă\u0005D#\u0002Ăú\u0003\u0002\u0002\u0002Ăý\u0003\u0002\u0002\u0002ă\u000b\u0003\u0002\u0002\u0002Ąċ\u0007#\u0002\u0002ąČ\u0005\u0088E\u0002ĆČ\u0005\u008eH\u0002ćĈ\u0005\u0088E\u0002Ĉĉ\u0007\u0004\u0002\u0002ĉĊ\u0005\u008eH\u0002ĊČ\u0003\u0002\u0002\u0002ċą\u0003\u0002\u0002\u0002ċĆ\u0003\u0002\u0002\u0002ċć\u0003\u0002\u0002\u0002ċČ\u0003\u0002\u0002\u0002Č\r\u0003\u0002\u0002\u0002čď\u0007'\u0002\u0002ĎĐ\u0007;\u0002\u0002ďĎ\u0003\u0002\u0002\u0002ďĐ\u0003\u0002\u0002\u0002Đđ\u0003\u0002\u0002\u0002đĒ\u0005L'\u0002Ēē\u0007%\u0002\u0002ēĔ\u0005\u0088E\u0002Ĕ\u000f\u0003\u0002\u0002\u0002ĕė\u0007*\u0002\u0002ĖĘ\t\u0002\u0002\u0002ėĖ\u0003\u0002\u0002\u0002ėĘ\u0003\u0002\u0002\u0002Ęĝ\u0003\u0002\u0002\u0002ęě\u0007\u008b\u0002\u0002ĚĜ\u0005¦T\u0002ěĚ\u0003\u0002\u0002\u0002ěĜ\u0003\u0002\u0002\u0002ĜĞ\u0003\u0002\u0002\u0002ĝę\u0003\u0002\u0002\u0002ĝĞ\u0003\u0002\u0002\u0002Ğ\u0011\u0003\u0002\u0002\u0002ğĤ\t\u0003\u0002\u0002ĠĢ\u0007\u008b\u0002\u0002ġģ\u0005¦T\u0002Ģġ\u0003\u0002\u0002\u0002Ģģ\u0003\u0002\u0002\u0002ģĥ\u0003\u0002\u0002\u0002ĤĠ\u0003\u0002\u0002\u0002Ĥĥ\u0003\u0002\u0002\u0002ĥ\u0013\u0003\u0002\u0002\u0002ĦĨ\u00076\u0002\u0002ħĩ\u0007\u008e\u0002\u0002Ĩħ\u0003\u0002\u0002\u0002Ĩĩ\u0003\u0002\u0002\u0002ĩĪ\u0003\u0002\u0002\u0002ĪĮ\u0007X\u0002\u0002īĬ\u0007T\u0002\u0002Ĭĭ\u0007j\u0002\u0002ĭį\u0007J\u0002\u0002Įī\u0003\u0002\u0002\u0002Įį\u0003\u0002\u0002\u0002įĳ\u0003\u0002\u0002\u0002İı\u0005\u0088E\u0002ıĲ\u0007\u0004\u0002\u0002ĲĴ\u0003\u0002\u0002\u0002ĳİ\u0003\u0002\u0002\u0002ĳĴ\u0003\u0002\u0002\u0002Ĵĵ\u0003\u0002\u0002\u0002ĵĶ\u0005\u0098M\u0002Ķķ\u0007o\u0002\u0002ķĸ\u0005\u008cG\u0002ĸĹ\u0007\u0005\u0002\u0002Ĺľ\u0005R*\u0002ĺĻ\u0007\u0007\u0002\u0002ĻĽ\u0005R*\u0002ļĺ\u0003\u0002\u0002\u0002Ľŀ\u0003\u0002\u0002\u0002ľļ\u0003\u0002\u0002\u0002ľĿ\u0003\u0002\u0002\u0002ĿŁ\u0003\u0002\u0002\u0002ŀľ\u0003\u0002\u0002\u0002Łń\u0007\u0006\u0002\u0002łŃ\u0007\u0096\u0002\u0002ŃŅ\u0005L'\u0002ńł\u0003\u0002\u0002\u0002ńŅ\u0003\u0002\u0002\u0002Ņ\u0015\u0003\u0002\u0002\u0002ņň\u00076\u0002\u0002Ňŉ\t\u0004\u0002\u0002ňŇ\u0003\u0002\u0002\u0002ňŉ\u0003\u0002\u0002\u0002ŉŊ\u0003\u0002\u0002\u0002ŊŎ\u0007\u0086\u0002\u0002ŋŌ\u0007T\u0002\u0002Ōō\u0007j\u0002\u0002ōŏ\u0007J\u0002\u0002Ŏŋ\u0003\u0002\u0002\u0002Ŏŏ\u0003\u0002\u0002\u0002ŏœ\u0003\u0002\u0002\u0002Őő\u0005\u0088E\u0002őŒ\u0007\u0004\u0002\u0002ŒŔ\u0003\u0002\u0002\u0002œŐ\u0003\u0002\u0002\u0002œŔ\u0003\u0002\u0002\u0002Ŕŕ\u0003\u0002\u0002\u0002ŕŬ\u0005\u008cG\u0002Ŗŗ\u0007\u0005\u0002\u0002ŗŜ\u0005D#\u0002Řř\u0007\u0007\u0002\u0002řś\u0005D#\u0002ŚŘ\u0003\u0002\u0002\u0002śŞ\u0003\u0002\u0002\u0002Ŝŝ\u0003\u0002\u0002\u0002ŜŚ\u0003\u0002\u0002\u0002ŝţ\u0003\u0002\u0002\u0002ŞŜ\u0003\u0002\u0002\u0002şŠ\u0007\u0007\u0002\u0002ŠŢ\u0005T+\u0002šş\u0003\u0002\u0002\u0002Ţť\u0003\u0002\u0002\u0002ţš\u0003\u0002\u0002\u0002ţŤ\u0003\u0002\u0002\u0002ŤŦ\u0003\u0002\u0002\u0002ťţ\u0003\u0002\u0002\u0002ŦŨ\u0007\u0006\u0002\u0002ŧũ\u0007\u0099\u0002\u0002Ũŧ\u0003\u0002\u0002\u0002Ũũ\u0003\u0002\u0002\u0002ũŭ\u0003\u0002\u0002\u0002Ūū\u0007%\u0002\u0002ūŭ\u0005:\u001e\u0002ŬŖ\u0003\u0002\u0002\u0002ŬŪ\u0003\u0002\u0002\u0002ŭ\u0017\u0003\u0002\u0002\u0002ŮŰ\u00076\u0002\u0002ůű\t\u0004\u0002\u0002Űů\u0003\u0002\u0002\u0002Űű\u0003\u0002\u0002\u0002űŲ\u0003\u0002\u0002\u0002ŲŶ\u0007\u008c\u0002\u0002ųŴ\u0007T\u0002\u0002Ŵŵ\u0007j\u0002\u0002ŵŷ\u0007J\u0002\u0002Ŷų\u0003\u0002\u0002\u0002Ŷŷ\u0003\u0002\u0002\u0002ŷŻ\u0003\u0002\u0002\u0002ŸŹ\u0005\u0088E\u0002Źź\u0007\u0004\u0002\u0002źż\u0003\u0002\u0002\u0002ŻŸ\u0003\u0002\u0002\u0002Żż\u0003\u0002\u0002\u0002żŽ\u0003\u0002\u0002\u0002ŽƂ\u0005\u009aN\u0002žƃ\u0007)\u0002\u0002ſƃ\u0007 \u0002\u0002ƀƁ\u0007]\u0002\u0002Ɓƃ\u0007m\u0002\u0002Ƃž\u0003\u0002\u0002\u0002Ƃſ\u0003\u0002\u0002\u0002Ƃƀ\u0003\u0002\u0002\u0002Ƃƃ\u0003\u0002\u0002\u0002ƃƒ\u0003\u0002\u0002\u0002ƄƓ\u0007?\u0002\u0002ƅƓ\u0007\\\u0002\u0002ƆƐ\u0007\u008f\u0002\u0002Ƈƈ\u0007m\u0002\u0002ƈƍ\u0005\u0092J\u0002ƉƊ\u0007\u0007\u0002\u0002Ɗƌ\u0005\u0092J\u0002ƋƉ\u0003\u0002\u0002\u0002ƌƏ\u0003\u0002\u0002\u0002ƍƋ\u0003\u0002\u0002\u0002ƍƎ\u0003\u0002\u0002\u0002ƎƑ\u0003\u0002\u0002\u0002Əƍ\u0003\u0002\u0002\u0002ƐƇ\u0003\u0002\u0002\u0002ƐƑ\u0003\u0002\u0002\u0002ƑƓ\u0003\u0002\u0002\u0002ƒƄ\u0003\u0002\u0002\u0002ƒƅ\u0003\u0002\u0002\u0002ƒƆ\u0003\u0002\u0002\u0002ƓƔ\u0003\u0002\u0002\u0002ƔƘ\u0007o\u0002\u0002ƕƖ\u0005\u0088E\u0002ƖƗ\u0007\u0004\u0002\u0002Ɨƙ\u0003\u0002\u0002\u0002Ƙƕ\u0003\u0002\u0002\u0002Ƙƙ\u0003\u0002\u0002\u0002ƙƚ\u0003\u0002\u0002\u0002ƚƞ\u0005\u008cG\u0002ƛƜ\u0007M\u0002\u0002ƜƝ\u0007D\u0002\u0002ƝƟ\u0007\u0082\u0002\u0002ƞƛ\u0003\u0002\u0002\u0002ƞƟ\u0003\u0002\u0002\u0002ƟƢ\u0003\u0002\u0002\u0002Ơơ\u0007\u0095\u0002\u0002ơƣ\u0005L'\u0002ƢƠ\u0003\u0002\u0002\u0002Ƣƣ\u0003\u0002\u0002\u0002ƣƤ\u0003\u0002\u0002\u0002Ƥƭ\u0007*\u0002\u0002ƥƪ\u0005> \u0002Ʀƪ\u0005,\u0017\u0002Ƨƪ\u0005\u001e\u0010\u0002ƨƪ\u0005:\u001e\u0002Ʃƥ\u0003\u0002\u0002\u0002ƩƦ\u0003\u0002\u0002\u0002ƩƧ\u0003\u0002\u0002\u0002Ʃƨ\u0003\u0002\u0002\u0002ƪƫ\u0003\u0002\u0002\u0002ƫƬ\u0007\u0003\u0002\u0002ƬƮ\u0003\u0002\u0002\u0002ƭƩ\u0003\u0002\u0002\u0002ƮƯ\u0003\u0002\u0002\u0002Ưƭ\u0003\u0002\u0002\u0002Ưư\u0003\u0002\u0002\u0002ưƱ\u0003\u0002\u0002\u0002ƱƲ\u0007F\u0002\u0002Ʋ\u0019\u0003\u0002\u0002\u0002ƳƵ\u00076\u0002\u0002ƴƶ\t\u0004\u0002\u0002Ƶƴ\u0003\u0002\u0002\u0002Ƶƶ\u0003\u0002\u0002\u0002ƶƷ\u0003\u0002\u0002\u0002Ʒƻ\u0007\u0093\u0002\u0002Ƹƹ\u0007T\u0002\u0002ƹƺ\u0007j\u0002\u0002ƺƼ\u0007J\u0002\u0002ƻƸ\u0003\u0002\u0002\u0002ƻƼ\u0003\u0002\u0002\u0002Ƽǀ\u0003\u0002\u0002\u0002ƽƾ\u0005\u0088E\u0002ƾƿ\u0007\u0004\u0002\u0002ƿǁ\u0003\u0002\u0002\u0002ǀƽ\u0003\u0002\u0002\u0002ǀǁ\u0003\u0002\u0002\u0002ǁǂ\u0003\u0002\u0002\u0002ǂǋ\u0005\u009cO\u0002ǃǈ\u0005\u0092J\u0002Ǆǅ\u0007\u0007\u0002\u0002ǅǇ\u0005\u0092J\u0002ǆǄ\u0003\u0002\u0002\u0002ǇǊ\u0003\u0002\u0002\u0002ǈǆ\u0003\u0002\u0002\u0002ǈǉ\u0003\u0002\u0002\u0002ǉǌ\u0003\u0002\u0002\u0002Ǌǈ\u0003\u0002\u0002\u0002ǋǃ\u0003\u0002\u0002\u0002ǋǌ\u0003\u0002\u0002\u0002ǌǍ\u0003\u0002\u0002\u0002Ǎǎ\u0007%\u0002\u0002ǎǏ\u0005:\u001e\u0002Ǐ\u001b\u0003\u0002\u0002\u0002ǐǑ\u00076\u0002\u0002Ǒǒ\u0007\u0094\u0002\u0002ǒǖ\u0007\u0086\u0002\u0002Ǔǔ\u0007T\u0002\u0002ǔǕ\u0007j\u0002\u0002ǕǗ\u0007J\u0002\u0002ǖǓ\u0003\u0002\u0002\u0002ǖǗ\u0003\u0002\u0002\u0002ǗǛ\u0003\u0002\u0002\u0002ǘǙ\u0005\u0088E\u0002Ǚǚ\u0007\u0004\u0002\u0002ǚǜ\u0003\u0002\u0002\u0002Ǜǘ\u0003\u0002\u0002\u0002Ǜǜ\u0003\u0002\u0002\u0002ǜǝ\u0003\u0002\u0002\u0002ǝǞ\u0005\u008cG\u0002Ǟǟ\u0007\u0090\u0002\u0002ǟǫ\u0005\u009eP\u0002Ǡǡ\u0007\u0005\u0002\u0002ǡǦ\u0005|?\u0002Ǣǣ\u0007\u0007\u0002\u0002ǣǥ\u0005|?\u0002ǤǢ\u0003\u0002\u0002\u0002ǥǨ\u0003\u0002\u0002\u0002ǦǤ\u0003\u0002\u0002\u0002Ǧǧ\u0003\u0002\u0002\u0002ǧǩ\u0003\u0002\u0002\u0002ǨǦ\u0003\u0002\u0002\u0002ǩǪ\u0007\u0006\u0002\u0002ǪǬ\u0003\u0002\u0002\u0002ǫǠ\u0003\u0002\u0002\u0002ǫǬ\u0003\u0002\u0002\u0002Ǭ\u001d\u0003\u0002\u0002\u0002ǭǯ\u0005V,\u0002Ǯǭ\u0003\u0002\u0002\u0002Ǯǯ\u0003\u0002\u0002\u0002ǯǰ\u0003\u0002\u0002\u0002ǰǱ\u0007?\u0002\u0002Ǳǲ\u0007O\u0002\u0002ǲǵ\u0005Z.\u0002ǳǴ\u0007\u0096\u0002\u0002ǴǶ\u0005L'\u0002ǵǳ\u0003\u0002\u0002\u0002ǵǶ\u0003\u0002\u0002\u0002Ƕ\u001f\u0003\u0002\u0002\u0002Ƿǹ\u0005V,\u0002ǸǷ\u0003\u0002\u0002\u0002Ǹǹ\u0003\u0002\u0002\u0002ǹǺ\u0003\u0002\u0002\u0002Ǻǻ\u0007?\u0002\u0002ǻǼ\u0007O\u0002\u0002Ǽǿ\u0005Z.\u0002ǽǾ\u0007\u0096\u0002\u0002ǾȀ\u0005L'\u0002ǿǽ\u0003\u0002\u0002\u0002ǿȀ\u0003\u0002\u0002\u0002Ȁȅ\u0003\u0002\u0002\u0002ȁȃ\u0005\\/\u0002Ȃȁ\u0003\u0002\u0002\u0002Ȃȃ\u0003\u0002\u0002\u0002ȃȄ\u0003\u0002\u0002\u0002ȄȆ\u0005`1\u0002ȅȂ\u0003\u0002\u0002\u0002ȅȆ\u0003\u0002\u0002\u0002Ȇ!\u0003\u0002\u0002\u0002ȇȉ\u0007A\u0002\u0002ȈȊ\u0007;\u0002\u0002ȉȈ\u0003\u0002\u0002\u0002ȉȊ\u0003\u0002\u0002\u0002Ȋȋ\u0003\u0002\u0002\u0002ȋȌ\u0005\u0088E\u0002Ȍ#\u0003\u0002\u0002\u0002ȍȎ\u0007C\u0002\u0002Ȏȑ\u0007X\u0002\u0002ȏȐ\u0007T\u0002\u0002ȐȒ\u0007J\u0002\u0002ȑȏ\u0003\u0002\u0002\u0002ȑȒ\u0003\u0002\u0002\u0002ȒȖ\u0003\u0002\u0002\u0002ȓȔ\u0005\u0088E\u0002Ȕȕ\u0007\u0004\u0002\u0002ȕȗ\u0003\u0002\u0002\u0002Ȗȓ\u0003\u0002\u0002\u0002Ȗȗ\u0003\u0002\u0002\u0002ȗȘ\u0003\u0002\u0002\u0002Șș\u0005\u0098M\u0002ș%\u0003\u0002\u0002\u0002Țț\u0007C\u0002\u0002țȞ\u0007\u0086\u0002\u0002Ȝȝ\u0007T\u0002\u0002ȝȟ\u0007J\u0002\u0002ȞȜ\u0003\u0002\u0002\u0002Ȟȟ\u0003\u0002\u0002\u0002ȟȣ\u0003\u0002\u0002\u0002Ƞȡ\u0005\u0088E\u0002ȡȢ\u0007\u0004\u0002\u0002ȢȤ\u0003\u0002\u0002\u0002ȣȠ\u0003\u0002\u0002\u0002ȣȤ\u0003\u0002\u0002\u0002Ȥȥ\u0003\u0002\u0002\u0002ȥȦ\u0005\u008cG\u0002Ȧ'\u0003\u0002\u0002\u0002ȧȨ\u0007C\u0002\u0002Ȩȫ\u0007\u008c\u0002\u0002ȩȪ\u0007T\u0002\u0002ȪȬ\u0007J\u0002\u0002ȫȩ\u0003\u0002\u0002\u0002ȫȬ\u0003\u0002\u0002\u0002ȬȰ\u0003\u0002\u0002\u0002ȭȮ\u0005\u0088E\u0002Ȯȯ\u0007\u0004\u0002\u0002ȯȱ\u0003\u0002\u0002\u0002Ȱȭ\u0003\u0002\u0002\u0002Ȱȱ\u0003\u0002\u0002\u0002ȱȲ\u0003\u0002\u0002\u0002Ȳȳ\u0005\u009aN\u0002ȳ)\u0003\u0002\u0002\u0002ȴȵ\u0007C\u0002\u0002ȵȸ\u0007\u0093\u0002\u0002ȶȷ\u0007T\u0002\u0002ȷȹ\u0007J\u0002\u0002ȸȶ\u0003\u0002\u0002\u0002ȸȹ\u0003\u0002\u0002\u0002ȹȽ\u0003\u0002\u0002\u0002ȺȻ\u0005\u0088E\u0002Ȼȼ\u0007\u0004\u0002\u0002ȼȾ\u0003\u0002\u0002\u0002ȽȺ\u0003\u0002\u0002\u0002ȽȾ\u0003\u0002\u0002\u0002Ⱦȿ\u0003\u0002\u0002\u0002ȿɀ\u0005\u009cO\u0002ɀ+\u0003\u0002\u0002\u0002ɁɃ\u0005V,\u0002ɂɁ\u0003\u0002\u0002\u0002ɂɃ\u0003\u0002\u0002\u0002Ƀɕ\u0003\u0002\u0002\u0002Ʉɖ\u0007\\\u0002\u0002Ʌɖ\u0007~\u0002\u0002Ɇɇ\u0007\\\u0002\u0002ɇɈ\u0007p\u0002\u0002Ɉɖ\u0007~\u0002\u0002ɉɊ\u0007\\\u0002\u0002Ɋɋ\u0007p\u0002\u0002ɋɖ\u0007\u0081\u0002\u0002Ɍɍ\u0007\\\u0002\u0002ɍɎ\u0007p\u0002\u0002Ɏɖ\u0007\u001d\u0002\u0002ɏɐ\u0007\\\u0002\u0002ɐɑ\u0007p\u0002\u0002ɑɖ\u0007L\u0002\u0002ɒɓ\u0007\\\u0002\u0002ɓɔ\u0007p\u0002\u0002ɔɖ\u0007U\u0002\u0002ɕɄ\u0003\u0002\u0002\u0002ɕɅ\u0003\u0002\u0002\u0002ɕɆ\u0003\u0002\u0002\u0002ɕɉ\u0003\u0002\u0002\u0002ɕɌ\u0003\u0002\u0002\u0002ɕɏ\u0003\u0002\u0002\u0002ɕɒ\u0003\u0002\u0002\u0002ɖɗ\u0003\u0002\u0002\u0002ɗɛ\u0007_\u0002\u0002ɘə\u0005\u0088E\u0002əɚ\u0007\u0004\u0002\u0002ɚɜ\u0003\u0002\u0002\u0002ɛɘ\u0003\u0002\u0002\u0002ɛɜ\u0003\u0002\u0002\u0002ɜɝ\u0003\u0002\u0002\u0002ɝɠ\u0005\u008cG\u0002ɞɟ\u0007%\u0002\u0002ɟɡ\u0005¤S\u0002ɠɞ\u0003\u0002\u0002\u0002ɠɡ\u0003\u0002\u0002\u0002ɡɭ\u0003\u0002\u0002\u0002ɢɣ\u0007\u0005\u0002\u0002ɣɨ\u0005\u0092J\u0002ɤɥ\u0007\u0007\u0002\u0002ɥɧ\u0005\u0092J\u0002ɦɤ\u0003\u0002\u0002\u0002ɧɪ\u0003\u0002\u0002\u0002ɨɦ\u0003\u0002\u0002\u0002ɨɩ\u0003\u0002\u0002\u0002ɩɫ\u0003\u0002\u0002\u0002ɪɨ\u0003\u0002\u0002\u0002ɫɬ\u0007\u0006\u0002\u0002ɬɮ\u0003\u0002\u0002\u0002ɭɢ\u0003\u0002\u0002\u0002ɭɮ\u0003\u0002\u0002\u0002ɮʎ\u0003\u0002\u0002\u0002ɯɰ\u0007\u0092\u0002\u0002ɰɱ\u0007\u0005\u0002\u0002ɱɶ\u0005L'\u0002ɲɳ\u0007\u0007\u0002\u0002ɳɵ\u0005L'\u0002ɴɲ\u0003\u0002\u0002\u0002ɵɸ\u0003\u0002\u0002\u0002ɶɴ\u0003\u0002\u0002\u0002ɶɷ\u0003\u0002\u0002\u0002ɷɹ\u0003\u0002\u0002\u0002ɸɶ\u0003\u0002\u0002\u0002ɹʈ\u0007\u0006\u0002\u0002ɺɻ\u0007\u0007\u0002\u0002ɻɼ\u0007\u0005\u0002\u0002ɼʁ\u0005L'\u0002ɽɾ\u0007\u0007\u0002\u0002ɾʀ\u0005L'\u0002ɿɽ\u0003\u0002\u0002\u0002ʀʃ\u0003\u0002\u0002\u0002ʁɿ\u0003\u0002\u0002\u0002ʁʂ\u0003\u0002\u0002\u0002ʂʄ\u0003\u0002\u0002\u0002ʃʁ\u0003\u0002\u0002\u0002ʄʅ\u0007\u0006\u0002\u0002ʅʇ\u0003\u0002\u0002\u0002ʆɺ\u0003\u0002\u0002\u0002ʇʊ\u0003\u0002\u0002\u0002ʈʆ\u0003\u0002\u0002\u0002ʈʉ\u0003\u0002\u0002\u0002ʉʏ\u0003\u0002\u0002\u0002ʊʈ\u0003\u0002\u0002\u0002ʋʏ\u0005:\u001e\u0002ʌʍ\u0007<\u0002\u0002ʍʏ\u0007\u0092\u0002\u0002ʎɯ\u0003\u0002\u0002\u0002ʎʋ\u0003\u0002\u0002\u0002ʎʌ\u0003\u0002\u0002\u0002ʏʑ\u0003\u0002\u0002\u0002ʐʒ\u0005.\u0018\u0002ʑʐ\u0003\u0002\u0002\u0002ʑʒ\u0003\u0002\u0002\u0002ʒ-\u0003\u0002\u0002\u0002ʓʔ\u0007o\u0002\u0002ʔʣ\u00074\u0002\u0002ʕʖ\u0007\u0005\u0002\u0002ʖʛ\u0005R*\u0002ʗʘ\u0007\u0007\u0002\u0002ʘʚ\u0005R*\u0002ʙʗ\u0003\u0002\u0002\u0002ʚʝ\u0003\u0002\u0002\u0002ʛʙ\u0003\u0002\u0002\u0002ʛʜ\u0003\u0002\u0002\u0002ʜʞ\u0003\u0002\u0002\u0002ʝʛ\u0003\u0002\u0002\u0002ʞʡ\u0007\u0006\u0002\u0002ʟʠ\u0007\u0096\u0002\u0002ʠʢ\u0005L'\u0002ʡʟ\u0003\u0002\u0002\u0002ʡʢ\u0003\u0002\u0002\u0002ʢʤ\u0003\u0002\u0002\u0002ʣʕ\u0003\u0002\u0002\u0002ʣʤ\u0003\u0002\u0002\u0002ʤʿ\u0003\u0002\u0002\u0002ʥˀ\u0007\u009a\u0002\u0002ʦʧ\u0007\u009b\u0002\u0002ʧʪ\u0007\u0085\u0002\u0002ʨʫ\u0005\u0092J\u0002ʩʫ\u0005\u0080A\u0002ʪʨ\u0003\u0002\u0002\u0002ʪʩ\u0003\u0002\u0002\u0002ʫʬ\u0003\u0002\u0002\u0002ʬʭ\u0007\b\u0002\u0002ʭʸ\u0005L'\u0002ʮʱ\u0007\u0007\u0002\u0002ʯʲ\u0005\u0092J\u0002ʰʲ\u0005\u0080A\u0002ʱʯ\u0003\u0002\u0002\u0002ʱʰ\u0003\u0002\u0002\u0002ʲʳ\u0003\u0002\u0002\u0002ʳʴ\u0007\b\u0002\u0002ʴʵ\u0005L'\u0002ʵʷ\u0003\u0002\u0002\u0002ʶʮ\u0003\u0002\u0002\u0002ʷʺ\u0003\u0002\u0002\u0002ʸʶ\u0003\u0002\u0002\u0002ʸʹ\u0003\u0002\u0002\u0002ʹʽ\u0003\u0002\u0002\u0002ʺʸ\u0003\u0002\u0002\u0002ʻʼ\u0007\u0096\u0002\u0002ʼʾ\u0005L'\u0002ʽʻ\u0003\u0002\u0002\u0002ʽʾ\u0003\u0002\u0002\u0002ʾˀ\u0003\u0002\u0002\u0002ʿʥ\u0003\u0002\u0002\u0002ʿʦ\u0003\u0002\u0002\u0002ˀ/\u0003\u0002\u0002\u0002ˁ˅\u0007t\u0002\u0002˂˃\u0005\u0088E\u0002˃˄\u0007\u0004\u0002\u0002˄ˆ\u0003\u0002\u0002\u0002˅˂\u0003\u0002\u0002\u0002˅ˆ\u0003\u0002\u0002\u0002ˆˇ\u0003\u0002\u0002\u0002ˇˎ\u0005 Q\u0002ˈˉ\u0007\b\u0002\u0002ˉˏ\u0005b2\u0002ˊˋ\u0007\u0005\u0002\u0002ˋˌ\u0005b2\u0002ˌˍ\u0007\u0006\u0002\u0002ˍˏ\u0003\u0002\u0002\u0002ˎˈ\u0003\u0002\u0002\u0002ˎˊ\u0003\u0002\u0002\u0002ˎˏ\u0003\u0002\u0002\u0002ˏ1\u0003\u0002\u0002\u0002ː˛\u0007{\u0002\u0002ˑ˜\u0005\u0094K\u0002˒˓\u0005\u0088E\u0002˓˔\u0007\u0004\u0002\u0002˔˖\u0003\u0002\u0002\u0002˕˒\u0003\u0002\u0002\u0002˕˖\u0003\u0002\u0002\u0002˖˙\u0003\u0002\u0002\u0002˗˚\u0005\u008cG\u0002˘˚\u0005\u0098M\u0002˙˗\u0003\u0002\u0002\u0002˙˘\u0003\u0002\u0002\u0002˚˜\u0003\u0002\u0002\u0002˛ˑ\u0003\u0002\u0002\u0002˛˕\u0003\u0002\u0002\u0002˛˜\u0003\u0002\u0002\u0002˜3\u0003\u0002\u0002\u0002˝˟\u0007|\u0002\u0002˞ˠ\u0007\u0083\u0002\u0002˟˞\u0003\u0002\u0002\u0002˟ˠ\u0003\u0002\u0002\u0002ˠˡ\u0003\u0002\u0002\u0002ˡˢ\u0005¢R\u0002ˢ5\u0003\u0002\u0002\u0002ˣ˨\u0007\u0081\u0002\u0002ˤ˦\u0007\u008b\u0002\u0002˥˧\u0005¦T\u0002˦˥\u0003\u0002\u0002\u0002˦˧\u0003\u0002\u0002\u0002˧˩\u0003\u0002\u0002\u0002˨ˤ\u0003\u0002\u0002\u0002˨˩\u0003\u0002\u0002\u0002˩˯\u0003\u0002\u0002\u0002˪ˬ\u0007\u008a\u0002\u0002˫˭\u0007\u0083\u0002\u0002ˬ˫\u0003\u0002\u0002\u0002ˬ˭\u0003\u0002\u0002\u0002˭ˮ\u0003\u0002\u0002\u0002ˮ˰\u0005¢R\u0002˯˪\u0003\u0002\u0002\u0002˯˰\u0003\u0002\u0002\u0002˰7\u0003\u0002\u0002\u0002˱˲\u0007\u0083\u0002\u0002˲˳\u0005¢R\u0002˳9\u0003\u0002\u0002\u0002˴˶\u0005V,\u0002˵˴\u0003\u0002\u0002\u0002˵˶\u0003\u0002\u0002\u0002˶˷\u0003\u0002\u0002\u0002˷˽\u0005<\u001f\u0002˸˹\u0005n8\u0002˹˺\u0005<\u001f\u0002˺˼\u0003\u0002\u0002\u0002˻˸\u0003\u0002\u0002\u0002˼˿\u0003\u0002\u0002\u0002˽˻\u0003\u0002\u0002\u0002˽˾\u0003\u0002\u0002\u0002˾́\u0003\u0002\u0002\u0002˿˽\u0003\u0002\u0002\u0002̀̂\u0005\\/\u0002́̀\u0003\u0002\u0002\u0002́̂\u0003\u0002\u0002\u0002̂̄\u0003\u0002\u0002\u0002̃̅\u0005`1\u0002̄̃\u0003\u0002\u0002\u0002̄̅\u0003\u0002\u0002\u0002̅;\u0003\u0002\u0002\u0002̆̈\u0007\u0084\u0002\u0002̇̉\t\u0005\u0002\u0002̈̇\u0003\u0002\u0002\u0002̈̉\u0003\u0002\u0002\u0002̉̊\u0003\u0002\u0002\u0002̊̏\u0005d3\u0002̋̌\u0007\u0007\u0002\u0002̌̎\u0005d3\u0002̍̋\u0003\u0002\u0002\u0002̎̑\u0003\u0002\u0002\u0002̏̍\u0003\u0002\u0002\u0002̏̐\u0003\u0002\u0002\u0002̞̐\u0003\u0002\u0002\u0002̑̏\u0003\u0002\u0002\u0002̜̒\u0007O\u0002\u0002̘̓\u0005f4\u0002̔̕\u0007\u0007\u0002\u0002̗̕\u0005f4\u0002̖̔\u0003\u0002\u0002\u0002̗̚\u0003\u0002\u0002\u0002̘̖\u0003\u0002\u0002\u0002̘̙\u0003\u0002\u0002\u0002̙̝\u0003\u0002\u0002\u0002̘̚\u0003\u0002\u0002\u0002̛̝\u0005h5\u0002̜̓\u0003\u0002\u0002\u0002̛̜\u0003\u0002\u0002\u0002̝̟\u0003\u0002\u0002\u0002̞̒\u0003\u0002\u0002\u0002̞̟\u0003\u0002\u0002\u0002̢̟\u0003\u0002\u0002\u0002̡̠\u0007\u0096\u0002\u0002̡̣\u0005L'\u0002̢̠\u0003\u0002\u0002\u0002̢̣\u0003\u0002\u0002\u0002̣̲\u0003\u0002\u0002\u0002̤̥\u0007R\u0002\u0002̥̦\u0007,\u0002\u0002̦̫\u0005L'\u0002̧̨\u0007\u0007\u0002\u0002̨̪\u0005L'\u0002̧̩\u0003\u0002\u0002\u0002̪̭\u0003\u0002\u0002\u0002̫̩\u0003\u0002\u0002\u0002̫̬\u0003\u0002\u0002\u0002̬̰\u0003\u0002\u0002\u0002̭̫\u0003\u0002\u0002\u0002̮̯\u0007S\u0002\u0002̯̱\u0005L'\u0002̰̮\u0003\u0002\u0002\u0002̰̱\u0003\u0002\u0002\u0002̱̳\u0003\u0002\u0002\u0002̲̤\u0003\u0002\u0002\u0002̲̳\u0003\u0002\u0002\u0002̳͑\u0003\u0002\u0002\u0002̴̵\u0007\u0092\u0002\u0002̵̶\u0007\u0005\u0002\u0002̶̻\u0005L'\u0002̷̸\u0007\u0007\u0002\u0002̸̺\u0005L'\u0002̷̹\u0003\u0002\u0002\u0002̺̽\u0003\u0002\u0002\u0002̻̹\u0003\u0002\u0002\u0002̻̼\u0003\u0002\u0002\u0002̼̾\u0003\u0002\u0002\u0002̻̽\u0003\u0002\u0002\u0002͍̾\u0007\u0006\u0002\u0002̿̀\u0007\u0007\u0002\u0002̀́\u0007\u0005\u0002\u0002́͆\u0005L'\u0002͂̓\u0007\u0007\u0002\u0002̓ͅ\u0005L'\u0002̈́͂\u0003\u0002\u0002\u0002͈ͅ\u0003\u0002\u0002\u0002͆̈́\u0003\u0002\u0002\u0002͇͆\u0003\u0002\u0002\u0002͇͉\u0003\u0002\u0002\u0002͈͆\u0003\u0002\u0002\u0002͉͊\u0007\u0006\u0002\u0002͊͌\u0003\u0002\u0002\u0002͋̿\u0003\u0002\u0002\u0002͌͏\u0003\u0002\u0002\u0002͍͋\u0003\u0002\u0002\u0002͍͎\u0003\u0002\u0002\u0002͎͑\u0003\u0002\u0002\u0002͏͍\u0003\u0002\u0002\u0002͐̆\u0003\u0002\u0002\u0002̴͐\u0003\u0002\u0002\u0002͑=\u0003\u0002\u0002\u0002͔͒\u0005V,\u0002͓͒\u0003\u0002\u0002\u0002͓͔\u0003\u0002\u0002\u0002͔͕\u0003\u0002\u0002\u0002͕͠\u0007\u008f\u0002\u0002͖͗\u0007p\u0002\u0002͗͡\u0007\u0081\u0002\u0002͙͘\u0007p\u0002\u0002͙͡\u0007\u001d\u0002\u0002͚͛\u0007p\u0002\u0002͛͡\u0007~\u0002\u0002͜͝\u0007p\u0002\u0002͝͡\u0007L\u0002\u0002͟͞\u0007p\u0002\u0002͟͡\u0007U\u0002\u0002͖͠\u0003\u0002\u0002\u0002͘͠\u0003\u0002\u0002\u0002͚͠\u0003\u0002\u0002\u0002͜͠\u0003\u0002\u0002\u0002͠͞\u0003\u0002\u0002\u0002͠͡\u0003\u0002\u0002\u0002͢͡\u0003\u0002\u0002\u0002ͣ͢\u0005Z.\u0002ͣͦ\u0007\u0085\u0002\u0002ͤͧ\u0005\u0092J\u0002ͥͧ\u0005\u0080A\u0002ͦͤ\u0003\u0002\u0002\u0002ͦͥ\u0003\u0002\u0002\u0002ͧͨ\u0003\u0002\u0002\u0002ͨͩ\u0007\b\u0002\u0002ͩʹ\u0005L'\u0002ͪͭ\u0007\u0007\u0002\u0002ͫͮ\u0005\u0092J\u0002ͬͮ\u0005\u0080A\u0002ͭͫ\u0003\u0002\u0002\u0002ͭͬ\u0003\u0002\u0002\u0002ͮͯ\u0003\u0002\u0002\u0002ͯͰ\u0007\b\u0002\u0002Ͱͱ\u0005L'\u0002ͱͳ\u0003\u0002\u0002\u0002Ͳͪ\u0003\u0002\u0002\u0002ͳͶ\u0003\u0002\u0002\u0002ʹͲ\u0003\u0002\u0002\u0002ʹ͵\u0003\u0002\u0002\u0002͵\u0379\u0003\u0002\u0002\u0002Ͷʹ\u0003\u0002\u0002\u0002ͷ\u0378\u0007\u0096\u0002\u0002\u0378ͺ\u0005L'\u0002\u0379ͷ\u0003\u0002\u0002\u0002\u0379ͺ\u0003\u0002\u0002\u0002ͺ?\u0003\u0002\u0002\u0002ͻͽ\u0005V,\u0002ͼͻ\u0003\u0002\u0002\u0002ͼͽ\u0003\u0002\u0002\u0002ͽ;\u0003\u0002\u0002\u0002;Ή\u0007\u008f\u0002\u0002Ϳ\u0380\u0007p\u0002\u0002\u0380Ί\u0007\u0081\u0002\u0002\u0381\u0382\u0007p\u0002\u0002\u0382Ί\u0007\u001d\u0002\u0002\u0383΄\u0007p\u0002\u0002΄Ί\u0007~\u0002\u0002΅Ά\u0007p\u0002\u0002ΆΊ\u0007L\u0002\u0002·Έ\u0007p\u0002\u0002ΈΊ\u0007U\u0002\u0002ΉͿ\u0003\u0002\u0002\u0002Ή\u0381\u0003\u0002\u0002\u0002Ή\u0383\u0003\u0002\u0002\u0002Ή΅\u0003\u0002\u0002\u0002Ή·\u0003\u0002\u0002\u0002ΉΊ\u0003\u0002\u0002\u0002Ί\u038b\u0003\u0002\u0002\u0002\u038bΌ\u0005Z.\u0002ΌΏ\u0007\u0085\u0002\u0002\u038dΐ\u0005\u0092J\u0002Ύΐ\u0005\u0080A\u0002Ώ\u038d\u0003\u0002\u0002\u0002ΏΎ\u0003\u0002\u0002\u0002ΐΑ\u0003\u0002\u0002\u0002ΑΒ\u0007\b\u0002\u0002ΒΝ\u0005L'\u0002ΓΖ\u0007\u0007\u0002\u0002ΔΗ\u0005\u0092J\u0002ΕΗ\u0005\u0080A\u0002ΖΔ\u0003\u0002\u0002\u0002ΖΕ\u0003\u0002\u0002\u0002ΗΘ\u0003\u0002\u0002\u0002ΘΙ\u0007\b\u0002\u0002ΙΚ\u0005L'\u0002ΚΜ\u0003\u0002\u0002\u0002ΛΓ\u0003\u0002\u0002\u0002ΜΟ\u0003\u0002\u0002\u0002ΝΛ\u0003\u0002\u0002\u0002ΝΞ\u0003\u0002\u0002\u0002Ξ\u03a2\u0003\u0002\u0002\u0002ΟΝ\u0003\u0002\u0002\u0002ΠΡ\u0007\u0096\u0002\u0002ΡΣ\u0005L'\u0002\u03a2Π\u0003\u0002\u0002\u0002\u03a2Σ\u0003\u0002\u0002\u0002ΣΨ\u0003\u0002\u0002\u0002ΤΦ\u0005\\/\u0002ΥΤ\u0003\u0002\u0002\u0002ΥΦ\u0003\u0002\u0002\u0002ΦΧ\u0003\u0002\u0002\u0002ΧΩ\u0005`1\u0002ΨΥ\u0003\u0002\u0002\u0002ΨΩ\u0003\u0002\u0002\u0002ΩA\u0003\u0002\u0002\u0002Ϊά\u0007\u0091\u0002\u0002Ϋέ\u0005\u0088E\u0002άΫ\u0003\u0002\u0002\u0002άέ\u0003\u0002\u0002\u0002έC\u0003\u0002\u0002\u0002ήΰ\u0005\u0092J\u0002ία\u0005F$\u0002ΰί\u0003\u0002\u0002\u0002ΰα\u0003\u0002\u0002\u0002αε\u0003\u0002\u0002\u0002βδ\u0005H%\u0002γβ\u0003\u0002\u0002\u0002δη\u0003\u0002\u0002\u0002εγ\u0003\u0002\u0002\u0002εζ\u0003\u0002\u0002\u0002ζE\u0003\u0002\u0002\u0002ηε\u0003\u0002\u0002\u0002θκ\u0005\u0084C\u0002ιθ\u0003\u0002\u0002\u0002κλ\u0003\u0002\u0002\u0002λμ\u0003\u0002\u0002\u0002λι\u0003\u0002\u0002\u0002μχ\u0003\u0002\u0002\u0002νξ\u0007\u0005\u0002\u0002ξο\u0005p9\u0002οπ\u0007\u0006\u0002\u0002πψ\u0003\u0002\u0002\u0002ρς\u0007\u0005\u0002\u0002ςσ\u0005p9\u0002στ\u0007\u0007\u0002\u0002τυ\u0005p9\u0002υφ\u0007\u0006\u0002\u0002φψ\u0003\u0002\u0002\u0002χν\u0003\u0002\u0002\u0002χρ\u0003\u0002\u0002\u0002χψ\u0003\u0002\u0002\u0002ψG\u0003\u0002\u0002\u0002ωϊ\u00075\u0002\u0002ϊό\u0005\u0084C\u0002ϋω\u0003\u0002\u0002\u0002ϋό\u0003\u0002\u0002\u0002όϮ\u0003\u0002\u0002\u0002ύώ\u0007u\u0002\u0002ώϐ\u0007c\u0002\u0002Ϗϑ\t\u0006\u0002\u0002ϐϏ\u0003\u0002\u0002\u0002ϐϑ\u0003\u0002\u0002\u0002ϑϒ\u0003\u0002\u0002\u0002ϒϔ\u0005J&\u0002ϓϕ\u0007(\u0002\u0002ϔϓ\u0003\u0002\u0002\u0002ϔϕ\u0003\u0002\u0002\u0002ϕϯ\u0003\u0002\u0002\u0002ϖϘ\u0007j\u0002\u0002ϗϖ\u0003\u0002\u0002\u0002ϗϘ\u0003\u0002\u0002\u0002Ϙϙ\u0003\u0002\u0002\u0002ϙϚ\u0007l\u0002\u0002Ϛϯ\u0005J&\u0002ϛϜ\u0007\u008e\u0002\u0002Ϝϯ\u0005J&\u0002ϝϞ\u00070\u0002\u0002Ϟϟ\u0007\u0005\u0002\u0002ϟϠ\u0005L'\u0002Ϡϡ\u0007\u0006\u0002\u0002ϡϯ\u0003\u0002\u0002\u0002Ϣϩ\u0007<\u0002\u0002ϣϪ\u0005p9\u0002ϤϪ\u0005r:\u0002ϥϦ\u0007\u0005\u0002\u0002Ϧϧ\u0005L'\u0002ϧϨ\u0007\u0006\u0002\u0002ϨϪ\u0003\u0002\u0002\u0002ϩϣ\u0003\u0002\u0002\u0002ϩϤ\u0003\u0002\u0002\u0002ϩϥ\u0003\u0002\u0002\u0002Ϫϯ\u0003\u0002\u0002\u0002ϫϬ\u00071\u0002\u0002Ϭϯ\u0005\u0094K\u0002ϭϯ\u0005N(\u0002Ϯύ\u0003\u0002\u0002\u0002Ϯϗ\u0003\u0002\u0002\u0002Ϯϛ\u0003\u0002\u0002\u0002Ϯϝ\u0003\u0002\u0002\u0002ϮϢ\u0003\u0002\u0002\u0002Ϯϫ\u0003\u0002\u0002\u0002Ϯϭ\u0003\u0002\u0002\u0002ϯI\u0003\u0002\u0002\u0002ϰϱ\u0007o\u0002\u0002ϱϲ\u00074\u0002\u0002ϲϴ\t\u0007\u0002\u0002ϳϰ\u0003\u0002\u0002\u0002ϳϴ\u0003\u0002\u0002\u0002ϴK\u0003\u0002\u0002\u0002ϵ϶\b'\u0001\u0002϶щ\u0005r:\u0002Ϸщ\u0007\u009e\u0002\u0002ϸϹ\u0005\u0088E\u0002ϹϺ\u0007\u0004\u0002\u0002Ϻϼ\u0003\u0002\u0002\u0002ϻϸ\u0003\u0002\u0002\u0002ϻϼ\u0003\u0002\u0002\u0002ϼϽ\u0003\u0002\u0002\u0002ϽϾ\u0005\u008cG\u0002ϾϿ\u0007\u0004\u0002\u0002ϿЁ\u0003\u0002\u0002\u0002Ѐϻ\u0003\u0002\u0002\u0002ЀЁ\u0003\u0002\u0002\u0002ЁЂ\u0003\u0002\u0002\u0002Ђщ\u0005\u0092J\u0002ЃЄ\u0005v<\u0002ЄЅ\u0005L'\u0010Ѕщ\u0003\u0002\u0002\u0002ІЇ\u0005\u0086D\u0002ЇД\u0007\u0005\u0002\u0002ЈЊ\u0007B\u0002\u0002ЉЈ\u0003\u0002\u0002\u0002ЉЊ\u0003\u0002\u0002\u0002ЊЋ\u0003\u0002\u0002\u0002ЋА\u0005L'\u0002ЌЍ\u0007\u0007\u0002\u0002ЍЏ\u0005L'\u0002ЎЌ\u0003\u0002\u0002\u0002ЏВ\u0003\u0002\u0002\u0002АЎ\u0003\u0002\u0002\u0002АБ\u0003\u0002\u0002\u0002БЕ\u0003\u0002\u0002\u0002ВА\u0003\u0002\u0002\u0002ГЕ\u0007\t\u0002\u0002ДЉ\u0003\u0002\u0002\u0002ДГ\u0003\u0002\u0002\u0002ДЕ\u0003\u0002\u0002\u0002ЕЖ\u0003\u0002\u0002\u0002ЖЗ\u0007\u0006\u0002\u0002Зщ\u0003\u0002\u0002\u0002ИЙ\u0007\u0005\u0002\u0002ЙО\u0005L'\u0002КЛ\u0007\u0007\u0002\u0002ЛН\u0005L'\u0002МК\u0003\u0002\u0002\u0002НР\u0003\u0002\u0002\u0002ОМ\u0003\u0002\u0002\u0002ОП\u0003\u0002\u0002\u0002ПС\u0003\u0002\u0002\u0002РО\u0003\u0002\u0002\u0002СТ\u0007\u0006\u0002\u0002Тщ\u0003\u0002\u0002\u0002УФ\u0007/\u0002\u0002ФХ\u0007\u0005\u0002\u0002ХЦ\u0005L'\u0002ЦЧ\u0007%\u0002\u0002ЧШ\u0005F$\u0002ШЩ\u0007\u0006\u0002\u0002Щщ\u0003\u0002\u0002\u0002ЪЬ\u0007j\u0002\u0002ЫЪ\u0003\u0002\u0002\u0002ЫЬ\u0003\u0002\u0002\u0002ЬЭ\u0003\u0002\u0002\u0002ЭЯ\u0007J\u0002\u0002ЮЫ\u0003\u0002\u0002\u0002ЮЯ\u0003\u0002\u0002\u0002Яа\u0003\u0002\u0002\u0002аб\u0007\u0005\u0002\u0002бв\u0005:\u001e\u0002вг\u0007\u0006\u0002\u0002гщ\u0003\u0002\u0002\u0002дж\u0007.\u0002\u0002ез\u0005L'\u0002же\u0003\u0002\u0002\u0002жз\u0003\u0002\u0002\u0002зн\u0003\u0002\u0002\u0002ий\u0007\u0095\u0002\u0002йк\u0005L'\u0002кл\u0007\u0089\u0002\u0002лм\u0005L'\u0002мо\u0003\u0002\u0002\u0002ни\u0003\u0002\u0002\u0002оп\u0003\u0002\u0002\u0002пн\u0003\u0002\u0002\u0002пр\u0003\u0002\u0002\u0002ру\u0003\u0002\u0002\u0002ст\u0007E\u0002\u0002тф\u0005L'\u0002ус\u0003\u0002\u0002\u0002уф\u0003\u0002\u0002\u0002фх\u0003\u0002\u0002\u0002хц\u0007F\u0002\u0002цщ\u0003\u0002\u0002\u0002чщ\u0005P)\u0002шϵ\u0003\u0002\u0002\u0002шϷ\u0003\u0002\u0002\u0002шЀ\u0003\u0002\u0002\u0002шЃ\u0003\u0002\u0002\u0002шІ\u0003\u0002\u0002\u0002шИ\u0003\u0002\u0002\u0002шУ\u0003\u0002\u0002\u0002шЮ\u0003\u0002\u0002\u0002шд\u0003\u0002\u0002\u0002шч\u0003\u0002\u0002\u0002щҠ\u0003\u0002\u0002\u0002ъы\f\u000f\u0002\u0002ыь\u0005x=\u0002ьэ\u0005L'\u0010эҟ\u0003\u0002\u0002\u0002юя\f\b\u0002\u0002яё\u0007`\u0002\u0002ѐђ\u0007j\u0002\u0002ёѐ\u0003\u0002\u0002\u0002ёђ\u0003\u0002\u0002\u0002ђѓ\u0003\u0002\u0002\u0002ѓҟ\u0005L'\tєі\f\u0007\u0002\u0002ѕї\u0007j\u0002\u0002іѕ\u0003\u0002\u0002\u0002ії\u0003\u0002\u0002\u0002їј\u0003\u0002\u0002\u0002јљ\u0007+\u0002\u0002љњ\u0005L'\u0002њћ\u0007$\u0002\u0002ћќ\u0005L'\bќҟ\u0003\u0002\u0002\u0002ѝў\f\u000b\u0002\u0002ўџ\u00071\u0002\u0002џҟ\u0005\u0094K\u0002ѠѢ\f\n\u0002\u0002ѡѣ\u0007j\u0002\u0002Ѣѡ\u0003\u0002\u0002\u0002Ѣѣ\u0003\u0002\u0002\u0002ѣѤ\u0003\u0002\u0002\u0002Ѥѥ\t\b\u0002\u0002ѥѨ\u0005L'\u0002Ѧѧ\u0007G\u0002\u0002ѧѩ\u0005L'\u0002ѨѦ\u0003\u0002\u0002\u0002Ѩѩ\u0003\u0002\u0002\u0002ѩҟ\u0003\u0002\u0002\u0002Ѫѯ\f\t\u0002\u0002ѫѰ\u0007a\u0002\u0002ѬѰ\u0007k\u0002\u0002ѭѮ\u0007j\u0002\u0002ѮѰ\u0007l\u0002\u0002ѯѫ\u0003\u0002\u0002\u0002ѯѬ\u0003\u0002\u0002\u0002ѯѭ\u0003\u0002\u0002\u0002Ѱҟ\u0003\u0002\u0002\u0002ѱѳ\f\u0006\u0002\u0002ѲѴ\u0007j\u0002\u0002ѳѲ\u0003\u0002\u0002\u0002ѳѴ\u0003\u0002\u0002\u0002Ѵѵ\u0003\u0002\u0002\u0002ѵҜ\u0007W\u0002\u0002ѶҀ\u0007\u0005\u0002\u0002ѷҁ\u0005:\u001e\u0002Ѹѽ\u0005L'\u0002ѹѺ\u0007\u0007\u0002\u0002ѺѼ\u0005L'\u0002ѻѹ\u0003\u0002\u0002\u0002Ѽѿ\u0003\u0002\u0002\u0002ѽѻ\u0003\u0002\u0002\u0002ѽѾ\u0003\u0002\u0002\u0002Ѿҁ\u0003\u0002\u0002\u0002ѿѽ\u0003\u0002\u0002\u0002Ҁѷ\u0003\u0002\u0002\u0002ҀѸ\u0003\u0002\u0002\u0002Ҁҁ\u0003\u0002\u0002\u0002ҁ҂\u0003\u0002\u0002\u0002҂ҝ\u0007\u0006\u0002\u0002҃҄\u0005\u0088E\u0002҄҅\u0007\u0004\u0002\u0002҅҇\u0003\u0002\u0002\u0002҆҃\u0003\u0002\u0002\u0002҆҇\u0003\u0002\u0002\u0002҇҈\u0003\u0002\u0002\u0002҈ҝ\u0005\u008cG\u0002҉Ҋ\u0005\u0088E\u0002Ҋҋ\u0007\u0004\u0002\u0002ҋҍ\u0003\u0002\u0002\u0002Ҍ҉\u0003\u0002\u0002\u0002Ҍҍ\u0003\u0002\u0002\u0002ҍҎ\u0003\u0002\u0002\u0002Ҏҏ\u0005\u008aF\u0002ҏҘ\u0007\u0005\u0002\u0002Ґҕ\u0005L'\u0002ґҒ\u0007\u0007\u0002\u0002ҒҔ\u0005L'\u0002ғґ\u0003\u0002\u0002\u0002Ҕҗ\u0003\u0002\u0002\u0002ҕғ\u0003\u0002\u0002\u0002ҕҖ\u0003\u0002\u0002\u0002Җҙ\u0003\u0002\u0002\u0002җҕ\u0003\u0002\u0002\u0002ҘҐ\u0003\u0002\u0002\u0002Ҙҙ\u0003\u0002\u0002\u0002ҙҚ\u0003\u0002\u0002\u0002Ққ\u0007\u0006\u0002\u0002қҝ\u0003\u0002\u0002\u0002ҜѶ\u0003\u0002\u0002\u0002Ҝ҆\u0003\u0002\u0002\u0002ҜҌ\u0003\u0002\u0002\u0002ҝҟ\u0003\u0002\u0002\u0002Ҟъ\u0003\u0002\u0002\u0002Ҟю\u0003\u0002\u0002\u0002Ҟє\u0003\u0002\u0002\u0002Ҟѝ\u0003\u0002\u0002\u0002ҞѠ\u0003\u0002\u0002\u0002ҞѪ\u0003\u0002\u0002\u0002Ҟѱ\u0003\u0002\u0002\u0002ҟҢ\u0003\u0002\u0002\u0002ҠҞ\u0003\u0002\u0002\u0002Ҡҡ\u0003\u0002\u0002\u0002ҡM\u0003\u0002\u0002\u0002ҢҠ\u0003\u0002\u0002\u0002ңҤ\u0007y\u0002\u0002ҤҰ\u0005\u0096L\u0002ҥҦ\u0007\u0005\u0002\u0002Ҧҫ\u0005\u0092J\u0002ҧҨ\u0007\u0007\u0002\u0002ҨҪ\u0005\u0092J\u0002ҩҧ\u0003\u0002\u0002\u0002Ҫҭ\u0003\u0002\u0002\u0002ҫҩ\u0003\u0002\u0002\u0002ҫҬ\u0003\u0002\u0002\u0002ҬҮ\u0003\u0002\u0002\u0002ҭҫ\u0003\u0002\u0002\u0002Үү\u0007\u0006\u0002\u0002үұ\u0003\u0002\u0002\u0002Ұҥ\u0003\u0002\u0002\u0002Ұұ\u0003\u0002\u0002\u0002ұӄ\u0003\u0002\u0002\u0002Ҳҳ\u0007o\u0002\u0002ҳҼ\t\t\u0002\u0002Ҵҵ\u0007\u0085\u0002\u0002ҵҽ\u0007l\u0002\u0002Ҷҷ\u0007\u0085\u0002\u0002ҷҽ\u0007<\u0002\u0002Ҹҽ\u0007-\u0002\u0002ҹҽ\u0007\u007f\u0002\u0002Һһ\u0007i\u0002\u0002һҽ\u0007\u001e\u0002\u0002ҼҴ\u0003\u0002\u0002\u0002ҼҶ\u0003\u0002\u0002\u0002ҼҸ\u0003\u0002\u0002\u0002Ҽҹ\u0003\u0002\u0002\u0002ҼҺ\u0003\u0002\u0002\u0002ҽӁ\u0003\u0002\u0002\u0002Ҿҿ\u0007g\u0002\u0002ҿӁ\u0005\u0084C\u0002ӀҲ\u0003\u0002\u0002\u0002ӀҾ\u0003\u0002\u0002\u0002ӁӃ\u0003\u0002\u0002\u0002ӂӀ\u0003\u0002\u0002\u0002Ӄӆ\u0003\u0002\u0002\u0002ӄӂ\u0003\u0002\u0002\u0002ӄӅ\u0003\u0002\u0002\u0002Ӆӑ\u0003\u0002\u0002\u0002ӆӄ\u0003\u0002\u0002\u0002ӇӉ\u0007j\u0002\u0002ӈӇ\u0003\u0002\u0002\u0002ӈӉ\u0003\u0002\u0002\u0002Ӊӊ\u0003\u0002\u0002\u0002ӊӏ\u0007=\u0002\u0002Ӌӌ\u0007Z\u0002\u0002ӌӐ\u0007>\u0002\u0002Ӎӎ\u0007Z\u0002\u0002ӎӐ\u0007V\u0002\u0002ӏӋ\u0003\u0002\u0002\u0002ӏӍ\u0003\u0002\u0002\u0002ӏӐ\u0003\u0002\u0002\u0002ӐӒ\u0003\u0002\u0002\u0002ӑӈ\u0003\u0002\u0002\u0002ӑӒ\u0003\u0002\u0002\u0002ӒO\u0003\u0002\u0002\u0002ӓӔ\u0007w\u0002\u0002Ӕә\u0007\u0005\u0002\u0002ӕӚ\u0007U\u0002\u0002Ӗӗ\t\n\u0002\u0002ӗӘ\u0007\u0007\u0002\u0002ӘӚ\u0005z>\u0002әӕ\u0003\u0002\u0002\u0002әӖ\u0003\u0002\u0002\u0002Ӛӛ\u0003\u0002\u0002\u0002ӛӜ\u0007\u0006\u0002\u0002ӜQ\u0003\u0002\u0002\u0002ӝӠ\u0005\u0092J\u0002ӞӠ\u0005L'\u0002ӟӝ\u0003\u0002\u0002\u0002ӟӞ\u0003\u0002\u0002\u0002Ӡӣ\u0003\u0002\u0002\u0002ӡӢ\u00071\u0002\u0002ӢӤ\u0005\u0094K\u0002ӣӡ\u0003\u0002\u0002\u0002ӣӤ\u0003\u0002\u0002\u0002ӤӦ\u0003\u0002\u0002\u0002ӥӧ\t\u0006\u0002\u0002Ӧӥ\u0003\u0002\u0002\u0002Ӧӧ\u0003\u0002\u0002\u0002ӧS\u0003\u0002\u0002\u0002Өө\u00075\u0002\u0002өӫ\u0005\u0084C\u0002ӪӨ\u0003\u0002\u0002\u0002Ӫӫ\u0003\u0002\u0002\u0002ӫԐ\u0003\u0002\u0002\u0002Ӭӭ\u0007u\u0002\u0002ӭӰ\u0007c\u0002\u0002ӮӰ\u0007\u008e\u0002\u0002ӯӬ\u0003\u0002\u0002\u0002ӯӮ\u0003\u0002\u0002\u0002Ӱӱ\u0003\u0002\u0002\u0002ӱӲ\u0007\u0005\u0002\u0002Ӳӷ\u0005R*\u0002ӳӴ\u0007\u0007\u0002\u0002ӴӶ\u0005R*\u0002ӵӳ\u0003\u0002\u0002\u0002Ӷӹ\u0003\u0002\u0002\u0002ӷӵ\u0003\u0002\u0002\u0002ӷӸ\u0003\u0002\u0002\u0002ӸӺ\u0003\u0002\u0002\u0002ӹӷ\u0003\u0002\u0002\u0002Ӻӻ\u0007\u0006\u0002\u0002ӻӼ\u0005J&\u0002Ӽԑ\u0003\u0002\u0002\u0002ӽӾ\u00070\u0002\u0002Ӿӿ\u0007\u0005\u0002\u0002ӿԀ\u0005L'\u0002Ԁԁ\u0007\u0006\u0002\u0002ԁԑ\u0003\u0002\u0002\u0002Ԃԃ\u0007N\u0002\u0002ԃԄ\u0007c\u0002\u0002Ԅԅ\u0007\u0005\u0002\u0002ԅԊ\u0005\u0092J\u0002Ԇԇ\u0007\u0007\u0002\u0002ԇԉ\u0005\u0092J\u0002ԈԆ\u0003\u0002\u0002\u0002ԉԌ\u0003\u0002\u0002\u0002ԊԈ\u0003\u0002\u0002\u0002Ԋԋ\u0003\u0002\u0002\u0002ԋԍ\u0003\u0002\u0002\u0002ԌԊ\u0003\u0002\u0002\u0002ԍԎ\u0007\u0006\u0002\u0002Ԏԏ\u0005N(\u0002ԏԑ\u0003\u0002\u0002\u0002Ԑӯ\u0003\u0002\u0002\u0002Ԑӽ\u0003\u0002\u0002\u0002ԐԂ\u0003\u0002\u0002\u0002ԑU\u0003\u0002\u0002\u0002ԒԔ\u0007\u0097\u0002\u0002ԓԕ\u0007x\u0002\u0002Ԕԓ\u0003\u0002\u0002\u0002Ԕԕ\u0003\u0002\u0002\u0002ԕԖ\u0003\u0002\u0002\u0002Ԗԛ\u0005X-\u0002ԗԘ\u0007\u0007\u0002\u0002ԘԚ\u0005X-\u0002ԙԗ\u0003\u0002\u0002\u0002Ԛԝ\u0003\u0002\u0002\u0002ԛԙ\u0003\u0002\u0002\u0002ԛԜ\u0003\u0002\u0002\u0002ԜW\u0003\u0002\u0002\u0002ԝԛ\u0003\u0002\u0002\u0002ԞԪ\u0005\u008cG\u0002ԟԠ\u0007\u0005\u0002\u0002Ԡԥ\u0005\u0092J\u0002ԡԢ\u0007\u0007\u0002\u0002ԢԤ\u0005\u0092J\u0002ԣԡ\u0003\u0002\u0002\u0002Ԥԧ\u0003\u0002\u0002\u0002ԥԣ\u0003\u0002\u0002\u0002ԥԦ\u0003\u0002\u0002\u0002ԦԨ\u0003\u0002\u0002\u0002ԧԥ\u0003\u0002\u0002\u0002Ԩԩ\u0007\u0006\u0002\u0002ԩԫ\u0003\u0002\u0002\u0002Ԫԟ\u0003\u0002\u0002\u0002Ԫԫ\u0003\u0002\u0002\u0002ԫԬ\u0003\u0002\u0002\u0002Ԭԭ\u0007%\u0002\u0002ԭԮ\u0007\u0005\u0002\u0002Ԯԯ\u0005:\u001e\u0002ԯ\u0530\u0007\u0006\u0002\u0002\u0530Y\u0003\u0002\u0002\u0002ԱԲ\u0005\u0088E\u0002ԲԳ\u0007\u0004\u0002\u0002ԳԵ\u0003\u0002\u0002\u0002ԴԱ\u0003\u0002\u0002\u0002ԴԵ\u0003\u0002\u0002\u0002ԵԶ\u0003\u0002\u0002\u0002ԶԹ\u0005\u008cG\u0002ԷԸ\u0007%\u0002\u0002ԸԺ\u0005¤S\u0002ԹԷ\u0003\u0002\u0002\u0002ԹԺ\u0003\u0002\u0002\u0002ԺՀ\u0003\u0002\u0002\u0002ԻԼ\u0007Y\u0002\u0002ԼԽ\u0007,\u0002\u0002ԽՁ\u0005\u0098M\u0002ԾԿ\u0007j\u0002\u0002ԿՁ\u0007Y\u0002\u0002ՀԻ\u0003\u0002\u0002\u0002ՀԾ\u0003\u0002\u0002\u0002ՀՁ\u0003\u0002\u0002\u0002Ձ[\u0003\u0002\u0002\u0002ՂՃ\u0007q\u0002\u0002ՃՄ\u0007,\u0002\u0002ՄՉ\u0005^0\u0002ՅՆ\u0007\u0007\u0002\u0002ՆՈ\u0005^0\u0002ՇՅ\u0003\u0002\u0002\u0002ՈՋ\u0003\u0002\u0002\u0002ՉՇ\u0003\u0002\u0002\u0002ՉՊ\u0003\u0002\u0002\u0002Պ]\u0003\u0002\u0002\u0002ՋՉ\u0003\u0002\u0002\u0002ՌՏ\u0005L'\u0002ՍՎ\u00071\u0002\u0002ՎՐ\u0005\u0094K\u0002ՏՍ\u0003\u0002\u0002\u0002ՏՐ\u0003\u0002\u0002\u0002ՐՒ\u0003\u0002\u0002\u0002ՑՓ\t\u0006\u0002\u0002ՒՑ\u0003\u0002\u0002\u0002ՒՓ\u0003\u0002\u0002\u0002Փ_\u0003\u0002\u0002\u0002ՔՕ\u0007f\u0002\u0002Օ\u0558\u0005L'\u0002Ֆ\u0557\t\u000b\u0002\u0002\u0557ՙ\u0005L'\u0002\u0558Ֆ\u0003\u0002\u0002\u0002\u0558ՙ\u0003\u0002\u0002\u0002ՙa\u0003\u0002\u0002\u0002՚՟\u0005p9\u0002՛՟\u0005\u0084C\u0002՜՟\u0007\u009f\u0002\u0002՝՟\u0005t;\u0002՞՚\u0003\u0002\u0002\u0002՞՛\u0003\u0002\u0002\u0002՞՜\u0003\u0002\u0002\u0002՞՝\u0003\u0002\u0002\u0002՟c\u0003\u0002\u0002\u0002\u0560խ\u0007\t\u0002\u0002աբ\u0005\u008cG\u0002բգ\u0007\u0004\u0002\u0002գդ\u0007\t\u0002\u0002դխ\u0003\u0002\u0002\u0002եժ\u0005L'\u0002զը\u0007%\u0002\u0002էզ\u0003\u0002\u0002\u0002էը\u0003\u0002\u0002\u0002ըթ\u0003\u0002\u0002\u0002թի\u0005~@\u0002ժէ\u0003\u0002\u0002\u0002ժի\u0003\u0002\u0002\u0002իխ\u0003\u0002\u0002\u0002լ\u0560\u0003\u0002\u0002\u0002լա\u0003\u0002\u0002\u0002լե\u0003\u0002\u0002\u0002խe\u0003\u0002\u0002\u0002ծկ\u0005\u0088E\u0002կհ\u0007\u0004\u0002\u0002հղ\u0003\u0002\u0002\u0002ձծ\u0003\u0002\u0002\u0002ձղ\u0003\u0002\u0002\u0002ղճ\u0003\u0002\u0002\u0002ճո\u0005\u008cG\u0002մն\u0007%\u0002\u0002յմ\u0003\u0002\u0002\u0002յն\u0003\u0002\u0002\u0002նշ\u0003\u0002\u0002\u0002շչ\u0005¤S\u0002ոյ\u0003\u0002\u0002\u0002ոչ\u0003\u0002\u0002\u0002չտ\u0003\u0002\u0002\u0002պջ\u0007Y\u0002\u0002ջռ\u0007,\u0002\u0002ռր\u0005\u0098M\u0002սվ\u0007j\u0002\u0002վր\u0007Y\u0002\u0002տպ\u0003\u0002\u0002\u0002տս\u0003\u0002\u0002\u0002տր\u0003\u0002\u0002\u0002րֱ\u0003\u0002\u0002\u0002ցւ\u0005\u0088E\u0002ւփ\u0007\u0004\u0002\u0002փօ\u0003\u0002\u0002\u0002քց\u0003\u0002\u0002\u0002քօ\u0003\u0002\u0002\u0002օֆ\u0003\u0002\u0002\u0002ֆև\u0005\u008aF\u0002և\u0590\u0007\u0005\u0002\u0002\u0588֍\u0005L'\u0002։֊\u0007\u0007\u0002\u0002֊\u058c\u0005L'\u0002\u058b։\u0003\u0002\u0002\u0002\u058c֏\u0003\u0002\u0002\u0002֍\u058b\u0003\u0002\u0002\u0002֍֎\u0003\u0002\u0002\u0002֎֑\u0003\u0002\u0002\u0002֏֍\u0003\u0002\u0002\u0002\u0590\u0588\u0003\u0002\u0002\u0002\u0590֑\u0003\u0002\u0002\u0002֑֒\u0003\u0002\u0002\u0002֒֗\u0007\u0006\u0002\u0002֓֕\u0007%\u0002\u0002֔֓\u0003\u0002\u0002\u0002֔֕\u0003\u0002\u0002\u0002֖֕\u0003\u0002\u0002\u0002֖֘\u0005¤S\u0002֗֔\u0003\u0002\u0002\u0002֗֘\u0003\u0002\u0002\u0002ֱ֘\u0003\u0002\u0002\u0002֣֙\u0007\u0005\u0002\u0002֚֟\u0005f4\u0002֛֜\u0007\u0007\u0002\u0002֜֞\u0005f4\u0002֛֝\u0003\u0002\u0002\u0002֞֡\u0003\u0002\u0002\u0002֟֝\u0003\u0002\u0002\u0002֟֠\u0003\u0002\u0002\u0002֤֠\u0003\u0002\u0002\u0002֡֟\u0003\u0002\u0002\u0002֢֤\u0005h5\u0002֣֚\u0003\u0002\u0002\u0002֣֢\u0003\u0002\u0002\u0002֤֥\u0003\u0002\u0002\u0002֥֦\u0007\u0006\u0002\u0002ֱ֦\u0003\u0002\u0002\u0002֧֨\u0007\u0005\u0002\u0002֨֩\u0005:\u001e\u0002֮֩\u0007\u0006\u0002\u0002֪֬\u0007%\u0002\u0002֪֫\u0003\u0002\u0002\u0002֫֬\u0003\u0002\u0002\u0002֭֬\u0003\u0002\u0002\u0002֭֯\u0005¤S\u0002֮֫\u0003\u0002\u0002\u0002֮֯\u0003\u0002\u0002\u0002ֱ֯\u0003\u0002\u0002\u0002ְձ\u0003\u0002\u0002\u0002ְք\u0003\u0002\u0002\u0002ְ֙\u0003\u0002\u0002\u0002ְ֧\u0003\u0002\u0002\u0002ֱg\u0003\u0002\u0002\u0002ֲֹ\u0005f4\u0002ֳִ\u0005j6\u0002ִֵ\u0005f4\u0002ֵֶ\u0005l7\u0002ֶָ\u0003\u0002\u0002\u0002ֳַ\u0003\u0002\u0002\u0002ָֻ\u0003\u0002\u0002\u0002ַֹ\u0003\u0002\u0002\u0002ֹֺ\u0003\u0002\u0002\u0002ֺi\u0003\u0002\u0002\u0002ֹֻ\u0003\u0002\u0002\u0002ּ\u05ca\u0007\u0007\u0002\u0002ֽֿ\u0007h\u0002\u0002־ֽ\u0003\u0002\u0002\u0002־ֿ\u0003\u0002\u0002\u0002ֿ׆\u0003\u0002\u0002\u0002׀ׂ\u0007d\u0002\u0002ׁ׃\u0007r\u0002\u0002ׁׂ\u0003\u0002\u0002\u0002ׂ׃\u0003\u0002\u0002\u0002׃ׇ\u0003\u0002\u0002\u0002ׇׄ\u0007[\u0002\u0002ׇׅ\u00077\u0002\u0002׆׀\u0003\u0002\u0002\u0002׆ׄ\u0003\u0002\u0002\u0002׆ׅ\u0003\u0002\u0002\u0002׆ׇ\u0003\u0002\u0002\u0002ׇ\u05c8\u0003\u0002\u0002\u0002\u05c8\u05ca\u0007b\u0002\u0002\u05c9ּ\u0003\u0002\u0002\u0002\u05c9־\u0003\u0002\u0002\u0002\u05cak\u0003\u0002\u0002\u0002\u05cb\u05cc\u0007o\u0002\u0002\u05ccך\u0005L'\u0002\u05cd\u05ce\u0007\u0090\u0002\u0002\u05ce\u05cf\u0007\u0005\u0002\u0002\u05cfה\u0005\u0092J\u0002אב\u0007\u0007\u0002\u0002בד\u0005\u0092J\u0002גא\u0003\u0002\u0002\u0002דז\u0003\u0002\u0002\u0002הג\u0003\u0002\u0002\u0002הו\u0003\u0002\u0002\u0002וח\u0003\u0002\u0002\u0002זה\u0003\u0002\u0002\u0002חט\u0007\u0006\u0002\u0002טך\u0003\u0002\u0002\u0002י\u05cb\u0003\u0002\u0002\u0002י\u05cd\u0003\u0002\u0002\u0002יך\u0003\u0002\u0002\u0002ךm\u0003\u0002\u0002\u0002כס\u0007\u008d\u0002\u0002לם\u0007\u008d\u0002\u0002םס\u0007!\u0002\u0002מס\u0007^\u0002\u0002ןס\u0007H\u0002\u0002נכ\u0003\u0002\u0002\u0002נל\u0003\u0002\u0002\u0002נמ\u0003\u0002\u0002\u0002נן\u0003\u0002\u0002\u0002סo\u0003\u0002\u0002\u0002עפ\t\f\u0002\u0002ףע\u0003\u0002\u0002\u0002ףפ\u0003\u0002\u0002\u0002פץ\u0003\u0002\u0002\u0002ץצ\u0007\u009d\u0002\u0002צq\u0003\u0002\u0002\u0002קװ\u0007\u009d\u0002\u0002רװ\u0007\u009f\u0002\u0002שװ\u0007 \u0002\u0002תװ\u0007l\u0002\u0002\u05ebװ\u00079\u0002\u0002\u05ecװ\u00078\u0002\u0002\u05edװ\u0007:\u0002\u0002\u05eeװ\u0005t;\u0002\u05efק\u0003\u0002\u0002\u0002\u05efר\u0003\u0002\u0002\u0002\u05efש\u0003\u0002\u0002\u0002\u05efת\u0003\u0002\u0002\u0002\u05ef\u05eb\u0003\u0002\u0002\u0002\u05ef\u05ec\u0003\u0002\u0002\u0002\u05ef\u05ed\u0003\u0002\u0002\u0002\u05ef\u05ee\u0003\u0002\u0002\u0002װs\u0003\u0002\u0002\u0002ױײ\t\r\u0002\u0002ײu\u0003\u0002\u0002\u0002׳״\t\u000e\u0002\u0002״w\u0003\u0002\u0002\u0002\u05f5\u05fe\u0007\r\u0002\u0002\u05f6\u05fe\t\u000f\u0002\u0002\u05f7\u05fe\t\f\u0002\u0002\u05f8\u05fe\t\u0010\u0002\u0002\u05f9\u05fe\t\u0011\u0002\u0002\u05fa\u05fe\t\u0012\u0002\u0002\u05fb\u05fe\u0007$\u0002\u0002\u05fc\u05fe\u0007p\u0002\u0002\u05fd\u05f5\u0003\u0002\u0002\u0002\u05fd\u05f6\u0003\u0002\u0002\u0002\u05fd\u05f7\u0003\u0002\u0002\u0002\u05fd\u05f8\u0003\u0002\u0002\u0002\u05fd\u05f9\u0003\u0002\u0002\u0002\u05fd\u05fa\u0003\u0002\u0002\u0002\u05fd\u05fb\u0003\u0002\u0002\u0002\u05fd\u05fc\u0003\u0002\u0002\u0002\u05fey\u0003\u0002\u0002\u0002\u05ff\u0600\u0007\u009f\u0002\u0002\u0600{\u0003\u0002\u0002\u0002\u0601\u0604\u0005L'\u0002\u0602\u0604\u0005D#\u0002\u0603\u0601\u0003\u0002\u0002\u0002\u0603\u0602\u0003\u0002\u0002\u0002\u0604}\u0003\u0002\u0002\u0002\u0605؆\t\u0013\u0002\u0002؆\u007f\u0003\u0002\u0002\u0002؇؈\u0007\u0005\u0002\u0002؈؍\u0005\u0092J\u0002؉؊\u0007\u0007\u0002\u0002؊،\u0005\u0092J\u0002؋؉\u0003\u0002\u0002\u0002،؏\u0003\u0002\u0002\u0002؍؋\u0003\u0002\u0002\u0002؍؎\u0003\u0002\u0002\u0002؎ؐ\u0003\u0002\u0002\u0002؏؍\u0003\u0002\u0002\u0002ؐؑ\u0007\u0006\u0002\u0002ؑ\u0081\u0003\u0002\u0002\u0002ؒؓ\t\u0014\u0002\u0002ؓ\u0083\u0003\u0002\u0002\u0002ؔؕ\u0005¨U\u0002ؕ\u0085\u0003\u0002\u0002\u0002ؖؗ\u0005¨U\u0002ؗ\u0087\u0003\u0002\u0002\u0002ؘؙ\u0005¨U\u0002ؙ\u0089\u0003\u0002\u0002\u0002ؚ؛\u0005¨U\u0002؛\u008b\u0003\u0002\u0002\u0002\u061c\u061d\u0005¨U\u0002\u061d\u008d\u0003\u0002\u0002\u0002؞؟\u0005¨U\u0002؟\u008f\u0003\u0002\u0002\u0002ؠء\u0005¨U\u0002ء\u0091\u0003\u0002\u0002\u0002آأ\u0005¨U\u0002أ\u0093\u0003\u0002\u0002\u0002ؤإ\u0005¨U\u0002إ\u0095\u0003\u0002\u0002\u0002ئا\u0005¨U\u0002ا\u0097\u0003\u0002\u0002\u0002بة\u0005¨U\u0002ة\u0099\u0003\u0002\u0002\u0002تث\u0005¨U\u0002ث\u009b\u0003\u0002\u0002\u0002جح\u0005¨U\u0002ح\u009d\u0003\u0002\u0002\u0002خد\u0005¨U\u0002د\u009f\u0003\u0002\u0002\u0002ذر\u0005¨U\u0002ر¡\u0003\u0002\u0002\u0002زس\u0005¨U\u0002س£\u0003\u0002\u0002\u0002شػ\u0007\u009c\u0002\u0002صػ\u0007\u009f\u0002\u0002ضط\u0007\u0005\u0002\u0002طظ\u0005¤S\u0002ظع\u0007\u0006\u0002\u0002عػ\u0003\u0002\u0002\u0002غش\u0003\u0002\u0002\u0002غص\u0003\u0002\u0002\u0002غض\u0003\u0002\u0002\u0002ػ¥\u0003\u0002\u0002\u0002ؼؽ\u0005¨U\u0002ؽ§\u0003\u0002\u0002\u0002ؾن\u0007\u009c\u0002\u0002ؿن\u0005\u0082B\u0002ـن\u0007\u009f\u0002\u0002فق\u0007\u0005\u0002\u0002قك\u0005¨U\u0002كل\u0007\u0006\u0002\u0002لن\u0003\u0002\u0002\u0002مؾ\u0003\u0002\u0002\u0002مؿ\u0003\u0002\u0002\u0002مـ\u0003\u0002\u0002\u0002مف\u0003\u0002\u0002\u0002ن©\u0003\u0002\u0002\u0002å¬®¹ÀÅËÑÓð÷ÿĂċďėěĝĢĤĨĮĳľńňŎœŜţŨŬŰŶŻƂƍƐƒƘƞƢƩƯƵƻǀǈǋǖǛǦǫǮǵǸǿȂȅȉȑȖȞȣȫȰȸȽɂɕɛɠɨɭɶʁʈʎʑʛʡʣʪʱʸʽʿ˅ˎ˕˙˛˟˦˨ˬ˯˵˽̢̘̜̞̫̰̲̻͍͓́̄̈̏͆͐ͦͭ͠ʹ\u0379ͼΉΏΖΝ\u03a2ΥΨάΰελχϋϐϔϗϩϮϳϻЀЉАДОЫЮжпушёіѢѨѯѳѽҀ҆ҌҕҘҜҞҠҫҰҼӀӄӈӏӑәӟӣӦӪӯӷԊԐԔԛԥԪԴԹՀՉՏՒ\u0558՞էժլձյոտք֍\u0590ְֹ֣֮֔֗֟֫־ׂ׆\u05c9הינף\u05ef\u05fd\u0603؍غم".toCharArray());
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];

        for(i = 0; i < _ATN.getNumberOfDecisions(); ++i) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }

    }

    public static class Any_nameContext extends ParserRuleContext {
        public TerminalNode IDENTIFIER() {
            return this.getToken(154, 0);
        }

        public SQLiteParser.KeywordContext keyword() {
            return (SQLiteParser.KeywordContext)this.getRuleContext(SQLiteParser.KeywordContext.class, 0);
        }

        public TerminalNode STRING_LITERAL() {
            return this.getToken(157, 0);
        }

        public SQLiteParser.Any_nameContext any_name() {
            return (SQLiteParser.Any_nameContext)this.getRuleContext(SQLiteParser.Any_nameContext.class, 0);
        }

        public Any_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 83;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterAny_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitAny_name(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T) ((SQLiteVisitor)visitor).visitAny_name(this) : visitor.visitChildren(this);
        }
    }

    public static class Transaction_nameContext extends ParserRuleContext {
        public SQLiteParser.Any_nameContext any_name() {
            return (SQLiteParser.Any_nameContext)this.getRuleContext(SQLiteParser.Any_nameContext.class, 0);
        }

        public Transaction_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 82;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterTransaction_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitTransaction_name(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitTransaction_name(this) : visitor.visitChildren(this);
        }
    }

    public static class Table_aliasContext extends ParserRuleContext {
        public TerminalNode IDENTIFIER() {
            return this.getToken(154, 0);
        }

        public TerminalNode STRING_LITERAL() {
            return this.getToken(157, 0);
        }

        public SQLiteParser.Table_aliasContext table_alias() {
            return (SQLiteParser.Table_aliasContext)this.getRuleContext(SQLiteParser.Table_aliasContext.class, 0);
        }

        public Table_aliasContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 81;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterTable_alias(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitTable_alias(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitTable_alias(this) : visitor.visitChildren(this);
        }
    }

    public static class Savepoint_nameContext extends ParserRuleContext {
        public SQLiteParser.Any_nameContext any_name() {
            return (SQLiteParser.Any_nameContext)this.getRuleContext(SQLiteParser.Any_nameContext.class, 0);
        }

        public Savepoint_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 80;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterSavepoint_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitSavepoint_name(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitSavepoint_name(this) : visitor.visitChildren(this);
        }
    }

    public static class Pragma_nameContext extends ParserRuleContext {
        public SQLiteParser.Any_nameContext any_name() {
            return (SQLiteParser.Any_nameContext)this.getRuleContext(SQLiteParser.Any_nameContext.class, 0);
        }

        public Pragma_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 79;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterPragma_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitPragma_name(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitPragma_name(this) : visitor.visitChildren(this);
        }
    }

    public static class Module_nameContext extends ParserRuleContext {
        public SQLiteParser.Any_nameContext any_name() {
            return (SQLiteParser.Any_nameContext)this.getRuleContext(SQLiteParser.Any_nameContext.class, 0);
        }

        public Module_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 78;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterModule_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitModule_name(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitModule_name(this) : visitor.visitChildren(this);
        }
    }

    public static class View_nameContext extends ParserRuleContext {
        public SQLiteParser.Any_nameContext any_name() {
            return (SQLiteParser.Any_nameContext)this.getRuleContext(SQLiteParser.Any_nameContext.class, 0);
        }

        public View_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 77;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterView_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitView_name(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitView_name(this) : visitor.visitChildren(this);
        }
    }

    public static class Trigger_nameContext extends ParserRuleContext {
        public SQLiteParser.Any_nameContext any_name() {
            return (SQLiteParser.Any_nameContext)this.getRuleContext(SQLiteParser.Any_nameContext.class, 0);
        }

        public Trigger_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 76;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterTrigger_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitTrigger_name(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitTrigger_name(this) : visitor.visitChildren(this);
        }
    }

    public static class Index_nameContext extends ParserRuleContext {
        public SQLiteParser.Any_nameContext any_name() {
            return (SQLiteParser.Any_nameContext)this.getRuleContext(SQLiteParser.Any_nameContext.class, 0);
        }

        public Index_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 75;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterIndex_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitIndex_name(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitIndex_name(this) : visitor.visitChildren(this);
        }
    }

    public static class Foreign_tableContext extends ParserRuleContext {
        public SQLiteParser.Any_nameContext any_name() {
            return (SQLiteParser.Any_nameContext)this.getRuleContext(SQLiteParser.Any_nameContext.class, 0);
        }

        public Foreign_tableContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 74;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterForeign_table(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitForeign_table(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitForeign_table(this) : visitor.visitChildren(this);
        }
    }

    public static class Collation_nameContext extends ParserRuleContext {
        public SQLiteParser.Any_nameContext any_name() {
            return (SQLiteParser.Any_nameContext)this.getRuleContext(SQLiteParser.Any_nameContext.class, 0);
        }

        public Collation_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 73;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterCollation_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitCollation_name(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitCollation_name(this) : visitor.visitChildren(this);
        }
    }

    public static class Column_nameContext extends ParserRuleContext {
        public SQLiteParser.Any_nameContext any_name() {
            return (SQLiteParser.Any_nameContext)this.getRuleContext(SQLiteParser.Any_nameContext.class, 0);
        }

        public Column_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 72;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterColumn_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitColumn_name(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitColumn_name(this) : visitor.visitChildren(this);
        }
    }

    public static class New_table_nameContext extends ParserRuleContext {
        public SQLiteParser.Any_nameContext any_name() {
            return (SQLiteParser.Any_nameContext)this.getRuleContext(SQLiteParser.Any_nameContext.class, 0);
        }

        public New_table_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 71;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterNew_table_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitNew_table_name(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitNew_table_name(this) : visitor.visitChildren(this);
        }
    }

    public static class Table_or_index_nameContext extends ParserRuleContext {
        public SQLiteParser.Any_nameContext any_name() {
            return (SQLiteParser.Any_nameContext)this.getRuleContext(SQLiteParser.Any_nameContext.class, 0);
        }

        public Table_or_index_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 70;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterTable_or_index_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitTable_or_index_name(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitTable_or_index_name(this) : visitor.visitChildren(this);
        }
    }

    public static class Table_nameContext extends ParserRuleContext {
        public SQLiteParser.Any_nameContext any_name() {
            return (SQLiteParser.Any_nameContext)this.getRuleContext(SQLiteParser.Any_nameContext.class, 0);
        }

        public Table_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 69;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterTable_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitTable_name(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitTable_name(this) : visitor.visitChildren(this);
        }
    }

    public static class Table_functionContext extends ParserRuleContext {
        public SQLiteParser.Any_nameContext any_name() {
            return (SQLiteParser.Any_nameContext)this.getRuleContext(SQLiteParser.Any_nameContext.class, 0);
        }

        public Table_functionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 68;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterTable_function(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitTable_function(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitTable_function(this) : visitor.visitChildren(this);
        }
    }

    public static class Schema_nameContext extends ParserRuleContext {
        public SQLiteParser.Any_nameContext any_name() {
            return (SQLiteParser.Any_nameContext)this.getRuleContext(SQLiteParser.Any_nameContext.class, 0);
        }

        public Schema_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 67;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterSchema_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitSchema_name(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitSchema_name(this) : visitor.visitChildren(this);
        }
    }

    public static class Function_nameContext extends ParserRuleContext {
        public SQLiteParser.Any_nameContext any_name() {
            return (SQLiteParser.Any_nameContext)this.getRuleContext(SQLiteParser.Any_nameContext.class, 0);
        }

        public Function_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 66;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterFunction_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitFunction_name(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitFunction_name(this) : visitor.visitChildren(this);
        }
    }

    public static class NameContext extends ParserRuleContext {
        public SQLiteParser.Any_nameContext any_name() {
            return (SQLiteParser.Any_nameContext)this.getRuleContext(SQLiteParser.Any_nameContext.class, 0);
        }

        public NameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 65;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterName(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitName(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitName(this) : visitor.visitChildren(this);
        }
    }

    public static class KeywordContext extends ParserRuleContext {
        public TerminalNode K_ABORT() {
            return this.getToken(27, 0);
        }

        public TerminalNode K_ACTION() {
            return this.getToken(28, 0);
        }

        public TerminalNode K_ADD() {
            return this.getToken(29, 0);
        }

        public TerminalNode K_AFTER() {
            return this.getToken(30, 0);
        }

        public TerminalNode K_ALL() {
            return this.getToken(31, 0);
        }

        public TerminalNode K_ALTER() {
            return this.getToken(32, 0);
        }

        public TerminalNode K_ANALYZE() {
            return this.getToken(33, 0);
        }

        public TerminalNode K_AND() {
            return this.getToken(34, 0);
        }

        public TerminalNode K_AS() {
            return this.getToken(35, 0);
        }

        public TerminalNode K_ASC() {
            return this.getToken(36, 0);
        }

        public TerminalNode K_ATTACH() {
            return this.getToken(37, 0);
        }

        public TerminalNode K_AUTOINCREMENT() {
            return this.getToken(38, 0);
        }

        public TerminalNode K_BEFORE() {
            return this.getToken(39, 0);
        }

        public TerminalNode K_BEGIN() {
            return this.getToken(40, 0);
        }

        public TerminalNode K_BETWEEN() {
            return this.getToken(41, 0);
        }

        public TerminalNode K_BY() {
            return this.getToken(42, 0);
        }

        public TerminalNode K_CASCADE() {
            return this.getToken(43, 0);
        }

        public TerminalNode K_CASE() {
            return this.getToken(44, 0);
        }

        public TerminalNode K_CAST() {
            return this.getToken(45, 0);
        }

        public TerminalNode K_CHECK() {
            return this.getToken(46, 0);
        }

        public TerminalNode K_COLLATE() {
            return this.getToken(47, 0);
        }

        public TerminalNode K_COLUMN() {
            return this.getToken(48, 0);
        }

        public TerminalNode K_COMMIT() {
            return this.getToken(49, 0);
        }

        public TerminalNode K_CONFLICT() {
            return this.getToken(50, 0);
        }

        public TerminalNode K_CONSTRAINT() {
            return this.getToken(51, 0);
        }

        public TerminalNode K_CREATE() {
            return this.getToken(52, 0);
        }

        public TerminalNode K_CROSS() {
            return this.getToken(53, 0);
        }

        public TerminalNode K_CURRENT_DATE() {
            return this.getToken(54, 0);
        }

        public TerminalNode K_CURRENT_TIME() {
            return this.getToken(55, 0);
        }

        public TerminalNode K_CURRENT_TIMESTAMP() {
            return this.getToken(56, 0);
        }

        public TerminalNode K_DATABASE() {
            return this.getToken(57, 0);
        }

        public TerminalNode K_DEFAULT() {
            return this.getToken(58, 0);
        }

        public TerminalNode K_DEFERRABLE() {
            return this.getToken(59, 0);
        }

        public TerminalNode K_DEFERRED() {
            return this.getToken(60, 0);
        }

        public TerminalNode K_DELETE() {
            return this.getToken(61, 0);
        }

        public TerminalNode K_DESC() {
            return this.getToken(62, 0);
        }

        public TerminalNode K_DETACH() {
            return this.getToken(63, 0);
        }

        public TerminalNode K_DISTINCT() {
            return this.getToken(64, 0);
        }

        public TerminalNode K_DROP() {
            return this.getToken(65, 0);
        }

        public TerminalNode K_EACH() {
            return this.getToken(66, 0);
        }

        public TerminalNode K_ELSE() {
            return this.getToken(67, 0);
        }

        public TerminalNode K_END() {
            return this.getToken(68, 0);
        }

        public TerminalNode K_ESCAPE() {
            return this.getToken(69, 0);
        }

        public TerminalNode K_EXCEPT() {
            return this.getToken(70, 0);
        }

        public TerminalNode K_EXCLUSIVE() {
            return this.getToken(71, 0);
        }

        public TerminalNode K_EXISTS() {
            return this.getToken(72, 0);
        }

        public TerminalNode K_EXPLAIN() {
            return this.getToken(73, 0);
        }

        public TerminalNode K_FAIL() {
            return this.getToken(74, 0);
        }

        public TerminalNode K_FOR() {
            return this.getToken(75, 0);
        }

        public TerminalNode K_FOREIGN() {
            return this.getToken(76, 0);
        }

        public TerminalNode K_FROM() {
            return this.getToken(77, 0);
        }

        public TerminalNode K_FULL() {
            return this.getToken(78, 0);
        }

        public TerminalNode K_GLOB() {
            return this.getToken(79, 0);
        }

        public TerminalNode K_GROUP() {
            return this.getToken(80, 0);
        }

        public TerminalNode K_HAVING() {
            return this.getToken(81, 0);
        }

        public TerminalNode K_IF() {
            return this.getToken(82, 0);
        }

        public TerminalNode K_IGNORE() {
            return this.getToken(83, 0);
        }

        public TerminalNode K_IMMEDIATE() {
            return this.getToken(84, 0);
        }

        public TerminalNode K_IN() {
            return this.getToken(85, 0);
        }

        public TerminalNode K_INDEX() {
            return this.getToken(86, 0);
        }

        public TerminalNode K_INDEXED() {
            return this.getToken(87, 0);
        }

        public TerminalNode K_INITIALLY() {
            return this.getToken(88, 0);
        }

        public TerminalNode K_INNER() {
            return this.getToken(89, 0);
        }

        public TerminalNode K_INSERT() {
            return this.getToken(90, 0);
        }

        public TerminalNode K_INSTEAD() {
            return this.getToken(91, 0);
        }

        public TerminalNode K_INTERSECT() {
            return this.getToken(92, 0);
        }

        public TerminalNode K_INTO() {
            return this.getToken(93, 0);
        }

        public TerminalNode K_IS() {
            return this.getToken(94, 0);
        }

        public TerminalNode K_ISNULL() {
            return this.getToken(95, 0);
        }

        public TerminalNode K_JOIN() {
            return this.getToken(96, 0);
        }

        public TerminalNode K_KEY() {
            return this.getToken(97, 0);
        }

        public TerminalNode K_LEFT() {
            return this.getToken(98, 0);
        }

        public TerminalNode K_LIKE() {
            return this.getToken(99, 0);
        }

        public TerminalNode K_LIMIT() {
            return this.getToken(100, 0);
        }

        public TerminalNode K_MATCH() {
            return this.getToken(101, 0);
        }

        public TerminalNode K_NATURAL() {
            return this.getToken(102, 0);
        }

        public TerminalNode K_NO() {
            return this.getToken(103, 0);
        }

        public TerminalNode K_NOT() {
            return this.getToken(104, 0);
        }

        public TerminalNode K_NOTNULL() {
            return this.getToken(105, 0);
        }

        public TerminalNode K_NULL() {
            return this.getToken(106, 0);
        }

        public TerminalNode K_OF() {
            return this.getToken(107, 0);
        }

        public TerminalNode K_OFFSET() {
            return this.getToken(108, 0);
        }

        public TerminalNode K_ON() {
            return this.getToken(109, 0);
        }

        public TerminalNode K_OR() {
            return this.getToken(110, 0);
        }

        public TerminalNode K_ORDER() {
            return this.getToken(111, 0);
        }

        public TerminalNode K_OUTER() {
            return this.getToken(112, 0);
        }

        public TerminalNode K_PLAN() {
            return this.getToken(113, 0);
        }

        public TerminalNode K_PRAGMA() {
            return this.getToken(114, 0);
        }

        public TerminalNode K_PRIMARY() {
            return this.getToken(115, 0);
        }

        public TerminalNode K_QUERY() {
            return this.getToken(116, 0);
        }

        public TerminalNode K_RAISE() {
            return this.getToken(117, 0);
        }

        public TerminalNode K_RECURSIVE() {
            return this.getToken(118, 0);
        }

        public TerminalNode K_REFERENCES() {
            return this.getToken(119, 0);
        }

        public TerminalNode K_REGEXP() {
            return this.getToken(120, 0);
        }

        public TerminalNode K_REINDEX() {
            return this.getToken(121, 0);
        }

        public TerminalNode K_RELEASE() {
            return this.getToken(122, 0);
        }

        public TerminalNode K_RENAME() {
            return this.getToken(123, 0);
        }

        public TerminalNode K_REPLACE() {
            return this.getToken(124, 0);
        }

        public TerminalNode K_RESTRICT() {
            return this.getToken(125, 0);
        }

        public TerminalNode K_RIGHT() {
            return this.getToken(126, 0);
        }

        public TerminalNode K_ROLLBACK() {
            return this.getToken(127, 0);
        }

        public TerminalNode K_ROW() {
            return this.getToken(128, 0);
        }

        public TerminalNode K_SAVEPOINT() {
            return this.getToken(129, 0);
        }

        public TerminalNode K_SELECT() {
            return this.getToken(130, 0);
        }

        public TerminalNode K_SET() {
            return this.getToken(131, 0);
        }

        public TerminalNode K_TABLE() {
            return this.getToken(132, 0);
        }

        public TerminalNode K_TEMP() {
            return this.getToken(133, 0);
        }

        public TerminalNode K_TEMPORARY() {
            return this.getToken(134, 0);
        }

        public TerminalNode K_THEN() {
            return this.getToken(135, 0);
        }

        public TerminalNode K_TO() {
            return this.getToken(136, 0);
        }

        public TerminalNode K_TRANSACTION() {
            return this.getToken(137, 0);
        }

        public TerminalNode K_TRIGGER() {
            return this.getToken(138, 0);
        }

        public TerminalNode K_UNION() {
            return this.getToken(139, 0);
        }

        public TerminalNode K_UNIQUE() {
            return this.getToken(140, 0);
        }

        public TerminalNode K_UPDATE() {
            return this.getToken(141, 0);
        }

        public TerminalNode K_USING() {
            return this.getToken(142, 0);
        }

        public TerminalNode K_VACUUM() {
            return this.getToken(143, 0);
        }

        public TerminalNode K_VALUES() {
            return this.getToken(144, 0);
        }

        public TerminalNode K_VIEW() {
            return this.getToken(145, 0);
        }

        public TerminalNode K_VIRTUAL() {
            return this.getToken(146, 0);
        }

        public TerminalNode K_WHEN() {
            return this.getToken(147, 0);
        }

        public TerminalNode K_WHERE() {
            return this.getToken(148, 0);
        }

        public TerminalNode K_WITH() {
            return this.getToken(149, 0);
        }

        public TerminalNode K_WITHOUT() {
            return this.getToken(150, 0);
        }

        public KeywordContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 64;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterKeyword(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitKeyword(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitKeyword(this) : visitor.visitChildren(this);
        }
    }

    public static class Column_name_listContext extends ParserRuleContext {
        public List<SQLiteParser.Column_nameContext> column_name() {
            return this.getRuleContexts(SQLiteParser.Column_nameContext.class);
        }

        public SQLiteParser.Column_nameContext column_name(int i) {
            return (SQLiteParser.Column_nameContext)this.getRuleContext(SQLiteParser.Column_nameContext.class, i);
        }

        public Column_name_listContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 63;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterColumn_name_list(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitColumn_name_list(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitColumn_name_list(this) : visitor.visitChildren(this);
        }
    }

    public static class Column_aliasContext extends ParserRuleContext {
        public TerminalNode IDENTIFIER() {
            return this.getToken(154, 0);
        }

        public TerminalNode STRING_LITERAL() {
            return this.getToken(157, 0);
        }

        public Column_aliasContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 62;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterColumn_alias(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitColumn_alias(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitColumn_alias(this) : visitor.visitChildren(this);
        }
    }

    public static class Module_argumentContext extends ParserRuleContext {
        public SQLiteParser.ExprContext expr() {
            return (SQLiteParser.ExprContext)this.getRuleContext(SQLiteParser.ExprContext.class, 0);
        }

        public SQLiteParser.Column_defContext column_def() {
            return (SQLiteParser.Column_defContext)this.getRuleContext(SQLiteParser.Column_defContext.class, 0);
        }

        public Module_argumentContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 61;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterModule_argument(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitModule_argument(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitModule_argument(this) : visitor.visitChildren(this);
        }
    }

    public static class Error_messageContext extends ParserRuleContext {
        public TerminalNode STRING_LITERAL() {
            return this.getToken(157, 0);
        }

        public Error_messageContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 60;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterError_message(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitError_message(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitError_message(this) : visitor.visitChildren(this);
        }
    }

    public static class Binary_operatorContext extends ParserRuleContext {
        public TerminalNode K_AND() {
            return this.getToken(34, 0);
        }

        public TerminalNode K_OR() {
            return this.getToken(110, 0);
        }

        public Binary_operatorContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 59;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterBinary_operator(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitBinary_operator(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitBinary_operator(this) : visitor.visitChildren(this);
        }
    }

    public static class Unary_operatorContext extends ParserRuleContext {
        public TerminalNode K_NOT() {
            return this.getToken(104, 0);
        }

        public Unary_operatorContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 58;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterUnary_operator(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitUnary_operator(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitUnary_operator(this) : visitor.visitChildren(this);
        }
    }

    public static class Boolean_literalContext extends ParserRuleContext {
        public TerminalNode TRUE() {
            return this.getToken(25, 0);
        }

        public TerminalNode FALSE() {
            return this.getToken(26, 0);
        }

        public Boolean_literalContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 57;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterBoolean_literal(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitBoolean_literal(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitBoolean_literal(this) : visitor.visitChildren(this);
        }
    }

    public static class Literal_valueContext extends ParserRuleContext {
        public TerminalNode NUMERIC_LITERAL() {
            return this.getToken(155, 0);
        }

        public TerminalNode STRING_LITERAL() {
            return this.getToken(157, 0);
        }

        public TerminalNode BLOB_LITERAL() {
            return this.getToken(158, 0);
        }

        public TerminalNode K_NULL() {
            return this.getToken(106, 0);
        }

        public TerminalNode K_CURRENT_TIME() {
            return this.getToken(55, 0);
        }

        public TerminalNode K_CURRENT_DATE() {
            return this.getToken(54, 0);
        }

        public TerminalNode K_CURRENT_TIMESTAMP() {
            return this.getToken(56, 0);
        }

        public SQLiteParser.Boolean_literalContext boolean_literal() {
            return (SQLiteParser.Boolean_literalContext)this.getRuleContext(SQLiteParser.Boolean_literalContext.class, 0);
        }

        public Literal_valueContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 56;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterLiteral_value(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitLiteral_value(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitLiteral_value(this) : visitor.visitChildren(this);
        }
    }

    public static class Signed_numberContext extends ParserRuleContext {
        public TerminalNode NUMERIC_LITERAL() {
            return this.getToken(155, 0);
        }

        public Signed_numberContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 55;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterSigned_number(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitSigned_number(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitSigned_number(this) : visitor.visitChildren(this);
        }
    }

    public static class Compound_operatorContext extends ParserRuleContext {
        public TerminalNode K_UNION() {
            return this.getToken(139, 0);
        }

        public TerminalNode K_ALL() {
            return this.getToken(31, 0);
        }

        public TerminalNode K_INTERSECT() {
            return this.getToken(92, 0);
        }

        public TerminalNode K_EXCEPT() {
            return this.getToken(70, 0);
        }

        public Compound_operatorContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 54;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterCompound_operator(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitCompound_operator(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitCompound_operator(this) : visitor.visitChildren(this);
        }
    }

    public static class Join_constraintContext extends ParserRuleContext {
        public TerminalNode K_ON() {
            return this.getToken(109, 0);
        }

        public SQLiteParser.ExprContext expr() {
            return (SQLiteParser.ExprContext)this.getRuleContext(SQLiteParser.ExprContext.class, 0);
        }

        public TerminalNode K_USING() {
            return this.getToken(142, 0);
        }

        public List<SQLiteParser.Column_nameContext> column_name() {
            return this.getRuleContexts(SQLiteParser.Column_nameContext.class);
        }

        public SQLiteParser.Column_nameContext column_name(int i) {
            return (SQLiteParser.Column_nameContext)this.getRuleContext(SQLiteParser.Column_nameContext.class, i);
        }

        public Join_constraintContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 53;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterJoin_constraint(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitJoin_constraint(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitJoin_constraint(this) : visitor.visitChildren(this);
        }
    }

    public static class Join_operatorContext extends ParserRuleContext {
        public TerminalNode K_JOIN() {
            return this.getToken(96, 0);
        }

        public TerminalNode K_NATURAL() {
            return this.getToken(102, 0);
        }

        public TerminalNode K_LEFT() {
            return this.getToken(98, 0);
        }

        public TerminalNode K_INNER() {
            return this.getToken(89, 0);
        }

        public TerminalNode K_CROSS() {
            return this.getToken(53, 0);
        }

        public TerminalNode K_OUTER() {
            return this.getToken(112, 0);
        }

        public Join_operatorContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 52;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterJoin_operator(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitJoin_operator(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitJoin_operator(this) : visitor.visitChildren(this);
        }
    }

    public static class Join_clauseContext extends ParserRuleContext {
        public List<SQLiteParser.Table_or_subqueryContext> table_or_subquery() {
            return this.getRuleContexts(SQLiteParser.Table_or_subqueryContext.class);
        }

        public SQLiteParser.Table_or_subqueryContext table_or_subquery(int i) {
            return (SQLiteParser.Table_or_subqueryContext)this.getRuleContext(SQLiteParser.Table_or_subqueryContext.class, i);
        }

        public List<SQLiteParser.Join_operatorContext> join_operator() {
            return this.getRuleContexts(SQLiteParser.Join_operatorContext.class);
        }

        public SQLiteParser.Join_operatorContext join_operator(int i) {
            return (SQLiteParser.Join_operatorContext)this.getRuleContext(SQLiteParser.Join_operatorContext.class, i);
        }

        public List<SQLiteParser.Join_constraintContext> join_constraint() {
            return this.getRuleContexts(SQLiteParser.Join_constraintContext.class);
        }

        public SQLiteParser.Join_constraintContext join_constraint(int i) {
            return (SQLiteParser.Join_constraintContext)this.getRuleContext(SQLiteParser.Join_constraintContext.class, i);
        }

        public Join_clauseContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 51;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterJoin_clause(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitJoin_clause(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitJoin_clause(this) : visitor.visitChildren(this);
        }
    }

    public static class Table_or_subqueryContext extends ParserRuleContext {
        public SQLiteParser.Table_nameContext table_name() {
            return (SQLiteParser.Table_nameContext)this.getRuleContext(SQLiteParser.Table_nameContext.class, 0);
        }

        public SQLiteParser.Schema_nameContext schema_name() {
            return (SQLiteParser.Schema_nameContext)this.getRuleContext(SQLiteParser.Schema_nameContext.class, 0);
        }

        public SQLiteParser.Table_aliasContext table_alias() {
            return (SQLiteParser.Table_aliasContext)this.getRuleContext(SQLiteParser.Table_aliasContext.class, 0);
        }

        public TerminalNode K_INDEXED() {
            return this.getToken(87, 0);
        }

        public TerminalNode K_BY() {
            return this.getToken(42, 0);
        }

        public SQLiteParser.Index_nameContext index_name() {
            return (SQLiteParser.Index_nameContext)this.getRuleContext(SQLiteParser.Index_nameContext.class, 0);
        }

        public TerminalNode K_NOT() {
            return this.getToken(104, 0);
        }

        public TerminalNode K_AS() {
            return this.getToken(35, 0);
        }

        public SQLiteParser.Table_functionContext table_function() {
            return (SQLiteParser.Table_functionContext)this.getRuleContext(SQLiteParser.Table_functionContext.class, 0);
        }

        public List<SQLiteParser.ExprContext> expr() {
            return this.getRuleContexts(SQLiteParser.ExprContext.class);
        }

        public SQLiteParser.ExprContext expr(int i) {
            return (SQLiteParser.ExprContext)this.getRuleContext(SQLiteParser.ExprContext.class, i);
        }

        public List<SQLiteParser.Table_or_subqueryContext> table_or_subquery() {
            return this.getRuleContexts(SQLiteParser.Table_or_subqueryContext.class);
        }

        public SQLiteParser.Table_or_subqueryContext table_or_subquery(int i) {
            return (SQLiteParser.Table_or_subqueryContext)this.getRuleContext(SQLiteParser.Table_or_subqueryContext.class, i);
        }

        public SQLiteParser.Join_clauseContext join_clause() {
            return (SQLiteParser.Join_clauseContext)this.getRuleContext(SQLiteParser.Join_clauseContext.class, 0);
        }

        public SQLiteParser.Select_stmtContext select_stmt() {
            return (SQLiteParser.Select_stmtContext)this.getRuleContext(SQLiteParser.Select_stmtContext.class, 0);
        }

        public Table_or_subqueryContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 50;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterTable_or_subquery(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitTable_or_subquery(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitTable_or_subquery(this) : visitor.visitChildren(this);
        }
    }

    public static class Result_columnContext extends ParserRuleContext {
        public SQLiteParser.Table_nameContext table_name() {
            return (SQLiteParser.Table_nameContext)this.getRuleContext(SQLiteParser.Table_nameContext.class, 0);
        }

        public SQLiteParser.ExprContext expr() {
            return (SQLiteParser.ExprContext)this.getRuleContext(SQLiteParser.ExprContext.class, 0);
        }

        public SQLiteParser.Column_aliasContext column_alias() {
            return (SQLiteParser.Column_aliasContext)this.getRuleContext(SQLiteParser.Column_aliasContext.class, 0);
        }

        public TerminalNode K_AS() {
            return this.getToken(35, 0);
        }

        public Result_columnContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 49;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterResult_column(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitResult_column(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitResult_column(this) : visitor.visitChildren(this);
        }
    }

    public static class Pragma_valueContext extends ParserRuleContext {
        public SQLiteParser.Signed_numberContext signed_number() {
            return (SQLiteParser.Signed_numberContext)this.getRuleContext(SQLiteParser.Signed_numberContext.class, 0);
        }

        public SQLiteParser.NameContext name() {
            return (SQLiteParser.NameContext)this.getRuleContext(SQLiteParser.NameContext.class, 0);
        }

        public TerminalNode STRING_LITERAL() {
            return this.getToken(157, 0);
        }

        public SQLiteParser.Boolean_literalContext boolean_literal() {
            return (SQLiteParser.Boolean_literalContext)this.getRuleContext(SQLiteParser.Boolean_literalContext.class, 0);
        }

        public Pragma_valueContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 48;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterPragma_value(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitPragma_value(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitPragma_value(this) : visitor.visitChildren(this);
        }
    }

    public static class Limit_clauseContext extends ParserRuleContext {
        public TerminalNode K_LIMIT() {
            return this.getToken(100, 0);
        }

        public List<SQLiteParser.ExprContext> expr() {
            return this.getRuleContexts(SQLiteParser.ExprContext.class);
        }

        public SQLiteParser.ExprContext expr(int i) {
            return (SQLiteParser.ExprContext)this.getRuleContext(SQLiteParser.ExprContext.class, i);
        }

        public TerminalNode K_OFFSET() {
            return this.getToken(108, 0);
        }

        public Limit_clauseContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 47;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterLimit_clause(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitLimit_clause(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitLimit_clause(this) : visitor.visitChildren(this);
        }
    }

    public static class Ordering_termContext extends ParserRuleContext {
        public SQLiteParser.ExprContext expr() {
            return (SQLiteParser.ExprContext)this.getRuleContext(SQLiteParser.ExprContext.class, 0);
        }

        public TerminalNode K_COLLATE() {
            return this.getToken(47, 0);
        }

        public SQLiteParser.Collation_nameContext collation_name() {
            return (SQLiteParser.Collation_nameContext)this.getRuleContext(SQLiteParser.Collation_nameContext.class, 0);
        }

        public TerminalNode K_ASC() {
            return this.getToken(36, 0);
        }

        public TerminalNode K_DESC() {
            return this.getToken(62, 0);
        }

        public Ordering_termContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 46;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterOrdering_term(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitOrdering_term(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitOrdering_term(this) : visitor.visitChildren(this);
        }
    }

    public static class Order_clauseContext extends ParserRuleContext {
        public TerminalNode K_ORDER() {
            return this.getToken(111, 0);
        }

        public TerminalNode K_BY() {
            return this.getToken(42, 0);
        }

        public List<SQLiteParser.Ordering_termContext> ordering_term() {
            return this.getRuleContexts(SQLiteParser.Ordering_termContext.class);
        }

        public SQLiteParser.Ordering_termContext ordering_term(int i) {
            return (SQLiteParser.Ordering_termContext)this.getRuleContext(SQLiteParser.Ordering_termContext.class, i);
        }

        public Order_clauseContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 45;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterOrder_clause(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitOrder_clause(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitOrder_clause(this) : visitor.visitChildren(this);
        }
    }

    public static class Qualified_table_nameContext extends ParserRuleContext {
        public SQLiteParser.Table_nameContext table_name() {
            return (SQLiteParser.Table_nameContext)this.getRuleContext(SQLiteParser.Table_nameContext.class, 0);
        }

        public SQLiteParser.Schema_nameContext schema_name() {
            return (SQLiteParser.Schema_nameContext)this.getRuleContext(SQLiteParser.Schema_nameContext.class, 0);
        }

        public TerminalNode K_AS() {
            return this.getToken(35, 0);
        }

        public SQLiteParser.Table_aliasContext table_alias() {
            return (SQLiteParser.Table_aliasContext)this.getRuleContext(SQLiteParser.Table_aliasContext.class, 0);
        }

        public TerminalNode K_INDEXED() {
            return this.getToken(87, 0);
        }

        public TerminalNode K_BY() {
            return this.getToken(42, 0);
        }

        public SQLiteParser.Index_nameContext index_name() {
            return (SQLiteParser.Index_nameContext)this.getRuleContext(SQLiteParser.Index_nameContext.class, 0);
        }

        public TerminalNode K_NOT() {
            return this.getToken(104, 0);
        }

        public Qualified_table_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 44;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterQualified_table_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitQualified_table_name(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitQualified_table_name(this) : visitor.visitChildren(this);
        }
    }

    public static class Common_table_expressionContext extends ParserRuleContext {
        public SQLiteParser.Table_nameContext table_name() {
            return (SQLiteParser.Table_nameContext)this.getRuleContext(SQLiteParser.Table_nameContext.class, 0);
        }

        public TerminalNode K_AS() {
            return this.getToken(35, 0);
        }

        public SQLiteParser.Select_stmtContext select_stmt() {
            return (SQLiteParser.Select_stmtContext)this.getRuleContext(SQLiteParser.Select_stmtContext.class, 0);
        }

        public List<SQLiteParser.Column_nameContext> column_name() {
            return this.getRuleContexts(SQLiteParser.Column_nameContext.class);
        }

        public SQLiteParser.Column_nameContext column_name(int i) {
            return (SQLiteParser.Column_nameContext)this.getRuleContext(SQLiteParser.Column_nameContext.class, i);
        }

        public Common_table_expressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 43;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterCommon_table_expression(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitCommon_table_expression(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitCommon_table_expression(this) : visitor.visitChildren(this);
        }
    }

    public static class With_clauseContext extends ParserRuleContext {
        public TerminalNode K_WITH() {
            return this.getToken(149, 0);
        }

        public List<SQLiteParser.Common_table_expressionContext> common_table_expression() {
            return this.getRuleContexts(SQLiteParser.Common_table_expressionContext.class);
        }

        public SQLiteParser.Common_table_expressionContext common_table_expression(int i) {
            return (SQLiteParser.Common_table_expressionContext)this.getRuleContext(SQLiteParser.Common_table_expressionContext.class, i);
        }

        public TerminalNode K_RECURSIVE() {
            return this.getToken(118, 0);
        }

        public With_clauseContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 42;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterWith_clause(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitWith_clause(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitWith_clause(this) : visitor.visitChildren(this);
        }
    }

    public static class Table_constraintContext extends ParserRuleContext {
        public List<SQLiteParser.Indexed_columnContext> indexed_column() {
            return this.getRuleContexts(SQLiteParser.Indexed_columnContext.class);
        }

        public SQLiteParser.Indexed_columnContext indexed_column(int i) {
            return (SQLiteParser.Indexed_columnContext)this.getRuleContext(SQLiteParser.Indexed_columnContext.class, i);
        }

        public SQLiteParser.Conflict_clauseContext conflict_clause() {
            return (SQLiteParser.Conflict_clauseContext)this.getRuleContext(SQLiteParser.Conflict_clauseContext.class, 0);
        }

        public TerminalNode K_CHECK() {
            return this.getToken(46, 0);
        }

        public SQLiteParser.ExprContext expr() {
            return (SQLiteParser.ExprContext)this.getRuleContext(SQLiteParser.ExprContext.class, 0);
        }

        public TerminalNode K_FOREIGN() {
            return this.getToken(76, 0);
        }

        public TerminalNode K_KEY() {
            return this.getToken(97, 0);
        }

        public List<SQLiteParser.Column_nameContext> column_name() {
            return this.getRuleContexts(SQLiteParser.Column_nameContext.class);
        }

        public SQLiteParser.Column_nameContext column_name(int i) {
            return (SQLiteParser.Column_nameContext)this.getRuleContext(SQLiteParser.Column_nameContext.class, i);
        }

        public SQLiteParser.Foreign_key_clauseContext foreign_key_clause() {
            return (SQLiteParser.Foreign_key_clauseContext)this.getRuleContext(SQLiteParser.Foreign_key_clauseContext.class, 0);
        }

        public TerminalNode K_CONSTRAINT() {
            return this.getToken(51, 0);
        }

        public SQLiteParser.NameContext name() {
            return (SQLiteParser.NameContext)this.getRuleContext(SQLiteParser.NameContext.class, 0);
        }

        public TerminalNode K_PRIMARY() {
            return this.getToken(115, 0);
        }

        public TerminalNode K_UNIQUE() {
            return this.getToken(140, 0);
        }

        public Table_constraintContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 41;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterTable_constraint(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitTable_constraint(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitTable_constraint(this) : visitor.visitChildren(this);
        }
    }

    public static class Indexed_columnContext extends ParserRuleContext {
        public SQLiteParser.Column_nameContext column_name() {
            return (SQLiteParser.Column_nameContext)this.getRuleContext(SQLiteParser.Column_nameContext.class, 0);
        }

        public SQLiteParser.ExprContext expr() {
            return (SQLiteParser.ExprContext)this.getRuleContext(SQLiteParser.ExprContext.class, 0);
        }

        public TerminalNode K_COLLATE() {
            return this.getToken(47, 0);
        }

        public SQLiteParser.Collation_nameContext collation_name() {
            return (SQLiteParser.Collation_nameContext)this.getRuleContext(SQLiteParser.Collation_nameContext.class, 0);
        }

        public TerminalNode K_ASC() {
            return this.getToken(36, 0);
        }

        public TerminalNode K_DESC() {
            return this.getToken(62, 0);
        }

        public Indexed_columnContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 40;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterIndexed_column(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitIndexed_column(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitIndexed_column(this) : visitor.visitChildren(this);
        }
    }

    public static class Raise_functionContext extends ParserRuleContext {
        public TerminalNode K_RAISE() {
            return this.getToken(117, 0);
        }

        public TerminalNode K_IGNORE() {
            return this.getToken(83, 0);
        }

        public SQLiteParser.Error_messageContext error_message() {
            return (SQLiteParser.Error_messageContext)this.getRuleContext(SQLiteParser.Error_messageContext.class, 0);
        }

        public TerminalNode K_ROLLBACK() {
            return this.getToken(127, 0);
        }

        public TerminalNode K_ABORT() {
            return this.getToken(27, 0);
        }

        public TerminalNode K_FAIL() {
            return this.getToken(74, 0);
        }

        public Raise_functionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 39;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterRaise_function(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitRaise_function(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitRaise_function(this) : visitor.visitChildren(this);
        }
    }

    public static class Foreign_key_clauseContext extends ParserRuleContext {
        public TerminalNode K_REFERENCES() {
            return this.getToken(119, 0);
        }

        public SQLiteParser.Foreign_tableContext foreign_table() {
            return (SQLiteParser.Foreign_tableContext)this.getRuleContext(SQLiteParser.Foreign_tableContext.class, 0);
        }

        public List<SQLiteParser.Column_nameContext> column_name() {
            return this.getRuleContexts(SQLiteParser.Column_nameContext.class);
        }

        public SQLiteParser.Column_nameContext column_name(int i) {
            return (SQLiteParser.Column_nameContext)this.getRuleContext(SQLiteParser.Column_nameContext.class, i);
        }

        public TerminalNode K_DEFERRABLE() {
            return this.getToken(59, 0);
        }

        public List<TerminalNode> K_ON() {
            return this.getTokens(109);
        }

        public TerminalNode K_ON(int i) {
            return this.getToken(109, i);
        }

        public List<TerminalNode> K_MATCH() {
            return this.getTokens(101);
        }

        public TerminalNode K_MATCH(int i) {
            return this.getToken(101, i);
        }

        public List<SQLiteParser.NameContext> name() {
            return this.getRuleContexts(SQLiteParser.NameContext.class);
        }

        public SQLiteParser.NameContext name(int i) {
            return (SQLiteParser.NameContext)this.getRuleContext(SQLiteParser.NameContext.class, i);
        }

        public List<TerminalNode> K_DELETE() {
            return this.getTokens(61);
        }

        public TerminalNode K_DELETE(int i) {
            return this.getToken(61, i);
        }

        public List<TerminalNode> K_UPDATE() {
            return this.getTokens(141);
        }

        public TerminalNode K_UPDATE(int i) {
            return this.getToken(141, i);
        }

        public TerminalNode K_NOT() {
            return this.getToken(104, 0);
        }

        public TerminalNode K_INITIALLY() {
            return this.getToken(88, 0);
        }

        public TerminalNode K_DEFERRED() {
            return this.getToken(60, 0);
        }

        public TerminalNode K_IMMEDIATE() {
            return this.getToken(84, 0);
        }

        public List<TerminalNode> K_SET() {
            return this.getTokens(131);
        }

        public TerminalNode K_SET(int i) {
            return this.getToken(131, i);
        }

        public List<TerminalNode> K_NULL() {
            return this.getTokens(106);
        }

        public TerminalNode K_NULL(int i) {
            return this.getToken(106, i);
        }

        public List<TerminalNode> K_DEFAULT() {
            return this.getTokens(58);
        }

        public TerminalNode K_DEFAULT(int i) {
            return this.getToken(58, i);
        }

        public List<TerminalNode> K_CASCADE() {
            return this.getTokens(43);
        }

        public TerminalNode K_CASCADE(int i) {
            return this.getToken(43, i);
        }

        public List<TerminalNode> K_RESTRICT() {
            return this.getTokens(125);
        }

        public TerminalNode K_RESTRICT(int i) {
            return this.getToken(125, i);
        }

        public List<TerminalNode> K_NO() {
            return this.getTokens(103);
        }

        public TerminalNode K_NO(int i) {
            return this.getToken(103, i);
        }

        public List<TerminalNode> K_ACTION() {
            return this.getTokens(28);
        }

        public TerminalNode K_ACTION(int i) {
            return this.getToken(28, i);
        }

        public Foreign_key_clauseContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 38;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterForeign_key_clause(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitForeign_key_clause(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitForeign_key_clause(this) : visitor.visitChildren(this);
        }
    }

    public static class ExprContext extends ParserRuleContext {
        public SQLiteParser.Literal_valueContext literal_value() {
            return (SQLiteParser.Literal_valueContext)this.getRuleContext(SQLiteParser.Literal_valueContext.class, 0);
        }

        public TerminalNode BIND_PARAMETER() {
            return this.getToken(156, 0);
        }

        public SQLiteParser.Column_nameContext column_name() {
            return (SQLiteParser.Column_nameContext)this.getRuleContext(SQLiteParser.Column_nameContext.class, 0);
        }

        public SQLiteParser.Table_nameContext table_name() {
            return (SQLiteParser.Table_nameContext)this.getRuleContext(SQLiteParser.Table_nameContext.class, 0);
        }

        public SQLiteParser.Schema_nameContext schema_name() {
            return (SQLiteParser.Schema_nameContext)this.getRuleContext(SQLiteParser.Schema_nameContext.class, 0);
        }

        public SQLiteParser.Unary_operatorContext unary_operator() {
            return (SQLiteParser.Unary_operatorContext)this.getRuleContext(SQLiteParser.Unary_operatorContext.class, 0);
        }

        public List<SQLiteParser.ExprContext> expr() {
            return this.getRuleContexts(SQLiteParser.ExprContext.class);
        }

        public SQLiteParser.ExprContext expr(int i) {
            return (SQLiteParser.ExprContext)this.getRuleContext(SQLiteParser.ExprContext.class, i);
        }

        public SQLiteParser.Function_nameContext function_name() {
            return (SQLiteParser.Function_nameContext)this.getRuleContext(SQLiteParser.Function_nameContext.class, 0);
        }

        public TerminalNode K_DISTINCT() {
            return this.getToken(64, 0);
        }

        public TerminalNode K_CAST() {
            return this.getToken(45, 0);
        }

        public TerminalNode K_AS() {
            return this.getToken(35, 0);
        }

        public SQLiteParser.Type_nameContext type_name() {
            return (SQLiteParser.Type_nameContext)this.getRuleContext(SQLiteParser.Type_nameContext.class, 0);
        }

        public SQLiteParser.Select_stmtContext select_stmt() {
            return (SQLiteParser.Select_stmtContext)this.getRuleContext(SQLiteParser.Select_stmtContext.class, 0);
        }

        public TerminalNode K_EXISTS() {
            return this.getToken(72, 0);
        }

        public TerminalNode K_NOT() {
            return this.getToken(104, 0);
        }

        public TerminalNode K_CASE() {
            return this.getToken(44, 0);
        }

        public TerminalNode K_END() {
            return this.getToken(68, 0);
        }

        public List<TerminalNode> K_WHEN() {
            return this.getTokens(147);
        }

        public TerminalNode K_WHEN(int i) {
            return this.getToken(147, i);
        }

        public List<TerminalNode> K_THEN() {
            return this.getTokens(135);
        }

        public TerminalNode K_THEN(int i) {
            return this.getToken(135, i);
        }

        public TerminalNode K_ELSE() {
            return this.getToken(67, 0);
        }

        public SQLiteParser.Raise_functionContext raise_function() {
            return (SQLiteParser.Raise_functionContext)this.getRuleContext(SQLiteParser.Raise_functionContext.class, 0);
        }

        public SQLiteParser.Binary_operatorContext binary_operator() {
            return (SQLiteParser.Binary_operatorContext)this.getRuleContext(SQLiteParser.Binary_operatorContext.class, 0);
        }

        public TerminalNode K_IS() {
            return this.getToken(94, 0);
        }

        public TerminalNode K_BETWEEN() {
            return this.getToken(41, 0);
        }

        public TerminalNode K_AND() {
            return this.getToken(34, 0);
        }

        public TerminalNode K_COLLATE() {
            return this.getToken(47, 0);
        }

        public SQLiteParser.Collation_nameContext collation_name() {
            return (SQLiteParser.Collation_nameContext)this.getRuleContext(SQLiteParser.Collation_nameContext.class, 0);
        }

        public TerminalNode K_LIKE() {
            return this.getToken(99, 0);
        }

        public TerminalNode K_GLOB() {
            return this.getToken(79, 0);
        }

        public TerminalNode K_REGEXP() {
            return this.getToken(120, 0);
        }

        public TerminalNode K_MATCH() {
            return this.getToken(101, 0);
        }

        public TerminalNode K_ESCAPE() {
            return this.getToken(69, 0);
        }

        public TerminalNode K_ISNULL() {
            return this.getToken(95, 0);
        }

        public TerminalNode K_NOTNULL() {
            return this.getToken(105, 0);
        }

        public TerminalNode K_NULL() {
            return this.getToken(106, 0);
        }

        public TerminalNode K_IN() {
            return this.getToken(85, 0);
        }

        public SQLiteParser.Table_functionContext table_function() {
            return (SQLiteParser.Table_functionContext)this.getRuleContext(SQLiteParser.Table_functionContext.class, 0);
        }

        public ExprContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 37;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterExpr(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitExpr(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitExpr(this) : visitor.visitChildren(this);
        }
    }

    public static class Conflict_clauseContext extends ParserRuleContext {
        public TerminalNode K_ON() {
            return this.getToken(109, 0);
        }

        public TerminalNode K_CONFLICT() {
            return this.getToken(50, 0);
        }

        public TerminalNode K_ROLLBACK() {
            return this.getToken(127, 0);
        }

        public TerminalNode K_ABORT() {
            return this.getToken(27, 0);
        }

        public TerminalNode K_FAIL() {
            return this.getToken(74, 0);
        }

        public TerminalNode K_IGNORE() {
            return this.getToken(83, 0);
        }

        public TerminalNode K_REPLACE() {
            return this.getToken(124, 0);
        }

        public Conflict_clauseContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 36;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterConflict_clause(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitConflict_clause(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitConflict_clause(this) : visitor.visitChildren(this);
        }
    }

    public static class Column_constraintContext extends ParserRuleContext {
        public TerminalNode K_PRIMARY() {
            return this.getToken(115, 0);
        }

        public TerminalNode K_KEY() {
            return this.getToken(97, 0);
        }

        public SQLiteParser.Conflict_clauseContext conflict_clause() {
            return (SQLiteParser.Conflict_clauseContext)this.getRuleContext(SQLiteParser.Conflict_clauseContext.class, 0);
        }

        public TerminalNode K_NULL() {
            return this.getToken(106, 0);
        }

        public TerminalNode K_UNIQUE() {
            return this.getToken(140, 0);
        }

        public TerminalNode K_CHECK() {
            return this.getToken(46, 0);
        }

        public SQLiteParser.ExprContext expr() {
            return (SQLiteParser.ExprContext)this.getRuleContext(SQLiteParser.ExprContext.class, 0);
        }

        public TerminalNode K_DEFAULT() {
            return this.getToken(58, 0);
        }

        public TerminalNode K_COLLATE() {
            return this.getToken(47, 0);
        }

        public SQLiteParser.Collation_nameContext collation_name() {
            return (SQLiteParser.Collation_nameContext)this.getRuleContext(SQLiteParser.Collation_nameContext.class, 0);
        }

        public SQLiteParser.Foreign_key_clauseContext foreign_key_clause() {
            return (SQLiteParser.Foreign_key_clauseContext)this.getRuleContext(SQLiteParser.Foreign_key_clauseContext.class, 0);
        }

        public TerminalNode K_CONSTRAINT() {
            return this.getToken(51, 0);
        }

        public SQLiteParser.NameContext name() {
            return (SQLiteParser.NameContext)this.getRuleContext(SQLiteParser.NameContext.class, 0);
        }

        public SQLiteParser.Signed_numberContext signed_number() {
            return (SQLiteParser.Signed_numberContext)this.getRuleContext(SQLiteParser.Signed_numberContext.class, 0);
        }

        public SQLiteParser.Literal_valueContext literal_value() {
            return (SQLiteParser.Literal_valueContext)this.getRuleContext(SQLiteParser.Literal_valueContext.class, 0);
        }

        public TerminalNode K_AUTOINCREMENT() {
            return this.getToken(38, 0);
        }

        public TerminalNode K_NOT() {
            return this.getToken(104, 0);
        }

        public TerminalNode K_ASC() {
            return this.getToken(36, 0);
        }

        public TerminalNode K_DESC() {
            return this.getToken(62, 0);
        }

        public Column_constraintContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 35;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterColumn_constraint(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitColumn_constraint(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitColumn_constraint(this) : visitor.visitChildren(this);
        }
    }

    public static class Type_nameContext extends ParserRuleContext {
        public List<SQLiteParser.NameContext> name() {
            return this.getRuleContexts(SQLiteParser.NameContext.class);
        }

        public SQLiteParser.NameContext name(int i) {
            return (SQLiteParser.NameContext)this.getRuleContext(SQLiteParser.NameContext.class, i);
        }

        public List<SQLiteParser.Signed_numberContext> signed_number() {
            return this.getRuleContexts(SQLiteParser.Signed_numberContext.class);
        }

        public SQLiteParser.Signed_numberContext signed_number(int i) {
            return (SQLiteParser.Signed_numberContext)this.getRuleContext(SQLiteParser.Signed_numberContext.class, i);
        }

        public Type_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 34;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterType_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitType_name(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitType_name(this) : visitor.visitChildren(this);
        }
    }

    public static class Column_defContext extends ParserRuleContext {
        public SQLiteParser.Column_nameContext column_name() {
            return (SQLiteParser.Column_nameContext)this.getRuleContext(SQLiteParser.Column_nameContext.class, 0);
        }

        public SQLiteParser.Type_nameContext type_name() {
            return (SQLiteParser.Type_nameContext)this.getRuleContext(SQLiteParser.Type_nameContext.class, 0);
        }

        public List<SQLiteParser.Column_constraintContext> column_constraint() {
            return this.getRuleContexts(SQLiteParser.Column_constraintContext.class);
        }

        public SQLiteParser.Column_constraintContext column_constraint(int i) {
            return (SQLiteParser.Column_constraintContext)this.getRuleContext(SQLiteParser.Column_constraintContext.class, i);
        }

        public Column_defContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 33;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterColumn_def(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitColumn_def(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitColumn_def(this) : visitor.visitChildren(this);
        }
    }

    public static class Vacuum_stmtContext extends ParserRuleContext {
        public TerminalNode K_VACUUM() {
            return this.getToken(143, 0);
        }

        public SQLiteParser.Schema_nameContext schema_name() {
            return (SQLiteParser.Schema_nameContext)this.getRuleContext(SQLiteParser.Schema_nameContext.class, 0);
        }

        public Vacuum_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 32;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterVacuum_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitVacuum_stmt(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitVacuum_stmt(this) : visitor.visitChildren(this);
        }
    }

    public static class Update_stmt_limitedContext extends ParserRuleContext {
        public TerminalNode K_UPDATE() {
            return this.getToken(141, 0);
        }

        public SQLiteParser.Qualified_table_nameContext qualified_table_name() {
            return (SQLiteParser.Qualified_table_nameContext)this.getRuleContext(SQLiteParser.Qualified_table_nameContext.class, 0);
        }

        public TerminalNode K_SET() {
            return this.getToken(131, 0);
        }

        public List<SQLiteParser.ExprContext> expr() {
            return this.getRuleContexts(SQLiteParser.ExprContext.class);
        }

        public SQLiteParser.ExprContext expr(int i) {
            return (SQLiteParser.ExprContext)this.getRuleContext(SQLiteParser.ExprContext.class, i);
        }

        public List<SQLiteParser.Column_nameContext> column_name() {
            return this.getRuleContexts(SQLiteParser.Column_nameContext.class);
        }

        public SQLiteParser.Column_nameContext column_name(int i) {
            return (SQLiteParser.Column_nameContext)this.getRuleContext(SQLiteParser.Column_nameContext.class, i);
        }

        public List<SQLiteParser.Column_name_listContext> column_name_list() {
            return this.getRuleContexts(SQLiteParser.Column_name_listContext.class);
        }

        public SQLiteParser.Column_name_listContext column_name_list(int i) {
            return (SQLiteParser.Column_name_listContext)this.getRuleContext(SQLiteParser.Column_name_listContext.class, i);
        }

        public SQLiteParser.With_clauseContext with_clause() {
            return (SQLiteParser.With_clauseContext)this.getRuleContext(SQLiteParser.With_clauseContext.class, 0);
        }

        public TerminalNode K_OR() {
            return this.getToken(110, 0);
        }

        public TerminalNode K_ROLLBACK() {
            return this.getToken(127, 0);
        }

        public TerminalNode K_ABORT() {
            return this.getToken(27, 0);
        }

        public TerminalNode K_REPLACE() {
            return this.getToken(124, 0);
        }

        public TerminalNode K_FAIL() {
            return this.getToken(74, 0);
        }

        public TerminalNode K_IGNORE() {
            return this.getToken(83, 0);
        }

        public TerminalNode K_WHERE() {
            return this.getToken(148, 0);
        }

        public SQLiteParser.Limit_clauseContext limit_clause() {
            return (SQLiteParser.Limit_clauseContext)this.getRuleContext(SQLiteParser.Limit_clauseContext.class, 0);
        }

        public SQLiteParser.Order_clauseContext order_clause() {
            return (SQLiteParser.Order_clauseContext)this.getRuleContext(SQLiteParser.Order_clauseContext.class, 0);
        }

        public Update_stmt_limitedContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 31;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterUpdate_stmt_limited(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitUpdate_stmt_limited(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitUpdate_stmt_limited(this) : visitor.visitChildren(this);
        }
    }

    public static class Update_stmtContext extends ParserRuleContext {
        public TerminalNode K_UPDATE() {
            return this.getToken(141, 0);
        }

        public SQLiteParser.Qualified_table_nameContext qualified_table_name() {
            return (SQLiteParser.Qualified_table_nameContext)this.getRuleContext(SQLiteParser.Qualified_table_nameContext.class, 0);
        }

        public TerminalNode K_SET() {
            return this.getToken(131, 0);
        }

        public List<SQLiteParser.ExprContext> expr() {
            return this.getRuleContexts(SQLiteParser.ExprContext.class);
        }

        public SQLiteParser.ExprContext expr(int i) {
            return (SQLiteParser.ExprContext)this.getRuleContext(SQLiteParser.ExprContext.class, i);
        }

        public List<SQLiteParser.Column_nameContext> column_name() {
            return this.getRuleContexts(SQLiteParser.Column_nameContext.class);
        }

        public SQLiteParser.Column_nameContext column_name(int i) {
            return (SQLiteParser.Column_nameContext)this.getRuleContext(SQLiteParser.Column_nameContext.class, i);
        }

        public List<SQLiteParser.Column_name_listContext> column_name_list() {
            return this.getRuleContexts(SQLiteParser.Column_name_listContext.class);
        }

        public SQLiteParser.Column_name_listContext column_name_list(int i) {
            return (SQLiteParser.Column_name_listContext)this.getRuleContext(SQLiteParser.Column_name_listContext.class, i);
        }

        public SQLiteParser.With_clauseContext with_clause() {
            return (SQLiteParser.With_clauseContext)this.getRuleContext(SQLiteParser.With_clauseContext.class, 0);
        }

        public TerminalNode K_OR() {
            return this.getToken(110, 0);
        }

        public TerminalNode K_ROLLBACK() {
            return this.getToken(127, 0);
        }

        public TerminalNode K_ABORT() {
            return this.getToken(27, 0);
        }

        public TerminalNode K_REPLACE() {
            return this.getToken(124, 0);
        }

        public TerminalNode K_FAIL() {
            return this.getToken(74, 0);
        }

        public TerminalNode K_IGNORE() {
            return this.getToken(83, 0);
        }

        public TerminalNode K_WHERE() {
            return this.getToken(148, 0);
        }

        public Update_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 30;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterUpdate_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitUpdate_stmt(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitUpdate_stmt(this) : visitor.visitChildren(this);
        }
    }

    public static class Select_or_valuesContext extends ParserRuleContext {
        public TerminalNode K_SELECT() {
            return this.getToken(130, 0);
        }

        public List<SQLiteParser.Result_columnContext> result_column() {
            return this.getRuleContexts(SQLiteParser.Result_columnContext.class);
        }

        public SQLiteParser.Result_columnContext result_column(int i) {
            return (SQLiteParser.Result_columnContext)this.getRuleContext(SQLiteParser.Result_columnContext.class, i);
        }

        public TerminalNode K_FROM() {
            return this.getToken(77, 0);
        }

        public TerminalNode K_WHERE() {
            return this.getToken(148, 0);
        }

        public List<SQLiteParser.ExprContext> expr() {
            return this.getRuleContexts(SQLiteParser.ExprContext.class);
        }

        public SQLiteParser.ExprContext expr(int i) {
            return (SQLiteParser.ExprContext)this.getRuleContext(SQLiteParser.ExprContext.class, i);
        }

        public TerminalNode K_GROUP() {
            return this.getToken(80, 0);
        }

        public TerminalNode K_BY() {
            return this.getToken(42, 0);
        }

        public TerminalNode K_DISTINCT() {
            return this.getToken(64, 0);
        }

        public TerminalNode K_ALL() {
            return this.getToken(31, 0);
        }

        public List<SQLiteParser.Table_or_subqueryContext> table_or_subquery() {
            return this.getRuleContexts(SQLiteParser.Table_or_subqueryContext.class);
        }

        public SQLiteParser.Table_or_subqueryContext table_or_subquery(int i) {
            return (SQLiteParser.Table_or_subqueryContext)this.getRuleContext(SQLiteParser.Table_or_subqueryContext.class, i);
        }

        public SQLiteParser.Join_clauseContext join_clause() {
            return (SQLiteParser.Join_clauseContext)this.getRuleContext(SQLiteParser.Join_clauseContext.class, 0);
        }

        public TerminalNode K_HAVING() {
            return this.getToken(81, 0);
        }

        public TerminalNode K_VALUES() {
            return this.getToken(144, 0);
        }

        public Select_or_valuesContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 29;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterSelect_or_values(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitSelect_or_values(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitSelect_or_values(this) : visitor.visitChildren(this);
        }
    }

    public static class Select_stmtContext extends ParserRuleContext {
        public List<SQLiteParser.Select_or_valuesContext> select_or_values() {
            return this.getRuleContexts(SQLiteParser.Select_or_valuesContext.class);
        }

        public SQLiteParser.Select_or_valuesContext select_or_values(int i) {
            return (SQLiteParser.Select_or_valuesContext)this.getRuleContext(SQLiteParser.Select_or_valuesContext.class, i);
        }

        public SQLiteParser.With_clauseContext with_clause() {
            return (SQLiteParser.With_clauseContext)this.getRuleContext(SQLiteParser.With_clauseContext.class, 0);
        }

        public List<SQLiteParser.Compound_operatorContext> compound_operator() {
            return this.getRuleContexts(SQLiteParser.Compound_operatorContext.class);
        }

        public SQLiteParser.Compound_operatorContext compound_operator(int i) {
            return (SQLiteParser.Compound_operatorContext)this.getRuleContext(SQLiteParser.Compound_operatorContext.class, i);
        }

        public SQLiteParser.Order_clauseContext order_clause() {
            return (SQLiteParser.Order_clauseContext)this.getRuleContext(SQLiteParser.Order_clauseContext.class, 0);
        }

        public SQLiteParser.Limit_clauseContext limit_clause() {
            return (SQLiteParser.Limit_clauseContext)this.getRuleContext(SQLiteParser.Limit_clauseContext.class, 0);
        }

        public Select_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 28;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterSelect_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitSelect_stmt(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitSelect_stmt(this) : visitor.visitChildren(this);
        }
    }

    public static class Savepoint_stmtContext extends ParserRuleContext {
        public TerminalNode K_SAVEPOINT() {
            return this.getToken(129, 0);
        }

        public SQLiteParser.Savepoint_nameContext savepoint_name() {
            return (SQLiteParser.Savepoint_nameContext)this.getRuleContext(SQLiteParser.Savepoint_nameContext.class, 0);
        }

        public Savepoint_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 27;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterSavepoint_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitSavepoint_stmt(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitSavepoint_stmt(this) : visitor.visitChildren(this);
        }
    }

    public static class Rollback_stmtContext extends ParserRuleContext {
        public TerminalNode K_ROLLBACK() {
            return this.getToken(127, 0);
        }

        public TerminalNode K_TRANSACTION() {
            return this.getToken(137, 0);
        }

        public TerminalNode K_TO() {
            return this.getToken(136, 0);
        }

        public SQLiteParser.Savepoint_nameContext savepoint_name() {
            return (SQLiteParser.Savepoint_nameContext)this.getRuleContext(SQLiteParser.Savepoint_nameContext.class, 0);
        }

        public SQLiteParser.Transaction_nameContext transaction_name() {
            return (SQLiteParser.Transaction_nameContext)this.getRuleContext(SQLiteParser.Transaction_nameContext.class, 0);
        }

        public TerminalNode K_SAVEPOINT() {
            return this.getToken(129, 0);
        }

        public Rollback_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 26;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterRollback_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitRollback_stmt(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitRollback_stmt(this) : visitor.visitChildren(this);
        }
    }

    public static class Release_stmtContext extends ParserRuleContext {
        public TerminalNode K_RELEASE() {
            return this.getToken(122, 0);
        }

        public SQLiteParser.Savepoint_nameContext savepoint_name() {
            return (SQLiteParser.Savepoint_nameContext)this.getRuleContext(SQLiteParser.Savepoint_nameContext.class, 0);
        }

        public TerminalNode K_SAVEPOINT() {
            return this.getToken(129, 0);
        }

        public Release_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 25;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterRelease_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitRelease_stmt(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitRelease_stmt(this) : visitor.visitChildren(this);
        }
    }

    public static class Reindex_stmtContext extends ParserRuleContext {
        public TerminalNode K_REINDEX() {
            return this.getToken(121, 0);
        }

        public SQLiteParser.Collation_nameContext collation_name() {
            return (SQLiteParser.Collation_nameContext)this.getRuleContext(SQLiteParser.Collation_nameContext.class, 0);
        }

        public SQLiteParser.Table_nameContext table_name() {
            return (SQLiteParser.Table_nameContext)this.getRuleContext(SQLiteParser.Table_nameContext.class, 0);
        }

        public SQLiteParser.Index_nameContext index_name() {
            return (SQLiteParser.Index_nameContext)this.getRuleContext(SQLiteParser.Index_nameContext.class, 0);
        }

        public SQLiteParser.Schema_nameContext schema_name() {
            return (SQLiteParser.Schema_nameContext)this.getRuleContext(SQLiteParser.Schema_nameContext.class, 0);
        }

        public Reindex_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 24;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterReindex_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitReindex_stmt(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitReindex_stmt(this) : visitor.visitChildren(this);
        }
    }

    public static class Pragma_stmtContext extends ParserRuleContext {
        public TerminalNode K_PRAGMA() {
            return this.getToken(114, 0);
        }

        public SQLiteParser.Pragma_nameContext pragma_name() {
            return (SQLiteParser.Pragma_nameContext)this.getRuleContext(SQLiteParser.Pragma_nameContext.class, 0);
        }

        public SQLiteParser.Schema_nameContext schema_name() {
            return (SQLiteParser.Schema_nameContext)this.getRuleContext(SQLiteParser.Schema_nameContext.class, 0);
        }

        public SQLiteParser.Pragma_valueContext pragma_value() {
            return (SQLiteParser.Pragma_valueContext)this.getRuleContext(SQLiteParser.Pragma_valueContext.class, 0);
        }

        public Pragma_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 23;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterPragma_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitPragma_stmt(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitPragma_stmt(this) : visitor.visitChildren(this);
        }
    }

    public static class Upsert_clauseContext extends ParserRuleContext {
        public TerminalNode K_ON() {
            return this.getToken(109, 0);
        }

        public TerminalNode K_CONFLICT() {
            return this.getToken(50, 0);
        }

        public TerminalNode DO_NOTHING() {
            return this.getToken(152, 0);
        }

        public TerminalNode DO_UPDATE() {
            return this.getToken(153, 0);
        }

        public TerminalNode K_SET() {
            return this.getToken(131, 0);
        }

        public List<SQLiteParser.ExprContext> expr() {
            return this.getRuleContexts(SQLiteParser.ExprContext.class);
        }

        public SQLiteParser.ExprContext expr(int i) {
            return (SQLiteParser.ExprContext)this.getRuleContext(SQLiteParser.ExprContext.class, i);
        }

        public List<SQLiteParser.Indexed_columnContext> indexed_column() {
            return this.getRuleContexts(SQLiteParser.Indexed_columnContext.class);
        }

        public SQLiteParser.Indexed_columnContext indexed_column(int i) {
            return (SQLiteParser.Indexed_columnContext)this.getRuleContext(SQLiteParser.Indexed_columnContext.class, i);
        }

        public List<SQLiteParser.Column_nameContext> column_name() {
            return this.getRuleContexts(SQLiteParser.Column_nameContext.class);
        }

        public SQLiteParser.Column_nameContext column_name(int i) {
            return (SQLiteParser.Column_nameContext)this.getRuleContext(SQLiteParser.Column_nameContext.class, i);
        }

        public List<SQLiteParser.Column_name_listContext> column_name_list() {
            return this.getRuleContexts(SQLiteParser.Column_name_listContext.class);
        }

        public SQLiteParser.Column_name_listContext column_name_list(int i) {
            return (SQLiteParser.Column_name_listContext)this.getRuleContext(SQLiteParser.Column_name_listContext.class, i);
        }

        public List<TerminalNode> K_WHERE() {
            return this.getTokens(148);
        }

        public TerminalNode K_WHERE(int i) {
            return this.getToken(148, i);
        }

        public Upsert_clauseContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 22;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterUpsert_clause(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitUpsert_clause(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitUpsert_clause(this) : visitor.visitChildren(this);
        }
    }

    public static class Insert_stmtContext extends ParserRuleContext {
        public TerminalNode K_INTO() {
            return this.getToken(93, 0);
        }

        public SQLiteParser.Table_nameContext table_name() {
            return (SQLiteParser.Table_nameContext)this.getRuleContext(SQLiteParser.Table_nameContext.class, 0);
        }

        public TerminalNode K_INSERT() {
            return this.getToken(90, 0);
        }

        public TerminalNode K_REPLACE() {
            return this.getToken(124, 0);
        }

        public TerminalNode K_OR() {
            return this.getToken(110, 0);
        }

        public TerminalNode K_ROLLBACK() {
            return this.getToken(127, 0);
        }

        public TerminalNode K_ABORT() {
            return this.getToken(27, 0);
        }

        public TerminalNode K_FAIL() {
            return this.getToken(74, 0);
        }

        public TerminalNode K_IGNORE() {
            return this.getToken(83, 0);
        }

        public TerminalNode K_VALUES() {
            return this.getToken(144, 0);
        }

        public List<SQLiteParser.ExprContext> expr() {
            return this.getRuleContexts(SQLiteParser.ExprContext.class);
        }

        public SQLiteParser.ExprContext expr(int i) {
            return (SQLiteParser.ExprContext)this.getRuleContext(SQLiteParser.ExprContext.class, i);
        }

        public SQLiteParser.Select_stmtContext select_stmt() {
            return (SQLiteParser.Select_stmtContext)this.getRuleContext(SQLiteParser.Select_stmtContext.class, 0);
        }

        public TerminalNode K_DEFAULT() {
            return this.getToken(58, 0);
        }

        public SQLiteParser.With_clauseContext with_clause() {
            return (SQLiteParser.With_clauseContext)this.getRuleContext(SQLiteParser.With_clauseContext.class, 0);
        }

        public SQLiteParser.Schema_nameContext schema_name() {
            return (SQLiteParser.Schema_nameContext)this.getRuleContext(SQLiteParser.Schema_nameContext.class, 0);
        }

        public TerminalNode K_AS() {
            return this.getToken(35, 0);
        }

        public SQLiteParser.Table_aliasContext table_alias() {
            return (SQLiteParser.Table_aliasContext)this.getRuleContext(SQLiteParser.Table_aliasContext.class, 0);
        }

        public List<SQLiteParser.Column_nameContext> column_name() {
            return this.getRuleContexts(SQLiteParser.Column_nameContext.class);
        }

        public SQLiteParser.Column_nameContext column_name(int i) {
            return (SQLiteParser.Column_nameContext)this.getRuleContext(SQLiteParser.Column_nameContext.class, i);
        }

        public SQLiteParser.Upsert_clauseContext upsert_clause() {
            return (SQLiteParser.Upsert_clauseContext)this.getRuleContext(SQLiteParser.Upsert_clauseContext.class, 0);
        }

        public Insert_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 21;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterInsert_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitInsert_stmt(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitInsert_stmt(this) : visitor.visitChildren(this);
        }
    }

    public static class Drop_view_stmtContext extends ParserRuleContext {
        public TerminalNode K_DROP() {
            return this.getToken(65, 0);
        }

        public TerminalNode K_VIEW() {
            return this.getToken(145, 0);
        }

        public SQLiteParser.View_nameContext view_name() {
            return (SQLiteParser.View_nameContext)this.getRuleContext(SQLiteParser.View_nameContext.class, 0);
        }

        public TerminalNode K_IF() {
            return this.getToken(82, 0);
        }

        public TerminalNode K_EXISTS() {
            return this.getToken(72, 0);
        }

        public SQLiteParser.Schema_nameContext schema_name() {
            return (SQLiteParser.Schema_nameContext)this.getRuleContext(SQLiteParser.Schema_nameContext.class, 0);
        }

        public Drop_view_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 20;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterDrop_view_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitDrop_view_stmt(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitDrop_view_stmt(this) : visitor.visitChildren(this);
        }
    }

    public static class Drop_trigger_stmtContext extends ParserRuleContext {
        public TerminalNode K_DROP() {
            return this.getToken(65, 0);
        }

        public TerminalNode K_TRIGGER() {
            return this.getToken(138, 0);
        }

        public SQLiteParser.Trigger_nameContext trigger_name() {
            return (SQLiteParser.Trigger_nameContext)this.getRuleContext(SQLiteParser.Trigger_nameContext.class, 0);
        }

        public TerminalNode K_IF() {
            return this.getToken(82, 0);
        }

        public TerminalNode K_EXISTS() {
            return this.getToken(72, 0);
        }

        public SQLiteParser.Schema_nameContext schema_name() {
            return (SQLiteParser.Schema_nameContext)this.getRuleContext(SQLiteParser.Schema_nameContext.class, 0);
        }

        public Drop_trigger_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 19;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterDrop_trigger_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitDrop_trigger_stmt(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitDrop_trigger_stmt(this) : visitor.visitChildren(this);
        }
    }

    public static class Drop_table_stmtContext extends ParserRuleContext {
        public TerminalNode K_DROP() {
            return this.getToken(65, 0);
        }

        public TerminalNode K_TABLE() {
            return this.getToken(132, 0);
        }

        public SQLiteParser.Table_nameContext table_name() {
            return (SQLiteParser.Table_nameContext)this.getRuleContext(SQLiteParser.Table_nameContext.class, 0);
        }

        public TerminalNode K_IF() {
            return this.getToken(82, 0);
        }

        public TerminalNode K_EXISTS() {
            return this.getToken(72, 0);
        }

        public SQLiteParser.Schema_nameContext schema_name() {
            return (SQLiteParser.Schema_nameContext)this.getRuleContext(SQLiteParser.Schema_nameContext.class, 0);
        }

        public Drop_table_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 18;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterDrop_table_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitDrop_table_stmt(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitDrop_table_stmt(this) : visitor.visitChildren(this);
        }
    }

    public static class Drop_index_stmtContext extends ParserRuleContext {
        public TerminalNode K_DROP() {
            return this.getToken(65, 0);
        }

        public TerminalNode K_INDEX() {
            return this.getToken(86, 0);
        }

        public SQLiteParser.Index_nameContext index_name() {
            return (SQLiteParser.Index_nameContext)this.getRuleContext(SQLiteParser.Index_nameContext.class, 0);
        }

        public TerminalNode K_IF() {
            return this.getToken(82, 0);
        }

        public TerminalNode K_EXISTS() {
            return this.getToken(72, 0);
        }

        public SQLiteParser.Schema_nameContext schema_name() {
            return (SQLiteParser.Schema_nameContext)this.getRuleContext(SQLiteParser.Schema_nameContext.class, 0);
        }

        public Drop_index_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 17;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterDrop_index_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitDrop_index_stmt(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitDrop_index_stmt(this) : visitor.visitChildren(this);
        }
    }

    public static class Detach_stmtContext extends ParserRuleContext {
        public TerminalNode K_DETACH() {
            return this.getToken(63, 0);
        }

        public SQLiteParser.Schema_nameContext schema_name() {
            return (SQLiteParser.Schema_nameContext)this.getRuleContext(SQLiteParser.Schema_nameContext.class, 0);
        }

        public TerminalNode K_DATABASE() {
            return this.getToken(57, 0);
        }

        public Detach_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 16;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterDetach_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitDetach_stmt(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitDetach_stmt(this) : visitor.visitChildren(this);
        }
    }

    public static class Delete_stmt_limitedContext extends ParserRuleContext {
        public TerminalNode K_DELETE() {
            return this.getToken(61, 0);
        }

        public TerminalNode K_FROM() {
            return this.getToken(77, 0);
        }

        public SQLiteParser.Qualified_table_nameContext qualified_table_name() {
            return (SQLiteParser.Qualified_table_nameContext)this.getRuleContext(SQLiteParser.Qualified_table_nameContext.class, 0);
        }

        public SQLiteParser.With_clauseContext with_clause() {
            return (SQLiteParser.With_clauseContext)this.getRuleContext(SQLiteParser.With_clauseContext.class, 0);
        }

        public TerminalNode K_WHERE() {
            return this.getToken(148, 0);
        }

        public SQLiteParser.ExprContext expr() {
            return (SQLiteParser.ExprContext)this.getRuleContext(SQLiteParser.ExprContext.class, 0);
        }

        public SQLiteParser.Limit_clauseContext limit_clause() {
            return (SQLiteParser.Limit_clauseContext)this.getRuleContext(SQLiteParser.Limit_clauseContext.class, 0);
        }

        public SQLiteParser.Order_clauseContext order_clause() {
            return (SQLiteParser.Order_clauseContext)this.getRuleContext(SQLiteParser.Order_clauseContext.class, 0);
        }

        public Delete_stmt_limitedContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 15;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterDelete_stmt_limited(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitDelete_stmt_limited(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitDelete_stmt_limited(this) : visitor.visitChildren(this);
        }
    }

    public static class Delete_stmtContext extends ParserRuleContext {
        public TerminalNode K_DELETE() {
            return this.getToken(61, 0);
        }

        public TerminalNode K_FROM() {
            return this.getToken(77, 0);
        }

        public SQLiteParser.Qualified_table_nameContext qualified_table_name() {
            return (SQLiteParser.Qualified_table_nameContext)this.getRuleContext(SQLiteParser.Qualified_table_nameContext.class, 0);
        }

        public SQLiteParser.With_clauseContext with_clause() {
            return (SQLiteParser.With_clauseContext)this.getRuleContext(SQLiteParser.With_clauseContext.class, 0);
        }

        public TerminalNode K_WHERE() {
            return this.getToken(148, 0);
        }

        public SQLiteParser.ExprContext expr() {
            return (SQLiteParser.ExprContext)this.getRuleContext(SQLiteParser.ExprContext.class, 0);
        }

        public Delete_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 14;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterDelete_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitDelete_stmt(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitDelete_stmt(this) : visitor.visitChildren(this);
        }
    }

    public static class Create_virtual_table_stmtContext extends ParserRuleContext {
        public TerminalNode K_CREATE() {
            return this.getToken(52, 0);
        }

        public TerminalNode K_VIRTUAL() {
            return this.getToken(146, 0);
        }

        public TerminalNode K_TABLE() {
            return this.getToken(132, 0);
        }

        public SQLiteParser.Table_nameContext table_name() {
            return (SQLiteParser.Table_nameContext)this.getRuleContext(SQLiteParser.Table_nameContext.class, 0);
        }

        public TerminalNode K_USING() {
            return this.getToken(142, 0);
        }

        public SQLiteParser.Module_nameContext module_name() {
            return (SQLiteParser.Module_nameContext)this.getRuleContext(SQLiteParser.Module_nameContext.class, 0);
        }

        public TerminalNode K_IF() {
            return this.getToken(82, 0);
        }

        public TerminalNode K_NOT() {
            return this.getToken(104, 0);
        }

        public TerminalNode K_EXISTS() {
            return this.getToken(72, 0);
        }

        public SQLiteParser.Schema_nameContext schema_name() {
            return (SQLiteParser.Schema_nameContext)this.getRuleContext(SQLiteParser.Schema_nameContext.class, 0);
        }

        public List<SQLiteParser.Module_argumentContext> module_argument() {
            return this.getRuleContexts(SQLiteParser.Module_argumentContext.class);
        }

        public SQLiteParser.Module_argumentContext module_argument(int i) {
            return (SQLiteParser.Module_argumentContext)this.getRuleContext(SQLiteParser.Module_argumentContext.class, i);
        }

        public Create_virtual_table_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 13;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterCreate_virtual_table_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitCreate_virtual_table_stmt(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitCreate_virtual_table_stmt(this) : visitor.visitChildren(this);
        }
    }

    public static class Create_view_stmtContext extends ParserRuleContext {
        public TerminalNode K_CREATE() {
            return this.getToken(52, 0);
        }

        public TerminalNode K_VIEW() {
            return this.getToken(145, 0);
        }

        public SQLiteParser.View_nameContext view_name() {
            return (SQLiteParser.View_nameContext)this.getRuleContext(SQLiteParser.View_nameContext.class, 0);
        }

        public TerminalNode K_AS() {
            return this.getToken(35, 0);
        }

        public SQLiteParser.Select_stmtContext select_stmt() {
            return (SQLiteParser.Select_stmtContext)this.getRuleContext(SQLiteParser.Select_stmtContext.class, 0);
        }

        public TerminalNode K_IF() {
            return this.getToken(82, 0);
        }

        public TerminalNode K_NOT() {
            return this.getToken(104, 0);
        }

        public TerminalNode K_EXISTS() {
            return this.getToken(72, 0);
        }

        public SQLiteParser.Schema_nameContext schema_name() {
            return (SQLiteParser.Schema_nameContext)this.getRuleContext(SQLiteParser.Schema_nameContext.class, 0);
        }

        public List<SQLiteParser.Column_nameContext> column_name() {
            return this.getRuleContexts(SQLiteParser.Column_nameContext.class);
        }

        public SQLiteParser.Column_nameContext column_name(int i) {
            return (SQLiteParser.Column_nameContext)this.getRuleContext(SQLiteParser.Column_nameContext.class, i);
        }

        public TerminalNode K_TEMP() {
            return this.getToken(133, 0);
        }

        public TerminalNode K_TEMPORARY() {
            return this.getToken(134, 0);
        }

        public Create_view_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 12;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterCreate_view_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitCreate_view_stmt(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitCreate_view_stmt(this) : visitor.visitChildren(this);
        }
    }

    public static class Create_trigger_stmtContext extends ParserRuleContext {
        public TerminalNode K_CREATE() {
            return this.getToken(52, 0);
        }

        public TerminalNode K_TRIGGER() {
            return this.getToken(138, 0);
        }

        public SQLiteParser.Trigger_nameContext trigger_name() {
            return (SQLiteParser.Trigger_nameContext)this.getRuleContext(SQLiteParser.Trigger_nameContext.class, 0);
        }

        public TerminalNode K_ON() {
            return this.getToken(109, 0);
        }

        public SQLiteParser.Table_nameContext table_name() {
            return (SQLiteParser.Table_nameContext)this.getRuleContext(SQLiteParser.Table_nameContext.class, 0);
        }

        public TerminalNode K_BEGIN() {
            return this.getToken(40, 0);
        }

        public TerminalNode K_END() {
            return this.getToken(68, 0);
        }

        public TerminalNode K_DELETE() {
            return this.getToken(61, 0);
        }

        public TerminalNode K_INSERT() {
            return this.getToken(90, 0);
        }

        public TerminalNode K_UPDATE() {
            return this.getToken(141, 0);
        }

        public TerminalNode K_IF() {
            return this.getToken(82, 0);
        }

        public TerminalNode K_NOT() {
            return this.getToken(104, 0);
        }

        public TerminalNode K_EXISTS() {
            return this.getToken(72, 0);
        }

        public List<SQLiteParser.Schema_nameContext> schema_name() {
            return this.getRuleContexts(SQLiteParser.Schema_nameContext.class);
        }

        public SQLiteParser.Schema_nameContext schema_name(int i) {
            return (SQLiteParser.Schema_nameContext)this.getRuleContext(SQLiteParser.Schema_nameContext.class, i);
        }

        public TerminalNode K_BEFORE() {
            return this.getToken(39, 0);
        }

        public TerminalNode K_AFTER() {
            return this.getToken(30, 0);
        }

        public TerminalNode K_INSTEAD() {
            return this.getToken(91, 0);
        }

        public List<TerminalNode> K_OF() {
            return this.getTokens(107);
        }

        public TerminalNode K_OF(int i) {
            return this.getToken(107, i);
        }

        public TerminalNode K_FOR() {
            return this.getToken(75, 0);
        }

        public TerminalNode K_EACH() {
            return this.getToken(66, 0);
        }

        public TerminalNode K_ROW() {
            return this.getToken(128, 0);
        }

        public TerminalNode K_WHEN() {
            return this.getToken(147, 0);
        }

        public SQLiteParser.ExprContext expr() {
            return (SQLiteParser.ExprContext)this.getRuleContext(SQLiteParser.ExprContext.class, 0);
        }

        public TerminalNode K_TEMP() {
            return this.getToken(133, 0);
        }

        public TerminalNode K_TEMPORARY() {
            return this.getToken(134, 0);
        }

        public List<SQLiteParser.Column_nameContext> column_name() {
            return this.getRuleContexts(SQLiteParser.Column_nameContext.class);
        }

        public SQLiteParser.Column_nameContext column_name(int i) {
            return (SQLiteParser.Column_nameContext)this.getRuleContext(SQLiteParser.Column_nameContext.class, i);
        }

        public List<SQLiteParser.Update_stmtContext> update_stmt() {
            return this.getRuleContexts(SQLiteParser.Update_stmtContext.class);
        }

        public SQLiteParser.Update_stmtContext update_stmt(int i) {
            return (SQLiteParser.Update_stmtContext)this.getRuleContext(SQLiteParser.Update_stmtContext.class, i);
        }

        public List<SQLiteParser.Insert_stmtContext> insert_stmt() {
            return this.getRuleContexts(SQLiteParser.Insert_stmtContext.class);
        }

        public SQLiteParser.Insert_stmtContext insert_stmt(int i) {
            return (SQLiteParser.Insert_stmtContext)this.getRuleContext(SQLiteParser.Insert_stmtContext.class, i);
        }

        public List<SQLiteParser.Delete_stmtContext> delete_stmt() {
            return this.getRuleContexts(SQLiteParser.Delete_stmtContext.class);
        }

        public SQLiteParser.Delete_stmtContext delete_stmt(int i) {
            return (SQLiteParser.Delete_stmtContext)this.getRuleContext(SQLiteParser.Delete_stmtContext.class, i);
        }

        public List<SQLiteParser.Select_stmtContext> select_stmt() {
            return this.getRuleContexts(SQLiteParser.Select_stmtContext.class);
        }

        public SQLiteParser.Select_stmtContext select_stmt(int i) {
            return (SQLiteParser.Select_stmtContext)this.getRuleContext(SQLiteParser.Select_stmtContext.class, i);
        }

        public Create_trigger_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 11;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterCreate_trigger_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitCreate_trigger_stmt(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitCreate_trigger_stmt(this) : visitor.visitChildren(this);
        }
    }

    public static class Create_table_stmtContext extends ParserRuleContext {
        public TerminalNode K_CREATE() {
            return this.getToken(52, 0);
        }

        public TerminalNode K_TABLE() {
            return this.getToken(132, 0);
        }

        public SQLiteParser.Table_nameContext table_name() {
            return (SQLiteParser.Table_nameContext)this.getRuleContext(SQLiteParser.Table_nameContext.class, 0);
        }

        public List<SQLiteParser.Column_defContext> column_def() {
            return this.getRuleContexts(SQLiteParser.Column_defContext.class);
        }

        public SQLiteParser.Column_defContext column_def(int i) {
            return (SQLiteParser.Column_defContext)this.getRuleContext(SQLiteParser.Column_defContext.class, i);
        }

        public TerminalNode K_AS() {
            return this.getToken(35, 0);
        }

        public SQLiteParser.Select_stmtContext select_stmt() {
            return (SQLiteParser.Select_stmtContext)this.getRuleContext(SQLiteParser.Select_stmtContext.class, 0);
        }

        public TerminalNode K_IF() {
            return this.getToken(82, 0);
        }

        public TerminalNode K_NOT() {
            return this.getToken(104, 0);
        }

        public TerminalNode K_EXISTS() {
            return this.getToken(72, 0);
        }

        public SQLiteParser.Schema_nameContext schema_name() {
            return (SQLiteParser.Schema_nameContext)this.getRuleContext(SQLiteParser.Schema_nameContext.class, 0);
        }

        public TerminalNode K_TEMP() {
            return this.getToken(133, 0);
        }

        public TerminalNode K_TEMPORARY() {
            return this.getToken(134, 0);
        }

        public List<SQLiteParser.Table_constraintContext> table_constraint() {
            return this.getRuleContexts(SQLiteParser.Table_constraintContext.class);
        }

        public SQLiteParser.Table_constraintContext table_constraint(int i) {
            return (SQLiteParser.Table_constraintContext)this.getRuleContext(SQLiteParser.Table_constraintContext.class, i);
        }

        public TerminalNode WITHOUT_ROWID() {
            return this.getToken(151, 0);
        }

        public Create_table_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 10;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterCreate_table_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitCreate_table_stmt(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitCreate_table_stmt(this) : visitor.visitChildren(this);
        }
    }

    public static class Create_index_stmtContext extends ParserRuleContext {
        public TerminalNode K_CREATE() {
            return this.getToken(52, 0);
        }

        public TerminalNode K_INDEX() {
            return this.getToken(86, 0);
        }

        public SQLiteParser.Index_nameContext index_name() {
            return (SQLiteParser.Index_nameContext)this.getRuleContext(SQLiteParser.Index_nameContext.class, 0);
        }

        public TerminalNode K_ON() {
            return this.getToken(109, 0);
        }

        public SQLiteParser.Table_nameContext table_name() {
            return (SQLiteParser.Table_nameContext)this.getRuleContext(SQLiteParser.Table_nameContext.class, 0);
        }

        public List<SQLiteParser.Indexed_columnContext> indexed_column() {
            return this.getRuleContexts(SQLiteParser.Indexed_columnContext.class);
        }

        public SQLiteParser.Indexed_columnContext indexed_column(int i) {
            return (SQLiteParser.Indexed_columnContext)this.getRuleContext(SQLiteParser.Indexed_columnContext.class, i);
        }

        public TerminalNode K_UNIQUE() {
            return this.getToken(140, 0);
        }

        public TerminalNode K_IF() {
            return this.getToken(82, 0);
        }

        public TerminalNode K_NOT() {
            return this.getToken(104, 0);
        }

        public TerminalNode K_EXISTS() {
            return this.getToken(72, 0);
        }

        public SQLiteParser.Schema_nameContext schema_name() {
            return (SQLiteParser.Schema_nameContext)this.getRuleContext(SQLiteParser.Schema_nameContext.class, 0);
        }

        public TerminalNode K_WHERE() {
            return this.getToken(148, 0);
        }

        public SQLiteParser.ExprContext expr() {
            return (SQLiteParser.ExprContext)this.getRuleContext(SQLiteParser.ExprContext.class, 0);
        }

        public Create_index_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 9;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterCreate_index_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitCreate_index_stmt(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitCreate_index_stmt(this) : visitor.visitChildren(this);
        }
    }

    public static class Commit_stmtContext extends ParserRuleContext {
        public TerminalNode K_COMMIT() {
            return this.getToken(49, 0);
        }

        public TerminalNode K_END() {
            return this.getToken(68, 0);
        }

        public TerminalNode K_TRANSACTION() {
            return this.getToken(137, 0);
        }

        public SQLiteParser.Transaction_nameContext transaction_name() {
            return (SQLiteParser.Transaction_nameContext)this.getRuleContext(SQLiteParser.Transaction_nameContext.class, 0);
        }

        public Commit_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 8;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterCommit_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitCommit_stmt(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitCommit_stmt(this) : visitor.visitChildren(this);
        }
    }

    public static class Begin_stmtContext extends ParserRuleContext {
        public TerminalNode K_BEGIN() {
            return this.getToken(40, 0);
        }

        public TerminalNode K_TRANSACTION() {
            return this.getToken(137, 0);
        }

        public TerminalNode K_DEFERRED() {
            return this.getToken(60, 0);
        }

        public TerminalNode K_IMMEDIATE() {
            return this.getToken(84, 0);
        }

        public TerminalNode K_EXCLUSIVE() {
            return this.getToken(71, 0);
        }

        public SQLiteParser.Transaction_nameContext transaction_name() {
            return (SQLiteParser.Transaction_nameContext)this.getRuleContext(SQLiteParser.Transaction_nameContext.class, 0);
        }

        public Begin_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 7;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterBegin_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitBegin_stmt(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitBegin_stmt(this) : visitor.visitChildren(this);
        }
    }

    public static class Attach_stmtContext extends ParserRuleContext {
        public TerminalNode K_ATTACH() {
            return this.getToken(37, 0);
        }

        public SQLiteParser.ExprContext expr() {
            return (SQLiteParser.ExprContext)this.getRuleContext(SQLiteParser.ExprContext.class, 0);
        }

        public TerminalNode K_AS() {
            return this.getToken(35, 0);
        }

        public SQLiteParser.Schema_nameContext schema_name() {
            return (SQLiteParser.Schema_nameContext)this.getRuleContext(SQLiteParser.Schema_nameContext.class, 0);
        }

        public TerminalNode K_DATABASE() {
            return this.getToken(57, 0);
        }

        public Attach_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 6;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterAttach_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitAttach_stmt(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitAttach_stmt(this) : visitor.visitChildren(this);
        }
    }

    public static class Analyze_stmtContext extends ParserRuleContext {
        public TerminalNode K_ANALYZE() {
            return this.getToken(33, 0);
        }

        public SQLiteParser.Schema_nameContext schema_name() {
            return (SQLiteParser.Schema_nameContext)this.getRuleContext(SQLiteParser.Schema_nameContext.class, 0);
        }

        public SQLiteParser.Table_or_index_nameContext table_or_index_name() {
            return (SQLiteParser.Table_or_index_nameContext)this.getRuleContext(SQLiteParser.Table_or_index_nameContext.class, 0);
        }

        public Analyze_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 5;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterAnalyze_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitAnalyze_stmt(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitAnalyze_stmt(this) : visitor.visitChildren(this);
        }
    }

    public static class Alter_table_stmtContext extends ParserRuleContext {
        public TerminalNode K_ALTER() {
            return this.getToken(32, 0);
        }

        public TerminalNode K_TABLE() {
            return this.getToken(132, 0);
        }

        public SQLiteParser.Table_nameContext table_name() {
            return (SQLiteParser.Table_nameContext)this.getRuleContext(SQLiteParser.Table_nameContext.class, 0);
        }

        public TerminalNode K_RENAME() {
            return this.getToken(123, 0);
        }

        public TerminalNode K_TO() {
            return this.getToken(136, 0);
        }

        public SQLiteParser.New_table_nameContext new_table_name() {
            return (SQLiteParser.New_table_nameContext)this.getRuleContext(SQLiteParser.New_table_nameContext.class, 0);
        }

        public TerminalNode K_ADD() {
            return this.getToken(29, 0);
        }

        public SQLiteParser.Column_defContext column_def() {
            return (SQLiteParser.Column_defContext)this.getRuleContext(SQLiteParser.Column_defContext.class, 0);
        }

        public SQLiteParser.Schema_nameContext schema_name() {
            return (SQLiteParser.Schema_nameContext)this.getRuleContext(SQLiteParser.Schema_nameContext.class, 0);
        }

        public TerminalNode K_COLUMN() {
            return this.getToken(48, 0);
        }

        public Alter_table_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 4;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterAlter_table_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitAlter_table_stmt(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitAlter_table_stmt(this) : visitor.visitChildren(this);
        }
    }

    public static class Sql_stmtContext extends ParserRuleContext {
        public SQLiteParser.Alter_table_stmtContext alter_table_stmt() {
            return (SQLiteParser.Alter_table_stmtContext)this.getRuleContext(SQLiteParser.Alter_table_stmtContext.class, 0);
        }

        public SQLiteParser.Analyze_stmtContext analyze_stmt() {
            return (SQLiteParser.Analyze_stmtContext)this.getRuleContext(SQLiteParser.Analyze_stmtContext.class, 0);
        }

        public SQLiteParser.Attach_stmtContext attach_stmt() {
            return (SQLiteParser.Attach_stmtContext)this.getRuleContext(SQLiteParser.Attach_stmtContext.class, 0);
        }

        public SQLiteParser.Begin_stmtContext begin_stmt() {
            return (SQLiteParser.Begin_stmtContext)this.getRuleContext(SQLiteParser.Begin_stmtContext.class, 0);
        }

        public SQLiteParser.Commit_stmtContext commit_stmt() {
            return (SQLiteParser.Commit_stmtContext)this.getRuleContext(SQLiteParser.Commit_stmtContext.class, 0);
        }

        public SQLiteParser.Create_index_stmtContext create_index_stmt() {
            return (SQLiteParser.Create_index_stmtContext)this.getRuleContext(SQLiteParser.Create_index_stmtContext.class, 0);
        }

        public SQLiteParser.Create_table_stmtContext create_table_stmt() {
            return (SQLiteParser.Create_table_stmtContext)this.getRuleContext(SQLiteParser.Create_table_stmtContext.class, 0);
        }

        public SQLiteParser.Create_trigger_stmtContext create_trigger_stmt() {
            return (SQLiteParser.Create_trigger_stmtContext)this.getRuleContext(SQLiteParser.Create_trigger_stmtContext.class, 0);
        }

        public SQLiteParser.Create_view_stmtContext create_view_stmt() {
            return (SQLiteParser.Create_view_stmtContext)this.getRuleContext(SQLiteParser.Create_view_stmtContext.class, 0);
        }

        public SQLiteParser.Create_virtual_table_stmtContext create_virtual_table_stmt() {
            return (SQLiteParser.Create_virtual_table_stmtContext)this.getRuleContext(SQLiteParser.Create_virtual_table_stmtContext.class, 0);
        }

        public SQLiteParser.Delete_stmtContext delete_stmt() {
            return (SQLiteParser.Delete_stmtContext)this.getRuleContext(SQLiteParser.Delete_stmtContext.class, 0);
        }

        public SQLiteParser.Delete_stmt_limitedContext delete_stmt_limited() {
            return (SQLiteParser.Delete_stmt_limitedContext)this.getRuleContext(SQLiteParser.Delete_stmt_limitedContext.class, 0);
        }

        public SQLiteParser.Detach_stmtContext detach_stmt() {
            return (SQLiteParser.Detach_stmtContext)this.getRuleContext(SQLiteParser.Detach_stmtContext.class, 0);
        }

        public SQLiteParser.Drop_index_stmtContext drop_index_stmt() {
            return (SQLiteParser.Drop_index_stmtContext)this.getRuleContext(SQLiteParser.Drop_index_stmtContext.class, 0);
        }

        public SQLiteParser.Drop_table_stmtContext drop_table_stmt() {
            return (SQLiteParser.Drop_table_stmtContext)this.getRuleContext(SQLiteParser.Drop_table_stmtContext.class, 0);
        }

        public SQLiteParser.Drop_trigger_stmtContext drop_trigger_stmt() {
            return (SQLiteParser.Drop_trigger_stmtContext)this.getRuleContext(SQLiteParser.Drop_trigger_stmtContext.class, 0);
        }

        public SQLiteParser.Drop_view_stmtContext drop_view_stmt() {
            return (SQLiteParser.Drop_view_stmtContext)this.getRuleContext(SQLiteParser.Drop_view_stmtContext.class, 0);
        }

        public SQLiteParser.Insert_stmtContext insert_stmt() {
            return (SQLiteParser.Insert_stmtContext)this.getRuleContext(SQLiteParser.Insert_stmtContext.class, 0);
        }

        public SQLiteParser.Pragma_stmtContext pragma_stmt() {
            return (SQLiteParser.Pragma_stmtContext)this.getRuleContext(SQLiteParser.Pragma_stmtContext.class, 0);
        }

        public SQLiteParser.Reindex_stmtContext reindex_stmt() {
            return (SQLiteParser.Reindex_stmtContext)this.getRuleContext(SQLiteParser.Reindex_stmtContext.class, 0);
        }

        public SQLiteParser.Release_stmtContext release_stmt() {
            return (SQLiteParser.Release_stmtContext)this.getRuleContext(SQLiteParser.Release_stmtContext.class, 0);
        }

        public SQLiteParser.Rollback_stmtContext rollback_stmt() {
            return (SQLiteParser.Rollback_stmtContext)this.getRuleContext(SQLiteParser.Rollback_stmtContext.class, 0);
        }

        public SQLiteParser.Savepoint_stmtContext savepoint_stmt() {
            return (SQLiteParser.Savepoint_stmtContext)this.getRuleContext(SQLiteParser.Savepoint_stmtContext.class, 0);
        }

        public SQLiteParser.Select_stmtContext select_stmt() {
            return (SQLiteParser.Select_stmtContext)this.getRuleContext(SQLiteParser.Select_stmtContext.class, 0);
        }

        public SQLiteParser.Update_stmtContext update_stmt() {
            return (SQLiteParser.Update_stmtContext)this.getRuleContext(SQLiteParser.Update_stmtContext.class, 0);
        }

        public SQLiteParser.Update_stmt_limitedContext update_stmt_limited() {
            return (SQLiteParser.Update_stmt_limitedContext)this.getRuleContext(SQLiteParser.Update_stmt_limitedContext.class, 0);
        }

        public SQLiteParser.Vacuum_stmtContext vacuum_stmt() {
            return (SQLiteParser.Vacuum_stmtContext)this.getRuleContext(SQLiteParser.Vacuum_stmtContext.class, 0);
        }

        public TerminalNode K_EXPLAIN() {
            return this.getToken(73, 0);
        }

        public TerminalNode K_QUERY() {
            return this.getToken(116, 0);
        }

        public TerminalNode K_PLAN() {
            return this.getToken(113, 0);
        }

        public Sql_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 3;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterSql_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitSql_stmt(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitSql_stmt(this) : visitor.visitChildren(this);
        }
    }

    public static class Sql_stmt_listContext extends ParserRuleContext {
        public List<SQLiteParser.Sql_stmtContext> sql_stmt() {
            return this.getRuleContexts(SQLiteParser.Sql_stmtContext.class);
        }

        public SQLiteParser.Sql_stmtContext sql_stmt(int i) {
            return (SQLiteParser.Sql_stmtContext)this.getRuleContext(SQLiteParser.Sql_stmtContext.class, i);
        }

        public Sql_stmt_listContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 2;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterSql_stmt_list(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitSql_stmt_list(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitSql_stmt_list(this) : visitor.visitChildren(this);
        }
    }

    public static class ErrorContext extends ParserRuleContext {
        public Token UNEXPECTED_CHAR;

        public TerminalNode UNEXPECTED_CHAR() {
            return this.getToken(162, 0);
        }

        public ErrorContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 1;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterError(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitError(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitError(this) : visitor.visitChildren(this);
        }
    }

    public static class ParseContext extends ParserRuleContext {
        public TerminalNode EOF() {
            return this.getToken(-1, 0);
        }

        public List<SQLiteParser.Sql_stmt_listContext> sql_stmt_list() {
            return this.getRuleContexts(SQLiteParser.Sql_stmt_listContext.class);
        }

        public SQLiteParser.Sql_stmt_listContext sql_stmt_list(int i) {
            return (SQLiteParser.Sql_stmt_listContext)this.getRuleContext(SQLiteParser.Sql_stmt_listContext.class, i);
        }

        public List<SQLiteParser.ErrorContext> error() {
            return this.getRuleContexts(SQLiteParser.ErrorContext.class);
        }

        public SQLiteParser.ErrorContext error(int i) {
            return (SQLiteParser.ErrorContext)this.getRuleContext(SQLiteParser.ErrorContext.class, i);
        }

        public ParseContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 0;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterParse(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitParse(this);
            }

        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            return visitor instanceof SQLiteVisitor ? (T)((SQLiteVisitor)visitor).visitParse(this) : visitor.visitChildren(this);
        }
    }
}
