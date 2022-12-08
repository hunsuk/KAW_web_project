import pymysql
class MyEmpDao:
	    def __init__(self):
	        pass
	    
	    def getEmps(self,user_id):
	        ret = []
	        db = pymysql.connect(host='localhost', user='root', db='web', password='khu1479', charset='utf8')
	        curs = db.cursor()
	        
	        sql = "select * from user where email='khu753'"
	        curs.execute(sql)
	        
	        rows = curs.fetchall()
	        for e in rows:
	            temp = {'auth':e[1],'email':e[2]}
	            ret.append(temp)
	        
	        db.commit()
	        db.close()
	        return ret
	    
	    # def insEmp(self, empno, name, department,phone):
	    #     db = pymysql.connect(host='localhost', user='root', db='python', password='python', charset='utf8')
	    #     curs = db.cursor()
	        
	    #     sql = '''insert into emp (empno, name, department, phone) values(%s,%s,%s,%s)'''
	    #     curs.execute(sql,(empno, name, department,phone))
	    #     db.commit()
	    #     db.close()
	    
	    # def updEmp(self, empno, name, department,phone): 
	    #     db = pymysql.connect(host='localhost', user='root', db='python', password='python', charset='utf8')
	    #     curs = db.cursor()
	        
	    #     sql = "update emp set name=%s, department=%s, phone=%s where empno=%s"
	    #     curs.execute(sql,(name, department, phone, empno))
	    #     db.commit()
	    #     db.close()
	    # def delEmp(self, empno):
	    #     db = pymysql.connect(host='localhost', user='root', db='python', password='python', charset='utf8')
	    #     curs = db.cursor()
	    #     sql = "delete from emp where empno=%s"
	    #     curs.execute(sql,empno)
	    #     db.commit()
	    #     db.close()
	 
if __name__ == '__main__':
        #MyEmpDao().insEmp('aaa', 'bb', 'cc', 'dd')
        #MyEmpDao().updEmp('aa', 'dd', 'dd', 'aa')
        #MyEmpDao().delEmp('aaa')
        emplist = MyEmpDao().getEmps(1)
        print(emplist)