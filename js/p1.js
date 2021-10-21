class TaxCalculator { 
  static taxRate = 10; 
  /**
   * * @param {number} grossIncome The gross income. 
   * * @param {number} deduction The total that is deductible before tax calculation. 
   * */ 
  constructor(grossIncome, deduction) { 
    this.grossIncome = grossIncome; this.deduction = deduction; 
  } 
  /** * @return {number} The payable tax after deducting deductible total from gross income. */ 
  
  getPayableTax() { 
    return (TaxCalculator.taxRate / 100) * (this.grossIncome - this.deduction); } 
}

ans = new TaxCalculator(1000, 100).getPayableTax();
console.log(ans);

14 - 16 - 66 - 33 - 45 - 41 - 76 - 50 - 35
45 - 

왼()
오()
출력()


14,16,66,

33, 45, 66
