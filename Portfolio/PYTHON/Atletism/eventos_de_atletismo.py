# PRUEBAS DE ATLETISMO
#Autor Joshua Guerra Castillo
# MÓDULOS
#Validate email revisa si el email está bien escrito
from validate_email import validate_email
#Reportlab es una libreria para crear pdf y modificarlo
from reportlab.pdfgen import canvas
#os es una libreria que ya viene en python y servira para la opcion 7
import os

# FUNCIONES
#menu principal
# Obtener datos de una disciplina
# E: lista de disciplinas, nombre de la disciplina de la que se ocupan los datos
# S: Si la disciplina existe en la lista retorna True y la tupla con los datos de la disciplina, sino existe retorna False y tupla vacía
def obtener_datos(lista, dato):
    for i in lista:
        if i[0] == dato or dato in i or dato == i:
            return True, i
    return False, tuple()

#Funcion para obtener especificamente datos de eventos
def obtener_datos_eventos(marcas, dato):
    for i in marcas:
        for j in i:
            for x in j[1:]:
                if x[0] == dato or dato in x or dato == x:
           
                    return True, x

    return False, tuple()

#Funcion para eliminar eventos
def del_eventos(marcas, dato):
    for i in marcas:
        for j in i:
            if dato == j:
           
                return True, j
    
    return False, tuple()

#Funcion para modificar atletas
#Retorna valor booleano
def obtener_datos_atleta(marcas, dato):
    for i in marcas:
        for j in i:
            if dato == j:
           
                return True, i[0]

    return False, tuple()

#Funcion para saber si un atleta ya tiene marcas en un evento y así no eliminarlo
#Retorna valor booleano
def obtener_datos_marcas(marcas, dato):
    for i in marcas:
        for j in i:
            for x in j[1:]:
                for y in x[1:]:
                    if dato in y:
                        return True
    return False

#Funcion para aceptar en cualquier parte del codigo
#Retorna valor booleano
def aceptar():
    while True:
        opcion = input("OPCION     <A> ACEPTAR_   ")
        if opcion == "A":
            return True
        else:
            input("OPCIÓN NO ES PERMITIDA. DAR <INTRO>")
            
#Funcion para aceptar o cancelar en cualquier parte del codigo
#Retorna valor booleano            
def aceptar_cancelar():
    while True:
        opcion = input("OPCION    <C> CANCELAR  <A> ACEPTAR ")
        if opcion == "A":
            return True
        if opcion == "C":
            return False
        else:
            input("OPCIÓN NO ES PERMITIDA. DAR <INTRO>")

#Funcion auxiliar para confirmar la eliminacion en cualquier parte del codigo
#Retorna valor booleano
def confirmacion_borrar(): 
    conf = input("CONFIRMA LA ELIMINACION (SI/NO)")
    if conf == "NO":
        return False
    if conf == "SI":
        return True


#Funcion auxiliar para validar que las fechas sean correctas
#Retorna valor booleano
def valid_fecha(fecha):
    d = int(fecha[0:2])
    m = int(fecha[3:5])
    a = int(fecha[6:])
    m_30 = [1, 3, 5, 7, 8, 10, 12]
    m_31 = [4, 6, 9, 11]
    if m in m_30:
        if 0 < d <=30 and 1990 <= a <= 2011:
            return True
        else:
            return False
    elif m in m_31:
        if 0 < d <=31 and 1990 <= a <= 2011:
            return True
        else:
            return False
    elif m == 28:
        if 0 < d <=28 and 1990 <= a <= 2011:
            return True
        else:
            return False
    else:
        return False


#Funcion para validar que los tiempos sean correctos
#Retorna el teimpo en el formato correcto
def valid_tiempo (marca):
    tiempo = [0, 0, 0, 0]
    if len(marca) == 8:
        tiempo[0] = int(marca[:2])
        tiempo[1] = int(marca[2:4])
        tiempo[2] = int(marca[4:6])
        tiempo[3] = int(marca[6:])
    elif len(marca) == 6:
        tiempo[1] = int(marca[:2])
        tiempo[2] = int(marca[2:4])
        tiempo[3] = int(marca[4:])
    elif len(marca) == 4:
        tiempo[2] = int(marca[:2])
        tiempo[3] = int(marca[2:])
    hh, mm, ss, cc = tiempo
    return f"{hh:02d}:{mm:02d}:{ss:02d}.{cc:02d}"

#Funcion para validar que los metros sean correctos
#Retorna los metros en el formato correcto
def valid_metros(marca):
    try:
        metros, centesimas = [int(i) for i in marca.split('.')]
    except ValueError:
        if len(marca) == 1:
            metros, centesimas = 1, 0
        else:
            return "Formato no válido"
    return f"{metros} m {centesimas:02d} cm"

#Funcion auxiliar para saber si la combinacion de datos esta en marcas
#Retorna valor booleano y la tupla con los datos para usarse despúes
def combinacion_datos(marcas, ident_2, cod, ident_a):
    for i in marcas:
        for j in i:
            if isinstance(j, list) and j[0] == cod:
                for y in j[1:]:
                    if isinstance(y, tuple) and ident_a in y:
                        return True, y
            elif isinstance(j, str) and ident_2 == i[0]:
                if j == cod:
                    return True, i
    return False, tuple()


#Funcion para validar los correos, acá se usa la libreria validate_email
#Retorna valor booleano
def val_correo(correo):
    val = validate_email(correo)
    if val:
        return True
    else:
        return False



# menú crud disciplinas
# Menú para agregar, consultar, modificar y eliminar disciplinas                          
def crud_disciplinas(disciplinas):
    while True:
        print("\n\n\n-------------------------------------------------------------------------------")
        print("                     EVENTOS DE ATLETISMO\n")
        print("                     REGISTRAR DISCIPLINAS\n")
        print(" 1. Agregar disciplinas")
        print(" 2. Consultar disiciplinas")
        print(" 3. Modificar disciplinas")
        print(" 4. Eliminar disciplinas")
        print(" 0. FIN")
        opcion = input("    OPCIÓN ")              
        match opcion: 
            case "1":
                agregar_disciplinas(disciplinas)
            case "2":
                consultar_disciplinas(disciplinas)
            case "3":
                modificar_disciplinas(disciplinas)
            case "4":
                eliminar_disciplinas(disciplinas, pruebas)  
            case "0":
                break
            case _: 
                input("OPCIÓN NO ES PERMITIDA. DAR <INTRO>")


# Agregar disciplinas
# E: lista de disciplinas
# S: lista de disciplinas actualizada 
def agregar_disciplinas(disciplinas):
        while True:
            print("\n\n\n-------------------------------------------------------------------------------")
            print("                       EVENTOS DE ATLETISMO\n")
            print("                       AGREGAR DISCIPLINAS\n")
            while True:
                nombre_disciplina = input("Nombre de la disciplina                 ")
                if nombre_disciplina == "C":
                    return
                if  len(nombre_disciplina) >=5 and len(nombre_disciplina) <= 30:
                    existe_disciplina, disciplina = obtener_datos(disciplinas, nombre_disciplina)
                    if existe_disciplina:
                        input("ESTA DISCIPLINA YA ESTÁ REGISTRADA, NO SE PUEDE AGREGAR. DAR <INTRO>")
                    else:
                        break
                else:
                    input("DATO DEBE TENER ENTRE 5 Y 30 CARACTERES. DAR <INTRO>")
                 
            while True:
                forma_medir = input("Forma de medir (T/M)                    ")
                if forma_medir in "TM":
                    break
                else:
                    input("DATO DEBE SER T O M. DAR <INTRO>")

            cond = aceptar_cancelar()
            if cond == False:
                break
            else:
                disciplinas.append((nombre_disciplina, forma_medir))


# Consultar disciplinas
# E: lista de disciplinas
# S: Despliega datos de disciplinas 
def consultar_disciplinas(disciplinas):
    while True:
        print("\n\n\n-------------------------------------------------------------------------------")
        print("                     EVENTOS DE ATLETISMO\n")
        print("                     CONSULTAR DISCIPLINAS\n")
        
        while True:
            while True:
                nombre_disciplina = input("Nombre de la disciplina                 ")
                if nombre_disciplina == "C":
                    return
                existe_disciplina, disciplina = obtener_datos(disciplinas, nombre_disciplina)

                if existe_disciplina:
                    break
                else:
                    input("ESTA DISCIPLINA NO ESTÁ REGISTRADA, NO SE PUEDE CONSULTAR. DAR <INTRO>")  

            print("Forma de medir (T/M)                   ", disciplina[1])
            print(disciplina)
            if aceptar():
                break


#Modificar disciplinas
# E: lista de disciplinas
# S: Disciplinas actualizadas 
def  modificar_disciplinas(disciplinas):
            while True:
                forma_medir = " "
                forma_nueva = " "
                print("\n\n\n-------------------------------------------------------------------------------")
                print("                     EVENTOS DE ATLETISMO\n")
                print("                     MODIFICAR DISCIPLINAS\n")
    
                while True:
                    nombre_disciplina = nomb = input("Nombre de la disciplina                 ")
                    if nomb == "C":
                        return
                    existe_disciplina, disciplina = obtener_datos(disciplinas, nombre_disciplina)
                    if existe_disciplina == False:
                        print("ESTA DISCIPLINA NO ESTA REGISTRADA, NO SE PUEDE MODIFICAR")
                    else:
                        break
                while True:
                    nuevo_nombre =  input("  NOMBRE MODIFICADO                 ")
                    if  len(nuevo_nombre) >=5 and len(nuevo_nombre) <= 30:
                        if nuevo_nombre == " ":
                            nombre_disciplina = nombre_disciplina
                            break
                        else:
                            nombre_disciplina = nuevo_nombre
                            break
                    else:
                          input("DATO DEBE TENER ENTRE 5 Y 30 CARACTERES. DAR <INTRO>")
                while True:
                    for i in disciplinas:
                        if nomb in i:
                            print("Forma de medir (T/M): ", i[1]) 
                            forma_nueva = input("   FORMA MODIFICADA                    ")
                            break
                    if forma_nueva == " ":
                        forma_medir = forma_medir
                        break
                    if forma_nueva in "TM":
                        forma_medir = forma_nueva
                        break
                    else:
                        input("DATO DEBE SER T O M. DAR <INTRO>")
                while True:
                    cond = aceptar_cancelar()
                    if cond == False:
                        break
                    if cond == True:
                        ind = disciplinas.index(i)
                        disciplinas.remove(i)
                        disciplinas.insert(ind, (nombre_disciplina, forma_medir))
                        break


                        
#Eliminar disciplinas
# E: lista de disciplinas y pruebas
# S: Lista de disciplinas actualizada
def eliminar_disciplinas(disciplinas, pruebas):
    
    while True:
        print("\n\n\n-------------------------------------------------------------------------------")
        print("                     EVENTOS DE ATLETISMO\n")
        print("                     ELIMINAR DISCIPLINAS\n")
        cond = 0
        while True:
            borrar = input("Nombre de la disciplina                 ")
            if borrar == "C":
                return
            existe_disciplina, disciplina = obtener_datos(disciplinas, borrar)
            if existe_disciplina == False:
                print("ESTA DISCIPLINA NO ESTA REGISTRADA, NO SE PUEDE ELIMINAR")
            else:
                break
            
        #Ciclo para sacar la tupla que se va a remover
        while True:
            for i in disciplinas:
                if borrar in i:
                    cond += 1
                    break
            if cond > 0:
                break
            
        cond_2 = aceptar_cancelar()
        if cond_2 == False:
            break
        if cond_2 == True:
            if len(pruebas) == 0:
                if confirmacion_borrar() == True:
                    disciplinas.remove(i)
                    break
            else:
                for j in pruebas:
                    if not borrar in j:
                        if confirmacion_borrar() == True:
                            disciplinas.remove(i)
                            break
                        else:
                            break
                    else:
                        print("DISCIPLINA NO SE PUEDE ELIMINAR PORQUE TIENE PRUEBAS")
                        break



                       

# menú crud pruebas
# Menú para agregar, consultar, modificar y eliminar pruebas 
# E: lista de pruebas, eventos, categorias y marcas                
def crud_pruebas(pruebas, eventos, categoria, marcas):
    while True:
        print("\n\n\n-------------------------------------------------------------------------------")
        print("                     EVENTOS DE ATLETISMO\n")
        print("                     REGISTRAR PRUEBAS POR DISCIPLNA\n")
        print(" 1. Agregar pruebas")
        print(" 2. Consultar pruebas")
        print(" 3. Modificar pruebas")
        print(" 4. Eliminar pruebas")
        print(" 0. FIN")
        opcion = input("    OPCIÓN ")              
        match opcion: 
            case "1":
                agregar_pruebas(pruebas, categoria)
            case "2":
                consultar_pruebas(pruebas)
            case "3":
                modificar_pruebas(pruebas, eventos, categoria, marcas)
            case "4":
                eliminar_pruebas(pruebas, eventos, categoria, marcas)  
            case "0":
                break
            case _: 
                input("OPCIÓN NO ES PERMITIDA. DAR <INTRO>")

#Agregar pruebas
# E: lista de pruebas y categorias
# S: lista de pruebas actualizada
def agregar_pruebas(pruebas, categoria):
    while True:
        while True:
            print("\n\n\n-------------------------------------------------------------------------------")
            print("                     EVENTOS DE ATLETISMO\n")
            print("                     AGREGAR PRUEBAS\n")
            
            while True:
                cod = input("Codigo de la prueba                 ")
                if len(cod) != 3:
                    print("El codigo debe ser de 3 digitos")
                if cod == "C":
                    return
                existe_prueba, prueba = obtener_datos(pruebas, cod)
                if existe_prueba:
                        print("ESTA PRUEBA YA ESTÁ REGISTRADA, NO SE PUEDE AGREGAR")
                else:
                    break

            while True:
                nombre_prueba = input("Nombre de la prueba                 ")
                if  len(nombre_prueba) >=3 and len(nombre_prueba) <= 30:
                    break
                else:
                    print("El nombre debe tener entre 3 y 30 caracteres")
                    
            while True:
                cat = input("Categoria                 ")
                if cat in categoria:
                    break
                else:
                    print("La categoria debe ser una de las siguientes: \n U12, U13, U14, U15, U16, U17, U18, U20, MAYOR, MASTER")

            while True:
                sexo = input("Sexo(F/M)                 ")
                if sexo in "FM":
                    break
                else:
                    print("El sexo debe ser F/M")

            nombre_disciplina = input("Nombre de la disciplina                 ")
            cond = aceptar_cancelar()
            if cond == False:
                break
            if cond == True:
                pruebas.append((cod, nombre_prueba, cat, sexo, nombre_disciplina))
                break

#Consultar categorias
# E: lista de pruebas
# S: Despliega datos de pruebas
def consultar_pruebas(pruebas):
    while True:
        print("\n\n\n-------------------------------------------------------------------------------")
        print("                     EVENTOS DE ATLETISMO\n")
        print("                     CONSULTAR PRUREBAS\n")
        while True:
            cod = input("Codigo de la prueba                 ")
            if cod == "C":
                return
            existe_prueba, prueba = obtener_datos(pruebas, cod)
            if existe_prueba == False:
                print("ESTA PRUEBA NO ESTA REGISTRADA, NO SE PUEDE CONSULTAR")
            else:
                for i in pruebas:
                    if cod in i:
                        print("Codigo de la prueba                 ", i[0])
                        print("Nombre de la prueba               ", i[1])
                        print("Categoria                                  ", i[2])
                        print("Sexo(F/M)                                 ", i[3])
                        print("Nombre de la disciplina            ", i[4])
                        break
                if aceptar():
                    break
                
                


    
#Modificar pruebas
# E: lista de pruebas y marcas
# S: lista de pruebas actualizada
def modificar_pruebas(pruebas, eventos, categoria, marcas):
    while True:
        while True:
            print("\n\n\n-------------------------------------------------------------------------------")
            print("                     EVENTOS DE ATLETISMOS\n")
            print("                     MODIFICAR PRUEBAS\n")
            
            i = val = 0
            while True:
                while True:
                    while True:
                        cod = input("Codigo de la prueba                 ")
                        if cod == "C":
                            return
                        existe_prueba, prueba = obtener_datos(pruebas, cod)
                        if existe_prueba == False:
                            print("ESTA PRUEBA NO ESTA REGISTRADA, NO SE PUEDE MODIFICAR")      
                        else:
                            for i in pruebas:
                                if cod in i:
                                    esta_en_eventos = obtener_datos_eventos(marcas, i[0])
                                    if esta_en_eventos == True:
                                        print("ESTA PRUEBA YA ESTA REGISTRADA EN EVENTOS, NO SE PUEDE MODIFICAR")
                                    else:
                                        val += 1
                                        break

                                       
                        break
                    if val > 0:
                        break

                        
                for j in pruebas:
                    if cod in j:
                        print("\nNombre de la prueba                 ", j[1])
                        
                        while True:
                            nombre = input("   NOMBRE MODIFICADO                 ")
                            if nombre == "":
                                nombre = j[1]
                                break
                            if  len(nombre) >=3 and len(nombre) <= 30:
                                break
                            else:
                                input("DATO DEBE TENER ENTRE 3 Y 30 CARACTERES. DAR <INTRO>")
                                
                        print("Categoria                 ", j[2])
                        while True:
                            cat = input("   CATEGORIA MODIFICADA                 ")
                            if cat == "":
                                cat = j[2]
                                break
                            if cat in categoria:
                                break
                            else:
                                 print("La categoria debe ser una de las siguientes: \n U12, U13, U14, U15, U16, U17, U18, U20, MAYOR, MASTER")
                            
                        print("Sexo (F/M)                 ", j[3])
                        
                        while True:
                            sexo = input("   SEXO MODIFICADO                 ")
                            if sexo == "":
                                sexo = j[3]
                                break
                            if sexo in "FM":
                                break
                            else:
                                 print("El sexo debe ser F/M")
                                 
                        print("Nombre de la desciplina                 ", j[4])
                        while True:
                            nomb_dis = input("   NOMBRE DISCIP MODIF                 ")
                            if nomb_dis == "":
                                nomb_dis = j[4]
                                break
                            if  len(nomb_dis) >=5 and len(nomb_dis) <= 30:
                                break
                            else:
                                input("DATO DEBE TENER ENTRE 5 Y 30 CARACTERES. DAR <INTRO>")
                        cond = aceptar_cancelar()
                        if cond == False:
                            break
                        elif cond == True:
                            ind = pruebas.index(j)
                            pruebas.remove(j)
                            pruebas.insert(ind, (cod, nombre, cat, sexo, nomb_dis))
                            break

                        
        

#Eliminar pruebas
# E: lista de pruebas y marcas
# S: lista de pruebas actualizada
def eliminar_pruebas(pruebas, eventos, categoria, marcas):
    while True:
        print("\n\n\n-------------------------------------------------------------------------------")
        print("                     EVENTOS DE ATLETISMOS\n")
        print("                     ELIMINAR PRUEBAS\n")
        cond = 0
        while True:
            cod = input("Codigo de la prueba")
            if cod == "C":
                return
            existe_disciplina, disciplina = obtener_datos(pruebas, cod)
            if existe_disciplina == False:
                print("ESTA PRUEBA NO ESTA REGISTRADA, NO SE PUEDE ELIMINAR")
            else:
                existe_en_evento, prueba = obtener_datos(marcas, cod)
                print(existe_en_evento, prueba)
                if existe_en_evento:
                    print("PRUEBA NO SE PUEDE ELIMINAR PORQUE ESTÁ EN LOS EVENTOS", prueba)
                else:

                    while True:
                        for i in pruebas:
                            if cod in i:
                                cond+=1
                                break
                        if cond > 0:
                             break


                    cond_2 = aceptar_cancelar()
                            
                    if cond_2 == False:
                        break
                    if cond_2 == True:
                        pruebas.remove(i)

                        break
                    for x in pruebas:
                        if cod in x:
                            print("Codigo de la prueba", x[0])
                            print("Nombre de la prueba", x[1])
                            print("Categoria", x[2])
                            print("Sexo (F/M)", x[3])
                            print("Nombre de la disciplina", x[4])
                            break
                        break                
                
        
#menú crud atletas
#Menú para agregar, consultar, modificar y eliminar atletas
# E: lista de atletas, lista de eventos, lista de marcas y lista de paises
def crud_atletas(atletas, eventos, marcas, paises):
      while True:
        print("\n\n\n-------------------------------------------------------------------------------")
        print("                     EVENTOS DE ATLETISMO\n")
        print("                     REGISTRAR ATLETAS\n")
        print(" 1. Agregar atletas")
        print(" 2. Consultar atletas")
        print(" 3. Modificar atletas")
        print(" 4. Eliminar atletas")
        print(" 0. Salir")
        opcion = input("    OPCIÓN ")              
        match opcion: 
            case "1":
                agregar_atletas(atletas, paises)
            case "2":
                consultar_atletas(atletas)
            case "3":
                modificar_atletas(atletas,paises)
            case "4":
                eliminar_atletas(atletas, marcas)  
            case "0":
                break
            case _: 
                input("OPCIÓN NO ES PERMITIDA. DAR <INTRO>")

#Agregar atletas
# E: lista de atletas y lista de paises
# S: lista de atletas actualizada
def agregar_atletas(atletas, paises):
    while True:
        print("\n\n\n-------------------------------------------------------------------------------")
        print("                     EVENTOS DE ATLETISMO\n")
        print("                     AGREGAR ATLETAS\n")
        while True:
            while True:
                ident = input("Identificacion del atleta                  ")
                if ident == "C":
                    return
                ident_2 = int(ident)
                existe_atleta, atleta = obtener_datos(atletas, ident_2)

                if existe_atleta ==  True:
                    print("ESTE ATLETA YA ESTÁ REGISTRADO, NO SE PUEDE AGREGAR") 
                else:
                    break
                
            while True:
                nombre = input("Nombre      ")
                if  len(nombre) >=2 and len(nombre) <= 20:
                    break
                else:
                    print("El nombre debe tener entre 2 y 20 caracteres")

            while True:
                apellido_1 = input("Apellido 1      ")
                if  len(apellido_1) >=2 and len(apellido_1) <= 20:
                    break
                else:
                    print("El apellido debe tener entre 2 y 20 caracteres")

            while True:
                apellido_2 = input("apellido 2      ")
                if  len(apellido_2) >=2 and len(apellido_2) <= 20:
                    break
                else:
                    print("El nombre debe tener entre 2 y 20 caracteres")
            
            while True:
                sexo = input("Sexo(F/M)                 ")
                if sexo in "FM":
                    break
                else:
                    print("El sexo debe ser F/M")

            while True:
                pais = input("Pais que representa      ")
                if len(pais) >= 3:
                    p, pai = obtener_datos(paises, pais)
                    if p == True:
                        break
                    else:
                        print("El pais debe estar en codificacion ISO 3166-1 alfa-3")
                   
                else:
                    print("El pais debe tener mas de 3 caracteres")

            while True:
                fecha = input("Ingrese la fecha con '/'\nFecha de nacimiento    ")
                if fecha == "":
                    print("Fecha invalida")
                else:
                    cond = valid_fecha(fecha)
                    if cond == False:
                        print("Fecha invalida")
                    else:
                        break


            while True:
                correo = input("Correo electronico         ")
                val = val_correo(correo)
                if val:
                    correo_2 = correo
                    break
                else:
                    print("Correo invalido")
               

            while True:
                tel = input("Telefono       ")
                if len(tel) >= 7 and len(tel) <= 20:
                    break
                else:
                    print("El telefono debe tener 8 numeros")

            cond_2 = aceptar_cancelar()
            if cond_2 == False:
                break
            if cond_2 == True:
                tel = int(tel)
                atletas.append([ident, nombre, apellido_1, apellido_2, sexo, pais, fecha, correo, tel])
                break



#Consultar atletas
# E: lista de atletas
# S: datos del atleta consultado
def consultar_atletas(atletas):
    while True:
        print("\n\n\n-------------------------------------------------------------------------------")
        print("                     EVENTOS DE ATLETISMO\n")
        print("                     CONSULTAR ATLETAS\n")
        while True:
            ident = input("Identificacion del atleta     ")
            if ident == "C":
                return
            ident_2 = int(ident)
            existe_atleta, atleta = obtener_datos(atletas, ident_2)
            if existe_atleta == False:
                print("ESTE ATLETA NO ESTA REGISTRADO, NO SE PUEDE CONSULTAR")
            else:
                for i in atletas:
                    if ident_2 in i:
                        print("Identificacion del atleta                ", i[0])
                        print("Nombre              ", i[1])
                        print("Apellido 1                                  ", i[2])
                        print("Apellido 2                                 ", i[3])
                        print("Sexo (F/M)            ", i[4])
                        print("Pais que representa            ", i[5])
                        print("Fecha de nacimiento            ", i[6])
                        print("Correo electronico            ", i[7])
                        print("Telefono            ", i[8])
                        break
                if aceptar():
                    break

#Modificar atletas
# E: lista de atletas, lista de paises
# S: lista de atletas actualizada
def modificar_atletas(atletas, paises):
    while True:
        while True:
            print("\n\n\n-------------------------------------------------------------------------------")
            print("                     EVENTOS DE ATLETISMOS\n")
            print("                     MODIFICAR ATLETAS\n")
            i = 0
            while True:
                while True:
                    ident = input("Identificacion del atleta       ")
                    if ident == "C":
                        return
                    ident_2 = int(ident)
                    existe_atleta = obtener_datos_atleta(atletas, ident_2)
                    if existe_atleta == False:
                        print("ESTE ATLETA NO ESTA REGISTRADO, NO SE PUEDE CONSULTAR")
                    else:
                        break
                for j in atletas:

                    if ident_2 in j:
                        print("Nombre      ", j[1])
                        while True:
                            nombre = input("   NOMBRE MODIFICADO        ")
                            if nombre == "":
                                nombre = j[1]
                            if  len(nombre) >=2 and len(nombre) <= 20:
                                break
                            else:
                                print("El nombre debe tener entre 2 y 20 caracteres")
                           
                        print("Apellido 1      ", j[2])
                        while True:
                            apellido_1 = input("APELLIDO 1 MODIF      ")
                            if apellido_1 == "":
                                apellido_1 = j[2]
                            if  len(apellido_1) >=2 and len(apellido_1) <= 20:
                                break
                            else:
                                print("El apellido debe tener entre 2 y 20 caracteres")
                                
                        print("Apellido 2     ",j[3])
                        while True:
                            apellido_2 = input("APELLIDO 2 MODIF      ")
                            if apellido_2 == "":
                                apellido_2 = j[3]
                            if  len(apellido_2) >=2 and len(apellido_2) <= 20:
                                break
                            else:
                                print("El nombre debe tener entre 2 y 20 caracteres")

                        print("Sexo (F/M)       ", j[4])
                        while True:
                            sexo = input("SEXO MODIFICADO                 ")
                            if sexo == "":
                                sexo = j[5]
                            if sexo in "FM":
                                break
                            else:
                                print("El sexo debe ser F/M")

                        print("Pais al que representa      ", j[5])
                        while True:
                            pais = input("PAIS MODIFICADO      ")
                            if pais == "":
                                pais = j[5]
                            if len(pais) >= 3:
                                p = obtener_datos(paises, pais)
                                if p:
                                    break
                                else:
                                    print("El pais debe estar en codificacion ISO 3166-1 alfa-3") 
                            else:
                                print("El pais debe tener mas de 3 caracteres")

                        print("Fecha de nacimiento         ", j[6])
                        while True:
                            fecha = input("Ingrese la fecha con '/'\nFECHA NAC MODIF      ")
                            if fecha == "":
                                fecha = j[6]
                            cond = valid_fecha(fecha)
                            if cond == False:
                                print("Fecha invalida")
                            else:
                                break

                        print("Correo electronico         ", j[7])
                        while True:
                            correo = input("CORREO MODIFICADO          ")
                            if correo == "":
                                correo = j[7]
                            val = val_correo(correo)
                            if val:
                                correo_2 = correo
                                break
                            else:
                                print("Correo invalido")
                            



                        print("Telefono        ", j[8])
                        while True:
                            tel = input("TELEFONO MODIF         ")
                            if tel == "":
                                tel = j[8]
                            if  len(tel) >= 7 and len(tel) <= 20:
                                break
                            else:
                                print("El telefono debe tener 8 numeros")

                        cond = aceptar_cancelar()
                        if cond == False:
                            break
                        elif cond == True:
                            ind = atletas.index(j)
                            atletas.remove(j)
                            tel = int(tel)
                            atletas.insert(ind, [ident_2, nombre, apellido_1, apellido_2, sexo, pais, fecha, correo, tel])
                            break


#Eliminar atletas
# E: lista de atletas, lista de marcas
# S: lista de atletas actualizada
def eliminar_atletas(atletas, marcas):
    while True:
        print("\n\n\n-------------------------------------------------------------------------------")
        print("                     EVENTOS DE ATLETISMOS\n")
        print("                     ELIMINAR ATLETAS\n")
        cond = 0
        while True:
            ident = input("identificacion del atleta           ")
            if ident == "C":
                return
            ident_2 = int(ident)
            existe_atleta, evento = obtener_datos_atleta(atletas, ident_2)
            if existe_atleta == False:
                print("ESTE ATLETA NO ESTA REGISTRADO, NO SE PUEDE ELIMINAR")
            else:
                break

        existe_en_evento = obtener_datos_marcas(marcas, ident_2)
        if existe_en_evento == False:
            for x in atletas:
                    if ident_2 in x:
                        print("Identificacion del atleta", x[0])
                        print("Nombre", x[1])
                        print("Apellido 1", x[2])
                        print("Apellido 2", x[3])
                        print("Sexo (F/M)", x[4])
                        print("Pais que representa", x[5])
                        print("Fecha de nacimiento", x[6])
                        print("Correo electronico", x[7])
                        print("Telefono", x[8])
                        break
            cond_2 = aceptar_cancelar()
            if cond_2 == False:
                pass
            elif cond_2 == True:
                while True:
                    for i in atletas:
                        if ident_2 in i:
                            cond+=1
                            break
                    if cond > 0:
                        break
                atletas.remove(i)
                
                
        else:
            for z in marcas:
                for v in z[1:]:
                    for b in v[1:]:
                        if ident_2 in b:
                            print("PRUEBA NO SE PUEDE ELIMINAR PORQUE ESTÁ EN LOS EVENTOS", z[0])
                            break
                    break
                break
            



#menú crud eventos
#Menú para agregar, consultar, modificar y eliminar eventos
# E: lista de eventos, lista de marcas, lista de paises
def crud_eventos(eventos, marcas, paises):
      while True:
        print("\n\n\n-------------------------------------------------------------------------------")
        print("                     EVENTOS DE ATLETISMO\n")
        print("                     REGISTRAR EVENTOS\n")
        print(" 1. Agregar eventos")
        print(" 2. Consultar eventos")
        print(" 3. Modificar eventos")
        print(" 4. Eliminar eventos")
        print(" 0. Salir")
        opcion = input("    OPCIÓN ")              
        match opcion: 
            case "1":
                agregar_eventos(eventos, paises)
            case "2":
                consultar_eventos(eventos)
            case "3":
                modificar_eventos(eventos, paises)
            case "4":
                eliminar_eventos(eventos, marcas)  
            case "0":
                break
            case _: 
                input("OPCIÓN NO ES PERMITIDA. DAR <INTRO>")

#Agregar eventos
# E: lista de eventos, lista de paises
# S: lista de eventos actualizada
def agregar_eventos(eventos, paises):
    while True:
        print("\n\n\n-------------------------------------------------------------------------------")
        print("                     EVENTOS DE ATLETISMO\n")
        print("                     AGREGAR EVENTOS\n")
        while True:
            while True:
                ident = input("Identificacion del evento         ")
                if ident == "C":
                    return
                ident_2 = int(ident)
                existe_evento, evento = obtener_datos(eventos, ident_2)
                if existe_evento:
                        print("ESTA PRUEBA YA ESTÁ REGISTRADA, NO SE PUEDE AGREGAR") 
                else:
                    if 1 <= ident_2:
                        break
                    else:
                        print("LA IDENTIFICACION DEBE SER UN ENTERO >= 1")
            while True:
                nombre = input("Nombre del evento         ")
                if  len(nombre) >= 5 and len(nombre) <= 60:
                    break
                else:
                    print("El nombre debe tener entre 5 y 60 caracteres")

            while True:
                pais = input("Pais anfitrion        ")
                if len(pais) >= 3:
                    p = obtener_datos(paises, pais)
                    if p == True:
                        break
                    else:
                        print("El pais debe estar en codificacion ISO 3166-1-alfa-3")
                else:
                    print("El pais debe tener mas de 3 caracteres ")

            while True:
                lugar = input("Lugar         ")
                if len(lugar) >= 5 and len(lugar) <= 60:
                    break
                else:
                    print("EL LUGAR DEBE TENER ENTRE 5 Y 60 CARACTERES")

            while True:
                fecha_ini = input("Fecha de inicio              ")
                fecha_fin = input("Fecha de finalizacion         ")
                if fecha_ini <= fecha_fin:
                    break
                else:
                    print("FECHA DE INICIO DEBE SER MENOR O IGUAL QUE FECHA DE FINALIZACION")

            cond = aceptar_cancelar()
            if cond == False:
                break
            elif cond == True:
                eventos.append([ident_2, nombre, pais, lugar, fecha_ini, fecha_fin])
                break

#Consultar eventos
# E: lista de eventos
# S: datos del evento consultado
def consultar_eventos(eventos):
    while True:
        print("\n\n\n-------------------------------------------------------------------------------")
        print("                     EVENTOS DE ATLETISMO\n")
        print("                     CONSULTAR EVENTOS\n")
        while True:
            ident = input("Identificacion del evento       ")
            if ident == "C":
                return
            ident_2 = int(ident)
            existe_evento, evento = obtener_datos(eventos, ident_2)
            if existe_evento == False:
                print("ESTE EVENTO NO ESTA REGISTRADO, NO SE PUEDE CONSULTAR")
            else:
                for i in eventos:
                    if ident_2 in i:
                        print("Nombre del evento                 ", i[1])
                        print("Pais anfitrion               ", i[2])
                        print("Lugar                                  ", i[3])
                        print("Fecha de inicio                                 ", i[4])
                        print("Fecha de finalizacion            ", i[5])
                        break
                if aceptar():
                    break

#Modificar eventos
# E: lista de eventos, lista de paises
# S: lista de eventos actualizada
def modificar_eventos(eventos, paises):

    while True:
        while True:
            print("\n\n\n-------------------------------------------------------------------------------")
            print("                     EVENTOS DE ATLETISMOS\n")
            print("                     MODIFICAR EVENTOS\n")
            i = 0
            while True:
                while True:
                    ident = input("Identificacion del evento          ")
                    if ident == "C":
                        return
                    ident_2 = int(ident)
                    existe_evento, evento = obtener_datos(eventos, ident_2)
                    if existe_evento == False:
                        print("ESTE EVENTO NO ESTA REGISTRADO, NO SE PUEDE MODIFICAR")      
                    else:
                        break
                for j in eventos:
                    if ident_2 in j:
                        print("\nNombre del evento                 ", j[1])
                            
                        while True:
                            nombre = input("   NOMBRE MODIFICADO                 ")
                            if nombre == "":
                                nombre = j[1]
                            if  len(nombre) >=5 and len(nombre) <= 60:
                                break
                            else:
                                input("DATO DEBE TENER ENTRE 5 Y 60 CARACTERES. DAR <INTRO>")
                                    
                        print("Pais anfitrion                 ", j[2])
                        while True:
                            pais = input("PAIS MODIFICADO      ")
                            if pais == "":
                                pais = j[2]
                            if len(pais) >= 3:
                                p = obtener_datos(paises, pais)
                                if p:
                                    break
                                else:
                                    print("El pais debe estar en codificacion ISO 3166-1 alfa-3") 
                            else:
                                print("El pais debe tener mas de 3 caracteres")
                                
                        print("Lugar                 ", j[3])
                            
                        while True:
                            lugar = input("   LUGAR MODIFICADO                 ")
                            if lugar == "":
                                lugar = j[3]
                            if len(lugar) >= 5 and len(lugar) <= 60:
                                break
                            else:
                                print("EL LUGAR DEBE TENER ENTRE 5 Y 60 CARACTERES")
                                     
                        print("Fecha de inicio                 ", j[4])
                        while True:
                            fecha_ini = input("FECHA DE INICIO MODIF          ")
                            if fecha_ini == "":
                                fecha_ini = j[4]
                            print("Fecha de finalizacion          ", j[5])
                            fecha_fin = input("FECHA FIN MODIF       ")
                            if fecha_fin == "":
                                fecha_fin = j[5]
                            if fecha_ini <= fecha_fin:
                                break
                            else:
                                print("FECHA DE INICIO DEBE SER MENOR O IGUAL QUE FECHA DE FINALIZACION")


                        cond = aceptar_cancelar()
                        if cond == False:
                            break
                        elif cond == True:
                            ind = eventos.index(j)
                            eventos.remove(j)
                            eventos.insert(ind, [ident, nombre, pais, lugar, fecha_ini, fecha_fin])
                            break
 #Eliminar eventos           

#Eliminar eventos
# E: lista de eventos, lista de marcas
# S: lista de eventos actualizada
def eliminar_eventos(eventos, marcas):
    while True:
        print("\n\n\n-------------------------------------------------------------------------------")
        print("                     EVENTOS DE ATLETISMOS\n")
        print("                     ELIMINAR EVENTOS\n")
        cond = 0
        while True:
            ident = input("Identificacion del evento          ")
            if ident == "C":
                return
            ident_2 = int(ident)
            existe_evento, evento = del_eventos(eventos, ident_2)

            if existe_evento == False:
                print("ESTE EVENTO NO ESTA REGISTRADO, NO SE PUEDE MODIFICAR")      
            else:
                break
            
        
            
        tiene_marcas, marca = del_eventos(marcas, ident_2)
        if tiene_marcas == False:
            for i in eventos:

                    if ident_2 in i:
                        print("Identificacion del evento", i[0])
                        print("Nombre del evento                 ", i[1])
                        print("Pais anfitrion               ", i[2])
                        print("Lugar                                  ", i[3])
                        print("Fecha de inicio                                 ", i[4])
                        print("Fecha de finalizacion            ", i[5])
                        break
 
            cond_2 = aceptar_cancelar()
            if cond_2 == False:
                pass
            elif cond_2 == True:
                while True:
                    for j in eventos:
                        if ident_2 in j:
                            cond += 1
                            break
                    if cond > 0:
                        break
                eventos.remove(j)
        else:
            print("EVENTO NO SE PUEDE ELIMINAR PORQUE TIENE MARCAS REGISTRADAS")

#menú crud marcas
#Menú para agregar, consultar, modificar y eliminar marcas
# E: lista de marcas, lista de eventos, lista de pruebas, lista de atletas
def crud_marcas(marcas, eventos, pruebas, atletas):
      while True:
        print("\n\n\n-------------------------------------------------------------------------------")
        print("                     EVENTOS DE ATLETISMO\n")
        print("                     REGISTRAR MARCAS\n")
        print(" 1. Agregar marcas")
        print(" 2. Consultar marcas")
        print(" 3. Modificar marcas")
        print(" 4. Eliminar marcas")
        print(" 0. Salir")
        opcion = input("    OPCIÓN ")              
        match opcion: 
            case "1":
                agregar_marcas(marcas, eventos, pruebas, atletas)
            case "2":
                consultar_marcas(marcas)
            case "3":
                modificar_marcas(marcas)
            case "4":
                eliminar_marcas(marcas)  
            case "0":
                break
            case _: 
                input("OPCIÓN NO ES PERMITIDA. DAR <INTRO>")

#Agregar marcas
# E: lista de marcas, lista de eventos, lista de pruebas, lista de atletas
# S: lista de marcas actualizada
def agregar_marcas(marcas, eventos, pruebas, atletas):
     while True:
        combinacion = []
        print("\n\n\n-------------------------------------------------------------------------------")
        print("                     EVENTOS DE ATLETISMO\n")
        print("                     AGREGAR MARCAS\n")
        while True:
            while True:
                ident = input("Identificacion del evento      ")
                if ident == "C":
                    return
                ident_2 = int(ident)
                existe_evento, evento = del_eventos(eventos, ident_2)
                if existe_evento == False:
                        print("EVENTO NO EXISTE, NO SE PUEDE AGREGAR A MARCAS") 
                else:
                    for i in eventos:
                        if ident_2 in i:
                            print(i[1])
                            break
                    break
            while True:
                cod = input("Codigo de prueba       ")
                existe_cod, codi = obtener_datos(pruebas, cod)
                if existe_cod == False:
                            print("PRUEBA NO EXISTE, NO SE PUEDEN AGREGAR MARCAS")
                else:
                    for x in pruebas:
                        if cod in x:
                            print(x[1:])
                            break
                    break
            while True:
                ident_a = int(input("Identificacion del atleta       "))
                existe_ident_a, tup = obtener_datos(atletas, ident_a)
                if existe_ident_a == False:
                    print("ATLETA NO EXISTE, NO SE PUEDEN AGREGAR MARCAS")
                else:
                    for y in atletas:
                        if ident_a in y:
                            print(atletas[1:4])
                            break
                    break
            for z in marcas:
                if ident in z:
                    for c in z:
                        if cod in c:
                            for v in c:
                                if ident_a in v:
                                    print("MARCA YA ESTÁ REGISTRADA, NO SE PUEDE AGREGAR")
                                else:
                                    break
                        else:
                            break
                else:
                    break
            while True:
                dorsal = int(input("Dorsal asignado"))
                evento, k = del_eventos(eventos, ident_2)
                existe_dorsal = obtener_datos_marcas(marcas, dorsal)
                if existe_dorsal:
                    print("DORSAL FUE ASIGNADO AL ATLETA")
                else:
                    break
            op = input("Marca en T o M?    ")
            if op == "T":
                marca = input("Marca              ")
                m = valid_tiempo(marca)
                
            else:
                if op == "M":
                    marca = input("Marca           ")
                    t = valid_tiempo(marca)

                
            cond = aceptar_cancelar()
            if cond == False:
                break
            else:
                marcas.append([ident_2, [cod, (ident_a, dorsal, marca)]])
                     
#Consultar marcas
# E: lista de marcas
# S: datos de lista de marcas consultada
def consultar_marcas(marcas):
    while True:
        print("\n\n\n-------------------------------------------------------------------------------")
        print("                     EVENTOS DE ATLETISMO\n")
        print("                     CONSULTAR MARCAS\n")
        while True:
            ident =input("Identificacion del evento      ")
            if ident == "C":
                return
            ident_2 = int(ident)
            cod = input("Codigo de prueba       ")
            ident_a = int(input("Identificacion del atleta       "))
            existe_comb, datos = combinacion_datos(marcas, ident_2, cod, ident_a)
            if existe_comb == False:
                print("MARCA NO ESTÁ REGISTRADA, NO SE PUEDE CONSULTAR")
            else:
                print("Dorsal asignado           ", datos[1])
                print("Marca                  ", datos[2])
                if aceptar():
                    break

#Modificar marcas
# E: lista de marcas
# S: lista de marcas actualizada
def modificar_marcas(marcas):
    while True:
        while True:
            
            print("\n\n\n-------------------------------------------------------------------------------")
            print("                     EVENTOS DE ATLETISMOS\n")
            print("                     MODIFICAR MARCAS\n")
            #Se verifica que la combinacion de datos exista
            ident =input("Identificacion del evento      ")
            if ident == "C":
                return
            ident_2 = int(ident)
            cod = input("Codigo de prueba       ")
            ident_a = int(input("Identificacion del atleta       "))
            existe_comb, datos = combinacion_datos(marcas, ident_2, cod, ident_a)
            if existe_comb == False:
                print("MARCA NO ESTÁ REGISTRADA, NO SE PUEDE MODIFICAR")
            else:
                try:
                    print("Dorsal asignado           ", datos[1])
                    dorsal = int(input("   DORSAL MODIF        "))
                except ValueError:
                        if dorsal == "":
                            dorsal = datos[1]
                try:
                    print("Marca                  ", datos[2])
                    marc = int(input("     MARCA MODIF      "))
                except ValueError:
                        if marc == "":
                            marc = datos[2]
                cond = aceptar_cancelar()
                if cond == False:
                    break
                else:
                    for i, j in enumerate(marcas):
                        if ident == j[0]:
                            break
                    for x, y in enumerate(j[1:]):
                        if cod == y[0]:
                            break

                    marcas[i][x+1] = [cod, (ident_a, dorsal, marc)]
                    print(marcas)#falta
                    
#Eliminar marcas
# E: lista de marcas
# S: lista de marcas actualizada
def eliminar_marcas(marcas):
    while True:

        print("\n\n\n-------------------------------------------------------------------------------")
        print("                     EVENTOS DE ATLETISMO\n")
        print("                     ELIMINAR MARCAS\n")
        while True:
            #Se verifica que la combinacion de datos exista
            ident = input("Identificacion del evento      ")
            if ident == "C":
                return
            ident_2 = int(ident)
            cod = input("Codigo de prueba    ")
            ident_a = int(input("Identificacion del atleta       "))
            for i in marcas:
                if ident_2 == i[0]:
                    break
            for j in i[1:]:
                if cod == j[0]:
                    break
            for x in j[1:]:
                if ident_a == x[0]:
                    break
            existe_comb, datos = combinacion_datos(marcas, ident_2, cod, ident_a)
            if existe_comb == False:
                print("MARCA NO ESTA REGISTRADA, NO SE PUEDE ELIMINAR")
            else:
                cond = aceptar_cancelar()
                if cond == False:
                    break
                else:
                    op = input("CONFIRMA LA ELIMINACIÓN (SI/NO)    ")
                    if op == "NO":
                        break
                    else:
                        #Se elimina la marca
                        j.remove(x)
                        for i2 in range(len(marcas)):
                            for j2 in range(len(marcas[i2])):
                                if marcas[i2][j2] == j:
                                    marcas[i2].remove(j)
                                    break


#Crud análisis de datos
#Menú para generar un PDF con los datos solicitados
# E: lista de marcas, lista de eventos, lista de pruebas, lista de atletas
def crud_analisis(marcas, eventos, pruebas, atletas):
    while True:
        print("\n\n\n-------------------------------------------------------------------------------")
        print("                     EVENTOS DE ATLETISMO\n")
        print("                      ANALISIS DE DATOS\n")
        print(" 1. Marcas por evento")
        print(" 2. Marcas por atleta")
        print(" 3. Mejores marcas por prueba")
        print(" 0. FIN")
        opcion = input("    OPCIÓN _  ")              
        match opcion: 
            case "1":
                marcas_por_evento(marcas, eventos, pruebas, atletas)
            case "2":
                marcas_por_atleta(marcas, eventos, pruebas, atletas)
                
            case "3":
                mejores_marcas_por_prueba(marcas, eventos, pruebas, atletas)
                 
            case "0":
                break
            case _: 
                input("OPCIÓN NO ES PERMITIDA. DAR <INTRO>")
                    
#Marcas por evento
#Menu para generar un PDF con las marcas de un evento especifico o de todos los eventos
#E: lista de marcas, lista de eventos, lista de pruebas, lista de atletas 
def marcas_por_evento(marcas, eventos, pruebas, atletas):                
    op = input("1. Marcas de un evento especifico\n2. Marcas de todos los eventos       ")
    match op:            
        case "1":
            e_especifico(marcas, eventos, pruebas, atletas)
        case "2":
            todos_eventos(marcas, eventos, pruebas, atletas)
        case _:
            input("OPCIÓN NO ES PERMITIDA. DAR <INTRO>")

#Marcas de un evento especifico
#E: lista de marcas, lista de eventos, lista de pruebas, lista de atletas
#S: PDF de marcas de un evento especifico
def e_especifico(marcas, eventos, pruebas, atletas):
    result = []
    #se crea el pdf
    pdf = canvas.Canvas("C:/Users/Lenovo/Desktop/pdf.pdf")
    pdf.drawString(260, 800, "Aplicación de atletismo")
    pdf.drawString(70, 780, "Marcas por evento")
    pdf.drawString(70, 760, "Evento especifico")

    ident = int(input("Identificacion del evento      "))
    while True:
        v = 0
        for i in marcas:
            
            for e in i:
                
                if ident == i[0]:
                    result.append(ident)
                    v += 1
                    break
                else:
                    print("EVENTO NO ESTA REGISTRADO")
            if v > 0:
                break
        if v > 0:
            break
           
    
    for j in eventos:
        if ident in j:
            result.append(j[1])
            break
    
    pdf.drawString(80, 740, "Evento: " + str(result[0]))
   

    for x in i[1:]:
        for y in pruebas:
            if x[0] == y[0]:
                
                result.append([y[0], y[2], y[3], y[4]])
                break

    esp_vertical_1 = 710
    # consiguien los datos necesarios
    for c in result[2:]:
        
        pdf.drawString(80, esp_vertical_1, "Prueba: ")
        pdf.drawString(125, esp_vertical_1, result[1])
        pdf.drawString(380, esp_vertical_1, c[0])
        pdf.drawString(420, esp_vertical_1, "Categoría: " + c[1])
        pdf.drawString(520, esp_vertical_1, "Sexo: " + c[2])
    
        esp_vertical_2 = esp_vertical_1 - 30

        pdf.drawString(80, esp_vertical_2, "Nombre del atleta")
        pdf.drawString(220, esp_vertical_2, "Dorsal")
        pdf.drawString(360, esp_vertical_2, "Marca")
        pdf.drawString(500, esp_vertical_2, "Posición")
        num = 1
        for v in marcas:
            if ident in v:
                for w in v[1:]:
                    if c[0] == w[0]:
                        for u in w[1:]:
                            if len(u) > 0:
                                for x in atletas:
                                    if u[0] in x:
                                        esp_vertical_2 -= 30
                                        nomb1 = str(x[1])
                                        nomb2 = str(x[2])
                                        nomb3 = str(x[3])
                                        nomb = nomb1 + " " + nomb2 + " " + nomb3
                                        pdf.drawString(80, esp_vertical_2, str(nomb))
                                        pdf.drawString(220, esp_vertical_2, str(u[1]))
                                        pdf.drawString(360, esp_vertical_2, str(u[2]))
                                        pdf.drawString(500, esp_vertical_2, str(num))
                                        num += 1
                                        break

        esp_vertical_1 = esp_vertical_2 - 30

    pdf.save()


#Marcas de todos los eventos    
#E: lista de marcas, lista de eventos, lista de pruebas, lista de atletas
#S: PDF de marcas de todos los eventos
def todos_eventos(marcas, eventos, pruebas, atletas):
    
    #Se crea el PDF
    pdf = canvas.Canvas("C:/Users/Lenovo/Desktop/pdf.pdf")
    pdf.drawString(260, 800, "Aplicación de atletismo")
    pdf.drawString(70, 780, "Marcas por evento")
    pdf.drawString(70, 760, "Todos los eventos")

    esp_vertical_1 = 710

    for i in marcas:
        pdf.drawString(80, esp_vertical_1, "Evento: " + str(i[0]))
        for j in eventos:
            if i[0] == j[0]:
                pdf.drawString(200, esp_vertical_1, j[1])
               
                break
    
    esp_vertical_2 = esp_vertical_1 - 20
    #Se crea la tabla de pruebas
    for c in i[1:]:
        for l in pruebas:
            pdf.drawString(80, esp_vertical_2, "Prueba: " + str(c[0]))
            pdf.drawString(380, esp_vertical_2, l[1])
            pdf.drawString(420, esp_vertical_2, "Categoría: " + l[2])
            pdf.drawString(520, esp_vertical_2, "Sexo: " + l[3])
            break
    
        esp_vertical_3 = esp_vertical_2 - 20
        #Se crea un ciclo para agegar los datos
        pdf.drawString(80, esp_vertical_3, "Nombre del atleta")
        pdf.drawString(220, esp_vertical_3, "Dorsal")
        pdf.drawString(360, esp_vertical_3, "Marca")
        pdf.drawString(500, esp_vertical_3, "Posición")
        num = 1
        for v in marcas:
            for w in v[1:]:                
                for u in w[1:]:
                    if len(u) > 0:       
                        for x in atletas:
                            u = list(u)
                            esp_vertical_3 -= 20
                            nomb1 = str(x[1])
                            nomb2 = str(x[2])
                            nomb3 = str(x[3])
                            nomb = nomb1 + " " + nomb2 + " " + nomb3
                            pdf.drawString(80, esp_vertical_3, str(nomb))
                            pdf.drawString(220, esp_vertical_3, str(u[1]))
                            pdf.drawString(360, esp_vertical_3, str(u[2]))
                            pdf.drawString(500, esp_vertical_3, str(num))
                            num += 1          
        esp_vertical_2 = esp_vertical_3 - 20
        esp_vertical_1 = esp_vertical_3 - 40 
    pdf.save()


#Marcas por atleta
#Menu para escoger entre marcas de un atleta especifico o de todos los atletas
#E: lista de marcas, lista de eventos, lista de pruebas, lista de atletas
def marcas_por_atleta(marcas, eventos, pruebas, atletas):
    op = input("1. Marcas de un atleta especifico\n2. Marcas de todos los atleas       ")
    match op:            
        case "1":
            a_especifico(marcas, eventos, pruebas, atletas)
        case "2":
            todos_atletas(marcas, atletas)
        case _:
            input("OPCIÓN NO ES PERMITIDA. DAR <INTRO>")

#Marcas de un atleta especifico
#E: lista de marcas, lista de eventos, lista de pruebas, lista de atletas
#S: PDF de marcas de un atleta especifico
def a_especifico(marcas, eventos, pruebas, atletas):
    result = []
    ident = int(input("Ingrese el número de identificación del atleta: "))
    for j in atletas:
        if ident in j:
            x = j[0]
            result.append(j)
            break
    #Se crea el PDF
    pdf = canvas.Canvas("C:/Users/Lenovo/Desktop/pdf.pdf")
    pdf.drawString(260, 800, "Aplicación de atletismo")
    pdf.drawString(70, 780, "Marcas por atleta")
    

    esp_vertical_1 = 710

    nomb1 = str(j[1])
    nomb2 = str(j[2])
    nomb3 = str(j[3])
    nomb = nomb1 + " " + nomb2 + " " + nomb3
    pdf.drawString(80, 730, "Nombre atleta: "+ nomb)

    for y in marcas:
        for v in y[1:]:
            for t in v[1:]:
                
                if len(t) > 0 and x == t[0]:
                    result.append(v[0])
                    g = t
                    break
    esp_vertical_2 = esp_vertical_1 - 30
    
    for h in pruebas:
        if len(v) > 0 and v[0] == h[0]:
            
            pdf.drawString(80, esp_vertical_1, "Prueba: " + str(h[0]))
            pdf.drawString(360, esp_vertical_1, "Categoría: " + h[2])
            pdf.drawString(500, esp_vertical_1, "Sexo: " + h[3])
            break
            
        
    pdf.drawString(80, esp_vertical_2, "Nombre del evento")
    pdf.drawString(360, esp_vertical_2, "Marca")
    pdf.drawString(480, esp_vertical_2, "Posición en el evento")
    num = 1
    esp_vertical_3 = esp_vertical_2 - 30
    #se recorren las listas para obtener los datos
    for v2 in eventos:
        for g in marcas:
            for q in g[1:]:
                for w in q[1:]:
                        if len(w) > 0:    
                            if v2[0] == g[0]:

                                w = list(w)
                                
                                esp_vertical_2 -= 30
                                pdf.drawString(80, esp_vertical_3, str(v2[1]))
                                pdf.drawString(360, esp_vertical_3, str(w[2]))
                                pdf.drawString(500, esp_vertical_3, str(num))
                                num += 1
                                break
    esp_vertical_1 = esp_vertical_3 - 20
    esp_vertical_2 = esp_vertical_3 - 40
    pdf.save()


#Marcas de todos los atletas
#E: lista de marcas, lista de atletas
#S: PDF de marcas de todos los atletas
def todos_atletas(marcas, atletas):
    #se crea el pdf
    pdf = canvas.Canvas("C:/Users/Lenovo/Desktop/pdf.pdf")
    pdf.drawString(260, 800, "Aplicación de atletismo")
    pdf.drawString(70, 780, "Marcas de todos los atletas")
    #se crean las variables para las posiciones verticales de los textos
    esp_vertical_1 = 710
    esp_vertical_2 = 690
    esp_vertical_3 = 670
    #se crea el ciclo para recorrer las listas y obtener sus datos
    for j in atletas:
        nomb1 = str(j[1])
        nomb2 = str(j[2])
        nomb3 = str(j[3])
        nomb = nomb1 + " " + nomb2 + " " + nomb3
        pdf.drawString(80, esp_vertical_1, "Nombre atleta: "+ nomb)
        esp_vertical_2 = esp_vertical_1 - 20
        num = 1
        for y in marcas:
            for v in y[1:]:
                for t in v[1:]:
                    t = list(t)
                    if len(t) > 0 and j[0] == t[0]:
                        pdf.drawString(80, esp_vertical_2, "Prueba: ")
                        pdf.drawString(360, esp_vertical_2, "Marca: ")
                        pdf.drawString(500, esp_vertical_2, "Posicion en el evento")
                        pdf.drawString(80, esp_vertical_2-20, str(v[0]))
                        pdf.drawString(360, esp_vertical_2-20,str(t[2]))
                        pdf.drawString(500, esp_vertical_2-20, str(num))
                        esp_vertical_3 = esp_vertical_2 - 20
                        num += 1
                        break
                esp_vertical_2 = esp_vertical_3 - 20
        esp_vertical_1 = esp_vertical_3 - 20
    pdf.save()

#Mejores marcas por prueba
#E: lista de marcas, lista de eventos, lista de pruebas, lista de atletas
#S: PDF de mejores marcas por prueba
def mejores_marcas_por_prueba(marcas, eventos, atletas):
    #Se crea el PDF
    pdf = canvas.Canvas("C:/Users/Lenovo/Desktop/pdf.pdf")
    pdf.drawString(260, 800, "Aplicación de atletismo")
    pdf.drawString(70, 780, "Mejores marcas por prueba")
    #Se crea la variable que indica la posición vertical del texto
    esp_vertical_1 = 710
    esp_vertical_2 = 690
    esp_vertical_3 = 670

    ini = int(input("Ingrese la fecha de inicio     "))
    fin = int(input("Ingrese la fecha de finalización    "))
    #Se recorren las listas para conseguir los datos
    for j in eventos:
        if j[-2] == ini and j[-1] == fin:
            pdf.drawString(80, esp_vertical_1, "Nombre del evento: "+ j[1])
            esp_vertical_2 = esp_vertical_1 - 20
            num = 1
            esp_vertical_3 = esp_vertical_2 - 30
            for y in marcas:
                for v in y[1:]:
                    for t in v[1:]:
                        t = list(t)
                        if len(t) > 0:
                            for h in atletas:
                                if h[0] == t[0]:
                                    nombre = h[1] + " " + h[2] + " " + h[3]
                                    pdf.drawString(20, esp_vertical_2, "Nombre del atleta:")
                                    pdf.drawString(140, esp_vertical_2, "Pais:")
                                    pdf.drawString(240, esp_vertical_2, "Marca:")
                                    pdf.drawString(360, esp_vertical_2, "Posicionen")
                                    pdf.drawString(360, esp_vertical_2-10, "en el evento:")
                                    pdf.drawString(460, esp_vertical_2, "Nombre de la prueba:")
                                    pdf.drawString(20, esp_vertical_3, nombre)
                                    pdf.drawString(140, esp_vertical_3, str(h[5]))
                                    pdf.drawString(240, esp_vertical_3,str(t[2]))
                                    pdf.drawString(360, esp_vertical_3, str(num))
                                    pdf.drawString(420, esp_vertical_3, str(j[1]))
                                    num += 1
                                    break
                            esp_vertical_2 = esp_vertical_3 - 20
                            esp_vertical_1 = esp_vertical_3 - 40
    pdf.save()






#Manual se usuario
#E: -
#S: PDF de manual de usuario
def ayuda():
    #Se abre el manual de usuario
    os.system("C:/Users/Lenovo/Desktop/proyecto_1_Joshua_Guerra/manual_de_usuario_eventos_de_atletismo.PDF.pdf")

#Opción 8, imprime la información del proyecto
#E: -
#S: información del proyecto
def acerca_de():
    while True:
        print("\n\n\n-------------------------------------------------------------------------------")
        print("                             ACERCA DE\n")
        print("Nombre: EVENTOS DE ATLETISMO\n")
        print("Version 1.0\nFecha: 20/04/2023\nAutor: Joshua Fabián Guerra Castillo\n")
        cond = aceptar()
        if cond == True:
            break


# FUNCIÓN PRINCIPAL
# categorías por edad predeterminadas
categoria = ("U12", "U13", "U14", "U15", "U16", "U17", "U18", "U20", "MAYOR", "MASTER")

#Listas
disciplinas = []
pruebas = []
eventos = []
atletas = []
marcas = []
paises = []

# Menú principal
while True:
    print("\n\n\n-------------------------------------------------------------------------------")
    print("                     EVENTOS DE ATLETISMO\n")
    print(" 1. Registrar disciplinas")
    print(" 2. Registrar pruebas por disciplina")
    print(" 3. Registrar atletas")
    print(" 4. Registrar eventos")
    print(" 5. Registrar marcas")
    print(" 6. Análisis de marcas")
    print(" 7. Ayuda")
    print(" 8. Acerca de")
    print(" 0. FIN")
    opcion = input("    OPCIÓN ")              
    match opcion: #  (de v3.10.x) analiza valor de opción y ejecuta el caso respectivo
        case "1":
            crud_disciplinas(disciplinas)
            
        case "2":
            crud_pruebas(pruebas, eventos, categoria, marcas)
              
        case "3":
            crud_atletas(atletas, eventos, marcas, paises)

        case "4":
            crud_eventos(eventos, marcas, paises)

        case "5":
            crud_marcas(marcas, eventos, pruebas, atletas)

        case "6":
            crud_analisis(marcas, eventos, pruebas, atletas)
            
        case "7":
            ayuda()
           
        case "8":
            acerca_de()

        case "0":
            break
        case _: # se ejecuta cuando ninguna de las opciones anteriores se ejecutó
            input("OPCIÓN NO ES PERMITIDA. DAR <INTRO>")
            
print("FIN DEL PROGRAMA")
