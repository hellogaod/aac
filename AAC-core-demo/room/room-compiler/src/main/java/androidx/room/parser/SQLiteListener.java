//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package androidx.room.parser;

import androidx.room.parser.SQLiteParser.Alter_table_stmtContext;
import androidx.room.parser.SQLiteParser.Analyze_stmtContext;
import androidx.room.parser.SQLiteParser.Any_nameContext;
import androidx.room.parser.SQLiteParser.Attach_stmtContext;
import androidx.room.parser.SQLiteParser.Begin_stmtContext;
import androidx.room.parser.SQLiteParser.Binary_operatorContext;
import androidx.room.parser.SQLiteParser.Boolean_literalContext;
import androidx.room.parser.SQLiteParser.Collation_nameContext;
import androidx.room.parser.SQLiteParser.Column_aliasContext;
import androidx.room.parser.SQLiteParser.Column_constraintContext;
import androidx.room.parser.SQLiteParser.Column_defContext;
import androidx.room.parser.SQLiteParser.Column_nameContext;
import androidx.room.parser.SQLiteParser.Column_name_listContext;
import androidx.room.parser.SQLiteParser.Commit_stmtContext;
import androidx.room.parser.SQLiteParser.Common_table_expressionContext;
import androidx.room.parser.SQLiteParser.Compound_operatorContext;
import androidx.room.parser.SQLiteParser.Conflict_clauseContext;
import androidx.room.parser.SQLiteParser.Create_index_stmtContext;
import androidx.room.parser.SQLiteParser.Create_table_stmtContext;
import androidx.room.parser.SQLiteParser.Create_trigger_stmtContext;
import androidx.room.parser.SQLiteParser.Create_view_stmtContext;
import androidx.room.parser.SQLiteParser.Create_virtual_table_stmtContext;
import androidx.room.parser.SQLiteParser.Delete_stmtContext;
import androidx.room.parser.SQLiteParser.Delete_stmt_limitedContext;
import androidx.room.parser.SQLiteParser.Detach_stmtContext;
import androidx.room.parser.SQLiteParser.Drop_index_stmtContext;
import androidx.room.parser.SQLiteParser.Drop_table_stmtContext;
import androidx.room.parser.SQLiteParser.Drop_trigger_stmtContext;
import androidx.room.parser.SQLiteParser.Drop_view_stmtContext;
import androidx.room.parser.SQLiteParser.ErrorContext;
import androidx.room.parser.SQLiteParser.Error_messageContext;
import androidx.room.parser.SQLiteParser.ExprContext;
import androidx.room.parser.SQLiteParser.Foreign_key_clauseContext;
import androidx.room.parser.SQLiteParser.Foreign_tableContext;
import androidx.room.parser.SQLiteParser.Function_nameContext;
import androidx.room.parser.SQLiteParser.Index_nameContext;
import androidx.room.parser.SQLiteParser.Indexed_columnContext;
import androidx.room.parser.SQLiteParser.Insert_stmtContext;
import androidx.room.parser.SQLiteParser.Join_clauseContext;
import androidx.room.parser.SQLiteParser.Join_constraintContext;
import androidx.room.parser.SQLiteParser.Join_operatorContext;
import androidx.room.parser.SQLiteParser.KeywordContext;
import androidx.room.parser.SQLiteParser.Limit_clauseContext;
import androidx.room.parser.SQLiteParser.Literal_valueContext;
import androidx.room.parser.SQLiteParser.Module_argumentContext;
import androidx.room.parser.SQLiteParser.Module_nameContext;
import androidx.room.parser.SQLiteParser.NameContext;
import androidx.room.parser.SQLiteParser.New_table_nameContext;
import androidx.room.parser.SQLiteParser.Order_clauseContext;
import androidx.room.parser.SQLiteParser.Ordering_termContext;
import androidx.room.parser.SQLiteParser.ParseContext;
import androidx.room.parser.SQLiteParser.Pragma_nameContext;
import androidx.room.parser.SQLiteParser.Pragma_stmtContext;
import androidx.room.parser.SQLiteParser.Pragma_valueContext;
import androidx.room.parser.SQLiteParser.Qualified_table_nameContext;
import androidx.room.parser.SQLiteParser.Raise_functionContext;
import androidx.room.parser.SQLiteParser.Reindex_stmtContext;
import androidx.room.parser.SQLiteParser.Release_stmtContext;
import androidx.room.parser.SQLiteParser.Result_columnContext;
import androidx.room.parser.SQLiteParser.Rollback_stmtContext;
import androidx.room.parser.SQLiteParser.Savepoint_nameContext;
import androidx.room.parser.SQLiteParser.Savepoint_stmtContext;
import androidx.room.parser.SQLiteParser.Schema_nameContext;
import androidx.room.parser.SQLiteParser.Select_or_valuesContext;
import androidx.room.parser.SQLiteParser.Select_stmtContext;
import androidx.room.parser.SQLiteParser.Signed_numberContext;
import androidx.room.parser.SQLiteParser.Sql_stmtContext;
import androidx.room.parser.SQLiteParser.Sql_stmt_listContext;
import androidx.room.parser.SQLiteParser.Table_aliasContext;
import androidx.room.parser.SQLiteParser.Table_constraintContext;
import androidx.room.parser.SQLiteParser.Table_functionContext;
import androidx.room.parser.SQLiteParser.Table_nameContext;
import androidx.room.parser.SQLiteParser.Table_or_index_nameContext;
import androidx.room.parser.SQLiteParser.Table_or_subqueryContext;
import androidx.room.parser.SQLiteParser.Transaction_nameContext;
import androidx.room.parser.SQLiteParser.Trigger_nameContext;
import androidx.room.parser.SQLiteParser.Type_nameContext;
import androidx.room.parser.SQLiteParser.Unary_operatorContext;
import androidx.room.parser.SQLiteParser.Update_stmtContext;
import androidx.room.parser.SQLiteParser.Update_stmt_limitedContext;
import androidx.room.parser.SQLiteParser.Upsert_clauseContext;
import androidx.room.parser.SQLiteParser.Vacuum_stmtContext;
import androidx.room.parser.SQLiteParser.View_nameContext;
import androidx.room.parser.SQLiteParser.With_clauseContext;
import org.antlr.v4.runtime.tree.ParseTreeListener;

public interface SQLiteListener extends ParseTreeListener {
    void enterParse(ParseContext var1);

    void exitParse(ParseContext var1);

    void enterError(ErrorContext var1);

    void exitError(ErrorContext var1);

    void enterSql_stmt_list(Sql_stmt_listContext var1);

    void exitSql_stmt_list(Sql_stmt_listContext var1);

    void enterSql_stmt(Sql_stmtContext var1);

    void exitSql_stmt(Sql_stmtContext var1);

    void enterAlter_table_stmt(Alter_table_stmtContext var1);

    void exitAlter_table_stmt(Alter_table_stmtContext var1);

    void enterAnalyze_stmt(Analyze_stmtContext var1);

    void exitAnalyze_stmt(Analyze_stmtContext var1);

    void enterAttach_stmt(Attach_stmtContext var1);

    void exitAttach_stmt(Attach_stmtContext var1);

    void enterBegin_stmt(Begin_stmtContext var1);

    void exitBegin_stmt(Begin_stmtContext var1);

    void enterCommit_stmt(Commit_stmtContext var1);

    void exitCommit_stmt(Commit_stmtContext var1);

    void enterCreate_index_stmt(Create_index_stmtContext var1);

    void exitCreate_index_stmt(Create_index_stmtContext var1);

    void enterCreate_table_stmt(Create_table_stmtContext var1);

    void exitCreate_table_stmt(Create_table_stmtContext var1);

    void enterCreate_trigger_stmt(Create_trigger_stmtContext var1);

    void exitCreate_trigger_stmt(Create_trigger_stmtContext var1);

    void enterCreate_view_stmt(Create_view_stmtContext var1);

    void exitCreate_view_stmt(Create_view_stmtContext var1);

    void enterCreate_virtual_table_stmt(Create_virtual_table_stmtContext var1);

    void exitCreate_virtual_table_stmt(Create_virtual_table_stmtContext var1);

    void enterDelete_stmt(Delete_stmtContext var1);

    void exitDelete_stmt(Delete_stmtContext var1);

    void enterDelete_stmt_limited(Delete_stmt_limitedContext var1);

    void exitDelete_stmt_limited(Delete_stmt_limitedContext var1);

    void enterDetach_stmt(Detach_stmtContext var1);

    void exitDetach_stmt(Detach_stmtContext var1);

    void enterDrop_index_stmt(Drop_index_stmtContext var1);

    void exitDrop_index_stmt(Drop_index_stmtContext var1);

    void enterDrop_table_stmt(Drop_table_stmtContext var1);

    void exitDrop_table_stmt(Drop_table_stmtContext var1);

    void enterDrop_trigger_stmt(Drop_trigger_stmtContext var1);

    void exitDrop_trigger_stmt(Drop_trigger_stmtContext var1);

    void enterDrop_view_stmt(Drop_view_stmtContext var1);

    void exitDrop_view_stmt(Drop_view_stmtContext var1);

    void enterInsert_stmt(Insert_stmtContext var1);

    void exitInsert_stmt(Insert_stmtContext var1);

    void enterUpsert_clause(Upsert_clauseContext var1);

    void exitUpsert_clause(Upsert_clauseContext var1);

    void enterPragma_stmt(Pragma_stmtContext var1);

    void exitPragma_stmt(Pragma_stmtContext var1);

    void enterReindex_stmt(Reindex_stmtContext var1);

    void exitReindex_stmt(Reindex_stmtContext var1);

    void enterRelease_stmt(Release_stmtContext var1);

    void exitRelease_stmt(Release_stmtContext var1);

    void enterRollback_stmt(Rollback_stmtContext var1);

    void exitRollback_stmt(Rollback_stmtContext var1);

    void enterSavepoint_stmt(Savepoint_stmtContext var1);

    void exitSavepoint_stmt(Savepoint_stmtContext var1);

    void enterSelect_stmt(Select_stmtContext var1);

    void exitSelect_stmt(Select_stmtContext var1);

    void enterSelect_or_values(Select_or_valuesContext var1);

    void exitSelect_or_values(Select_or_valuesContext var1);

    void enterUpdate_stmt(Update_stmtContext var1);

    void exitUpdate_stmt(Update_stmtContext var1);

    void enterUpdate_stmt_limited(Update_stmt_limitedContext var1);

    void exitUpdate_stmt_limited(Update_stmt_limitedContext var1);

    void enterVacuum_stmt(Vacuum_stmtContext var1);

    void exitVacuum_stmt(Vacuum_stmtContext var1);

    void enterColumn_def(Column_defContext var1);

    void exitColumn_def(Column_defContext var1);

    void enterType_name(Type_nameContext var1);

    void exitType_name(Type_nameContext var1);

    void enterColumn_constraint(Column_constraintContext var1);

    void exitColumn_constraint(Column_constraintContext var1);

    void enterConflict_clause(Conflict_clauseContext var1);

    void exitConflict_clause(Conflict_clauseContext var1);

    void enterExpr(ExprContext var1);

    void exitExpr(ExprContext var1);

    void enterForeign_key_clause(Foreign_key_clauseContext var1);

    void exitForeign_key_clause(Foreign_key_clauseContext var1);

    void enterRaise_function(Raise_functionContext var1);

    void exitRaise_function(Raise_functionContext var1);

    void enterIndexed_column(Indexed_columnContext var1);

    void exitIndexed_column(Indexed_columnContext var1);

    void enterTable_constraint(Table_constraintContext var1);

    void exitTable_constraint(Table_constraintContext var1);

    void enterWith_clause(With_clauseContext var1);

    void exitWith_clause(With_clauseContext var1);

    void enterCommon_table_expression(Common_table_expressionContext var1);

    void exitCommon_table_expression(Common_table_expressionContext var1);

    void enterQualified_table_name(Qualified_table_nameContext var1);

    void exitQualified_table_name(Qualified_table_nameContext var1);

    void enterOrder_clause(Order_clauseContext var1);

    void exitOrder_clause(Order_clauseContext var1);

    void enterOrdering_term(Ordering_termContext var1);

    void exitOrdering_term(Ordering_termContext var1);

    void enterLimit_clause(Limit_clauseContext var1);

    void exitLimit_clause(Limit_clauseContext var1);

    void enterPragma_value(Pragma_valueContext var1);

    void exitPragma_value(Pragma_valueContext var1);

    void enterResult_column(Result_columnContext var1);

    void exitResult_column(Result_columnContext var1);

    void enterTable_or_subquery(Table_or_subqueryContext var1);

    void exitTable_or_subquery(Table_or_subqueryContext var1);

    void enterJoin_clause(Join_clauseContext var1);

    void exitJoin_clause(Join_clauseContext var1);

    void enterJoin_operator(Join_operatorContext var1);

    void exitJoin_operator(Join_operatorContext var1);

    void enterJoin_constraint(Join_constraintContext var1);

    void exitJoin_constraint(Join_constraintContext var1);

    void enterCompound_operator(Compound_operatorContext var1);

    void exitCompound_operator(Compound_operatorContext var1);

    void enterSigned_number(Signed_numberContext var1);

    void exitSigned_number(Signed_numberContext var1);

    void enterLiteral_value(Literal_valueContext var1);

    void exitLiteral_value(Literal_valueContext var1);

    void enterBoolean_literal(Boolean_literalContext var1);

    void exitBoolean_literal(Boolean_literalContext var1);

    void enterUnary_operator(Unary_operatorContext var1);

    void exitUnary_operator(Unary_operatorContext var1);

    void enterBinary_operator(Binary_operatorContext var1);

    void exitBinary_operator(Binary_operatorContext var1);

    void enterError_message(Error_messageContext var1);

    void exitError_message(Error_messageContext var1);

    void enterModule_argument(Module_argumentContext var1);

    void exitModule_argument(Module_argumentContext var1);

    void enterColumn_alias(Column_aliasContext var1);

    void exitColumn_alias(Column_aliasContext var1);

    void enterColumn_name_list(Column_name_listContext var1);

    void exitColumn_name_list(Column_name_listContext var1);

    void enterKeyword(KeywordContext var1);

    void exitKeyword(KeywordContext var1);

    void enterName(NameContext var1);

    void exitName(NameContext var1);

    void enterFunction_name(Function_nameContext var1);

    void exitFunction_name(Function_nameContext var1);

    void enterSchema_name(Schema_nameContext var1);

    void exitSchema_name(Schema_nameContext var1);

    void enterTable_function(Table_functionContext var1);

    void exitTable_function(Table_functionContext var1);

    void enterTable_name(Table_nameContext var1);

    void exitTable_name(Table_nameContext var1);

    void enterTable_or_index_name(Table_or_index_nameContext var1);

    void exitTable_or_index_name(Table_or_index_nameContext var1);

    void enterNew_table_name(New_table_nameContext var1);

    void exitNew_table_name(New_table_nameContext var1);

    void enterColumn_name(Column_nameContext var1);

    void exitColumn_name(Column_nameContext var1);

    void enterCollation_name(Collation_nameContext var1);

    void exitCollation_name(Collation_nameContext var1);

    void enterForeign_table(Foreign_tableContext var1);

    void exitForeign_table(Foreign_tableContext var1);

    void enterIndex_name(Index_nameContext var1);

    void exitIndex_name(Index_nameContext var1);

    void enterTrigger_name(Trigger_nameContext var1);

    void exitTrigger_name(Trigger_nameContext var1);

    void enterView_name(View_nameContext var1);

    void exitView_name(View_nameContext var1);

    void enterModule_name(Module_nameContext var1);

    void exitModule_name(Module_nameContext var1);

    void enterPragma_name(Pragma_nameContext var1);

    void exitPragma_name(Pragma_nameContext var1);

    void enterSavepoint_name(Savepoint_nameContext var1);

    void exitSavepoint_name(Savepoint_nameContext var1);

    void enterTable_alias(Table_aliasContext var1);

    void exitTable_alias(Table_aliasContext var1);

    void enterTransaction_name(Transaction_nameContext var1);

    void exitTransaction_name(Transaction_nameContext var1);

    void enterAny_name(Any_nameContext var1);

    void exitAny_name(Any_nameContext var1);
}
