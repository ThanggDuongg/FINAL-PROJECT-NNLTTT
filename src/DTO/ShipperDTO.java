package DTO;

import DTO.AbstractDTO.AbstractPerson;

public class ShipperDTO extends AbstractPerson {
    //fields
    private static final float heSo = 5.1F;
    private double Salary;
    enum Bac{
        Bac0(0),
        Bac1(1),
        Bac2(2),
        Bac3(3),
        Bac4(4),
        Bac5(5),
        Bac6(6),
        Bac7(7);
        public int value;

        private Bac(int value) {
            this.value = value;
        }
    }

    //constructor
    public ShipperDTO() {
    }

    public ShipperDTO(double salary) {
        Salary = salary;
    }

    public ShipperDTO(Integer ID, String firstname, String lastname, String phone, String gender,
                      int age, String email, String password, double salary) {
        super(ID, firstname, lastname, phone, gender, age, email, password);
        Salary = salary;
    }

    public ShipperDTO(String firstname, String lastname, String phone, String gender, int age, String email, String password, double salary) {
        super(firstname, lastname, phone, gender, age, email, password);
        Salary = salary;
    }

    //getter & setter
    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
        Salary = salary;
    }

    //methods
    @Override
    public String toString() {
        return "ShipperDTO{" +
                "Salary=" + Salary +
                '}';
    }

    @Override
    public double tinhLuong() {
        return Math.round(this.TinhThue(heSo) * 10)/10;
    }

    private double GiamTru(double heso)
    {
        double GiamTruGiaCanhBanThan = 11000000;
        //8% BHXH, 1.5% BHYT, 1% BHTN
        double BaoHiem = this.Salary * heso * (0.08 + 0.015 + 0.01);
        double GiamTru = GiamTruGiaCanhBanThan + BaoHiem;
        return GiamTru;
    }

    private int XacDinhBacThue(double heso)
    {
        double ThuNhapTinhThue = this.Salary * heso - this.GiamTru(heso);
        if (ThuNhapTinhThue <= 0)
            return Bac.Bac0.ordinal();

        if (ThuNhapTinhThue <= 500  )
            return Bac.Bac1.ordinal();
        else
        {
            if (ThuNhapTinhThue <= 10000000)
            {
                return Bac.Bac2.ordinal();
            }
            else
            {
                if (ThuNhapTinhThue <= 18000000)
                {
                    return Bac.Bac3.ordinal();
                }
                else
                {
                    if (ThuNhapTinhThue <= 32000000)
                    {
                        return Bac.Bac4.ordinal();
                    }
                    else
                    {
                        if (ThuNhapTinhThue <= 52000000)
                        {
                            return Bac.Bac5.ordinal();
                        }
                        else
                        {
                            if (ThuNhapTinhThue <= 80000000)
                            {
                                return Bac.Bac6.ordinal();
                            }
                            else
                                return Bac.Bac7.ordinal();
                        }
                    }
                }
            }
        }
    }

    private double TinhThue(double heso)
    {
        double ThuNhapTinhThue = this.Salary * heso - this.GiamTru(heso);
        int Bac = XacDinhBacThue(heso);
        double SoThuePhaiNop = 0;
        switch (Bac)
        {
            case 0:
            {
                return 0;
            }
            case 1:
            {
                SoThuePhaiNop = ThuNhapTinhThue * 0.05;
                break;
            }
            case 2:
            {
                SoThuePhaiNop = (ThuNhapTinhThue * 0.1) - 250000;
                break;
            }
            case 3:
            {
                SoThuePhaiNop = (ThuNhapTinhThue * 0.15) - 750000;
                break;
            }
            case 4:
            {
                SoThuePhaiNop = (ThuNhapTinhThue * 0.2) - 1650000;
                break;
            }
            case 5:
            {
                SoThuePhaiNop = (ThuNhapTinhThue * 0.25) - 3250000;
                break;
            }
            case 6:
            {
                SoThuePhaiNop = (ThuNhapTinhThue * 0.3) - 5850000;
                break;
            }
            default:
            {
                SoThuePhaiNop = (ThuNhapTinhThue * 0.35) - 9850000;
                break;
            }
        }
        return SoThuePhaiNop;
    }
}
