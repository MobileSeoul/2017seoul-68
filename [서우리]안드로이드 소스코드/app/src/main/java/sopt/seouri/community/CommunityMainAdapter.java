package sopt.seouri.community;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by yangseunghyuk on 2017-09-27.
 */

public class CommunityMainAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    Context context;

    public CommunityMainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // 구 게시판
                GuBulletinFragment guFragment = new GuBulletinFragment();
                return guFragment;
            case 1:  // 시 게시판
                 SeoulsiBulletinFragment seoulsiBulletinFragment = new SeoulsiBulletinFragment();
                return seoulsiBulletinFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public int getItemPosition(Object object) {
        if (false) {
            return POSITION_NONE;
        } else {
            return super.getItemPosition(object);
        }
    }
}
