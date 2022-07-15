package com.main.utility;

import com.entityClasses.book;
import com.entityClasses.video;
import com.main.db.DB;
import java.util.List;

public class BookAndVideoUtility {
	DB db = new DB();
    public int validateCallNumber(List<book> bookList , List<video> videoList , double callNum) {
    	/* Cases:
        call number invalid: -1
        call number valid and is available: 1 for book, 2 for video
        call number valid but is not available: 3
     */

    int val;

    if(bookExists(callNum) || videoExists(callNum)){

        val = 3; //callNumber is valid but is not available

        for(book b: bookList ) {
            if(b.getCallNumber() == callNum) {
                val = 0; // callNumber is valid and book is available
                break;
            }

        }
        for(video v: videoList) {
            if(v.getCallNumber() == callNum) {
                val = 1; //callNumber is valid and video is available
                break;
            }
        }

    }
    else{
        val = -1; // Call number is invalid
    }


    return val;
}

private boolean bookExists(double callNum) {
    boolean val = false;
    for (book b : db.showBooks()) {
        if(b.getCallNumber() == callNum){
            val = true;
            break;
        }
    }
    return val;
}

private boolean videoExists(double callNum) {
    boolean val = false;
    for (video v : db.showVideos()) {
        if(v.getCallNumber() == callNum){
            val = true;
            break;
            }

        }
        return val;
    }
}
