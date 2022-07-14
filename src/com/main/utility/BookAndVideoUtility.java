package com.main.utility;

import com.entityClasses.book;
import com.entityClasses.video;

import java.util.List;

public class BookAndVideoUtility {
    public int validateCallNumber(List<book> bookList , List<video> videoList , double callNum) {
        int val = -1;

        for(book b: bookList ) {
            if(b.getCallNumber() == callNum) {
                val = 0;
                break;
            }

        }
        for(video v: videoList) {
            if(v.getCallNumber() == callNum) {
                val = 1;
                break;
            }

        }
        return val;
    }
}
