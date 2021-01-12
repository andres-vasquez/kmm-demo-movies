package com.github.andresvasquez.topmovies.androidApp.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.RectF
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import com.github.andresvasquez.topmovies.shared.utils.Constants
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class CompressImage {
    companion object {
        fun compressImage(image: Uri?, context: Context): Uri? {
            val out: FileOutputStream
            val strPhotoName = "user_" + Calendar.getInstance().timeInMillis + ".jpg"
            return try {
                //Step 1: Convert to bitmap
                var bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, image)

                //Step 2: Prepare the files
                //Folder
                val folder =
                    File(Environment.getExternalStorageDirectory(), Constants.DIRECTORY_IMAGE)
                if (!folder.exists()) {
                    folder.mkdirs()
                }
                //File
                val file = File(
                    Environment.getExternalStorageDirectory(),
                    Constants.DIRECTORY_IMAGE.toString() + File.separator + strPhotoName
                )
                out = FileOutputStream(file)

                //Step 3: Matrix to compress
                val m = Matrix()
                m.setRectToRect(
                    RectF(0f, 0f, bitmap.width.toFloat(), bitmap.height.toFloat()),
                    RectF(0f, 0f, 1280f, 720f), Matrix.ScaleToFit.CENTER
                )
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, m, true)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
                out.flush()
                out.close()
                Uri.fromFile(file)
            } catch (e: IOException) {
                Timber.e(e)
                null
            }
        }
    }
}