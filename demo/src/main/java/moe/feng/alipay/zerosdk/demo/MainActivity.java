package moe.feng.alipay.zerosdk.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import moe.feng.alipay.zerosdk.AlipayZeroSdk;

public class MainActivity extends AppCompatActivity {

	private AppCompatEditText mQRCodeResultEdit;
	private TextView mStatusText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Set up window
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set up views
		mQRCodeResultEdit = (AppCompatEditText) findViewById(R.id.qrcode_text);
		mStatusText = (TextView) findViewById(R.id.client_status_text);

		findViewById(R.id.btn_transfer).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// Prevent get a null argument
				if (mQRCodeResultEdit.getText().toString().isEmpty()) {
					Toast.makeText(
							getApplicationContext(),
							R.string.toast_qrcode_empty,
							Toast.LENGTH_SHORT
					).show();
					return;
				}

				// Start Alipay client
				AlipayZeroSdk.startAlipayClient(
						MainActivity.this, mQRCodeResultEdit.getText().toString()
				);
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();

		// Print Alipay client's status
		boolean hasInstalledAlipayClient = AlipayZeroSdk.hasInstalledAlipayClient(this);
		String versionName = AlipayZeroSdk.getAlipayClientVersion(this);
		mStatusText.setText(
				getString(R.string.format_status_text,
						String.valueOf(hasInstalledAlipayClient),
						versionName)
		);
	}

}
