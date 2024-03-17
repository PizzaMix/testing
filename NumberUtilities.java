import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.StringTokenizer;

public class NumberUtilities {

        public static final MathContext mathContext = new MathContext(15, RoundingMode.HALF_UP);
        public static final MathContext mathContext4 = new MathContext(4, RoundingMode.HALF_UP);
        public static final MathContext mathContext2 = new MathContext(2, RoundingMode.HALF_UP);

        public static final MathContext mathContext1 = new MathContext(1, RoundingMode.HALF_UP);
        public static final MathContext zeroPrecMathContext = new MathContext(0, RoundingMode.HALF_UP);

        public static final RoundingMode DefaultRoundingMode = RoundingMode.HALF_UP;
        public static final BigDecimal BDMILLION = new BigDecimal("1000000");
        public static final BigDecimal BDTHOUSAND = new BigDecimal("1000");
        public static final BigDecimal HUNDRED = new BigDecimal("100.0");
        private static final String NF = "#,##0";

        public enum NF_FORMAT {

            F1("#########0,0000000"),

            F2("#,##0"),

            F5("#0.00000");

            private String format = null;

            NF_FORMAT(String format) {
                this.format = format;
            }

            public String getFormat() {
                return this.format;
            }
        }

        public static NumberFormat getNumberFormat() {
            DecimalFormat df = new DecimalFormat(NF);
            df.setRoundingMode(DefaultRoundingMode);
            return df;
        }

        public static NumberFormat getNumberFormat(Locale locale, int digits) {
            NumberFormat df = NumberFormat.getInstance(locale);
            df.setMaximumFractionDigits(digits);
            df.setRoundingMode(DefaultRoundingMode);
            return df;
        }

    public static String asString(BigDecimal bd) {
        DecimalFormat df = (DecimalFormat)DecimalFormat.getInstance(Locale.ENGLISH);
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        df.setGroupingSize(3);
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(bd.doubleValue());
    }
    public static String asStringAmount(Double bd) {
        DecimalFormat df = (DecimalFormat)DecimalFormat.getInstance(Locale.ENGLISH);
        df.setRoundingMode(RoundingMode.HALF_UP);
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        return df.format(bd);
    }

    public static String asStringAmount(Double bd, Locale loc) {
        DecimalFormat df = (DecimalFormat)DecimalFormat.getInstance(loc);
        df.setRoundingMode(RoundingMode.HALF_UP);
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        return df.format(bd);
    }

    public static Locale getLocale(String localeString) {
        StringTokenizer st = null;
        if (localeString.contains("-")) st = new StringTokenizer(localeString, "-", false);
        else  st = new StringTokenizer(localeString, "_", false);

        Locale ret = null;
        if (st.countTokens() == 3) {
            ret = new Locale(st.nextToken(), st.nextToken(), st.nextToken());
        }
        if (st.countTokens() == 2) {
            ret = new Locale(st.nextToken(), st.nextToken());
        }
        if (st.countTokens() == 1) {
            ret = new Locale(st.nextToken());
        }

        if (ret == null) throw new RuntimeException(localeString + " is not valid locale specification");

        return ret;
    }

        public static NumberFormat getNumberFormat(NF_FORMAT fmt) {
            NumberFormat df = new DecimalFormat(fmt.getFormat());
            df.setRoundingMode(DefaultRoundingMode);
            return df;
        }

        public static BigDecimal toZeroIfNull(BigDecimal amount) {
            return amount != null ? amount : BigDecimal.ZERO;
        }

        public static BigDecimal toBigDecimal(Object value) {
            if (value == null) return null;

            if (value instanceof BigDecimal) return (BigDecimal)value;
            if (value instanceof Integer) return BigDecimal.valueOf((Integer)value);
            if (value instanceof String) return new BigDecimal((String)value);
            if (value instanceof Double) return BigDecimal.valueOf((Double) value);

            throw new UnsupportedOperationException("toBigDecimal conversion not defined for object of type " + value.getClass().getName());
        }

        public static boolean isNumeric(String sn) {
            if (sn == null) return false;
            if (sn.length() == 0) return false;

            return sn.matches("-?\\d+(\\.\\d+)?");
        }

        public static BigDecimal negate(BigDecimal bd) {
            if (bd == null) return null;
            if (bd.compareTo(BigDecimal.ZERO) == 0) return bd;

            return bd.negate();
        }
        public static BigDecimal abs(BigDecimal bd) {
            if (bd == null) return null;
            if (bd.compareTo(BigDecimal.ZERO) == 0) return bd;

            return bd.abs();
        }
    }
