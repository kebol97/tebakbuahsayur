package com.cococue.tebakbuahsayur.data

import com.cococue.tebakbuahsayur.R

object GameRepository {

    fun getQuestions(category: GameCategory): List<Question> {
        return when (category) {
            GameCategory.FRUIT_VEG -> listOf(
                Question(
                    id = 1,
                    category = GameCategory.FRUIT_VEG,
                    title = "Buah apakah aku? Berwarna merah dan rasanya manis segar.",
                    imageRes = R.drawable.apel, // Ganti dengan R.drawable.apel (setelah file jpg/png dimasukkan ke res/drawable)
                    options = listOf("Apel", "Pisang", "Wortel", "Bayam"),
                    correctAnswer = "Apel"
                ),
                Question(
                    id = 2,
                    category = GameCategory.FRUIT_VEG,
                    title = "Buah apa aku? Panjang dan kulit berwarna kuning.",
                    imageRes = R.drawable.pisang,
                    options = listOf("Mangga", "Pisang", "Jeruk", "Brokoli"),
                    correctAnswer = "Pisang"
                ),
                Question(
                    id = 3,
                    category = GameCategory.FRUIT_VEG,
                    title = "Sayuran apa aku? Berwarna jingga dan disukai kelinci.",
                    imageRes = R.drawable.wortel,
                    options = listOf("Wortel", "Tomat", "Semangka", "Anggur"),
                    correctAnswer = "Wortel"
                ),
                Question(
                    id = 4,
                    category = GameCategory.FRUIT_VEG,
                    title = "Sayuran apa aku? Bentuknya seperti pohon mini berwarna hijau.",
                    imageRes = R.drawable.brokoli,
                    options = listOf("Bayam", "Brokoli", "Jagung", "Kentang"),
                    correctAnswer = "Brokoli"
                ),
                Question(
                    id = 5,
                    category = GameCategory.FRUIT_VEG,
                    title = "Buah apa aku? Bulat, segar, dan kaya vitamin C berwarna orange.",
                    imageRes = R.drawable.jeruk,
                    options = listOf("Jeruk", "Apel", "Pisang", "Wortel"),
                    correctAnswer = "Jeruk"
                ),
                Question(
                    id = 6,
                    category = GameCategory.FRUIT_VEG,
                    title = "Buah apa aku? Hijau di luar, merah manis berair di dalam.",
                    imageRes = R.drawable.semangka,
                    options = listOf("Semangka", "Mangga", "Stroberi", "Anggur"),
                    correctAnswer = "Semangka"
                ),
                Question(
                    id = 7,
                    category = GameCategory.FRUIT_VEG,
                    title = "Buah apa aku? Merah kecil dengan bintik-bintik biji di luar.",
                    imageRes = R.drawable.stroberi,
                    options = listOf("Stroberi", "Apel", "Jeruk", "Pisang"),
                    correctAnswer = "Stroberi"
                ),
                Question(
                    id = 8,
                    category = GameCategory.FRUIT_VEG,
                    title = "Sayuran apa aku? Berwarna kuning, bijinya manis enak dibakar.",
                    imageRes = R.drawable.jagung,
                    options = listOf("Jagung", "Brokoli", "Wortel", "Bayam"),
                    correctAnswer = "Jagung"
                ),
                Question(
                    id = 9,
                    category = GameCategory.FRUIT_VEG,
                    title = "Buah apa aku? Berwarna hijau harum dan sangat manis saat matang.",
                    imageRes = R.drawable.mangga,
                    options = listOf("Mangga", "Semangka", "Stroberi", "Jeruk"),
                    correctAnswer = "Mangga"
                ),
                Question(
                    id = 10,
                    category = GameCategory.FRUIT_VEG,
                    title = "Sayuran apa aku? Daun hijau sehat yang disukai Popeye.",
                    imageRes = R.drawable.bayam,
                    options = listOf("Bayam", "Brokoli", "Wortel", "Jagung"),
                    correctAnswer = "Bayam"
                ),
                Question(
                    id = 11,
                    category = GameCategory.FRUIT_VEG,
                    title = "Buah apa aku? Ungu atau hijau, kecil bergerombol dalam tandan.",
                    imageRes = R.drawable.anggur,
                    options = listOf("Anggur", "Apel", "Jeruk", "Mangga"),
                    correctAnswer = "Anggur"
                ),
                Question(
                    id = 12,
                    category = GameCategory.FRUIT_VEG,
                    title = "Buah apa aku? Daging lembut berwarna hijau mentega.",
                    imageRes = R.drawable.alpukat,
                    options = listOf("Alpukat", "Pisang", "Semangka", "Stroberi"),
                    correctAnswer = "Alpukat"
                ),
                Question(
                    id = 13,
                    category = GameCategory.FRUIT_VEG,
                    title = "Buah apa aku? Kulit berduri dan ada mahkota di atasnya.",
                    imageRes = R.drawable.nanas,
                    options = listOf("Nanas", "Mangga", "Jeruk", "Apel"),
                    correctAnswer = "Nanas"
                ),
                Question(
                    id = 14,
                    category = GameCategory.FRUIT_VEG,
                    title = "Buah apa aku? Bulat segar berdaging kuning manis.",
                    imageRes = R.drawable.melon,
                    options = listOf("Melon", "Mangga", "Stroberi", "Jeruk"),
                    correctAnswer = "Melon"
                ),
                Question(
                    id = 15,
                    category = GameCategory.FRUIT_VEG,
                    title = "Buah apa aku? Berambut cokelat berisi air kelapa segar.",
                    imageRes = R.drawable.kelapa,
                    options = listOf("Kelapa", "Semangka", "Nanas", "Melon"),
                    correctAnswer = "Kelapa"
                ),
                Question(
                    id = 16,
                    category = GameCategory.FRUIT_VEG,
                    title = "Buah apa aku? Merah kecil mirip buah beri, berpasangan seperti Anting.",
                    imageRes = R.drawable.ceri,
                    options = listOf("Ceri", "Stroberi", "Apel", "Jeruk"),
                    correctAnswer = "Ceri"
                ),
                Question(
                    id = 17,
                    category = GameCategory.FRUIT_VEG,
                    title = "Buah apa aku? Kuning asam segar, kaya akan vitamin C.",
                    imageRes = R.drawable.lemon,
                    options = listOf("Lemon", "Jeruk", "Mangga", "Nanas"),
                    correctAnswer = "Lemon"
                ),
                Question(
                    id = 18,
                    category = GameCategory.FRUIT_VEG,
                    title = "Sayuran apa aku? Merah segar, sering dikira buah padahal sayur.",
                    imageRes = R.drawable.tomat,
                    options = listOf("Tomat", "Wortel", "Brokoli", "Bayam"),
                    correctAnswer = "Tomat"
                ),
                Question(
                    id = 19,
                    category = GameCategory.FRUIT_VEG,
                    title = "Sayuran apa aku? Umbi cokelat di dalam tanah yang lezat digoreng.",
                    imageRes = R.drawable.kentang,
                    options = listOf("Kentang", "Jagung", "Wortel", "Bayam"),
                    correctAnswer = "Kentang"
                ),
                Question(
                    id = 20,
                    category = GameCategory.FRUIT_VEG,
                    title = "Sayuran apa aku? Panjang hijau berair, biasa jadi lalapan segar.",
                    imageRes = R.drawable.timun,
                    options = listOf("Timun", "Tomat", "Wortel", "Brokoli"),
                    correctAnswer = "Timun"
                ),
                Question(
                    id = 21,
                    category = GameCategory.FRUIT_VEG,
                    title = "Sayuran apa aku? Berwarna ungu tua lonjong dan lembut dimasak.",
                    imageRes = R.drawable.terong,
                    options = listOf("Terong", "Tomat", "Kentang", "Timun"),
                    correctAnswer = "Terong"
                ),
                Question(
                    id = 22,
                    category = GameCategory.FRUIT_VEG,
                    title = "Sayuran apa aku? Kecil merah pedas membakar lidah.",
                    imageRes = R.drawable.cabai,
                    options = listOf("Cabai", "Tomat", "Wortel", "Brokoli"),
                    correctAnswer = "Cabai"
                ),
                Question(
                    id = 23,
                    category = GameCategory.FRUIT_VEG,
                    title = "Bumbu dapur apa aku? Berlapis-lapis dan bikin menangis saat diiris.",
                    imageRes = R.drawable.bawang,
                    options = listOf("Bawang", "Kentang", "Wortel", "Tomat"),
                    correctAnswer = "Bawang"
                ),
                Question(
                    id = 24,
                    category = GameCategory.FRUIT_VEG,
                    title = "Sayuran apa aku? Tumbuh di tempat lembap seperti payung kecil.",
                    imageRes = R.drawable.jamur,
                    options = listOf("Jamur", "Kentang", "Bawang", "Kol"),
                    correctAnswer = "Jamur"
                ),
                Question(
                    id = 25,
                    category = GameCategory.FRUIT_VEG,
                    title = "Biji-bijian apa aku? Gurih dan enak dijadikan selai atau camilan.",
                    imageRes = R.drawable.kacang,
                    options = listOf("Kacang", "Jagung", "Wortel", "Kentang"),
                    correctAnswer = "Kacang"
                )
            )
            GameCategory.ANIMAL -> listOf(
                Question(
                    id = 101,
                    category = GameCategory.ANIMAL,
                    title = "Hewan apa aku? Suka mengeong dan sangat lucu.",
                    imageRes = R.drawable.kucing, // Ganti dengan R.drawable.kucing
                    options = listOf("Kucing", "Anjing", "Sapi", "Kelinci"),
                    correctAnswer = "Kucing"
                ),
                Question(
                    id = 102,
                    category = GameCategory.ANIMAL,
                    title = "Hewan apa aku? Setia pada manusia dan menggonggong.",
                    imageRes = R.drawable.anjing,
                    options = listOf("Anjing", "Kucing", "Monyet", "Kambing"),
                    correctAnswer = "Anjing"
                ),
                Question(
                    id = 103,
                    category = GameCategory.ANIMAL,
                    title = "Hewan apa aku? Bertubuh sangat besar dan punya belalai panjang.",
                    imageRes = R.drawable.gajah,
                    options = listOf("Gajah", "Singa", "Kuda", "Harimau"),
                    correctAnswer = "Gajah"
                ),
                Question(
                    id = 104,
                    category = GameCategory.ANIMAL,
                    title = "Hewan apa aku? Raja hutan yang bersuara auman sangat keras.",
                    imageRes = R.drawable.singa,
                    options = listOf("Singa", "Gajah", "Kera", "Bebek"),
                    correctAnswer = "Singa"
                ),
                Question(
                    id = 105,
                    category = GameCategory.ANIMAL,
                    title = "Hewan apa aku? Suka melompat dan suka makan wortel.",
                    imageRes = R.drawable.kelinci,
                    options = listOf("Kelinci", "Kucing", "Anjing", "Sapi"),
                    correctAnswer = "Kelinci"
                ),
                Question(
                    id = 106,
                    category = GameCategory.ANIMAL,
                    title = "Hewan apa aku? Bisa terbang tinggi di langit dengan sayap.",
                    imageRes = R.drawable.burung,
                    options = listOf("Burung", "Bebek", "Kucing", "Sapi"),
                    correctAnswer = "Burung"
                ),
                Question(
                    id = 107,
                    category = GameCategory.ANIMAL,
                    title = "Hewan apa aku? Penghasil susu segar dan bersuara 'Moo'.",
                    imageRes = R.drawable.sapi,
                    options = listOf("Sapi", "Kambing", "Kuda", "Domba"),
                    correctAnswer = "Sapi"
                ),
                Question(
                    id = 108,
                    category = GameCategory.ANIMAL,
                    title = "Hewan apa aku? Suka memanjat pohon dan sangat suka pisang.",
                    imageRes = R.drawable.monyet,
                    options = listOf("Monyet", "Singa", "Gajah", "Kelinci"),
                    correctAnswer = "Monyet"
                ),
                Question(
                    id = 109,
                    category = GameCategory.ANIMAL,
                    title = "Hewan apa aku? Berenang di air dan bersuara 'Kwek kwek'.",
                    imageRes = R.drawable.bebek,
                    options = listOf("Bebek", "Burung", "Kucing", "Anjing"),
                    correctAnswer = "Bebek"
                ),
                Question(
                    id = 110,
                    category = GameCategory.ANIMAL,
                    title = "Hewan apa aku? Kucing besar berwarna belang-belang oren hitam.",
                    imageRes = R.drawable.harimau,
                    options = listOf("Harimau", "Singa", "Gajah", "Kuda"),
                    correctAnswer = "Harimau"
                ),
                Question(
                    id = 111,
                    category = GameCategory.ANIMAL,
                    title = "Hewan apa aku? Kuat berlari kencang dan suka ditunggangi.",
                    imageRes = R.drawable.kuda,
                    options = listOf("Kuda", "Sapi", "Kambing", "Kucing"),
                    correctAnswer = "Kuda"
                ),
                Question(
                    id = 112,
                    category = GameCategory.ANIMAL,
                    title = "Hewan apa aku? Bertanduk dan berbulu tebal, bersuara 'Mbeee'.",
                    imageRes = R.drawable.kambing,
                    options = listOf("Kambing", "Sapi", "Kuda", "Kelinci"),
                    correctAnswer = "Kambing"
                ),
                Question(
                    id = 113,
                    category = GameCategory.ANIMAL,
                    title = "Hewan apa aku? Unggas berkokok di pagi hari dan bertelur.",
                    imageRes = R.drawable.ayam,
                    options = listOf("Ayam", "Bebek", "Burung", "Kucing"),
                    correctAnswer = "Ayam"
                ),
                Question(
                    id = 114,
                    category = GameCategory.ANIMAL,
                    title = "Hewan apa aku? Hidup di dalam air bernapas dengan insang.",
                    imageRes = R.drawable.ikan,
                    options = listOf("Ikan", "Bebek", "Burung", "Kucing"),
                    correctAnswer = "Ikan"
                ),
                Question(
                    id = 115,
                    category = GameCategory.ANIMAL,
                    title = "Hewan apa aku? Berjalan sangat lambat dan membawa cangkang di punggung.",
                    imageRes = R.drawable.kurakura,
                    options = listOf("Kura-kura", "Katak", "Ikan", "Kucing"),
                    correctAnswer = "Kura-kura"
                ),
                Question(
                    id = 116,
                    category = GameCategory.ANIMAL,
                    title = "Hewan apa aku? Melata tidak punya kaki dan mendesis.",
                    imageRes = R.drawable.ular,
                    options = listOf("Ular", "Katak", "Kura-kura", "Kucing"),
                    correctAnswer = "Ular"
                ),
                Question(
                    id = 117,
                    category = GameCategory.ANIMAL,
                    title = "Hewan apa aku? Bisa melompat jauh di air dan darat.",
                    imageRes = R.drawable.kodok,
                    options = listOf("Katak", "Ikan", "Bebek", "Ular"),
                    correctAnswer = "Katak"
                ),
                Question(
                    id = 118,
                    category = GameCategory.ANIMAL,
                    title = "Hewan apa aku? Lehernya sangat panjang untuk memakan daun di pohon tinggi.",
                    imageRes = R.drawable.jerapah,
                    options = listOf("Jerapah", "Gajah", "Singa", "Harimau"),
                    correctAnswer = "Jerapah"
                ),
                Question(
                    id = 119,
                    category = GameCategory.ANIMAL,
                    title = "Hewan apa aku? Berbulu hitam putih asal China suka makan bambu.",
                    imageRes = R.drawable.panda,
                    options = listOf("Panda", "Kucing", "Kelinci", "Monyet"),
                    correctAnswer = "Panda"
                ),
                Question(
                    id = 120,
                    category = GameCategory.ANIMAL,
                    title = "Hewan apa aku? Bertubuh besar berbulu lebat, suka tidur di musim dingin.",
                    imageRes = R.drawable.beruang,
                    options = listOf("Beruang", "Singa", "Harimau", "Gajah"),
                    correctAnswer = "Beruang"
                ),
                Question(
                    id = 121,
                    category = GameCategory.ANIMAL,
                    title = "Hewan apa aku? Mirip anjing liar yang melolong di malam hari.",
                    imageRes = R.drawable.serigala,
                    options = listOf("Serigala", "Anjing", "Singa", "Harimau"),
                    correctAnswer = "Serigala"
                ),
                Question(
                    id = 122,
                    category = GameCategory.ANIMAL,
                    title = "Hewan apa aku? Kuda unik berpola belang hitam putih.",
                    imageRes = R.drawable.zebra,
                    options = listOf("Zebra", "Kuda", "Sapi", "Kambing"),
                    correctAnswer = "Zebra"
                ),
                Question(
                    id = 123,
                    category = GameCategory.ANIMAL,
                    title = "Hewan apa aku? Punya kantong di perut untuk anaknya dan suka melompat.",
                    imageRes = R.drawable.kangguru,
                    options = listOf("Kangguru", "Kelinci", "Monyet", "Kuda"),
                    correctAnswer = "Kangguru"
                ),
                Question(
                    id = 124,
                    category = GameCategory.ANIMAL,
                    title = "Hewan apa aku? Burung kutub berjas hitam putih yang tidak bisa terbang.",
                    imageRes = R.drawable.penguin,
                    options = listOf("Penguin", "Bebek", "Burung", "Ikan"),
                    correctAnswer = "Penguin"
                ),
                Question(
                    id = 125,
                    category = GameCategory.ANIMAL,
                    title = "Hewan apa aku? Mamalia laut cerdas yang suka melompat di atas air.",
                    imageRes = R.drawable.lumbalumba,
                    options = listOf("Lumba-lumba", "Ikan", "Bebek", "Kura-kura"),
                    correctAnswer = "Lumba-lumba"
                )
            )
        }
    }
}
