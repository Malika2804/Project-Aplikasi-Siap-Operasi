package com.example.ui.components

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilePresent
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ui.theme.MedicalCanvasBg
import com.example.ui.theme.MedicalOutline
import com.example.ui.theme.MedicalSurfaceVariant
import com.example.ui.theme.OceanBlueDark
import com.example.ui.theme.OceanBlueLight
import com.example.ui.theme.OceanBluePrimary
import com.example.ui.theme.ToskaSecondary
import com.example.util.QrCodeGenerator

@Composable
fun QrUrlSettingsDialog(
    currentWebUrl: String,
    onSaveUrl: (String) -> Unit,
    onResetDefault: () -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    var urlInput by rememberSaveable(currentWebUrl) {
        mutableStateOf(
            if (QrCodeGenerator.isUnconfiguredDefaultUrl(currentWebUrl)) "" else currentWebUrl
        )
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 460.dp)
                .padding(horizontal = 16.dp, vertical = 20.dp)
                .testTag("qr_url_settings_dialog"),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, MedicalOutline),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(18.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Atur Link Web Tujuan QR Code",
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF0F172A),
                        fontSize = 15.5.sp,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .size(48.dp)
                            .testTag("close_qr_url_settings_btn")
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = MedicalSurfaceVariant,
                            modifier = Modifier.size(36.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Tutup",
                                    modifier = Modifier.size(18.dp),
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))
                HorizontalDivider(color = MedicalOutline)
                Spacer(modifier = Modifier.height(10.dp))

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = OceanBlueLight,
                    border = BorderStroke(1.dp, Color(0xFFBAE6FD)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "Tidak Perlu Sewa Server Berbayar!",
                            fontWeight = FontWeight.ExtraBold,
                            color = OceanBlueDark,
                            fontSize = 12.5.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "1. Ketuk tombol 'Bagikan File Web (index.html)' di bawah.\n" +
                                "2. Buka app.netlify.com/drop (atau GitHub Pages) secara gratis, lalu unggah file index.html tersebut.\n" +
                                "3. Salin link https://... yang muncul, tempel di kolom bawah, lalu ketuk Simpan Link QR.",
                            color = Color(0xFF1E293B),
                            fontSize = 11.5.sp,
                            lineHeight = 16.5.sp
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        OutlinedButton(
                            onClick = { QrCodeGenerator.shareStandaloneWebHtml(context) },
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(1.dp, OceanBluePrimary),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = Color.White,
                                contentColor = OceanBluePrimary
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 48.dp)
                                .testTag("share_web_html_from_settings_btn")
                        ) {
                            Icon(
                                imageVector = Icons.Default.FilePresent,
                                contentDescription = null,
                                modifier = Modifier.size(17.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Bagikan File Web (index.html)",
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Alamat Link Web Publik (URL HTTPS)",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E293B),
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(6.dp))

                OutlinedTextField(
                    value = urlInput,
                    onValueChange = { urlInput = it },
                    placeholder = {
                        Text(
                            text = "https://siap-operasi-rs.netlify.app",
                            fontSize = 12.sp
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = OceanBluePrimary,
                        unfocusedBorderColor = MedicalOutline
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_qr_web_url")
                )

                Spacer(modifier = Modifier.height(10.dp))

                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = MedicalCanvasBg,
                    border = BorderStroke(1.dp, MedicalOutline),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Catatan: Jika Anda ingin menggunakan QR secara lokal di ruangan RS tanpa unggah ke internet, cukup tutup jendela ini lalu pilih mode 'Hotspot / Wi-Fi HP' pada menu QR.",
                        color = Color(0xFF334155),
                        fontSize = 11.5.sp,
                        lineHeight = 16.5.sp,
                        modifier = Modifier.padding(10.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            onResetDefault()
                            urlInput = ""
                            Toast.makeText(
                                context,
                                "Link QR dikembalikan ke pengaturan awal.",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, ToskaSecondary),
                        modifier = Modifier
                            .weight(1f)
                            .heightIn(min = 48.dp)
                            .testTag("reset_qr_url_btn")
                    ) {
                        Text(
                            text = "Reset Default",
                            color = ToskaSecondary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }

                    Button(
                        onClick = {
                            onSaveUrl(urlInput)
                            Toast.makeText(
                                context,
                                "Link tujuan QR Code berhasil diperbarui!",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = OceanBluePrimary),
                        modifier = Modifier
                            .weight(1.35f)
                            .heightIn(min = 48.dp)
                            .testTag("save_qr_url_btn")
                    ) {
                        Text(
                            text = "Simpan Link QR",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.5.sp
                        )
                    }
                }
            }
        }
    }
}
