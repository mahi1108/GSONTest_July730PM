package cubex.mahesh.gsontest

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.io.FileInputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

  /*      ij.setOnClickListener({
                        var g = Gson( )
            var emp = Employee(et1.text.toString().toInt(),
  et2.text.toString(),et3.text.toString(),et4.text.toString())
    var list = mutableListOf<Employee>()
    list.add(emp)
    var emps = Employees(list)
    var json_resp = g.toJson(emps)
     var fos =   openFileOutput("employees.json",
                                    Context.MODE_PRIVATE)
     fos.write(json_resp.toByteArray())
     fos.flush()
     fos.close()
        }) */

        ij.setOnClickListener({
            var g = Gson( )
            var emp = Employee(et1.text.toString().toInt(),
                    et2.text.toString(),et3.text.toString(),et4.text.toString())
             var list:MutableList<Employee>
            var fis:FileInputStream?
                try {
        fis = openFileInput("employees.json")
                }catch (e:Exception){
                    fis=null
                }
            var emps:Employees
           if(fis==null){
               list = mutableListOf<Employee>()
               list.add(emp)
               emps= Employees(list)
           }else{
               emps =   g.fromJson(InputStreamReader(fis),
                       Employees::class.java)
                list = emps.employees
               list.add(emp)
           }


            var json_resp = g.toJson(emps)
            var fos =   openFileOutput("employees.json",
                    Context.MODE_PRIVATE)
            fos.write(json_resp.toByteArray())
            fos.flush()
            fos.close()
        })

        rj.setOnClickListener({
          var fis =  openFileInput("employees.json")
            var g = Gson( )
          var emps =   g.fromJson(InputStreamReader(fis),
                    Employees::class.java)
          var list = emps.employees
          var temp_list =   mutableListOf<String>()
          for (emp in list){
              temp_list.add(emp.id.toString() +"|" +
                        emp.name+"|"+emp.desig+"|"+emp.dept)
          }
          var myAdapter = ArrayAdapter<String>(
                  this@MainActivity,
                  android.R.layout.simple_list_item_single_choice,
                  temp_list)
           lview.adapter = myAdapter
        })

    }
}
