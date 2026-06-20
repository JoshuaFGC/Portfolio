from tkinter import *
from tkinter import messagebox
import pickle
import os
import random 

jugadas = []
cuadricula_labels=[]
cuadricula = []
l_rehacer = []

def eliminar_datos(seccion):
    with open("kakuro2023configuracion.dat", "r") as archivo:
        datos = archivo.readlines()

    n_datos = [dato for dato in datos if dato.strip() != seccion]

    with open("kakuro2023configuracion.dat", "w") as archivo:
        archivo.writelines(n_datos)

def faciles():
    eliminar_datos("f")

def medios():
    eliminar_datos("m")

def dificiles():
    eliminar_datos("d")

def expertos():
    eliminar_datos("e")

def cron():
    eliminar_datos("cron")

def timer():
    eliminar_datos("timer")



def jugar(config):
    global tab
    config.withdraw()
    detener = False
    
    
    def detener_cron():
        ventana.after_cancel(temp)

    def inicio():
        iniciar.configure(state='disabled')
    #crear una variable que sea pasada a por cada funcion y dependeindo del valor al inicar sea facil, medio u otro
        if len(nomb.get())<1:
            messagebox.showwarning("Error", "Debe ingresar un nombre de jugador")
            iniciar.configure(state='normal')
            return
        
        elif len(nomb.get()) > 40:
            messagebox.showwarning("Error", "El nombre de jugador debe tener menos de 40 caracteres")
            iniciar.configure(state='normal')
            return
        else:
            nomb.configure(state='disabled')
        
        with open("kakuro2023configuracion.dat", "r") as archivo:
            contenido = archivo.read()
            if "cron" in contenido:
                global tiempo
                tiempo=0
                
                
                

                def cron():
                    global tiempo, temp
                    tiempo += 1

                    horas = tiempo // 3600
                    minutos = (tiempo % 3600) // 60
                    segundos = tiempo % 60

                    
                    h.config(text="Horas")
                    m.config(text="Minutos")
                    s.config(text="Segundos")
                    thoras.config(text="{:02d}".format(horas))
                    tminutos.config(text="{:02d}".format(minutos))
                    tsegundos.config(text="{:02d}".format(segundos))
                    temp = ventana.after(1000, cron)
                cron()
            elif "timer" in contenido:
                pass


            
        
                
            



    #Cambia el color de los botones del 1 al 9
    def cambiar_color(numero):
        for i, boton in enumerate(botones):
            if i + 1 == numero:
                boton.configure(bg="sky blue")
            else:
                boton.configure(background="SystemButtonFace")

    #Sirve para seleccionar los botones del 1 al 9
    def seleccionar(numero, pila_movimientos):
        global numero_seleccionado
        numero_seleccionado = numero
        cambiar_color(numero)

    #Funcion para deshacer una jugada
    def deshacer():
        if len(jugadas) != 0:
            u_num = jugadas.pop()
            tablero[u_num[0]][u_num[1]].config(text="")
            u_num2 = pila_movimientos.pop()
            l_rehacer.append([u_num, u_num2])
        else:
            messagebox.showwarning("Alerta","No se puede deshacer\nninguna jugada")

    #Funcion para rehacer una jugada
    def rehacer():
        global jugadas, l_rehacer, numero_seleccionado
        if len(l_rehacer)!=0:
            deshacer = l_rehacer.pop()
            posicion = deshacer[0]
            num = deshacer[1]
            
            if validar_numero(num, posicion[1], posicion[0]):
                tablero[posicion[0]][posicion[1]].config(text = num)
                pila_movimientos.append(num)
                jugadas.append(posicion)
            
        else:
            messagebox.showwarning("Alerta", "No se puede rehacer\nninguna jugada")
           
    #Funciones auxiliares para colocar_numero
    def obtener_posicion(boton):
        for fila in range(len(tablero)):
            if boton in tablero[fila]:
                columna = tablero[fila].index(boton)
                return fila, columna

    def validar_numero(numero, fila, columna):
        # Validar en la fila
        for c in range(len(tablero[fila])):
            if c != columna and tablero[fila][c].cget("text") == numero:
                messagebox.showinfo("Error", "El número se repite en la columna.")
                return False
        
        # Validar en la columna
        for f in range(len(tablero)):
            if f != fila and tablero[f][columna].cget("text") == numero:
                messagebox.showinfo("Error", "El número se repite en la fila.")
                return False
        return True
        


    #Coloca los numeros en las casillas seleccioandas
    def colocar_numero(event):
        global numero_seleccionado
        if numero_seleccionado is not None:
            boton = event.widget
            fila, columna = obtener_posicion(boton)
            posicion = obtener_posicion(boton)
            jugadas.append(posicion)
            pila_movimientos.append(numero_seleccionado)
            if validar_numero(numero_seleccionado, fila, columna):
                texto_actual = boton.cget("text")
                if not texto_actual:
                    boton.config(text=numero_seleccionado)
            
    #Funcion para borrar una casilla faltaaaaaa
    def borrar_casilla(event):
        button = event.widget
        button.config(text="")



    def guardar_j():
        global jugadas, tab
        
        archivo = "kakuro2023juegoactual.dat"
        datos = [jugadas, tab]

        try:
            with open(archivo, "wb") as f:
                pickle.dump(datos, f)
        except IOError:
            messagebox.showerror("Error al guardar")
            return

        op = messagebox.askyesno("GUARDAR", "¿Desea seguir jugando?")
        if op:
            pass
        else:
            ventana.destroy()
            config.deiconify()
            return

    def cargar_j():
        
        detener_cron()
        
        archivo = "kakuro2023juegoactual.dat"

        if os.path.isfile(archivo):
            try:
                with open(archivo, "rb") as f:
                    datos = pickle.load(f)
                    jugadas = datos[0]
                    tablero = datos[1]


                    for i in tablero:
                        f = i[2]
                        col = i[3]
                        digit = i[1]

                        boton = tablero[f-1][col-1]
                        contenido  = boton.cget("text")

                        if dig == 0:
                            boton.config(text="", state="disabled")
                        else:
                            if contenido:
                                if i[0] == 1:
                                    cont2 = r"\     " + str(digit) + " \n" + r"\ " + "  \n  " + contenido + "   \ "
                                elif i[0] == 2:
                                    cont2 = r"\ " + "\n   \ " + contenido + "\n " + str(digit) + "\ " + contenido
                                else:
                                    cont2 = str(digit)
                            else:
                                cont2 = str(digit)

                            boton.config(text=cont2, state="disabled")


                    for jugada in jugadas:
                        num = jugada[0]
                        coordenadas = jugada[1]
                        tablero[coordenadas[0]][coordenadas[1]].config(text=num)
            except (IOError, pickle.UnpicklingError) as error:
                messagebox.showerror("Error", "Error al cargar el juego: " + str(error))
        else:
            messagebox.showerror("Error", "No se encontró el archivo del juego")



    def borrar():
        op = messagebox.askyesno("Alerta", "¿Seguro que desea borrar el juego?")
        if not op:
            return
        else:
            for fila in tablero:
                for casilla in fila:
                    if casilla.cget("state") != "disabled":
                        casilla.config(text="", state="normal")
            iniciar.config(state="normal")


    def terminar_j():
        detener_cron()
        estado = iniciar.cget("state")
        if estado == "normal":
            messagebox.showwarning("Alerta", "No se puede terminar\nun juego no iniciado")
            return
        else:
            op = messagebox.askyesno("Alerta", "¿Seguro que desea terminar el juego?")
            if not op:
                return
            else:
                jugar(config)
    
    #Funcion utilizada por top10 para acomodar los tops en la ventana
    def imprimir_top(top, ventana, col, row):
        
        
        
        for i, t in enumerate(top):
            f = row + i
            colum = col
            num = t[0]
            nomb = t[1]
            tiem = t[2]
            numero = Label(ventana, text=num, bg = "bisque")
            numero.grid(row=f, column=colum, padx=10, pady=1, sticky="w")
            nombre = Label(ventana, text=nomb, bg = "bisque")
            nombre.grid(row=f, column=colum + 1, padx=10, pady=1, sticky="w")
            tiempo = Label(ventana, text=tiem, bg = "bisque")
            tiempo.grid(row=f, column=colum + 2, padx=10, pady=1, sticky="w")

    #Top 10
    def top10():
        detener_cron()
        ventana.withdraw()
        top10 = Toplevel(ventana)
        top10.title("Top 10")
        top10.geometry("500x500")
        top10.configure(bg="bisque")

        topfacil = []

        topmedio = []

        topdificil = []

        topexperto = []

        facil = Label(top10, text="Facil", bg="bisque")
        facil.grid(row=1, column=0, columnspan=3, padx=10, pady=10)

        medio = Label(top10, text="Medio", bg="bisque")
        medio.grid(row=1, column=3, columnspan=3, padx=10, pady=10)

        dificil = Label(top10, text="Dificil", bg="bisque")
        dificil.grid(row=1, column=6, columnspan=3, padx=10, pady=10)

        experto = Label(top10, text="Experto", bg="bisque")
        experto.grid(row=1, column=9, columnspan=3, padx=10, pady=10)

        atras = Button(top10, text="<-", command=lambda: atr(top10, ventana), width = 5, height=1, bg="sky blue", fg = "white")
        atras.grid(row= 0, column=0)
        if len(topfacil) !=0:
            imprimir_top(topfacil, top10, 0, 1)
        if len(topmedio) !=0:
            imprimir_top(topmedio, top10, 3, 1)
        if len(topdificil) !=0:
            imprimir_top(topdificil, top10, 6, 1)
        if len(topexperto) !=0:
            imprimir_top(topexperto, top10, 9, 1)
        else:
            for i in range(4):
                
                nada = Label(top10, text="No hay datos     ", bg = "bisque")
                nada.grid(row=3, column=i+1)
                

   

    

    #Creacion del archivo con los parametros predeterminados

    archivo = open("kakuro2023configuracion.dat", "a")
    archivo.write("f")
    archivo.write("cron")


    pila_movimientos=[]
    ventana = Tk()
    ventana.title("Kakuro")
    ventana.geometry("800x800")
    ventana.configure(bg="bisque")

    nombre = Label(ventana, text="KAKURO", bg="bisque")
    nombre.grid(row=0, column=0)

    nombre_jugador = Label(ventana, text="Jugador: ", bg="bisque")
    nombre_jugador.place(x=400, y=10)
    nomb = Entry(ventana, width=30)
    nomb.place(x=450, y=10)




    tablero = []
    for i in range(9):
        sublista = []
        for j in range(9):
            casilla = Button(ventana, width=5, height= 2)
            casilla.place(x=i*46+10, y=j*46+30)
            casilla.bind("<Button-1>", colocar_numero)
            #casilla.configure(state="disabled")
            sublista.append(casilla)
        tablero.append(sublista)


    iniciar = Button(ventana, text="INICIAR\nJUEGO", command=inicio, width=10, height=2, bg="deep pink2")
    iniciar.place(x=5, y=450)

    deshacer_j = Button(ventana, text="DESHACER\nJUGADA", command=deshacer, width=10, height=2, bg="green1")
    deshacer_j.place(x=150, y=450)

    rehacer_j = Button(ventana, text="REHACER\nJUGADA", command= rehacer, width=10, height=2, bg="sky blue")
    rehacer_j.place(x=150, y= 500)

    borrar_c = Button(ventana, text="BORRAR\nCASILLA", command=lambda:borrar_casilla, width=10, height=2, bg="gray80")
    borrar_c.place(x=250, y=450)

    borrar_j = Button(ventana, text="BORRAR\nJUEGO", command = borrar, width=10, height=2, bg="dark turquoise")
    borrar_j.place(x=250, y=500)

    terminar = Button(ventana, text="TERMINAR\nJUEGO", command = terminar_j, width=10, height=2, bg="green3")
    terminar.place(x=250, y=560)

    top = Button(ventana, text="TOP\n10", command=top10, width=10, height=2, bg="yellow")
    top.place(x=350, y=450)

    guardar = Button(ventana, text="GUARDAR\nJUEGO", command = guardar_j, width=10, height=2, bg="orange")
    guardar.place(x=350, y=500)

    cargar = Button(ventana, text="CARGAR\nJUEGO", command= cargar_j, width=10, height=2, bg="saddle brown")
    cargar.place(x=350, y=560)




    botones = []
    for i in range(1, 10):
        boton = Button(ventana, text=str(i), command=lambda x=i: seleccionar(x, pila_movimientos), width=3)
        boton.place(x=460, y=i*26+20)
        botones.append(boton)



    

  
    with open("kakuro2023configuracion.dat", "r") as archivo:
            contenido = archivo.read()
            print(contenido)
            if "f" in contenido:
                m1 = (
                    ((2, 8, 6, 2, 3), (2, 6, 6, 3, 7), (2, 4, 2, 4, 2), (2, 29, 2, 5, 3),
                    (2, 24, 3, 5, 5), (2, 15, 2, 6, 5), (2, 7, 1, 7, 3), (2, 42, 1, 8, 7),
                    (2, 6, 3, 9, 3), (2, 10, 2, 6, 2), (2, 4, 3, 2, 2), (2, 6, 3, 5, 3),
                    (2, 28, 4, 2, 7), (2, 10, 5, 1, 2), (2, 12, 5, 4, 2), (2, 11, 5, 7, 2),
                    (2, 3, 6, 1, 2), (2, 4, 6, 4, 2), (2, 6, 6, 7, 2), (2, 28, 7, 1, 7),
                    (2, 7, 8, 2, 3), (2, 16, 8, 6, 2), (2, 3, 9, 2, 2), (1, 0, 1, 1, 0),
                    (1, 0, 1, 2, 0), (1, 0, 1, 3, 0), (1, 0, 1, 4, 0), (1, 0, 1, 5, 0),
                    (1, 0, 1, 6, 0), (1, 0, 1, 9, 0), (1, 0, 2, 1, 0), (1, 0, 2, 2, 0),
                    (1, 0, 2, 5, 0), (1, 0, 2, 9, 0), (1, 0, 3, 1, 0), (1, 0, 4, 1, 0),
                    (1, 0, 7, 9, 0), (1, 0, 8, 1, 0), (1, 0, 8, 9, 0), (1, 0, 9, 1, 0),
                    (1, 0, 9, 5, 0), (1, 0, 9, 6, 0), (1, 0, 9, 7, 0), (1, 0, 9, 8, 0),
                    (1, 0, 9, 9, 0)),

                        
                    ((2, 7, 4, 2, 3), (2, 29, 2, 3, 7), (2, 4, 2, 4, 2), (2, 6, 6, 4, 3),
                    (2, 24, 3, 5, 5), (2, 15, 2, 6, 5), (2, 7, 1, 7, 3), (2, 8, 6, 7, 2),
                    (2, 42, 1, 8, 7), (2, 6, 3, 9, 3), (1, 10, 2, 6, 2), (1, 4, 3, 2, 2),
                    (1, 6, 3, 5, 3), (1, 28, 4, 2, 7), (1, 10, 5, 1, 2), (1, 12, 5, 4, 2),
                    (1, 11, 5, 7, 2), (1, 3, 6, 1, 2), (1, 4, 6, 4, 2), (1, 6, 6, 7, 2),
                    (1, 28, 7, 1, 7), (1, 7, 8, 2, 3), (1, 16, 8, 6, 2), (1, 3, 9, 2, 2),
                    (1, 0, 1, 1, 0), (1, 0, 1, 2, 0), (1, 0, 1, 3, 0), (1, 0, 1, 4, 0),
                    (1, 0, 1, 5, 0), (1, 0, 1, 6, 0), (1, 0, 1, 9, 0), (1, 0, 2, 1, 0),
                    (1, 0, 2, 2, 0), (1, 0, 2, 5, 0), (1, 0, 2, 9, 0), (1, 0, 3, 1, 0),
                    (1, 0, 4, 1, 0), (1, 0, 7, 9, 0), (1, 0, 8, 1, 0), (1, 0, 8, 9, 0),
                    (1, 0, 9, 1, 0), (1, 0, 9, 5, 0), (1, 0, 9, 6, 0), (1, 0, 9, 7, 0),
                    (1, 0, 9, 8, 0), (1, 0, 9, 9, 0)),
                    
                    
                    ((2, 7, 4, 2, 3), (2, 29, 2, 3, 7), (2, 4, 2, 4, 2), (2, 6, 6, 4, 3),
                    (2, 24, 3, 5, 5), (2, 15, 2, 6, 5), (2, 7, 1, 7, 3), (2, 8, 6, 7, 2),
                    (2, 42, 1, 8, 7), (2, 6, 3, 9, 3), (1, 10, 2, 6, 2), (1, 4, 3, 2, 2),
                    (1, 6, 3, 5, 3), (1, 28, 4, 2, 7), (1, 10, 5, 1, 2), (1, 12, 5, 4, 2),
                    (1, 11, 5, 7, 2), (1, 3, 6, 1, 2), (1, 4, 6, 4, 2), (1, 6, 6, 7, 2),
                    (1, 28, 7, 1, 7), (1, 7, 8, 2, 3), (1, 16, 8, 6, 2), (1, 3, 9, 2, 2),
                    (1, 0, 1, 1, 0), (1, 0, 1, 2, 0), (1, 0, 1, 3, 0), (1, 0, 1, 4, 0),
                    (1, 0, 1, 5, 0), (1, 0, 1, 6, 0), (1, 0, 1, 9, 0), (1, 0, 2, 1, 0),
                    (1, 0, 2, 2, 0), (1, 0, 2, 5, 0), (1, 0, 2, 9, 0), (1, 0, 3, 1, 0),
                    (1, 0, 4, 1, 0), (1, 0, 7, 9, 0), (1, 0, 8, 1, 0), (1, 0, 8, 9, 0),
                    (1, 0, 9, 1, 0), (1, 0, 9, 5, 0), (1, 0, 9, 6, 0), (1, 0, 9, 7, 0),
                    (1, 0, 9, 8, 0), (1, 0, 9, 9, 0)))

                tab = random.choice(m1)

                



            

            elif "m" in contenido:
                m2 = (((2, 7, 4, 2, 3), (2, 29, 2, 3, 7), (2, 4, 2, 4, 2), (2, 6, 6, 4, 3),
                    (2, 24, 3, 5, 5), (2, 15, 2, 6, 5), (2, 7, 1, 7, 3), (2, 8, 6, 7, 2),
                    (2, 42, 1, 8, 7), (2, 6, 3, 9, 3), (1, 10, 2, 6, 2), (1, 4, 3, 2, 2),
                    (1, 6, 3, 5, 3), (1, 28, 4, 2, 7), (1, 10, 5, 1, 2), (1, 12, 5, 4, 2),
                    (1, 11, 5, 7, 2), (1, 3, 6, 1, 2), (1, 4, 6, 4, 2), (1, 6, 6, 7, 2),
                    (1, 28, 7, 1, 7), (1, 7, 8, 2, 3), (1, 16, 8, 6, 2), (1, 3, 9, 2, 2),
                    (1, 0, 1, 1, 0), (1, 0, 1, 2, 0), (1, 0, 1, 3, 0), (1, 0, 1, 4, 0),
                    (1, 0, 1, 5, 0), (1, 0, 1, 6, 0), (1, 0, 1, 9, 0), (1, 0, 2, 1, 0),
                    (1, 0, 2, 2, 0), (1, 0, 2, 5, 0), (1, 0, 2, 9, 0), (1, 0, 3, 1, 0),
                    (1, 0, 4, 1, 0), (1, 0, 7, 9, 0), (1, 0, 8, 1, 0), (1, 0, 8, 9, 0),
                    (1, 0, 9, 1, 0), (1, 0, 9, 5, 0), (1, 0, 9, 6, 0), (1, 0, 9, 7, 0),
                    (1, 0, 9, 8, 0), (1, 0, 9, 9, 0)),

                    ((2, 7, 4, 2, 3), (2, 29, 2, 3, 7), (2, 4, 2, 4, 2), (2, 6, 6, 4, 3),
                    (2, 24, 3, 5, 5), (2, 15, 2, 6, 5), (2, 7, 1, 7, 3), (2, 8, 6, 7, 2),
                    (2, 42, 1, 8, 7), (2, 6, 3, 9, 3), (1, 10, 2, 6, 2), (1, 4, 3, 2, 2),
                    (1, 6, 3, 5, 3), (1, 28, 4, 2, 7), (1, 10, 5, 1, 2), (1, 12, 5, 4, 2),
                    (1, 11, 5, 7, 2), (1, 3, 6, 1, 2), (1, 4, 6, 4, 2), (1, 6, 6, 7, 2),
                    (1, 28, 7, 1, 7), (1, 7, 8, 2, 3), (1, 16, 8, 6, 2), (1, 3, 9, 2, 2),
                    (1, 0, 1, 1, 0), (1, 0, 1, 2, 0), (1, 0, 1, 3, 0), (1, 0, 1, 4, 0),
                    (1, 0, 1, 5, 0), (1, 0, 1, 6, 0), (1, 0, 1, 9, 0), (1, 0, 2, 1, 0),
                    (1, 0, 2, 2, 0), (1, 0, 2, 5, 0), (1, 0, 2, 9, 0), (1, 0, 3, 1, 0),
                    (1, 0, 4, 1, 0), (1, 0, 7, 9, 0), (1, 0, 8, 1, 0), (1, 0, 8, 9, 0),
                    (1, 0, 9, 1, 0), (1, 0, 9, 5, 0), (1, 0, 9, 6, 0), (1, 0, 9, 7, 0),
                    (1, 0, 9, 8, 0), (1, 0, 9, 9, 0))
                    
                    ((2, 3, 4, 1, 3), (2, 14, 2, 2, 4), (2, 4, 1, 3, 2), (2, 6, 3, 3, 1),
                    (2, 11, 1, 4, 2), (2, 10, 1, 5, 2), (2, 10, 1, 6, 2), (2, 11, 3, 6, 2),
                    (2, 14, 2, 7, 4), (2, 15, 1, 8, 2), (2, 21, 3, 8, 6), (2, 22, 1, 9, 6),
                    (1, 5, 1, 1, 2), (1, 8, 2, 1, 4), (1, 10, 3, 1, 6), (1, 7, 1, 2, 3),
                    (1, 9, 3, 2, 5), (1, 5, 1, 3, 2), (1, 6, 2, 3, 3), (1, 6, 3, 3, 4),
                    (1, 6, 1, 4, 2), (1, 5, 1, 5, 2), (1, 3, 1, 6, 2), (1, 6, 1, 7, 2),
                    (1, 4, 3, 7, 4), (1, 7, 1, 8, 3), (1, 11, 3, 8, 5), (1, 12, 1, 9, 5),
                    (1, 0, 1, 1, 0), (1, 0, 1, 2, 0), (1, 0, 1, 3, 0), (1, 0, 1, 4, 0),
                    (1, 0, 1, 5, 0), (1, 0, 1, 6, 0), (1, 0, 1, 7, 0), (1, 0, 1, 8, 0),
                    (1, 0, 1, 9, 0), (1, 0, 2, 1, 0), (1, 0, 2, 2, 0), (1, 0, 2, 3, 0),
                    (1, 0, 2, 4, 0), (1, 0, 2, 5, 0), (1, 0, 2, 6, 0), (1, 0, 2, 7, 0),
                    (1, 0, 2, 8, 0), (1, 0, 2, 9, 0)))
                
                tab = random.choice(m2)
            elif "d" in contenido:
                m3 =(((2, 7, 4, 2, 3), (2, 29, 2, 3, 7), (2, 4, 2, 4, 2), (2, 6, 6, 4, 3),
                    (2, 24, 3, 5, 5), (2, 15, 2, 6, 5), (2, 7, 1, 7, 3), (2, 8, 6, 7, 2),
                    (2, 42, 1, 8, 7), (2, 6, 3, 9, 3), (1, 10, 2, 6, 2), (1, 4, 3, 2, 2),
                    (1, 6, 3, 5, 3), (1, 28, 4, 2, 7), (1, 10, 5, 1, 2), (1, 12, 5, 4, 2),
                    (1, 11, 5, 7, 2), (1, 3, 6, 1, 2), (1, 4, 6, 4, 2), (1, 6, 6, 7, 2),
                    (1, 28, 7, 1, 7), (1, 7, 8, 2, 3), (1, 16, 8, 6, 2), (1, 3, 9, 2, 2),
                    (1, 0, 1, 1, 0), (1, 0, 1, 2, 0), (1, 0, 1, 3, 0), (1, 0, 1, 4, 0),
                    (1, 0, 1, 5, 0), (1, 0, 1, 6, 0), (1, 0, 1, 9, 0), (1, 0, 2, 1, 0),
                    (1, 0, 2, 2, 0), (1, 0, 2, 5, 0), (1, 0, 2, 9, 0), (1, 0, 3, 1, 0),
                    (1, 0, 4, 1, 0), (1, 0, 7, 9, 0), (1, 0, 8, 1, 0), (1, 0, 8, 9, 0),
                    (1, 0, 9, 1, 0), (1, 0, 9, 5, 0), (1, 0, 9, 6, 0), (1, 0, 9, 7, 0),
                    (1, 0, 9, 8, 0), (1, 0, 9, 9, 0)),
                    
                    ((2, 3, 4, 1, 3), (2, 14, 2, 2, 4), (2, 4, 1, 3, 2), (2, 6, 3, 3, 1),
                    (2, 11, 1, 4, 2), (2, 10, 1, 5, 2), (2, 10, 1, 6, 2), (2, 11, 3, 6, 2),
                    (2, 14, 2, 7, 4), (2, 15, 1, 8, 2), (2, 21, 3, 8, 6), (2, 22, 1, 9, 6),
                    (1, 5, 1, 1, 2), (1, 8, 2, 1, 4), (1, 10, 3, 1, 6), (1, 7, 1, 2, 3),
                    (1, 9, 3, 2, 5), (1, 5, 1, 3, 2), (1, 6, 2, 3, 3), (1, 6, 3, 3, 4),
                    (1, 6, 1, 4, 2), (1, 5, 1, 5, 2), (1, 3, 1, 6, 2), (1, 6, 1, 7, 2),
                    (1, 4, 3, 7, 4), (1, 7, 1, 8, 3), (1, 11, 3, 8, 5), (1, 12, 1, 9, 5),
                    (1, 0, 1, 1, 0), (1, 0, 1, 2, 0), (1, 0, 1, 3, 0), (1, 0, 1, 4, 0),
                    (1, 0, 1, 5, 0), (1, 0, 1, 6, 0), (1, 0, 1, 7, 0), (1, 0, 1, 8, 0),
                    (1, 0, 1, 9, 0), (1, 0, 2, 1, 0), (1, 0, 2, 2, 0), (1, 0, 2, 3, 0),
                    (1, 0, 2, 4, 0), (1, 0, 2, 5, 0), (1, 0, 2, 6, 0), (1, 0, 2, 7, 0),
                    (1, 0, 2, 8, 0), (1, 0, 2, 9, 0)),

                    ((2, 7, 4, 2, 3), (2, 29, 2, 3, 7), (2, 4, 2, 4, 2), (2, 6, 6, 4, 3),
                    (2, 24, 3, 5, 5), (2, 15, 2, 6, 5), (2, 7, 1, 7, 3), (2, 8, 6, 7, 2),
                    (2, 42, 1, 8, 7), (2, 6, 3, 9, 3), (1, 10, 2, 6, 2), (1, 4, 3, 2, 2),
                    (1, 6, 3, 5, 3), (1, 28, 4, 2, 7), (1, 10, 5, 1, 2), (1, 12, 5, 4, 2),
                    (1, 11, 5, 7, 2), (1, 3, 6, 1, 2), (1, 4, 6, 4, 2), (1, 6, 6, 7, 2),
                    (1, 28, 7, 1, 7), (1, 7, 8, 2, 3), (1, 16, 8, 6, 2), (1, 3, 9, 2, 2),
                    (1, 0, 1, 1, 0), (1, 0, 1, 2, 0), (1, 0, 1, 3, 0), (1, 0, 1, 4, 0),
                    (1, 0, 1, 5, 0), (1, 0, 1, 6, 0), (1, 0, 1, 9, 0), (1, 0, 2, 1, 0),
                    (1, 0, 2, 2, 0), (1, 0, 2, 5, 0), (1, 0, 2, 9, 0), (1, 0, 3, 1, 0),
                    (1, 0, 4, 1, 0), (1, 0, 7, 9, 0), (1, 0, 8, 1, 0), (1, 0, 8, 9, 0),
                    (1, 0, 9, 1, 0), (1, 0, 9, 5, 0), (1, 0, 9, 6, 0), (1, 0, 9, 7, 0),
                    (1, 0, 9, 8, 0), (1, 0, 9, 9, 0)))

                tab = random.choice(m3)
            elif "e" in contenido:
                m4 =(((2, 3, 4, 1, 3), (2, 14, 2, 2, 4), (2, 4, 1, 3, 2), (2, 6, 3, 3, 1),
                    (2, 11, 1, 4, 2), (2, 10, 1, 5, 2), (2, 10, 1, 6, 2), (2, 11, 3, 6, 2),
                    (2, 14, 2, 7, 4), (2, 15, 1, 8, 2), (2, 21, 3, 8, 6), (2, 22, 1, 9, 6),
                    (1, 5, 1, 1, 2), (1, 8, 2, 1, 4), (1, 10, 3, 1, 6), (1, 7, 1, 2, 3),
                    (1, 9, 3, 2, 5), (1, 5, 1, 3, 2), (1, 6, 2, 3, 3), (1, 6, 3, 3, 4),
                    (1, 6, 1, 4, 2), (1, 5, 1, 5, 2), (1, 3, 1, 6, 2), (1, 6, 1, 7, 2),
                    (1, 4, 3, 7, 4), (1, 7, 1, 8, 3), (1, 11, 3, 8, 5), (1, 12, 1, 9, 5),
                    (1, 0, 1, 1, 0), (1, 0, 1, 2, 0), (1, 0, 1, 3, 0), (1, 0, 1, 4, 0),
                    (1, 0, 1, 5, 0), (1, 0, 1, 6, 0), (1, 0, 1, 7, 0), (1, 0, 1, 8, 0),
                    (1, 0, 1, 9, 0), (1, 0, 2, 1, 0), (1, 0, 2, 2, 0), (1, 0, 2, 3, 0),
                    (1, 0, 2, 4, 0), (1, 0, 2, 5, 0), (1, 0, 2, 6, 0), (1, 0, 2, 7, 0),
                    (1, 0, 2, 8, 0), (1, 0, 2, 9, 0)),
                    
                    ((2, 7, 4, 2, 3), (2, 29, 2, 3, 7), (2, 4, 2, 4, 2), (2, 6, 6, 4, 3),
                    (2, 24, 3, 5, 5), (2, 15, 2, 6, 5), (2, 7, 1, 7, 3), (2, 8, 6, 7, 2),
                    (2, 42, 1, 8, 7), (2, 6, 3, 9, 3), (1, 10, 2, 6, 2), (1, 4, 3, 2, 2),
                    (1, 6, 3, 5, 3), (1, 28, 4, 2, 7), (1, 10, 5, 1, 2), (1, 12, 5, 4, 2),
                    (1, 11, 5, 7, 2), (1, 3, 6, 1, 2), (1, 4, 6, 4, 2), (1, 6, 6, 7, 2),
                    (1, 28, 7, 1, 7), (1, 7, 8, 2, 3), (1, 16, 8, 6, 2), (1, 3, 9, 2, 2),
                    (1, 0, 1, 1, 0), (1, 0, 1, 2, 0), (1, 0, 1, 3, 0), (1, 0, 1, 4, 0),
                    (1, 0, 1, 5, 0), (1, 0, 1, 6, 0), (1, 0, 1, 9, 0), (1, 0, 2, 1, 0),
                    (1, 0, 2, 2, 0), (1, 0, 2, 5, 0), (1, 0, 2, 9, 0), (1, 0, 3, 1, 0),
                    (1, 0, 4, 1, 0), (1, 0, 7, 9, 0), (1, 0, 8, 1, 0), (1, 0, 8, 9, 0),
                    (1, 0, 9, 1, 0), (1, 0, 9, 5, 0), (1, 0, 9, 6, 0), (1, 0, 9, 7, 0),
                    (1, 0, 9, 8, 0), (1, 0, 9, 9, 0)),

                    ((2, 3, 4, 1, 3), (2, 14, 2, 2, 4), (2, 4, 1, 3, 2), (2, 6, 3, 3, 1),
                    (2, 11, 1, 4, 2), (2, 10, 1, 5, 2), (2, 10, 1, 6, 2), (2, 11, 3, 6, 2),
                    (2, 14, 2, 7, 4), (2, 15, 1, 8, 2), (2, 21, 3, 8, 6), (2, 22, 1, 9, 6),
                    (1, 5, 1, 1, 2), (1, 8, 2, 1, 4), (1, 10, 3, 1, 6), (1, 7, 1, 2, 3),
                    (1, 9, 3, 2, 5), (1, 5, 1, 3, 2), (1, 6, 2, 3, 3), (1, 6, 3, 3, 4),
                    (1, 6, 1, 4, 2), (1, 5, 1, 5, 2), (1, 3, 1, 6, 2), (1, 6, 1, 7, 2),
                    (1, 4, 3, 7, 4), (1, 7, 1, 8, 3), (1, 11, 3, 8, 5), (1, 12, 1, 9, 5),
                    (1, 0, 1, 1, 0), (1, 0, 1, 2, 0), (1, 0, 1, 3, 0), (1, 0, 1, 4, 0),
                    (1, 0, 1, 5, 0), (1, 0, 1, 6, 0), (1, 0, 1, 7, 0), (1, 0, 1, 8, 0),
                    (1, 0, 1, 9, 0), (1, 0, 2, 1, 0), (1, 0, 2, 2, 0), (1, 0, 2, 3, 0),
                    (1, 0, 2, 4, 0), (1, 0, 2, 5, 0), (1, 0, 2, 6, 0), (1, 0, 2, 7, 0),
                    (1, 0, 2, 8, 0), (1, 0, 2, 9, 0)))
                tab = random.choice(m4)

            if "cron" in contenido:
                h = Label(ventana, text="Horas", bg = "bisque")
                h.place(x=20, y=590)

                m = Label(ventana, text="Minutos", bg = "bisque")
                m.place(x=60, y=590)

                s = Label(ventana, text="Segundos", bg = "bisque")
                s.place(x=120, y=590)

                thoras = Label(ventana, text="00", bg = "bisque")
                thoras.place(x=25, y=620)

                tminutos = Label(ventana, text="00", bg = "bisque")
                tminutos.place(x=65, y=620)

                tsegundos = Label(ventana, text="00", bg = "bisque")
                tsegundos.place(x=125, y=620)

            elif "timer" in contenido:
                h = Label(ventana, text="Horas", bg = "bisque")
                h.place(x=20, y=590)

                m = Label(ventana, text="Minutos", bg = "bisque")
                m.place(x=60, y=590)

                s = Label(ventana, text="pene", bg = "bisque")
                s.place(x=120, y=590)

                thoras = Label(ventana, text="00", bg = "bisque")
                thoras.place(x=25, y=620)

                tminutos = Label(ventana, text="00", bg = "bisque")
                tminutos.place(x=65, y=620)

                tsegundos = Label(ventana, text="00", bg = "bisque")
                tsegundos.place(x=125, y=620)
            for fila in tab:
                        f = fila[2]
                        col = fila[3]
                        dig = fila[1]
#(modo-1, dig-0, col-9, fila-9, 0)
                        if dig != 0:
                            button = tablero[f-1][col-1]
                            
                            texto = button.cget("text")
                            if texto:
                                if fila[0] == 1:
                                    button.config(text="\ "+ str(dig) + " \n"  + "\ " + "  \n  " + texto + "  \ ", bg="black", state="disabled")
                                if fila[0] == 2:
                                    button.config(text=" " + str(dig) + " \n" + texto + "  \ ", bg="black", state="disabled")
                            else:
                                button.config(text=str(dig), bg="black", fg="white", state="disabled")
                        else:
                            button = tablero[f-1][col-1]
                            button.config(bg="black", state="disabled")
    ventana.mainloop()










#Funcion para seleccioanr dificultad
def nivel(config):
    config.withdraw()
    nivel=Toplevel(config)
    nivel.title("Dificultades")
    nivel.geometry("300x300")
    nivel.configure(bg= "bisque")


    #Botones de las dificulatdes
    facil=Button(nivel, text="Facil", command=faciles,width= 10, height= 2, bg="sky blue")
    facil.place(x=110, y=90)

    medio=Button(nivel, text="Medio", command=medios,width= 10, height= 2, bg="sky blue")
    medio.place(x=110, y=130)

    dificil=Button(nivel, text="Dificil", command=dificiles,width= 10, height= 2, bg="sky blue")
    dificil.place(x=110, y=170)

    experto=Button(nivel, text="Experto", command=expertos,width= 10, height= 2, bg="sky blue")
    experto.place(x=110, y=210)


    atras = Button(nivel, text="<-", command=lambda: atr(nivel, config), width = 5, height=1, bg="sky blue", fg = "white")
    atras.grid(row= 0, column=0)


   



#Ventana para escojer el reloj
def relojes(config):
    config.withdraw()
    reloj=Toplevel(config)
    reloj.title("Selector de reloj")
    reloj.geometry("300x300")
    reloj.configure(bg= "bisque")

    #Botones de los relojes
    crono=Button(reloj, text="Cronometro", command=cron,width= 10, height= 2, bg="sky blue")
    crono.place(x=110, y=90)

    sin_r=Button(reloj, text="Sin reloj", width= 10, height= 2, bg="sky blue")
    sin_r.place(x=110, y=130)

    tim=Button(reloj, text="Timer", command=timer,width= 10, height= 2, bg="sky blue")
    tim.place(x=110, y=170)

    atras = Button(reloj, text="<-", command=lambda: atr(reloj, config), width = 5, height=1, bg="sky blue", fg = "white")
    atras.grid(row= 0, column=0)

def salida():
    config.quit()
#Funcion para salir de la aplicacion

def ayud():
    os.system("C:/Users/Lenovo/Desktop/manual_de_usuario_kakuro.pdf")

#Despliega informacion del juego
def acerca_de():
    messagebox.showinfo("Acerca de","Juego Kakuro\nFecha de creacion: 18/5/2023\nAutor: Joshua Guerra Castillo")

#Funcion para el boton "<-"
def atr(cerrar, abrir):
    cerrar.destroy()   
    abrir.deiconify()

config=Tk()
config.title("Configuracion")
config.geometry("400x400")
config.configure(bg= "bisque")

niveles = Button(config, text= "NIVEL", command=lambda:nivel(config) , width= 15, height= 3, bg="sky blue")
niveles.place(x= 50, y=50)

reloj = Button(config, text= "RELOJ", command=lambda:relojes(config) , width= 15, height= 3, bg="sky blue")
reloj.place(x= 250, y=50)

ayuda = Button(config, text= "AYUDA", command=ayud , width= 15, height= 3, bg="sky blue")
ayuda.place(x= 50, y=150)

acerca = Button(config, text= "ACERCA DE", command=acerca_de , width= 15, height= 3, bg="sky blue")
acerca.place(x= 250, y=150)

salir = Button(config, text= "SALIR", command=salida , width= 15, height= 3, bg="sky blue")
salir.place(x= 250, y=250)



jug = Button(config, text="JUGAR", command=lambda:jugar(config), width = 15, height=3, bg="sky blue")
jug.place(x= 50, y=250)
config.mainloop()