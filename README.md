# Tutorial APAP

## Authors

* **YUDHA PRADIPTA RAMADAN** - *1706043424* - *B*

---
## Tutorial 1
### What I have learned today
(Masukkan pertanyaan yang diikuti jawaban di setiap nomor, contoh seperti dibawah. Anda juga boleh
menambahkan catatan apapun dibagian ini)
#### Github
1. Apa itu Issue Tracker? Masalah apa yang dapat diselesaikan dengan Issue Tracker?
(Tuliskan jawabanmu)
2. Apa perbedaan dari git merge dan merge --squash?
#### Spring
3. Apa itu library & dependency?
4. Apa itu Maven? Mengapa kita perlu menggunakan Maven?
5. Apa alternatif dari Maven?

### What I did not understand
(tuliskan apa saja yang kurang Anda mengerti, Anda dapat men-_check_ apabila Anda sudah mengerti
dikemudian hari, dan tambahkan tulisan yang membuat Anda mengerti)
- [ ] Kenapa saya harus belajar APAP?
- [x] Kenapa?


Karena …
(Anda dapat membuat tampilan code dalam README.md menjadi lebih baik. Cari tahu lebih dalam
tentang penulisan README.md pada github pada link
[berikut](https://help.github.com/en/articles/basic-writing-and-formatting-syntax))

## Tutorial 2
### What I have learned today

#### Pertanyaan 1: Cobalah untuk menambahkan sebuah restoran dengan mengakses link berikut:
http://localhost:8080/restoran/add?idRestoran=1&nama=PanyuFC&alamat=Kantin%20Fasilkom&nomorTelepon=14022

#### Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi

Whitelabel Error Page, karena template belum ada

#### Pertanyaan 2: Cobalah untuk menambahkan sebuah restoran dengan mengakses link berikut:
http://localhost:8080/restoran/add?idRestoran=2&nama=KentukuFC&alamat=Kantin%20FIK

#### Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi.

Whitelabel Error Page, karena untuk melakukan add restoran nomorTelepon harus ada

#### Pertanyaan 3: Jika Papa APAP ingin melihat restoran PanyuFC, link apa yang harus diakses?

http://localhost:8080/restoran/view?idRestoran=1
http://localhost:8080/restoran/view?idRestoran=1

#### Pertanyaan 4: Tambahkan 1 contoh restoran lainnya sesukamu. Lalu cobalah untuk mengakses
http://localhost:8080/restoran/viewall
#### apa yang akan ditampilkan? Sertakan juga bukti screenshotmu.

link tersebut akan menampilkan semua restoran yang ada pada listRestoran.

![alt text](https://i.ibb.co/XL2t3Bb/image.png "Screenshot viewall")


## Tutorial 3
### What I have learned today

#### 1. Pada class MenuDb, terdapat method findByRestoranIdRestoran, apakah kegunaan dari method tersebut?
method findByRestoranIdRestoran adalah method untuk yang mengemabalikan list menu dari restoran yang memiliki id sama dengan idRestoran.

#### 2. Pada class RestoranController, jelaskan perbedaan method addRestoranFormPage dan addRestoranSubmit?
addRestoranFormPage adalah untuk membuat restoran baru dan mengisi data restoran dari form add restoran
addRestoranSubmit adalah untuk menambah restoran baru dengan data-data yang telah diisi sebelumnya ke table restoran

#### 3. Jelaskan apa kegunaan dari JPA Repository?
Java Persistence API (JPA) adalah merupakan tool untuk mengolah data relasional. 

#### 4. Sebutkan dan jelaskan di bagian kode mana sebuah relasi antara RestoranModel dan MenuModel dibuat?
Di kelas RestoranModel di bagian @OneToMany menjelaskan bahwa relasi antara RestoranModel dan MenuModel adalah one-to-many.
Di kelas MenuModel dibagian @ManyToOne menjelaskan bahwa relasi antara MenuModel dan RestoranModel adalah many-to-one

![alt text](https://i.ibb.co/fN4G6sW/Untitled-Diagram.png "Screenshot relasi")

#### 5. Jelaskan kegunaan FetchType.LAZY, CascadeType.ALL, dan FetchType.EAGER
FetchType.LAZY : untuk jika ingin mengeload menu saat hanya disuruh. misalnya ingin mengeload id menu harus dilakukan method getId().
CascadeType.ALL : persistence akan menyebarkan semua operasi EntityManager (PERSIST, REMOVE, REFRESH, MERGE, DETACH)


## Tutorial 4
### What I have learned today

#### 1.Jelaskan yang anda pelajari dari melakukan latihan nomor 2, dan jelaskan tahapan bagaimana anda menyelesaikan latihan nomor 2
	1). Mengubah text di navbar-brand di fragments.html menjadi ${titleNavbar}
	2). Menambahkan modelAttribute di setiap URL mapping di RestoranController dan MenuController agar titleNavbar memiliki value nama page yang dibuka
	
#### 2. Jelaskan yang anda pelajari dari latihan nomor 3, dan jelaskan tahapan bagaimana anda menyelesaikan latihan nomor 3
	1). Mengubah file form-add-menu.html agar form add menu menyamping dengan cara membuat tabel dan box input menjadi kolom
	2). Membuat file addMenu.js dan membuat method menambah row dan menghapus row
	3). Mengubah file fragments.html agar file addMenu.js dapat berjalan
	4). Mengubah file form-add-menu.html agar button add row button delete row dapat memanggil method di addMenu.js

#### 3. Jelaskan perbedaan th:include dan th:replace
	th:include akan memasukkan konten fragments ke host tag (di tutorial ini host tag nya adalah <object>), sedangkan th:replace akan mereplace host tag dengan fragments.
	
#### 4. Jelaskan bagaimana penggunaan th:object beserta tujuannya
	th:object digunakan dengan th:action untuk form input. th:action digunakan untuk memberikan url sedangkan th:action digunakan untuk menspesifikasikan object yang akan diikat oleh data form yang disubmit

	
## Tutorial 5
### What I have learned today

#### Coverage MenuService sebelum unit test dibuat

![alt text](https://i.ibb.co/Tk2dvTs/test-coverage-0.jpg "Screenshot coverage sebelum")

![alt text](https://i.ibb.co/6byc1n4/Test-coverage-100.jpg "Screenshot coverage setelah")

#### 1. Jelaskan bagian mana saja dari test yang dibuat pada latihan no 2 adalah given, when, dan and then
a. Given
	bagian given adalah dari inisiasi dummyMenu hingga ketika menentukan memanggil method dari restoranService akan mengembalikan restoranViewed
b. When
	bagian when adalah ketika pemanggilan mockMvc.perform(get("/restoran/view?idRestoran=1")) dan akan mengembalikan halaman view
c. andThen
	bagian andThen adalah ketika pemanggilan method .andExpect(MockMvcResultMatchers.status().isOk()) dan .andExpect(model().attribute("resto", hasProperty("nama", is("dummy 1")))); yang berfungsi untuk mengecek status dan nama restoran

#### 2. Jelaskan perbedaan line coverage dan logic coverage.
line coverage: hanya menghitung jumlah line yang tercover saat melakukan testing
logic coverage: mengcover code yang berupa branching (if, else)

#### 3. Pada keadaan ideal, apa yang seharusnya dibuat terlebih dahulu, code atau unit test? Mengapa seperti itu? Apa akibatnya jika urutannya dibalik, adakah risiko tak terlihat yang mungkin terjadi?
pada keadaan ideal unit test harus dibuat terlebih dahulu karena unit test adalah sebagai bagaimana kode harus berjalan dan jika unit test dibuat setelah kode selesai akan meningkatkan resiko terjadinya error


## Tutorial 6
### What I have learned today

#### 1. Apa itu postman? Apa kegunaan dari postman?
Postman adalah aplikasi yang dapat mengakses method yang tidak dapat diakses oleh URL seperti PUT,DELETE,POST,dll. Kegunaan dari postman adalah untuk mengakses method tadi tanpa harus membuat terlebih dahulu sistem yang menerima API dari kita.

#### 2. Apa kegunaan dari annotation @JsonIgnoreProperties?
JsonIgnoreProperties adalah annotation yang dapat digunakan untuk membenamkan serialization properties atau menngabaikan proses read JSON properties 

#### 3. Apa itu ResponseEntity dan apa kegunaannya?
ResponseEntity adalah extention dari HttpEntoty yang menambahkan HttpStatusCode. Digunakan di RestTemplate dan method @Controller

## Tutorial 9
### What I have learned today

#### 1. Ceritakan langkah - langkah yang kalian lakukan untuk solve LATIHAN no.1, dan mengapa kalian melakukan langkah - langkah tersebut?
Untuk menyelesaikan latihan no 1, yang dilakukan adalah melakukan this.setState nama, alamat, nomorTelepon, dan rating menjadi string kosong ('') pada addRestoranHandler. Karena sebelumnya untuk melakukan add Restoran
nama, alamat, nomorTelepon, dan rating di setState menjadi apa yang diisi di form add restoran dan state dari nama, alamat, nomorTelepon, dan rating itu yang disubmit. Jadi disini setelah disubmit, nama, alamat, nomorTelepon, dan rating di setState kembali menjadi string kosong ('').

#### 2. Jelaskan fungsi dari async dan await!
- keyword "async" berfungsi untuk memanggil fungsi async yang berguna untuk melakukan data fetching dari url dan melakukan parse Json Response.
- await berfungsi untuk keluar dari fungsi async untuk sementara dan menlanjutkan nya kembali setelah task yang diberikan selesai

#### 3. Masukkan jawaban dari TODO (Screenshot) pada Component Lifecycle pada pertanyaan ini.
1. ![alt text](https://i.postimg.cc/ry7fzyCD/DO-1-2-tutorial9.png "")
2. ![alt text](https://i.postimg.cc/Rh8tp0d2/DO-2-1-tutorial9.png "")
3. ![alt text](https://i.postimg.cc/6QQPnj7q/DO-2-2-tutorial9.png "")
4. ![alt text](https://i.postimg.cc/BnzT7MYX/DO-2-3-tutorial9.png "")
5. ![alt text](https://i.postimg.cc/XqNBYCHR/DO-2-4-tutorial9.png "")

#### 4. Jelaskan fungsi dari componentDidMount, shouldComponentUpdate, componentDidUpdate, componentWillReceiveProps, componentWillUnmount. 
##### Notes : Penjelasan harus mencantumkan “kapan fungsi dipanggil” dan “use case apa saja yang biasanya menggunakan lifecycle method tersebut”.
- componentDidMount: melakukan task yang ditentukan as soon as component is mounted
contoh: ![alt text](https://i.postimg.cc/3JzGhdhw/tutorial94-1.png "componentDidMount")
pada contoh ini setelah component mounted, task yang akan dilakukan adalah log console, memanggil loadRestorans, dan melakukan setState pada filtered
- shouldComponentUpdate:  method ini berguna untuk mengeluarkan component dari update life cycle jika tidak ada alasan untuk 
meng apply render baru contoh:
![alt text](https://i.postimg.cc/tRMVj2Gb/tutorial94-2.png "shouldComponentUpdate")
- componentDidUpdate: metode ini tidak terpanggil pada initial render tetapi dipanggil setelah component terupdate.
metode ini baik digunakan untuk melakukan sesuatu pada DOM setelah component terupdate.
- componentWillReceiveProps: metode ini terpanggil sebelum mounted component menerima props baru.
metode ini baik digunakan untuk mengupdate state setelah ada props change.
- componentWillUnmount: metode ini dipanggil sebelum component di unmount atau destroyed.
Metode ini baik digunakan untuk membersihkan hal-hal yang perlu dibersihkan sebelum component di unmount atau destroyed.
