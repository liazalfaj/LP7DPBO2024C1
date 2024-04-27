# LP7DPBO2024C1
## Janji
Saya Amelia Zalfa Julianti dengan NIM 2203999 mengerjakan Latihan 7 mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan sesuai dengan apa yang telah dispesifikasikan. Aamiin.

## Desain Program
1. Kelas FlappyBird
   - Mengimplementasikan `ActionListener` dan `KeyListener`
   - Menginisialisasi dan menampilkan elemen-elemen permainan seperti latar belakang, burung, pipa, skor, dan loop permainan.
   - Mengatur gerakan burung, mendeteksi tabrakan dengan pipa, dan menangani penempatan pipa baru secara berkala.
   - Menangani input dari pengguna (melalui tombol spasi) untuk membuat burung melompat
   - Menampilkan pesan "Game Over" dan opsi untuk memulai permainan baru.
2. Kelas Pipe
   - Merepresentasikan objek pipa yang harus dihindari oleh burung.
   - Memiliki atribut seperti posisi, dimensi, gambar, dan kecepatan gerak.
   - Menyediakan metode untuk mengakses dan mengubah atribut pipa.
3. Kelas Player
   - Merepresentasikan objek burung yang dikendalikan oleh pemain.
   - Memiliki atribut seperti posisi, dimensi, gambar, dan kecepatan vertikal.
   - Menyediakan metode untuk mengakses dan mengubah atribut pemain.
   - Memiliki metode `collidesWith` untuk mendeteksi tabrakan antara burung dan pipa.
4. App
   - Merupakan kelas utama yang membuat JFrame untuk menampilkan menu utama permainan.
   - Memiliki tombol "Start" yang, ketika ditekan, akan menutup jendela menu utama dan membuka jendela permainan utama (FlappyBird).

## Alur Program
Alur program permainan Flappy Bird ini adalah sebagai berikut:

1. Program dimulai dengan menampilkan jendela menu utama yang berisi judul "Flappy Bird" dan tombol "Start".

2. Ketika tombol "Start" ditekan, jendela menu utama ditutup, dan jendela permainan utama (`FlappyBird`) dibuka.

3. Pada jendela permainan utama, objek `FlappyBird` diinisialisasi, yang merupakan kelas utama yang menangani logika permainan.

4. Kelas `FlappyBird` memuat aset-aset permainan (gambar latar belakang, burung, dan pipa) dan menginisialisasi objek `Player` (burung) dan daftar `pipes` (pipa).

5. Timer `pipesCoolDown` dijalankan setiap 1,5 detik untuk memanggil metode `placePipes()` yang akan menambahkan pasangan pipa baru ke dalam daftar `pipes`.

6. Timer `gameLoop` dijalankan dengan kecepatan 60 frame per detik (fps) untuk mengeksekusi metode `actionPerformed()` yang memanggil metode `move()`.

7. Metode `move()` mengupdate posisi burung berdasarkan kecepatan vertikalnya dan gravitasi. Metode ini juga menggerakkan pipa-pipa ke arah kiri dan mendeteksi tabrakan antara burung dan pipa. Jika burung melewati pipa, skor akan bertambah.

8. Metode `draw()` dipanggil untuk menggambar latar belakang, burung, pipa-pipa, dan skor pada `JPanel`.

9. Ketika tombol spasi ditekan, metode `keyPressed()` akan memanggil metode `setVelocityY()` pada objek `Player` untuk membuat burung melompat.

10. Jika terjadi tabrakan antara burung dan pipa atau burung menyentuh tanah, variabel `gameOver` akan diatur menjadi `true`, dan pesan "Game Over" akan ditampilkan pada layar.

11. Ketika permainan berakhir, pengguna dapat menekan tombol "R" untuk memulai permainan baru dengan memanggil metode `restartGame()`.

12. Dalam metode `restartGame()`, daftar `pipes` dibersihkan, posisi burung diatur kembali, variabel `gameOver` diatur menjadi `false`, dan skor diatur kembali menjadi 0.
## Dokumentasi
https://github.com/liazalfaj/LP7DPBO2024C1/assets/114666885/bd74ca27-16a4-4b56-b76f-0f42f2e09ec2

