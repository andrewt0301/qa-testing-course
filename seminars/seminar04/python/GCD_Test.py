import unittest
import GCD


class GCDTestCase(unittest.TestCase):
    """
    Tests for the GCD class from the GCD module.
    """

    @classmethod
    def setUpClass(cls):
        """
        Executed before all tests of the class.
        """
        pass

    @classmethod
    def tearDownClass(cls):
        """
        Executed after all tests of the class.
        """
        pass

    def setUp(self):
        """
        Executed before each test.
        """
        self.gcd = GCD.GCD()
        pass

    def tearDown(self):
        """
        Executed after each test
        """
        pass

    def test_positive_basic(self):
        """
        Test for positive numbers that have a GCD != 1.
        """
        x = self.gcd.gcd(50, 30)
        self.assertEqual(x, 10)

    def test_positive_prime_numbers(self):
        """
        Test for positive prime numbers.
        """
        x = self.gcd.gcd(13, 31)
        self.assertEqual(x, 1)

    @unittest.expectedFailure
    def test_failure(self):
        """
        Test with an expected failure.
        """
        self.fail("Expected failure")

    @unittest.skip('Skipped')
    def test_skipped(self):
        """
        Skipped test.
        """
        pass


if __name__ == '__main__':
    unittest.main()

