#Modulos 
#Interfaz
from tkinter import *
from tkinter import messagebox
#Guardar en archivos
import json
#Correos
import smtplib
from decouple import config
from validate_email import validate_email
from email.mime.multipart import MIMEMultipart
from email.mime.base import MIMEBase
from email.mime.text import MIMEText
from email import encoders
#Horarios y citas
import datetime
#Colas
from queue import Queue
#Manual de usuario
import os
#PDF
from reportlab.pdfgen import canvas

#Clase para los nodos del arbol
class Nodo:

#__init__ es un nombre especial
#E: Una cia
#S: Acomoda las citas en el arbol
    def __init__(self, datos):
        self.datos = datos
        self.izquierda = None
        self.derecha = None

#__str__ es un nombre especial
#E: Nada
#S: Convierte los datos a str
    def __str__(self):
        return str(self.datos)

    def get_cita(self):
        return self.datos[0]

    def get_placa(self):
        return self.datos[8]
#Clase del arbol de citas
class Arbol:

#__init__ es un nombre especial
#E: Una cia
#S: Llama a la clase Nodo para que acomode las citas en el arbol
    def __init__(self, datos):
        self.raiz = Nodo(datos)

#Agregar de forma recursiva
#E: Un nodo del arbol y los datos a agregar
#S: Actualiza el arbol con una nueva cita
    def agregar_recursivo(self, nodo, datos):
        if datos[0] < nodo.datos[0]:
            if nodo.izquierda is None:
                nodo.izquierda = Nodo(datos)
            else:
                self.agregar_recursivo(nodo.izquierda, datos)
        else:
            if nodo.derecha is None:
                nodo.derecha = Nodo(datos)
            else:
                self.agregar_recursivo(nodo.derecha, datos)

#Funcion publica
#E: Cita
#S: Llama a la funcion recursiva del arbol
    def agregar(self, datos):
        self.agregar_recursivo(self.raiz, datos)

#Eliminar de forma recursiva
#E: Un nodo del arbol y los datos a eliminar
#S: Actualiza el arbol con una cita menos 
    def eliminar_recursivo(self, nodo, datos):
        if nodo is None:
            return nodo

        if datos.datos[0] < nodo.datos[0]:
            nodo.izquierda = self.eliminar_recursivo(nodo.izquierda, datos)
        elif datos.datos[0] > nodo.datos[0]:
            nodo.derecha = self.eliminar_recursivo(nodo.derecha, datos)
        else:
            if nodo.izquierda is None:
                temp = nodo.derecha
                nodo = None
                return temp
            elif nodo.derecha is None:
                temp = nodo.izquierda
                nodo = None
                return temp

            temp = self.encontrar_minimo(nodo.derecha)
            nodo.datos = temp.datos
            nodo.derecha = self.eliminar_recursivo(nodo.derecha, temp.datos)

        return nodo

#Funcion publica
#E: Cita
#S: Llama a la funcion recursiva del arbol
    def eliminar(self, datos):
        self.raiz = self.eliminar_recursivo(self.raiz, datos)

    def encontrar_minimo(self, nodo):
        actual = nodo
        while actual.izquierda is not None:
            actual = actual.izquierda
        return actual

#Buscar de forma recursiva
#E: Un nodo del arbol y los datos a buscar, en este caso el numero de cita y la placa
#S: Devuelve la cita que se busca en caso de que exista 
    def buscar_recursivo(self, nodo, dato1, dato2):
        if nodo is None or nodo.datos[0] == dato1 and nodo.datos[8] == dato2:
            return nodo

        if dato1 < nodo.datos[0]:
            return self.buscar_recursivo(nodo.izquierda, dato1, dato2)
        else:
            return self.buscar_recursivo(nodo.derecha, dato1, dato2)

#Funcion publica
#E: #cita y placa
#S: Llama a la funcion recursiva del arbol
    def buscar(self, dato1, dato2):
        return self.buscar_recursivo(self.raiz, dato1, dato2)

#Buscar de forma recursiva, es distinta a la anterior para buscar otros dos datos especificos
#E: Un nodo del arbol y los datos a buscar, en este caso el mes y la placa
#S: Devuelve la cita que se busca en caso de que exista  
    def buscar_recursivo2(self, nodo, dato1, dato2):
        if nodo is None or nodo.datos[3] == dato1 and nodo.datos[8] == dato2:
            return nodo

        if dato1 < nodo.datos[3]:
            return self.buscar_recursivo2(nodo.izquierda, dato1, dato2)
        else:
            return self.buscar_recursivo2(nodo.derecha, dato1, dato2)

#Funcion publica
#E: Cita
#S: Llama a la funcion recursiva del arbol
    def buscar2(self, dato1, dato2):
        return self.buscar_recursivo2(self.raiz, dato1, dato2)
    
 #Funcion para actualizar el arbol
 #E: Nodo, dato a reemplazar, dato a agregar
 #S: Arbol actualizado
    def reemplazar_dato_recursivo(self, nodo, dato_viejo, dato_nuevo):
        if nodo is None:
            return None

        if nodo.get_cita() == dato_viejo.get_cita() and nodo.get_placa() == dato_viejo.get_placa():
            nodo.datos = dato_nuevo

        elif dato_viejo.get_cita() < nodo.get_cita():
            nodo.izquierda = self.reemplazar_dato_recursivo(nodo.izquierda, dato_viejo, dato_nuevo)

        else:
            nodo.derecha = self.reemplazar_dato_recursivo(nodo.derecha, dato_viejo, dato_nuevo)

        return nodo

#Funcion publica
#E: Cita vieja, cita actualizada
#Llama a la funcion recursiva
    def reemplazar(self, dato_viejo, dato_nuevo):
        self.raiz = self.reemplazar_dato_recursivo(self.raiz, dato_viejo, dato_nuevo)




#Boton para ir atras
#E: Ventana a cerrar y ventana a abrir
#S: Nada
def atr(cerrar, abrir):
    cerrar.destroy()   
    abrir.deiconify()

#Validaciones de configuracion
#E: Usa variables globales
#S: Actualiza el arbol, no retorna nada
def validaciones_config():
    
    configdat = open("configuracion.dat", "r")
    datos = configdat.read()
    elementos = eval(datos)
    configdat.close()
    horario = elementos[1]
    h = 0
    guardar = True

    if lineas.get() == "":
        elementos[0] = elementos[0]
    elif 25 >= int(lineas.get()) >= 1:
        #Se reinicia la cola para evitar que las 6 iniciales alteren el resultado
        cola = Queue()
        elementos[0] = int(lineas.get())
        #Se crean las lineas necesarias
        for i in range(int(lineas.get())):
            cola.put([])
    else: 
        messagebox.showwarning("Error", "El numero de lineas debe ser menor a 25")
        guardar = False

    if hora1.get() == "":
        horario[0] = horario[0]
        h+=1

    if hora2.get() == "":
        horario[1]=horario[1]
        h+=1
    elif 23 >= int(hora1.get()) >= 0 and 23 >= int(hora2.get()) >=0:
        horario[0] = int(hora1.get())
        horario[1] = int(hora2.get())
        elementos[1] = horario
    else:
        messagebox.showwarning("Error", "Las horas pueden ir de 1 a 23")
        guardar = False

    if h == 2:
        elementos[1] = elementos[1]
        
    if minutos.get() == "":
        elementos[2] = elementos[2]
    elif 45 >= int(minutos.get()) >= 5:
        elementos[2] = int(minutos.get())
    else:
        messagebox.showwarning("Error", "El minimo de minutos es de 5 y el maximo 45")
        guardar = False

    if dias.get() == "":
        elementos[3] = elementos[3]
    elif 60 >= int(dias.get()) >= 1:
        elementos[3] = int(dias.get())
    else:
        messagebox.showwarning("Error", "La cantidad de dias debe ser menor a 60")
        guardar = False

    if falla.get() == "":
        elementos[4] = elementos[4]
    elif int(falla.get()) > 0:
        elementos[4] = int(falla.get())
    else:
        messagebox.showwarning("Error", "La cantidad de fallas debe ser mayor a 0")
        guardar = False

    if meses.get() == "":
        elementos[5] = elementos[5]
    elif 12 >= int(meses.get()) >= 1:
        elementos[5] = int(meses.get())
    else:
        messagebox.showwarning("Error", "Los meses deben ser menores a 13")
        guardar = False

    if iva.get() == "":
        elementos[6] = elementos[6]
    elif 20 >= float(iva.get()) > 0:
        elementos[6] = float(iva.get())
    else:
        messagebox.showwarning("Error", "El IVA debe ser menor a 20%")
        guardar = False
    
    if guardar:
        configdat = open("configuracion.dat", "w")
        configdat.write(str(elementos))
        configdat.close()

#Funcion para guardar los datos de tarifas en el archivo
#El uso de json es porque al usar eval() da error de sintaxis
#E: Una lista de las tarifas nuevas y la lista de tarifas para actualizar
#S: Guarda en la configuracion la nueva lista de tarifas
def g_tarifas(entradas, tarifas):
    with open("configuracion.dat", "r") as archivo:
        datos = archivo.read()
        elementos = json.loads(datos)
    archivo.close()

   
    for i, tarifa in enumerate(entradas):
        val = tarifa.get()
        if val:
            tarifas[i] = int(val)
    elementos[7] = tarifas

    with open("configuracion.dat", "w") as archivo:
        archivo.write(json.dumps(elementos))
    archivo.close()


#Modificar tarifas
#E: Usa variables globales
#S: Nada, llama a la funcion g_tarifas
def tarifas_mod():
    tar = Tk()
    tar.title("Tarifas")
    tar.geometry("300x300")
    tar.configure(bg="aquamarine")

    configdat = open("configuracion.dat", "r")
    datos = configdat.read()
    elementos = eval(datos)
    configdat.close()
    tarifas = elementos[7]
    entradas = []
    
    for i, vehi in enumerate(t_vehiculos):
        tip = Label(tar, text=vehi, bg="aquamarine")
        tip.grid(row=i+1, column=0, sticky="w")

        tarifa = Entry(tar)
        tarifa.grid(row=i+1, column=1)
        tarifa.insert(END, str(tarifas[i]))
        entradas.append(tarifa)

    guardar = Button(tar, text="Guardar", command=lambda:g_tarifas(entradas, tarifas), width=5, height=1, bg="sky blue", fg = "white")
    guardar.grid(row=len(vehi)+2, column=0, sticky="w")

    tar.mainloop()
    

#Configuracion del programa
#E: Ventana principal y el archivo de configuracion
#S: Nada, guarda la configuracion nueva
def configuracion(principal, configdat):
    principal.withdraw()
    configu = Tk()
    configu.title("Configuracion del sistema")
    configu.geometry("430x600")
    configu.configure(bg="aquamarine")

    global lineas, hora1, hora2, minutos, dias, falla, meses, iva
    
    iva = StringVar()

    atras = Button(configu, text="<-", command=lambda: atr(configu, principal), width = 5, height=1, bg="sky blue", fg = "white")
    atras.pack(anchor="nw")

    lin = Label(configu, text="Cantidad de lineas de trabajo", bg="aquamarine")
    lin.pack()

    lineas = Entry(configu)
    lineas.pack()

    hor = Label(configu, text="Horario de la estacion", bg="aquamarine")
    hor.pack()

    hora1 = Entry(configu)
    hora1.pack()

    hora2 = Entry(configu)
    hora2.pack()

    minu = Label(configu, text="Minutos por revision", bg="aquamarine")
    minu.pack()

    minutos = Entry(configu)
    minutos.pack()

    dia = Label(configu, text="Dias para reinspeccion", bg="aquamarine")
    dia.pack()

    dias = Entry(configu)
    dias.pack()

    fall = Label(configu, text="Cantidad de fallas permitidas", bg="aquamarine")
    fall.pack()

    falla = Entry(configu)
    falla.pack()

    mes = Label(configu, text="Meses disponibles para dar citas", bg="aquamarine")
    mes.pack()

    meses = Entry(configu)
    meses.pack()

    iv = Label(configu, text="'%' de Impuesto", bg="aquamarine")
    iv.pack()

    iva = Entry(configu)
    iva.pack()

    tar = Button(configu, text="Tarifas para cada vehiculo", command=tarifas_mod, bg="sky blue", fg = "white")
    tar.pack()

    guardar = Button(configu, text="Guardar", command=lambda: validaciones_config(), width = 5, height=1, bg="sky blue", fg = "white")
    guardar.pack()

    configdat.close()

    
#----------------------------------------------------------

#Función para seleccionar solo una opción
#E: La opcion
#S: Nada, solo sirve para estetica del programa
def seleccionada(opcion):
    op.set(opcion)

#Funcion utilizada por vehiculo
#E: event, porque asi se evita un error
#S: Nada, le asigna a la variable tvehiculo el dato seleccionado de la listbox
def vehiculo_select(event):
    selec = lista.curselection()
    if selec:
        index = selec[0]
        dato = lista.get(index)
        tvehiculo.set(dato)
        
#Crea la listbox de los vehiculos
#E: Usa variables globales
#S: llama a la funcion vehiculo_select para guardar el vehiculo seleccionado
def vehiculo():
    vehiculos = Tk()
    vehiculos.title("Tipo de vehiculo")
    vehiculos.geometry("300x300")
    vehiculos.configure(bg="aquamarine")

    global lista, tvehiculo
    lista=Listbox(vehiculos, selectmode=SINGLE)
    lista.pack()

    for i in t_vehiculos:
        lista.insert(END, i)

    tvehiculo = StringVar()
    lista.bind("<<ListboxSelect>>", vehiculo_select)

    vehiculos.mainloop()


#Funcion para enviar correos
#E: Correo de la cita_actual y la fecha y hora de la cita_actual
#S: Nada, envia el correo con la informacion de la cita
def env_correo(receptor, fyh):

    mensaje = f"""Subject: Su cita ha sido programada!

    
                Cita programada para el dia {fyh[0]}/{fyh[1]}/{fyh[2]} a las {fyh[3]}:{fyh[4]}.
                Gracias por preferirnos!

                Riteve...
                """.encode('utf-8')

    serv = smtplib.SMTP("smtp.gmail.com", 587)
    serv.starttls()
    serv.login("pruebap3jfgc@gmail.com", config("contra"))
    serv.sendmail("pruebap3jfgc@gmail.com", receptor, mensaje) 
    serv.quit()

#Validaciones para la cita
#E: Contador de citas
#S: Nada, agrega la cita al arbol
def validaciones_citas(): 
    global cita_actual, op, nplaca, marca, modelo, propietario, tel, direc, horayminutos, cont_citas
    val = True
    if len(nplaca.get()) > 8 or len(nplaca.get()) < 1:
        messagebox.showwarning("Error", "La placa debe tener como maximo 8 digitos")
        val = False
    
    if len(marca.get()) > 15 or len(marca.get()) < 3:
        messagebox.showwarning("Error", "La marca debe tener como maximo 15 digitos y\ncomo minimo 3")
        val = False
    
    if len(modelo.get()) > 15 or len(modelo.get()) < 1:
        messagebox.showwarning("Error", "El modelo debe tener como maximo 15 digitos")
        val = False
        
    if len(propietario.get()) > 40 or len(propietario.get()) < 3:
        messagebox.showwarning("Error", "El nombre debe tener como maximo 40 digitos y\ncomo minimo 3")
        val = False
    
    if len(tel.get()) != 20:
        messagebox.showwarning("Error", "El telefono debe tener 20 digitos")
        val = False
        
    if not validate_email(str(correo.get())):
        messagebox.showwarning("Error", "Ingrese un correo valido")
        val = False
        
    if len(direc.get()) > 40 or len(direc.get()) < 10:
        messagebox.showwarning("Error", "La direccion debe tener como maximo 40 digitos y\ncomo minimo 10")
        val = False

    pla = str(nplaca.get())
    
    #Validacion para no sacar dos citas para la misma placa el mismo dia


    cit = citas.buscar(fyh[0], pla)

    if cit is not None and "Primera" in cit:
        messagebox.showwarning("Error", "No puede sacar dos citas el mismo día para la misma placa")
        val = False
    if val:
        cita_actual.append(cont_citas)
        cita_actual.append("PENDIENTE")
        cita_actual.append(fyh[0])
        cita_actual.append(fyh[1])
        cita_actual.append(fyh[2])
        cita_actual.append(fyh[3])
        cita_actual.append(fyh[4])
        cita_actual.append(op.get())
        cita_actual.append(nplaca.get())
        cita_actual.append(tvehiculo.get())
        cita_actual.append(marca.get())
        cita_actual.append(modelo.get())
        cita_actual.append(propietario.get())
        cita_actual.append(tel.get())
        cita_actual.append(correo.get())
        cita_actual.append(direc.get())
        receptor = correo.get()

        horas_tomadas.append(horayminutos)
        
        #Limpiar campos
        op.set("")
        nplaca.delete(0, END)
        tvehiculo.set("")
        marca.delete(0, END)
        modelo.delete(0, END)
        propietario.delete(0, END)
        tel.delete(0, END)
        correo.delete(0, END)
        direc.delete(0, END)

        #Aumentar contador de citas
        cont_citas += 1
        env_correo(receptor, fyh)
        citas.agregar(cita_actual)


#Funciones para hora de cita
#Guardar fecha y hora
#E: Usa variables globales
#S: Nada, guarda la fecha y hora en fyh
def g_fyh():
    
    configdat = open("configuracion.dat", "r")
    datos = configdat.read()
    elementos=eval(datos)
    configdat.close()
    horas = elementos[1]

    global fyh, horayminutos
    val = True
    fyh = []
    #Variables para validar que el dia y mes no sean menores a los actuales
    m_a = fecha_actual.month
    d_a = fecha_actual.day

    #Variables para validar que las horas y minutos no sean menores a los actuales
    h_a = fecha_actual.strftime("%I")
    mi_a = fecha_actual.minute

    if int(d.get()) < d_a:
        messagebox.showwarning("Error", "El dia no puede ser menor a "+str(d_a))
        val = False
    if int(d.get()) > 31:
        messagebox.showwarning("Error", "El dia no puede ser mayor a 31")
        val = False

    if int(m.get()) < m_a:
        messagebox.showwarning("Error", "El mes no puede ser menor a " + str(m_a))
        val = False
    elif int(m.get()) > 12:
        messagebox.showwarning("Error", "El mes no puede ser mayor a 12")
        val = False

    if int(h.get()) >= horas[1] or int(h.get()) <= horas[0]:
        messagebox.showwarning("Error", "El horario es de " + str(horas[0]) + " a " + str(horas[1]))        
        val = False
    if int(h.get()) < int(h_a):
        messagebox.showwarning("Error", "La hora no puede ser menor a " + str(h_a))
        val = False

    if int(mi.get()) < mi_a:
        messagebox.showwarning("Error", "Los minutos no pueden ser menores a " + str(mi_a))
        val = False
    if int(mi.get()) > 60: 
        messagebox.showwarning("Error", "Los minutos no pueden ser mayores a 60")
        val = False

    horayminutos = [int(h.get()), int(mi.get())]
    if horayminutos in horas_tomadas:
        messagebox.showerror("Error", "Ya esa hora esta ocupada")
        val = False
    if val:
        fyh=[int(d.get()), int(m.get()), a_a, int(h.get()), int(mi.get())]



#Asignacion de hora manual
#E: Usa variables globales
#S: Nada, llama a la funcion g_fyh para guardar datos
def manual():

    mvent = Tk()
    mvent.title("Hora de cita manual")
    mvent.geometry("300x300")
    mvent.configure(bg="aquamarine")

    global d, m, a, h, mi, fecha_actual, a_a


    fecha_actual = datetime.datetime.now()
    a_a = fecha_actual.year


    dia = Label(mvent, text="Dia", bg="aquamarine")
    dia.pack()

    d = Entry(mvent)
    d.pack()

    mes = Label(mvent, text="Mes", bg="aquamarine")
    mes.pack()

    m = Entry(mvent)
    m.pack()

    año = Label(mvent, text="Año", bg="aquamarine")
    año.pack()
    
    a = Label(mvent, text=a_a, bg="aquamarine")
    a.pack()

    hora = Label(mvent, text="Hora", bg="aquamarine")
    hora.pack()

    h = Entry(mvent)
    h.pack()

    minutos = Label(mvent, text="Minutos", bg="aquamarine")
    minutos.pack()

    mi = Entry(mvent)
    mi.pack()

    acept = Button(mvent, text="Aceptar", command=g_fyh, bg="sky blue", fg = "white")
    acept.pack()

#----------------------------
#Guarda fyh con el dia y el mes
def g_fyh_auto():
    global fyh, horayminutos
    hor = fyh.get()

    ho, minu = hor.split(":")

    fecha_act = datetime.datetime.now()
    a_a = fecha_act.year
    ma = fecha_act.month
    da = fecha_act.day

    val = True
    
    horayminutos = [int(ho), int(minu)]
    if horayminutos in horas_tomadas:
        messagebox.showerror("Error", "Ya esa hora esta ocupada")
        val = False
    if val:
        fyh=[da, ma, a_a, int(ho), int(minu)]


#Funciona para gaurdar en fyh la hora seleccionada
def hora_select(event):
    i = horas_lista.curselection()
    dato = horas_lista.get(i)
    fyh.set(dato)
    
#Funcion para conseguir las horas disponibles
#E: Usa variables globales
#S: Una lista con las horas disponibles
def horas_disponibles():
    horas = []
    with open("configuracion.dat", "r") as archivo:
        datos = archivo.read()
        elementos = json.loads(datos)
    archivo.close()
    horaymin = elementos[1]
    hini = horaymin[0]
    hfin = horaymin[1]
    m_por_cita = elementos[2]

    for i in range(hini, hfin):
        for j in range(0, 60, m_por_cita):
            if [i, j] not in horas_tomadas:
                #.zfill(2) sirve para rellenar la hora con un 0 
                hora = str(i).zfill(2)
                minuto = str(j).zfill(2)
                tiemp = f'{hora}:{minuto}'
                horas.append(tiemp)
    return horas

#Sacar citas automaticas
#Variables globales
#Muestra una listbox creda con funciones auxiliares
def automatica():
    global horas_lista, fyh
    fyh = []
    auto = Tk()
    auto.title('Automatica')
    auto.geometry("300x300")
    auto.configure(bg="aquamarine")

    fyh = StringVar()

    horas_lista = Listbox(auto)
    horas_lista.pack(fill='both', expand=True)

    horas_dispo = horas_disponibles()

    for i in horas_dispo:
        horas_lista.insert('end', i)
    
    horas_lista.bind('<<ListboxSelect>>', hora_select)

    guardar = Button(auto, text="Guardar", command=g_fyh_auto, bg="sky blue", fg="white")
    guardar.pack()
        


    auto.mainloop()


# Función principal para programar citas
#E: Contador de citas
#S: Nada, llama a la funcion vehiculo para asignar el tipod e vehivulo y a la funcion validaciones_citas para gaurdar la cita
def programar(cont_citas):
    global cita_actual, op, nplaca, marca, modelo, propietario, tel, correo, direc, tipo
    cita_actual = []
    principal.withdraw()
    programar = Tk()
    programar.title("Programar citas")
    programar.geometry("500x600")
    programar.configure(bg="aquamarine")

    atras = Button(programar, text="<-", command=lambda: atr(programar, principal), width = 5, height=1, bg="sky blue", fg = "white")
    atras.pack(anchor="nw")

    cita = Label(programar, text=cont_citas, bg="aquamarine")
    cita.pack()

    # Variable para guardar la opción del tipo de cita
    op = StringVar()

    op1 = Radiobutton(programar, text="Primera vez", variable=op, value="Primera",
                      command=lambda: seleccionada("Primera"), bg = "aquamarine")
    op1.pack()

    op2 = Radiobutton(programar, text="Reinspeccion", variable=op, value="Reinspeccion",
                      command=lambda: seleccionada("Reinspeccion"), bg="aquamarine")
    op2.pack()

    placa = Label(programar, text="# Placa", bg="aquamarine")
    placa.pack()

    nplaca = Entry(programar)
    nplaca.pack()

    tipo = Button(programar, text="Tipo\nde\nvehiculo", command=vehiculo, bg="sky blue", fg = "white")
    tipo.pack()
    
    m = Label(programar, text="Marca del vehiculo", bg="aquamarine")
    m.pack()

    marca = Entry(programar)
    marca.pack()

    mod = Label(programar, text="Modelo del vehiculo", bg="aquamarine")
    mod.pack()

    modelo = Entry(programar)
    modelo.pack()

    prop = Label(programar, text="Propietario", bg="aquamarine")
    prop.pack()

    propietario = Entry(programar)
    propietario.pack()

    telefono=Label(programar, text="Telefono", bg="aquamarine")
    telefono.pack()

    tel=Entry(programar)
    tel.pack()

    corr = Label(programar, text="Correo electronico", bg="aquamarine")
    corr.pack()

    correo = Entry(programar)
    correo.pack()

    direccion = Label(programar, text="Direccion", bg="aquamarine")
    direccion.pack()

    direc = Entry(programar)
    direc.pack()

    fyhl = Label(programar, text="Fecha y hora", bg="aquamarine")
    fyhl.pack()

    manu = Button(programar, text="Manual", command=manual, bg="sky blue", fg = "white")
    manu.pack()

    auto = Button(programar, text="Automatico", command=automatica, bg="sky blue", fg = "white")
    auto.pack()
    
    guardar = Button(programar, text="Guardar", command=validaciones_citas, bg="sky blue", fg = "white")
    guardar.pack()

    programar.mainloop()

#---------------------------------------------------


#Confirmar la eliminacion de la cita
#Solo funciona para el mensaje
#Llama a la funcion verif_cancel
def si_no_cancel():
    respuesta = messagebox.askyesno("Cancelar cita", "Seguro que desea cancelar su cita?")
    if respuesta:
        verif_cancel(citas)

#Cancelacion de cita
#E: Arbol de citas
#S: Nada, puede mostrar mensajes de error o eliminar la cita llamando a eliminar de la clase Arbol
def verif_cancel(citas):
    global v
    cita = citas.buscar(int(n_cita.get()), str(pla.get()))
    if cita is not None:
        pass
    else:
        messagebox.showwarning("Error", "Cita no registrada")
        return 
    if n_cita.get() == str(cita.datos[0]) and pla.get() == str(cita.datos[8]) and "PENDIENTE" == str(cita.datos[1]):
        messagebox.showinfo("Eliminada", "Se ha cancelado su cita")
        
        citas.eliminar(cita)



#Menu de cancelacion de cita
#Llama a la funcion de verificacion si_no_cancel
def cancel_cit():
    cancelar = Tk()
    cancelar.title("Cancelar cita")
    cancelar.geometry("500x600")
    cancelar.configure(bg="aquamarine")

    global n_cita, pla, val_cit, v

    val_cit = asignar_contador(citas.raiz)
    v = 0

    atras = Button(cancelar, text="<-", command=lambda: atr(cancelar, principal), width = 5, height=1, bg="sky blue", fg = "white")
    atras.pack(anchor="nw")

    ncita = Label(cancelar, text="# de cita", bg="aquamarine")
    ncita.pack()

    n_cita = Entry(cancelar)
    n_cita.pack()

    placa = Label(cancelar, text="# Placa", bg="aquamarine")
    placa.pack()

    pla = Entry(cancelar)
    pla.pack()

    can = Button(cancelar, text="CANCELAR", command=si_no_cancel, bg="sky blue", fg = "white")
    can.pack()

    cancelar.mainloop()

#---------------------------------------------------------------
#Funcion para buscar en la cola de espera
#E: Cola, tupla con el numero de cita y la placa
#S: Valor booleano
def bus_cola(cola , i):
    for j in range(cola.qsize()):
        if i in cola.queue[j]:
            return True
    return False

#Validaciones para consultar la cita
#E: Ventana ing y cola de espera
#S: Nada, muestra datos
def val_consul(ing, cola):

    #Sacar la cita del arbol
    cita_a_revisar = citas.buscar(int(n_ing.get()), str(p_ing.get()))

    #Asignar la hora a variables para su validacion
    m = cita_a_revisar.datos[3]
    d = cita_a_revisar.datos[2]
    h = cita_a_revisar.datos[5]

    fecha_actual = datetime.datetime.now()
    m_a = fecha_actual.month
    d_a = fecha_actual.day
    h_a = fecha_actual.strftime("%I")

    if cita_a_revisar.datos[1] == "PENDIENTE":
        if bus_cola(cola, [int(n_ing.get()), int(p_ing.get())]):
            messagebox.showwarning("Error", "Su vehiculo ya esta\nen la cola de revision")
        else:

            with open("configuracion.dat", "r") as archivo:
                datos = archivo.read()
                elementos = json.loads(datos)
            archivo.close()
            t_vehi=cita_a_revisar.datos[9]

            tarifas = elementos[7]

            #Se asigna la tarifa del vehiculo
            for i in t_vehiculos:
                if t_vehi == i:
                    tarifa = tarifas[t_vehiculos.index(i)]

            imp = elementos[6]
            
            impuesto = imp / 100
            total = tarifa + (tarifa * impuesto)

            marc = Label(ing, text="Marca: " + cita_a_revisar.datos[10], bg="aquamarine")
            marc.pack()

            mod = Label(ing, text= "Modelo: " + cita_a_revisar.datos[11], bg="aquamarine")
            mod.pack()

            prop = Label(ing, text="Propietario: " + cita_a_revisar.datos[12], bg="aquamarine")
            prop.pack()

            costo = Label(ing, text="Costo con IVA: " + str(total), bg="aquamarine")
            costo.pack()

            if int(h_a)-2 < h <= int(h_a):
                if m == m_a and d == d_a:
                    cola_menor = float('inf')
                    p_menor = None
                    for i in range(cola.qsize()):
                        elemento = cola.get()
                        cant = len(elemento) 

                        if cant < cola_menor:
                            cola_menor = cant
                            p_menor = i
                        cola.put(elemento)
                    
                    if p_menor !=None :
                        for i in range(cola.qsize()):
                            elemento = cola.get()
                            if i ==p_menor:
                                elemento.append([int(p_ing.get())])
                            cola.put(elemento)
                else:
                    messagebox.showwarning("Error", "Su cita no es para hoy")
                    
            else:
                messagebox.showwarning("Error", "Falta mas de una hora para su cita")
                
    else:
        messagebox.showwarning("Error", "La cita no esta pendiente")
        
#Ventana de ingreso de vehiculos
#E: Usa variables globales
#S: Llama a la funcion val_consul
def ingre():
    ing = Tk()
    ing.title("Ingreso de vehiculos")
    ing.geometry("500x600")
    ing.configure(bg="aquamarine")

    global n_ing, p_ing
    atras = Button(ing, text="<-", command=lambda: atr(ing, principal), width = 5, height=1, bg="sky blue", fg = "white")
    atras.pack(anchor="nw")

    num_cita = Label(ing, text="# de cita", bg="aquamarine")
    num_cita.pack()

    n_ing = Entry(ing)
    n_ing.pack()
   

    placa = Label(ing, text="# Placa", bg="aquamarine")
    placa.pack()

    p_ing = Entry(ing)
    p_ing.pack()
 

    consul = Button(ing, text="Consultar", command=lambda:val_consul(ing, cola), bg="sky blue", fg = "white")
    consul.pack()

    ing.mainloop()

#------------------------------------------------
#Envia los correos con los PDF
#Correo receptor, valor booleano
#Envia los datos
def env_res(receptor, val_co):
    remite = "pruebap3jfgc@gmail.com"
    contraseña = config("contra")

    mensaje = MIMEMultipart()
    mensaje['From'] = remite
    mensaje['To'] = receptor
    mensaje['Subject'] = "Resultado de revisión"

    if val_co:
        pdfs = ["C:/Users/Lenovo/Desktop/Certificado.pdf", "C:/Users/Lenovo/Desktop/Resultado de la revision.pdf"]
    else:
        pdfs = ["C:/Users/Lenovo/Desktop/Resultado de la revision.pdf"]

    for pdf in pdfs:
        adjunto = MIMEBase('application', 'octet-stream')
        adjunto.set_payload(open(pdf, 'rb').read())
        encoders.encode_base64(adjunto)
        adjunto.add_header('Content-Disposition', f'attachment; filename="{pdf}"')
        mensaje.attach(adjunto)

    contenido_mensaje = "Subject: Resultado de su cita\n\n"
    contenido_texto = MIMEText(contenido_mensaje, 'plain')
    mensaje.attach(contenido_texto)

    servidor_smtp = smtplib.SMTP('smtp.gmail.com', 587)
    servidor_smtp.starttls()
    servidor_smtp.login(remite, contraseña)
    servidor_smtp.sendmail(remite, receptor, mensaje.as_string())
    servidor_smtp.quit()

#Actualiza el tablero de revision
#Funciona a medias
def actualizar():
    global val, com
    val = True
    fila = ["Linea", "Puesto 1", "Puesto 2", "Puesto 3", "Puesto 4", "Puesto 5"]
    for i, texto in enumerate(fila):
        tex = Label(tablero_v, text=texto, width=8, height=2, relief=RIDGE)
        tex.grid(row=0, column=i)
    
    for i in range(cola.qsize()):
        num = Label(tablero_v, text=i+1, width=8, height=2, relief=RIDGE)
        num.grid(row=i+1, column=0)

    for i in range(revision.qsize()):
        placa = revision.queue[i]
        for j in range(5):
            if val:
                tex2 = Label(tablero_v, text=placa, width=8, height=2, relief=RIDGE)
                val = False
            else:
                tex2 = Label(tablero_v, text="", width=8, height=2, relief=RIDGE)
            tex2.grid(row=i+1, column=j+1)
        val = True

    com = Entry(tablero_v, width=10)
    com.grid(row=7, columnspan=6)
    comando = Button(tablero_v, text="actualizar", command=opcion, bg="sky blue", fg="white")
    comando.grid(row=8, columnspan=6)





#Lee el codigo ingresado para realizar alguna accion
def opcion():
    global placa_tab, cola
    n = 1
    val_co = False
    dato = list(com.get())

    comando = dato[0]

    if comando == "T":
        
        placa_list = dato[1:]
        placa_tab = "".join(str(i) for i in placa_list)
    
        cola2 = cola

        dato = cola2.get()

        if int(placa_tab) in dato:
            cola = cola2
            revision.put([placa_tab])
            actualizar()

    if comando == "E":
        placa_list = dato[1:9]
        placa_tab = "".join(str(i) for i in placa_list)
        codfalla_list = dato[9:]
        codfalla = int(codfalla_list[0])

        if int(codfalla) in dic_fallas:
            ele = revision.get()
            ele.append(codfalla)
            revision.put(ele)
            actualizar()

    if comando == "F":
        with open("configuracion.dat", "r") as archivo:
                datos = archivo.read()
                elementos = json.loads(datos)
        archivo.close()
        
        placa_list = dato[1:]
        placa_tab = "".join(str(i) for i in placa_list)
        
        cita = citas.buscar(n, placa_tab)
        citan = cita.datos
        if revision.qsize()-1 > elementos[6]:
            citan[1] = "SACAR DE CIRCULACION"
            citas.reemplazar(cita, citan)
        
        elif 0 < revision.qsize()-1 < elementos[6]:
            citan[1] = "REINSPECCION"
            citas.reemplazar(cita, citan)
        
        elif revision.qsize()-1 == 0:
            fecha = datetime.datetime.now()
            year = fecha.year
            mes = fecha.month
            dia = fecha.day

            #Certificado de transito
            val_co = True
            citan[1] = "APROBADA"
            citas.reemplazar(cita, citan)
            cert = canvas.Canvas("C:/Users/Lenovo/Desktop/Certificado.pdf")
            cert.drawString(260, 800, "Certificado de transito")
            cert.drawString(70, 780, "Placa: "+str(citan[8]))
            cert.drawString(70, 760, "Marca: " + str(citan[10]))
            cert.drawString(70, 740, "Tipo de cita: "+ str(citan[7]))
            cert.drawString(70, 720, "Dueño: "+ str(citan[12]))
            cert.drawString(70, 700, "Vigencia desde el "+str(dia)+"-"+str(mes)+"-"+str(year)+" hasta el "+str(dia)+"-"+str(mes)+"-"+str(year+1))
            cert.save()

        #Resultado de la revision
        pdf = canvas.Canvas("C:/Users/Lenovo/Desktop/Resultado de la revision.pdf")
        pdf.drawString(260, 800, "Resultado de la revision")
        pdf.drawString(70, 780, "Numero de cita: " + str(citan[0]))
        pdf.drawString(70, 760, "Estado: " + str(citan[1]))
        pdf.drawString(70, 740, "Fecha: " + str(citan[3])+"-"+str(citan[4])+"-"+str(citan[5]))
        pdf.drawString(70, 720, "Hora: "+ str(citan[5])+":"+str(citan[6]))
        pdf.drawString(70, 700, "Tipo de cita: "+ str(citan[7]))
        pdf.drawString(70, 680, "Placa: "+str(citan[8]))
        pdf.drawString(70, 660, "Tipo de vehiculo: " + str(citan[9]))
        pdf.drawString(70, 640, "Marca: " + str(citan[10]))
        pdf.drawString(70, 620, "Modelo: " + str(citan[11]))
        pdf.drawString(70, 600, "Dueño: "+ str(citan[12]))
        pdf.drawString(70, 580, "Telefono: "+ str(citan[13]))
        pdf.drawString(70, 560, "Correo: " + str(citan[14]))
        pdf.drawString(70, 540, "Direccion: " + str(citan[15]))
        pdf.save()

        env_res(str(citan[14]), val_co)

#Tablero de revision
#E: Usa variables globales
#Llama a la funcion opcion
def tablero():

    global com, tablero_v
    tablero_v = Tk()
    tablero_v.title("Tablero de revision")
    tablero_v.geometry("500x600")
    tablero_v.configure(bg="aquamarine")


    fila = ["Linea", "Puesto 1", "Puesto 2", "Puesto 3", "Puesto 4", "Puesto 5"]
    for i, texto in enumerate(fila):
        tex = Label(tablero_v, text=texto, width=8, height=2, relief=RIDGE)
        tex.grid(row=0, column=i)
    
    for i in range(cola.qsize()):
        num = Label(tablero_v, text = i+1, width=8, height=2, relief=RIDGE)
        num.grid(row=i+1, column=0)

    lista = []
    for i in range(cola.qsize()):
        filas = []
        for j in range(cola.qsize()-1):
            tex2 = Label(tablero_v, text="", width = 8, height = 2, relief=RIDGE)
            tex2.grid(row=i+1, column=j+1)
            filas.append(tex2)
        lista.append(filas)

    com = Entry(tablero_v, width = 10)
    com.grid(row=7, columnspan=6)
    comando = Button(tablero_v, text="actualizar", command=opcion, bg="sky blue", fg = "white")
    comando.grid(row=8, columnspan=6)

    tablero_v.mainloop()

#-------------------------------------------
#Agregar fallas
#Diccionario de fallas
#Diccionario actualizado
def agregar_falla(dic_fallas):
    if len(afa.get()) == 0 or len(des.get()) == 0:
        messagebox.showerror("Error", "Debe llenar todos los espacios")
    else:


        for i, j in enumerate(dic_fallas):
            if i == int(afa.get()) or j == des.get():
                messagebox.showerror("Ya existe", "Esta falla ya estaba en el sistema")
                return
        
        dic_fallas[int(afa.get())] = (des.get(), a_gra.get())
        messagebox.showinfo("Agregada", "Falla agregada con exito!")

#Menu de agregar fallas
#Diccionario de fallas
#Llama a la funcion agregar_falla
def agg_fallas(dic_fallas):
    afallas = Tk()
    afallas.title("agregar fallas")
    afallas.geometry("300x300")
    afallas.configure(bg="aquamarine")

    global afa, des, a_gra

    atras = Button(afallas, text="<-", command=lambda: atr(afallas, fallas), width = 5, height=1, bg="sky blue", fg = "white")
    atras.pack(anchor="nw")

    num = Label(afallas, text="Ingrese el codigo de la falla", bg="aquamarine")
    num.pack()
    
    afa = Entry(afallas)
    afa.pack()

    descrip = Label(afallas, text="Ingrese la descripcion de la falla", bg="aquamarine")
    descrip.pack()

    des = Entry(afallas)
    des.pack()

    grado = Label(afallas, text="Nivel de la falla", bg="aquamarine")
    grado.pack()

    a_gra = Entry(afallas)
    a_gra.pack()

    aceptar = Button(afallas, text="Agregar", command=lambda:agregar_falla(dic_fallas), bg="sky blue", fg = "white")
    aceptar.pack()

    afallas.mainloop()   


#Consultar fallas
#Diccionario de fallas
#Informacion
def consultar_falla(dic_fallas):
    if len(cfa.get()) == 0:
        messagebox.showerror("Error", "Debe llenar todos los espacios")
    else:

        for i in dic_fallas:
            if i == int(cfa.get()):
                inf = Label(cfallas, text=dic_fallas[i])
                inf.pack()
                return   
        messagebox.showerror("No existe", "Esta falla no existe en el sistema")
        return
    
#Menu consultar fallas
#Diccionario de fallas
#Informacion
def cons_fallas(dic_fallas):
    global cfa, cfallas

    cfallas = Tk()
    cfallas.title("Consultar fallas")
    cfallas.geometry("300x300")
    cfallas.configure(bg="aquamarine")

    atras = Button(cfallas, text="<-", command=lambda: atr(cfallas, fallas), width = 5, height=1, bg="sky blue", fg = "white")
    atras.pack(anchor="nw")

    num = Label(cfallas, text="Ingrese el codigo de la falla", bg="aquamarine")
    num.pack()

    cfa = Entry(cfallas)
    cfa.pack()

    aceptar = Button(cfallas, text="Consultar", command=lambda:consultar_falla(dic_fallas), bg="sky blue", fg = "white")
    aceptar.pack()

    cfallas.mainloop()    

#Modificar fallas
#Diccionario de fallas y cola de revision
#Diccionario actualizado
def modificar_falla(dic_fallas, revision):
    if len(mfa.get()) == 0:
        messagebox.showerror("Error", "Debe llenar todos los espacios")
    else:
        val = True
        resp = messagebox.askyesno("Eliminar falla", "Seguro que desea eliminar esta falla?")
        if resp:
            revision2 = revision
            while not revision.empty():
                elemento=revision2.get()
                if elemento == int(mfa.get()):
                    messagebox.showinfo("No es posible", "No se puede modificar ya\nque esta falla se encuentra en\nla cola de revision")
                    return
            for i in dic_fallas:
                if int(mfa.get()) == i:
                    dic_fallas[int(mfa.get())] = (desc.get(), gra.get())
                    messagebox.showinfo("Modificada", "Modificada")
                    val=True
                    break
                else:
                    val = False
            if val == False:
                messagebox.showerror("Error", "Falla no existe en la lista de fallas")
        else:
            return

#Menu modificar fallas
#Diccionario de fallas, cola de revision
#Llama a la funcion modificar_falla
def mod_fallas(dic_fallas, revision):
    mfallas = Tk()
    mfallas.title("Modificar fallas")
    mfallas.geometry("300x300")
    mfallas.configure(bg="aquamarine")

    global mfa, desc, gra

    atras = Button(mfallas, text="<-", command=lambda: atr(mfallas, fallas), width = 5, height=1, bg="sky blue", fg = "white")
    atras.pack(anchor="nw")

    num = Label(mfallas, text="Ingrese el codigo de la falla", bg="aquamarine")
    num.pack()

    mfa = Entry(mfallas)
    mfa.pack()

    descripcion = Label(mfallas, text="Ingrese la nueva descripcion de falla", bg="aquamarine")
    descripcion.pack()

    desc = Entry(mfallas)
    desc.pack()

    grado = Label(mfallas, text="Ingrese el grado de la falla", bg="aquamarine")
    grado.pack()

    gra = Entry(mfallas)
    gra.pack()

    aceptar = Button(mfallas, text="Modificar", command=lambda:modificar_falla(dic_fallas, revision), bg="sky blue", fg = "white")
    aceptar.pack()

    mfallas.mainloop()

#Eliminar fallas
#Diccionario de fallas, cola de revision
#Diccionario actualizado
def eliminar_falla(dic_fallas, revision):
    if len(fa.get()) == 0:
        messagebox.showerror("Error", "Debe llenar todos los espacios")
    else:
        val = True
        resp = messagebox.askyesno("Eliminar falla", "Seguro que desea eliminar esta falla?")
        if resp:
            revision2 = revision
            while not revision.empty():
                elemento=revision2.get()
                if elemento == int(fa.get()):
                    messagebox.showinfo("No es posible", "No se puede eliminar ya\nque esta falla se encuentra en\nla cola de revision")
                    return
            for i in dic_fallas:
                if int(fa.get()) == i:
                    del dic_fallas[int(fa.get())]
                    messagebox.showinfo("Eliminada", "Falla eliminada")
                else:
                    val = False
            if val == False:
                messagebox.showerror("Error", "Falla no existe en la lista de fallas")
        else:
            return

#Menu elimianar fallas
#Diccionario de fallas, cola de revision
#Llama a la funcion elimianr_falla
def el_fallas(dic_fallas, revision):
    efallas = Tk()
    efallas.title("Eliminar fallas")
    efallas.geometry("300x300")
    efallas.configure(bg="aquamarine")

    global fa

    atras = Button(efallas, text="<-", command=lambda: atr(efallas, fallas), width = 5, height=1, bg="sky blue", fg = "white")
    atras.pack(anchor="nw")

    num = Label(efallas, text="Ingrese el codigo de la falla", bg="aquamarine")
    num.pack()

    fa = Entry(efallas)
    fa.pack()

    aceptar = Button(efallas, text="Eliminar", command=lambda:eliminar_falla(dic_fallas, revision), bg="sky blue", fg = "white")
    aceptar.pack()

    efallas.mainloop()


#Menu CRUD fallas
#Diccionario de fallas, cola de revision
#Llama a las funciones CRUD
def conf_fallas(dic_fallas, revision):
    global fallas
    fallas = Tk()
    fallas.title("Lista de fallas")
    fallas.geometry("300x300")
    fallas.configure(bg="aquamarine")


    atras = Button(fallas, text="<-", command=lambda: atr(fallas, principal), width = 5, height=1, bg="sky blue", fg = "white")
    atras.pack(anchor="nw")

    titulo = Label(fallas, text="MENU CRUD FALLAS", bg="sky blue")
    titulo.pack

    agg = Button(fallas, text="Agregar falla", command=lambda:agg_fallas(dic_fallas), bg="sky blue", fg="white")
    agg.pack()

    cons = Button(fallas, text="Consultar falla", command=lambda:cons_fallas(dic_fallas), bg="sky blue", fg="white")
    cons.pack() 

    mod = Button(fallas, text="Modificar falla", command=lambda:mod_fallas(dic_fallas, revision), bg="sky blue", fg="white")
    mod.pack()

    elimi = Button(fallas, text="Eliminar falla", command=lambda:el_fallas(dic_fallas, revision), bg="sky blue", fg="white")
    elimi.pack()

    fallas.mainloop()

#Ayuda
#Despliega el manual de usuario
def ayuda():
    os.system("C:/Users/Lenovo/Desktop/manual_de_usuario_RETEVE.pdf")

#Acerca de
#Muestra informacion
def acerca_de():
    messagebox.showinfo("Acerca de...", "Nombre: Riteve\nVersion: 1.6\nFecha: 19-06-2023 \nAutor: Joshua Guerra Castillo") #Tengo que poner la ultima evrsion y la ultima fecha

#Salir
#Cierra el programa
def salir():
    principal.quit()

#Datos globales
global configdat, t_vehiculos, cont_citas, cola, espera, fallas, horas_tomadas

#diccionario de fallas inicial 
dic_fallas = {1:("Luces", "leve"),
              2:("Amortiguadores", "leve"),
              3:("Frenado", "grave"),
              4:("Holguras", "grave"),
              5:("Emisiones", "grave")}



#Datos de configuracion iniciales
#[lineas, [horas], min, dias para reispeccion, fallas, meses, IVA, [tarifas]]
dat = [6, [1, 24], 20, 30, 4, 5, 13.0, [10920, 14380, 14380, 11785, 14380, 7195, 14380, 6625]]

#Inicializacion de archivo y guardado de datos
configdat=open("configuracion.dat", "w")
configdat.write(str(dat))
configdat.close()

#Cola de espera
cola = Queue()
cola.put([12345678])
cola.put([12345679])
cola.put([10293847])
cola.put([])
cola.put([])
cola.put([])

#Cola de revision
revision = Queue() 


#Lista de tipos de vehiculos
t_vehiculos = ["Particular y de carga liviana (<=3000kg)",
        "Particular y de carga liviana (>3500 y <8000kg)",
        "Carga pesada y cabezales (>=8000kg)",
        "Taxis",
        "Autobuses, buses y microbuses",
        "Motocicletas",
        "Equipo especial de obras",
        "Equipo especial agricola"]

#Arbol de citas
#[#CITA, ESTADO, DIA, MES, AÑO, HORA, MINUTOS, TCITA, PLACA, TVEHICULO, MARCA, MODELO, DUEÑO, TEL, CORREO, DIRECCION]
citas = Arbol([1, "PENDIENTE", 21, 6, 2023, 12, 50, "Primera", "12345678", "Taxis", "Mitsubishi", "Mirage LS 1997", "Joshua", "12345678901234567890", "joshuagc98@gmail.com", "al norte a la derecha"])
citas.agregar([2, "PENDIENTE", 16, 6, 2023, 7, 40, "Primera", "12345679", "Taxis", "FORD", "MUSTANG", "PEDRO", "12345678901234567890", "joshuagc98@gmail.com", "alnortealaizquierda"])
citas.agregar([3, "PENDIENTE", 17, 6, 2023, 2, 40, "Primera", "87654321", "Taxis", "McLaren", "P1", "Oracio", "12345678900987654321", "joshuagc98@gmail.com", "norestesuroestrecarmen"])

#Lista para llevar un control de las horas tomadas
horas_tomadas = []
#Contador de citas
def asignar_contador(nodo):
    if nodo is None:
        return 1
    return max(asignar_contador(nodo.izquierda), asignar_contador(nodo.derecha)) + 1

cont_citas = asignar_contador(citas.raiz)





#Ventana principal
principal = Tk()
principal.title("Menu")
principal.geometry("430x600")
principal.configure(bg="aquamarine")

#Botones de ventana principal
prog = Button(principal, text="PROGRAMAR\nCITA", command=lambda:programar(cont_citas), width=10, height=3, bg="sky blue")
prog.place(x=100, y=100)

cancel = Button(principal, text="CANCELAR\nCITA", command= cancel_cit, width=10, height=3, bg="sky blue")
cancel.place(x=300, y=100)

ingreso = Button(principal, text="INGRESO\nDE\nVEHICULOS", command=ingre, width=10, height=3, bg="sky blue")
ingreso.place(x=100, y=200)

tabl = Button(principal, text="TABLERO\nDE\nREVISION", command = tablero, width=10, height=3, bg="sky blue")
tabl.place(x=300, y=200)

bfallas = Button(principal, text="REVISION\nDE\nFALLAS", command=lambda:conf_fallas(dic_fallas, revision), width=10, height=3, bg="sky blue")
bfallas.place(x=100, y=300)

configur = Button(principal, text="CONFIGURACION\nDEL\nSISTEMA", command=lambda:configuracion(principal, configdat), width=13, height=3, bg="sky blue")
configur.place(x=290, y=300)

ayuda_b = Button(principal, text="AYUDA", command = ayuda, width=10, height=3, bg="sky blue")
ayuda_b.place(x=100, y=400)

acerca = Button(principal, text="ACERCA DE", command = acerca_de, width=10, height=3, bg="sky blue")
acerca.place(x=300, y=400)

salir_b = Button(principal, text="Salir", command = salir, width=10, height=3, bg="sky blue")
salir_b.place(x=200, y=470)

principal.mainloop()





