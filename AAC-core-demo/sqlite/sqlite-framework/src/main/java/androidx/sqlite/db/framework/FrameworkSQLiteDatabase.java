/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.sqlite.db.framework;

import static android.text.TextUtils.isEmpty;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteTransactionListener;
import android.os.Build;
import android.os.CancellationSignal;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteCompat;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Delegates all calls to an implementation of {@link SQLiteDatabase}.
 */
@SuppressWarnings("unused")
class FrameworkSQLiteDatabase implements SupportSQLiteDatabase {
    private static final String[] CONFLICT_VALUES = new String[]
            {"", " OR ROLLBACK ", " OR ABORT ", " OR FAIL ", " OR IGNORE ", " OR REPLACE "};
    private static final String[] EMPTY_STRING_ARRAY = new String[0];

    private final SQLiteDatabase mDelegate;

    /**
     * Creates a wrapper around {@link SQLiteDatabase}.
     *
     * @param delegate The delegate to receive all calls.
     */
    FrameworkSQLiteDatabase(SQLiteDatabase delegate) {
        mDelegate = delegate;
    }

    @Override
    public SupportSQLiteStatement compileStatement(String sql) {
        //compileStatement方法获取SQLiteStatement对象并重用，而不是让系统每次insert都构造一个对应的SQLiteStatement对象，这样能够提高内存的使用率。
        return new FrameworkSQLiteStatement(mDelegate.compileStatement(sql));
    }

    @Override
    public void beginTransaction() {
        mDelegate.beginTransaction();
    }

    @Override
    public void beginTransactionNonExclusive() {
        mDelegate.beginTransactionNonExclusive();
    }

    @Override
    public void beginTransactionWithListener(SQLiteTransactionListener transactionListener) {
        mDelegate.beginTransactionWithListener(transactionListener);
    }

    @Override
    public void beginTransactionWithListenerNonExclusive(
            SQLiteTransactionListener transactionListener) {
        mDelegate.beginTransactionWithListenerNonExclusive(transactionListener);
    }

    @Override
    public void endTransaction() {
        mDelegate.endTransaction();
    }

    @Override
    public void setTransactionSuccessful() {
        mDelegate.setTransactionSuccessful();
    }

    @Override
    public boolean inTransaction() {
        return mDelegate.inTransaction();
    }

    @Override
    public boolean isDbLockedByCurrentThread() {
        return mDelegate.isDbLockedByCurrentThread();
    }

    @Override
    public boolean yieldIfContendedSafely() {
        return mDelegate.yieldIfContendedSafely();
    }

    @Override
    public boolean yieldIfContendedSafely(long sleepAfterYieldDelay) {
        return mDelegate.yieldIfContendedSafely(sleepAfterYieldDelay);
    }

    @Override
    public boolean isExecPerConnectionSQLSupported() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.R;
    }

    // Adding @RequiresApi(30) would prevent unbundled implementations from offering this
    // functionality to lower API levels.
    @SuppressWarnings("ClassVerificationFailure")
    @Override
    public void execPerConnectionSQL(@NonNull String sql, @Nullable Object[] bindArgs) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            mDelegate.execPerConnectionSQL(sql, bindArgs);
        } else {
            throw new UnsupportedOperationException("execPerConnectionSQL is not supported on a "
                    + "SDK version lower than 30, current version is: " + Build.VERSION.SDK_INT);
        }
    }

    @Override
    public int getVersion() {
        return mDelegate.getVersion();
    }

    @Override
    public void setVersion(int version) {
        mDelegate.setVersion(version);
    }

    @Override
    public long getMaximumSize() {
        return mDelegate.getMaximumSize();
    }

    @Override
    public long setMaximumSize(long numBytes) {
        return mDelegate.setMaximumSize(numBytes);
    }

    @Override
    public long getPageSize() {
        return mDelegate.getPageSize();
    }

    @Override
    public void setPageSize(long numBytes) {
        mDelegate.setPageSize(numBytes);
    }

    @Override
    public Cursor query(String query) {
        return query(new SimpleSQLiteQuery(query));
    }

    @Override
    public Cursor query(String query, Object[] bindArgs) {
        return query(new SimpleSQLiteQuery(query, bindArgs));
    }


    @Override
    public Cursor query(final SupportSQLiteQuery supportQuery) {
        //不使用rawQuery方法，而是用rawQueryWithFactory的目的在于CursorFactory中调用supportQulite的bindTo方法
        return mDelegate.rawQueryWithFactory(new SQLiteDatabase.CursorFactory() {
            @Override
            public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver masterQuery,
                    String editTable, SQLiteQuery query) {
                supportQuery.bindTo(new FrameworkSQLiteProgram(query));
                return new SQLiteCursor(masterQuery, editTable, query);
            }
        }, supportQuery.getSql(), EMPTY_STRING_ARRAY, null);
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public Cursor query(final SupportSQLiteQuery supportQuery,
            CancellationSignal cancellationSignal) {
        return SupportSQLiteCompat.Api16Impl.rawQueryWithFactory(mDelegate, supportQuery.getSql(),
                EMPTY_STRING_ARRAY, null, cancellationSignal, new SQLiteDatabase.CursorFactory() {
                    @Override
                    public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver masterQuery,
                            String editTable, SQLiteQuery query) {
                        supportQuery.bindTo(new FrameworkSQLiteProgram(query));
                        return new SQLiteCursor(masterQuery, editTable, query);
                    }
                });
    }

    @Override
    public long insert(String table, int conflictAlgorithm, ContentValues values)
            throws SQLException {
        return mDelegate.insertWithOnConflict(table, null, values,
                conflictAlgorithm);
    }

    @Override
    public int delete(String table, String whereClause, Object[] whereArgs) {
        String query = "DELETE FROM " + table
                + (isEmpty(whereClause) ? "" : " WHERE " + whereClause);
        SupportSQLiteStatement statement = compileStatement(query);
        SimpleSQLiteQuery.bind(statement, whereArgs);
        return statement.executeUpdateDelete();
    }


    @Override
    public int update(String table, int conflictAlgorithm, ContentValues values, String whereClause,
            Object[] whereArgs) {
        // taken from SQLiteDatabase class.
        if (values == null || values.size() == 0) {
            throw new IllegalArgumentException("Empty values");
        }
        StringBuilder sql = new StringBuilder(120);
        sql.append("UPDATE ");
        sql.append(CONFLICT_VALUES[conflictAlgorithm]);
        sql.append(table);
        sql.append(" SET ");

        // move all bind args to one array
        int setValuesSize = values.size();
        int bindArgsSize = (whereArgs == null) ? setValuesSize : (setValuesSize + whereArgs.length);
        Object[] bindArgs = new Object[bindArgsSize];
        int i = 0;
        for (String colName : values.keySet()) {
            sql.append((i > 0) ? "," : "");
            sql.append(colName);
            bindArgs[i++] = values.get(colName);
            sql.append("=?");
        }
        if (whereArgs != null) {
            for (i = setValuesSize; i < bindArgsSize; i++) {
                bindArgs[i] = whereArgs[i - setValuesSize];
            }
        }
        if (!isEmpty(whereClause)) {
            sql.append(" WHERE ");
            sql.append(whereClause);
        }
        SupportSQLiteStatement stmt = compileStatement(sql.toString());
        SimpleSQLiteQuery.bind(stmt, bindArgs);
        return stmt.executeUpdateDelete();
    }

    @Override
    public void execSQL(String sql) throws SQLException {
        mDelegate.execSQL(sql);
    }

    @Override
    public void execSQL(String sql, Object[] bindArgs) throws SQLException {
        mDelegate.execSQL(sql, bindArgs);
    }

    @Override
    public boolean isReadOnly() {
        return mDelegate.isReadOnly();
    }

    @Override
    public boolean isOpen() {
        return mDelegate.isOpen();
    }

    @Override
    public boolean needUpgrade(int newVersion) {
        return mDelegate.needUpgrade(newVersion);
    }

    @Override
    public String getPath() {
        return mDelegate.getPath();
    }

    @Override
    public void setLocale(Locale locale) {
        mDelegate.setLocale(locale);
    }

    @Override
    public void setMaxSqlCacheSize(int cacheSize) {
        mDelegate.setMaxSqlCacheSize(cacheSize);
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setForeignKeyConstraintsEnabled(boolean enable) {
        SupportSQLiteCompat.Api16Impl.setForeignKeyConstraintsEnabled(mDelegate, enable);
    }

    @Override
    public boolean enableWriteAheadLogging() {
        return mDelegate.enableWriteAheadLogging();
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void disableWriteAheadLogging() {
        SupportSQLiteCompat.Api16Impl.disableWriteAheadLogging(mDelegate);
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public boolean isWriteAheadLoggingEnabled() {
        return SupportSQLiteCompat.Api16Impl.isWriteAheadLoggingEnabled(mDelegate);
    }

    @Override
    public List<Pair<String, String>> getAttachedDbs() {
        return mDelegate.getAttachedDbs();
    }

    @Override
    public boolean isDatabaseIntegrityOk() {
        return mDelegate.isDatabaseIntegrityOk();
    }

    @Override
    public void close() throws IOException {
        mDelegate.close();
    }

    /**
     * Checks if this object delegates to the same given database reference.
     */
    boolean isDelegate(SQLiteDatabase sqLiteDatabase) {
        return mDelegate == sqLiteDatabase;
    }
}
