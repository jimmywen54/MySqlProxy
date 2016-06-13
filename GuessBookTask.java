/*
 * Copyright (c) 2015 yy.com. 
 *
 * All Rights Reserved.
 *
 * This program is the confidential and proprietary information of 
 * YY.INC. ("Confidential Information").  You shall not disclose such
 * Confidential Information and shall use it only in accordance with
 * the terms of the license agreement you entered into with yy.com.
 */
package vicody.pool.db;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wzm on 2016/6/8.
 */
public class GuessBookTask implements Runnable {
    GuessBook book;

    public GuessBook getBook() {
        return book;
    }

    public void setBook(GuessBook book) {
        this.book = book;
    }

    public GuessBookTask(GuessBook book) {
        this.book = book;
    }

    @Override
    public void run() {
        for (int i=0; i<5; i++) {
            book.add();
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        GuessBook book1 = new GuessBook();
        for (int i=0; i<10; i++) {
            service.execute(new GuessBookTask(book1));
        }
    }
}
