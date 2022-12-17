from flask import Flask, render_template, request, send_file, abort
from user_info import MyEmpDao
import pandas as pd
import time
from flask_cors import CORS
import matplotlib.pyplot as plt
from matplotlib.figure import Figure
import glob
import os
from io import BytesIO, StringIO
import base64

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


@app.route('/predict')
def predict():
    user = '기아'
    if request.args.get('user') != None and request.args.get('user') != '':
        user = request.args.get('user')
        gcwd = os.getcwd()
        filepath = glob.glob(gcwd+'/coname_graph/*'+user+'.csv')
        cofile = pd.read_csv(filepath[0])
        newcolumns = ['quarter','revenue','income','profit']
        cofile = cofile.values.tolist()
        ndf = pd.DataFrame(cofile, columns=newcolumns)

        ndfQ = ndf[ndf['quarter'].str.contains('Q')]
        ndfE = ndf[ndf['quarter'].str.contains('E')]

        plt.subplots(constrained_layout=True)

        plt.subplot(221)
        plt.title('Quarter revenue')
        plt.plot(ndfQ['quarter'],ndfQ['revenue'],'g')
        plt.xticks(rotation=90,fontsize=6.5)

        plt.subplot(222)
        plt.title('Yearly revenue')
        plt.plot(ndfE['quarter'],ndfE['revenue'],'g')
        plt.xticks(fontsize=6.5)

        plt.subplot(223)
        plt.title('Quarter income, profit')
        plt.plot(ndfQ['quarter'],ndfQ['income'])
        plt.plot(ndfQ['quarter'],ndfQ['profit'])
        plt.xticks(rotation=90,fontsize=6.5)

        plt.subplot(224)
        plt.title('Yearly income, profit')
        plt.plot(ndfE['quarter'],ndfE['income'])
        plt.plot(ndfE['quarter'],ndfE['profit'])
        plt.xticks(fontsize=6.5)

        plt.savefig(gcwd+'/static/graphs/'+user+'.png')
        return render_template('statics.html', name = user, image_file = 'graphs/'+user+'.png')
    else:
        return render_template('page_not_found.html'), 404

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