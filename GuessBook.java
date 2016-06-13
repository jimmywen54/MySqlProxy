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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzm on 2016/6/8.
 * 实现一个留言本的增加和显示,如果留言超过5个，自动删除最老的,显示返回HTML，每个消息一行，偶数行背景色是灰色
 */
public class GuessBook {
    public static List<String> messages = new ArrayList<String>();
    public static int SEQ = 0;
   /* private static class SubGuestBook {
        private static final GuessBook subGuessBook = new GuessBook();
    }

    public GuessBook getGuessBookInstance() {
        return SubGuestBook.subGuessBook;
    }
*/
    public synchronized void add()
    {
        //TODO
        int size = messages.size();
        if  (size >= 5) {
            for (int i=size-1; i>=4; i--) {
                messages.remove(4-i);
            }
        }
        GuessBook.SEQ++;
        messages.add(Thread.currentThread().getName() + "_" + GuessBook.SEQ+"");
        for (int i=0; i<messages.size(); i++) {
            System.out.print(messages.get(i) + ",");
        }
        System.out.println();

    }

    public String show(){
        return "";
    }

    public static void main(String[] args) {
        GuessBook book = new GuessBook();
        for (int i=0; i<10; i++) {
            book.add();
        }
    }

}
