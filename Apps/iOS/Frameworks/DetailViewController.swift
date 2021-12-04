//
//  DetailViewController.swift
//  AppleFrameworks
//
//  Created by APPS2T on 23/11/21.
//

import UIKit
import SafariServices

class DetailViewController: UIViewController {
    
    var framework: Framework?
         
    @IBOutlet var imageView: UIImageView!
    @IBOutlet var nameLabel: UILabel!
    @IBOutlet var descriptionText: UITextView!
    @IBOutlet var learnButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.navigationController?.navigationBar.tintColor = UIColor(named: "black")
        renderUI()
        learnButton.layer.cornerRadius = 6
    }
    
    private func renderUI()  {
        guard let framework = framework else { return }
        nameLabel.text = framework.name
        imageView.image = UIImage(named: framework.imageName)
        descriptionText.text = framework.description
    }
    
    @IBAction func safariButton(_ sender: Any) {
        if let url = URL(string: framework?.urlString ?? ""){
            let safari = SFSafariViewController(url: url)
            safari.modalPresentationStyle = .automatic
            present(safari, animated: true, completion: nil)
        }
    }
}
