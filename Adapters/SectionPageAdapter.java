package com.zaeem.mychat.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.BaseAdapter;

import com.zaeem.mychat.Fragments.ChatsFragment;
import com.zaeem.mychat.Fragments.FriendsFragment;
import com.zaeem.mychat.Fragments.RequestsFragment;

/**
 * Created by zaeem on 7/5/2017.
 */

public class SectionPageAdapter  extends FragmentPagerAdapter{

    public SectionPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                RequestsFragment requestsFragment = new RequestsFragment();
                return requestsFragment;
            case 1:
                ChatsFragment chatsFragment = new ChatsFragment();
                return chatsFragment;
            case 2:
                FriendsFragment friendsFragment = new FriendsFragment();
                return friendsFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return "REQUESTS";
            case 1:
                return "FRIENDS";
            case 2:
                return "CHATS";
            default:
                return null;
        }
    }
}
