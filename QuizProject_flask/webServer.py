from flask import Flask, render_template, request, send_file, abort
from user_info import MyEmpDao
import pandas as pd
import time
from flask_cors import CORS


app = Flask(__name__)
app.config['JSON_AS_ASCII'] = False
CORS(app, resources={r'*': {'origins': 'https://127.0.0.1:8080'}})


@app.route('/user',methods=['GET', 'POST'])
def user():
    # empList = MyEmpDao().getEmps(1)
    # print(empList[0]['email'])
    if request.method == 'GET':
        return render_template('user_input.html')
    else:
        totime = time.strftime('%Y-%m-%d', time.localtime(time.time()))
        print(request.files['file'])
        f = request.files['file']
        name = request.form
        coname = ""
        for key, value in name.items():
            coname = value

        data_csv = pd.read_csv(f)
        data_csv.to_csv('coname_input_data/' + totime + '-' +coname + ".csv",index=False)
        
        return data_csv.to_html()



@app.route('/pridict_respons_file')
def csv_file_download_with_file():
    file_name = f"input_format/user_info_datafomat - sheet1.csv"
    return send_file(file_name,
                     mimetype='text/csv',
                     attachment_filename='downloaded_forMatFile_name.csv',# 다운받아지는 파일 이름. 
                     as_attachment=True)


@app.route('/request_file')
def csv_file_download_with_request_file():
    file_name = f"input_format/user_info_datafomat_request - sheet1.csv"
    return send_file(file_name,
                     mimetype='text/csv',
                     attachment_filename='downloaded_forMatFile_name.csv',# 다운받아지는 파일 이름. 
                     as_attachment=True)


#파레트 발주 페이지 및 발주 넣기
@app.route('/request_palette',methods=['GET','POST'])
def PalletRequest():
    if request.method == 'GET':
        return render_template('PalletRequest.html')    
    else:
        totime = time.strftime('%Y-%m-%d', time.localtime(time.time()))
        print(request.files['file'])
        f = request.files['file']
        name = request.form
        coname = ""
        for key, value in name.items():
            coname = value

        data_csv = pd.read_csv(f)
        data_csv.to_csv('save_request/' + totime + '-' +coname + ".csv",index=False)
        
        return data_csv.to_html()


@app.route('/admin')
def admin():
    # empList = MyEmpDao().getEmps(1)
    # print(empList[0]['email'])
    return render_template('admin_input.html')

if __name__ == '__main__':
    app.run()