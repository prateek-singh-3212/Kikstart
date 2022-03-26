package `in`.bitlogger.kikstart.ui

import `in`.bitlogger.kikstart.databinding.ActivitySchemesDetailBinding
import `in`.bitlogger.kikstart.db.model.SchemesModel
import `in`.bitlogger.kikstart.utils.Constants
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.bumptech.glide.Glide


class SchemesDetailActivity : AppCompatActivity() {
    private lateinit var schemesDetailBinding: ActivitySchemesDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        schemesDetailBinding = ActivitySchemesDetailBinding.inflate(layoutInflater)
        setContentView(schemesDetailBinding.root)

        val data = intent.getParcelableExtra(Constants.SCHEME_LIST) ?: SchemesModel(
            ",",
            0,
            "",
            "",
            ""
        )

        schemesDetailBinding.schemesDetailTitle.text = data.title
        schemesDetailBinding.schemesDetailDescription.text = data.content
        Glide.with(this).load(data.image.toUri()).into(schemesDetailBinding.schemesDetailImage)

        schemesDetailBinding.schemesDetailApplyNow.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse("https://www.lendingkart.com/application?from=product&content&CEversion=ZT=mudra-loan/")
            startActivity(i)
        }
    }
}