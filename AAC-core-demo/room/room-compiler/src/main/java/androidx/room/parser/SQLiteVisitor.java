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
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

public interface SQLiteVisitor<T> extends ParseTreeVisitor<T> {
    T visitParse(ParseContext var1);

    T visitError(ErrorContext var1);

    T visitSql_stmt_list(Sql_stmt_listContext var1);

    T visitSql_stmt(Sql_stmtContext var1);

    T visitAlter_table_stmt(Alter_table_stmtContext var1);

    T visitAnalyze_stmt(Analyze_stmtContext var1);

    T visitAttach_stmt(Attach_stmtContext var1);

    T visitBegin_stmt(Begin_stmtContext var1);

    T visitCommit_stmt(Commit_stmtContext var1);

    T visitCreate_index_stmt(Create_index_stmtContext var1);

    T visitCreate_table_stmt(Create_table_stmtContext var1);

    T visitCreate_trigger_stmt(Create_trigger_stmtContext var1);

    T visitCreate_view_stmt(Create_view_stmtContext var1);

    T visitCreate_virtual_table_stmt(Create_virtual_table_stmtContext var1);

    T visitDelete_stmt(Delete_stmtContext var1);

    T visitDelete_stmt_limited(Delete_stmt_limitedContext var1);

    T visitDetach_stmt(Detach_stmtContext var1);

    T visitDrop_index_stmt(Drop_index_stmtContext var1);

    T visitDrop_table_stmt(Drop_table_stmtContext var1);

    T visitDrop_trigger_stmt(Drop_trigger_stmtContext var1);

    T visitDrop_view_stmt(Drop_view_stmtContext var1);

    T visitInsert_stmt(Insert_stmtContext var1);

    T visitUpsert_clause(Upsert_clauseContext var1);

    T visitPragma_stmt(Pragma_stmtContext var1);

    T visitReindex_stmt(Reindex_stmtContext var1);

    T visitRelease_stmt(Release_stmtContext var1);

    T visitRollback_stmt(Rollback_stmtContext var1);

    T visitSavepoint_stmt(Savepoint_stmtContext var1);

    T visitSelect_stmt(Select_stmtContext var1);

    T visitSelect_or_values(Select_or_valuesContext var1);

    T visitUpdate_stmt(Update_stmtContext var1);

    T visitUpdate_stmt_limited(Update_stmt_limitedContext var1);

    T visitVacuum_stmt(Vacuum_stmtContext var1);

    T visitColumn_def(Column_defContext var1);

    T visitType_name(Type_nameContext var1);

    T visitColumn_constraint(Column_constraintContext var1);

    T visitConflict_clause(Conflict_clauseContext var1);

    T visitExpr(ExprContext var1);

    T visitForeign_key_clause(Foreign_key_clauseContext var1);

    T visitRaise_function(Raise_functionContext var1);

    T visitIndexed_column(Indexed_columnContext var1);

    T visitTable_constraint(Table_constraintContext var1);

    T visitWith_clause(With_clauseContext var1);

    T visitCommon_table_expression(Common_table_expressionContext var1);

    T visitQualified_table_name(Qualified_table_nameContext var1);

    T visitOrder_clause(Order_clauseContext var1);

    T visitOrdering_term(Ordering_termContext var1);

    T visitLimit_clause(Limit_clauseContext var1);

    T visitPragma_value(Pragma_valueContext var1);

    T visitResult_column(Result_columnContext var1);

    T visitTable_or_subquery(Table_or_subqueryContext var1);

    T visitJoin_clause(Join_clauseContext var1);

    T visitJoin_operator(Join_operatorContext var1);

    T visitJoin_constraint(Join_constraintContext var1);

    T visitCompound_operator(Compound_operatorContext var1);

    T visitSigned_number(Signed_numberContext var1);

    T visitLiteral_value(Literal_valueContext var1);

    T visitBoolean_literal(Boolean_literalContext var1);

    T visitUnary_operator(Unary_operatorContext var1);

    T visitBinary_operator(Binary_operatorContext var1);

    T visitError_message(Error_messageContext var1);

    T visitModule_argument(Module_argumentContext var1);

    T visitColumn_alias(Column_aliasContext var1);

    T visitColumn_name_list(Column_name_listContext var1);

    T visitKeyword(KeywordContext var1);

    T visitName(NameContext var1);

    T visitFunction_name(Function_nameContext var1);

    T visitSchema_name(Schema_nameContext var1);

    T visitTable_function(Table_functionContext var1);

    T visitTable_name(Table_nameContext var1);

    T visitTable_or_index_name(Table_or_index_nameContext var1);

    T visitNew_table_name(New_table_nameContext var1);

    T visitColumn_name(Column_nameContext var1);

    T visitCollation_name(Collation_nameContext var1);

    T visitForeign_table(Foreign_tableContext var1);

    T visitIndex_name(Index_nameContext var1);

    T visitTrigger_name(Trigger_nameContext var1);

    T visitView_name(View_nameContext var1);

    T visitModule_name(Module_nameContext var1);

    T visitPragma_name(Pragma_nameContext var1);

    T visitSavepoint_name(Savepoint_nameContext var1);

    T visitTable_alias(Table_aliasContext var1);

    T visitTransaction_name(Transaction_nameContext var1);

    T visitAny_name(Any_nameContext var1);
}
