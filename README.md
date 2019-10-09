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
