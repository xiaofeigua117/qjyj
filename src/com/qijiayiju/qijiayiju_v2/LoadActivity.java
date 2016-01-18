package com.qijiayiju.qijiayiju_v2;

import java.util.List;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.DbUtils.DaoConfig;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.qijiayiju.entity.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoadActivity extends Activity {
	EditText editText_name;
	EditText editText_password;
	User user2;
	User parent;
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load);
		this.getActionBar().hide();
		editText_name = (EditText) findViewById(R.id.name_edit);
		editText_password = (EditText) findViewById(R.id.password_edit);
		button = (Button) findViewById(R.id.denglu);

		// �������ݿ�
		DaoConfig config = new DaoConfig(this);
		config.setDbName("Xutils.db"); // db��
		config.setDbVersion(1); // db�汾
		DbUtils db = DbUtils.create(config);
		User user = new User(); // ������Ҫע�����User���������id���ԣ�������ͨ��@IDע�������
		user.setName("qijiayiju");
		user.setPassword("a123456");
		try {
			db.save(user);
		} catch (DbException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			// User entity = db.findById(User.class, user1.getId());
			parent = db.findFirst(Selector.from(User.class).where("name", "=",
					"qijiayiju"));
			List<User> list = db.findAll(User.class);
			Log.i("dssd", list.get(0).toString());
			// Toast.makeText(this, parent.getName(), 3000).show();
			// editText.setText(db.findById(User.class,
			// user1.getId()).getName().toString());
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String name = editText_name.getText().toString();
				String password = editText_password.getText().toString();
				if (name.equals("qjyj") && password.equals("123456")) {
					Intent intent = new Intent(LoadActivity.this,
							MainActivity.class);
					startActivity(intent);
				} else {
					Toast.makeText(LoadActivity.this, "�������", 3000).show();
				}
			}
		});

		// User user1 = new User(); //������Ҫע�����User���������id���ԣ�������ͨ��@IDע�������
		// user1.setName("yxn");
		// user1.setPassword("123456");

		// User user = new User(); //������Ҫע�����User���������id���ԣ�������ͨ��@IDע�������
		// user.setName("qijiayiju");
		// user.setPassword("a123456");
		// try {
		// db.save(user1);
		//
		//
		//
		// } catch (DbException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }//�ڱ��б���һ��user�������ִ�б��涯��ʱ��Ҳ�ᴴ��User��
		//

	}
}
