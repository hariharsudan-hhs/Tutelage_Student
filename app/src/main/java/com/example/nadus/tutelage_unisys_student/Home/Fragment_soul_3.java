package com.example.nadus.tutelage_unisys_student.Home;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nadus.tutelage_unisys_student.Adapters.FileAdapter;
import com.example.nadus.tutelage_unisys_student.R;
import com.hlab.fabrevealmenu.listeners.OnFABMenuSelectedListener;
import com.hlab.fabrevealmenu.model.FABMenuItem;
import com.hlab.fabrevealmenu.view.FABRevealMenu;

import java.io.File;
import java.util.ArrayList;

import me.anwarshahriar.calligrapher.Calligrapher;
import me.gujun.android.taggroup.TagGroup;

import static android.app.Activity.RESULT_OK;

/**
 * Created by nadus on 21-12-2017.
 */

public class Fragment_soul_3 extends BaseFragment implements OnFABMenuSelectedListener
{
    FloatingActionButton fab;
    Calligrapher calligrapher;
    TextView empty_text;
    RecyclerView recyclerView;
    ArrayList<String> list = new ArrayList<String>();

    ArrayList<FileAdapter> fileAdapters=new ArrayList<>();
    ItemAdapter3 itemAdapter3;

    FloatingActionButton field;
    RecyclerView indivRecycler;

    private static final int PICK_IMAGE = 1;
    private static final int PICK_Camera_IMAGE = 2;
    public static final int REQUEST_PICK_VIDEO = 4;
    private static final int VIDEO_CAPTURE = 101;
    private static final int PICK_FILE = 5;
    Uri audioUri;
    BottomSheetBehavior bottomSheetBehavior, bottomSheetBehavior2, bottomSheetBehavior3, bottomSheetBehavior4, bottomSheetBehavior5;

    //BottomSheetDialog bottomSheetDialog, bottomSheetDialog2, bottomSheetDialog3, bottomSheetDialog4, bottomSheetDialog5;


    protected static final int REQUEST_PICK_AUDIO = 6;
    public static final int ACTIVITY_RECORD_SOUND = 7;
    CharSequence[] items1 = {"Take Photo", "Choose from library", "Cancel"};
    private ArrayList<FABMenuItem> items;
    CharSequence[] itemVi={"Take Video","Choose from library","Cancel"};

    Uri imageData,imageUri,yourUri;
    Uri VideoUri;
    String descr,path;
    public Bitmap bitmap;
    public String picturePath;
    BottomSheetDialog bottomSheetDialog;
    View bottomSheet,bottomSheet2,bottomSheet3,bottomSheet4,bottomSheet5;

    public static Fragment_soul_3 newInstance() {
        Fragment_soul_3 fragment = new Fragment_soul_3();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_soul_dhamo, container, false);

        calligrapher = new Calligrapher(getActivity());
        calligrapher.setFont(getActivity(),"GlacialIndifference-Regular.ttf",true);
//
//        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
//        empty_text = (TextView) v.findViewById(R.id.empty_text);
//
//        fab = (FloatingActionButton) v.findViewById(R.id.fab);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            }
//        });
//
//        if(list.isEmpty())
//        {
//            recyclerView.setVisibility(View.GONE);
//            empty_text.setVisibility(View.VISIBLE);
//        }
//        else
//        {
//            recyclerView.setVisibility(View.VISIBLE);
//            empty_text.setVisibility(View.GONE);
//        }

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        field=(FloatingActionButton)view.findViewById(R.id.field);
        indivRecycler=(RecyclerView)view.findViewById(R.id.recyclerindiv);
        indivRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        bottomSheet=view.findViewById(R.id.b1);
        bottomSheet2=view.findViewById(R.id.b2);
        bottomSheet3=view.findViewById(R.id.b3);
        bottomSheet4=view.findViewById(R.id.b4);
        bottomSheet5=view.findViewById(R.id.b5);
        bottomSheetBehavior=BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior2 = BottomSheetBehavior.from(bottomSheet2);
        bottomSheetBehavior3=BottomSheetBehavior.from(bottomSheet3);
        bottomSheetBehavior4=BottomSheetBehavior.from(bottomSheet4);
        bottomSheetBehavior5=BottomSheetBehavior.from(bottomSheet5);

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        bottomSheetBehavior2.setState(BottomSheetBehavior.STATE_HIDDEN);
        bottomSheetBehavior3.setState(BottomSheetBehavior.STATE_HIDDEN);
        bottomSheetBehavior4.setState(BottomSheetBehavior.STATE_HIDDEN);
        bottomSheetBehavior5.setState(BottomSheetBehavior.STATE_HIDDEN);

        fileAdapters.add(new FileAdapter("Dummy",new String[]{"attr1","attr2"}));
        int i=6;
        while(i>0)
        {
            fileAdapters.add(new FileAdapter("FileName",new String[]{"Description","Type","Path","Size","Uploaded On"}));
            i--;
        }

        itemAdapter3=new ItemAdapter3(R.layout.classlistcard,fileAdapters);
        indivRecycler.setAdapter(itemAdapter3);
        final FABRevealMenu fabMenu = view.findViewById(R.id.fabMenu);
        //initItems(false);

        try
        {
            if (field != null && fabMenu != null)
            {
                //attach menu to fab
                setFabMenu(fabMenu);
                //set menu items from arrylist
                //fabMenu.setMenuItems(items);
                //attach menu to fab
                fabMenu.bindAnchorView(field);
                //set menu item selection
                fabMenu.setShowOverlay(true);
                fabMenu.enableItemAnimation(true);
                fabMenu.setOnFABMenuSelectedListener(this);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void initItems(boolean toShowDoubleItems)
    {
        items = new ArrayList<>();
        items.add(new FABMenuItem("Attachments", AppCompatResources.getDrawable(getActivity(), R.drawable.attachments_insert)));
        items.add(new FABMenuItem("Images", AppCompatResources.getDrawable(getActivity(), R.drawable.photo_insert)));
        items.add(new FABMenuItem("Video", AppCompatResources.getDrawable(getActivity(), R.drawable.video_insert)));
        items.add(new FABMenuItem("Text", AppCompatResources.getDrawable(getActivity(), R.drawable.text_insert)));
        items.add(new FABMenuItem("Audio",AppCompatResources.getDrawable(getActivity(),R.drawable.music_insert)));
    }

    @Override
    public void onMenuItemSelected(View view, int id)
    {
        switch (id) {
            case R.id.menu_file:
                selectFiles();
                break;
            case R.id.menu_image:
                selectImage();
                break;
            case R.id.menu_video:
                selectVideo();
                break;
            case R.id.menu_text:
                selectText();
                break;
            case R.id.menu_audio:
                selectAudio();
                break;
        }


    }

    private void selectText() {

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

    }

    private class ItemAdapter3 extends RecyclerView.Adapter<ViewHolder>
    {
        private int listItemLayout;
        private ArrayList<FileAdapter> list1;
        public ItemAdapter3(int listlayout, ArrayList<FileAdapter> tl)
        {
            this.listItemLayout=listlayout;
            this.list1=tl;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(listItemLayout, parent, false);
            final ViewHolder myViewHolder = new ViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position)
        {
            holder.mTagGroup.setTags(list1.get(position).getG());
            Typeface typeFace = Typeface.createFromAsset(getResources().getAssets(), "GlacialIndifference-Regular.ttf");
            holder.textView.setTypeface(typeFace);
        }

        @Override
        public int getItemCount()
        {
            return list1==null?0:list1.size();
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder
    {

        TagGroup mTagGroup;
        TextView textView;
        public ViewHolder(View view)
        {
            super(view);
            mTagGroup = (TagGroup) view.findViewById(R.id.tag_group);
            textView=(TextView)view.findViewById(R.id.Filename);
        }

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {


        if (requestCode == PICK_IMAGE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(getActivity(), ""+imageUri+"data="+data.getData(), Toast.LENGTH_SHORT).show();
                bottomSheetBehavior2.setState(BottomSheetBehavior.STATE_EXPANDED);

            }
        }
        if (requestCode == PICK_Camera_IMAGE) {
            if (resultCode == RESULT_OK) {
                try {
                    bottomSheetBehavior2.setState(BottomSheetBehavior.STATE_EXPANDED);
                    Toast.makeText(getActivity(), ""+imageUri, Toast.LENGTH_SHORT).show();


                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

        if (requestCode == PICK_FILE)
        {

            bottomSheetBehavior5.setState(BottomSheetBehavior.STATE_EXPANDED);
        }

        if (requestCode == VIDEO_CAPTURE)
        {
            if (resultCode == getActivity().RESULT_OK)
            {
                if(path!=null) {
                    Toast.makeText(getContext(), "Video has been saved to:\n" +
                            path, Toast.LENGTH_LONG).show();
                    bottomSheetBehavior4.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            } else if (resultCode == getActivity().RESULT_CANCELED) {
                Toast.makeText(getContext(), "Video recording cancelled.",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), "Failed to record video",
                        Toast.LENGTH_LONG).show();
            }
        }
        if (requestCode == ACTIVITY_RECORD_SOUND)
        {
            if(resultCode==getActivity().RESULT_OK)
            {
                //audioUri = data.getData();
                bottomSheetBehavior3.setState(BottomSheetBehavior.STATE_EXPANDED);
            }

        }
        if(requestCode==ACTIVITY_RECORD_SOUND)
        {
            if(resultCode==getActivity().RESULT_OK)
            {
                Toast.makeText(getActivity(), "Audio Uri"+audioUri, Toast.LENGTH_SHORT).show();
            }
        }

        //  String path = audioUri.getPath(); // "file:///mnt/sdcard/FileName.mp3"


        //System.out.println("Path is " + audioUri + " d " + obj.getPaths(Timeline.this, audioUri));



        try
        {
            if (requestCode == REQUEST_PICK_VIDEO)
            {


                if (data.equals(null))
                {
                    Toast.makeText(getContext(), "Video was not selected", Toast.LENGTH_SHORT).show();
                } else {
                    VideoUri = data.getData();
                    Toast.makeText(getContext(), VideoUri.toString(), Toast.LENGTH_SHORT).show();

                    System.out.println("VIDEOO" + VideoUri + " d " + VideoUri.getPath());
                    bottomSheetBehavior4.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
            if (requestCode == REQUEST_PICK_AUDIO) {
                bottomSheetBehavior3.setState(BottomSheetBehavior.STATE_EXPANDED);
                //audioUri = data.getData();
            }



        }
        catch(Exception e)
        {

        }
    }
    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose picture..");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    String fileName = "new-photo-name.jpg";
                    //create parameters for Intent with filename
                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Images.Media.TITLE, fileName);
                    values.put(MediaStore.Images.Media.DESCRIPTION, "Image capture by camera");
                    //imageUri is the current activity attribute, define and save it for later usage (also in onSaveInstanceState)
                    imageUri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                    //create new Intent
                   // Toast.makeText(getActivity(), "Image Uri="+imageUri, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                    startActivityForResult(intent, PICK_Camera_IMAGE);
                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            PICK_IMAGE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();


    }
    private void selectVideo() {
        final CharSequence[] items = {"Take Video", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose video..");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Video")) {
                    File mediaFile = new
                            File(Environment.getExternalStorageDirectory().getAbsolutePath()
                            + "/myvideo"+".mp4");
                    Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                    Toast.makeText(getContext(), "Take Video", Toast.LENGTH_SHORT).show();
                    VideoUri = FileProvider.getUriForFile(getActivity(), getActivity().getPackageName() + ".provider", mediaFile);

                    path=VideoUri.toString();

                    intent.putExtra(MediaStore.EXTRA_OUTPUT, VideoUri);
                    startActivityForResult(intent, VIDEO_CAPTURE);


                } else if (items[item].equals("Choose from Library"))
                {
                    Intent gintent = new Intent();
                    gintent.setType("video/*");
                    gintent.setAction(Intent.ACTION_PICK);
                    startActivityForResult(
                            Intent.createChooser(gintent, "Select Picture"),
                            REQUEST_PICK_VIDEO);

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();


    }
    private void selectAudio() {
        final CharSequence[] items = {"Record Audio", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose audio..");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Record Audio")) {
                    File mediaFile1=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/myaudios.mp3");

                    Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
                    audioUri = FileProvider.getUriForFile(getActivity(), getActivity().getPackageName() + ".provider", mediaFile1);
                    startActivityForResult(intent, ACTIVITY_RECORD_SOUND);
                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent();
                    intent.setType("audio/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Audio "), REQUEST_PICK_AUDIO);

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();


    }


    private void selectFiles()
    {
        System.out.println("About to pick files");
        Intent gintent = new Intent();
        gintent.setType("file/*");
        gintent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(gintent, "Select File"),
                PICK_FILE);


    }
}
