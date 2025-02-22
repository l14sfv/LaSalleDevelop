import tkinter as tk
from controllers.usuario_controller import UsuarioController
from controllers.producto_controller import ProductoController
from controllers.categoria_controller import CategoriaController

class App:
    def __init__(self, root):
        self.root = root
        self.root.title("Mercado Inteligente")

        self.usuario_controller = UsuarioController()
        self.producto_controller = ProductoController()
        self.categoria_controller = CategoriaController()

        self.create_widgets()

    def create_widgets(self):
        # Usuario Section
        tk.Label(self.root, text="Usuarios").grid(row=0, column=0)
        self.usuario_listbox = tk.Listbox(self.root)
        self.usuario_listbox.grid(row=1, column=0)
        tk.Button(self.root, text="Agregar Usuario", command=self.agregar_usuario).grid(row=2, column=0)

        # Producto Section
        tk.Label(self.root, text="Productos").grid(row=0, column=1)
        self.producto_listbox = tk.Listbox(self.root)
        self.producto_listbox.grid(row=1, column=1)
        tk.Button(self.root, text="Agregar Producto", command=self.agregar_producto).grid(row=2, column=1)

        # Categoria Section
        tk.Label(self.root, text="Categorias").grid(row=0, column=2)
        self.categoria_listbox = tk.Listbox(self.root)
        self.categoria_listbox.grid(row=1, column=2)
        tk.Button(self.root, text="Agregar Categoria", command=self.agregar_categoria).grid(row=2, column=2)

    def agregar_usuario(self):
        self.usuario_controller.crear_usuario("John", "Doe", "123456", "john@example.com", "1234567890", "johndoe", "password")
        self.actualizar_listas()

    def agregar_producto(self):
        self.producto_controller.crear_producto("Producto1", "Descripción1", 10.0, 5)
        self.actualizar_listas()

    def agregar_categoria(self):
        self.categoria_controller.crear_categoria("Categoria1", "Descripción1")
        self.actualizar_listas()

    def actualizar_listas(self):
        self.usuario_listbox.delete(0, tk.END)
        for usuario in self.usuario_controller.obtener_usuarios():
            self.usuario_listbox.insert(tk.END, usuario.nombre_usuario)

        self.producto_listbox.delete(0, tk.END)
        for producto in self.producto_controller.obtener_productos():
            self.producto_listbox.insert(tk.END, producto.nombre)

        self.categoria_listbox.delete(0, tk.END)
        for categoria in self.categoria_controller.obtener_categorias():
            self.categoria_listbox.insert(tk.END, categoria.nombre)

if __name__ == "__main__":
    root = tk.Tk()
    app = App(root)
    root.mainloop()
    