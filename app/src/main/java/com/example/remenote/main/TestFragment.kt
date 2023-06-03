package com.example.remenote.main

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.remenote.R
import com.example.remenote.databinding.EditWebviewBinding
import jp.wasabeef.richeditor.RichEditor


class TestFragment : Fragment() {
    lateinit var binding: EditWebviewBinding
    private lateinit var editor: RichEditor
    private lateinit var launcher:ActivityResultLauncher<Intent>
    var mPermissionList = arrayOf<String>(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data: Intent? = result.data
                    if (data != null) {
                        val realPathFromUri = RealPathFromUriUtils.copyFileToPrivateStorage(
                            requireActivity(),
                            data.data
                        );

                        editor.insertImage(
                            "window.location.href='$realPathFromUri",
                            "$realPathFromUri\" style=\"max-width:100%"
                        )
                    } else {
                        Toast.makeText(requireActivity(), "图片损坏，请重新选择", Toast.LENGTH_SHORT)
                            .show();
                    }

                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EditWebviewBinding.inflate(inflater)
//        val webView =  binding.webView
//        webView.settings.javaScriptEnabled = true
//
//        webView.loadUrl("file:///android_asset/eidtor.html");
        editor = binding.editor
        editor.setEditorHeight(200);
        editor.setEditorFontSize(22);
        editor.setEditorFontColor(Color.BLACK);
        editor.setPadding(10, 10, 10, 10);
        editor.setBackgroundColor(Color.BLUE);
        editor.setPlaceholder("Insert text here...");
        editor.setInputEnabled(true);

        val toolbar = binding.toolbar
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(toolbar)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.rich_panel_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_undo -> {
                editor.undo()
            }

            R.id.action_redo -> {
                editor.redo()
            }

            R.id.action_italic -> {
                editor.focusEditor()
                editor.setItalic()
            }

            R.id.action_subscript -> {
                editor.focusEditor()
                if (editor.html != null) {
                    editor.setSubscript()
                }
            }

            R.id.action_superscript -> {
                editor.focusEditor()
                if (editor.html != null) {
                    editor.setSuperscript()
                }
            }

            R.id.action_strikethrough -> {
                editor.focusEditor()
                editor.setStrikeThrough()
            }

            R.id.action_underline -> {
                editor.focusEditor()
                editor.setUnderline()
            }

            R.id.action_heading1 -> {
                editor.setHeading(1)
            }

            R.id.action_heading2 -> {
                editor.setHeading(2)
            }

            R.id.action_heading3 -> {
                editor.setHeading(3)
            }

            R.id.action_heading4 -> {
                editor.setHeading(4)
            }

            R.id.action_heading5 -> {
                editor.setHeading(5)
            }

            R.id.action_heading6 -> {
                editor.setHeading(6)
            }

            R.id.action_txt_color_red -> {
                editor.focusEditor()
                editor.setTextColor(Color.RED)
            }

            R.id.action_txt_color_yellow -> {
                editor.focusEditor()
                editor.setTextColor(Color.YELLOW)
            }

            R.id.action_txt_color_blue -> {
                editor.focusEditor()
                editor.setTextColor(Color.BLUE)
            }

            R.id.action_txt_color_green -> {
                editor.focusEditor()
                editor.setTextColor(Color.GREEN)
            }

            R.id.action_txt_color_black -> {
                editor.focusEditor()
                editor.setTextColor(Color.BLACK)
            }

            R.id.action_txt_bg_red -> {
                editor.focusEditor()
                editor.setTextBackgroundColor(Color.RED)
            }

            R.id.action_txt_bg_yellow -> {
                editor.focusEditor()
                editor.setTextBackgroundColor(Color.YELLOW)
            }

            R.id.action_txt_bg_blue -> {
                editor.focusEditor()
                editor.setTextBackgroundColor(Color.BLUE)
            }

            R.id.action_txt_bg_green -> {
                editor.focusEditor()
                editor.setTextBackgroundColor(Color.GREEN)
            }

            R.id.action_txt_bg_black -> {
                editor.focusEditor()
                editor.setTextBackgroundColor(Color.BLACK)
            }

            R.id.action_indent -> {
                editor.focusEditor()
                editor.setIndent()
            }

            R.id.action_outdent -> {
                editor.focusEditor()
                editor.setOutdent()
            }

            R.id.action_align_left -> {
                editor.focusEditor()
                editor.setAlignLeft()
            }

            R.id.action_align_right -> {
                editor.focusEditor()
                editor.setAlignRight()
            }

            R.id.action_align_center -> {
                editor.focusEditor()
                editor.setAlignCenter()
            }

            R.id.action_insert_bullets -> {
                editor.setBullets()
            }

            R.id.action_insert_numbers -> {
                editor.setNumbers()
            }

            R.id.action_blockquote -> {
                editor.setBlockquote()
            }

            R.id.action_insert_image -> {
                editor.focusEditor()
                requestPermissions()

            }

            R.id.action_insert_link -> {}
            R.id.action_insert_checkbox -> {}


        }

        return super.onOptionsItemSelected(item)
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val granted = permissions.entries.all {
                it.value
            }
//            if (granted) {


//                }

                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.type = "image/*"
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                launcher.launch(intent)
//            } else {
//                Toast.makeText(requireActivity(), "请设置必要权限", Toast.LENGTH_SHORT).show()
//            }
        }

    private fun requestPermissions() {
        val permissions = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        requestPermissionLauncher.launch(permissions)
    }




}