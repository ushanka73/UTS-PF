import scala.annotation.tailrec
import scala.io.StdIn

case class BalBako(id: String, berat: Double, kadarair: Double, posisidaun: String)
case class BalGiling(id: String, beratawal: Double, grade: String, estimasiberatakhir: Double, tindakan: String)

object SistemBalBako {
  def tentukanGrade(posisiDaun: String): String = posisiDaun match
    case "bawah" => "Grade C"
    case "tengah" => "Grade B"
    case "atas"  => "Grade A"
    case _       => "Tidak Ada Grade"

  def lossRateFermentasi(grade: String): Double = grade match
    case "Grade A" => 0.15
    case "Grade B" => 0.12
    case "Grade C" => 0.10
    case _         => 0.20

  val ambilKadarAir: BalBako => Double = _.kadarair
  
  val tentukanTindakan: Double => String = kadar =>
    if kadar > 18.0 then "Diperlukan Penjemuran"
    else if kadar < 12.0 then "Diperlukan proses oven"
    else "Siap digiling"

  val tindakanGudang: BalBako => String = ambilKadarAir andThen tentukanTindakan

  def ambilInfoBal(bal: BalBako, formatter: BalBako => String): String = 
    formatter(bal)

  def ambilBalIdeal(balList: List[BalBako]): List[String] = 
    balList
      .filter(b => b.kadarair >= 12.0 && b.kadarair <= 18.0)
      .map(b => s"Bal ${b.id} (${b.berat} kg) siap digiling")

  @tailrec
  def hitungTotalEstimasiBerat(balList: List[BalGiling], accumulator: Double): Double = {
    if (balList.isEmpty) {
        accumulator
    } else {
        hitungTotalEstimasiBerat(balList.tail, accumulator + balList.head.estimasiberatakhir) 
    }
  }

  @tailrec
  def inputUser(sisaInput: Int, daftarBal: List[BalBako]): List[BalBako] =
    if (sisaInput == 0) then
      daftarBal
    else
      println(s"\n--- Masukkan Data Bal ke-${daftarBal.length + 1} ---")
      print("ID Bal (misal: TB-01): ")
      val id = StdIn.readLine()
      
      print("Berat awal (Kg): ")
      val berat = StdIn.readDouble()
      
      print("Kadar Air (%): ")
      val kadar = StdIn.readDouble()
      
      print("Posisi Daun (bawah/tengah/atas): ")
      val posisi = StdIn.readLine()
      
      val balBaru = BalBako(id, berat, kadar, posisi)
      inputUser(sisaInput - 1, daftarBal :+ balBaru)

  @main def main(): Unit =
    println("=== SISTEM BALBAKO ===")
    print("Masukkan jumlah Bal yang akan diinput: ")
    val totalBal = StdIn.readInt()

    val daftarBalMasuk = inputUser(totalBal, List.empty)
    
    println("\n=== HASIL ===")

    println("\nTindakan Gudang:")
    daftarBalMasuk.foreach(b => println(s" - Bal ${b.id}: ${tindakanGudang(b)}"))

    println("\nInfo Lengkap Semua Bal:")
    val semuaInfoBal = daftarBalMasuk.map(bal => 
      ambilInfoBal(bal, b => s"ID: ${b.id} / Posisi: ${b.posisidaun} / Grade: ${tentukanGrade(b.posisidaun)}")
    )
    semuaInfoBal.foreach(println)

    println("\nBal dengan Kadar Air Ideal:")
    val balIdeal = ambilBalIdeal(daftarBalMasuk)
    if (balIdeal.isEmpty) println("[!] Tidak ada bal dengan kadar air ideal.")
    else balIdeal.foreach(b => println(s" - $b"))

    println("\nEstimasi Berat Akhir:")

    val daftarBalGiling: List[BalGiling] = daftarBalMasuk.map(b => {
      val grade = tentukanGrade(b.posisidaun)
      val estimasi = b.berat * (1.0 - lossRateFermentasi(grade))
      BalGiling(b.id, b.berat, grade, estimasi, tindakanGudang(b))
    })

    println("Detail Estimasi per Bal:")
    daftarBalGiling.foreach(bg => 
      println(f" - ${bg.id} | Awal: ${bg.beratawal}%.2f kg | Grade: ${bg.grade} | Estimasi Akhir: ${bg.estimasiberatakhir}%.2f kg")
    )

    val totalBeratAkhir = hitungTotalEstimasiBerat(daftarBalGiling, 0.0)
    println(f"TOTAL estimasi berat akhir semua bal: $totalBeratAkhir%.2f kg")
    
    println("\n=== END ===")
}