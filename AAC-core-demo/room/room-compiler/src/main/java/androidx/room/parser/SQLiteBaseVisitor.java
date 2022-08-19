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
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

public class SQLiteBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements SQLiteVisitor<T> {
    public SQLiteBaseVisitor() {
    }

    public T visitParse(ParseContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitError(ErrorContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitSql_stmt_list(Sql_stmt_listContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitSql_stmt(Sql_stmtContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitAlter_table_stmt(Alter_table_stmtContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitAnalyze_stmt(Analyze_stmtContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitAttach_stmt(Attach_stmtContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitBegin_stmt(Begin_stmtContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitCommit_stmt(Commit_stmtContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitCreate_index_stmt(Create_index_stmtContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitCreate_table_stmt(Create_table_stmtContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitCreate_trigger_stmt(Create_trigger_stmtContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitCreate_view_stmt(Create_view_stmtContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitCreate_virtual_table_stmt(Create_virtual_table_stmtContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitDelete_stmt(Delete_stmtContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitDelete_stmt_limited(Delete_stmt_limitedContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitDetach_stmt(Detach_stmtContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitDrop_index_stmt(Drop_index_stmtContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitDrop_table_stmt(Drop_table_stmtContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitDrop_trigger_stmt(Drop_trigger_stmtContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitDrop_view_stmt(Drop_view_stmtContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitInsert_stmt(Insert_stmtContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitUpsert_clause(Upsert_clauseContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitPragma_stmt(Pragma_stmtContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitReindex_stmt(Reindex_stmtContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitRelease_stmt(Release_stmtContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitRollback_stmt(Rollback_stmtContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitSavepoint_stmt(Savepoint_stmtContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitSelect_stmt(Select_stmtContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitSelect_or_values(Select_or_valuesContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitUpdate_stmt(Update_stmtContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitUpdate_stmt_limited(Update_stmt_limitedContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitVacuum_stmt(Vacuum_stmtContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitColumn_def(Column_defContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitType_name(Type_nameContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitColumn_constraint(Column_constraintContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitConflict_clause(Conflict_clauseContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitExpr(ExprContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitForeign_key_clause(Foreign_key_clauseContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitRaise_function(Raise_functionContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitIndexed_column(Indexed_columnContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitTable_constraint(Table_constraintContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitWith_clause(With_clauseContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitCommon_table_expression(Common_table_expressionContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitQualified_table_name(Qualified_table_nameContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitOrder_clause(Order_clauseContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitOrdering_term(Ordering_termContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitLimit_clause(Limit_clauseContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitPragma_value(Pragma_valueContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitResult_column(Result_columnContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitTable_or_subquery(Table_or_subqueryContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitJoin_clause(Join_clauseContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitJoin_operator(Join_operatorContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitJoin_constraint(Join_constraintContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitCompound_operator(Compound_operatorContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitSigned_number(Signed_numberContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitLiteral_value(Literal_valueContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitBoolean_literal(Boolean_literalContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitUnary_operator(Unary_operatorContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitBinary_operator(Binary_operatorContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitError_message(Error_messageContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitModule_argument(Module_argumentContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitColumn_alias(Column_aliasContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitColumn_name_list(Column_name_listContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitKeyword(KeywordContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitName(NameContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitFunction_name(Function_nameContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitSchema_name(Schema_nameContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitTable_function(Table_functionContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitTable_name(Table_nameContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitTable_or_index_name(Table_or_index_nameContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitNew_table_name(New_table_nameContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitColumn_name(Column_nameContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitCollation_name(Collation_nameContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitForeign_table(Foreign_tableContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitIndex_name(Index_nameContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitTrigger_name(Trigger_nameContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitView_name(View_nameContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitModule_name(Module_nameContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitPragma_name(Pragma_nameContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitSavepoint_name(Savepoint_nameContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitTable_alias(Table_aliasContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitTransaction_name(Transaction_nameContext ctx) {
        return this.visitChildren(ctx);
    }

    public T visitAny_name(Any_nameContext ctx) {
        return this.visitChildren(ctx);
    }
}
